package practise.AppiumFramework;

import helper.assertion.AssertionHelper;
import helper.logger.LoggerHelper;
import helper.reader.ObjectReader;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LogInPage;

public class LoginPageTest extends Base {

    private final Logger logger = LoggerHelper.getLogger(LoginPageTest.class);
    private HomePage homePage;
    private LogInPage logInPage;

    @Test(description = "E2E test for login to Storm app")
    public void loginToStormTest() throws InterruptedException {

        homePage = new HomePage(driver);
        homePage.clickHamurgerMenu();
        homePage.clickLogin();

        logInPage = new LogInPage(driver);
        logInPage.enterPassword(ObjectReader.reader.getPassword());
        logInPage.enterUserName(ObjectReader.reader.getUserName());
        logInPage.clickStormIn();
        logInPage.verifySecureAccountPage();
        logInPage.clickCloseSecurePage();

        Thread.sleep(10000);
        homePage.clickHamurgerMenu();

        boolean status = logInPage.verifySignOutLink();
        AssertionHelper.updateTestStatus(status);

    }

    @Test(description = "E2E Test to login if user already logged in")
    public void logoutLoginToStormTest() throws InterruptedException {

        homePage = new HomePage(driver);
        homePage.clickHamurgerMenu();
        homePage.clickLogin();

        logInPage = new LogInPage(driver);
        logInPage.enterPassword(ObjectReader.reader.getPassword());
        logInPage.enterUserName(ObjectReader.reader.getUserName());
        logInPage.clickStormIn();
        logInPage.verifySecureAccountPage();
        logInPage.clickCloseSecurePage();

        Thread.sleep(20000);
        homePage.clickHamurgerMenu();
        logInPage.clickSignOut();

        Thread.sleep(10000);
        homePage.logIn.click();

        boolean status = logInPage.verifyWelcomeBackText();
        logInPage.enterPasswordInWelcomeScreen.sendKeys(ObjectReader.reader.getPassword());
        logInPage.clickStormIn();
        logInPage.verifySecureAccountPage();
        logInPage.clickCloseSecurePage();
        Thread.sleep(20000);
        homePage.clickHamurgerMenu();
        boolean result = logInPage.verifySignOutLink();
        boolean finalResult = status && result;
        AssertionHelper.updateTestStatus(finalResult);

    }

    @Test
    public void loginUsingMobileNoTest() throws InterruptedException {

        homePage = new HomePage(driver);
        homePage.clickHamurgerMenu();
        homePage.clickLogin();

        logInPage = new LogInPage(driver);
        logInPage.enterPassword(ObjectReader.reader.getPassword());
        logInPage.enterUserName(ObjectReader.reader.getMobileNumber());
        logInPage.clickStormIn();
        logInPage.verifySecureAccountPage();
        logInPage.clickCloseSecurePage();

        Thread.sleep(20000);
        homePage.clickHamurgerMenu();

        boolean status = logInPage.verifySignOutLink();
        AssertionHelper.updateTestStatus(status);

    }

    @Test(description = "Validate error if invalid username entered")
    public void invalidUserNameTest() {

        homePage = new HomePage(driver);
        homePage.clickHamurgerMenu();
        homePage.clickLogin();

        logInPage = new LogInPage(driver);
        logInPage.enterPassword(ObjectReader.reader.getPassword());
        logInPage.enterUserName("abc");
        logInPage.clickStormIn();

        boolean result = logInPage.verifyIncorrectCredentialsBanner();
        AssertionHelper.updateTestStatus(result);

    }

    @Test(description = "Validate error if invalid password entered")
    public void invalidPasswordTest() {

        homePage = new HomePage(driver);
        homePage.clickHamurgerMenu();
        homePage.clickLogin();

        logInPage = new LogInPage(driver);
        logInPage.enterPassword("abc");
        logInPage.enterUserName(ObjectReader.reader.getUserName());
        logInPage.clickStormIn();

        boolean result = logInPage.verifyIncorrectCredentialsBanner();
        AssertionHelper.updateTestStatus(result);

    }

    @Test(description = "Validate error if empty username entered")
    public void emptyUserNameTest() {

        homePage = new HomePage(driver);
        homePage.clickHamurgerMenu();
        homePage.clickLogin();

        logInPage = new LogInPage(driver);
        logInPage.enterPassword(ObjectReader.reader.getPassword());
        logInPage.clickStormIn();

        boolean result = logInPage.verifyEmptyCredentialsBanner();
        AssertionHelper.updateTestStatus(result);

    }

    @Test(description = "Validate error if empty password entered")
    public void emptyPasswordTest() {

        homePage = new HomePage(driver);
        homePage.clickHamurgerMenu();
        homePage.clickLogin();

        logInPage = new LogInPage(driver);
        logInPage.enterPassword("");
        logInPage.enterUserName(ObjectReader.reader.getUserName());
        logInPage.clickStormIn();

        boolean result = logInPage.verifyEmptyCredentialsBanner();
        AssertionHelper.updateTestStatus(result);

    }

    @Test(description = "Validate error if invalid mobile entered")
    public void invalidMobileNoTest() {

        homePage = new HomePage(driver);
        homePage.clickHamurgerMenu();
        homePage.clickLogin();

        logInPage = new LogInPage(driver);
        logInPage.enterPassword(ObjectReader.reader.getPassword());
        logInPage.enterUserName("99646577");
        logInPage.clickStormIn();

        boolean result = logInPage.verifyIncorrectCredentialsBanner();
        AssertionHelper.updateTestStatus(result);

    }

    @Test(description = "Validate forgot password page is displayed")
    public void forgotPasswordPageTest() {

        homePage = new HomePage(driver);
        homePage.clickHamurgerMenu();
        homePage.clickLogin();

        logInPage = new LogInPage(driver);
        logInPage.clickForgotPasswordLink();

        boolean result = logInPage.verifyResetPasswordPage();
        AssertionHelper.updateTestStatus(result);

    }
}
