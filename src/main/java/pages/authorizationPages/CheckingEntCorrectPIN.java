package pages.authorizationPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import pages.BasePage;

public class PIN extends BasePage {

    public CheckingEntCorrectPIN(AppiumDriver<MobileElement> driver) {
        super(driver);

        @AndroidFindBy(id = " ")

    }
}
