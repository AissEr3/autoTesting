package util;

import pages.LoginPage;
import pages.admin.AdminHomePage;

public class LoginUtil {
    private LoginUtil(){

    }

    public static void login(LoginPage login,String username,String password){
        login.inputUsername(username).inputPassword(password).clickLoginButton();
    }

    public static AdminHomePage loginByAdmin(){
        login(LoginPage.getInstance(),"admin","123456");
        return AdminHomePage.getInstance();
    }

    public static void loginBySystem(){
        login(LoginPage.getInstance(),"system","123456");
    }

    public static void loginByShenhe(){
        login(LoginPage.getInstance(),"shenhe","123456");
    }

    public static void loginByManager(){
        login(LoginPage.getInstance(),"manager","123456");
    }
}
