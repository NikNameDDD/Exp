package pages.registrationPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SetPasswordPage extends RegistrationCommonElements {

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_create_password_headline")
    private MobileElement setPasswordTitle;

    public SetPasswordPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Получаем текст тайтла 'Придумайте пароль'")
    public String getSetPasswordTitleText() {
        wait.until(ExpectedConditions.visibilityOf(setPasswordTitle));
        return setPasswordTitle.getText();
    }
}