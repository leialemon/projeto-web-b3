package tech.ada.jjh.homebroker.controller;

import org.springframework.web.bind.annotation.*;
import tech.ada.jjh.homebroker.dto.TransactionDTORequest;
import tech.ada.jjh.homebroker.dto.TransactionDTOResponse;
import tech.ada.jjh.homebroker.service.create.CreateTransactionService;

@RestController
@RequestMapping("api/v1/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final CreateTransactionService createTransactionService;


    public TransactionController(CreateTransactionService createTransactionService){
        this.createTransactionService = createTransactionService;
    }

    @PostMapping
    public TransactionDTOResponse createTransaction(@RequestBody TransactionDTORequest transactionDTORequest){
        return createTransactionService.createTransaction(transactionDTORequest);
    }
}
