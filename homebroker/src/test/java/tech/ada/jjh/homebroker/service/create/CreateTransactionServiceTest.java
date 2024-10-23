package tech.ada.jjh.homebroker.service.create;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.jjh.homebroker.dto.TransactionDTORequest;
import tech.ada.jjh.homebroker.dto.TransactionDTOResponse;
import tech.ada.jjh.homebroker.mapper.TransactionMapper;
import tech.ada.jjh.homebroker.model.AppUser;
import tech.ada.jjh.homebroker.model.Transaction;
import tech.ada.jjh.homebroker.model.TransactionType;
import tech.ada.jjh.homebroker.repository.TransactionRepository;
import tech.ada.jjh.homebroker.service.fetch.FetchUserService;
import tech.ada.jjh.homebroker.service.patch.PatchUserService;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class CreateTransactionServiceTest {

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    FetchUserService fetchUserService;

    @Mock
    TransactionMapper transactionMapper;

    @Mock
    PatchUserService patchUserService;

    @InjectMocks
    CreateTransactionService createTransactionService;

    @Test
    void mustReturnTheCreatedTransaction(){
        TransactionDTORequest transactionDTORequest = new TransactionDTORequest();
        TransactionDTOResponse transactionDTOResponse = new TransactionDTOResponse();
        Transaction transaction = new Transaction();

        AppUser user = new AppUser();
        user.setCpf("123456");
        user.setTransactionHistory(new ArrayList<>());

        transactionDTORequest.setUserCpf(user.getCpf());
        transactionDTORequest.setType(TransactionType.DEPOSIT);
        transaction.setType(transactionDTORequest.getType());

        Mockito.when(fetchUserService.getByCpf(user.getCpf())).thenReturn(user);
        Mockito.when(transactionMapper.toEntity(transactionDTORequest)).thenReturn(transaction);
        Mockito.when(transactionRepository.save(transaction)).thenReturn(transaction);
        Mockito.when(transactionMapper.transactionToTransactionDTOResponse(transaction)).thenReturn(transactionDTOResponse);

        createTransactionService.createTransaction(transactionDTORequest);

        Assertions.assertEquals(1, user.getTransactionHistory().size());
    }
}