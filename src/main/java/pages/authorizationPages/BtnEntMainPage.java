package pages.authorizationPages;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import pages.BasePage;

public class BtnEntMainPage extends BasePage {//C5870102
    public BtnEntMainPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @AndroidFindBy(id = "ru.andersen.afinny:id/primary_button")
    public WebElement mainBtn;

    @Override
    protected MobileElement waitForElementBeingClickableInSeconds(MobileElement element, int timeoutInSeconds) {
        return super.waitForElementBeingClickableInSeconds(element, timeoutInSeconds);

    }
    @Step("Нажать на кнопку войти")
    public void ClickOnBtn(){
        mainBtn.click();
    }
}
