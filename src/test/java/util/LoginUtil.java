package util;

import pages.LoginPage;

public class LoginUtil {
    public static void login(LoginPage login,String username,String password){
        login.inputUsername(username);
        login.inputPassword(password);
        login.clickLoginButton();
    }
}
