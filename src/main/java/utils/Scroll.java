package utils;

import driverInitialization.Driver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

import java.time.Duration;

public class Scroll {

    public static void scrollBy(AppiumDriver<MobileElement> driver, double coefficientStartX, double coefficientStartY,
                                double coefficientEndX, double coefficientEndY) {
        Dimension dimension = driver.manage().window().getSize();
        int startX = (int) (dimension.width * coefficientStartX);
        int startY = (int) (dimension.height * coefficientStartY);
        int endX = (int) (dimension.width * coefficientEndX);
        int endY = (int) (dimension.height * coefficientEndY);
        TouchAction touch = new TouchAction(driver);
        touch.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endX, endY)).release().perform();
    }
}