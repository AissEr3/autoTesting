package pages.admin.usermanage.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class SelectPage{
    protected WebDriver driver;

    protected WebElement confirmButton;
    protected WebElement cancelButton;

    public SelectPage(WebDriver fatherPageDriver){
        driver = fatherPageDriver;
        driver.switchTo().defaultContent();
    }

    public abstract SelectPage select(String ... selector);

    public void clickConfirmButton(){
        driver.switchTo().defaultContent();
        confirmButton = driver.findElements(By.xpath("//input[@value=\"确定\"]")).get(0);
        confirmButton.click();
    }

    public void clickCancelButton(){
        driver.switchTo().defaultContent();
        cancelButton = driver.findElement(By.xpath("//input[@value=\"取消\"]"));
        cancelButton.click();
    }
}
