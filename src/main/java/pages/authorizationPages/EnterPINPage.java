package pages.authorizationPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import pages.BasePage;

public class EnterPINPage extends BasePage {

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_enter_by_pin_title")
    private MobileElement fragmentEnterByPINTitle;

    public EnterPINPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Отображение элемента 'Введите PIN'")
    public boolean displayedFragmentEnterByPINTitle() {
        return isElementDisplayed(fragmentEnterByPINTitle);
    }

    @Step("Отображение элемента 'Введите PIN'")
    public String getTextFragmentEnterByPINTitle() {
        return fragmentEnterByPINTitle.getText();
    }
}