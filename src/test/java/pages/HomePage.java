package pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage{
    public String homepageUrl = "https://automationexercise.com";
    public By login_signup_button = By.xpath("//a[normalize-space()='Signup / Login']");

    public void loadHomePage(){
        loadAWebPage(homepageUrl);
    }
}
