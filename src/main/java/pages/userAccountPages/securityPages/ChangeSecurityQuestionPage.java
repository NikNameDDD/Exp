package pages.userAccountPages.securityPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import pages.BasePage;

public class ChangeSecurityQuestionPage extends BasePage {

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_security_question_dropdown")
    private MobileElement securityQuestionDropdown;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[1]/android.widget.EditText")
    private MobileElement createSecurityQuestionField;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[2]/android.widget.EditText")
    private MobileElement answerSecurityQuestionField;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_edit_text_counter")
    private MobileElement textSymbolsCounter;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Напишите контрольный вопрос']")
    private MobileElement createSecurityQuestionFieldLabelText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Напишите ответ на контрольный вопрос']")
    private MobileElement answerSecurityQuestionInputFieldLabelText;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_edit_text_input")
    private MobileElement textInputField;

    @AndroidFindBy(id = "ru.andersen.afinny:id/afinny_edit_text_error")
    private MobileElement errorMessageText;

    public ChangeSecurityQuestionPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Нажимаем на выпадающий список контрольных вопросов")
    public ChangeSecurityQuestionPage clickSecurityQuestionDropdown() {
        waitForElementVisibilityInSeconds(securityQuestionDropdown, 2).click();
        return this;
    }

    @Step("Нажимаем на поле 'Напишите контрольный вопрос'")
    public void clickCreateSecurityQuestionField() {
        createSecurityQuestionField.click();
    }

    @Step("Нажимаем на поле 'Напишите ответ на контрольный вопрос'")
    public void clickAnswerSecurityQuestionField() {
        answerSecurityQuestionField.click();
    }

    @Step("Кликаем по варианту 'Написать свой вопрос'")
    public ChangeSecurityQuestionPage clickWriteOwnSecurityQuestionVariant() {
        TouchAction action = new TouchAction(driver);
        waitForElementVisibilityInSeconds(securityQuestionDropdown, 2);
        action.tap(PointOption.point(500, 1500)).perform();
        return this;
    }

    @Step("Кликаем по варианту 'Любимая книга'")
    public ChangeSecurityQuestionPage clickFavouriteBookVariant() {
        TouchAction action = new TouchAction(driver);
        waitForElementVisibilityInSeconds(securityQuestionDropdown, 2);
        action.tap(PointOption.point(500, 1100)).perform();
        return this;
    }

    @Step("Отображения курсора ввода текста")
    public boolean isInputFieldCursorDisplayed(MobileElement element) {
        return element.getAttribute("focused").equals("true");
    }

    @Step("Отображения курсора ввода текста поля 'Напишите контрольный вопрос'")
    public boolean isCreateSecurityQuestionInputFieldCursorDisplayed() {
        return isInputFieldCursorDisplayed(createSecurityQuestionField);
    }

    @Step("Отображения курсора ввода текста поля 'Напишите ответ на контрольный вопрос'")
    public boolean isAnswerSecurityQuestionInputFieldCursorDisplayed() {
        return isInputFieldCursorDisplayed(answerSecurityQuestionField);
    }

    @Step("Отображение лейбла над полем 'Напишите контрольный вопрос'")
    public boolean isCreateSecurityQuestionFieldLabelTextDisplayed() {
        return isElementDisplayed(createSecurityQuestionFieldLabelText);
    }

    @Step("Отображение лейбла над полем 'Напишите ответ на контрольный вопрос'")
    public boolean isAnswerSecurityQuestionInputFieldLabelTextDisplayed() {
        return isElementDisplayed(answerSecurityQuestionInputFieldLabelText);
    }

    @Step("Отображение счётчика введенных символов")
    public boolean isTextSymbolsCounterDisplayed() {
        return isElementDisplayed(textSymbolsCounter);
    }

    @Step("Получаем текст поля 'Напишите контрольный вопрос'")
    public String getCreateSecurityQuestionFieldText() {
        return createSecurityQuestionField.getText();
    }

    @Step("Получаем текст поля 'Напишите ответ на контрольный вопрос'")
    public String getAnswerSecurityQuestionFieldText() {
        return answerSecurityQuestionField.getText();
    }

    @Step("Ввод текста в поле 'Напишите контрольный вопрос'")
    public ChangeSecurityQuestionPage enterCreateSecurityQuestionFieldText(String text) {
        createSecurityQuestionField.sendKeys(text);
        return this;
    }

    @Step("Ввод текста в поле 'Напишите ответ на контрольный вопрос'")
    public ChangeSecurityQuestionPage enterAnswerSecurityQuestionFieldText(String text) {
        answerSecurityQuestionField.sendKeys(text);
        return this;
    }

    @Step("Очищаем поле 'Напишите контрольный вопрос'")
    public void clearCreateSecurityQuestionField() {
        createSecurityQuestionField.clear();
    }

    @Step("Очищаем поле 'Напишите ответ на контрольный вопрос'")
    public void clearAnswerSecurityQuestionField() {
        answerSecurityQuestionField.clear();
    }

    @Step("Ввод текста в поле")
    public ChangeSecurityQuestionPage enterTextInputFieldText(String text) {
        textInputField.sendKeys(text);
        return this;
    }

    @Step("Получаем текст поля")
    public String getTextInputFieldText() {
        return textInputField.getText();
    }

    @Step("Отображение сообщения об ошибке")
    public boolean isErrorMessageTextDisplayed() {
        return isElementDisplayed(errorMessageText);
    }
}
