HelpDesk API

API REST desenvolvida com Spring Boot para gerenciamento de chamados de suporte técnico.
O sistema permite criar, listar, atualizar e consultar chamados, simulando o funcionamento básico de um sistema de Help Desk utilizado em empresas de TI.

Este projeto foi desenvolvido com foco em boas práticas de desenvolvimento backend, separação em camadas e uso de DTOs para comunicação com a API.

Tecnologias utilizadas

Java 17

Spring Boot

Spring Data JPA

Hibernate

H2 Database

Maven

Bean Validation

Arquitetura do projeto

O projeto segue o padrão de arquitetura em camadas, comum em aplicações Spring Boot:

Controller → Service → Repository → Entity

Estrutura de pacotes:

src/main/java/com/victor/HelpDesk

controller – Camada responsável pelos endpoints da API
serveces – Camada de regras de negócio
repository – Comunicação com o banco de dados
entity – Entidades do sistema
chamados – DTOs utilizados para transferência de dados
enums – Enumerações de status e prioridade
config – Configurações da aplicação

Essa organização facilita manutenção, testes e escalabilidade do projeto.

Modelo de domínio

A aplicação possui a entidade principal:

Chamado

Um chamado possui informações como:

título

descrição

prioridade

status

data de abertura

Enums utilizados:

Prioridade

BAIXA

MEDIA

ALTA

Status

ABERTO

EM_ANDAMENTO

FINALIZADO

Endpoints da API
Criar chamado

POST /chamados

Exemplo de JSON:

{
"titulo": "Problema no computador",
"descricao": "Computador não liga",
"prioridade": "ALTA"
}

Listar todos os chamados

GET /chamados

Retorna uma lista com todos os chamados cadastrados.

Buscar chamado por ID

GET /chamados/{id}

Retorna os detalhes de um chamado específico.

Atualizar chamado

PUT /chamados/{id}

Permite atualizar informações do chamado.

Banco de dados

O projeto utiliza H2 Database em memória para facilitar testes e execução.

Console do banco:

http://localhost:8080/h2-console

Configuração padrão:

JDBC URL
jdbc:h2:mem

User
sa

Password
(vazio)

Como executar o projeto

Clone o repositório:

git clone https://github.com/VictorAlves94/HelpDesk-BackAnd.git

as práticas de organização de código

Modelagem de domínio

Integração com banco de dados usando JPA

Entre na pasta do projeto:

cd HelpDesk-BackAnd

Execute a aplicação:

mvn spring-boot

A aplicação estará disponível em:

http://localhost:8080

Objetivo do projeto

Este projeto foi desenvolvido com o objetivo de praticar desenvolvimento de APIs REST com Spring Boot, aplicando conceitos importantes como:

Arquitetura em camadas

Uso de DTOs

Bo
