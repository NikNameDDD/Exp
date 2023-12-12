package epic_1_registrationAuthorizationSecurity.us_1_1_registration.us_1_1_3_registrationBurgerMenu.exchangeRatesSection;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static constants.ErrorMessages.COLUMN_TITLE_NOT_DISPLAYED;
import static constants.ErrorMessages.CURRENCY_INFO_NOT_DISPLAYED;
import static constants.ErrorMessages.BACK_BUTTON_NOT_DISPLAYED;
import static constants.ErrorMessages.HEADER_INCORRECT_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.EXPECTED_PAGE_DID_NOT_OPEN;

@Epic("Epic-1 Регистрация/Авторизация/Безопасность")
@Feature("US-1.1 Регистрация")
@Story("US-1.1.3 Регистрация (Бургерное меню)")
public class ExchangeRatesTest extends BaseTest {

    @BeforeEach
    public void goToExchangeRatesPage() {
        mainPage.clickOnBurgerMenuButton();
        burgerMenuPage.clickOnExchangeRatesButton();
    }

    @DisplayName("C5972674 Проверка формы 'Курсы валют'")
    @Test
    public void exchangeRatesTest() {
        assertAll("Проверка формы 'Курсы валют' на отображение всех элементов",
                () -> assertEquals("Курсы валют", exchangeRatesPage.getExchangeRatesHeaderText(),
                        HEADER_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertTrue(exchangeRatesPage.isColumnCurrencyDisplayed(), COLUMN_TITLE_NOT_DISPLAYED),
                () -> assertTrue(exchangeRatesPage.isColumnBuyDisplayed(), COLUMN_TITLE_NOT_DISPLAYED),
                () -> assertTrue(exchangeRatesPage.isColumnSellDisplayed(), COLUMN_TITLE_NOT_DISPLAYED),
                () -> assertTrue(exchangeRatesPage.isInfoOnUSDDisplayed(), CURRENCY_INFO_NOT_DISPLAYED),
                () -> assertTrue(exchangeRatesPage.isInfoOnEURDisplayed(), CURRENCY_INFO_NOT_DISPLAYED),
                () -> assertTrue(exchangeRatesPage.isBackButtonDisplayed(), BACK_BUTTON_NOT_DISPLAYED));
    }

    @DisplayName("C5972675 Проверка кнопки 'Назад' в разделе 'Курсы валют'")
    @Test
    public void exchangeRatesBackButtonTest() {
        exchangeRatesPage.clickOnBackButton();
        assertEquals("Меню", burgerMenuPage.getBurgerMenuHeaderText(), EXPECTED_PAGE_DID_NOT_OPEN);
    }

    @AfterEach
    public void clickOnBackButton() {
        exchangeRatesPage.restartApp();
    }
}