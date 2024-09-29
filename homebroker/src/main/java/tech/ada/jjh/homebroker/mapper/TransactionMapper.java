package tech.ada.jjh.homebroker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import tech.ada.jjh.homebroker.dto.TransactionDTORequest;
import tech.ada.jjh.homebroker.dto.TransactionDTOResponse;
import tech.ada.jjh.homebroker.model.Transaction;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {


    Transaction toEntity(TransactionDTORequest transactionDTORequest);

    @Mapping(target = "user", source = "appUser")
    TransactionDTOResponse transactionToTransactionDTOResponse(Transaction transaction);

    List<TransactionDTOResponse> listToDto(List<Transaction> transactions);
}
