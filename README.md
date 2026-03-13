🛠 HelpDesk API

API REST desenvolvida para gerenciamento de chamados técnicos (Help Desk), permitindo controle de usuários, técnicos e tickets de atendimento.

O sistema permite registrar clientes, abrir chamados, acompanhar status de atendimento e gerenciar o ciclo de vida de um ticket de suporte.

Este projeto foi desenvolvido utilizando Java com Spring Boot, aplicando boas práticas de arquitetura REST, tratamento global de exceções e autenticação baseada em JWT (JSON Web Token).



🚀 Tecnologias utilizadas

Java 11+

Spring Boot

Spring Security

JWT (Json Web Token)

Hibernate / JPA

H2 Database

Maven

Bean Validation

REST API



📚 Funcionalidades

O sistema possui as seguintes funcionalidades principais:



👤 Gerenciamento de usuários

Cadastro de clientes

Cadastro de técnicos

Atualização de dados

Listagem de usuários

Busca por ID

Remoção de usuários



🎫 Gerenciamento de chamados

Abertura de chamados

Atualização de status

Atribuição de técnico responsável

Alteração de prioridade

Encerramento de chamados



🔐 Autenticação e segurança

Autenticação com JWT

Rotas protegidas com Spring Security

Controle de acesso por autenticação

⚠ Tratamento global de erros

A API possui um sistema centralizado de tratamento de exceções com:

ObjectNotFoundException

DataIntegrityViolationException

ValidationError

As respostas de erro seguem um padrão estruturado:

{
  "timestamp": 1710250000000,
  "status": 404,
  "error": "Object Not Found",
  "message": "Objeto não encontrado",
  "path": "/api/chamados/1"
}

Erros de validação retornam também os campos inválidos:

{
  "timestamp": 1710250000000,
  "status": 400,
  "error": "Validation Error",
  "message": "Erro na validação dos campos",
  "path": "/api/clientes",
  "errors": [
    {
      "fieldName": "email",
      "message": "Email inválido"
    }
  ]
}


🧱 Arquitetura do projeto

O projeto segue uma arquitetura em camadas:

src/main/java/com/victor/HelpDesk

config
 └── configurações de segurança e CORS


controller
 └── endpoints da API REST

service
 └── regras de negócio


repository
 └── acesso ao banco de dados


domain
 └── entidades do sistema


dto
 └── objetos de transferência de dados


enums
 └── status e prioridades do sistema


exceptions
 └── tratamento global de erros


security
 └── autenticação JWT


Essa organização segue boas práticas utilizadas em projetos com Spring Boot.



🔑 Segurança

A API utiliza JWT (JSON Web Token) para autenticação.

Fluxo básico:

Usuário realiza login

API gera um token JWT

Token deve ser enviado no header das requisições

Exemplo:

Authorization: Bearer SEU_TOKEN_AQUI

O token é validado por filtros de segurança configurados no Spring Security.

🗄 Banco de dados

Durante o desenvolvimento foi utilizado:

H2 Database

Console disponível em:

/h2-console
▶ Como executar o projeto

1️⃣ Clonar o repositório
git clone https://github.com/seu-usuario/helpdesk-api.git

2️⃣ Entrar na pasta do projeto
cd helpdesk-api

3️⃣ Executar o projeto
mvn spring-boot:run

Ou executar pela IDE.


📡 Principais endpoints
Usuários
GET    /usuarios
GET    /usuarios/{id}
POST   /usuarios
PUT    /usuarios/{id}
DELETE /usuarios/{id}
Chamados
GET    /chamados
GET    /chamados/{id}
POST   /chamados
PUT    /chamados/{id}
