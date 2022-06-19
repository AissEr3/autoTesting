package test.usermanage;

import base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import pages.admin.AdminHomePage;
import pages.admin.usermanage.UserManageHomePage;
import pages.admin.usermanage.subpage.ResetPasswordPage;
import util.LoginUtil;

import static org.assertj.core.api.Assertions.assertThat;

public class ResetPasswordPageTest extends BaseTest {
    private String realName = "AissEr1";
    private UserManageHomePage index;
    private ResetPasswordPage reset;

    @BeforeEach
    public void setUp(){
        driver.get(url);
        LoginUtil.loginByAdmin();
        index = AdminHomePage.getInstance().openUserManager();
        index.selectUser().byRealName(realName);
        reset = index.clickResetPasswordButton();
    }

    @Test
    public void rightTest(){
        reset.setPassword("1234567")
                .setRePassword("1234567")
                .clickConfirmButton();
        //�û�: test002�������óɹ�
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id=\"content-main\"]/iframe[2]")));
        WebElement elem = driver.findElement(By.xpath("/html/body/div[3]/div[2]"));

        assertThat(elem.getText()).contains("�û�: "+"test002"+"�������óɹ�");

        // ���µ�¼��֤
        driver.get(url);
        LoginPage login = new LoginPage();
        LoginUtil.login(login,"test002","1234567");
        driverWait.until(ExpectedConditions.titleIs("��ҵ����ƽ̨"));
        assertThat(login.getUserName()).isEqualTo("test002");
    }
}
