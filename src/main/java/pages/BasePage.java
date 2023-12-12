package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    public AppiumDriver<MobileElement> driver;

    protected FluentWait<AppiumDriver> wait;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='A-Finny']")
    private MobileElement appIcon;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout/android.widget.TextView")
    private List<MobileElement> appIconContextMenuItems;

    @AndroidFindBy(id = "com.android.settings:id/button2")
    private MobileElement clearCashButton;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[4]")
    private MobileElement buttonCash;

    @AndroidFindBy(xpath = "//android.widget.ImageButton")
    private MobileElement backButton;

    @AndroidFindBy(id = "ru.andersen.afinny:id/menuItem_toolbar_burger")
    private MobileElement burgerMenuButton;

    @AndroidFindBy(id = "ru.andersen.afinny:id/primary_button")
    @Description("Большая жёлтая кнопка внизу экрана. На разных экранах имеет разную роль: " +
            "на экранах регистрации и доступа к местоположению - роль 'Продолжить', " +
            "на экране политики конфиденциальности - роль 'Вернуться назад', " +
            "на экране авторизации - роль 'Войти'")
    private MobileElement primaryButton;

    public BasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new FluentWait<AppiumDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Отображение кнопки 'Назад'")
    public boolean isBackButtonDisplayed() {
        return backButton.isDisplayed();
    }

    @Step("Отображение кнопки бургерного меню")
    public boolean isBurgerMenuButtonDisplayed() {
        return burgerMenuButton.isDisplayed();
    }

    @Step("Отображение primary-кнопки")
    public boolean isPrimaryButtonDisplayed() {
        return primaryButton.isDisplayed();
    }

    @Step("Нажимаем на кнопку 'Назад'")
    public void clickOnBackButton() {
        backButton.click();
    }

    @Step("Нажимаем на кнопку бургерного меню")
    public void clickOnBurgerMenuButton() {
        burgerMenuButton.click();
    }

    @Step("Нажимаем на primary-кнопку")
    public void clickOnPrimaryButton() {
        primaryButton.click();
    }

    @Step("Получаем текст primary-кнопки")
    public String getPrimaryButtonText() {
        wait.until(ExpectedConditions.visibilityOf(primaryButton));
        return primaryButton.getText();
    }

    @Step("Активность primary-кнопки")
    public boolean isPrimaryButtonEnabled() {
        return primaryButton.isEnabled();
    }

    @Step("Очищаем кэш и открываем приложение")
    public void clearCacheAndStartApplication() {
        new Actions(driver).clickAndHold(appIcon).perform();
        chooseMenuItem(appIconContextMenuItems).click();
        buttonCash.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.settings:id/button2")));
        clearCashButton.click();
        driver.navigate().back();
        driver.navigate().back();
        appIcon.click();
    }

    @Step("Открываем приложение")
    public void startApp() {
        driver.launchApp();
    }

    @Step("Закрываем приложение")
    public void closeApp() {
        driver.closeApp();
    }

    @Step("Переоткрываем приложение")
    public void restartApp() {
        closeApp();
        startApp();
    }

    @Step("Переоткрываем приложение через иконку на рабочем столе")
    public BasePage restartAppViaAppIcon() {
        closeApp();
        appIcon.click();
        waitForPageLoaded();
        return new BasePage(driver);
    }

    @Step("Ожидаем загрузку контента страницы")
    public void waitForPageLoaded() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }

    @Step("Метод выбирает в контекстном меню иконки приложения нужный пункт - 'App info'. " +
            "Порядок пунктов в меню может различаться в зависимости от расположения иконки на экране девайса. " +
            "Этот метод определяет, первым или вторым по счёту идёт нужный пункт, и выбирает его. ")
    public MobileElement chooseMenuItem(List<MobileElement> list) {
        if (list.get(0).getText().equals("App info")) {
            return list.get(0);
        }
        return list.get(1);
    }

    @Step("Кликаем на кнопку 'Назад' на девайсе")
    public void clickOnDeviceBackButton() {
        driver.navigate().back();
    }

    protected MobileElement waitForElementVisibilityInSeconds(MobileElement element, int timeoutInSeconds) {
        return (MobileElement) new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected MobileElement waitForElementBeingClickableInSeconds(MobileElement element, int timeoutInSeconds) {
        return (MobileElement) new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    @Step("Отображение клавиатуры на девайсе")
    public boolean isDeviceKeyboardShown() {
        return ((AndroidDriver)driver).isKeyboardShown();
    }

    @Step("Включение/выключение Wifi и передачи данных")
    public void switchDeviceNetworkConnections() {
        ((AndroidDriver)driver).toggleWifi();
        ((AndroidDriver)driver).toggleData();
    }

    @Step("Отображение элемента")
    public boolean isElementDisplayed(MobileElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}