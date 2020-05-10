# CONSIGNADO
<p>Aplicação de Demonstração para Crédito Consignado</p>
<p>Copyright © 2020 Richardson Software Ltda.</p>

## Introdução

Essa aplicação tem o intuito de apresentar um CRUD simples, que realiza busca de ofertas de crédito consignado, respeitando a nova <b>Instrução Normativa nº 100, do Instituto Nacional do Seguro Social, de 28/12/2018</b>.

Com a nova Instrução Normativa, os bancos somente poderão oferecer crédito consignado, aos aposentados e pensionistas, mediante as regras abaixo:

* **Regra nº 1**

  * Todo cidadão que se aposentar a partir de 30/03/2019, terá seu benefício bloqueado automaticamente, pelo período de 90 dias, a partir da data de concessão pelo INSS (Data de Despacho do Benefício - DDB).

* **Regra nº 2**

  * Após 90 dias da Data de Despacho do Benefício, o aposentado ou pensionista poderá contratar um crédito consignado. Para isto, é necessário que um termo de pré-autorização seja assinado pelo beneficiário para que a instituição financeira possa solicitar o acesso às informações do benefício ao INSS. Com a aprovação, pelo INSS, do termo, será considerado mais um prazo de 90 dias a partir da Data de Autorização da Consulta aos Dados, para que seja permitido aos bancos ofertarem crédito consignado.

* **Regra nº 3**

  * Passados 180 dias da Data de Despacho do Benefício (DDB), 90 dias do bloqueio mais 90 dias para frente, os bancos podem iniciar o processo de oferta de crédito consignado aos seus clientes aposentados e pensionistas, desde que o intervalo entre a Data de Despacho do Benefício, e a Data de Autorizaão da Consulta, seja de, no mínimo, 90 dias (DDB - DAC >= 90).

## Tecnologias Utilizadas

* Java (JDK 13);
* Spring Boot;
* Maven;
* REST;
* H2 In-memory Database

## Operações:

* **recuperarOfertas:**

  Essa operação realiza a busca dos Beneficiarios INSS cuja Data de Despacho do Benefício (DDB) seja igual ou inferior à data corrente subtraída de 180 dias

  * **Endpoint:** /consignado/recuperarOfertas
  * **Método:** GET
  * **Consumo Local:**
    ```
    curl -X GET "http://localhost:8080/consignado/recuperarOfertas" -H "accept: application/json"
    ```
  * **Response:** Listagem de BeneficiarioDTO
  * **Exemplo de Resposta:**
  ```javascript
  [
    {
      "cpfCliente": "004835706056",
      "nomeCliente": "CLIENTE PROPOSTA AUTORIZADA",
      "margemDisponivel": 0.3,
      "numeroBeneficio": 87654321,
      "dataAutorizacaoConsulta": "2020-01-02",
      "dataNascimentoCliente": "1958-07-20",
      "dataDespachoBeneficio": "2019-08-21"
    },
    {
      "cpfCliente": "011894307208",
      "nomeCliente": "CLIENTE PROPOSTA DDB ANTERIOR A 180 DIAS, DAC SUPERIOR A 90 DIAS",
      "margemDisponivel": 0.2,
      "numeroBeneficio": 12345678,
      "dataAutorizacaoConsulta": "2019-12-02",
      "dataNascimentoCliente": "1952-09-01",
      "dataDespachoBeneficio": "2019-09-01"
    },
    {
      "cpfCliente": "027864377840",
      "nomeCliente": "CLIENTE NÃO EMITIU AUTORIZAÇÃO PARA CONSULTA DADOS",
      "margemDisponivel": 0.32,
      "numeroBeneficio": 98765432,
      "dataAutorizacaoConsulta": null,
      "dataNascimentoCliente": "1950-12-21",
      "dataDespachoBeneficio": "2019-07-01"
    }
  ]
  ```

* **recuperarOfertasAutorizadas:**

  Essa operação realiza a busca dos Beneficiarios INSS cuja Data de Despacho do Benefício (DDB) seja igual ou inferior à data corrente subtraída de 180 dias, porém haja assinatura da pré-autorização (Data de Autorização da Consulta - DAC), e a diferença entre a DAC e a DDB seja de, no mínimo, 90 dias

  * **Endpoint:** /consignado/recuperarOfertasAutorizadas
  * **Método:** GET
  * **Consumo Local:**
  ```
  curl -X GET "http://localhost:8080/consignado/recuperarOfertasAutorizadas" -H "accept: application/json"
  ```
  * **Response:** Listagem de BeneficiarioDTO
  * **Exemplo de Resposta:**
  ```javascript
  [
    {
      "cpfCliente": "004835706056",
      "nomeCliente": "CLIENTE PROPOSTA AUTORIZADA",
      "margemDisponivel": 0.3,
      "numeroBeneficio": 87654321,
      "dataAutorizacaoConsulta": "2020-01-02",
      "dataNascimentoCliente": "1958-07-20",
      "dataDespachoBeneficio": "2019-08-21"
    },
    {
      "cpfCliente": "011894307208",
      "nomeCliente": "CLIENTE PROPOSTA DDB ANTERIOR A 180 DIAS, DAC SUPERIOR A 90 DIAS",
      "margemDisponivel": 0.2,
      "numeroBeneficio": 12345678,
      "dataAutorizacaoConsulta": "2019-12-02",
      "dataNascimentoCliente": "1952-09-01",
      "dataDespachoBeneficio": "2019-09-01"
    }
  ]
  ```

* **armazenarCreditoContratado:**

  Essa operação realiza a persistência dos eventuais *prospects* de crédito consignado, na base de dados solicitada

  * **Endpoint:** /consignado
  * **Método:** POST
  * **Request:** CreditoContratadoDTO
  * **Exemplo de Requisição:**
  ```javascript
  {
    "cpfCliente": "00000000191",
    "dataEnceramentoContrato": "2020-10-08",
    "dataInicioContrato": "2020-04-08",
    "dataVencimentoParcela": "10",
    "numeroBeneficio": 6654333,
    "quantidadeParcelas": 5,
    "valorContratado": 1000.00
  }
  ```
  * **Consumo Local:**
  ```
  curl -X POST "http://localhost:8080/consignado" -H "accept: */*" -H "Content-Type: application/json" 
  -d "{ \"cpfCliente\": \"00000000191\", \"dataEnceramentoContrato\": \"2020-10-08\", 
  \"dataInicioContrato\": \"2020-04-08\", \"dataVencimentoParcela\": \"10\", \"numeroBeneficio\": 6654333, 
  \"quantidadeParcelas\": 5, \"valorContratado\": 1000.00 }"
  ```
  * **Response:** CreditoContratadoDTO persistido
  * **Exemplo de Resposta:**
  ```javascript
  {
    "id": 1,
    "cpfCliente": "00000000191",
    "valorContratado": 1000,
    "quantidadeParcelas": 5,
    "dataVencimentoParcela": "10",
    "dataInicioContrato": "2020-04-08",
    "dataEnceramentoContrato": "2020-10-08",
    "numeroBeneficio": 6654333
  }
  ```

## Modelo de Dados:

* **BeneficiarioDTO:**
  ```javascript
  {
    "cpfCliente": "String",
    "nomeCliente": "String",
    "margemDisponivel": Double,
    "numeroBeneficio": Integer,
    "dataAutorizacaoConsulta": "String",
    "dataNascimentoCliente": "String",
    "dataDespachoBeneficio": "String"
  }
  ```
* **CreditoContratadoDTO:**
  ```javascript
  {
    "id": Integer,
    "cpfCliente": "String",
    "dataEnceramentoContrato": "String",
    "dataInicioContrato": "String",
    "dataVencimentoParcela": "String",
    "numeroBeneficio": Integer,
    "quantidadeParcelas": Integer,
    "valorContratado": Integer
  }
  ```

## Execução em Nuvem

* Acessar a URL [https://teste-consignado.herokuapp.com/](https://teste-consignado.herokuapp.com/), via Postman ou via navegador. Em sendo acessada via navegador, será apresentada a interface do Swagger com as operações disponíveis.

## Execução Local

* Utilizando um terminal (Command Prompt do Windows, Git Bash ou terminal do Linux), acessar o diretório raiz da aplicação (teste-consignado);

* **Maven:**

  * Digitar o comando:
    ```
    mvn spring-boot:run
    ```
  
  * Acessar http://localhost:8080 no navegador de preferência;
  * Será apresentado o Swagger da aplicação

* **Execução via jar:**

  * Realizar o build do projeto com sua ferramenta preferencial (para Maven, comando mvn install);
  * Digitar o comando:
    ```
    java -jar target/teste-consignado-1.0.0-SNAPSHOT.jar
    ```

  * Acessar http://localhost:8080 no navegador de preferência;
  * Será apresentado o Swagger da aplicação
  
## Swagger

<p align="center"><img src="https://github.com/jokarichardson/teste-consignado/blob/master/src/main/resources/swagger.png" /></p>
