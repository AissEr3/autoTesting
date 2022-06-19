package test.usermanage;

import base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import pages.admin.AdminHomePage;
import pages.admin.usermanage.subpage.AddUserPage;
import util.LoginUtil;

import static org.assertj.core.api.Assertions.*;

public class AddUserPageTest extends BaseTest {
    private AddUserPage addUser;

    @BeforeEach
    public void setUpAdd(){
        driver.get(url);
        LoginUtil.loginByAdmin();
        addUser = AdminHomePage.getInstance().openUserManager().clickAddUserButton();
    }

    @Test
    public void correctInput(){
        String username = "test002";
        addUser.setUsername(username)
                .setPassword("123456")
                .setRePassword("123456")
                .setRealName("AissEr")
                .setCellPhone("13212341234")
                .setOfficePhone("13212341234")
                .setEmail("512345@qq.com");
        addUser.clickSelectOrgButton().select("test2","test","IT部").clickConfirmButton();
        addUser.switchFrame();
        addUser.clickCheckRoleButton().select("填报人员","系统管理员").clickConfirmButton();
        addUser.clickConfirmButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id=\"content-main\"]/iframe[2]")));
        WebElement elem = driver.findElement(By.xpath("/html/body/div[3]/div[2]"));

        assertThat(elem.getText()).contains("用户: "+username+"添加成功");
    }

}
