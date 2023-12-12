package pages.userAccountPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;
import utils.Scroll;

@Getter
public class FirstPage extends BasePage {

    @AndroidFindBy(id = "ru.andersen.afinny:id/main_menu_top_user_name")
    private MobileElement userName;
    @AndroidFindBy(id = "ru.andersen.afinny:id/main_menu_top_avatar")
    private MobileElement avatar;

    @AndroidFindBy(xpath = "//*[@text='Карты']")
    private MobileElement cardsTextButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Карты']/../android.widget.ImageView")
    private MobileElement cardsDropdown;
    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[2]/android.widget.LinearLayout")
    private MobileElement cardsContents;
    @AndroidFindBy(xpath = "//*[@text='Депозиты']")
    private MobileElement depositsTextButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Депозиты']/../android.widget.ImageView")
    private MobileElement depositsDropdown;
    @AndroidFindBy(xpath = "//*[@text='Кредиты']")
    private MobileElement loansTextButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Кредиты']/../android.widget.ImageView")
    private MobileElement loansDropdown;
    @AndroidFindBy(xpath = "//*[@text='Страхование']")
    private MobileElement insuranceTextButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Страхование']/../android.widget.ImageView")
    private MobileElement insuranceDropdown;
    @AndroidFindBy(xpath = "//*[@text='Инвестиции']")
    private MobileElement investmentTextButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Инвестиции']/../android.widget.ImageView")
    private MobileElement investmentDropdown;
    @AndroidFindBy(xpath = "//*[@text='Акции и предложения']")
    private MobileElement promotionsAndRecommendationsTextButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Акции и предложения']/../android.widget.ImageView")
    private MobileElement promotionsAndRecommendationsDropdown;
    @AndroidFindBy(xpath = "//*[@text='Курсы валют']")
    private MobileElement exchangeRatesTextButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Курсы валют']/../android.widget.ImageView")
    private MobileElement exchangeRatesDropdown;
    @AndroidFindBy(xpath = "//*[@text='Аналитика']")
    private MobileElement analyticsTextButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Аналитика']/../android.widget.ImageView")
    private MobileElement analyticsDropdown;

    @AndroidFindBy(id = "ru.andersen.afinny:id/item_main_menu_top_rv_add_item_icon")
    private MobileElement addItemButton;
    @AndroidFindBy(xpath = "//*[@text='Пополнение мобильного']")
    private MobileElement depositingMobileTopMenuButton;
    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[2]//android.widget.ImageView[1]")
    private MobileElement depositingMobileTopMenuIcon;
    @AndroidFindBy(xpath = "//*[@text='Перевод на карту']")
    private MobileElement moneyTransferToCardTopMenuButton;
    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[3]//android.widget.ImageView[1]")
    private MobileElement moneyTransferToCardTopMenuIcon;
    @AndroidFindBy(xpath = "//*[@text='Коммунальные услуги']")
    private MobileElement utilityServicesTopMenuButton;
    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[4]//android.widget.ImageView[1]")
    private MobileElement utilityServicesTopMenuIcon;
    @AndroidFindBy(xpath = "//*[@text='Обмен валют']")
    private MobileElement currencyExchangeTopMenuButton;
    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[4]//android.widget.ImageView[1]")
    private MobileElement currencyExchangeTopMenuIcon;

    @AndroidFindBy(xpath = "//*[@text='Главная']")
    private MobileElement mainPageNavigationBarButton;
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Главная']//android.widget.ImageView")
    private MobileElement mainPageNavigationBarIcon;
    @AndroidFindBy(xpath = "//*[@text='Платежи']")
    private MobileElement paymentsNavigationBarButton;
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Платежи']//android.widget.ImageView")
    private MobileElement paymentsNavigationBarIcon;
    @AndroidFindBy(xpath = "//*[@text='Переводы']")
    private MobileElement transfersNavigationBarButton;
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Переводы']//android.widget.ImageView")
    private MobileElement transfersNavigationBarIcon;
    @AndroidFindBy(xpath = "//*[@text='История']")
    private MobileElement paymentRecordsNavigationBarButton;
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='История']//android.widget.ImageView")
    private MobileElement paymentRecordsNavigationBarIcon;
    @AndroidFindBy(xpath = "//*[@text='Еще']")
    private MobileElement moreNavigationBarButton;
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Еще']//android.widget.ImageView")
    private MobileElement moreNavigationBarIcon;

    public FirstPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Скроллим основное меню вниз")
    public void scrollMainMenuDown() {
        if (driver.findElements(By.xpath("//*[@text='Аналитика']")).isEmpty()) {
            Scroll.scrollBy(driver, 0.5, 0.9, 0.5, 0.5);
        }
    }

    @Step("Скроллим верхнее меню вправо")
    public void scrollTopMenuToRight() {
        if (driver.findElements(By.xpath("//*[@text='Обмен валют']")).isEmpty()) {
            Scroll.scrollBy(driver, 0.7, 0.25, 0.4, 0.25);
        }
    }

    @Step("Отображение содержимого дропдауна 'Карты'")
    public boolean isCardsContentsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(cardsContents));
        return isElementDisplayed(cardsContents);
    }

    @Step("Считаем количество открытых дропдаунов")
    public int countOpenDropdowns() {
        return driver.findElements(By.xpath("//androidx.recyclerview" +
                ".widget.RecyclerView[2]/android.widget.LinearLayout")).size();
    }

    @Step("Нажимаем на иконку пользователя")
    public PersonalDataMenu clickUserAvatar() {
        waitForElementVisibilityInSeconds(avatar, 3).click();
        return new PersonalDataMenu(driver);
    }
}