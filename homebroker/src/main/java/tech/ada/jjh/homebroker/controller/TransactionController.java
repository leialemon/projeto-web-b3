package tech.ada.jjh.homebroker.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.jjh.homebroker.dto.TransactionDTO;
import tech.ada.jjh.homebroker.service.create.CreateTransactionService;
import tech.ada.jjh.homebroker.service.patch.PatchUserService;

@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {

    private final CreateTransactionService createTransactionService;


    public TransactionController(CreateTransactionService createTransactionService){
        this.createTransactionService = createTransactionService;
    }

    @PostMapping
    public TransactionDTO createTransaction(@RequestBody TransactionDTO transactionDTO){
        return createTransactionService.createTransaction(transactionDTO);
    }
}
