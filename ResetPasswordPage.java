package pages.admin.usermanage.subpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.admin.usermanage.base.BasePage;

public class ResetPasswordPage extends BasePage{
    @FindBy(xpath = "//*[@id=\"formobj\"]/table/tbody/tr[1]/td[2]/input")
    private WebElement password;

    @FindBy(xpath = "//*[@id=\"repassword\"]")
    private WebElement rePassword;

    public ResetPasswordPage(WebDriver fatherPageDriver){
        driver = fatherPageDriver;
        driver.switchTo().defaultContent();
        switchFrame();
        PageFactory.initElements(driver,this);
    }

    public void switchFrame(){
        WebElement frame = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/iframe"));
        driver.switchTo().frame(frame);
    }

    public ResetPasswordPage setPassword(String password){
        this.password.sendKeys(password);
        return this;
    }

    public ResetPasswordPage setRePassword(String rePassword){
        this.rePassword.sendKeys(rePassword);
        return this;
    }
}
