package pages.registrationPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class EnterSMSCodePage extends RegistrationCommonElements {

    @AndroidFindBy(id = "ru.andersen.afinny:id/registration_sms_code_title")
    private MobileElement enterSMSCodeTitle;

    @AndroidFindBy(id = "ru.andersen.afinny:id/registration_sms_code_sent_on_number_text_view")
    private MobileElement codeSentToNumber;

    @AndroidFindBy(id = "ru.andersen.afinny:id/registration_sms_code_phone_number")
    private MobileElement phoneNumberSMSCodeSentTo;

    @AndroidFindBy(id = "ru.andersen.afinny:id/registration_sms_code_pin_field")
    private MobileElement smsCodeInput;

    @AndroidFindBy(id = "ru.andersen.afinny:id/registration_sms_code_wrong_number_text_view")
    private MobileElement wrongPhoneNumber;

    @AndroidFindBy(id = "ru.andersen.afinny:id/registration_sms_code_change_phone_number_text")
    private MobileElement changePhoneNumberButton;

    @AndroidFindBy(id = "ru.andersen.afinny:id/registration_sms_code_timer")
    private MobileElement smsCodeTimer;

    @AndroidFindBy(id = "ru.andersen.afinny:id/registration_sms_code_send_sms_again_text")
    private MobileElement sendSMSAgainButton;

    @AndroidFindBy(id = "ru.andersen.afinny:id/registration_sms_code_error_text")
    private MobileElement incorrectSMSCodeErrorMessage;

    @AndroidFindBy(id = "android:id/message")
    private MobileElement tooManyAttemptsPopup;

    @AndroidFindBy(id = "android:id/button1")
    private MobileElement okPopupButton;

    @AndroidFindBy(id = "ru.andersen.afinny:id/registration_sms_code_request_sms_text_view")
    private MobileElement requestSMSCodeIn;

    public EnterSMSCodePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Получаем текст тайтла 'Введите код из SMS'")
    public String getEnterSMSCodeTitleText() {
        wait.until(ExpectedConditions.visibilityOf(enterSMSCodeTitle));
        return enterSMSCodeTitle.getText();
    }

    @Step("Получаем текст элемента 'Код отправлен на номер'")
    public String getCodeSentToNumberText() {
        wait.until(ExpectedConditions.visibilityOf(codeSentToNumber));
        return codeSentToNumber.getText();
    }

    @Step("Отображение номера телефона, на который отправлен смс-код")
    public String getPhoneNumberCodeSentTo() {
        wait.until(ExpectedConditions.visibilityOf(phoneNumberSMSCodeSentTo));
        return phoneNumberSMSCodeSentTo.getText();
    }

    @Step("Отображение поля ввода СМС-кода")
    public boolean isSMSCodeInputDisplayed() {
        return isElementDisplayed(smsCodeInput);
    }

    @Step("Получаем текст элемента 'Номер телефона указан неверно?'")
    public String getWrongPhoneNumberText() {
        wait.until(ExpectedConditions.visibilityOf(wrongPhoneNumber));
        return wrongPhoneNumber.getText();
    }

    @Step("Получаем текст кнопки 'Изменить номер телефона'")
    public String getChangePhoneNumberButtonText() {
        wait.until(ExpectedConditions.visibilityOf(changePhoneNumberButton));
        return changePhoneNumberButton.getText();
    }

    @Step("Получаем текст таймера")
    public String getSMSCodeTimerText() {
        wait.until(ExpectedConditions.visibilityOf(smsCodeTimer));
        return smsCodeTimer.getText();
    }

    @Step("Получаем текст кнопки 'Отправить SMS с кодом ещё раз'")
    public String getSendSMSAgainButtonText() {
        wait.until(ExpectedConditions.visibilityOf(sendSMSAgainButton));
        return sendSMSAgainButton.getText();
    }

    @Step("Вводим СМС-код")
    public void enterSMSCode(String code) {
        smsCodeInput.sendKeys(code);
        wait.until(ExpectedConditions.attributeToBeNotEmpty(smsCodeInput, "text"));
    }

    @Step("Получаем текст поля для ввода смс-кода")
    public String getSMSCodeInputText() {
        return smsCodeInput.getText();
    }

    @Step("Получаем текст сообщения об ошибке при вводе неверного смс-кода")
    public String getIncorrectSMSCodeErrorMessageText() {
        wait.until(ExpectedConditions.visibilityOf(incorrectSMSCodeErrorMessage));
        return incorrectSMSCodeErrorMessage.getText();
    }

    @Step("Отображение сообщения об ошибке при вводе неверного смс-кода")
    public boolean isIncorrectSMSCodeErrorMessageDisplayed() {
        return driver.findElements(By.id("ru.andersen.afinny:id/afinny_mask_edit_text_error")).size() != 0;
    }

    @Step("Очистить поле ввода смс-кода")
    public void clearSMSCodeInput() {
        smsCodeInput.clear();
    }

    @Step("Получаем текст попапа о слишком большом количестве неудачных попыток ввода смс-кода")
    public String getTooManyAttemptsPopupText() {
        wait.until(ExpectedConditions.visibilityOf(tooManyAttemptsPopup));
        return tooManyAttemptsPopup.getText();
    }

    @Step("Кликаем на кнопку ОК в попапе о слишком большом количестве неудачных попыток ввода смс-кода")
    public void clickOnOKPopupButton() {
        okPopupButton.click();
    }

    @Step("Отображение попапа о слишком большом количестве неудачных попыток ввода смс-кода")
    public boolean isTooManyAttemptsPopupDisplayed() {
        return driver.findElements(By.id("android:id/message")).size() != 0;
    }

    @Step("Получаем текст элемента 'Запросите SMS-код через'")
    public String getRequestSMSCodeInText() {
        wait.until(ExpectedConditions.visibilityOf(requestSMSCodeIn));
        return requestSMSCodeIn.getText();
    }

    @Step("Кликаем на кнопку 'Отправить SMS с кодом ещё раз'")
    public void clickOnSendSMSAgainButton() {
        sendSMSAgainButton.click();
    }

    @Step("Кликаем на кнопку 'Отправить SMS с кодом ещё раз' после истечения таймера")
    public void clickOnSendSMSAgainButtonWhenTimerExpires() {
        try {
            FluentWait<AppiumDriver> waitFor30Seconds = new FluentWait<AppiumDriver>(driver)
                    .withTimeout(Duration.ofSeconds(33))
                    .pollingEvery(Duration.ofSeconds(3));
            waitFor30Seconds.until(ExpectedConditions.stalenessOf(smsCodeTimer));
            sendSMSAgainButton.click();
        } catch (Exception e) {
            sendSMSAgainButton.click();
        }
    }
}