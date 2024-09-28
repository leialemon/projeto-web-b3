package tech.ada.jjh.homebroker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import tech.ada.jjh.homebroker.dto.TransactionDTO;
import tech.ada.jjh.homebroker.model.Transaction;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {
    Transaction toEntity(TransactionDTO transactionDTO);

    TransactionDTO toDto(Transaction transaction);

    List<TransactionDTO> listToDto(List<Transaction> transactions);
}
