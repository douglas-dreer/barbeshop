# Changelog

Todos as mudanças notáveis neste projeto serão documentadas neste arquivo.

O formato é baseado em [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
e este projeto adere ao [Versionamento Semântico](https://semver.org/spec/v2.0.0.html).

## [Não Lançado]

### Adicionado
- 

### Modificado
- 

### Corrigido
- 

---

## [0.1.0] - 2024-08-01

*Esta é a versão inicial do projeto, estabelecendo a base da arquitetura e funcionalidades principais.*

### Adicionado

- **Estrutura do Projeto:** Configuração inicial do projeto com Kotlin, Spring Boot e Gradle/Maven.
- **Persistência de Dados:** Integração com o banco de dados Oracle via Spring Data JPA e Hibernate.
- **Configuração de Ambiente:** Criação do perfil de desenvolvimento (`application-dev.yml`) com suporte a variáveis de
  ambiente para configuração do datasource.
- **Tratamento de Exceções:** Implementação de um `GlobalExceptionHandler` para padronizar as respostas de erro da API.
    - Adicionado `ErroDto` para estruturar as mensagens de erro.
    - Mapeamento para exceções de validação (`MethodArgumentNotValidException`), regras de negócio e erros genéricos.
- **Exceções de Negócio:** Criação de exceções customizadas como `ServicoJaCadastradoException` para representar regras
  de negócio específicas.
- **Documentação:** Adição dos arquivos `README.md` e `CHANGELOG.md` para documentar o projeto.