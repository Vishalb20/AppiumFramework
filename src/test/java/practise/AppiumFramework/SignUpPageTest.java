package practise.AppiumFramework;

import helper.assertion.AssertionHelper;
import helper.logger.LoggerHelper;
import helper.reader.ObjectReader;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LogInPage;
import pageObjects.SignUpPage;

public class SignUpPageTest extends Base {

    private final Logger logger = LoggerHelper.getLogger(SignUpPageTest.class);
    private HomePage homePage;
    private LogInPage logInPage;
    private SignUpPage signUpPage;
    private Utilities utilities;

    @Test(description = "signup to storms app")
    public void signupInStormsTest() throws InterruptedException {

        homePage = new HomePage(driver);
        homePage.clickHamurgerMenu();
        homePage.clickLogin();

        logInPage = new LogInPage(driver);
        logInPage.clickSignup();

        utilities = new Utilities(driver);

        signUpPage = new SignUpPage(driver);
        signUpPage.setPassword(ObjectReader.reader.getPassword());
        signUpPage.setMobileNumber(ObjectReader.reader.getMobileNumber());
        signUpPage.setUsername(ObjectReader.reader.getUserName()+utilities.generateRandomNumber());
        signUpPage.setGamerTag(ObjectReader.reader.getGamerTag()+utilities.generateRandomNumber());
        signUpPage.setConfirmPassword(ObjectReader.reader.getPassword());
        utilities.scrollToText(driver, "I AGREE");
        signUpPage.clickIAgree();
        Thread.sleep(10000);
        boolean result = signUpPage.verifyAuthenticatePage();
        AssertionHelper.updateTestStatus(result);

    }

    @Test(description = "signup to storms app")
    public void validateErrorDuringSignupTest() throws InterruptedException{

        homePage = new HomePage(driver);
        Thread.sleep(10000);
        homePage.clickHamurgerMenu();
        homePage.clickLogin();

        logInPage = new LogInPage(driver);
        logInPage.clickSignup();

        signUpPage = new SignUpPage(driver);

        utilities = new Utilities(driver);
        utilities.scrollToText(driver, "I AGREE");
        signUpPage.clickIAgree();

        boolean result = signUpPage.gamerTagPresent.isDisplayed();
        AssertionHelper.updateTestStatus(result);

    }

    @Test(description = "validate fields in signup")
    public void validateSignUpPageTest() {

        homePage = new HomePage(driver);
        homePage.clickHamurgerMenu();
        homePage.clickLogin();

        logInPage = new LogInPage(driver);
        logInPage.clickSignup();

        signUpPage = new SignUpPage(driver);

        boolean status = signUpPage.verifySignUpPage();
        AssertionHelper.updateTestStatus(status);

    }
}
