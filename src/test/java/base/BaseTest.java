package base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseTest {
    protected static String url = "http://127.0.0.1:8080/wygls/";
    protected static WebDriver driver;
    protected static WebDriverWait driverWait;

    @BeforeAll
    public static void setUpAll(){
        // 指定driver可省略
        String path = "C:\\D_commonFiles\\java_project\\autoTesting\\chromedriver.exe";
        System.setProperty("webdrvier.chrome.driver",path);

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driverWait = new WebDriverWait(driver,Duration.ofSeconds(15));
    }

    @AfterAll
    public static void tearDownAll(){
        if(driver != null){
            driver.quit();
        }
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
