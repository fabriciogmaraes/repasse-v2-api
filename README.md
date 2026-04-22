# API de Repasse de Veículos - Repasse V2

## Descrição do Domínio
Este projeto foi desenvolvido como parte da disciplina de Desenvolvimento Web II (PPgTI/UFRN). O domínio escolhido é o **Repasse de Veículos**, um sistema voltado para a negociação de automóveis entre lojistas. O sistema permite o cadastro de veículos, associação com lojas e inclusão de acessórios, além de possuir um motor de busca para "Oportunidades" (veículos abaixo da tabela FIPE).

## Tecnologias Utilizadas
* Java 21+
* Spring Boot 3.x
* Spring Data JPA
* Banco de Dados H2 (Persistência Híbrida)
* Jakarta Bean Validation (Hibernate Validator)

## Arquitetura e Relacionamentos
A API segue o padrão de camadas (**Controller, Service, Repository, DTO e Model**).

* **One-To-Many (1:N):** Uma `Loja` pode possuir vários `Veiculo`.
* **Many-To-Many (N:N):** Um `Veiculo` pode ter vários `Acessorio`, e um `Acessorio` pode estar presente em diversos veículos.
* **Cascade:** Utilizado `CascadeType.ALL` no relacionamento de acessórios para garantir integridade.

## Persistência em Múltiplas Bases (Requisito Obrigatório)
A aplicação utiliza dois DataSources distintos configurados no `application.properties`:
1.  **Base A (Principal):** Armazena dados de Lojas, Veículos e Acessórios (`jdbc:h2:mem:base_principal`).
2.  **Base B (Audit/Log):** Armazena logs de operações (`jdbc:h2:mem:base_auditoria`). 
    * *Toda criação de veículo dispara automaticamente um log de auditoria via `AuditoriaService`.*

## Queries Customizadas
* **JPQL (@Query):** Busca de oportunidades (preço de anúncio < valor FIPE).
* **Native Query:** Implementada consulta SQL nativa para contagem total de registros.
* **JOIN FETCH:** Utilizado para carregamento EAGER de acessórios, otimizando a performance e evitando o problema de N+1.
