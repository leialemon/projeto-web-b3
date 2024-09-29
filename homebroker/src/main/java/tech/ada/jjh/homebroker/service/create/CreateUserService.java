package tech.ada.jjh.homebroker.service.create;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.dto.AppUserDTOResponse;
import tech.ada.jjh.homebroker.dto.AppUserDTORequest;
import tech.ada.jjh.homebroker.mapper.AppUserMapper;
import tech.ada.jjh.homebroker.model.AppUser;
import tech.ada.jjh.homebroker.model.Stock;
import tech.ada.jjh.homebroker.repository.UserRepository;
import tech.ada.jjh.homebroker.service.fetch.FetchOrderService;
import tech.ada.jjh.homebroker.service.fetch.FetchStockService;

import java.math.BigDecimal;
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
        entity.setTransactionHistory(new ArrayList<>());
        entity.setOrderHistory(new ArrayList<>());
        entity.setPortfolio(new HashMap<>());
        entity.setBalance(BigDecimal.ZERO);
        entity = userRepository.save(entity);
        return appUserMapper.toAppUserDTOResponse(entity);
    }

//    public static AppUserDTOResponse mapPortfolio(AppUserDTOResponse response, AppUser entity){
//        response.setPortfolio(new HashMap<>());
//        for (Stock stock : entity.getPortfolio().keySet()){
//            response.getPortfolio().put(stock.getTicker(), entity.getPortfolio().get(stock));
//        }
//        return response;
//    }
}
