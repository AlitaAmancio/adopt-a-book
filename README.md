# Adopt a Book 📖

Adopt a Book é uma aplicação para gerenciar a adoção de livros.

## Configuração e Instalação

### Pré-requisitos

- Java 19.0.2
- Maven
- PostgreSQL
- VSCode (opcional, mas recomendado)

### Configuração do Banco de Dados

1. Instale e configure o PostgreSQL.
2. Crie um banco de dados para a aplicação.
3. Crie um arquivo `application.properties` com base no arquivo `application.properties.example` e então configure as variáveis de ambiente.

### Configuração do application.properties

No arquivo `application.properties` configure as propriedades `sua_database`, `seu_usuario` e `sua_senha` substituindo-as pelas suas variáveis de ambiente reais, o nome do banco de dados criado para a aplicação, seu nome de usuário e senha respectivamente.

### Executando a Aplicação

1. Clone o repositório do projeto:
   ```sh
   git clone https://github.com/AlitaAmancio/adopt-a-book.git
   ```
2. Navegue até o diretório do projeto:
   ```sh
   cd adopt-a-book
   ```
3. Compile e execute a aplicação usando Maven:
   ```sh
   mvn clean spring-boot:run
   ```
   
### A aplicação estará disponível em `http://localhost:8080`.

## Estrutura do Projeto

- `src/main/java`: Código fonte da aplicação.
- `src/main/resources`: Recursos da aplicação, incluindo o arquivo `application.properties` e scripts de migração Flyway.
- `src/test/java`: Testes unitários.




