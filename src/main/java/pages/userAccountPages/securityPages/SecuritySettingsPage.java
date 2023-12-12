package pages.userAccountPages.securityPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import pages.BasePage;

public class SecuritySettingsPage extends BasePage {

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_security_change_security_question_button")
    private MobileElement changeSecurityQuestionOption;

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_security_change_password_button")
    private MobileElement changePasswordOption;

    public SecuritySettingsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Нажимаем по кнопке 'Изменить контрольный вопрос'")
    public ChangeSecurityQuestionPage clickChangeSecurityQuestionOption() {
        waitForElementVisibilityInSeconds(changeSecurityQuestionOption, 3).click();
        return new ChangeSecurityQuestionPage(driver);
    }

    @Step("Нажимаем по кнопке 'Изменить пароль'")
    public ChangePasswordPage clickChangePasswordOption() {
        waitForElementVisibilityInSeconds(changePasswordOption, 3).click();
        return new ChangePasswordPage(driver);
    }
}