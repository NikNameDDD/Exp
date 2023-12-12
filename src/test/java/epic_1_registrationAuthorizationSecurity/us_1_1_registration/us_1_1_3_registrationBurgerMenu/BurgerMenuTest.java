package epic_1_registrationAuthorizationSecurity.us_1_1_registration.us_1_1_3_registrationBurgerMenu;

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
import static constants.ErrorMessages.BACK_BUTTON_NOT_DISPLAYED;
import static constants.ErrorMessages.IMAGE_NOT_DISPLAYED;
import static constants.ErrorMessages.HEADER_INCORRECT_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.EXPECTED_PAGE_DID_NOT_OPEN;
import static constants.ErrorMessages.PRIMARY_BUTTON_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT;

@Epic("Epic-1 Регистрация/Авторизация/Безопасность")
@Feature("US-1.1 Регистрация")
@Story("US-1.1.3 Регистрация (Бургерное меню)")
public class BurgerMenuTest extends BaseTest {

    @BeforeEach
    public void goToBurgerMenuPage() {
        mainPage.clickOnBurgerMenuButton();
    }

    @DisplayName("C5854358 Проверка кнопки 'Меню' (Бургер)")
    @Test
    public void burgerMenuButtonTest() {
        assertAll("Проверка основного раздела Бургерного меню на отображение всех элементов",
                () -> assertTrue(burgerMenuPage.isATMsAndBranchesButtonDisplayed(),
                        "Не отображается кнопка 'Банкоматы и отделения'"),
                () -> assertTrue(burgerMenuPage.isExchangeRatesButtonDisplayed(),
                        "Не отображается кнопка 'Курсы валют'"),
                () -> assertTrue(burgerMenuPage.isLocalSupportNumberDisplayed(),
                        "Не отображается номер телефона для звонков по России"),
                () -> assertTrue(burgerMenuPage.isInternationalSupportNumberDisplayed(),
                        "Не отображается номер телефона для международных звонков"),
                () -> assertTrue(burgerMenuPage.isBugReportButtonDisplayed(),
                        "Не отображается кнопка 'Отправить отчет об ошибке'"),
                () -> assertTrue(burgerMenuPage.isSetLangDropdownDisplayed(),
                        "Не отображается дропдаун выбора языка приложения"),
                () -> assertTrue(burgerMenuPage.isSetThemeDropdownDisplayed(),
                        "Не отображается дропдаун выбора темы приложения"),
                () -> assertTrue(burgerMenuPage.isBackButtonDisplayed(), BACK_BUTTON_NOT_DISPLAYED));
    }

    @DisplayName("C5888869 Проверка формы 'Разрешите доступ к вашей геопозиции' (Бургер)")
    @Test
    public void allowAccessToGeoPositionPageTest() {
        burgerMenuPage.clickOnATMsAndBranchesButton();
        assertAll("Проверка экрана 'Разрешите доступ к вашей геопозиции'",
                () -> assertEquals("Банкоматы и отделения", allowAccessToGeoPositionPage.getAllowGeoAccessHeaderText(),
                        HEADER_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertEquals("Разрешите доступ к вашей геопозиции", allowAccessToGeoPositionPage.getShortDescriptionText(),
                        DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertEquals("Доступ к вашему местоположению позволит приложению Affinity Bank находить отделения, " +
                                "банкоматы и терминалы рядом с вами.", allowAccessToGeoPositionPage.getLongDescriptionText(),
                        DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertTrue(allowAccessToGeoPositionPage.isAllowGeoAccessImageDisplayed(), IMAGE_NOT_DISPLAYED),
                () -> assertTrue(allowAccessToGeoPositionPage.isPrimaryButtonDisplayed(),
                        PRIMARY_BUTTON_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT),
                () -> assertTrue(allowAccessToGeoPositionPage.isBackButtonDisplayed(), BACK_BUTTON_NOT_DISPLAYED)
        );
    }

    @DisplayName("C5888875 Проверка кнопки 'Назад' (Бургер)")
    @Test
    public void allowAccessToGeoPositionBackButtonTest() {
        burgerMenuPage.clickOnATMsAndBranchesButton();
        burgerMenuPage.clickOnBackButton();
        assertEquals("Меню", burgerMenuPage.getBurgerMenuHeaderText(), EXPECTED_PAGE_DID_NOT_OPEN);
    }

    @AfterEach
    public void goToWelcomePage() {
        burgerMenuPage.restartApp();
    }
}
