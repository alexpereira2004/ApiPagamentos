# API Pagamentos
## Visão Geral
Este projeto atende a implementação de endpoints que simulam operações bancárias, mais especificamente transações 
financeiras comuns realizadas com cartão de crédito.

Por se tratar de um projeto demo os dados são persistidos em banco de dados H2 (em memória).

As operações serão as seguintes:

- ### Pagamento

> POST<br> 
> [http://localhost:8080/v1/pagamento](http://localhost:8080/v1/pagamento)
```json
    {
    "transacao": {
        "cartao": "4444555566661234",
        "id" : "1000235689000005",
        "descricao": {
            "valor" : "500.00",
            "dataHora": "01/05/2021 18:30:00",
            "estabelecimento": "Pet Shop do Adão"
        },
        "formaPagamento": {
            "tipo": "AVISTA",
            "parcelas": 1
        }
    }
}
```

- ###Estorno:
> PATCH <br>
> http://localhost:8080/v1/pagamento/{{Pagamento_Id}}/estorno

- ###Consulta:
  - ####Todos
  > GET <br>
  > http://localhost:8080/v1/pagamento/
  - ####Por ID
  > GET <br>
  > http://localhost:8080/v1/pagamento/{{Pagamento_Id}}


### Validações


### Como executar
O projeto contém um servidor Tomcat embarcado e com ele é possível executar a aplicação.

Pré-requisito para executar o projeto é possuir instalado o JDK 11.

No Windows, executar o seguinte comando:
> ./mvnw spring-boot:run

Para executar os testes unitários, executar o seguinte comando:
> ./mvnw test

### Convenções
- A entrada de dados de data e hora deverá seguir um padrão para todo o projeto que se baseia no formato "dd/MM/yyyy H:mm:ss". 