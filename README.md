# 📚 Forum Hub API

## 📖 **Descrição**

O **Forum Hub API** é uma aplicação REST desenvolvida com **Spring Boot** que permite o gerenciamento de tópicos de discussão. A API inclui funcionalidades de autenticação com **JWT** (JSON Web Token), segurança via **Spring Security** e persistência de dados utilizando o banco de dados **PostgreSQL**.

A aplicação foi criada para oferecer um CRUD completo de tópicos, com endpoints protegidos por autenticação e documentação automatizada utilizando **Swagger UI**.

---

## 🛠️ **Tecnologias Utilizadas**

A API foi construída utilizando as seguintes tecnologias:

| Tecnologia         | Descrição                                            |
|--------------------|----------------------------------------------------|
| **Spring Boot**    | Framework para criar aplicações Java de forma rápida e produtiva. |
| **Spring Security**| Biblioteca de segurança que adiciona autenticação e autorização. |
| **JWT (JSON Web Token)** | Método de autenticação para proteger os endpoints da API. |
| **PostgreSQL**     | Banco de dados relacional utilizado para persistência dos dados. |
| **Spring Data JPA**| Abstração para facilitar o acesso ao banco de dados. |
| **Swagger UI**     | Ferramenta de documentação interativa para APIs REST. |

---

## ⚙️ **Configuração Inicial**

1️⃣ **Clone o repositório:**

```bash
git clone https://github.com/seu-usuario/forum-hub.git
cd forum-hub
```

2️⃣ **Configure o banco de dados no arquivo `application.properties`** localizado em `src/main/resources/`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/forum_hub
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
```

3️⃣ **Execute a aplicação:**

```bash
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

---

## 🔐 **Segurança da API**

Todos os endpoints, exceto `/login`, são protegidos por **JWT** e requerem um token de autenticação válido. As senhas dos usuários são armazenadas de forma segura utilizando o algoritmo **BCrypt**.

---

## 🔑 **Como Autenticar na API**

Para obter um **token JWT**, faça uma requisição POST para o endpoint `/login` com as credenciais de um usuário válido:

### **POST** `/login`

Exemplo de payload da requisição:

```json
{
  "login": "admin",
  "senha": "123456"
}
```

Se as credenciais forem válidas, a API retornará um token JWT:

```json
{
  "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZGF1dCIsImlzcyI6IkFQSSBGb3J1bSBIdWIiLCJleHAiOjE3MzY3NT..."
}
```

Esse token deve ser enviado no cabeçalho das próximas requisições, conforme o exemplo abaixo:

```
Authorization: Bearer <seu_token>
```

---

## 📄 **Documentação com Swagger UI**

A documentação interativa da API está disponível em:

👉 **Swagger UI:**  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 🚀 **Endpoints Disponíveis**

### 🔐 **Autenticação**
- **POST** `/login`  
  Realiza a autenticação do usuário e retorna um token JWT.

---

### 📋 **Gerenciamento de Tópicos**
Base URL: `/topicos`

| Método  | Endpoint         | Descrição                                  |
|---------|------------------|------------------------------------------|
| **GET** | `/topicos`        | Lista todos os tópicos.                  |
| **POST**| `/topicos`        | Cadastra um novo tópico.                 |
| **GET** | `/topicos/{id}`   | Busca um tópico específico por ID.       |
| **PUT** | `/topicos/{id}`   | Atualiza as informações de um tópico.    |
| **DELETE** | `/topicos/{id}`| Exclui um tópico pelo ID.                |

---

### ✅ **Detalhes dos Endpoints**

#### **1. Cadastrar um Novo Tópico**

**POST** `/topicos`

Exemplo de Payload da Requisição:

```json
{
  "tituloDoComentario": "Como configurar Spring Security",
  "mensagem": "Estou com uma dúvida sobre Spring Security",
  "autor": "João Silva",
  "curso": "Spring Boot"
}
```

---

#### **2. Buscar Tópico por ID**

**GET** `/topicos/{id}`

Esse endpoint permite buscar um tópico específico pelo ID informado.

---

#### **3. Atualizar um Tópico**

**PUT** `/topicos/{id}`

Exemplo de Payload da Requisição:

```json
{
  "tituloDoComentario": "Título Atualizado",
  "mensagem": "Mensagem Atualizada"
}
```

---

#### **4. Excluir um Tópico**

**DELETE** `/topicos/{id}`

---

## 🧪 **Como Testar a API**

Você pode testar os endpoints da API utilizando ferramentas como **Postman**, **Insomnia**, ou diretamente através do **Swagger UI**.

**Exemplo de Teste via Swagger UI:**
1. Acesse `http://localhost:8080/swagger-ui/index.html`.
2. Faça o login no endpoint `/login` e copie o token JWT gerado.
3. Use o token JWT para autenticar nas demais requisições.

---

## 🎯 **Desafios e Funcionalidades Extras**

Durante o desenvolvimento, alguns desafios adicionais foram implementados:

1. **Buscar Tópicos Recentes:**  
   Endpoint `/topicos/recentes` retorna os 10 tópicos mais recentes.

2. **Buscar Tópicos por Curso e Ano:**  
   Endpoint `/topicos/buscar?curso=<curso>&ano=<ano>` permite filtrar tópicos por curso e ano específico.

---

## 🛡️ **Regras de Negócio Implementadas**

- Todos os tópicos precisam de um ID para serem atualizados, excluídos ou buscados.
- Apenas usuários autenticados podem acessar a API (exceto o endpoint de login).
- As senhas dos usuários são armazenadas de forma segura com **BCrypt**.
- Verificação para evitar o cadastro de tópicos duplicados (mesmo título e mensagem).

---

## 🔧 **Possíveis Melhorias Futuras**

- Implementar paginação e ordenação avançada.
- Adicionar suporte para comentários nos tópicos.
- Melhorar a interface da documentação com Swagger.

---

## 📧 **Contato**

- Desenvolvedor: Adaut Lima
- Email: adautlima@gmail.com
- LinkedIn: (https://www.linkedin.com/in/adaut-sacchi-d-albuquerque-lima-67a314210/)

---

Feito com ❤️ usando **Spring Boot** e **PostgreSQL**.
