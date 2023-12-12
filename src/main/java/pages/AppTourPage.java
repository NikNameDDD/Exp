package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;

public class AppTourPage extends BasePage {

    @AndroidFindBy(id = "ru.andersen.afinny:id/error_title")
    private MobileElement appTourPageHeader;

    public AppTourPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Получаем текст заголовка страницы 'Тур по приложению'")
    public String getAppTourPageHeaderText() {
        return appTourPageHeader.getText();
    }
}