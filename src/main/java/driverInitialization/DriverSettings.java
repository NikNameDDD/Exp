package driverInitialization;

import dataproviders.AppiumConfigDataProvider;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static driverInitialization.Constants.AUTOMATION_NAME_VALUE;
import static driverInitialization.Constants.DEVICE_NAME_IOS_VALUE;
import static driverInitialization.Constants.PLATFORM_NAME_ANDROID_VALUE;
import static driverInitialization.Constants.PLATFORM_NAME_IOS_VALUE;
import static driverInitialization.Constants.PLATFORM_VERSION_IOS_VALUE;
import static driverInitialization.Constants.UDID_IOS_VALUE;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.AUTOMATION_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.PLATFORM_VERSION;
import static io.appium.java_client.remote.MobileCapabilityType.UDID;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public class DriverSettings {

    private static DriverSettings instance;

    AppiumConfigDataProvider appiumConfigData = AppiumConfigDataProvider.getInstance();

    private DriverSettings() {
    }

    public static DriverSettings getInstance() {
        if (instance == null) {
            instance = new DriverSettings();
        }
        return instance;
    }

    public URL getRemoteUrl() {
        URL remoteUrl = null;
        try {
            remoteUrl = new URL(String.format(
                    "http://%s:%s/wd/hub", appiumConfigData.getAppiumURL(), appiumConfigData.getAppiumPort()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return remoteUrl;
    }

    public DesiredCapabilities getAndroidCapabilities(AppiumConfigDataProvider.Phone phone) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(DEVICE_NAME, appiumConfigData.getDeviceNameByPhone(phone));
        capabilities.setCapability(PLATFORM_NAME, PLATFORM_NAME_ANDROID_VALUE);
        capabilities.setCapability(PLATFORM_VERSION, appiumConfigData.getAndroidPlatformVersion());
        capabilities.setCapability(APP_PACKAGE, appiumConfigData.getAndroidAppPackage());
        capabilities.setCapability(APP_ACTIVITY, appiumConfigData.getAndroidAppActivity());
        capabilities.setCapability("avd", appiumConfigData.getAvdNameByPhone(phone));
        capabilities.setCapability("avdLaunchTimeout", appiumConfigData.getLaunchTimeoutByPhone(phone));
        capabilities.setCapability("avdReadyTimeout", appiumConfigData.getReadyTimeoutByPhone(phone));
        return capabilities;
    }

    public DesiredCapabilities getIOSCapabilities(AppiumConfigDataProvider.Phone phone) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(AUTOMATION_NAME, AUTOMATION_NAME_VALUE);
        capabilities.setCapability(DEVICE_NAME, appiumConfigData.getDeviceNameByPhone(phone));
        capabilities.setCapability(PLATFORM_NAME, PLATFORM_NAME_IOS_VALUE);
        capabilities.setCapability(PLATFORM_VERSION, PLATFORM_VERSION_IOS_VALUE);
        capabilities.setCapability(UDID, UDID_IOS_VALUE);
        capabilities.setCapability(DEVICE_NAME, DEVICE_NAME_IOS_VALUE);
        return capabilities;
    }
}