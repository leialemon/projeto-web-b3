package tech.ada.jjh.homebroker.model;

public enum OrderStatus {
    PENDING,
    EXECUTED,
    CANCELED_BY_USER,
    CANCELED_LACK_FUNDS,
    CANCELED_NOT_ENOUGH_STOCK,
    EXPIRED
}
