package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;

public class ExchangeRatesPage extends BasePage {

    @AndroidFindBy(xpath = "//*[@text='Курсы валют']")
    private MobileElement exchangeRatesHeader;

    @AndroidFindBy(id = "ru.andersen.afinny:id/header_title_currency")
    private MobileElement columnCurrency;

    @AndroidFindBy(id = "ru.andersen.afinny:id/header_title_buy")
    private MobileElement columnBuy;

    @AndroidFindBy(id = "ru.andersen.afinny:id/header_title_sell")
    private MobileElement columnSell;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='USD']")
    private MobileElement infoOnUSD;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='EUR']")
    private MobileElement infoOnEUR;

    public ExchangeRatesPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Получаем текст заголовка раздела")
    public String getExchangeRatesHeaderText() {
        return exchangeRatesHeader.getText();
    }

    @Step("Отображение заголовка столбца 'Валюта'")
    public boolean isColumnCurrencyDisplayed() {
        return isElementDisplayed(columnCurrency);
    }

    @Step("Отображение заголовка столбца 'Покупка'")
    public boolean isColumnBuyDisplayed() {
        return isElementDisplayed(columnBuy);
    }

    @Step("Отображение заголовка столбца 'Продажа'")
    public boolean isColumnSellDisplayed() {
        return isElementDisplayed(columnSell);
    }

    @Step("Отображение информации о USD")
    public boolean isInfoOnUSDDisplayed() {
        return isElementDisplayed(infoOnUSD);
    }

    @Step("Отображение информации о EUR")
    public boolean isInfoOnEURDisplayed() {
        return isElementDisplayed(infoOnEUR);
    }
}