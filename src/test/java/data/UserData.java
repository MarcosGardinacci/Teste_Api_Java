package data;

public class UserData {

    public static String validEmail() {
        return "horadoqa@qa.com.br";
    }

    public static String validPassword() {
        return "horadoqa";
    }

    public static String invalidEmail() {
        return "invalido@teste.com";
    }

    public static String invalidPassword() {
        return "senhaerrada";
    }

    public static String invalidFormatEmail() {
        return "nao_eh_um_email";
    }

    public static String emptyEmail() {
        return "";
    }

    public static String emptyPassword() {
        return "";
    }
}
