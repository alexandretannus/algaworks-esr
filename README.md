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
