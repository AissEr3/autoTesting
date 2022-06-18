package pages.admin.usermanage.subPage;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;

public class SelectOrganizationPage{
    private WebDriver driver;

    // ѡ����Ϣ��id�����span�ؼ���
    @CacheLookup
    @FindBy(xpath = "//*[@id=\"departSelect\"]//span[contains(@id,\"span\")]")
    private List<WebElement> optionsInfo;

    // ����treenode_check���Ե��Ǹ�ѡ��
    @CacheLookup
    @FindBy(xpath = "//*[@id=\"departSelect\"]//span[@treenode_check]")
    private List<WebElement> selections;

    private HashMap<WebElement,WebElement> selectMap;

    private WebElement confirmButton;
    private WebElement cancelButton;

    public SelectOrganizationPage(WebDriver fatherPageDriver){
        driver = fatherPageDriver;
        driver.switchTo().defaultContent();
        // �л�iframe�����ж�λ
        WebElement frame = driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/iframe"));
        driver.switchTo().frame(frame);
        // ��ʾ���п�ѡ��
        displayAll();
        // �ҳ���Ҫ�Ľ��
        PageFactory.initElements(driver,this);
        // �����һһ��Ӧ
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
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public SelectOrganizationPage selectOrganization(String ... targets){
        for(WebElement elem : optionsInfo){
            for(String s : targets){
                if(elem.getText().equals(s)){
                    selectMap.get(elem).click();
                }
            }
        }
        return this;
    }

    public void clickConfirmButton(){
        driver.switchTo().defaultContent();
        confirmButton = driver.findElements(By.xpath("//input[@value=\"ȷ��\"]")).get(0);
        confirmButton.click();
    }

    public void clickCancelButton(){
        driver.switchTo().defaultContent();
        cancelButton = driver.findElement(By.xpath("//input[@value=\"ȡ��\"]"));
        cancelButton.click();
    }
}