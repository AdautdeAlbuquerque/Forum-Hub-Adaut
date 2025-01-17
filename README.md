# 📚 **Forum Hub API**

Bem-vindo ao **Forum Hub API**, o terceiro desafio da série **Challenge Back End** da Alura! Este projeto consiste na construção de uma API REST para gerenciamento de tópicos de discussão, utilizando as melhores práticas de desenvolvimento backend com **Spring Boot**.

## ✨ **História**

Um fórum é um espaço de aprendizado e colaboração, onde participantes podem tirar dúvidas, compartilhar ideias e buscar soluções. Neste desafio, o objetivo é recriar a lógica por trás de um fórum, como o utilizado na Alura, focando no backend e simulando funcionalidades essenciais.

Nosso desafio é implementar uma API que permita:

- **Criar** novos tópicos;
- **Listar** todos os tópicos;
- **Buscar** tópicos específicos por ID;
- **Atualizar** tópicos existentes;
- **Excluir** tópicos.

Essas operações, conhecidas como **CRUD** (Create, Read, Update, Delete), são implementadas com uma abordagem REST, com autenticação e autorização seguras, e persistência de dados em um banco relacional.

---

## 🚀 **Funcionalidades**

A API oferece:

1. **CRUD de Tópicos**: Operações completas de criação, leitura, atualização e exclusão.
2. **Autenticação JWT**: Apenas usuários autenticados podem acessar os endpoints.
3. **Validações**: As entradas são validadas conforme as regras de negócio.
4. **Segurança**: As senhas são armazenadas de forma segura utilizando **BCrypt**.
5. **Persistência**: Uso de banco de dados relacional **PostgreSQL** com mapeamento via **JPA**.
6. **Documentação Interativa**: Disponível via **Swagger UI**.

---

## 🛠️ **Tecnologias Utilizadas**

| Tecnologia            | Descrição                                              |
|-----------------------|--------------------------------------------------------|
| **Java**             | Linguagem de programação principal usada no projeto.    |
| **Spring Boot**       | Framework para desenvolvimento rápido de APIs REST.    |
| **Spring Security**   | Biblioteca para autenticação e autorização seguras.    |
| **JWT (JSON Web Token)** | Método para autenticação e proteção dos endpoints.      |
| **PostgreSQL**        | Banco de dados relacional utilizado para persistência.  |
| **Spring Data JPA**   | Abstração para simplificar o acesso ao banco de dados.  |
| **Swagger UI**        | Ferramenta para documentação interativa da API.         |
| **BCrypt**            | Algoritmo para criptografia segura de senhas.           |


---

## ⚙️ **Como Configurar**

### 1️⃣ **Clonar o Repositório**

```bash
git clone https://github.com/seu-usuario/forum-hub.git
cd forum-hub
```

### 2️⃣ **Configurar o Banco de Dados**

Edite o arquivo `application.properties` em `src/main/resources`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/forum_hub
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
```

### 3️⃣ **Executar a Aplicação**

Certifique-se de ter o **Maven** configurado e execute o comando:

```bash
mvn spring-boot:run
```

A aplicação estará disponível em: [http://localhost:8080](http://localhost:8080).

---

## 🔐 **Autenticação**

### **Como Obter um Token JWT**

1. Faça uma requisição **POST** para o endpoint `/login` com as credenciais de um usuário válido (É importante cadastrar o usuário e a senha já criptografada:

   **Payload de Exemplo:**

   ```json
   {
     "login": "admin",
     "senha": "123456"
   }
   ```

   ## 🔐 **Importante: Cadastro de Usuários para Autenticação**

Para testar a API e obter um token JWT válido, é necessário que o usuário esteja previamente cadastrado no banco de dados com a senha criptografada. A aplicação utiliza o algoritmo **BCrypt** para proteger as senhas.

### **Como Cadastrar o Usuário**

1. Insira manualmente o usuário no banco de dados, garantindo que a senha seja armazenada de forma criptografada.

2. Utilize um gerador de hash **BCrypt** para gerar a senha criptografada. Você pode usar ferramentas online, como [BCrypt Generator](https://bcrypt-generator.com/), ou bibliotecas em sua linguagem de preferência.

   **Exemplo de Senha Criptografada:**
   - Senha: `123456`
   - Hash gerado com BCrypt: `$2a$10$WzB5TvhdUqmvZ2Ns1OgU..V0H6mF...`

3. Insira os dados no banco de dados manualmente ou via script SQL:

   ```
INSERT INTO usuarios (login, senha, role) 
VALUES ('admin', '$2a$10$WzB5TvhdUqmvZ2Ns1OgU..V0H6mF...', 'ROLE_USER');
   ```

   - **`login`**: Nome de usuário (e-mail ou identificador único).
   - **`senha`**: Hash da senha gerado com **BCrypt**.
   - **`role`**: Papel ou permissão (exemplo: `ROLE_USER`).

4. Após isso, use as credenciais para realizar a autenticação via endpoint `/login`.

---

**Observação:** Durante o desenvolvimento ou testes, você pode implementar um endpoint temporário de registro, mas lembre-se de removê-lo ou protegê-lo em ambientes de produção.

2. A resposta incluirá um token JWT:

   **Resposta de Exemplo:**

   ```json
   {
     "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6IkFQSSBGb3J1bSBIdWIiLCJleHAiOjE3MzY3..."
   }
   ```

3. Utilize o token nas requisições subsequentes, enviando-o no cabeçalho:

   ```
   Authorization: Bearer <seu_token>
   ```

---

## 📄 **Documentação da API**

Acesse a documentação interativa via Swagger UI em:  
👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 📋 **Endpoints Disponíveis**

### **Autenticação**

| Método  | Endpoint  | Descrição                                    |
|---------|-----------|----------------------------------------------|
| **POST**| `/login`  | Realiza a autenticação e retorna um token JWT.|

### **Gerenciamento de Tópicos**

| Método    | Endpoint          | Descrição                          |
|-----------|-------------------|-------------------------------------|
| **GET**   | `/topicos`         | Lista todos os tópicos.            |
| **POST**  | `/topicos`         | Cadastra um novo tópico.           |
| **GET**   | `/topicos/{id}`    | Busca um tópico específico por ID. |
| **PUT**   | `/topicos/{id}`    | Atualiza informações de um tópico. |
| **DELETE**| `/topicos/{id}`    | Exclui um tópico pelo ID.          |

---

## 📦 **Exemplos de Uso**

### 1️⃣ **Cadastrar um Novo Tópico**

**POST** `/topicos`

**Payload de Exemplo:**

```json
{
  "tituloDoComentario": "Dúvida sobre Spring Boot",
  "mensagem": "Como configurar o Spring Security?",
  "autor": "João Silva",
  "curso": "Spring Boot"
}
```

---

### 2️⃣ **Buscar Tópico por ID**

**GET** `/topicos/{id}`

---

### 3️⃣ **Atualizar um Tópico**

**PUT** `/topicos/{id}`

**Payload de Exemplo:**

```json
{
  "tituloDoComentario": "Título Atualizado",
  "mensagem": "Mensagem Atualizada"
}
```

---

### 4️⃣ **Excluir um Tópico**

**DELETE** `/topicos/{id}`

---

## 🎯 **Funcionalidades Extras**

1. **Tópicos Recentes:**  
   Endpoint `/topicos/recentes` para listar os 10 tópicos mais recentes.

2. **Filtrar por Curso e Ano:**  
   Endpoint `/topicos/buscar?curso=<curso>&ano=<ano>` para filtrar tópicos por curso e ano.

---

## 🔧 **Possíveis Melhorias**

- Implementar paginação e ordenação.
- Adicionar suporte a comentários em tópicos.
- Melhorar a experiência de documentação com Swagger.

---

## 📧 **Contato**

- **Desenvolvedor:** Adaut Lima  
- **Email:** adautlima@gmail.com  
- **LinkedIn:** [Adaut Lima](https://www.linkedin.com/in/adaut-sacchi-d-albuquerque-lima-67a314210/)  

