package helper.reader;

import helper.resource.ResourceHelper;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader implements ConfigReader {

    private static FileInputStream file;
    public static Properties OR;

    public PropertyReader() {
        try {
            String filePath = ResourceHelper.getResourcePath("src/main/resources/configfile/config.properties");
            file = new FileInputStream(new File(filePath));
            OR = new Properties();
            OR.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getImplicitWait() {
        return Integer.parseInt(OR.getProperty("implicitwait"));
    }

    public int getExplicitWait() {
        return Integer.parseInt(OR.getProperty("explicitwait"));
    }

    public int getPageLoadTime() {
        return Integer.parseInt(OR.getProperty("pageloadtime"));
    }

    public String getUserName() {
        if(System.getProperty("userName")!=null){
            return System.getProperty("userName");
        }
        return OR.getProperty("userName");
    }

    public String getPassword() {
        if(System.getProperty("password")!=null){
            return System.getProperty("password");
        }
        return OR.getProperty("password");
    }

    public String getMobileNumber() {
        if(System.getProperty("mobile")!=null){
            return System.getProperty("mobile");
        }
        return OR.getProperty("mobile");
    }

    @Override
    public String getGamerTag() {
        if(System.getProperty("gamertag")!=null){
            return System.getProperty("gamertag");
        }
        return OR.getProperty("gamertag");
    }

    @Override
    public String getDevice() {
        if(System.getProperty("device")!=null){
            return System.getProperty("device");
        }
        return OR.getProperty("device");
    }

    @Override
    public String getAppName() {
        if(System.getProperty("appName")!=null){
            return System.getProperty("appName");
        }
        return OR.getProperty("appName");
    }

    @Override
    public String getSDKName() {
        if(System.getProperty("sdkLocation")!=null){
            return System.getProperty("sdkLocation");
        }
        return OR.getProperty("sdkLocation");
    }

}
