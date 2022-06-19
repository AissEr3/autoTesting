package pages.admin.usermanage.subpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.admin.usermanage.base.SelectPage;

import java.util.List;

public class SelectRolePage extends SelectPage {
    private static final String[] CHECK_NAME = {"填报人员","审核人员","系统管理员","管理人员"};
    @FindBy(xpath = "//input[@name=\"ck\"]")
    private List<WebElement> checker;

    @FindBy(xpath = "/html/body/div/div[2]/div[2]/div[1]/div[1]/div/table/tbody/tr/td[2]/div/input")
    private WebElement checkAll;

    public SelectRolePage(WebDriver fatherPageDriver){
        super(fatherPageDriver);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement frame = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/iframe"));
        driver.switchTo().frame(frame);
        PageFactory.initElements(driver,this);
    }

    public SelectRolePage selectAll(){
        checkAll.click();
        return this;
    }

    public SelectRolePage select(String ... selector){
        for(String select : selector){
            for(int i = 0; i < CHECK_NAME.length; i++){
                if(select.equals(CHECK_NAME[i])){
                    checker.get(i).click();
                    break;
                }
            }
        }
        return this;
    }

}
