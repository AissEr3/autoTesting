package test.loginTest;

import base.BaseTest;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    private LoginPage login;
    private static String rightPassword = "123456";

    @BeforeEach
    public void setUp(){
        driver.get(url);
        login = new LoginPage();
    }

    @ParameterizedTest
    @ValueSource(strings = {"admin","system","shenhe","manager"})
    public void rightLoginTest(String username){
        login.inputUsername(username);
        login.inputPassword(rightPassword);
        login.clickLoginButton();
        driverWait.until(ExpectedConditions.titleIs("物业管理平台"));
        assertThat(login.getUserName()).isEqualTo(username);
    }
}
