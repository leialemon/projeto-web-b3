## (Em andamento)Projeto para o módulo Programação Web 2 do curso Back end em Java ADA B3+ inclua.

## Diagrama de classes e CRC cards

```mermaid
---
title: ADAHomebroker
---
%% TODO: 1. Ir atualizando os métodos das classes conforme a necessidade;
%% TODO: 2. Modelar classes dos outros pacotes.
classDiagram

namespace model {
    class ModelClasses
    class ModelCRCCards
}

namespace ModelClasses {

class User {
    String name
    String password
    String email
    String birthday
    String cpf - key
    BigDecimal balance
    List ~Portfolio~ portfolios
    List ~Transaction~ transactionHistory
    List ~Order~ orderHistory
    getters()
    setters()
}

class Order{
    Long id - key
    BigDecimal price
    int status
    Portfolio portfolio
    ENUM.Type type
    Map ~Stocks~ stocks
    getters()
    setters()
}

class Stock{
    String ticker
    String name
    BigDecimal price
    getters()
    setters()
}

class Broker{
    Long id
    String name
    List ~Double~ fees
    getters()
    setters()
}

class Portfolio{
    User user
    Broker broker
    List ~Stock~ stocks
    getters()
    setters()
}

class Transaction{
    Long id
    BigDecimal amount
    LocalDateTime dateTime
    getters()
    setters()
}

}

User --> Transaction
User -- Portfolio

Order --> Portfolio
Order --> Stock

Portfolio --> Broker
Portfolio --> Stock

namespace ModelCRCCards{
    class UserCRC
    class OrderCRC
    class PortfolioCRC
    class TransactionCRC
    class BrokerCRC
    class StockCRC
}

namespace UserCRC{
    class UserResponsibiliy{
        Represents the user of the system. \n It's the starting point for most of the processes 
    }
    class UserCollaboration{
        Portfolio
        Transaction
        Order
    }
}

namespace OrderCRC{
    class OrderResponsibiliy{
        Represents the user's orders. \n Manages the contents of a portfolio.
    }
    class OrderCollaboration{
        Portfolio
        Stock
    }
}

namespace PortfolioCRC{
    class PortfolioResponsibiliy{
        Created when an user adds a broker. \n Contains Stocks. \n All orders must be placed from a portfolio.
    }
    class PortfolioCollaboration{
        Broker
        User
        Order
        Stock
    }
}

namespace TransactionCRC{
    class TransactionResponsibiliy{
        Modifies an user's balance.
    }
    class TransactionCollaboration{
        User
    }
}

namespace BrokerCRC{
    class BrokerResponsibiliy{
        Represents the brokerage firms through which \n the user places the orders. \n Creates an unique portfolio for the user. \n Defines if fees are charged in an order \n and their amounts.
    }
    class BrokerCollaboration{
        Portfolio
    }
}

namespace StockCRC{
    class StockResponsibiliy{
        The negotiable asset. \n Contains the price that will inform \n if an automatic order will trigger.
    }
    class StockCollaboration
}

namespace repository{
    class Repository
}

namespace service{
    class Service
}

namespace controller{
    class Controller
}
```

## Modelagem de dados

