# SELETOR-ROTAS
<p>Avaliador da melhor rota entre dois pontos, considerando-se o menor custo</p>
<p>Copyright © 2020 Richardson Software Ltda.</p>

## Introdução

Essa aplicação tem o intuito de buscar a rota mais barata, entre dois pontos, independentemente da quantidade de pontos intermediários.

O problema gerador dessa solução aduz que um viajante deseja ir de uma origem a um destino, pagando o menor valor possível, não importando a quantidade de escalas que venha a passar.

Trata-se de uma abordagem pessoal do famoso algoritmo criado por Edsger Djisktra, em 1956 (publicado em 1959), utilizando-se somente de Listas e Mapas Java.

Para maiores informações acerca do Algoritmo de Djikstra, acesse [http://www.inf.ufsc.br/grafos/temas/custo-minimo/dijkstra.html](http://www.inf.ufsc.br/grafos/temas/custo-minimo/dijkstra.html).

Para uma implementação Java do mesmo, acesse [https://www.baeldung.com/java-dijkstra](https://www.baeldung.com/java-dijkstra).  

## Tecnologias Utilizadas

* Java (JDK 13);
* Spring Boot;
* Maven;
* REST;
* Console;
* Arquivos CSV

## Execução Local - ConsoleApplication

* Utilizando um terminal (Command Prompt do Windows, Git Bash ou terminal do Linux), acessar o diretório raiz da aplicação (seletor-rotas);

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
    java -jar target/seletor-rotas-1.0.0-SNAPSHOT.jar
    ```

  * Acessar http://localhost:8080 no navegador de preferência;
  * Será apresentado o Swagger da aplicação

## Execução Local - WebApplication

* Utilizando um terminal (Command Prompt do Windows, Git Bash ou terminal do Linux), acessar o diretório raiz da aplicação (seletor-rotas);

* **Maven:**

  * Digitar o comando:
    ```
    mvn spring-boot:run
    ```

* **Execução via jar:**

  * Realizar o build do projeto com sua ferramenta preferencial (para Maven, comando mvn install);
  * Digitar o comando:
    ```
    java -jar target/seletor-rotas-1.0.0-SNAPSHOT.jar
    ```

## Utilização ConsoleApplication:



## Utilização WebApplication - Operações REST

* **Recuperar Rotas:**

  Essa operação realiza a busca pelas rotas cadastradas inicialmente.

  * **Endpoint:** /seletorrotas/rotas
  * **Método:** GET
  * **Consumo Local:**
    ```
    curl -X GET "http://localhost:8080//seletorrotas/rotas" -H "accept: application/json"
    ```
  * **Response:** Listagem de Rotas
  * **Exemplo de Resposta:**
    ```javascript
    [
      {
        "origem": "GRU",
        "destino": "BRC",
        "custo": 10
      },
      {
        "origem": "BRC",
        "destino": "SCL",
        "custo": 5
      },
      {
        "origem": "GRU",
        "destino": "CDG",
        "custo": 75
      },
      {
        "origem": "GRU",
        "destino": "SCL",
        "custo": 20
      },
      {
        "origem": "GRU",
        "destino": "ORL",
        "custo": 56
      },
      {
        "origem": "ORL",
        "destino": "CDG",
        "custo": 8
      },
      {
        "origem": "SCL",
        "destino": "ORL",
        "custo": 20
      },
      {
        "origem": "BRC",
        "destino": "CDG",
        "custo": 35
      }
    ]
    ```

* **Recuperar Melhor Rota:**

  Essa operação realiza a busca pela rota mais barata, entre uma origem e um destino, a partir das rotas cadastradas inicialmente.

  * **Endpoint:** /seletorrotas/melhor-rota
  * **Método:** GET
  * **Parâmetros:**
    * **origem:** local de origem da rota. Exemplo: origem=GRU
    * **destino:** local de destino da rota. Exemplo: destino=CDG
  * **Consumo Local:**
    ```
    curl -X GET "http://localhost:8080/seletorrotas/melhor-rota?origem=GRU&destino=CDG" 
    -H "accept: application/json"
    ```
  * **Response:** String com a melhor rota encontrada
  * **Exemplo de Resposta:**
    ```javascript
    GRU - BRC - SCL - ORL - CDG
    ```

* **Incluir uma Rota:**

  Essa operação realiza a persistência de uma nova rota na lista de rotas em memória.

  * **Endpoint:** /seletorrotas/rotas
  * **Método:** POST
  * **Request:** Objeto Rota
  * **Exemplo de Requisição:**
    ```javascript
    {
      "origem": "CDG",
      "destino": "GRU",
      "custo": "50"
    }
    ```
  * **Consumo Local:**
    ```
    curl -X POST "http://localhost:8080/seletorrotas/rotas" -H "Content-Type: application/json" 
    -d "{ \"origem\": \"CDG\", \"destino\": \"GRU\", \"custo\": 50 }"
    ```
  * **Response: HttpStatus 201 - CREATED**

## Modelo de Dados:

* **Rota:**
  ```javascript
  {
    "origem": "String",
    "destino": "String",
    "custo": Integer
  }
  ```

## Execução em Nuvem

* As operações REST podem ser acessadas pela URL [https://seletor-rotas.herokuapp.com/](https://seletor-rotas.herokuapp.com/)

## Execução Local

* Utilizando um terminal (Command Prompt do Windows, Git Bash ou terminal do Linux), acessar o diretório raiz da aplicação (seletor-rotas);

* **Maven:**

  * Digitar o comando:
    ```
    mvn spring-boot:run
    ```
  
  * As operações REST estarão disponíveis em http://localhost:8080;

* **Execução via jar:**

  * Realizar o build do projeto com sua ferramenta preferencial (para Maven, comando mvn install);
  * Digitar o comando:
    ```
    java -jar target/seletor-rotas-1.0.0-SNAPSHOT.jar
    ```

  * As operações REST estarão disponíveis em http://localhost:8080;
  
## Testes Unitários e Cobertura

Para execução dos testes da aplicação, com o Maven instalado em sua máquina, digite o seguinte comando na pasta raiz do projeto:

```javascript
\seletor-rotas>mvn clean test
```

Será gerado o arquivo index.html, com o resultado dos testes, na pasta "/target/site/jacoco/":

<img src="https://github.com/jokarichardson/seletor-rotas/blob/master/src/main/resources/resultado_testes.png" />