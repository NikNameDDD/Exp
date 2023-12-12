package epic_1_registrationAuthorizationSecurity.us_1_1_registration.us_1_1_1_registrationMainPage;

import baseTest.BaseTest;
import io.qameta.allure.Epic;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static constants.ErrorMessages.BURGER_MENU_BUTTON_NOT_DISPLAYED;

@Epic("Epic-1 Регистрация/Авторизация/Безопасность")
@Feature("US-1.1 Регистрация")
@Story("US-1.1.1 Регистрация (Главная страница)")
public class MainPageTest extends BaseTest {
    @Test
    @DisplayName("C5965637 Проверка формы 'Главная страница'")
    public void mainPageTest() {
        assertAll("Проверка формы 'Главная страница' на отображение всех элементов",
                () -> assertTrue(mainPage.isRuButtonDisplayed(), "Не отображается кнопка 'Ru'"),
                () -> assertTrue(mainPage.isEnButtonDisplayed(), "Не отображается кнопка 'En'"),
                () -> assertTrue(mainPage.isBurgerMenuButtonDisplayed(), BURGER_MENU_BUTTON_NOT_DISPLAYED),
                () -> assertTrue(mainPage.isWelcomeLogoDisplayed(), "Не отображается лого"),
                () -> assertTrue(mainPage.isLoginButtonDisplayed(), "Не отображается кнопка 'Войти'"),
                () -> assertTrue(mainPage.isSignUpButtonDisplayed(), "Не отображается кнопка 'Зарегистрироваться'"),
                () -> assertTrue(mainPage.isAppTourButtonDisplayed(), "Не отображается кнопка 'Тур по приложению'"));
    }
}