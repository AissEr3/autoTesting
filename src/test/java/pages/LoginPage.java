package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy(name = "userName")
    @CacheLookup
    private WebElement username;

    @FindBy(name = "password")
    @CacheLookup
    private WebElement password;

    @FindBy(id = "but_login")
    @CacheLookup
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
}
