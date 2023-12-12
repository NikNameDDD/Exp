package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import pages.authorizationPages.EnterLoginDataPage;

public class MainPage extends BasePage {

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_welcome_btn_login")
    private MobileElement loginButton;

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_welcome_btn_signUp")
    private MobileElement signUpButton;

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_welcome_btn_tour")
    private MobileElement appTourButton;

    @AndroidFindBy(id = "ru.andersen.afinny:id/language_en")
    private MobileElement enButton;

    @AndroidFindBy(id = "ru.andersen.afinny:id/language_ru")
    private MobileElement ruButton;

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_welcome_logo")
    private MobileElement welcomeLogo;

    public MainPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Отображение логотипа")
    public boolean isWelcomeLogoDisplayed() {
        return isElementDisplayed(welcomeLogo);
    }

    @Step("Отображение кнопки 'Войти'")
    public boolean isLoginButtonDisplayed() {
        return isElementDisplayed(loginButton);
    }

    @Step("Нажимаем на кнопку 'Войти'")
    public EnterLoginDataPage clickOnLoginButton() {
        loginButton.click();
        return new EnterLoginDataPage(driver);
    }

    @Step("Отображение кнопки 'Зарегистрироваться'")
    public boolean isSignUpButtonDisplayed() {
        return isElementDisplayed(signUpButton);
    }

    @Step("Нажимаем на кнопку 'Зарегистрироваться'")
    public void clickOnSignUpButton() {
        signUpButton.click();
    }

    @Step("Отображение элемента 'Eng'")
    public boolean isEnButtonDisplayed() {
        return isElementDisplayed(enButton);
    }

    @Step("Отображение элемента 'Ru'")
    public boolean isRuButtonDisplayed() {
        return isElementDisplayed(ruButton);
    }

    @Step("Отображение элемента 'Тур по приложению'")
    public boolean isAppTourButtonDisplayed() {
        return isElementDisplayed(appTourButton);
    }

    @Step("Нажимаем на кнопку 'Тур по приложению'")
    public void clickOnAppTourButton() {
        appTourButton.click();
    }

    @Step("Нажимаем на кнопку 'Ru'")
    public void clickOnRuButton() throws InterruptedException {
        ruButton.click();
        waitForPageLoaded();
    }

    @Step("Нажимаем на кнопку 'En'")
    public void clickOnEngButton() throws InterruptedException {
        enButton.click();
        waitForPageLoaded();
    }

    @Step("Отображение текста кнопки 'Login'")
    public String getLoginButtonText() {
        return loginButton.getText();
    }
}