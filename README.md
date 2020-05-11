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

## Formato Arquivo CSV

Para carregar a aplicação com suas rotas, gere um arquivo CSV (comma-separated-values) contendo a estrutura "ORIGEM, DESTINO e CUSTO", conforme abaixo:

```
GRU,BRC,10
BRC,SCL,5
GRU,CDG,75
GRU,SCL,20
GRU,ORL,56
ORL,CDG,8
SCL,ORL,20
BRC,CDG,35
```

Os dados de local e nome do arquivo serão utilizados para executar a aplicação, no lugar do parâmetro "local_e_nome_arquivo_de_rotas".

## Execução Local - ConsoleApplication

* Utilizando um terminal (Command Prompt do Windows, Git Bash ou terminal do Linux), acessar o diretório raiz da aplicação (seletor-rotas);

* **Maven:**

  * Digitar o comando:
    ```
    mvn spring-boot:run -Dspring-boot.run.arguments="local_e_nome_arquivo_de_rotas"
    ```
    * Exemplo: mvn spring-boot:run -Dspring-boot.run.arguments="C:\input-routes.csv"

## Execução Local - WebApplication

* Utilizando um terminal (Command Prompt do Windows, Git Bash ou terminal do Linux), acessar o diretório raiz da aplicação (seletor-rotas);

* **Maven:**

  * Digitar o comando:
    ```
    mvn spring-boot:run -Dspring-boot.run.arguments="local_e_nome_arquivo_de_rotas, web"
    ``` 
    * **web** acima é a palavra "web", indicada para iniciar a execução da interface REST.
    * Exemplo: mvn spring-boot:run -Dspring-boot.run.arguments="C:\input-routes.csv, web"

## Utilização ConsoleApplication:

* Será apresentada a mensagem: **Por favor, informe a rota desejada (Ex.: ORI-DES):**
* Digite uma rota no formato ORI-DES, onde ORI = origem e DES = destino. Ex: GRU-CDG
* O sistema calculará a melhor rota e exibirá a resposta: **Melhor Rota: GRU - BRC - SCL - ORL - CDG**
* Para nova consulta, informe uma nova rota ORI-DES
* Para encerrar a aplicação, informe **SAIR** 

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

  Essa operação realiza a persistência de uma nova rota na lista de rotas em memória e no arquivo de entrada informado quando do início da aplicação.

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
  
  * O arquivo será atualizado com a(s) nova(s) rota(s).

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
  
## Testes Unitários e Cobertura

Para execução dos testes da aplicação, com o Maven instalado em sua máquina, digite o seguinte comando na pasta raiz do projeto:

```javascript
\seletor-rotas>mvn clean test
```

Será gerado o arquivo index.html, com o resultado dos testes, na pasta "/target/site/jacoco/":

<img src="https://github.com/jokarichardson/seletor-rotas/blob/master/src/main/resources/resultado_testes.png" />