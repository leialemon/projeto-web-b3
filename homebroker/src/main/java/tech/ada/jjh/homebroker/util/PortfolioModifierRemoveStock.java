package tech.ada.jjh.homebroker.util;

import tech.ada.jjh.homebroker.model.AppUser;
import tech.ada.jjh.homebroker.model.Order;

public class PortfolioModifierRemoveStock implements PortfolioModifier{
    @Override
    public AppUser modifyPortfolio(Order order) {
        AppUser user = order.getUser();
        Integer quantity = user.getPortfolio().get(order.getStock());
        quantity = quantity - order.getStockQuantity();
        if (quantity == 0){
            user.getPortfolio().remove(order.getStock());
        } else {
            user.getPortfolio().replace(order.getStock(), quantity);
        }
        return user;
    }
}
