package epic_1_registrationAuthorizationSecurity.us_1_1_registration.us_1_1_2_registrationChooseLanguage;

import baseTest.BaseTest;
import io.qameta.allure.Epic;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static constants.ErrorMessages.LANGUAGE_NOT_CHANGED;
import static constants.ErrorMessages.APP_IN_ENGLISH_WHEN_FIRST_OPEN;

@Epic("Epic-1 Регистрация/Авторизация/Безопасность")
@Feature("US-1.1 Регистрация")
@Story("US-1.1.2 Регистрация (Выбор языка)")
public class ChoiceLanguageTest extends BaseTest {

    @Test
    @DisplayName("C5854350 Проверка смены языка на EN")
    public void choiceLanguageTest() throws InterruptedException {
        mainPage.clickOnEngButton();
        assertEquals("Login", mainPage.getLoginButtonText(), LANGUAGE_NOT_CHANGED);
        mainPage.clickOnRuButton();
        assertEquals("Войти", mainPage.getLoginButtonText(), LANGUAGE_NOT_CHANGED);
    }

    @Test
    @DisplayName("C5854351 Проверка сохранения выбора языка при очищенном кэше(чек LocalStorage)")
    public void savingLanguageSettingsClearingCacheTest() throws InterruptedException {
        assertEquals("Войти", mainPage.getLoginButtonText(), APP_IN_ENGLISH_WHEN_FIRST_OPEN);
        mainPage.clickOnEngButton();
        assertEquals("Login", mainPage.getLoginButtonText(), LANGUAGE_NOT_CHANGED);
        mainPage.closeApp();
        mainPage.clearCacheAndStartApplication();
        assertEquals("Login", mainPage.getLoginButtonText(), LANGUAGE_NOT_CHANGED);
    }

    @AfterEach
    public void restartApplication() {
        mainPage.restartApp();
    }
}