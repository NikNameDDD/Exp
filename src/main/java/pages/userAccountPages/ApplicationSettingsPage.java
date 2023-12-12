package pages.userAccountPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class ApplicationSettingsPage extends BasePage {

    @AndroidFindBy(id = "ru.andersen.afinny:id/toolbar")
    private MobileElement applicationSettingsHeader;

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_settings_log_in_setup_title")
    private MobileElement applicationSettingsLogIn;

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_settings_authorisation_by_pin_text")
    private MobileElement applicationSettingsByPin;

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_settings_authorisation_by_login_text")
    private MobileElement applicationSettingsByLogin;

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_settings_authorisation_by_bio_text")
    private MobileElement applicationSettingsByBio;

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_settings_authorisation_by_bio_explanation_text")
    private MobileElement applicationSettingsExplanation;

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_settings_language_text")
    private MobileElement languageText;

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_settings_language_text_popup")
    private MobileElement settingsLanguageDrop;

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_settings_theme_text")
    private MobileElement settingsThemeText;

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_settings_theme_text_popup")
    private MobileElement settingsThemeDrop;

    @AndroidFindBy(className = "android.widget.ImageButton")
    private MobileElement backButton;

    public ApplicationSettingsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Отображение заголовка 'Настройка приложения'")
    public boolean isApplicationSettingsHeaderDisplayed(){
        return waitForElementVisibilityInSeconds(applicationSettingsHeader, 2).isDisplayed();
    }

    @Step("Отображение 'Настройка входа'")
    public boolean isApplicationSettingsLogInDisplayed(){
        return waitForElementVisibilityInSeconds(applicationSettingsLogIn, 2).isDisplayed();
    }

    @Step("Отображение Radiobutton 'Вход по PIN-коду'")
    public boolean isApplicationSettingsByPinDisplayed(){
       return waitForElementVisibilityInSeconds(applicationSettingsByPin, 2).isDisplayed();
    }

    @Step("Отображение Radiobutton 'Вход по логину и паролю'")
    public boolean isApplicationSettingsByLogInDisplayed(){
        return waitForElementVisibilityInSeconds(applicationSettingsByLogin, 2).isDisplayed();
    }

    @Step("Отображение Radiobutton 'Использовать биометрию'")
    public boolean isApplicationSettingsByBioDisplayed(){
        return waitForElementVisibilityInSeconds(applicationSettingsByBio, 2).isDisplayed();
    }

    @Step("Получение текста под Radiobutton 'Использовать биометрию'")
    public String getTextSettingsExplanation(){
        wait.until(ExpectedConditions.visibilityOf(applicationSettingsExplanation));
        return applicationSettingsExplanation.getText();
    }

    @Step("Отображение иконки 'Язык'")
    public boolean isLanguageTextDisplayed(){
        return waitForElementVisibilityInSeconds(languageText,2).isDisplayed();
    }

    @Step("Отображение Dropdown (EN/RU Выбор языка)")
    public boolean isLanguageSettingDropDisplayed(){
        return waitForElementVisibilityInSeconds(settingsLanguageDrop, 2).isDisplayed();
    }

    @Step("Нажимаем Dropdown (EN/RU Выбор языка)")
    public void languageSettingDropClick(){
        wait.until(ExpectedConditions.visibilityOf(settingsLanguageDrop));
        settingsLanguageDrop.click();
    }

    @Step("Отображение смены языка")
    public String getLanguageText(){
        wait.until(ExpectedConditions.visibilityOf(settingsLanguageDrop));
        return settingsLanguageDrop.getText();
    }

    @Step("Отображение иконки 'Тема'")
    public boolean isSettingsThemeTextDisplayed(){
        return waitForElementVisibilityInSeconds(settingsThemeText, 2).isDisplayed();
    }

    @Step("Отображение Dropdown (Темная/Светлая)")
    public boolean isSettingsThemeDropDisplayed(){
        return waitForElementVisibilityInSeconds(settingsThemeDrop, 2).isDisplayed();
    }

    @Step("Отображение кнопки назад")
    public boolean backButtonDisplayed(){
        return waitForElementVisibilityInSeconds(backButton,2).isDisplayed();
    }

    @Step("Кликаем по варианту 'En'")
    public ApplicationSettingsPage clickEnglishSettingLanguage() {
        TouchAction action = new TouchAction(driver);
        waitForElementVisibilityInSeconds(languageText, 2);
        action.tap(PointOption.point(700, 1200)).perform();
        return this;
    }
}