package driverInitialization;

import dataproviders.AppiumConfigDataProvider;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.extern.slf4j.Slf4j;
import utils.EnvironmentService;

import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static io.appium.java_client.remote.MobilePlatform.IOS;

@Slf4j
public class Driver {

    private AppiumDriver<MobileElement> driver;

    private static Driver instance;

    private EnvironmentService environmentService = EnvironmentService.getInstance();
    private DriverSettings settings = DriverSettings.getInstance();
    private static final String SYSTEM_PROPERTY_FOR_PHONE = "phone";


    private Driver() {
    }

    public static Driver getInstance() {
        if (instance == null)
            instance = new Driver();
        return instance;
    }

    public AppiumDriver<MobileElement> getDriver(String current) {
        if (!environmentService.getAppiumLocalService().isRunning()) {
            environmentService.getAppiumLocalService().start();
        }
        String receivedPhoneString = System.getProperty(SYSTEM_PROPERTY_FOR_PHONE);
        AppiumConfigDataProvider.Phone phone = null;
        if (driver == null) {
            switch (current) {
                case ANDROID:
                    if (receivedPhoneString == null) {
                        phone = AppiumConfigDataProvider.Phone.PIXEL_6;
                    } else {
                        phone = AppiumConfigDataProvider.Phone.valueOf(receivedPhoneString);
                    }
                    driver = new AndroidDriver<>(settings.getRemoteUrl(), settings.getAndroidCapabilities(phone));
                    break;
                case IOS:
                    driver = new IOSDriver<>(settings.getRemoteUrl(), settings.getIOSCapabilities(phone));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + current);
            }
        }
        log.info("driverInitialization.Driver initialized successfully");
        return driver;
    }

    public void deleteWebDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            log.info("driverInitialization.Driver deleted successfully");
        } else {
            log.info("driverInitialization.Driver is already deleted");
        }
    }
}