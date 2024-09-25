# 🛠️ (Em andamento) Projeto para o módulo Programação Web 2 do curso Back end em Java ADA B3+ inclua.
## Tecnologias utilizadas
<p align="center">
    <a href="https://skillicons.dev">
        <img src="https://skillicons.dev/icons?i=java,maven,spring,postman,idea" alt="ícones das tecnologias usadas no projeto: java, maven, spring, postman e idea.">
    </a>
</p>

## Diagrama de classes 

```mermaid
classDiagram
namespace ModelClasses {

class User {
    String cpf - key
    String name
    String password
    String email
    String birthday
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
    int stockQt
    Stock stock
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
    User appUser
    Broker broker
    List ~Stock~ stocks
    getters()
    setters()
}

class Transaction{
    Long id
    BigDecimal amount
    LocalDateTime dateTime
    User appUser
    getters()
    setters()
}

}

User -- Transaction
User -- Portfolio
User --> Order

Order --> Portfolio
Order --> Stock

Portfolio --> Broker
Portfolio --> Stock
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

### Referências

ANICHE, Maurício. **Orientação a Objetos e SOLID para ninjas**: projetando classes flexíveis. São Paulo: Casa do Código, 2015.

BROOCH, Grady; RUMBAUGH, James; JACOBSON, Ivar. **UML, guia do usuário**. trad. Fábio Freitas da Silva. Rio de Janeiro: Campus, 2000.

MARTIN, Robert C. **Código limpo**: habilidades práticas do Agile Software. Rio de Janeiro: Alta Books, 2011.

RUMBAUGH, James et al. **Modelagem e projetos baseados em objetos**. trad. Dalton Conde de Alencar. Rio de Janeiro: Campus, 1994.
