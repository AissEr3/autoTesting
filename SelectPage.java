package pages.admin.usermanage.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class SelectPage extends BasePage{
    public SelectPage(WebDriver fatherPageDriver){
        driver = fatherPageDriver;
        driver.switchTo().defaultContent();
    }

    public abstract SelectPage select(String ... selector);

    public void clickCancelButton(){
        driver.switchTo().defaultContent();
        cancelButton = driver.findElement(By.xpath("//input[@value=\"取消\"]"));
        cancelButton.click();
    }

    public void clickConfirmButton(){
        driver.switchTo().defaultContent();
        confirmButton = driver.findElements(By.xpath("//input[@value=\"确定\"]")).get(0);
        confirmButton.click();
    }
}
