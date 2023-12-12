package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Scroll;

public class ATMsAndBranchesMapPage extends BasePage {

    private final String LOCATION_POP_UP = "//android.widget.Button[3]";

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button")
    private MobileElement whileUsingTheAppButton;

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_one_time_button")
    private MobileElement onlyThisTimeButton;

    @AndroidFindBy(xpath = "//android.widget.Button[3]")
    private MobileElement denyButton;

    @AndroidFindBy(accessibility = "Карта")
    private MobileElement mapLinearLayout;

    @AndroidFindBy(accessibility = "Список")
    private MobileElement listLinearLayout;

    @AndroidFindBy(id = "ru.andersen.afinny:id/my_location_button")
    private MobileElement determineLocationButton;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Карта Google']/android.view.View[1]")
    private MobileElement locationPin;

    @AndroidFindBy(id = "android:id/message")
    private MobileElement messageOnAbsenceOfAccessToGeoPosition;

    public ATMsAndBranchesMapPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Отображение кнопки 'Карта'")
    public boolean isMapLinearLayoutDisplayed() {
        return isElementDisplayed(mapLinearLayout);
    }

    @Step("Отображение кнопки 'Список'")
    public boolean listLinearLayoutChecking() {
        return isElementDisplayed(listLinearLayout);
    }

    @Step("Отображение выделения кнопки 'Карта'")
    public boolean mapLinearLayoutSelectedChecking() {
        return mapLinearLayout.isSelected();
    }

    @Step("Отображение выделения кнопки 'Список'")
    public boolean listLinearLayoutSelectedChecking() {
        return listLinearLayout.isSelected();
    }

    @Step("Отображение сообщения о запрете доступа к геолокации")
    public boolean isMessageOnAbsenceDisplayed() {
        return isElementDisplayed(messageOnAbsenceOfAccessToGeoPosition);
    }

    @Step("Отображение пина локации")
    public boolean isLocationPinDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(locationPin));
        return isElementDisplayed(locationPin);
    }

    @Step("Нажимаем кнопку")
    public void mapLinearLayoutClick() {
        mapLinearLayout.click();
    }

    @Step("Нажимаем на кнопку 'Разрешить только при использовании приложения' в попапе доступа к местоположению")
    public void clickOnWhileUsingTheAppButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(LOCATION_POP_UP)));
        whileUsingTheAppButton.click();
    }

    @Step("Нажимаем на кнопку 'Разрешить только сейчас' в попапе доступа к местоположению")
    public void clickOnOnlyThisTimeButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(LOCATION_POP_UP)));
        onlyThisTimeButton.click();
    }

    @Step("Нажимаем на кнопку 'Отклонить' в попапе доступа к местоположению")
    public void clickOnDenyButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(LOCATION_POP_UP)));
        denyButton.click();
    }

    @Step("Нажимаем на кнопку определения местоположения")
    public void clickOnDetermineLocationButton() {
        determineLocationButton.click();
    }

    @Step("Скроллим карту")
    public void scrollMap() {
        Scroll.scrollBy(driver, 0.05, 0.5, 0.95, 0.5);
    }
}