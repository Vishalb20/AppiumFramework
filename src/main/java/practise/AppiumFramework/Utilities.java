package practise.AppiumFramework;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.concurrent.ThreadLocalRandom;

public class Utilities {
    AndroidDriver<AndroidElement> driver;

    public Utilities(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
    }

    public static void scrollToText(AndroidDriver<AndroidElement> driver, String text) {
        MobileElement el = (MobileElement) driver.findElementByAndroidUIAutomator("new UiScrollable("
                +"new UiSelector().scrollable(true)).scrollIntoView("+"new UiSelector().text(\""+text+"\"));");
    }

    public int generateRandomNumber() {
        int randomNumber = ThreadLocalRandom.current().nextInt();
        return randomNumber;
    }
}
