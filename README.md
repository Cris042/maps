# Documentação

Este documento oferece uma visão abrangente dos pontos finais e funcionalidades para um sistema de gerenciamento de finanças pessoais.

## Tecnologias Utilizadas

- Java 8 
- Spring Boot 2.7
- Maven
- H2 Database
- Flyway
- Angular 15

## H2

Optamos por utilizar o H2 devido à sua natureza de base de dados em memória, o que dispensa a necessidade de um servidor dedicado. Essa escolha facilita a execução de testes durante o desenvolvimento.

- Duas instâncias do H2 foram empregadas: uma para o ambiente de desenvolvimento, que sera armazenada em formato de arquivo, e outra para o ambiente de testes que sera in-memory.
- Um script foi elaborado para popular a tabela de usuário, sendo executado automaticamente ao iniciar a aplicação.

## Flyway

O Flyway foi empregado para o versionamento do banco de dados, bem como para população inicial com dados.

## Testes

Os testes unitários foram implementados com JUnit e Mockito. Ultizamos TDD para o desenvolvimento dos testes.

- Teste do CRUD de Ativos Financeiros.
- Teste do CRUD de Transações Financeiras ( Testagem de um ciclo de compra e venda ).

## Autorização

Para autorização de acesso aos endpoints, foi adotado o JWT (JSON Web Token) em formato de cookie, para facilitar a testagem.
Além disso, foram definidos dois tipos de roles: user adm.

## Atores 

### ADMIN

- Cadastrar, atualizar, excluir e listar ativos financeiros.
- Visualizar detalhes de uma posição de um ativo.

### USER

- Verificar saldo
- Cadastrar e listar transações financeiras.
- Visualizar detalhes de uma posição de um ativo.

### Acessos 

- ROLE USER( "usuario0", "senha0")
- ROLE ADMIN( "root", "spiderman")

## Ativos Financeiros

Foram Regristados ativos de : ATIVO0 ate ATIVO127, com data de inicio de 02/01/2010 e data de finalização 31/03/2025 .

## Thread-safe.

A aplicação foi desenvolvida de forma multithread.

- Para buscar posição de um ativo, foi utilizado um pool de threads para executar tarefas assíncronas, mantendo um mapa de resultados de processamento e fornecendo métodos para iniciar e recuperar resultados de tarefas assíncronas.O desempenhos que obtive usando 10 threads foram de 762 ms para  o processo de mapeamento e processamento da posiçao do ativo , e em seguinda obtivemos o tempo de 24.8 ms para buscar pelo registro.

## Front End

Foi criado a tela de home, login e cadastro.

## Outros

O arquivo api/req.json contém exemplos de requisições para os endpoints da API

# Endpoints

## Autenticar Usuário

- **URL**: `/api/auth/signin`
- **Método**: `POST`
- **Descrição**: Autentica um usuário e gera um cokie JWT.
- **Parâmetros**:

      "username" : "root"
      "password" : "spiderman",
  
- **Resposta**:
    - Sucesso: 200 OK com o cokie JWT.
    - Falha: 400 Bad Request com uma mensagem de erro se as credenciais forem inválidas.

## Registrar Usuário

- **URL**: `/api/auth/signup`
- **Método**: `POST`
- **Descrição**: Registra um novo usuário no sistema.
- **Parâmetros**:
  
  
      "username" : "",
      "password" : "",
  
- **Resposta**:
    - Sucesso: 200 OK com uma mensagem de confirmação.
    - Falha: 400 Bad Request com uma mensagem de erro se o nome de usuário já estiver em uso.

## Encerrar Sessão

- **URL**: `/api/auth/signout`
- **Método**: `POST`
- **Descrição**: Encerra a sessão do usuário e limpa o token JWT.
- **Resposta**:
    - Sucesso: 200 OK com uma mensagem de confirmação.

## Cadastro Ativos Financeiros

- **URL**: `/api/financial-active/save`
- **Método**: `POST`
- **Descrição**: Cria um ativo financeiro.
- **Parâmetros**:
    
       "name" : "",
       "typeFinancialAssets" : "",
       "dateIssue" : "",
       "dateTerminus" : ""
    
- **Resposta**:
    - Sucesso: 200 OK com uma mensagem de confirmação.
    - Falha: 400 Bad Request com uma mensagem de erro se o nome do ativo financeiro.

## Buscar Todos os Ativos Financeiros

- **URL**: `/api/financial-active/all`
- **Método**: `GET`
- **Descrição**: Retorna todos os ativos financeiros cadastrados.
- **Resposta**:
    - Sucesso: 200 OK com uma lista de ativos financeiros.
    - Falha: 404 Not Found se não houver ativos financeiros cadastrados.

## Buscar Ativo Financeiro por ID

- **URL**: `/api/financial-active/{id}`
- **Método**: `GET`
- **Descrição**: Retorna um ativo financeiro com base no ID fornecido.
- **Parâmetros**:
    - `id`: ID do ativo financeiro a ser buscado.
- **Resposta**:
    - Sucesso: 200 OK com o ativo financeiro correspondente.
    - Falha: 404 Not Found se o ativo financeiro não for encontrado.

## Atualizar Ativo Financeiro

- **URL**: `/api/financial-active/update/{id}`
- **Método**: `PUT`
- **Descrição**: Atualiza um ativo financeiro existente com base no ID fornecido.
- **Parâmetros**:
    - `id`: ID do ativo financeiro a ser atualizado.
    - Corpo da requisição contendo os dados atualizados do ativo financeiro.
- **Resposta**:
    - Sucesso: 200 OK com uma mensagem de confirmação.
    - Falha: 400 Bad Request se os dados fornecidos forem inválidos ou 404 Not Found se o ativo financeiro não for encontrado.

## Excluir Ativo Financeiro

- **URL**: `/api/financial-active/delete/{id}`
- **Método**: `DELETE`
- **Descrição**: Exclui um ativo financeiro com base no ID fornecido.
- **Parâmetros**:
    - `id`: ID do ativo financeiro a ser excluído.
- **Resposta**:
    - Sucesso: 200 OK com uma mensagem de confirmação.
    - Falha: 404 Not Found se o ativo financeiro não for encontrado

## Ver Saldo

- **URL**: `/api/account/balance/{accountId}?date=yyyy-mm-dd`
- **Método**: `GET`
- **Descrição**: Verifica o saldo de uma conta.
- **Parâmetros**:
    - `accountId`: ID da conta.
    - `date`: Data para a qual o saldo deve ser verificado.
- **Resposta**:
    - Sucesso: 200 OK com uma o saldo da conta.
    - Falha: 404 Not Found se a conta não for encontrada

## Ver Posição de Ativo

- **URL**: `/api/position/{nameAtive}?data=yyyy-mm-dd`
- **Método**: `GET`
- **Descrição**: Verifica a pocição de um ativo em uma data .
- **Parâmetros**:
    - `nameAtive`: Nome do ativo.
    - `date`: Data verificado.
- **Resposta**:
    - Sucesso: 200 OK com o id da execução.
    - Falha: 424 Im used

## Ver Posição de Ativo (Assíncrono)

- **URL**: `/api/position/findById/{id}`
- **Método**: `GET`
- **Descrição**: Verifica a pocição de um ativo em uma data .
- **Parâmetros**:
    - `id`: id da execução da request anterior.
- **Resposta**:
    - Sucesso: 200 OK com o informaços da posição na data especifica.
    - Falha: 424 Im used

## Venda 

- **URL**: `/api/financial-release/sale`
- **Método**: `POST`
- **Descrição**: Venda .
- **Parâmetros**:
  
        "description": "",
        "valueRelease" : 0.0,
        "dateMovement" : "yyyy-MM-dd",
        "account" : {
        "id" : 1
        }
  
- **Resposta**:
    - Sucesso: 200 OK com uma mensagem de confirmação.
    - Falha: 404 bad request

## Comprar 

- **URL**: `/api/financial-release/purchase`
- **Método**: `POST`
- **Descrição**: Compra .
- **Parâmetros**:
  
        "description": "",
        "valueRelease" : 0.0,
        "dateMovement" : "yyyy-MM-dd",
        "account" : {
          "id" : 1
         }
  
- **Resposta**:
    - Sucesso: 200 OK com uma mensagem de confirmação.
    - Falha: 404 bad request

## Venda de Ativos

- **URL**: `/api/financial-movement/sale`
- **Método**: `POST`
- **Descrição**: Venda de ativos .
- **Parâmetros**:
  
        "amount": 10.0,
        "valueOverall" : 20.0,
        "nameFinancialActive" : "ATIVO100",
        "dateMovement" : "2020-10-10",
        "account" : {
            "id" : 1
         }
  
- **Resposta**:
    - Sucesso: 200 OK com uma mensagem de confirmação.
    - Falha: 404 bad request 

## Comprar de Ativos

- **URL**: `/api/financial-movement/purchase`
- **Método**: `POST`
- **Descrição**: Compra de ativos.
- **Parâmetros**:
  
        "description": "",
        "valueRelease" : 0.0,
        "dateMovement" : "yyyy-MM-dd",
        "account" : {
          "id" : 1
        }
  
- **Resposta**:
    - Sucesso: 200 OK com uma mensagem de confirmação.
    - Falha: 404 bad request 

