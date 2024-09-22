package com.JJH.homebroker.model;

import lombok.Getter;

@Getter
public enum OrderType{
    MARKET_ORDER("Ordem à Mercado"),
    LIMIT_ORDER("Ordem Limitada"),
    STOP_LOSS("Stop Loss"),
    STOP_GAIN("Stop Gain"),
    STOP_LIMIT("Stop-Limit"),
    TRAILING_STOP("Trailing Stop"),
    ICEBERG_ORDER("Ordem Iceberg"),
    ALL_OR_NONE("All or None"),
    DISCRETIONARY_ORDER("Ordem Discricionária"),
    GTC("Good 'Til Cancelled");

    OrderType(String name){}

}
