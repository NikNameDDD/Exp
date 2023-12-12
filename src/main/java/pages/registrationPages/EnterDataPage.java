package pages.registrationPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Scroll;

public class EnterDataPage extends RegistrationCommonElements {

    @AndroidFindBy(id = "ru.andersen.afinny:id/headline")
    private MobileElement enterDataTitle;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[1]/android.widget.EditText")
    private MobileElement firstNameInput;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[2]/android.widget.EditText")
    private MobileElement lastNameInput;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[3]/android.widget.EditText")
    private MobileElement middleNameInput;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[4]/android.widget.EditText")
    private MobileElement passportNumberInput;

    @AndroidFindBy(id = "ru.andersen.afinny:id/resident_radio")
    private MobileElement residentRadioButton;

    @AndroidFindBy(id = "ru.andersen.afinny:id/nonresident_radio")
    private MobileElement nonResidentRadioButton;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_edit_text_label")
    private MobileElement labelAboveInput;

    public EnterDataPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Получаем текст тайтла страницы")
    public String getEnterDataTitleText() {
        wait.until(ExpectedConditions.visibilityOf(enterDataTitle));
        return enterDataTitle.getText();
    }

    @Step("Получаем текст плейсхолдера поля ввода имени")
    public String getFirstNameInputText() {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        return firstNameInput.getText();
    }

    @Step("Получаем текст плейсхолдера поля ввода фамилии")
    public String getLastNameInputText() {
        wait.until(ExpectedConditions.visibilityOf(lastNameInput));
        return lastNameInput.getText();
    }

    @Step("Получаем текст плейсхолдера поля ввода отчества")
    public String getMiddleNameInputText() {
        wait.until(ExpectedConditions.visibilityOf(middleNameInput));
        return middleNameInput.getText();
    }

    @Step("Получаем текст плейсхолдера поля ввода номера паспорта")
    public String getPassportNumberInputText() {
        wait.until(ExpectedConditions.visibilityOf(passportNumberInput));
        return passportNumberInput.getText();
    }

    @Step("Получаем текст радиобаттона 'Резидент'")
    public String getResidentRadioButtonText() {
        wait.until(ExpectedConditions.visibilityOf(residentRadioButton));
        return residentRadioButton.getText();
    }

    @Step("Получаем текст радиобаттона 'Нерезидент'")
    public String getNonResidentRadioButtonText() {
        wait.until(ExpectedConditions.visibilityOf(nonResidentRadioButton));
        return nonResidentRadioButton.getText();
    }

    @Step("Получаем текст лейбла над полем ввода")
    public String getLabelAboveInputText() {
        wait.until(ExpectedConditions.visibilityOf(labelAboveInput));
        return labelAboveInput.getText();
    }

    @Step("Кликаем в поле ввода имени")
    public void clickInFirstNameInput() {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        firstNameInput.click();
    }

    @Step("Кликаем в поле ввода фамилии")
    public void clickInLastNameInput() {
        wait.until(ExpectedConditions.visibilityOf(lastNameInput));
        lastNameInput.click();
    }

    @Step("Кликаем в поле ввода отчества")
    public void clickInMiddleNameInput() {
        wait.until(ExpectedConditions.visibilityOf(middleNameInput));
        middleNameInput.click();
    }

    @Step("Кликаем в поле ввода номера паспорта")
    public void clickInPassportNumberInput() {
        wait.until(ExpectedConditions.visibilityOf(passportNumberInput));
        passportNumberInput.click();
    }

    @Step("Кликаем по радиобаттону 'Резидент'")
    public void clickOnResidentRadioButton() {
        wait.until(ExpectedConditions.visibilityOf(residentRadioButton));
        residentRadioButton.click();
    }

    @Step("Кликаем по радиобаттону 'Нерезидент'")
    public void clickOnNonResidentRadioButton() {
        wait.until(ExpectedConditions.visibilityOf(nonResidentRadioButton));
        nonResidentRadioButton.click();
    }

    @Step("Скроллим форму")
    public void scrollForm(double startX, double startY, double endX, double endY) {
        Scroll.scrollBy(driver, startX, startY, endX, endY);
    }

    @Step("Заполняем форму")
    public void fillInForm(String firstName, String lastName, String middleName, String passportNumber) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        middleNameInput.sendKeys(middleName);
        passportNumberInput.sendKeys(passportNumber);
    }

    @Step("Очищаем форму")
    public void clearForm() {
        firstNameInput.clear();
        lastNameInput.clear();
        middleNameInput.clear();
        passportNumberInput.clear();
    }
}