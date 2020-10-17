package pageObjects;

import helper.logger.LoggerHelper;
import helper.wait.WaitHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import practise.AppiumFramework.Base;

import java.util.List;

public class LogInPage {

    private final Logger logger = LoggerHelper.getLogger(LogInPage.class);
    private AndroidDriver driver;
    private WaitHelper waitHelper;

    public LogInPage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        Base.logExtentReport("LogInPage object created...");
        new Base().getNavigationScreen(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[@index='2']")
    public WebElement enterUserName;

    @AndroidFindBy(xpath = "//android.widget.EditText[@index='5']")
    public WebElement enterPassword;

    @AndroidFindBy(xpath = "//android.widget.EditText[@index='3']")
    public WebElement enterPasswordInWelcomeScreen;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Forgot your password?']")
    public WebElement forgotPassword;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='STORM IN']")
    public WebElement stormIn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SIGN OUT']")
    public WebElement signOut;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Hello,\n"+
            "Gamer']")
    public WebElement helloLoginPage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Interested in Storms? Join']")
    public WebElement signUp;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Incorrect username or password.']")
    public WebElement incorrectCredentials;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Username or Password is empty']")
    public WebElement emptyCredentials;

    @AndroidFindBy(xpath = "//android.view.View[@text='Secure Your Account']")
    public WebElement secureYourAccountScreen;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@index='0']")
    public WebElement closeSecurePage;

    @AndroidFindBy(xpath = "//android.view.View[@text='Welcome Back']")
    public WebElement welcomeBack;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Latest']")
    public WebElement latestLink;

    @AndroidFindBy(xpath = "//android.view.View[@text='Reset Password']")
    public WebElement resetPassword;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Log in failed']")
    public WebElement logInFailed;

    public void enterUserName(String userName) {
        waitHelper = new WaitHelper(driver);
        waitHelper.waitForElement(enterUserName, helper.reader.ObjectReader.reader.getExplicitWait());
        logger.info("entering username... "+userName);
        Base.logExtentReport("entering username... "+userName);
        enterUserName.clear();
        enterUserName.sendKeys(userName);
    }

    public void enterPassword(String password) {
        waitHelper = new WaitHelper(driver);
        waitHelper.waitForElement(enterPassword, helper.reader.ObjectReader.reader.getExplicitWait());
        logger.info("entering password... "+password);
        Base.logExtentReport("entering password... "+password);
        enterPassword.click();
        enterPassword.sendKeys(password);
    }

    public void clickStormIn() {
        waitHelper = new WaitHelper(driver);
        waitHelper.waitForElement(stormIn, helper.reader.ObjectReader.reader.getExplicitWait());
        logger.info("click on storm in... ");
        Base.logExtentReport("click on storm in... ");
        stormIn.click();
    }

    public void clickSignOut() {
        waitHelper = new WaitHelper(driver);
        waitHelper.waitForElement(signOut, helper.reader.ObjectReader.reader.getExplicitWait());
        logger.info("click on signout... ");
        Base.logExtentReport("click on signout... "+this.getClass());
        signOut.click();
    }

    public void clickForgotPasswordLink() {
        waitHelper = new WaitHelper(driver);
        waitHelper.waitForElement(forgotPassword, helper.reader.ObjectReader.reader.getExplicitWait());
        logger.info("click on forgot password link... ");
        Base.logExtentReport("click on forgot password link... ");
        forgotPassword.click();
    }

    public void clickSignup() {
        waitHelper = new WaitHelper(driver);
        waitHelper.waitForElement(signUp, helper.reader.ObjectReader.reader.getExplicitWait());
        logger.info("click on signup link...");
        Base.logExtentReport("click on signup link...");
        signUp.click();
    }

    public boolean verifyLoginPage() {
        logger.info("verify login page is displayed...");
        Base.logExtentReport("verify login page is displayed...");
        if (helloLoginPage.isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean verifyLogInFailed() {
        logger.info("verify login failed banner is displayed...");
        Base.logExtentReport("verify login failed banner is displayed...");
        if (logInFailed.isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean verifyResetPasswordPage() {
        logger.info("verify reset password page is displayed...");
        Base.logExtentReport("verify reset password page is displayed...");
        if (resetPassword.isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean verifySecureAccountPage() {
        waitHelper = new WaitHelper(driver);
        waitHelper.waitForElement(secureYourAccountScreen, helper.reader.ObjectReader.reader.getExplicitWait());
        logger.info("verify secure account page is displayed...");
        Base.logExtentReport("verify secure account page is displayed...");
        if (secureYourAccountScreen.isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean verifySignOutLink() {
        logger.info("verify signout link is displayed...");
        Base.logExtentReport("verify signout link is displayed...");
        if (signOut.isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean verifyIncorrectCredentialsBanner() {
        logger.info("verify incorrect credentials banner is displayed...");
        Base.logExtentReport("verify incorrect credentials banner is displayed...");
        if (incorrectCredentials.isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean verifyEmptyCredentialsBanner() {
        logger.info("verify empty credentials banner is displayed...");
        Base.logExtentReport("verify empty credentials banner is displayed...");
        if (emptyCredentials.isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean verifyWelcomeBackText() {
        logger.info("verify welcome back text is displayed...");
        Base.logExtentReport("verify welcome back text is displayed...");
        if (welcomeBack.isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean verifyUserAlreadyLoggedIn() {
        logger.info("verify user already logged in link is displayed...");
        Base.logExtentReport("verify user already logged in link is displayed...");
        if (latestLink.isDisplayed()) {
            return true;
        }
        return false;
    }

    public void clickCloseSecurePage() {
        logger.info("click on close secure account page...");
        Base.logExtentReport("click on close secure account page...");
        List<AndroidElement> elementList = driver.findElements(By.xpath("//android.view.ViewGroup[@index='0']"));
        for (WebElement element : elementList) {
            int y = element.getLocation().getY();
            System.out.println("y value is: "+y);
            if (y == 53) {
                element.click();
            }
        }
    }

}
