HelpDesk API

API REST desenvolvida com Spring Boot para gerenciamento de chamados de suporte técnico.

O sistema simula um ambiente de Help Desk corporativo, onde clientes podem abrir chamados e técnicos são responsáveis por atendê-los.

A aplicação foi construída seguindo boas práticas de arquitetura backend, utilizando separação em camadas, DTOs, autenticação JWT e integração com banco de dados via JPA.

Tecnologias utilizadas

Java 17

Spring Boot

Spring Data JPA

Hibernate

Spring Security

JWT Authentication

H2 Database

Maven

Bean Validation

Arquitetura do projeto

O projeto segue o padrão de arquitetura em camadas.

Controller
   ↓
Service
   ↓
Repository
   ↓
Entity

Estrutura de pacotes:

config
controller
dto
entity
enums
exceptions
repository
security
services

Descrição das camadas:

controller
Responsável pelos endpoints da API.

services
Contém as regras de negócio do sistema.

repository
Camada de acesso ao banco de dados utilizando Spring Data JPA.

entity
Representa as entidades persistidas no banco.

dto
Objetos de transferência de dados entre API e cliente.

enums
Enumerações utilizadas no sistema como status e prioridade.

exceptions
Tratamento global de erros da aplicação.

security
Configurações de autenticação e autorização utilizando JWT.

config
Configurações iniciais e dados de teste da aplicação.

Modelo de domínio

O sistema possui três entidades principais:

Cliente
Usuário responsável por abrir chamados de suporte.

Técnico
Responsável por atender e resolver chamados.

Chamado
Representa uma solicitação de suporte aberta por um cliente.

Cada chamado possui:

título

descrição

prioridade

status

cliente responsável

técnico responsável

data de abertura

data de fechamento

observações

Enumerações do sistema

Prioridade

BAIXA

MEDIA

ALTA

Status

ABERTO

EM_ANDAMENTO

FINALIZADO

Segurança da aplicação

O sistema utiliza Spring Security com autenticação baseada em JWT.

Principais componentes:

JwtAuthenticationFilter

JwtAuthorizationFilter

SecurityConfig

UserDetailsServiceImpl

Isso permite proteger os endpoints da API e controlar o acesso dos usuários.

Endpoints principais

Clientes

GET /clientes
POST /clientes
PUT /clientes/{id}
GET /clientes/{id}

Técnicos

GET /tecnicos
POST /tecnicos
PUT /tecnicos/{id}
GET /tecnicos/{id}

Chamados

GET /chamados
POST /chamados
PUT /chamados/{id}
GET /chamados/{id}
Banco de dados

O projeto utiliza H2 Database em memória.

Console do banco:

http://localhost:8080/h2-console

Configuração padrão

JDBC URL: jdbc:h2:mem:testdb
User: sa
Password: (vazio)
Como executar o projeto

Clone o repositório

git clone https://github.com/VictorAlves94/HelpDesk-BackAnd.git

Entre na pasta

cd HelpDesk-BackAnd

Execute a aplicação

mvn spring-boot:run

A API estará disponível em

http://localhost:8080
Projeto relacionado

Frontend da aplicação

https://github.com/VictorAlves94/HelpDesk-FrontEnd
