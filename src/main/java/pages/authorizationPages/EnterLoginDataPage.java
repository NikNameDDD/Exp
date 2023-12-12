package pages.authorizationPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;
import pages.userAccountPages.FirstPage;

public class EnterLoginDataPage extends BasePage {

    @AndroidFindBy(xpath = "//*[@text='Авторизация']")
    private MobileElement authorizationPageHeader;

    @AndroidFindBy(id = "ru.andersen.afinny:id/authorization_title_text_view")
    private MobileElement enterDataTitle;

    @AndroidFindBy(id = "ru.andersen.afinny:id/select_login_type_text_view")
    private MobileElement selectLoginTypeDropdown;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.TextView")
    private MobileElement loginTypeByRUPhoneNumber;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[2]/android.widget.TextView")
    private MobileElement loginTypeByBYPhoneNumber;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[3]/android.widget.TextView")
    private MobileElement loginTypeByPassport;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[1]/android.widget.EditText")
    private MobileElement phoneNumberInputField;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[2]/android.widget.EditText")
    private MobileElement passwordInputField;

    @AndroidFindBy(id = "ru.andersen.afinny:id/forgot_password_text_view")
    private MobileElement forgotPasswordButton;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_edit_text_toggle_icon")
    private MobileElement passwordVisibleToggle;

    @AndroidFindBy(id = "ru.andersen.afinny:id/primary_button")
    private MobileElement loginButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[1]/android.widget.EditText")
    private MobileElement passportInputField;

    @AndroidFindBy(xpath = "//*[@text = 'Неверный пароль или логин']")
    private MobileElement invalidPasswordOrLoginMessage;

    @AndroidFindBy(xpath = "//*[@text = 'Недопустимые символы']")
    private MobileElement unacceptableSymbolsMessages;

    @AndroidFindBy(xpath = "//*[@text = 'Номер паспорта должен содержать не более 20 символов'")
    private MobileElement tooLongPassportMessages;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_mask_edit_text_label")
    private MobileElement phoneNumberLabelText;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_edit_text_label")
    private MobileElement passwordAndPassportLabelText;

    public EnterLoginDataPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Получаем текст заголовка страницы 'Авторизация'")
    public String getAuthorizationPageHeaderText() {
        wait.until(ExpectedConditions.visibilityOf(authorizationPageHeader));
        return authorizationPageHeader.getText();
    }

    @Step("Получаем текст элемента 'Введите данные для входа'")
    public String getEnterDataTitleText() {
        return enterDataTitle.getText();
    }

    @Step("Получаем текст поля дропдауна выбора типа авторизации")
    public String getSelectLoginTypeDropdownText() {
        wait.until(ExpectedConditions.visibilityOf(selectLoginTypeDropdown));
        return selectLoginTypeDropdown.getText();
    }

    @Step("Кликаем на дропдаун выбора типа авторизации")
    public void clickOnSelectLoginTypeDropdown() {
        selectLoginTypeDropdown.click();
    }

    @Step("Получаем текст пункта дропдауна 'Номер телефона: (+7) Россия'")
    public String getLoginTypeByRUPhoneNumberText() {
        wait.until(ExpectedConditions.visibilityOf(loginTypeByRUPhoneNumber));
        return loginTypeByRUPhoneNumber.getText();
    }

    @Step("Получаем текст пункта дропдауна 'Номер телефона: (+375) Беларусь'")
    public String getLoginTypeByBYPhoneNumberText() {
        wait.until(ExpectedConditions.visibilityOf(loginTypeByBYPhoneNumber));
        return loginTypeByBYPhoneNumber.getText();
    }

    @Step("Получаем текст пункта дропдауна 'Номер паспорта'")
    public String getLoginTypeByPassportText() {
        wait.until(ExpectedConditions.visibilityOf(loginTypeByPassport));
        return loginTypeByPassport.getText();
    }

    @Step("Получаем текст поля ввода номера телефона")
    public String getPhoneNumberInputText() {
        return phoneNumberInputField.getText();
    }

    @Step("Выбираем тип авторизации 'Номер телефона: (+7) Россия'")
    public EnterLoginDataPage chooseLoginTypeByRUPhoneNumber() {
        selectLoginTypeDropdown.click();
        loginTypeByRUPhoneNumber.click();
        return this;
    }

    @Step("Выбираем тип авторизации 'Номер паспорта'")
    public EnterLoginDataPage chooseLoginTypeByPassport() {
        selectLoginTypeDropdown.click();
        loginTypeByPassport.click();
        return this;
    }

    @Step("Выбираем тип авторизации 'Номер телефона: (+375) Беларусь'")
    public EnterLoginDataPage chooseLoginTypeByBYPhoneNumber() {
        selectLoginTypeDropdown.click();
        loginTypeByBYPhoneNumber.click();
        return this;
    }

    @Step("Выбираем тип авторизации 'Номер паспорта' в окне DropDown")
    public EnterLoginDataPage clickLoginTypeByPassport() {
        loginTypeByPassport.click();
        return this;
    }

    @Step("Отображение поля для ввода 'Номер паспорта'")
    public boolean isPassportNumberInputDisplayed() {
        return isElementDisplayed(passportInputField);
    }

    @Step("Отображение поля для ввода 'Номер телефона'")
    public boolean isPhoneNumberInputDisplayed() {
        return isElementDisplayed(phoneNumberInputField);
    }

    @Step("Отображение поля для ввода 'Пароль'")
    public boolean isPasswordInputDisplayed() {
        return isElementDisplayed(phoneNumberInputField);
    }

    @Step("Отображение элемента 'Просмотреть пароль'")
    public boolean isPasswordVisibleToggleDisplayed() {
        passwordInputField.click();
        return isElementDisplayed(passwordVisibleToggle);
    }

    @Step("Получаем текст кнопки 'Забыли пароль?'")
    public String getForgotPasswordButtonText() {
        wait.until(ExpectedConditions.visibilityOf(forgotPasswordButton));
        return forgotPasswordButton.getText();
    }

    @Step("Вводим номер телефона")
    public EnterLoginDataPage enterPhoneNumber(String value) {
        phoneNumberInputField.sendKeys(value);
        return this;
    }

    @Step("Вводим пароль")
    public EnterLoginDataPage enterPassword(String value) {
        passwordInputField.sendKeys(value);
        return this;
    }

    @Step("Вводим текст в поле паспорта")
    public EnterLoginDataPage enterPassport(String text) {
        waitForElementVisibilityInSeconds(passportInputField, 3).sendKeys(text);
        return this;
    }

    @Step("Получаем текст поля ввода пароля")
    public String getPasswordInputText() {
        wait.until(ExpectedConditions.visibilityOf(passwordInputField));
        return passwordInputField.getText();
    }

    @Step("Кликаем на кнопку 'Посмотреть пароль'")
    public EnterLoginDataPage toggleVisiblePasswordClick() {
        passwordVisibleToggle.click();
        return this;
    }

    @Step("Кликаем на кнопку 'Войти'")
    public FirstPage clickLoginButton() {
        loginButton.click();
        return new FirstPage(driver);
    }

    @Step("Кликаем на кнопку 'Войти'")
    public InstallPINPage clickLoginButtonFirstTimeAfterRegistration() {
        loginButton.click();
        return new InstallPINPage(driver);
    }

    @Step("Получаем текст из поля ввода паспорта")
    public String getTextFromPassportField() {
        return waitForElementVisibilityInSeconds(passportInputField, 3).getText();
    }

    @Step("Отображение сообщения о неверном логине или пароле")
    public boolean isInvalidPasswordOrLoginMessageDisplayed() {
        return isElementDisplayed(invalidPasswordOrLoginMessage);
    }

    @Step("Отображение сообщения 'Недопустимые символы'")
    public boolean isUnacceptableSymbolsMessageDisplayed() {
        return isElementDisplayed(unacceptableSymbolsMessages);
    }

    @Step("Отображение сообщения о превышении длины пароля")
    public boolean isTooLongPassportMessageDisplayed() {
        return isElementDisplayed(tooLongPassportMessages);
    }

    @Step("Активность кнопки 'Войти'")
    public boolean isLoginButtonActive() {
        return waitForElementVisibilityInSeconds(loginButton, 2).isEnabled();
    }

    @Step("Очищаем поле ввода телефона")
    public EnterLoginDataPage clearPhoneInputField() {
        waitForElementVisibilityInSeconds(phoneNumberInputField, 2).clear();
        return this;
    }

    @Step("Очищаем поле ввода паспорта")
    public EnterLoginDataPage clearPassportInputField() {
        waitForElementVisibilityInSeconds(passportInputField, 2).clear();
        return this;
    }

    @Step("Очищаем поле ввода пароля")
    public EnterLoginDataPage clearPasswordInputField() {
        waitForElementVisibilityInSeconds(passwordInputField, 2).clear();
        return this;
    }

    @Step("Отображение элемента 'Введите данные для входа'")
    public boolean isEnterDataTitleTextDisplayed() {
        return isElementDisplayed(enterDataTitle);
    }

    @Step("Кликаем на поле ввода 'Пароль'")
    public void clickPasswordInputField() {
        passwordInputField.click();
    }

    @Step("Кликаем на поле ввода 'Номер телефона'")
    public void clickPhoneNumberInputField() {
        phoneNumberInputField.click();
    }

    @Step("Кликаем на поле ввода 'Паспорт'")
    public void clickPassportInputField() {
        passportInputField.click();
    }

    @Step("Отображение лейбла над полем ввода 'Номер телефона'")
    public boolean isPhoneNumberLabelTextDisplayed() {
        return isElementDisplayed(phoneNumberLabelText);
    }

    @Step("Отображение лейбла над полем ввода 'Пароль'/'Паспорт'")
    public boolean isPasswordOrPassportLabelTextDisplayed() {
        return isElementDisplayed(passwordAndPassportLabelText);
    }

    @Step("Отображения курсора ввода текста в поле 'Номер телефона'")
    public boolean isPhoneNumberInputFieldCursorDisplayed() {
        return phoneNumberInputField.getAttribute("focused").equals("true");
    }

    @Step("Отображения курсора ввода текста в поле 'Номер паспорта'")
    public boolean isPassportInputFieldCursorDisplayed() {
        return passportInputField.getAttribute("focused").equals("true");
    }

    @Step("Отображения курсора ввода текста в поле 'Пароль'")
    public boolean isPasswordInputFieldCursorDisplayed() {
        return passwordInputField.getAttribute("focused").equals("true");
    }

    @Step("Нажатие на любое место")
    public void clickAnywhere() {
        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(500, 1000)).perform();
    }
}