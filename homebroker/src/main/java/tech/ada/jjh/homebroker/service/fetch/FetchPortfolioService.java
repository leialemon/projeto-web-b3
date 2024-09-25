package tech.ada.jjh.homebroker.service.fetch;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.model.Portfolio;
import tech.ada.jjh.homebroker.repository.PortfolioRepository;
import java.util.List;
import java.util.Optional;

@Service
public class FetchPortfolioService {
    private final PortfolioRepository portfolioRepository;
    public FetchPortfolioService(PortfolioRepository portfolioRepository){
        this.portfolioRepository = portfolioRepository;
    }

    public List<Portfolio> fetchAll(){
        return portfolioRepository.findAll();
    }


    public Optional<Portfolio> fetchById(Long id){
        return portfolioRepository.findById(id);
    }
}

