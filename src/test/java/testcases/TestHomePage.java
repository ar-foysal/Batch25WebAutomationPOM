package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utilities.DriverSetup;

public class TestHomePage extends DriverSetup {

    HomePage homePage = new HomePage();

    @Test
    public void testHomePageTitle(){
        homePage.loadAWebPage(homePage.homepageUrl);
        homePage.addScreenshot("Home page");
        Assert.assertEquals(getDriver().getTitle(),"Automation Exercise");

    }
}
