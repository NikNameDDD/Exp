package dataproviders;

import java.util.Properties;

public class AppiumConfigDataProvider {

    private static AppiumConfigDataProvider instance;

    private static final String CONFIG_FILE_URL = "src/main/resources/appium_configs.properties";

    private Properties appiumConfig = PropertyReader.getInstance().loadProperties(CONFIG_FILE_URL);

    private AppiumConfigDataProvider() {
    }

    public static AppiumConfigDataProvider getInstance() {
        if (instance == null) {
            instance = new AppiumConfigDataProvider();
        }
        return instance;
    }

    public enum Phone {

        PIXEL_6("Pixel_6");

        Phone(String name) {
            this.name = name;
        }

        private String name;

        @Override
        public String toString() {
            return this.name;
        }
    }

    public String getDeviceNameByPhone(Phone phone) {
        return appiumConfig.getProperty(phone + ".ADB_DEVICE_NAME");
    }

    public String getAvdNameByPhone(Phone phone) {
        return appiumConfig.getProperty(phone + ".AVD_NAME");
    }

    public String getReadyTimeoutByPhone(Phone phone) {
        return appiumConfig.getProperty(phone + ".AVD_READY_TIMEOUT_MILLISECONDS");
    }

    public String getLaunchTimeoutByPhone(Phone phone) {
        return appiumConfig.getProperty(phone + ".AVD_LAUNCH_TIMEOUT_MILLISECONDS");
    }

    public String getAppiumPort() {
        return appiumConfig.getProperty("APPIUM_PORT");
    }

    public String getAppiumURL() {
        return appiumConfig.getProperty("APPIUM_URL");
    }

    public String getAppiumJSRelativePath() {
        return appiumConfig.getProperty("APPIUM_JS_PATH_RELATIVE_APPIUM_HOME");
    }

    public String getNodeJSRelativePath() {
        return appiumConfig.getProperty("NODE_JS_PATH_RELATIVE_NODEJS_HOME");
    }

    public String getAndroidAppPackage() {
        return appiumConfig.getProperty("ANDROID.APP_PACKAGE_VALUE");
    }

    public String getAndroidAppActivity() {
        return appiumConfig.getProperty("ANDROID.APP_ACTIVITY_VALUE");
    }

    public String getAndroidPlatformVersion() {
        return appiumConfig.getProperty("ANDROID.PLATFORM_VERSION");
    }
}