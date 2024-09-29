package tech.ada.jjh.homebroker.mapper;


import tech.ada.jjh.homebroker.model.Stock;
import java.util.HashMap;


public class StockTickerMapper {
    public StockTickerMapper() {
    }

    public String toTicker(Stock stock){
        return stock.getTicker();
    }

    public HashMap<String, Integer> mapToTicker(HashMap<Stock, Integer> portfolio){
        HashMap<String, Integer> response = new HashMap<>();
        for (Stock stock : portfolio.keySet()){
            response.put(stock.getTicker(), portfolio.get(stock));
        }
        return response;
    }
}
