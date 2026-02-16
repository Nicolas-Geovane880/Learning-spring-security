API REST desenvolvida com Spring Boot para gerenciamento acadêmico (alunos, professores e turmas), com autenticação e autorização via Spring Security.

O projeto foi construído com foco em boas práticas de arquitetura backend: separação em camadas, DTOs, validação, mapeamento com MapStruct e regras de negócio em services.

### Funcionalidades

- Autenticação e Segurança
- Login com autenticação baseada em credenciais
- Criptografia de senha com BCrypt
- Controle de acesso por roles (TEACHER, STUDENT)
- Configuração de Spring Security desacoplada

#### Alunos

- Cadastro de aluno
- Atualização de dados
- Regras acadêmicas de aprovação

#### Professores

- Cadastro de professor
- Atualização
- Remoção

#### Turmas

- Criação de turmas
- Associação de professor
- Limite de alunos por turma

### Arquitetura

O projeto segue arquitetura em camadas:

controller → mapper → service → repository  

### Segurança

- PasswordEncoder: BCrypt
- UserDetails custom por tipo de usuário
- Enum de roles
- Filtro de autenticação
- Configuração centralizada de Security

### Persistência

- Spring Data JPA/ Hibernate

#### Relacionamentos:

- Teacher → Classes (1:N)
- Class → Students (1:N)
- Student → Grades

### Validação

Validação com Bean Validation + regras de negócio em services.

Exemplos:

- Email válido
- Senha obrigatória
- Limite de alunos por turma
- Limite de turmas por professor

### Regras de Negócio

- Professor possui limite máximo de turmas
- Turma possui limite máximo de alunos
- Aluno é aprovado conforme média de notas
- Email único no sistema

### Tecnologias

- Java/Spring Boot
- Spring Security
- Spring Data JPA / Hibernate
- MapStruct
- Bean Validation
- BCrypt
- Maven
- Docker
- Flyway

### Endpoints

Alguns endpoints são necessários autenticação. Todos os endpoints foram acessados usando o PostMan.
Basta ir na aba de Authorization, em Auth Type coloque Basic Auth (o campo "username" e "password" são respectivamente o email e a senha do usuário criado)

Caso rode direto na IDE (na qual usa o banco baseado em memória H2)
rode em http://localhost:8080. Caso esteja rodando com docker-compose, rode em http://localhost

````
 POST: Valores passados no corpo da requisição
 - /api/v1/student/save (requires body)
 - /api/v1/teacher/save (requires body)
 - /api/v1/class/save (requires body)
 - /api/v1/grade/set-grades (requires body)
 
 GET: Valores passados na URL
 - /api/v1/student/find/{id} (requres id)
 - /api/v1/teacher/find/{id} (requires id)
 - /api/v1/class/find/{id} (requires id)
 - /api/v1/class/all-by-discipline/{discipline} (requires discipline)
 - /api/v1/student/passed-by-class/{id} (requires id)
   
 PUT: Valores passados na URL e no corpo da requisição
 - /api/v1/student/update/{id} (requires id and body)
 - /api/v1/teacher/update/{id} (requires id and body)
 - /api/v1/class/update/{id} (requires id and body)
 
 DELETE: Valores passados na URL 
 - /api/v1/student/delete/{id} (requires id)
 - /api/v1/teacher/delete/{id} (requires id)
 - /api/v1/class/delete/{id} (requires id)
````


