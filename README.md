# Homebroker ADA-B3
### Projeto para o módulo Programação Web 2 do curso Back end em Java ADA B3+ inclua.

## Sobre o projeto

O Homebroker é uma API REST desenvolvida em Java com Spring boot. Foi desenvolvido para aplicar os conhecimentos adquiridos sobre Spring Boot, REST e aplicações web no geral.

Seu propósito é simular um sistema de corretagem online, onde os usuários podem gerenciar suas ações e fazer operações de compra e venda de ativos financeiros.

#### Desenvolvido pelos alunos [Juliana Barros](https://github.com/leialemon), [Nero Haziel](https://github.com/HepoHB) e Jebson Trindade.

## Usabilidade

> Este repositório possui um arquivo [JSON](Homebroker.postman_collection.json) com requisições do Postman que pode ser usadas para testar as funcionalidades da API. Bem como um [front-end minificado](miniFront) que desempenha as mesmas funções.

### Documentação OpenAPI 
<p align="center">
<img  width="50" height="50" src="./README-contents/swagger.png" alt="Ícone swagger"> 
</p>
<p> A documentação OpenAPI spec está disponível no link: https://leialemon.github.io/Homebroker-ADA-B3-Swagger/</p>


# Detalhes técnicos

## Tecnologias utilizadas
<p align="center">
    <a href="https://skillicons.dev">
        <img src="https://skillicons.dev/icons?i=java,maven,spring,hibernate,postman,idea" alt="ícones das tecnologias usadas no projeto: java, maven, spring, postman e idea.">
    </a>
</p>

### Outras ferramentas

- Para agilizar o desenvolvimento do código, utilizamos o [Mapstruct](https://mapstruct.org/) para mapear as transformações entre as entidades do banco de dados e seus DTOs. Adicionalmente, criamos manualmente a classe StockTickerMapper, de modo a alimentar o código gerado pelo Mapstruct com transformações específicas à nossas entidades. As classes de mapeamento estão salvas no pacote `mapper`.

- Utilizamos [H2](https://www.h2database.com/html/main.html) com JPA como banco de dados em memória.

- A documentação OpenAPI foi gerada com [Springdoc](https://springdoc.org/).


## Modelagem do projeto

O projeto foi modelado a partir do mapeamento das entidades-chave em um [diagrama EER](https://github.com/leialemon/Ada-Homebroker?tab=readme-ov-file#modelagem-de-dados) e em um [diagrama UML de classes](https://github.com/leialemon/Ada-Homebroker/blob/main/README.md#diagrama-de-classes), seguidos da implementação em código.

O padrão de design Strategy foi utilizado para gerenciar as regras de cálculo de preço de ordens e as modificações no saldo e portfolio do usuário. As classes que implementam este padrão estão no pacote `util`.

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
    - Map ~Stock, Integer~ portfolio
    - List ~Transaction~ transactionHistory
    - List ~Order~ orderHistory
    getters()
    setters()
}

class Order{
    - String uuid
    - Stock stock
    - Integer stockQuantity
    - BigDecimal rawPrice
    - BigDecimal totalPrice
    - AppUser user
    - ENUM.OrderStatus status
    - ENUM.OrderType type
    - LocalDateTime dateTimeCreation
    - LocalDateTime dateTimeExecution
    - List~Fee~ fees
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


class Transaction{
    - BigDecimal amount
    - LocalDateTime dateTime
    - AppUser appUser
    - ENUM.TransactionType type
    - ENUM.TransactionStatus status
    getters()
    setters()
}

class Fee{
   - Double amount
   - ENUM.FeeType type
}
```

## Modelagem de dados

<p align="center">
    <img src="README-contents/EERProjetoADAB3.png">    
</p>

