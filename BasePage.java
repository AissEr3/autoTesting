package pages.admin.usermanage.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    protected WebDriver driver;

    protected WebElement confirmButton;

    protected WebElement cancelButton;

    public void clickCancelButton(){
        driver.switchTo().defaultContent();
        cancelButton = driver.findElement(By.xpath("//input[@value=\"�ر�\"]"));
        cancelButton.click();
    }

    public void clickConfirmButton(){
        driver.switchTo().defaultContent();
        confirmButton = driver.findElement(By.xpath("//input[@value=\"ȷ��\"]"));
        confirmButton.click();
    }
}
