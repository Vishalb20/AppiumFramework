package pageObjects;

import helper.logger.LoggerHelper;
import helper.wait.WaitHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import practise.AppiumFramework.Base;

public class SignUpPage {

    private final Logger logger = LoggerHelper.getLogger(SignUpPage.class);
    private AndroidDriver driver;
    private WaitHelper waitHelper;

    public SignUpPage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        Base.logExtentReport("SignUpPage object created...");
        new Base().getNavigationScreen(driver);
    }

    @AndroidFindBy(xpath = "//android.view.View[@text='Join']")
    public WebElement join;

    @AndroidFindBy(xpath = "//android.widget.EditText[@index='2']")
    public WebElement enterMobileNumber;

    @AndroidFindBy(xpath = "//android.widget.EditText[@index='5']")
    public WebElement enterUsername;

    @AndroidFindBy(xpath = "//android.widget.EditText[@index='8']")
    public WebElement gamerTag;

    @AndroidFindBy(xpath = "//android.widget.EditText[@index='11']")
    public WebElement enterPassword;

    @AndroidFindBy(xpath = "//android.widget.EditText[@index='16']")
    public WebElement confirmPassword;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Already have an account?']")
    public WebElement accountAlreadyExists;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@index='4']")
    public WebElement details;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='STRONG']")
    public WebElement strongPassword;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='I AGREE']")
    public WebElement iAgree;

    @AndroidFindBy(xpath = "(//android.view.ViewGroup)[2]")
    public WebElement back;

    @AndroidFindBy(xpath = "(//android.view.ViewGroup)[4]")
    public WebElement cancelDetails;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Please fill in your Mobile Number']")
    public WebElement mobileNumber;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Username may not be empty']")
    public WebElement userName;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Please fill in your Gamertag']")
    public WebElement gamerTagPresent;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Password may not be empty']")
    public WebElement password;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Please confirm your Password']")
    public WebElement confirmPasswordPresent;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Password must be between 7-20 characters, contain at least one number (0-9), one uppercase character (A-Z), and one lowercase character (a-z).']")
    public WebElement invalidPassword;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Passwords need to be the same.']")
    public WebElement samePassword;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='MEDIUM']")
    public WebElement mediumPassword;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='WEAK']")
    public WebElement weakPassword;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='GamerTag is not available.']")
    public WebElement gamerTagNotPresent;

    @AndroidFindBy(xpath = "//android.view.View[@text='Authenticate']")
    public WebElement authenticate;


    public void setMobileNumber(String number) {
        logger.info("enter mobile number... "+number);
        Base.logExtentReport("enter mobile number... "+number);
        enterMobileNumber.sendKeys(number);
    }

    public void setUsername(String userName) {
        logger.info("enter username... "+userName);
        Base.logExtentReport("enter username... "+userName);
        enterUsername.sendKeys(userName);
    }

    public void setPassword(String password) {
        logger.info("enter password... "+password);
        Base.logExtentReport("enter password... "+password);
        enterPassword.sendKeys(password);
    }

    public void setConfirmPassword(String password) {
        logger.info("confirm password... "+password);
        Base.logExtentReport("confirm password... "+password);
        confirmPassword.sendKeys(password);
    }

    public void setGamerTag(String gamerTagValue) {
        logger.info("set gamerTag... "+gamerTagValue);
        Base.logExtentReport("set gamerTag... "+gamerTagValue);
        gamerTag.sendKeys(gamerTagValue);
    }

    public void clickIAgree() {
        waitHelper = new WaitHelper(driver);
        waitHelper.waitForElement(iAgree, helper.reader.ObjectReader.reader.getExplicitWait());
        iAgree.click();
    }

    public void clickAccountAlreadyExists() {
        waitHelper = new WaitHelper(driver);
        waitHelper.waitForElement(accountAlreadyExists, helper.reader.ObjectReader.reader.getExplicitWait());
        accountAlreadyExists.click();
    }

    public boolean verifyGamerTagIsPresent() {
        logger.info("verify gamertag not present displayed...");
        Base.logExtentReport("verify gamertag not present displayed...");
        if (gamerTagNotPresent.isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean verifySignUpPage() {
        try {
            logger.info("validating signup page...");
            Base.logExtentReport("validating signup page...");
            enterMobileNumber.isDisplayed();
            enterUsername.isDisplayed();
            enterPassword.isDisplayed();
            confirmPassword.isDisplayed();
            gamerTag.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyAuthenticatePage() {
        logger.info("verify authenticate text is displayed...");
        Base.logExtentReport("verify authenticate text is displayed...");
        if (authenticate.isDisplayed()) {
            return true;
        }
        return false;
    }
}
