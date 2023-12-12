package pages.registrationPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public abstract class RegistrationCommonElements extends BasePage {

    @AndroidFindBy(id = "ru.andersen.afinny:id/menu_item_toolbar_skip")
    private MobileElement skipButton;

    @AndroidFindBy(xpath = "//*[@text='Регистрация']")
    private MobileElement registrationPageHeader;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]")
    private MobileElement progressBar;

    public RegistrationCommonElements(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Нажимаем на кнопку 'Пропустить'")
    public void clickOnSkipButton() {
        skipButton.click();
    }

    @Step("Получаем текст заголовка 'Регистрация'")
    public String getRegistrationPageHeaderText() {
        wait.until(ExpectedConditions.visibilityOf(registrationPageHeader));
        return registrationPageHeader.getText();
    }

    @Step("Отображение прогресс-бара")
    public boolean isProgressBarDisplayed() {
        return progressBar.isDisplayed();
    }
}