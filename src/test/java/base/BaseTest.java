package base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseTest {
    protected static String url = "http://127.0.0.1:8080/wygls/";
    protected static WebDriver driver;
    protected static WebDriverWait driverWait;

    @BeforeEach
    public void setUpAll(){
        // 指定driver可省略
        String path = "C:\\D_commonFiles\\java_project\\autoTesting\\chromedriver.exe";
        System.setProperty("webdrvier.chrome.driver",path);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driverWait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDownAll(){
        if(driver != null){
            driver.quit();
        }
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
