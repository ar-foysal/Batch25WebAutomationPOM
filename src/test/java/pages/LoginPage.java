package pages;

public class LoginPage extends BasePage{


    HomePage homePage = new HomePage();

    public void navigateToLoginPage(){
        homePage.loadHomePage();
        homePage.clickOnElement(homePage.login_signup_button);

    }
}