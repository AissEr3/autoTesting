package util;

import pages.LoginPage;
import pages.admin.AdminHomePage;
import pages.admin.usermanage.UserManageHomePage;
import pages.admin.usermanage.subPage.AddUserPage;

public class UserManagerUtil {
    private UserManagerUtil(){

    }

    public static AddUserPage openAddUserPage(LoginPage login){
        LoginUtil.login(login,"admin","123456");
        return new AdminHomePage().openUserManager().clickAddUserButton();
    }
}
