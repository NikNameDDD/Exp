package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;

public class AllowAccessToGeoPositionPage extends BasePage {

    @AndroidFindBy(xpath = "//*[@text='Банкоматы и отделения']")
    private MobileElement allowGeoAccessHeader;

    @AndroidFindBy(id = "ru.andersen.afinny:id/allow_geolocation_access_text")
    private MobileElement allowGeoAccessShortDescriptionText;

    @AndroidFindBy(id = "ru.andersen.afinny:id/allow_geolocation_access_description_text")
    private MobileElement allowGeoAccessLongDescriptionText;

    @AndroidFindBy(id = "ru.andersen.afinny:id/allow_geo_access_image")
    private MobileElement allowGeoAccessImage;

    public AllowAccessToGeoPositionPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Получаем текст заголовка раздела")
    public String getAllowGeoAccessHeaderText() {
        return allowGeoAccessHeader.getText();
    }

    @Step("Получаем короткий пояснительный текст")
    public String getShortDescriptionText() {
        return allowGeoAccessShortDescriptionText.getText();
    }

    @Step("Получаем длинный пояснительный текст")
    public String getLongDescriptionText() {
        return allowGeoAccessLongDescriptionText.getText();
    }

    @Step("Отображение тематического изображения")
    public boolean isAllowGeoAccessImageDisplayed() {
        return isElementDisplayed(allowGeoAccessImage);
    }
}