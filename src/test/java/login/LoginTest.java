package login;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LoginTest {

    private static final String BASE_URI = "https://serverest.dev";

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = BASE_URI;
    }

    @Test
    @DisplayName("Login com credenciais válidas deve retornar 200 e token de autorização")
    void loginComCredenciaisValidas() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"email\":\"fulano@qa.com\",\"password\":\"teste\"}")
        .when()
            .post("/login")
        .then()
            .statusCode(200)
            .body("message", equalTo("Login realizado com sucesso"))
            .body("authorization", notNullValue())
            .body("authorization", startsWith("Bearer"));
    }

    @Test
    @DisplayName("Login com email inválido deve retornar 401")
    void loginComEmailInvalido() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"email\":\"invalido@teste.com\",\"password\":\"teste\"}")
        .when()
            .post("/login")
        .then()
            .statusCode(401)
            .body("message", equalTo("Email e/ou senha inválidos"));
    }

    @Test
    @DisplayName("Login com senha inválida deve retornar 401")
    void loginComSenhaInvalida() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"email\":\"fulano@qa.com\",\"password\":\"senhaerrada\"}")
        .when()
            .post("/login")
        .then()
            .statusCode(401)
            .body("message", equalTo("Email e/ou senha inválidos"));
    }

    @Test
    @DisplayName("Login com email e senha inválidos deve retornar 401")
    void loginComCredenciaisInvalidas() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"email\":\"invalido@teste.com\",\"password\":\"senhaerrada\"}")
        .when()
            .post("/login")
        .then()
            .statusCode(401)
            .body("message", equalTo("Email e/ou senha inválidos"));
    }

    @Test
    @DisplayName("Login sem informar email deve retornar 400")
    void loginSemEmail() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"password\":\"teste\"}")
        .when()
            .post("/login")
        .then()
            .statusCode(400)
            .body("email", equalTo("email é obrigatório"));
    }

    @Test
    @DisplayName("Login sem informar senha deve retornar 400")
    void loginSemSenha() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"email\":\"fulano@qa.com\"}")
        .when()
            .post("/login")
        .then()
            .statusCode(400)
            .body("password", equalTo("password é obrigatório"));
    }

    @Test
    @DisplayName("Login com body vazio deve retornar 400 para email e senha")
    void loginSemBody() {
        given()
            .contentType(ContentType.JSON)
            .body("{}")
        .when()
            .post("/login")
        .then()
            .statusCode(400)
            .body("email", equalTo("email é obrigatório"))
            .body("password", equalTo("password é obrigatório"));
    }

    @Test
    @DisplayName("Login com email em formato inválido deve retornar 400")
    void loginComEmailFormatoInvalido() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"email\":\"nao_eh_um_email\",\"password\":\"teste\"}")
        .when()
            .post("/login")
        .then()
            .statusCode(400);
    }

    @Test
    @DisplayName("Login com email vazio deve retornar 400")
    void loginComEmailVazio() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"email\":\"\",\"password\":\"teste\"}")
        .when()
            .post("/login")
        .then()
            .statusCode(400)
            .body("email", equalTo("email não pode ficar em branco"));
    }

    @Test
    @DisplayName("Login com senha vazia deve retornar 400")
    void loginComSenhaVazia() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"email\":\"fulano@qa.com\",\"password\":\"\"}")
        .when()
            .post("/login")
        .then()
            .statusCode(400)
            .body("password", equalTo("password não pode ficar em branco"));
    }
}
