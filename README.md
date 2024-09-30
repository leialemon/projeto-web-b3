# 🛠️ (Em andamento) Projeto para o módulo Programação Web 2 do curso Back end em Java ADA B3+ inclua.
## Tecnologias utilizadas
<p align="center">
    <a href="https://skillicons.dev">
        <img src="https://skillicons.dev/icons?i=java,maven,spring,hibernate,postman,idea" alt="ícones das tecnologias usadas no projeto: java, maven, spring, postman e idea.">
    </a>
</p>

## Documentação OpenAPI 

<img src="./README-contents/swagger.png" alt="Ícone swagger"> <p> A documentação OpenAPI spec está disponível no link: https://leialemon.github.io/Homebroker-ADA-B3-Swagger/</p>

## Diagrama de classes 

```mermaid
classDiagram
direction LR
class AppUser {
    - String cpf 
    - String name
    - String email
    - String password
    - String birthday
    - BigDecimal balance
    - List ~Transaction~ transactionHistoryDTO
    - List ~Portfolio~ portfolios
    - List ~Order~ orderHistory
    getters()
    setters()
}

class Order{
    - Stock stock
    - int stockQuantity
    - BigDecimal rawPrice
    - BigDecimal totalPrice
    - Portfolio portfolio
    - LocalDateTime dateTimeExecution
    List~Fee~ fees
    getters()
    setters()
}

class Stock{
    - String ticker
    - String name
    - BigDecimal price
    getters()
    setters()
}

class Broker{
    - String name
    - List ~Fee~ fees
    getters()
    setters()
}

class Portfolio{
    - AppUser appUser
    - Broker broker
    - List ~Stock~ stocks
    - List ~Order~ orderHistory
    getters()
    setters()
}

class Transaction{
    - BigDecimal amount
    - LocalDateTime dateTime
    - AppUser appUser
    getters()
    setters()
}

class Fee{
   - Double amount
   - ENUM.FeeType type
}

AppUser -- Transaction
AppUser -- Portfolio
AppUser --> Order

Order -- Portfolio
Order --> Stock
Order --> Fee

Portfolio --> Broker
Portfolio --> Stock

Broker --> Fee
```
## Implementação de diferentes regras de cálculo para cada Fee
```mermaid
classDiagram
 class Fee{
   - Double amount
   - ENUM.FeeType type
    }
    class FeeType{
        - FeeCalculationRule calculationRule
        getCalculationRule()
    }
    class FeeCalculationRule{
        calculate(BigDecimal orderPrice, Double feeAmount) BigDecimal
    }
    class FeeFixedCalculationRule{
        calculate(BigDecimal orderPrice, Double feeAmount) BigDecimal
    }
    class FeePercentileCalculationRule{
        calculate(BigDecimal orderPrice, Double feeAmount) BigDecimal
    }

<<Enumeration>> FeeType
<<Interface>> FeeCalculationRule
Fee --> FeeType
FeeType --> FeeCalculationRule
FeeCalculationRule <|.. FeeFixedCalculationRule
FeeCalculationRule <|.. FeePercentileCalculationRule
```

## CRC Cards

```mermaid
classDiagram
direction RL
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
        Represents the appUser of the system. \n It's the starting point for most of the processes 
    }
    class UserCollaboration{
        Portfolio
        Transaction
        Order
    }
}

namespace OrderCRC{
    class OrderResponsibiliy{
        Represents the appUser's orders. \n Manages the contents of a portfolio.
    }
    class OrderCollaboration{
        Portfolio
        Stock
    }
}

namespace PortfolioCRC{
    class PortfolioResponsibiliy{
        Created when an appUser adds a broker. \n Contains Stocks. \n All orders must be placed from a portfolio.
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
        Modifies an appUser's balance.
    }
    class TransactionCollaboration{
        User
    }
}

namespace BrokerCRC{
    class BrokerResponsibiliy{
        Represents the brokerage firms through which \n the appUser places the orders. \n Creates an unique portfolio for the appUser. \n Defines if fees are charged in an order \n and their amounts.
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
```

## Modelagem de dados

<p align="center">
    <img src="./README-contents/EERProjetoB3.png" alt="Imagem do diagrama de modelagem de dados do projeto.">
</p>

## Padrões e escolhas de design
