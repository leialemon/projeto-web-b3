package tech.ada.jjh.homebroker.service.create;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.config.IsAMinorException;
import tech.ada.jjh.homebroker.dto.AppUserDTOResponse;
import tech.ada.jjh.homebroker.dto.AppUserDTORequest;
import tech.ada.jjh.homebroker.mapper.AppUserMapper;
import tech.ada.jjh.homebroker.model.AppUser;
import tech.ada.jjh.homebroker.model.Stock;
import tech.ada.jjh.homebroker.repository.UserRepository;
import tech.ada.jjh.homebroker.service.fetch.FetchOrderService;
import tech.ada.jjh.homebroker.service.fetch.FetchStockService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class CreateUserService {

    private final UserRepository userRepository;
    private final AppUserMapper appUserMapper;


    public CreateUserService(UserRepository userRepository, AppUserMapper appUserMapper){
        this.userRepository = userRepository;
        this.appUserMapper = appUserMapper;
    }

    public AppUserDTOResponse create(AppUserDTORequest appUserDTORequest){
        //Regra de idade. Retornar uma exceção caso a pessoa for menor de 18 anos?
        AppUser entity = appUserMapper.toAppUser(appUserDTORequest);
        if (!isAdult(entity.getBirthDate())){
            throw new IsAMinorException();
        }
        entity.setTransactionHistory(new ArrayList<>());
        entity.setOrderHistory(new ArrayList<>());
        entity.setPortfolio(new HashMap<>());
        entity.setBalance(BigDecimal.ZERO);
        entity = userRepository.save(entity);
        return appUserMapper.toAppUserDTOResponse(entity);
    }

    private boolean isAdult(String birthDateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthDate = LocalDate.parse(birthDateString, formatter);
            LocalDate currentDate = LocalDate.now();
            Period age = Period.between(birthDate, currentDate);
            return age.getYears() >= 18;

        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Use dd/MM/yyyy.");
            return false;
        }
    }
}
