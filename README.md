# Testes API Java

Projeto de automação de testes de API REST utilizando Java, RestAssured e JUnit 5.

## API Testada

[ServerRest](https://serverest.dev) - API pública para estudo de testes.

## Stack

- Java 21
- RestAssured 5.3.0
- JUnit 5.10.1
- Maven

## Estrutura

```
src/test/java/login/
└── LoginTest.java    ← Testes de login
```

## Como Rodar

```bash
mvn test
```

## Testes

| Cenário | Status Code |
|---------|-------------|
| Login com credenciais válidas | 200 |
| Login com email inexistente | 401 |
| Login com senha incorreta | 401 |
| Login com email e senha inválidos | 401 |
| Login sem informar email | 400 |
| Login sem informar senha | 400 |
| Login com body vazio | 400 |
| Login com email formato inválido | 400 |
| Login com email vazio | 400 |
| Login com senha vazia | 400 |

## CI/CD

O projeto possui um workflow GitHub Actions que executa os testes automaticamente a cada push ou manualmente via `workflow_dispatch`.
