package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BurgerMenuPage extends BasePage {

    @AndroidFindBy(xpath = "//*[@text='Меню']")
    private MobileElement burgerMenuHeader;

    @AndroidFindBy(id = "ru.andersen.afinny:id/section_finances_ATMs_and_branches")
    private MobileElement atmsAndBranchesButton;

    @AndroidFindBy(id = "ru.andersen.afinny:id/section_finances_exchange_rates")
    private MobileElement exchangeRatesButton;

    @AndroidFindBy(id = "ru.andersen.afinny:id/section_support_free_calls_text_body")
    private MobileElement localSupportNumber;

    @AndroidFindBy(id = "ru.andersen.afinny:id/section_support_international_calls_text_body")
    private MobileElement internationalSupportNumber;

    @AndroidFindBy(id = "ru.andersen.afinny:id/section_support_bugreport")
    private MobileElement bugReportButton;

    @AndroidFindBy(id = "ru.andersen.afinny:id/section_settings_language_value")
    private MobileElement setLangDropdown;

    @AndroidFindBy(id = "ru.andersen.afinny:id/section_settings_theme_value")
    private MobileElement setThemeDropdown;

    public BurgerMenuPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Отображение кнопки 'Банкоматы и отделения'")
    public boolean isATMsAndBranchesButtonDisplayed() {
        return isElementDisplayed(atmsAndBranchesButton);
    }

    @Step("Отображение кнопки 'Курсы валют'")
    public boolean isExchangeRatesButtonDisplayed() {
        return isElementDisplayed(exchangeRatesButton);
    }

    @Step("Отображение номера телефона для звонков по России")
    public boolean isLocalSupportNumberDisplayed() {
        return isElementDisplayed(localSupportNumber);
    }

    @Step("Отображение номера телефона для международных звонков")
    public boolean isInternationalSupportNumberDisplayed() {
        return isElementDisplayed(internationalSupportNumber);
    }

    @Step("Отображение кнопки 'Отправить отчет об ошибке'")
    public boolean isBugReportButtonDisplayed() {
        return isElementDisplayed(bugReportButton);
    }

    @Step("Отображение дропдауна с выбором языка приложения")
    public boolean isSetLangDropdownDisplayed() {
        return isElementDisplayed(setLangDropdown);
    }

    @Step("Отображение дропдауна с выбором темы приложения")
    public boolean isSetThemeDropdownDisplayed() {
        return isElementDisplayed(setThemeDropdown);
    }

    @Step("Кликаем по кнопке 'Банкоматы и отделения'")
    public void clickOnATMsAndBranchesButton() {
        wait.until(ExpectedConditions.visibilityOf(atmsAndBranchesButton));
        atmsAndBranchesButton.click();
    }

    @Step("Кликаем по кнопке 'Курсы валют'")
    public void clickOnExchangeRatesButton() {
        exchangeRatesButton.click();
    }

    @Step("Получаем текст заголовка меню")
    public String getBurgerMenuHeaderText() {
        return burgerMenuHeader.getText();
    }
}