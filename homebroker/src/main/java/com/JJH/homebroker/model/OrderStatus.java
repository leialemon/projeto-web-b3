package com.JJH.homebroker.model;

import lombok.Getter;

@Getter
public enum OrderStatus{
    PENDING("Em Espera"),
    PLACED("Colocada"),
    PARTIALLY_FILLED("Parcialmente Executada"),
    FILLED("Executada"),
    CANCELLED("Cancelada"),
    REJECTED("Rejeitada"),
    EXPIRED("Expirada"),
    PENDING_CANCEL("Cancelamento Pendente"),
    PENDING_REPLACE("Substituição Pendente"),
    SUSPENDED("Suspensa"),
    STOPPED("Parada"),
    QUEUING("Em Fila"),
    AMENDED("Alterada");

    private String name;

    OrderStatus(String name){
        this.name = name;

    }

}
