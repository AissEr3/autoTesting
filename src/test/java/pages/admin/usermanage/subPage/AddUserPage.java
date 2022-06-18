package pages.admin.usermanage.subPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class AddUserPage extends UserPage{
    private WebElement username;
    private WebElement password;
    private WebElement rePassword;

    public AddUserPage(WebDriver fatherPageDriver){
        super(fatherPageDriver);
        PageFactory.initElements(driver,this);
        initWebElement();
    }

    @Override
    protected void initWebElement(){
        username    = inputList.get(0);
        realName    = inputList.get(1);
        password    = inputList.get(2);
        rePassword  = inputList.get(3);
        cellPhone   = inputList.get(8);
        officePhone = inputList.get(9);
        email       = inputList.get(10);
    }

    public AddUserPage setUsername(String username){
        this.username.sendKeys(username);
        return this;
    }

    public AddUserPage setPassword(String password){
        this.password.sendKeys(password);
        return this;
    }

    public AddUserPage setRePassword(String rePassword){
        this.rePassword.sendKeys(rePassword);
        return this;
    }
}
