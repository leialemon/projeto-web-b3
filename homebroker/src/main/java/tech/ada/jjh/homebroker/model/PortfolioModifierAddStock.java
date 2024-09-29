package tech.ada.jjh.homebroker.model;

public class PortfolioModifierAddStock implements PortfolioModifier{
    @Override
    public AppUser modifyPortfolio(Order order) {
        AppUser user = order.getUser();
        user.getPortfolio().put(order.getStock(), order.getStockQuantity());
        return user;
    }
}
