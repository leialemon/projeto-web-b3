package tech.ada.jjh.homebroker.service.create;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.exceptions.NotEnoughFundsException;
import tech.ada.jjh.homebroker.dto.TransactionDTORequest;
import tech.ada.jjh.homebroker.dto.TransactionDTOResponse;
import tech.ada.jjh.homebroker.mapper.TransactionMapper;
import tech.ada.jjh.homebroker.model.Transaction;
import tech.ada.jjh.homebroker.model.TransactionStatus;
import tech.ada.jjh.homebroker.model.TransactionType;
import tech.ada.jjh.homebroker.repository.TransactionRepository;
import tech.ada.jjh.homebroker.service.fetch.FetchUserService;
import tech.ada.jjh.homebroker.service.patch.PatchUserService;

import java.time.LocalDateTime;

@Service
public class CreateTransactionService {
    private final TransactionRepository transactionRepository;
    private final FetchUserService fetchUserService;
    private final TransactionMapper transactionMapper;
    private final PatchUserService patchUserService;

    public CreateTransactionService(TransactionRepository transactionRepository, FetchUserService fetchUserService, TransactionMapper transactionMapper, PatchUserService patchUserService){
        this.transactionRepository = transactionRepository;
        this.fetchUserService = fetchUserService;
        this.transactionMapper = transactionMapper;
        this.patchUserService = patchUserService;
    }

    public TransactionDTOResponse createTransaction(TransactionDTORequest transaction){
        Transaction entity = transactionMapper.toEntity(transaction);
        entity.setAppUser(fetchUserService.getByCpf(transaction.getUserCpf()));
        entity.setDateTime(LocalDateTime.now());
        checkFunds(entity);
        entity.getAppUser().getTransactionHistory().add(entity);
        return transactionMapper.transactionToTransactionDTOResponse(transactionRepository.save(entity));
    }

    private void checkFunds(Transaction transaction){
        if (transaction.getType().equals(TransactionType.WITHDRAWAL)){
            if(transaction.getAmount().compareTo(transaction.getAppUser().getBalance()) > 0){
                transaction.setStatus(TransactionStatus.CANCELED);
                throw new NotEnoughFundsException("Saldo insuficiente para saque nesse valor");
            } else{
                patchUserService.modifyUserBalance(transaction);
                transaction.setStatus(TransactionStatus.COMPLETED);
            }
        } else {
            patchUserService.modifyUserBalance(transaction);
            transaction.setStatus(TransactionStatus.COMPLETED);
        }
    }
}



