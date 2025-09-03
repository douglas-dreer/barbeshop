# Barbershop Server API

API de backend para o sistema de gerenciamento de barbearia, desenvolvida com Kotlin e Spring Boot.

## 🚀 Tecnologias Utilizadas

- **Linguagem:** [Kotlin](https://kotlinlang.org/)
- **Framework:** [Spring Boot 3](https://spring.io/projects/spring-boot)
- **Acesso a Dados:
  ** [Spring Data JPA](https://spring.io/projects/spring-data-jpa) / [Hibernate](https://hibernate.org/)
- **Banco de Dados:** [Oracle](https://www.oracle.com/database/)
- **Build Tool:** Maven ou Gradle

---

## ⚙️ Como Executar o Projeto Localmente

Siga os passos abaixo para configurar e executar a aplicação em seu ambiente de desenvolvimento.

### 1. Pré-requisitos

- **Java Development Kit (JDK):** Versão 17 ou superior.
- **Build Tool:** [Maven](https://maven.apache.org/) ou [Gradle](https://gradle.org/) instalado.
- **Docker:** (Recomendado) Para executar uma instância do banco de dados Oracle facilmente.

### 2. Configuração do Banco de Dados

A forma mais simples de ter um banco de dados Oracle rodando é via Docker.

1. **Execute o container do Oracle XE:**
   Use o comando abaixo para iniciar uma instância do Oracle Express Edition. A senha do usuário `SYSTEM` será `oracle`.

   ```bash
   docker run -d --name oracle-xe -p 1521:1521 -e ORACLE_PASSWORD=oracle gvenzl/oracle-xe:21-slim-faststart
   ```

2. **Aguarde o Banco de Dados Iniciar:**
   Pode levar alguns minutos para o banco de dados estar totalmente operacional. Você pode verificar os logs com
   `docker logs -f oracle-xe`.

### 3. Configuração da Aplicação

O projeto utiliza o arquivo `application-dev.yml` para as configurações de desenvolvimento. Ele está preparado para usar
variáveis de ambiente, mas também possui valores padrão.

- **Local:** `src/main/resources/application-dev.yml`

```yaml
spring:
  datasource:
    url: jdbc:oracle:thin:@${DATASOURCE_HOST:localhost}:${DATASOURCE_PORT:1521}:${DATASOURCE_SERVICE:XE}
    username: ${USERNAME_DB:barman}
    password: ${PASSWORD_DB:Barman@123456}
```

Se você usou o comando Docker acima, as configurações padrão para `host`, `port` e `service` funcionarão. Você só
precisa garantir que o usuário e a senha (`barman`/`Barman@123456`) existam no banco de dados ou altere-os para um
usuário válido (como `SYSTEM`/`oracle` para testes iniciais).

### 4. Executando a Aplicação

Você pode iniciar a aplicação de duas maneiras:

1. **Via Linha de Comando (usando o wrapper):**

    * Com Maven:
      ```bash
      ./mvnw spring-boot:run
      ```
    * Com Gradle:
      ```bash
      ./gradlew bootRun
      ```

2. **Via IDE (IntelliJ IDEA, VS Code, etc.):**
    * Importe o projeto como um projeto Maven/Gradle.
    * Encontre a classe `ServerApplicationKt` e execute o método `main`.

Após a inicialização, a API estará disponível em `http://localhost:8080`.

---

## 🏛️ Estrutura do Projeto

O projeto segue uma arquitetura em camadas para separar as responsabilidades:

- `domain`: Contém a lógica de negócio principal, incluindo entidades, exceções de negócio (
  `ServicoJaCadastradoException`) e interfaces de repositório.
- `infrastructure`: Implementações de tecnologias e frameworks.
    - `web`: Controladores REST e tratamento global de exceções (`GlobalExceptionHandler`).
    - `persistence`: Implementações dos repositórios do `domain` usando Spring Data JPA.
- `application`: (Camada de serviço) Orquestra os casos de uso, utilizando os componentes do `domain`.