package tech.ada.jjh.homebroker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.jjh.homebroker.model.Portfolio;
import tech.ada.jjh.homebroker.service.create.CreatePortfolioService;
import tech.ada.jjh.homebroker.service.fetch.FetchPortfolioService;

import java.util.List;

@RestController
@RequestMapping("api/v1/portfolios")
public class PortfolioController {
    private final CreatePortfolioService createPortfolioService;
    private final FetchPortfolioService fetchPortfolioService;

    public PortfolioController(CreatePortfolioService createPortfolioService, FetchPortfolioService fetchPortfolioService){
        this.createPortfolioService = createPortfolioService;
        this.fetchPortfolioService = fetchPortfolioService;
    }

    public List<Portfolio> fetchAll(){
        return fetchPortfolioService.fetchAll();
    }

//    public Portfolio createPortfolio(){
//
//    }
}
