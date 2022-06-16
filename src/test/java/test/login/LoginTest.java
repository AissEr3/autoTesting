package test.login;

import base.BaseTest;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import util.LoginUtil;

public class LoginTest extends BaseTest {
    private LoginPage login;
    private static final String DEFAULT_PASSWORD = "123456";

    @BeforeEach
    public void setUp(){
        driver.get(url);
        login = new LoginPage();
    }

    @DisplayName("各角色正确登录系统测试")
    @ParameterizedTest
    @ValueSource(strings = {"admin","system","shenhe","manager"})
    public void rightLoginTest(String username){
        LoginUtil.login(login,username,DEFAULT_PASSWORD);
        driverWait.until(ExpectedConditions.titleIs("物业管理平台"));
        assertThat(login.getUserName()).isEqualTo(username);
    }

    @DisplayName("用户名或密码填写错误测试")
    @ParameterizedTest
    @CsvFileSource(resources = "/loginTestCsv/wrongTest.csv",numLinesToSkip = 1)
    public void wrongLoginTest(String username,String password){
        LoginUtil.login(login,username,password);
        // 如果超时了仍然没有显示错误信息，则该测试失败
        try{
            driverWait.until(ExpectedConditions.textToBe(By.id("showErrMsg"),"用户名或密码错误"));
        }catch(TimeoutException e){
            fail("login error not found error message");
        }
    }

    @DisplayName("用户名为空测试")
    @Test
    public void UsernameEmptyTest(){
        LoginUtil.login(login,"","123456");
        // 如果超时了仍然没有显示错误信息，则该测试失败
        try{
            driverWait.until(ExpectedConditions.textToBe(By.id("showErrMsg"),"请输入用户名"));
        }catch(TimeoutException e){
            fail("login error not found error message");
        }
    }

    @DisplayName("密码为空测试")
    @Test
    public void passwordEmptyTest(){
        LoginUtil.login(login,"","");
        // 如果超时了仍然没有显示错误信息，则该测试失败
        try{
            driverWait.until(ExpectedConditions.textToBe(By.id("showErrMsg"),"请输入用户名"));
        }catch(TimeoutException e){
            fail("login error not found error message");
        }
    }
}
