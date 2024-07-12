package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utilities.DataSet;
import utilities.DriverSetup;

public class TestLoginPage extends DriverSetup {

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    @BeforeMethod
    public void loadPage(){
        loginPage.navigateToLoginPage();
    }

    @Test
    public void test_login_with_valid_credentials(){
        loginPage.writeOnElement(loginPage.loginEmail, "shobuj@yopmail.com");
        loginPage.writeOnElement(loginPage.loginPassword, "shobuj123");
        loginPage.clickOnElement(loginPage.login_button);
        Assert.assertTrue(homePage.getDisplayStatus(homePage.logout_Button));
        Assert.assertFalse(loginPage.getDisplayStatus(loginPage.login_button));
    }

    @Test
    public void test_login_with_invalid_password(){
        loginPage.writeOnElement(loginPage.loginEmail, "shobuj@yopmail.com");
        loginPage.writeOnElement(loginPage.loginPassword, "shobuj12");
        loginPage.clickOnElement(loginPage.login_button);
        Assert.assertEquals(loginPage.getElementText(loginPage.errorMsg), "Your email or password is incorrect!");
        Assert.assertTrue(loginPage.getDisplayStatus(loginPage.login_button));
    }

    @Test
    public void test_login_with_Wrong_Email(){
        loginPage.writeOnElement(loginPage.loginEmail, "shobuj@yopmai.com");
        loginPage.writeOnElement(loginPage.loginPassword, "shobuj123");
        loginPage.clickOnElement(loginPage.login_button);
        Assert.assertEquals(loginPage.getElementText(loginPage.errorMsg), "Your email or password is incorrect!");
        Assert.assertTrue(loginPage.getDisplayStatus(loginPage.login_button));
    }

    @Test
    public void test_login_with_invalid_email(){
        loginPage.writeOnElement(loginPage.loginEmail, "shobuj");
        loginPage.writeOnElement(loginPage.loginPassword, "shobuj123");
        loginPage.clickOnElement(loginPage.login_button);
        Assert.assertEquals(loginPage.getAttributeText(loginPage.loginEmail,"validationMessage"),
                "Please enter an email address.");
        Assert.assertTrue(loginPage.getDisplayStatus(loginPage.login_button));
    }

    @Test
    public void test_login_without_email(){
        loginPage.writeOnElement(loginPage.loginPassword, "shobuj123");
        loginPage.clickOnElement(loginPage.login_button);
        Assert.assertEquals(loginPage.getAttributeText(loginPage.loginEmail,"validationMessage"),
                "Please fill out this field.");
        Assert.assertTrue(loginPage.getDisplayStatus(loginPage.login_button));
    }

    @Test
    public void test_login_without_password(){
        loginPage.writeOnElement(loginPage.loginEmail, "shobuj@yopmai.com");
        loginPage.clickOnElement(loginPage.login_button);
        Assert.assertEquals(loginPage.getAttributeText(loginPage.loginPassword,"validationMessage"),
                "Please fill out this field.");
        Assert.assertTrue(loginPage.getDisplayStatus(loginPage.login_button));
    }

    @Test
    public void test_login_without_credentials(){
        loginPage.clickOnElement(loginPage.login_button);
        Assert.assertEquals(loginPage.getAttributeText(loginPage.loginEmail,"validationMessage"),
                "Please fill out this field.");
        Assert.assertTrue(loginPage.getDisplayStatus(loginPage.login_button));
    }

    @Test
    public void test_login_with_invalid_email_password(){
        loginPage.writeOnElement(loginPage.loginEmail, "shobuj@adds.com");
        loginPage.writeOnElement(loginPage.loginPassword, "shobuj12");
        loginPage.clickOnElement(loginPage.login_button);
        Assert.assertEquals(loginPage.getElementText(loginPage.errorMsg), "Your email or password is incorrect!");
        Assert.assertTrue(loginPage.getDisplayStatus(loginPage.login_button));
    }

    @Test(dataProvider = "invalidCredentials", dataProviderClass = DataSet.class)
    public void test_login_with_invalid_credentials(String email, String password, String errorMsg, String emailValidationMessage, String passwordValidationMessage){
        loginPage.writeOnElement(loginPage.loginEmail, email);
        loginPage.writeOnElement(loginPage.loginPassword, password);
        loginPage.clickOnElement(loginPage.login_button);
        Assert.assertEquals(loginPage.getAttributeText(loginPage.loginEmail,"validationMessage"),
                emailValidationMessage);
        Assert.assertEquals(loginPage.getAttributeText(loginPage.loginPassword,"validationMessage"),
                passwordValidationMessage);
        Assert.assertEquals(loginPage.getErrorMessage(errorMsg), errorMsg);
        Assert.assertTrue(loginPage.getDisplayStatus(loginPage.login_button));
    }
}
