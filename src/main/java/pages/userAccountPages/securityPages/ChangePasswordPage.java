package pages.userAccountPages.securityPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import pages.BasePage;

public class ChangePasswordPage extends BasePage {

    @AndroidFindBy(xpath = "//*[@resource-id='ru.andersen.afinny:id/old_password_input']//*[@resource-id='ru.andersen.afinny:id/afinny_edit_text_input']")
    private MobileElement oldPasswordInputField;

    @AndroidFindBy(xpath = "//*[@resource-id='ru.andersen.afinny:id/old_password_input']//*[@resource-id='ru.andersen.afinny:id/afinny_edit_text_toggle_icon']")
    private MobileElement oldPasswordToggle;

    @AndroidFindBy(xpath = "//*[@resource-id='ru.andersen.afinny:id/new_password_input']//*[@resource-id='ru.andersen.afinny:id/afinny_edit_text_input']")
    private MobileElement newPasswordInputField;

    @AndroidFindBy(xpath = "//*[@resource-id='ru.andersen.afinny:id/new_password_input']//*[@resource-id='ru.andersen.afinny:id/afinny_edit_text_toggle_icon']")
    private MobileElement newPasswordToggle;

    @AndroidFindBy(xpath = "//*[@resource-id='ru.andersen.afinny:id/new_password_input']//*[@resource-id='ru.andersen.afinny:id/afinny_edit_text_label']")
    private MobileElement enterNewPasswordLabel;

    @AndroidFindBy(xpath = "//*[@resource-id='ru.andersen.afinny:id/new_password_confirm']//*[@resource-id='ru.andersen.afinny:id/afinny_edit_text_toggle_icon']")
    private MobileElement confirmPasswordToggle;

    @AndroidFindBy(xpath = "//*[@resource-id='ru.andersen.afinny:id/new_password_confirm']//*[@resource-id='ru.andersen.afinny:id/afinny_edit_text_input']")
    private MobileElement confirmPasswordInputField;

    @AndroidFindBy(xpath = "//*[@resource-id='ru.andersen.afinny:id/new_password_confirm']//*[@resource-id='ru.andersen.afinny:id/afinny_edit_text_label']")
    private MobileElement confirmPasswordLabel;

    @AndroidFindBy(id = "ru.andersen.afinny:id/primary_button")
    private MobileElement changePasswordButton;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_edit_text_error")
    private MobileElement errorMessage;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_create_password_edit_text_text_condition_length")
    private MobileElement passwordLengthChecker;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_create_password_edit_text_text_condition_uppercase_letters")
    private MobileElement upperCaseChecker;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_create_password_edit_text_text_condition_lowercase_letters")
    private MobileElement lowerCaseChecker;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_create_password_edit_text_text_condition_digits")
    private MobileElement digitsChecker;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_create_password_edit_text_text_condition_special_symbols")
    private MobileElement specialSymbolsChecker;

    @AndroidFindBy(xpath = "//*[@resource-id='ru.andersen.afinny:id/old_password_input']//*[@resource-id='ru.andersen.afinny:id/afinny_edit_text_error']")
    private MobileElement oldPasswordErrorMessage;

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_create_password_headline")
    private MobileElement pageHeadline;

    public ChangePasswordPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Получаем текст из поля ввода старого пароля")
    public String getTextFromOldPasswordInputField() {
        return waitForElementVisibilityInSeconds(oldPasswordInputField, 2).getText();
    }

    @Step("Получаем текст из поля ввода нового пароля")
    public String getTextFromNewPasswordInputField() {
        return waitForElementVisibilityInSeconds(newPasswordInputField, 2).getText();
    }

    @Step("Получаем текст из поля ввода подтверждения пароля")
    public String getTextFromConfirmPasswordInputField() {
        return waitForElementVisibilityInSeconds(confirmPasswordInputField, 2).getText();
    }

    @Step("Вводим текст в поле старого пароля")
    public ChangePasswordPage inputOldPassword(String text) {
        waitForElementVisibilityInSeconds(oldPasswordInputField, 2).click();
        oldPasswordInputField.sendKeys(text);
        return this;
    }

    @Step("Вводим текст в поле нового пароля")
    public ChangePasswordPage inputNewPassword(String text) {
        waitForElementVisibilityInSeconds(newPasswordInputField, 2).click();
        newPasswordInputField.sendKeys(text);
        return this;
    }

    @Step("Вводим текст в поле подтверждения пароля")
    public ChangePasswordPage inputConfirmPassword(String text) {
        waitForElementVisibilityInSeconds(confirmPasswordInputField, 2).click();
        confirmPasswordInputField.sendKeys(text);
        return this;
    }

    @Step("Получаем текст из лейбла 'Введите новый пароль'")
    public String getEnterNewPasswordLabelText() {
        return waitForElementVisibilityInSeconds(enterNewPasswordLabel, 2).getText();
    }

    @Step("Получаем текст из лейбла 'Подтвердите пароль'")
    public String getConfirmPasswordLabelText() {
        return waitForElementVisibilityInSeconds(confirmPasswordLabel, 2).getText();
    }

    @Step("Активность ли кнопка 'Изменить'")
    public boolean isChangePasswordButtonActive() {
        return waitForElementVisibilityInSeconds(changePasswordButton, 1).isEnabled();
    }

    @Step("Кликаем по кнопке 'Изменить'")
    public ChangePasswordPage clickChangePasswordButton() {
        waitForElementBeingClickableInSeconds(changePasswordButton, 1).click();
        return this;
    }

    @Step("Получаем текст сообщения об ошибке")
    public String getErrorMessageText() {
        return waitForElementVisibilityInSeconds(errorMessage, 2).getText();
    }

    @Step("Получаем текст проверки нового пароля на длину")
    public String getTextFromPasswordLengthChecker() {
        return waitForElementVisibilityInSeconds(passwordLengthChecker, 2).getText();
    }

    @Step("Получаем текст проверки нового пароля на наличие верхнего регистра")
    public String getTextFromPasswordUpperCaseChecker() {
        return waitForElementVisibilityInSeconds(upperCaseChecker, 2).getText();
    }

    @Step("Получаем текст проверки нового пароля на наличие нижнего регистра")
    public String getTextFromPasswordLowerCaseChecker() {
        return waitForElementVisibilityInSeconds(lowerCaseChecker, 2).getText();
    }

    @Step("Получаем текст проверки нового пароля на наличие цифр")
    public String getTextFromPasswordDigitsChecker() {
        return waitForElementVisibilityInSeconds(digitsChecker, 2).getText();
    }

    @Step("Получаем текст проверки нового пароля на наличие специальных символов")
    public String getTextFromPasswordSpecialSymbolsChecker() {
        return waitForElementVisibilityInSeconds(specialSymbolsChecker, 2).getText();
    }

    @Step("Получаем текст сообщения о неверном старом пароле")
    public String getTextFromOldPasswordErrorMessage() {
        return waitForElementVisibilityInSeconds(oldPasswordErrorMessage, 2).getText();
    }

    @Step("Кликаем по тайтлу страницы")
    public ChangePasswordPage clickPageHeadline() {
        waitForElementVisibilityInSeconds(pageHeadline, 2).click();
        return this;
    }

    @Step("Видимость кнопки 'Посмотреть пароль в поле ввода нового пароля'")
    public boolean isPasswordToggleInNewPasswordFieldVisible() {
        return waitForElementVisibilityInSeconds(newPasswordToggle, 2).isDisplayed();
    }

    @Step("Видимость кнопки 'Посмотреть пароль в поле ввода подтверждения пароля'")
    public boolean isPasswordToggleInConfirmPasswordFieldVisible() {
        return waitForElementVisibilityInSeconds(confirmPasswordToggle, 2).isDisplayed();
    }

    @Step("Очищаем поле старого пароля")
    public ChangePasswordPage clearOldPasswordField() {
        waitForElementVisibilityInSeconds(oldPasswordInputField, 2).clear();
        return this;
    }

    @Step("Очищаем поле нового пароля")
    public ChangePasswordPage clearNewPasswordField() {
        waitForElementVisibilityInSeconds(newPasswordInputField, 2).clear();
        return this;
    }

    @Step("Очищаем поле подтверждения пароля")
    public ChangePasswordPage clearConfirmPasswordField() {
        waitForElementVisibilityInSeconds(confirmPasswordInputField, 2).clear();
        return this;
    }
}