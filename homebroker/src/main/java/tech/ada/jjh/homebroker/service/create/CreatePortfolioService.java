package tech.ada.jjh.homebroker.service.create;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.model.Portfolio;
import tech.ada.jjh.homebroker.repository.PortfolioRepository;

@Service
public class CreatePortfolioService{

    private final PortfolioRepository portfolioRepository;

    public CreatePortfolioService(PortfolioRepository portfolioRepository){
        this.portfolioRepository = portfolioRepository;
    }

    public Portfolio createPortfolio(Portfolio portfolio){
        return portfolioRepository.save(portfolio);
    }
}
