package practise.AppiumFramework;

import helper.assertion.AssertionHelper;
import helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LogInPage;

public class HomePageTest extends Base {

    private final Logger logger = LoggerHelper.getLogger(HomePageTest.class);
    private HomePage homePage;
    private LogInPage logInPage;


    @Test(description = "validate elements present in Home Page")
    public void validateHomePageTest() {

        homePage = new HomePage(driver);
        boolean status = homePage.verifyHomePage();
        AssertionHelper.updateTestStatus(status);

    }

    @Test(description = "validate Login Page")
    public void navigateToLoginPageTest() {

        homePage = new HomePage(driver);
        homePage.clickHamurgerMenu();
        homePage.clickLogin();

        logInPage = new LogInPage(driver);

        boolean status = logInPage.verifyLoginPage();
        AssertionHelper.updateTestStatus(status);

    }
}
