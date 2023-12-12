package utils;

import dataproviders.AppiumConfigDataProvider;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.io.IOException;

public class EnvironmentService {

    private static EnvironmentService instance;
    AppiumConfigDataProvider appiumConfigData = AppiumConfigDataProvider.getInstance();

    private EnvironmentService() {
    }

    public static EnvironmentService getInstance() {
        if (instance == null) {
            instance = new EnvironmentService();
        }
        return instance;
    }

    public AppiumDriverLocalService getAppiumLocalService() {
        return new AppiumServiceBuilder()
                .usingPort(Integer.parseInt(appiumConfigData.getAppiumPort()))
                .withIPAddress(appiumConfigData.getAppiumURL())
                .usingDriverExecutable(new File(System.getenv("NODEJS_HOME") + appiumConfigData.getNodeJSRelativePath()))
                .withAppiumJS(new File(System.getenv("APPIUM_HOME") + appiumConfigData.getAppiumJSRelativePath()))
                .build();
    }

    public void killAndroidEmulatorProcess(AppiumConfigDataProvider.Phone phone) {
        try {
            Runtime.getRuntime().exec(String.format("adb -s %s emu kill", appiumConfigData.getDeviceNameByPhone(phone)));
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}