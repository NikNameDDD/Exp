package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;

public class PrivacyPolicyPage extends BasePage {

    @AndroidFindBy(id = "ru.andersen.afinny:id/registration_privacy_policy_title")
    private MobileElement privacyPolicyTitle;

    public PrivacyPolicyPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Получаем заголовок раздела")
    public String getPrivacyPolicyTitleText() {
        return privacyPolicyTitle.getText();
    }
}