LAB para geração de Microserviços Utilizando Axon - CQRS + Event Sourcing + Saga + DDD
============================

**Pré-requisitos**
- Java 8
- Maven 3.6
- Docker
- Docker-compose

**Módulos**
- chat-command
- chat-query

**Execução - Geração dos jars.**
```bash
mvn clean install -DskipTests
```
**Execução - Remoção de containers existentes**
```bash
docker-compose down -v
```
**Execução - Execução dos containers de Database e EventStore**
```bash
docker-compose up --build --force-recreate --remove-orphans axonserver sqlserver mysqlserver
```
**Execução - Execução dos containers Web - SpringBoot Command e SpringBoot Query** 
```bash
docker-compose up --build --force-recreate --remove-orphans chat-command chat-query
```
**Testes - Importe o arquivo postman_collection.json com o PostMan**

**Referência**
- https://docs.axoniq.io/reference-guide/v/3.1/part-i-getting-started/quick-start
- https://github.com/AxonIQ/axon-quick-start

**Seja feliz!**

