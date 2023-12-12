package pages.registrationPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static java.lang.String.valueOf;

public class EnterPhoneForRegistrationPage extends RegistrationCommonElements {

    @AndroidFindBy(id = "ru.andersen.afinny:id/enter_phone_number_title_text_view")
    private MobileElement enterPhoneNumberTitle;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_mask_edit_text_input")
    private MobileElement phoneNumberInput;

    @AndroidFindBy(id = "ru.andersen.afinny:id/privacy_policy_notice_text_view")
    private MobileElement privacyPolicyNotice;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_mask_edit_text_error")
    private MobileElement existingClientErrorMessage;

    @AndroidFindBy(id = "ru.andersen.afinny:id/select_code_text_view")
    private MobileElement selectCountryCodeDropdown;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.TextView")
    private MobileElement russianNumberDropdownItem;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[2]/android.widget.TextView")
    private MobileElement belarusianNumberDropdownItem;

    public EnterPhoneForRegistrationPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Получаем текст элемента 'Введите номер телефона'")
    public String getEnterPhoneNumberTitleText() {
        return enterPhoneNumberTitle.getText();
    }

    @Step("Отображение элемента 'Введите номер телефона'")
    public boolean isEnterPhoneNumberTitleDisplayed() {
        return isElementDisplayed(enterPhoneNumberTitle);
    }

    @Step("Получаем текст дропдауна выбора кода страны")
    public String getSelectCountryCodeDropdownText() {
        wait.until(ExpectedConditions.visibilityOf(selectCountryCodeDropdown));
        return selectCountryCodeDropdown.getText();
    }

    @Step("Получаем текст пункта дропдауна '(+7) Россия'")
    public String getRussianNumberDropdownItemText() {
        wait.until(ExpectedConditions.visibilityOf(russianNumberDropdownItem));
        return russianNumberDropdownItem.getText();
    }

    @Step("Получаем текст пункта дропдауна '(+375) Беларусь'")
    public String getBelarusianNumberDropdownItemText() {
        wait.until(ExpectedConditions.visibilityOf(belarusianNumberDropdownItem));
        return belarusianNumberDropdownItem.getText();
    }

    @Step("Кликаем на пункт дропдауна '(+7) Россия'")
    public void clickOnRussianNumberDropdownItem() {
        russianNumberDropdownItem.click();
    }

    @Step("Кликаем на пункт дропдауна '(+375) Беларусь'")
    public void clickOnBelarusianNumberDropdownItem() {
        belarusianNumberDropdownItem.click();
    }

    @Step("Выбираем страну и код номера телефона")
    public void switchCountryCodeTo(Enum country) {
        clickOnSelectCountryCodeDropdown();
        switch (valueOf(country)) {
            case "RU":
                clickOnRussianNumberDropdownItem();
                break;
            case "BY":
                clickOnBelarusianNumberDropdownItem();
                break;
        }
    }

    @Step("Выбираем страну и код номера телефона")
    public void switchCountryCodeTo(String phone) {
        clickOnSelectCountryCodeDropdown();
        if (phone.length() == 10) {
            clickOnRussianNumberDropdownItem();
        } else {
            clickOnBelarusianNumberDropdownItem();
        }
    }

    @Step("Отображение поля для ввода номера телефона")
    public boolean isPhoneNumberInputDisplayed() {
        return isElementDisplayed(phoneNumberInput);
    }

    @Step("Получаем значение поля ввода номера телефона")
    public String getPhoneNumberInputText() {
        return phoneNumberInput.getText();
    }

    @Step("Получаем текст элемента 'Политика конфиденциальности'")
    public String getPrivacyPolicyNoticeText() {
        return privacyPolicyNotice.getText();
    }

    @Step("Отображение уведомления 'Политика конфиденциальности'")
    public boolean isPrivacyPolicyNoticeDisplayed() {
        return isElementDisplayed(privacyPolicyNotice);
    }

    @Step("Кликаем по полю дропдауна выбора кода страны")
    public void clickOnSelectCountryCodeDropdown() {
        selectCountryCodeDropdown.click();
    }

    @Step("Стираем номер номер телефона")
    public void deleteMobileNumber() {
        phoneNumberInput.sendKeys(Keys.DELETE);
    }

    @Step("Нажимаем на кнопку 'Политика конфиденциальности'")
    public void clickOnPrivacyPolicyNotice() {
        privacyPolicyNotice.click();
    }

    @Step("Заполняем номер телефона")
    public void enterPhoneNumber(String number) {
        phoneNumberInput.sendKeys(number);
    }

    @Step("Кликабельность уведомления о конфиденциальности")
    public String isPrivacyPolicyNoticeClickable() {
        return privacyPolicyNotice.getAttribute("clickable");
    }

    @Step("Отображение сообщения об ошибке при вводе номера уже зарегистрированного клиента")
    public boolean isExistingClientErrorMessageDisplayed() {
        return driver.findElements(By.id("ru.andersen.afinny:id/afinny_mask_edit_text_error")).size() != 0;
    }

    @Step("Получаем текст сообщения об ошибке при вводе номера уже зарегистрированного клиента")
    public String getExistingClientErrorMessageText() {
        wait.until(ExpectedConditions.visibilityOf(existingClientErrorMessage));
        return existingClientErrorMessage.getText();
    }

    @Step("Очищаем поле для ввода номера телефона")
    public void clearPhoneNumberInput() {
        phoneNumberInput.clear();
    }

    @Step("Заполняем поле для ввода номера телефона невалидными символами")
    public void fillPhoneNumberInputWithInvalidSymbols(String androidKey) {
        if (androidKey.equals("space")) {
            for (int i = 0; i < 10; i++) {
                ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.SPACE));
            }
        } else {
            for (int i = 0; i < 3; i++) {
                ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_DOT));
                ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_COMMA));
                ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.MINUS));
            }
        }
    }
}
