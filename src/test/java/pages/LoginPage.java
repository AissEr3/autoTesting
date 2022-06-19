package pages;

import base.BaseTest;
import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @CacheLookup
    @FindBy(name = "userName")
    private WebElement username;

    @CacheLookup
    @FindBy(name = "password")
    private WebElement password;

    @CacheLookup
    @FindBy(id = "but_login")
    private WebElement loginButton;

    public LoginPage() throws IllegalStateException{
        driver = BaseTest.getDriver();
        if(!driver.getTitle().equals("物业管理平台")){
            throw new IllegalStateException("this is not target page");
        }
        PageFactory.initElements(driver,this);
    }

    public LoginPage inputUsername(String username){
        this.username.sendKeys(username);
        return this;
    }

    public LoginPage inputPassword(String password){
        this.password.sendKeys(password);
        return this;
    }

    public LoginPage clickLoginButton(){
        loginButton.click();
        return this;
    }

    public String getUserName(){
        return driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[1]/div[1]/a/span/span[1]/strong")).getText();
    }

    public static LoginPage getInstance(){
        return new LoginPage();
    }
}
