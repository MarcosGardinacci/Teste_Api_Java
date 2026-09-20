package tests.login;

import helpers.RequestHelper;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.AuthService;

import static org.hamcrest.Matchers.*;

public class LoginTest {

    @BeforeEach
    void setUp() {
        RequestHelper.setup();
    }

    @Test
    @DisplayName("Login com credenciais válidas deve retornar 200 e token")
    void loginComCredenciaisValidas() {
        Response response = AuthService.loginWithValidCredentials();

        response.then()
            .statusCode(200)
            .body("message", equalTo("Login realizado com sucesso"))
            .body("authorization", notNullValue())
            .body("authorization", startsWith("Bearer"));
    }

    @Test
    @DisplayName("Login com email inválido deve retornar 401")
    void loginComEmailInvalido() {
        Response response = AuthService.loginWithInvalidEmail();

        response.then()
            .statusCode(401)
            .body("message", equalTo("Email e/ou senha inválidos"));
    }

    @Test
    @DisplayName("Login com senha inválida deve retornar 401")
    void loginComSenhaInvalida() {
        Response response = AuthService.loginWithInvalidPassword();

        response.then()
            .statusCode(401)
            .body("message", equalTo("Email e/ou senha inválidos"));
    }

 

    @Test
    @DisplayName("Login sem informar email deve retornar 400")
    void loginSemEmail() {
        Response response = AuthService.loginWithoutEmail();

        response.then()
            .statusCode(400)
            .body("email", equalTo("email é obrigatório"));
    }

    @Test
    @DisplayName("Login sem informar senha deve retornar 400")
    void loginSemSenha() {
        Response response = AuthService.loginWithoutPassword();

        response.then()
            .statusCode(400)
            .body("password", equalTo("password é obrigatório"));
    }

  

    @Test
    @DisplayName("Login com email formato inválido deve retornar 400")
    void loginComEmailFormatoInvalido() {
        Response response = AuthService.loginWithInvalidFormatEmail();

        response.then()
            .statusCode(400);
    }

   
}
