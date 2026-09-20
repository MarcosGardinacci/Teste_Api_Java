package services;

import data.UserData;
import helpers.RequestHelper;
import io.restassured.response.Response;

public class AuthService {

    private static final String LOGIN_PATH = "/login";

    public static Response login(String email, String password) {
        String body = String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password);
        return RequestHelper.post(LOGIN_PATH, body);
    }

    public static Response loginWithValidCredentials() {
        return login(UserData.validEmail(), UserData.validPassword());
    }

    public static Response loginWithInvalidEmail() {
        return login(UserData.invalidEmail(), UserData.validPassword());
    }

    public static Response loginWithInvalidPassword() {
        return login(UserData.validEmail(), UserData.invalidPassword());
    }

    public static Response loginWithInvalidCredentials() {
        return login(UserData.invalidEmail(), UserData.invalidPassword());
    }

    public static Response loginWithoutEmail() {
        return RequestHelper.post(LOGIN_PATH, "{\"password\":\"teste\"}");
    }

    public static Response loginWithoutPassword() {
        return RequestHelper.post(LOGIN_PATH, "{\"email\":\"horadoqa@qa.com.br\"}");
    }

    

    public static Response loginWithInvalidFormatEmail() {
        return login(UserData.invalidFormatEmail(), UserData.validPassword());
    }
}
