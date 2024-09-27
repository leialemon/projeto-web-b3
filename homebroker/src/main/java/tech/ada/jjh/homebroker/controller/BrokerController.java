package tech.ada.jjh.homebroker.controller;

import org.springframework.web.bind.annotation.*;
import tech.ada.jjh.homebroker.dto.BrokerDTO;
import tech.ada.jjh.homebroker.model.Fee;
import tech.ada.jjh.homebroker.service.create.CreateBrokerService;
import tech.ada.jjh.homebroker.service.create.CreateFeeService;
import tech.ada.jjh.homebroker.service.fetch.FetchBrokerService;

@RestController
@RequestMapping("api/v1/brokers")
public class BrokerController {

    private final CreateBrokerService createBrokerService;
    private final FetchBrokerService fetchBrokerService;
    private final CreateFeeService createFeeService;

    public BrokerController(CreateBrokerService createBrokerService, FetchBrokerService fetchBrokerService, CreateFeeService createFeeService){
        this.createBrokerService = createBrokerService;
        this.fetchBrokerService = fetchBrokerService;
        this.createFeeService = createFeeService;
    }

    @PostMapping()
    public BrokerDTO createBroker(@RequestBody BrokerDTO brokerDTO){
        return createBrokerService.createBroker(brokerDTO);
    }

    @PatchMapping
    public BrokerDTO insertBrokerFee(Fee fee){

    }
}
