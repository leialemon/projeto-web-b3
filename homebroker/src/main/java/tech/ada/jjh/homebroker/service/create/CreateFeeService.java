package tech.ada.jjh.homebroker.service.create;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.model.Fee;
import tech.ada.jjh.homebroker.model.FeeType;
import tech.ada.jjh.homebroker.repository.FeeRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreateFeeService {

    private final FeeRepository feeRepository;

    public CreateFeeService(FeeRepository feeRepository){
        this.feeRepository = feeRepository;
    }

    public List<Fee> createBasicFees(){
        List<Fee> fees = new ArrayList<>();
        Fee b3emolument = new Fee();
        b3emolument.setAmount(0.03);
        b3emolument.setName("b3emoluments");
        b3emolument.setType(FeeType.EMOLUMENT);
        fees.add(b3emolument);
        feeRepository.save(b3emolument);

        Fee taxes = new Fee();
        taxes.setName("taxes");
        taxes.setAmount(0.02);
        taxes.setType(FeeType.TAX);
        fees.add(taxes);
        feeRepository.save(taxes);
        return fees;
    }
}
