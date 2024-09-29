package tech.ada.jjh.homebroker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.ada.jjh.homebroker.mapper.StockTickerMapper;

@Configuration
public class MapperConfig {
    @Bean
    public StockTickerMapper stockTickerMapper() {
        return new StockTickerMapper(); // use a implementação gerada pelo MapStruct
    }
}
