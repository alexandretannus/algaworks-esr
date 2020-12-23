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