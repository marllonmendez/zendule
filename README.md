# Instruções de uso

## Sobre a documentação
**⚠️ Obersvação:** _A documentação foi elaborada apenas para o **User Service**, pois o Email Service atua exclusivamente como um consumidor de mensagens — sua responsabilidade é apenas receber os dados do usuário e enviar o e-mail de boas-vindas. Como não há interação direta do usuário final com o microsserviço de e-mail, não há necessidade de expor sua API._

### 📚 Wiki no GitHub
```
https://github.com/marllonmendez/zendule/wiki
```
### 📌 Swagger - User Service
```
http://localhost:8081/swagger-ui/index.html
```

## 🔧 Dependências no ``application.properties``

### 1. 🔗 [PostgreSQL (PgAdmin4) - Clique aqui](https://www.postgresql.org/)
Crie um banco de dados para cada serviço da sua aplicação, configurando:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```
💡 Use o PgAdmin para gerenciar o banco de forma visual.

### 2. 🐰 [CloudAMQP - Clique aqui](https://www.cloudamqp.com/)
- Acesse CloudAMQP e crie uma instância gratuita.
- Escolha a região AWS São Paulo (sa-east-1) para menor latência.
- Após criada, copie a URL de conexão do painel, exemplo de imagem ILUSTRATIVA.
![CloudAMQP](/assets/cloudAMQP.png)
```
spring.rabbitmq.addresses=url_cloudAMQP
```

### 3. 📺 [App Password Google - Clique aqui](https://www.youtube.com/watch?v=A6lASY-Yu-I&t=2s)
O Gmail exige uma senha específica de aplicativo para permitir o envio de e-mails via SMTP em aplicações externas.
Este vídeo ensina como gerar essa senha utilizando sua conta Google com autenticação em duas etapas
```
spring.mail.username=seu_email@gmail.com
spring.mail.password=sua_app_password
```

## 💻 Execução dos Microsserviços

### 1. 🧬 Clone o projeto
```
https://github.com/marllonmendez/zendule.git
```

### 2. 🧱 Instale as dependências e compile
```
mvn clean install
```

### 3. 🚀 Execute a aplicação Spring Boot
```
mvn spring-boot:run
```

## 🧪 Testes

### 1. Inicie primeiro o [User Service](user).
Você pode utilizar Swagger para realizar testes e criar um usuário.

### 2. Após isso, inicie o [Email Service](email).
Se tudo estiver configurado corretamente, você deverá receber um e-mail de boas-vindas no endereço utilizado na criação do usuário.

## 📷 Evidências

### [Email Service](email) recebendo dados de [User Service](user)
![Email Service](/assets/email-service.png)

### Envio do E-mail de boas-vindas
![Email Service](/assets/boas-vindas.png)

### Documentação no Swagger
![Swagger](/assets/swagger.png)

## 👥 Equipe
- [Marlon Mendes](https://github.com/marllonmendez)
- [Daniella Brito](https://github.com/daniellabritto)
- [David Matheus](https://github.com/davidmatheusk)
- [Gean Ribeiro](https://github.com/Rayleigh48)
