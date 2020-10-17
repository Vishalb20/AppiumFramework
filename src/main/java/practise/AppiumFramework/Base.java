package practise.AppiumFramework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import helper.logger.LoggerHelper;
import helper.reader.ObjectReader;
import helper.reader.PropertyReader;
import helper.resource.ResourceHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import utils.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Base {

    public static AppiumDriverLocalService service;
    public static AndroidDriver<AndroidElement> driver;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static File reportDirectery;
    private Logger log = LoggerHelper.getLogger(Base.class);

    @BeforeSuite
    public void beforeSuite() {
        extent = ExtentManager.getInstance();
    }

    @BeforeTest
    public void beforeTest() {
        ObjectReader.reader = new PropertyReader();
        reportDirectery = new File(ResourceHelper.getResourcePath("src/main/resources/screenShots"));
        test = extent.createTest(getClass().getSimpleName());
    }

    @BeforeMethod
    public void beforeMethod(Method method) throws Exception{
        ObjectReader.reader = new PropertyReader();
        service = startServer();
        AndroidDriver<AndroidElement> driver = capabilities(ObjectReader.reader.getAppName());
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        test = extent.createTest(method.getName());
        Thread.sleep(40000);
        test.log(Status.INFO, method.getName()+"**************test started***************");
        log.info("**************"+method.getName()+"Started***************");
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException{
        if(result.getStatus() == ITestResult.FAILURE){
            test.log(Status.FAIL, result.getThrowable());
            String imagePath = captureScreen(result.getName(),driver);
            test.addScreenCaptureFromPath(imagePath);
        }
        else if(result.getStatus() == ITestResult.SUCCESS){
            test.log(Status.PASS, result.getName()+" is pass");
        }
        else if(result.getStatus() == ITestResult.SKIP){
            test.log(Status.SKIP, result.getThrowable());
        }
        log.info("**************"+result.getName()+"Finished***************");
        extent.flush();
        if(driver!=null){
            driver.quit();
        }
        if(service!=null){
            service.stop();
        }
    }

    @AfterTest
    public void afterTest(){
    }

    public AppiumDriverLocalService startServer() {
        boolean flag = checkIfServerIsRunnning(4723);
        if (!flag) {
            service = AppiumDriverLocalService.buildDefaultService();
            service.start();
        }
        return service;
    }

    public static boolean checkIfServerIsRunnning(int port) {
        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

    public static void startEmulator() throws IOException, InterruptedException {
        String deviceName = ObjectReader.reader.getDevice();
        String sdkLocation = ObjectReader.reader.getSDKName();
        Runtime.getRuntime().exec(sdkLocation+"emulator -avd "+ deviceName);
        Thread.sleep(20000);
    }

    public static AndroidDriver<AndroidElement> capabilities(String appName) throws IOException, InterruptedException {
        File appDir = new File("src");
        File app = new File(appDir, ObjectReader.reader.getAppName());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String device = ObjectReader.reader.getDevice();
        if (device.contains("emulator")) {
            startEmulator();
        }
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 50);
        capabilities.setCapability("appPackage", "com.storms.store");
        capabilities.setCapability("appActivity", "com.stormsclient.MainActivity");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability("appWaitForLaunch", "false");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        return driver;
    }

    public static void getScreenshot(String s) throws IOException {
        File scrfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrfile, new File(System.getProperty("user.dir")+"\\"+s+".png"));
    }

    public static void logExtentReport(String s1){
        test.log(Status.INFO, s1);
    }

    public String captureScreen(String fileName, WebDriver driver){
        if(driver == null){
            log.info("driver is null..");
            return null;
        }
        if(fileName==""){
            fileName = "blank";
        }
        Reporter.log("captureScreen method called");
        File destFile = null;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        File screFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try{
            destFile = new File(reportDirectery+"/"+fileName+"_"+formater.format(calendar.getTime())+".png");
            Files.copy(screFile.toPath(), destFile.toPath());
            Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"'height='100' width='100'/></a>");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return destFile.toString();
    }

    public void getNavigationScreen(WebDriver driver) {
        log.info("capturing ui navigation screen...");
        String screen = captureScreen("", driver);
        try {
            test.addScreenCaptureFromPath(screen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}