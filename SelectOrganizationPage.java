package pages.admin.usermanage.subpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.admin.usermanage.base.SelectPage;

import java.util.HashMap;
import java.util.List;

public class SelectOrganizationPage extends SelectPage {
    // 选项信息的id里包含span关键字
    @CacheLookup
    @FindBy(xpath = "//*[@id=\"departSelect\"]//span[contains(@id,\"span\")]")
    private List<WebElement> optionsInfo;

    // 包含treenode_check属性的是复选框
    @CacheLookup
    @FindBy(xpath = "//*[@id=\"departSelect\"]//span[@treenode_check]")
    private List<WebElement> selections;

    private HashMap<WebElement,WebElement> selectMap;


    public SelectOrganizationPage(WebDriver fatherPageDriver){
        super(fatherPageDriver);
        // 切换iframe，进行定位
        WebElement frame = driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/iframe"));
        driver.switchTo().frame(frame);
        // 显示所有可选项
        displayAll();
        // 找出需要的结果
        PageFactory.initElements(driver,this);
        // 将结果一一对应
        createSelectMap();
    }

    private void createSelectMap(){
        selectMap = new HashMap<>();
        for(int i = 0; i < optionsInfo.size(); i++){
            selectMap.put(optionsInfo.get(i),selections.get(i));
        }
    }

    private void displayAll(){
        for(WebElement elem : driver.findElements(By.xpath("//*[@id=\"departSelect\"]//span[contains(@id,\"switch\")]"))){
            elem.click();
        }
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public SelectOrganizationPage select(String ... targets){
        for(WebElement elem : optionsInfo){
            for(String s : targets){
                if(elem.getText().equals(s)){
                    selectMap.get(elem).click();
                }
            }
        }
        return this;
    }
}
