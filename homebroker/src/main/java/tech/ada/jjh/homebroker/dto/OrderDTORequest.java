package tech.ada.jjh.homebroker.dto;


public class OrderDTORequest {

    private String stockTicker;

    private Integer stockQuantity;

    public String getStock() {
        return this.stockTicker;
    }

    public void setStock(String stock) {
        this.stockTicker = stock;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
