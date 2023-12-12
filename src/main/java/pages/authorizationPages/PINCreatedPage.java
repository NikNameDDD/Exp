package pages.authorizationPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import pages.BasePage;

public class PINCreatedPage extends BasePage {

    @AndroidFindBy(id = "ru.andersen.afinny:id/authorization_pin_code_created_title")
    private MobileElement pinCreatedTitle;

    @AndroidFindBy(id = "ru.andersen.afinny:id/authorization_pin_code_created_text")
    private MobileElement pinCreatedSubtitle;

    @AndroidFindBy(id = "ru.andersen.afinny:id/authorization_pin_code_created_image")
    private MobileElement pinCreatedImage;

    public PINCreatedPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Получаем текст элемента 'PIN создан'")
    public String getPINCreatedTitleText() {
        return pinCreatedTitle.getText();
    }

    @Step("Получаем текст элемента 'Вы сможете изменить PIN код в настройках.'")
    public String getPINCreatedSubtitleText() {
        return pinCreatedSubtitle.getText();
    }

    @Step("Отображение тематической картинки")
    public boolean isPINCreatedImageDisplayed() {
        return isElementDisplayed(pinCreatedImage);
    }
}
