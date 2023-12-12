package pages.userAccountPages.notificationPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import pages.BasePage;

import java.util.Objects;

public class EmailEditPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'E-mail']")
    private MobileElement emailEditPageTitle;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_edit_text_input")
    private MobileElement emailInputField;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_edit_text_error")
    private MobileElement errorText;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_edit_text_label")
    private MobileElement emailLabelText;

    public EmailEditPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Отображение заголовка страницы 'Email'")
    public boolean isEmailEditPageTitleDisplayed() {
        return isElementDisplayed(emailEditPageTitle);
    }

    @Step("Вводим данные в поле 'Email'")
    public void enterEmail(String email) {
        emailInputField.sendKeys(email);
    }

    @Step("Получаем символы, введенные в поле 'Введите email'")
    public String getEmailInputFieldText() {
        return emailInputField.getText();
    }

    @Step("Отображение сообщения об ошибке")
    public boolean isErrorTextDisplayed() {
        return isElementDisplayed(errorText);
    }

    @Step("Нажимаем на поле ввода Email")
    public void clickEmailInputField() {
        emailInputField.click();
    }

    @Step("Отображение лейбла над полем Email")
    public boolean isEmailLabelTextDisplayed() {
        return isElementDisplayed(emailLabelText);
    }

    @Step("Отображения курсора ввода текста")
    public boolean isEmailInputFieldCursorDisplayed() {
        return Objects.equals(emailInputField
                .getAttribute("focused"), "true");
    }

    @Step("Нажатие на любое место")
    public void clickAnywhere(){
        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(500, 1000)).perform();
    }
}

