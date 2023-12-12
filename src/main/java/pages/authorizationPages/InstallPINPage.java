package pages.authorizationPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.util.ArrayList;
import java.util.List;

public class InstallPINPage extends BasePage {

    private String pinPadButtonLocator = "//android.widget.Button[contains(@text, '%d')]";

    @AndroidFindBy(id = "ru.andersen.afinny:id/enter_pin_code_title")
    private MobileElement pinPageTitle;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_widget_pinpad_subtitle")
    private MobileElement pinPageSubtitle;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_widget_pinpad_input")
    private MobileElement pinInput;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_widget_pinpad_backspace_button")
    private MobileElement backspaceButton;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_widget_pinpad_error_text")
    private MobileElement pinPageErrorText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Подтвердите PIN']")
    private MobileElement pinConfirmPageTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Создайте PIN']")
    private MobileElement pinCreatePageTitle;

    @AndroidFindBy(id = "ru.andersen.afinny:id/enter_pin_code_title")
    private MobileElement enterPinCodeTitleElement;

    public InstallPINPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Получаем кнопку с цифрой")
    public MobileElement getPinPadButton(int number) {
        By pinButton = By.xpath(String.format(pinPadButtonLocator, number));
        return (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(pinButton));
    }

    @Step("Нажимаем на кнопку с цифрой")
    public void pinPadButtonClick(int number) {
        getPinPadButton(number).click();
    }

    @Step("Отображение всех кнопок с цифрами")
    public boolean isPinPadButtonsDisplayed() {
        List<Boolean> resultList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            resultList.add(getPinPadButton(i).isDisplayed());
        }
        return resultList.stream().allMatch(s -> s.equals(true));
    }

    @Step("Отображение в поле ввода PIN нажатий всех кнопок с цифрой")
    public boolean isClickPinPadButtonsDisplayed() {
        List<Boolean> resultList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            backspaceButtonClick();
            pinPadButtonClick(i);
            resultList.add(!getPinInputText().isEmpty());
        }
        return resultList.stream().allMatch(s -> s.equals(true));
    }

    @Step("Отображение элемента 'Стереть'")
    public boolean isBackspaceButtonDisplayed() {
        return isElementDisplayed(backspaceButton);
    }

    @Step("Нажимаем на кнопку 'Стереть'")
    public void backspaceButtonClick() {
        backspaceButton.click();
    }

    @Step("Отображение поля для ввода PIN")
    public boolean isPINInputDisplayed() {
        return isElementDisplayed(pinInput);
    }

    @Step("Получаем текст заголовка страницы создания PIN")
    public String getPINPageTitleText() {
        return pinPageTitle.getText();
    }

    @Step("Получаем текст подзаголовка страницы создания PIN")
    public String getPINPageSubtitleText() {
        return pinPageSubtitle.getText();
    }

    @Step("Отображение заголовка страницы создания PIN")
    public boolean isPINCreatePageTitleDisplayed() {
        return isElementDisplayed(pinCreatePageTitle);
    }

    @Step("Вводим PIN")
    public InstallPINPage enterPin(String pin) {
        String[] result = pin.split("");
        for (String num : result) {
            pinPadButtonClick(Integer.parseInt(num));
        }
        return new InstallPINPage(driver);
    }

    @Step("Получаем символы из поля ввода PIN")
    public String getPinInputText() {
        return pinInput.getText();
    }

    @Step("Отображение заголовка страницы подтверждения PIN")
    public boolean isPINConfirmPageTitleDisplayed() {
        return isElementDisplayed(pinConfirmPageTitle);
    }

    @Step("Получаем текст сообщения об ошибке")
    public String getPinPageErrorText() {
        return pinPageErrorText.getText();
    }

    @Step("Отображение сообщения об ошибке")
    public boolean isPinPageErrorTextDisplayed() {
        return isElementDisplayed(pinPageErrorText);
    }

    @Step("Стирание элементов из поля ввода PIN")
    public boolean isPinInputTextDeleted() {
        int beforeDelete = getPinInputText().length();
        backspaceButtonClick();
        int afterDelete = getPinInputText().length();
        return  beforeDelete > afterDelete;
    }

    @Step("Получаем текст из тайтла")
    public String getTitleText() {
        return enterPinCodeTitleElement.getText();
    }
}