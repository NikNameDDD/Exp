package epic_1_registrationAuthorizationSecurity.us_1_1_registration.us_1_1_1_registrationMainPage;

import baseTest.BaseTest;
import io.qameta.allure.Epic;

import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static constants.ErrorMessages.EXPECTED_PAGE_DID_NOT_OPEN;

@Epic("Epic-1 Регистрация/Авторизация/Безопасность")
@Feature("US-1.1 Регистрация")
@Story("US-1.1.1 Регистрация (Главная страница)")
public class BasicButtonsOnMainPageTest extends BaseTest {

    @Test
    @DisplayName("C5854363 Проверка кнопки 'Войти'")
    public void loginButtonTest() {
        mainPage.clickOnLoginButton();
        assertEquals("Авторизация", enterLoginDataPage.getAuthorizationPageHeaderText(), EXPECTED_PAGE_DID_NOT_OPEN);
    }

    @Test
    @DisplayName("C5966210 Проверка кнопки 'Зарегистрироваться'")
    public void signUpButtonTest() {
        mainPage.clickOnSignUpButton();
        enterPhoneForRegistrationPage.clickOnSkipButton();
        assertEquals("Регистрация", enterPhoneForRegistrationPage.getRegistrationPageHeaderText(), EXPECTED_PAGE_DID_NOT_OPEN);
    }

    @Test
    @DisplayName("C5966211 Проверка кнопки 'Тур по приложению'")
    public void appTourButtonTest() {
        mainPage.clickOnAppTourButton();
        assertEquals("Функциональность временно недоступна", appTourPage.getAppTourPageHeaderText(), EXPECTED_PAGE_DID_NOT_OPEN);
    }

    @AfterEach
    public void closeRegistrationPage() {
        mainPage.restartApp();
    }
}