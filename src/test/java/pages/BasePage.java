package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utilities.DriverSetup.getDriver;

public class BasePage {

    public WebElement getElement(By locator){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void clickOnElement(By locator){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void loadAWebPage(String url){
        getDriver().get(url);
    }

    public void writeOnElement(By locator, String text){
        getElement(locator).clear();
        getElement(locator).sendKeys(text);
    }

    public Boolean getDisplayStatus(By locator){
        try {
            return getDriver().findElement(locator).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public String getAttributeText(By locator, String attributeName){
        return getElement(locator).getAttribute(attributeName);
    }

    public String getElementText(By locator){
        return getElement(locator).getText();
    }


}
