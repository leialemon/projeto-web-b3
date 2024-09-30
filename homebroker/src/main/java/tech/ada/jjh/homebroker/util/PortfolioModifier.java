package tech.ada.jjh.homebroker.util;

import tech.ada.jjh.homebroker.model.AppUser;
import tech.ada.jjh.homebroker.model.Order;

public interface PortfolioModifier {
    AppUser modifyPortfolio(Order order);
}
