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

    @DisplayName("����ɫ��ȷ��¼ϵͳ����")
    @ParameterizedTest
    @ValueSource(strings = {"admin","system","shenhe","manager"})
    public void rightLoginTest(String username){
        LoginUtil.login(login,username,DEFAULT_PASSWORD);
        driverWait.until(ExpectedConditions.titleIs("��ҵ����ƽ̨"));
        assertThat(login.getUserName()).isEqualTo(username);
    }

    @DisplayName("�û�����������д�������")
    @ParameterizedTest
    @CsvFileSource(resources = "/loginTestCsv/wrongTest.csv",numLinesToSkip = 1)
    public void wrongLoginTest(String username,String password){
        LoginUtil.login(login,username,password);
        // �����ʱ����Ȼû����ʾ������Ϣ����ò���ʧ��
        try{
            driverWait.until(ExpectedConditions.textToBe(By.id("showErrMsg"),"�û������������"));
        }catch(TimeoutException e){
            fail("login error not found error message");
        }
    }

    @DisplayName("�û���Ϊ�ղ���")
    @Test
    public void UsernameEmptyTest(){
        LoginUtil.login(login,"","123456");
        // �����ʱ����Ȼû����ʾ������Ϣ����ò���ʧ��
        try{
            driverWait.until(ExpectedConditions.textToBe(By.id("showErrMsg"),"�������û���"));
        }catch(TimeoutException e){
            fail("login error not found error message");
        }
    }

    @DisplayName("����Ϊ�ղ���")
    @Test
    public void passwordEmptyTest(){
        LoginUtil.login(login,"","");
        // �����ʱ����Ȼû����ʾ������Ϣ����ò���ʧ��
        try{
            driverWait.until(ExpectedConditions.textToBe(By.id("showErrMsg"),"�������û���"));
        }catch(TimeoutException e){
            fail("login error not found error message");
        }
    }

}
