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


public class HomePage {

    private final Logger logger = LoggerHelper.getLogger(HomePage.class);
    private AndroidDriver driver;
    private WaitHelper waitHelper;

    public HomePage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        Base.logExtentReport("HomePage object created...");
        new Base().getNavigationScreen(driver);
//        waitHelper = new WaitHelper(driver);
//        waitHelper.waitForElement(news, helper.reader.ObjectReader.reader.getImpliciteWait());
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@index='2']")
    public WebElement search;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='News']")
    public WebElement news;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Games']")
    public WebElement games;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Account']")
    public WebElement account;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='LOG IN']")
    public WebElement logIn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Filters']")
    public WebElement filters;


    public void clickHamurgerMenu() {
        waitHelper = new WaitHelper(driver);
        waitHelper.waitForElement(news, helper.reader.ObjectReader.reader.getExplicitWait());
        logger.info("clicking on Hamburger menu...");
        Base.logExtentReport("clicking on Hamburger menu...");
        List<AndroidElement> elementList = driver.findElements(By.xpath("//android.view.ViewGroup[@index='0']"));
        for (WebElement element : elementList) {
            int x = element.getLocation().getX();
            if (x == 15) {
                element.click();
            }
        }
    }

    public boolean verifyHomePage() {
        try {
            logger.info("validating home page...");
            Base.logExtentReport("validating home page...");
            news.isDisplayed();
            games.isDisplayed();
            account.isDisplayed();
            search.isDisplayed();
            filters.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogin() {
        logger.info("click on login...");
        Base.logExtentReport("click on login in Home page...");
        waitHelper = new WaitHelper(driver);
        waitHelper.waitForElement(logIn, helper.reader.ObjectReader.reader.getExplicitWait());
        this.logIn.click();
    }

}
