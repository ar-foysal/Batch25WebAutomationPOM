package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class DriverSetup {

    private static String browserName = System.getProperty("browser", "Firefox");

    private static final ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return WEB_DRIVER_THREAD_LOCAL.get();
    }
    public static void setDriver(WebDriver driver) {
        DriverSetup.WEB_DRIVER_THREAD_LOCAL.set(driver);
    }

    public WebDriver getBrowser(String browserName){
        if (browserName.equalsIgnoreCase("Chrome"))
            return new ChromeDriver();
        else if (browserName.equalsIgnoreCase("Firefox"))
            return new FirefoxDriver();
        else if (browserName.equalsIgnoreCase("Edge"))
            return new EdgeDriver();
        else {
            throw  new RuntimeException("Browser is not currently available with the given name: " + browserName);
        }
    }

    @BeforeMethod
    public void openABrowser(){
        WebDriver driver = getBrowser(browserName);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        setDriver(driver);
    }

    @AfterMethod
    public void closeBrowser(){
        getDriver().quit();
    }

}
