package util;

import pages.LoginPage;

public class LoginUtil {
    private LoginUtil(){

    }

    public static void login(LoginPage login,String username,String password){
        login.inputUsername(username).inputPassword(password).clickLoginButton();
    }
}
