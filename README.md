# Curso Especialista Spring REST - Algaworks

Repositório com códigos desenvolvidos no curso ESR da Algaworks.

Cada branch representa o código desenvolvido na aula referenciada. Algumas aulas são apenas teóricas, sem desenvolvimento de código.

## Módulo 2

### Aula 2.5 - Criando um projeto Spring Boot com Spring Initializr
Criação do projeto básico do Spring

### Aula 2.7 - Criando um controller com Spring MVC
Criação de um controller básico

### Aula 2.8 - Restart mais rápido da aplicação com DevTools
Adição da dependência DevTools para reinicialização rápida durante o desenvolvimento da aplicação

### Aula 2.11 - Definindo beans com @Component
Utilização da anotação @Component para definir beans gerenciados pelo Spring.  
Exemplo com um simulador de notificação de emails para clientes

### Aula 2.12 - Injetando dependências (beans Spring)
Injeção de dependências através de construtor
Criação da interface Notificador (padrão de projeto Factory)

### Aula 2.13 - Usando @Configuration e @Bean para definir beans
Criação de classes de configuração e beans

### Aula 2.14 - Conhecendo os pontos de injeção e a anotação @Autowired
Injeçao de dependências por construtor, *setter* e anotação @Autowired 

### Aula 2.15 - Dependência opcional com @Autowired
Configuração de dependência opcional com o parâmetro required na anotação @Autowired

### Aula 2.16 - Ambiguidade de beans e injeção de lista de beans
Desambiguação de beans utilizando listas.  Todos os beans são utilizados

### Aula 2.17 - Desambiguação de beans com @Primary
Utilização da anotação @Primary para definir prioridade de beans

### Aula 2.18 - Desambiguação de beans com @Qualifier
Utilização da anotação @Qualifier para definir prioridade de beans

### Aula 2.19 - Desambiguação de beans com anotação customizada
@Qualifier é resolvido em tempo de execução.  
Criação de anotação para definir prioridade do bean. 

### Aula 2.20 - Mudando o comportamento da aplicação com Spring Profiles
Criação de profiles com arquivos *.properties. Utilização da anotação @Profile

### Aula 2.21 - Criando métodos de callback do ciclo de vida dos beans
Configuração de callbacks de inicialização e finalização do bean com
- Anotações @PostConstruct e @PreDestroy
- Classe de configuração com propriedades init e destroy na anotação @Bean

### Aula 2.22 - Publicando e consumindo eventos customizados
Implementação do padrão de projeto Observer para notificações

### Aula 2.23 - Configurando projetos Spring Boot com o application.properties
Configuração do application.properties.  
Link da documentação do Spring: https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html

### Aula 2.25 - Criando e acessando propriedades customizadas com @Value
Criação e utilização de propriedades no arquivo application.properties e utilização em NotificadorEmail com anotação @Value

### Aula 2.26 - Acessando propriedades com @ConfigurationProperties
Configuração da classe NotificadorProperties e utilização de propriedades com anotação @ConfigurationProperties

## Módulo 3
### Aula 3.3 - Adicionando JPA e configurando o Data Source
Configuração inicial do Spring Data JPA e das propriedades do banco de dados

### Aula 3.4 - Mapeando entidades com JPA
Mapeamento das entidades Restaurante e Cozinha.  Anotações @Entity, @Table, @Id e @Column.

### Aula 3.5 - Criando as tabelas do banco a partir das entidades
Geração automática de tabelas utilizando as entidades e as configurações spring.jpa.generate-ddl e spring.jpa.hibernate.ddl-auto

### Aula 3.6 - Mapeando o id da entidade para autoincremento
Configuração do autoincrmento do id através da anotação @GenerationType

### Aula 3.7 - Importando dados de teste com import.sql
Criação de script para popular a tabela cozinha no banco de dados.  
Arquivo com nome *import.sql* (obrigatório ser esse nome)

### Aula 3.8 - Consultando objetos do banco de dados
Listagem de objetos da classe Cozinha.  
Uso da classe EntityManager com a anotação @PersistenceContext. 
Ver SQL gerado com spring.jpa.show-sql

### Aula 3.9 - Adicionando um objeto no banco de dados
Inserção de dados no banco.  
Método merge.  
Necessário anotar o método adicionar com @Transactional 

### Aula 3.10 - Buscando um objeto pelo id no banco de dados
Busca de cozinha por Id.  
Sem tratamento para Id inexistente.

### Aula 3.11 - Atualizando um objeto no banco de dados
Atualização de dados no banco.  
Refatoração: Mudança do nome do método adicionar para salvar.  

### Aula 3.12 - Excluindo um objeto do banco de dados
Exclusão de dados do banco.  
Necessário obter instância gerenciável do objeto.  
Artigo sobre JPA e estados da instância: https://blog.algaworks.com/tutorial-jpa/

### Aula 3.14 - Conhecendo e implementando o padrão Repository
Implementação de interface CozinhaRepository e da classe de implementação CozinhaRepository.  
Cópia dos métodos de CadastroCozinha para CozinhaRepositoryImpl.  
Exclusão da classe CadastroCozinha.  
Refatoração das classes de manipulação de dados criadas nas aulas 3.9 a 3.12.

### Aula 3.15 - Conhecendo e usando o Lombok
Configuração da dependência do Lombok.  
Anotações @Getter, @Setter, @EqualsAndHashCode e @Data.  
Propriedade onlyExplicityIncluded em @EqualsAndHashCode. 
Utilização na classe Cozinha.   

### Aula 3.16 - Desafio: Lombok e repositório de restaurantes
Utilização do Lombok na classe Restaurante.  
Criação do repositório de restaurantes.  
Inserção de SQL para restaurantes. 

### Aula 3.17 - Mapeando relacionamento com @ManyToOne
Configuração de relacionamento muitos pra um na entidade Restaurante.  
Configuração do dialeto padrão do banco de dados com spring.jpa.hibernate.dialect (application.properties)

### Aula 3.18 - A anotação @JoinColumn
Definição de nome para a coluna de relacionamento com @JoinColumn com o parâmetro name

### Aula 3.19 - Propriedade nullable de @Column e @JoinColumn
Configuração de propriedade não nula nas colunas (válido apenas para geração automática de tabelas)

### Aula 3.20 - Desafio: mapeando entidades
Mapeamento de entidades e criação dos repositórios
- Cidade
- Estado
- Permissao
- FormaPagamento

## Módulo 4

### Aula 4.10 - Modelando e requisitando um Collection Resource com GET
Criação do controlador para obtenção de lista de cozinhas

### Aula 4.11 - Desafio: collection resource de estados
Criação do controlador para obtenção de lista de estados

### Aula 4.13 - Implementando content negotiation para retornar JSON ou XML
Definição de formato de dados para utilização nos endpoints (JSON ou XML).  
O formato pode ser definido em escopo de classe (na anotação RequestMapping) ou em escopo do método (na anotação GetMapping, por exemplo).  
A anotação de método tem prioridade sobre a anotação de classe.  
O mesmo mapeamento pode produzir diferentes formatos.  

### Aula 4.14 - Consultando Singleton Resource com GET e @PathVariable
Mapeamento para recurso único.  
Utilização da anotação @PathVariable

### Aula 4.15 - Customizando as representações XML e JSON com @JsonIgnore, @JsonProperty e @JsonRootName
- @JsonRootName - Mudar nome da representação da classe (XML)
- @JsonProperty - Pode ser utilizada para alterar a representação do at
- @JsonIgnore - Ignorar a propriedade na representação

@JsonProperty possui prioridade sobre @JsonIgnore. Caso ambas sejam colocadas no mesmo atributo @JsonProperty funcionará corretamente e @JsonIgnore será desconsiderada.

### Aula 4.16. Customizando a representação em XML com Wrapper e anotações do Jackson
Customização da lista no XML. 
Criação da classe CozinhaXmlWrapper
Anotações utilizadas: 
- @JacksonXmlRootElement - define o nome do wrapper da lista
- @JacksonXmlProperty ou @JsonProperty - define o nome do recurso único
- @JacksonXmlElementWrapper - elimina a duplicação

### Aula 4.19. Definindo o status da resposta HTTP com @ResponseStatus
Alteração do status de resposta da requisição

### Aula 4.20 - Manipulando a resposta HTTP com ResponseEntity
Configuração do status de retorno utilizando ResponseEntity

### Aula 4.21. Corrigindo o Status HTTP para resource inexistente
Lançamento de código 404 para recurso inexistente.

### Aula 4.23 - Modelando e implementando a inclusão de recursos com POST
Inclusão de cozinha com POST em /cozinhas.  
Alteração do status de resposta para 201 (Created).

### Aula 4.25 - Modelando e implementando a atualização de recursos com PUT
Alteração de cozinha com PUT.  
Tratamento de erro quando o Id não existe (status 404).

### Aula 4.26 - Modelando e implementando a exclusão de recursos com DELETE
Exclusão de cozinha com DELETE.  
- Em caso de sucesso, status 204 (No Content)  
- Em caso de falha por não existir o recurso, status 404 (Not Found)  
- Em caso de falha por violação de integridade de chave estrangeira, status 409 (Conflict)  

### Aula 4.27 - Implementando a camada de domain services (e a importância da linguagem ubíqua)
Implementação da camada de serviços para Cozinha.  
Anotação @Service.  
Utilizar esta camada para implementação de regras de negócio.  

### Aula 4.28 - Refatorando a exclusão de cozinhas para usar domain services
Alteração do método de exclusão no repositório para receber o id como parâmetro.
Lançamento de exceção EmptyResultDataAccess -> EntidadeNaoEncontradaException
Lançamento de exceção DataIntegrityViolationException -> EntidadeEmUsoException  

### Aula 4.29 - Desafio: modelando e implementando a consulta de recursos de restaurantes
Retirada do suporte a XML
Implementação do controlador de restaurantes
- Lista de restaurantes (/restaurantes)
- Busca de restaurantes (/restaurantes/{id})

### Aula 4.30 - Modelando e implementando a inclusão de recursos de restaurantes
Implementação de adição de restaurante (POST /restaurantes)
Criação da classe de serviços de restaurante
Tratamento de exceção para inserção de restaurante com cozinha inexistente

### Aula 4.31 - Desafio: Modelando e implementando a atualização de recursos de restaurantes
Implementação da atualização de restaurante (PUT /restaurantes/{id})

### Aula 4.32 - Desafio: implementando serviços REST de cidades e estados
Implementação de serviços de estados e cidades
- Listagem (GET /estados - /cidades)
- Busca (GET /estados/{id} - /cidades/{id})
- Inclusão (POST /estados - /cidades)
- Atualização (PUT /estados/{id} - /cidades/{id})
- Exclusão (DELETE /estados/{id} - /cidades/{id})

### Aula 4.33 - Analisando solução para atualização parcial de recursos com PATCH
Início da implementação de atualização parcial de restaurante (PATCH /restaurantes/{id}).  
Utilização de mapa (Map)

### Aula 4.34 - Finalizando a atualização parcial com a API de Reflections do Spring
Utilização da Reflections API para atualização parcial
- findField
- getField
- setField

## Módulo 5

### Aula 5.1 - Implementando consultas JPQL em repositórios
Anotação  @Repository  
Consulta JPQL por nome de cozinha  
Utilização de cláusula where

### Aula 5.3 - Criando um repositório com Spring Data JPA (SDJ)
Exclusão da implementação de respositório de cozinha.  
Interface de repositório anotada com @Repository e herança de JpaRepository.  
*Código não executa. Correções na próxima aula*

### Aula 5.4 - Refatorando o código do projeto para usar o repositório do SDJ
Utilização dos métodos de JpaRepository para cozinhas
- findAll
- findById (retorna Optional)
- save
- deleteById

### Aula 5.5 - Desafio: refatorando todos os repositórios para usar SDJ
Remoção das implementações de reposítorio de Restaurante, Cidade, Estado, Permissao e FormaPagamento.  
Refatoração dos serviços e controladores de Restaurante, Cidade e Estado.  

### Aula 5.6 - Criando consultas com query methods
Método com o nome da propriedade (teste com propriedade nome da classe Cozinha).
Prefixo findBy

### Aula 5.7 - Usando as keywords para definir critérios de query methods
- Containing
- Between
- And
Outras palavras chave: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation

### Aula 5.8 - Conhecendo os prefixos de query methods
- First - Retorna o primeiro resultado da lista
- Top<x> - Retorna uma lista com os *x* primeiros elementos
- Exists - Verifica a existência de um elemento na lista que satisfaz um critério de seleção
- Count - contagem de elementos da lista que satisfazem um critério de seleção

### Aula 5.9 - Usando queries JPQL customizadas com @Query
Utilização de @Query para consultas 
Anotação @Param para compatibilizar nomes de variáveis

### Aula 5.10 - Externalizando consultas JPQL para um arquivo XML
Criação de arquivo orm.xml para colocação da consulta JPQL (Anotação @Query pode ser retirada do método)

### Aula 5.11 - Implementando um repositório SDJ customizado
Implementação de métodos de forma customizada.  
Padrão de nome da classe: <nome-da-interface-do-repositorio>Impl  
Extração de interface customizada e herança múltipla de interface no repositório

### Aula 5.12 - Implementando uma consulta dinâmica com JPQL
Consulta dinâmica utilizando JPQL e Hashmap. Possibilidade de utilizar apenas alguns parâmetros

### Aula 5.13 - Implementando uma consulta simples com Criteria API
Introdução à Criteria API. Consulta simples para lista de restaurantes

### Aula 5.14 - Adicionando restrições na cláusula where com Criteria API
Consultas não dinâmicas com Criteria API e cláusula where.  
Necessário utilizar Predicates

### Aula 5.15 - Tornando a consulta com Criteria API com filtros dinâmicos
Implementação de lista dinâmica de filtros com ArrayList de Predicate

### Aula 5.17 - Implementando Specifications com SDJ
Criação das specifications *ComFreteGratis* e *ComNomeSemlhante*.  
Classes herdam de Specification (org.springframework.data.jpa.domain.Specification).  
Ajuste do repositório para herdar a interface JpaSpecificationExecutor (org.springframework.data.jpa.repository.JpaSpecificationExecutor)

### Aula 5.18 - Criando uma fábrica de Specifications
Fábrica de Specifications para restaurantes.  
Exclusão das classes individuais referentes às specifications *ComFreteGratis* e *ComNomeSemlhante*

### Aula 5.19 - Injetando o próprio repositório na implementação customizada e a anotação @Lazy
Mudança da chamada das specs para o repositório customizado.  
Problema de referência circular do repositório - corrigido com anotação @Lazy

### Aula 5.20 - Estendendo o JpaRepository para customizar o repositório base
Criação do CustomJpaRepository para implementações comuns a várias classes.  
Habilitação do novo repositório em AlgafoodApiApplication com anotação @EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)

## Módulo 6

### Aula 6.1 - Mapeando relacionamento bidirecional com @OneToMany
Implementação de relacionamento bidirecional entre restaurante e cozinha.  
Anotação @OneToMany com propriedade mappedBy.  
Correção de referência circular entre entidades com @JsonIgnore

### Aula 6.2 - Mapeando relacionamento muitos-para-muitos com @ManyToMany
Implementação de relacionamento muitos para muitos entre *Restaurante* e *FormasPagamento*.  
Anotação @JoinTable com propriedades joinColumns e inverseJoinColumns

### Aula 6.4 - Mapeando classes incorporáveis com @Embedded e @Embeddable
Mapeamento de classes embutidas. Exemplo com endereço e restaurante

### Aula 6.6 - Mapeando propriedades com @CreationTimestamp e @UpdateTimestamp
Utilização de timestamps de criação e atualização de dados com anotações próprias do Hibernate. 

### Aula 6.7 - Desafio: mapeando relacionamento muitos-para-um
Mapeamento da entidade Produto com relacionamento muitos-pra-um com a entidade Restaurante

### Aula 6.8 - Desafio: mapeando relacionamento um-para-muitos
Mapeamento do relacionamento um para muitos entre Restaurante e Produto.  
Correção no controller de produto para evitar problemas na atualização dos dados.

### Aula 6.9 - Desafio: mapeando relacionamento muitos-para-muitos
Criação das entidades Grupo e Usuario.  
Mapeamento muitos para muitos entre
- Usuario e Grupo
- Grupo e Permissao

### Aula 6.14 - Resolvendo o Problema do N+1 com fetch join na JPQL
Correção do problema N+1, que gera excesso de consultas SQL. Utilização do join fetch na consulta JPQL.

## Módulo 7 

### Aula 7.3 - Configurando o pool de conexões do Hikari
Configuração de mínimo e máximo de conexões e tempo ocioso (idle-timeout)

### Aula 7.6 - Adicionando o Flyway no projeto e criando a primeira migração
Migração para tabela cozinha.  
Padrão: V<numero>__<nome>.sql

### Aula 7.7 - Evoluindo o banco de dados com novas migrações
Migração da tabela cidade (sem foreign key para estado)

### Aula 7.8 - Criando migrações complexas com remanejamento de dados
Migração da tabela estado.
Ajuste da tabela cidade para receber a chave estrangeira de estado

### Aula 7.9 - Criando migração a partir de DDL gerado por schema generation
Migração das tabelas
- restaurante
- forma_pagamento
- restaurante_forma_pagamento
- produto 
- permissao
- grupo
- usuario
- grupo_permissao
- usuario_grupo
Utilização de propriedades de spring.jpa.properties.javax.persistence.schema-generation.scripts
- action: tipo de ação desejada (create)
- create-target: arquivo de destino

### Aula 7.10 - Adicionando dados de testes com callback do Flyway
Callback afterMigrate e configuração de locations para o flyway

### Aula 7.11 - Reparando migrações com erros
Formas de reparação de erros em migrações
- Exclusão da linha da migração na tabela flyway-schema e correção da migração
- mvnw flyway:repair -Dflyway.cofigFiles=<caminho-para-arquivo-config-flyway>

### Aula 7.12 - Desafio: Criando migrações e mapeando as entidades Pedido e ItemPedido
Criação das migrações e das entidades Pedido e ItemPedido considerando todos os relacionamentos envolvidos.  

## Módulo 8

### Aula 8.2 - Lançando exceções customizadas anotadas com @ResponseStatus
Anotação das exceções EntidadeEmUsoException e EntidadeNaoEncontradaException com @ResponseStatus.  
Alteração do controller de Cozinha 

### Aula 8.3 - Lançando exceções do tipo ResponseStatusException
Utilização da classe ResponseStatusException para lançamento de exceções customizadas com status HTTP e mensagem de erro.  
WebServerInputException herda de ResponseStatusException e retorna código 400 (Bad Request) por padrão.

### Aula 8.4 - Estendendo ResponseStatusException
Classe EntidadeNaoEncontradaException herda de ResponseStatusException 

### Aula 8.5 - Simplificando o código com o uso de @ResponseStatus em exceptions
Refatoração do código do controlador de cozinha.  
Criação do método buscarOuFalhar na classe de serviços de cozinha

### Aula 8.6 - Desafio: refatorando os serviços REST
Refatoração dos serviços e controladores de restaurante, cidade e estado

### Aula 8.8 - Criando a exception NegocioException
Criação da exceção de negócio (NegocioException) e refatoração do controlador de cidade para utilizar a exceção

### Aula 8.9 - Desafio: usando a exception NegocioException
Refatoração do controlador de restaurante para utilizar a exceção NegocioException

### Aula 8.10 - Afinando a granularidade e definindo a hierarquia das exceptions de negócios
Criação de classe de exceção específica para estado não encontrado (EstadoNaoEncontradoException).  
Hierarquia de exceções: Exception -> RuntimeException -> NegocioException -> EntidadeNaoEncontradaException -> EstadoNaoEncontradoException

### Aula 8.11 - Desafio: lançando exceptions de granularidade fina
Criação e configuração de exceções de granularidade fina para Restaurante, Cidade e Cozinha.

### Aula 8.12 - Tratando exceções em nível de controlador com @ExceptionHandler
Tratamento de exceções no controlador de cidades com mensagens customizadas.  
Criação de classe para customização da mensagem de erro (Problema.java).  
Uso da anotação @Builder para criar um construtor (builder) para a classe Problema.  

### Aula 8.13 - Tratando exceções globais com @ExceptionHandler e @ControllerAdvice
Criação de classe de tratamento de exceções de forma global (ApiExceptionHandler) utilizando anotação @ControllerAdvice.  
Implementação de tratamento de exceção de tipo de mídia não suportado (HttpMediaTypeNotSupportedException).  

### Aula 8.14 - Desafio: implementando exception handler
Implementação de tratamento de exceção global para EntidadeEmUsoException.  

### Aula 8.15. Criando um exception handler global com ResponseEntityExceptionHandler
Herança de ResponseEntityExceptionHandler em ApiExceptionHandler.  
Código do método *tratarHttpMediaTypeNotSupportedException* comentado por conflito 

### Aula 8.16 - Customizando o corpo da resposta padrão de ResponseEntityExceptionHandler
Sobrescrita da função handleExceptionInternal para customizar o corpo da respota.  
Refatoração dos métodos de lançamento de exceção para utilizar o handleExceptionInternal.

### Aula 8.18 - Padronizando o formato de problemas no corpo de respostas com a RFC 7807
Corpo da resposta com parâmetros definidos pela RFC 7807 (status, type, title, detail).  
Criação do enum ProblemType.  
Criação do método createProblemBuilder em ApiHandlerException.  
Refatoração do método handleEntidadeNaoEncontradaException para utilizar a RFC 7807

### Aula 8.19 - Desafio: usando o formato de problemas no corpo de respostas
Refatoração dos métodos handleEntidadeEmUsoException e handleNegocioException de acordo com a RFC 7807.

### Aula 8.20 - Customizando exception handlers de ResponseEntityExceptionHandler
Sobrescrita do método handleHttpMessageNotReadable para customização seguindo RFC

### Aula 8.21. Tratando a exception InvalidFormatException na desserialização
Tratamento de tipo de dados inválido.  
Adição no pom.xml da dependência Apache Commons Lang3 (commons-lang3)

### Aula 8.22 - Habilitando erros na desserialização de propriedades inexistentes ou ignoradas
Alteração de application.properties para lançamento de erros na desserialização
- Propriedade ignorada - spring.jackson.deserialization.fail-on-ignored-properties=true
- Propriedade inexistente - spring.jackson.deserialization.fail-on-unknown-properties=true


### Aula 8.23 - Desafio: tratando a PropertyBindingException na desserialização
Tratamento da exceção PropertyBindingException para customização da mensagem de erro de propriedade ignorada e inexistente

### Aula 8.24 - Lançando exception de desserialização na atualização parcial (PATCH)
Lançamento da exceção PropertyBindingException em caso de atualização parcial (método HTTP Patch).  
Configuração do lançamento da exceção através do objectMapper.  
Relançamento da exceção IllegalArgumentException como HttpMessageNotReadableException no método merge do controlador de restaurante.  


### Aula 8.25 - Desafio: tratando exception de parâmetro de URL inválido
Implementação da exceção de tipo de parâmetro inválido na URL. 
Configuração personalizada da exceção MethodArgumentTypeMismatchException

### Aula 8.26 - Desafio: tratando a exceção NoHandlerFoundException
Configuração personalizada da exceção NoHandlerFoundException

### Aula 8.27 - Desafio: tratando outras exceções não capturadas
Configuração personalizada das mensagens de exceção para situações não previstas em outros métodos

### Aula 8.29 - Desafio: estendendo o formato do problema
Adição dos atributos *userMessage*e *timestamp* na classe *Problem*.  
Refatoração do método *createProblemBuilder* e dos métodos de lançamento de exceções para tratar os novos atributos criados.  
Criação de mensagem genérica de erro para o usuário.

## Módulo 9

### Aula 9.1 - Validação do modelo com Bean Validation
Adição da dependência *spring-boot-starter-validation*. Essa adição deve ser feita em qualquer projeto Spring a partir da versão 2.3.0

### Aula 9.2 - Adicionando constraints e validando no controller com @Valid
Adição de validação *NotNull* no atributo *nome* da classe *Restaurante*.  
Configuração do controlador com anotação @Valid.

### Aula 9.3 - Desafio: tratando exception de violação de constraints de validação
Customização da exceção *handleMethodArgumentNotValid*

### Aula 9.4 - Estendendo o Problem Details para adicionar as propriedades com constraints violadas
Extensão da classe *Problem* com a lista de campos não validados.

### Aula 9.5 - Conhecendo e adicionando mais constraints de validação no modelo
Adição das constraints @NotEmpty e @NotBlank para o atributo *nome* e @DecimalMin e @PositiveOrZero para o atributo *taxaFrete*.  
Observação: Algumas validações possuem anotações depreciadas do pacote *hibernate.validator*. Não utilizar estas validações.

### Aula 9.6 - Validando as associações de uma entidade em cascata
Validação para a passagem de cozinha com id nulo na entidade Restaurante.  
Observação: Quebra o cadastro de cozinhas

### Aula 9.7 - Agrupando e restringindo constraints que devem ser usadas na validação
Criação de grupos de validação para cada classe.  
Alteração da anotação @Valid para @Validated no controlador para aceitar a configuração dos grupos.

### Aula 9.8 - Convertendo grupos de constraints para validação em cascata com @ConvertGroup
Uso da anotação @ConvertGroup para simplificar a validação em cascata 

### Aula 9.9 - Desafio: adicionando constraints de validação no modelo
Validação nos modelos de Cidade e Estado e controladores de Cidade, Estado e Cozinha (métodos atualizar e adicionar)

### Aula 9.10 - Customizando mensagens de validação na anotação da constraint
Alteração da mensagem de validação através do parâmetro message da anotação

### Aula 9.11 - Customizando e resolvendo mensagens de validação globais em Resource Bundle
Criação do arquivo message.properties para configuração de mensagens de erro globais.  
Configuração da exceção para buscar no arquivo message.properties utilizando uma isntância da classe MessageSource

### Aula 9.12 - Desafio: customizando mensagens de validação
Customização das mensagens de validação das classes Cozinha, Restaurante, Estado e Cidade.  
Adição da constraint NotNull em taxaFrete.

### Aula 9.13 - Resolvendo mensagens de validação com Resource Bundle do Bean Validation
Customização das mensagens do *Bean Validation* através do arquivo ValidationMessages.properties.  
*Resource Bundle* do Spring (message.properties) tem precedência sobre o do *Bean Validation* (ValidationMessages.properties).  
Possível criar propriedade customizada no ValidationMessages e utilizar na propriedade *message* da anotação

### Aula 9.14 - Usando o Resource Bundle do Spring como Resource Bundle do Bean Validation
Criação de classe de configuração de validação (ValidationConfig.java).  
Mudança da configuração de grupos de validação para o pacote core.validation e refatoração nas classes necessárias

### Aula 9.15 - Criando constraints de validação customizadas usando composição
Criação de anotação customizada para validação. 

### Aula 9.16 - Criando constraints de validação customizadas com implementação de ConstraintValidator
Implementação de anotação com regra de negócio específica

### Aula 9.17 - Criando constraints de validação customizadas em nível de classe
Implementação de validação para a classe com verificação de atributos específicos.

### Aula 9.18 - Ajustando Exception Handler para adicionar mensagens de validação em nível de classe
Correção do problema de não mostrar os campos de validação na mensagem de validação (campo *fields*).  
Customização da mensagem de erro de ValorZeroIncluiDescricao.  

### Aula 9.19 - Executando processo de validação programaticamente
Validação programática para a atualização parcial de restaurante.  
Criação de exceção ValidacaoException.  

### Aula 9.20 - Desafio: tratando a exception customizada de validações programáticas
Configuração do lançamento de ValidacaoException através da classe ApiExceptionHandler

## Módulo 10

### Aula 10.3 - Criando e rodando um teste de integração com Spring Boot, JUnit e AssertJ
Testes de integração para verificar cadastro de cozinha com sucesso e com problemas de validação relativos ao nome da cozinha.

### Aula 10.5 - Desafio: escrevendo testes de integração
Testes de integração para exclusão de cozinhas

### Aula 10.7 - Configurando Maven Failsafe Plugin no projeto
Adição do plugin Maven Failsafe para agilizar o processo de build.  
Renomeação da classe CadastroCozinhaIntegrationTests para CadastroCozinhaIT (padrão do Maven Failsafe).   

### Aula 10.8 - Implementando Testes de API com REST Assured e validando o código de status HTTP
Implementação de testes de API.  
COnfiguração do teste para subir um servidor em uma porta específica. 
Log de erros com método *enableLoggingOfRequestAndResponseIfValidationFails*