package epic_1_registrationAuthorizationSecurity.us_1_2_authorization.us_1_2_2_authorizationPINSetting;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static data.UserData.RU_USER_NAME;
import static data.UserData.RU_PHONE_NUMBER;
import static data.UserData.RU_PASSWORD;
import static constants.ErrorMessages.PRIMARY_BUTTON_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT;
import static constants.ErrorMessages.USER_NAME_INCORRECT_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.HEADER_INCORRECT_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.BACK_BUTTON_NOT_DISPLAYED;
import static constants.ErrorMessages.BURGER_MENU_BUTTON_NOT_DISPLAYED;
import static constants.ErrorMessages.INPUT_NOT_DISPLAYED;
import static constants.ErrorMessages.DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.EXPECTED_PAGE_DID_NOT_OPEN;
import static constants.ErrorMessages.ERROR_MESSAGE_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT;
import static constants.ErrorMessages.ERROR_MESSAGE_DISPLAYED;
import static constants.ErrorMessages.INPUT_WAS_NOT_CLEARED;
import static constants.ErrorMessages.NOT_VISIBLE;
import static constants.TestConstants.VALID_PIN;
import static constants.TestConstants.DIFFERENT_VALID_PIN;
import static constants.TestConstants.INVALID_PIN;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Epic("Epic-1 Регистрация/Авторизация/Безопасность")
@Feature("US-1.2 Авторизация")
@Story("US-1.2.2 Авторизация (Установка PIN)")
public class InstallPINTest extends BaseTest {

    @BeforeEach
    public void openAuthorizationFormCreatePIN() {
        mainPage.clickOnLoginButton();
        enterLoginDataPage.chooseLoginTypeByRUPhoneNumber();
        enterLoginDataPage.enterPhoneNumber(RU_PHONE_NUMBER);
        enterLoginDataPage.enterPassword(RU_PASSWORD);
        enterLoginDataPage.clickOnPrimaryButton();
    }

    @Test
    @DisplayName("C5868364 Проверка формы авторизации 'Создайте PIN'")
    public void createPINTest() {
        assertAll("Проверка отображения элементов формы 'Создайте PIN'",
                () -> assertEquals("Авторизация", enterLoginDataPage.getAuthorizationPageHeaderText(),
                        HEADER_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertEquals("Создайте PIN", installPINPage.getPINPageTitleText(),
                        DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertEquals("Введите 6 цифр", installPINPage.getPINPageSubtitleText(),
                        DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertTrue(installPINPage.isPINInputDisplayed(), INPUT_NOT_DISPLAYED),
                () -> assertTrue(installPINPage.isPinPadButtonsDisplayed(), NOT_VISIBLE),
                () -> assertTrue(installPINPage.isBackspaceButtonDisplayed(), "Кнопка 'Стереть'" + NOT_VISIBLE),
                () -> assertTrue(mainPage.isBurgerMenuButtonDisplayed(), BURGER_MENU_BUTTON_NOT_DISPLAYED),
                () -> assertTrue(basePage.isBackButtonDisplayed(), BACK_BUTTON_NOT_DISPLAYED)
                );
        basePage.clickOnBurgerMenuButton();
        assertAll("Проверка отображения элементов бургерного меню",
                () -> assertTrue(burgerMenuPage.isATMsAndBranchesButtonDisplayed(),
                        "Элемент 'Банкоматы и отделения'" + NOT_VISIBLE),
                () -> assertTrue(burgerMenuPage.isExchangeRatesButtonDisplayed(),
                        "Элемент 'Курсы валют'" + NOT_VISIBLE)
        );
    }

    @Test
    @DisplayName("C5868365 Установка PIN")
    public void installPINTest() {
        installPINPage.enterPin(VALID_PIN);
        assertAll("Проверка отображения элементов экрана 'Подтвердите PIN'",
                () -> assertTrue(installPINPage.isPINConfirmPageTitleDisplayed(), EXPECTED_PAGE_DID_NOT_OPEN),
                () -> assertEquals("Авторизация", enterLoginDataPage.getAuthorizationPageHeaderText(),
                        HEADER_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertEquals("Подтвердите PIN", installPINPage.getPINPageTitleText(),
                        DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertEquals("Повторно введите 6 цифр", installPINPage.getPINPageSubtitleText(),
                        DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertTrue(installPINPage.isPINInputDisplayed(), INPUT_NOT_DISPLAYED),
                () -> assertTrue(installPINPage.isPinPadButtonsDisplayed(), NOT_VISIBLE),
                () -> assertTrue(installPINPage.isBackspaceButtonDisplayed(), "Кнопка 'Стереть'" + NOT_VISIBLE),
                () -> assertTrue(mainPage.isBurgerMenuButtonDisplayed(), BURGER_MENU_BUTTON_NOT_DISPLAYED),
                () -> assertTrue(basePage.isBackButtonDisplayed(), BACK_BUTTON_NOT_DISPLAYED)
        );
        basePage.clickOnBurgerMenuButton();
        assertAll("Проверка отображения элементов бургерного меню",
                () -> assertTrue(burgerMenuPage.isATMsAndBranchesButtonDisplayed(),
                        "Элемент 'Банкоматы и отделения'" + NOT_VISIBLE),
                () -> assertTrue(burgerMenuPage.isExchangeRatesButtonDisplayed(),
                        "Элемент 'Курсы валют'" + NOT_VISIBLE)
        );
        basePage.clickOnBackButton();
        installPINPage.enterPin(VALID_PIN);
        assertAll("Проверка отображения элементов экрана 'PIN создан'",
                () -> assertTrue(pinCreatedPage.isPINCreatedImageDisplayed(), EXPECTED_PAGE_DID_NOT_OPEN),
                () -> assertEquals("Авторизация", enterLoginDataPage.getAuthorizationPageHeaderText(),
                        HEADER_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertEquals("PIN создан", pinCreatedPage.getPINCreatedTitleText(),
                        DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertEquals("Вы сможете изменить PIN код в настройках.",
                        pinCreatedPage.getPINCreatedSubtitleText(), DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertTrue(pinCreatedPage.isPrimaryButtonDisplayed(), PRIMARY_BUTTON_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT),
                () -> assertTrue(basePage.isBackButtonDisplayed(), BACK_BUTTON_NOT_DISPLAYED),
                () -> assertTrue(mainPage.isBurgerMenuButtonDisplayed(), BURGER_MENU_BUTTON_NOT_DISPLAYED)
        );
        basePage.clickOnBurgerMenuButton();
        assertAll("Проверка отображения элементов бургерного меню",
                () -> assertTrue(burgerMenuPage.isATMsAndBranchesButtonDisplayed(),
                        "Элемент 'Банкоматы и отделения'" + NOT_VISIBLE),
                () -> assertTrue(burgerMenuPage.isExchangeRatesButtonDisplayed(),
                        "Элемент 'Курсы валют'" + NOT_VISIBLE)
        );
        basePage.clickOnBackButton();
        pinCreatedPage.clickOnPrimaryButton();
        assertEquals(RU_USER_NAME, firstPage.getUserName().getText(), USER_NAME_INCORRECT_OR_NOT_DISPLAYED);
    }

    @Test
    @DisplayName("C5868366 Ввод в поле 'Создайте PIN' менее 6 цифр")
    public void invalidPINCreationTest() {
        installPINPage.enterPin(INVALID_PIN);
        assertFalse(installPINPage.isPINConfirmPageTitleDisplayed(),
                        "Произошел переход на страницу подтверждения пина при неполном введении");
    }

    @Test
    @DisplayName("C5868370 Ввод в поле 'Подтвердите PIN' менее 6 цифр")
    public void invalidPINConfirmTest() {
        installPINPage.enterPin(VALID_PIN);
        assertTrue(installPINPage.isPINConfirmPageTitleDisplayed(), EXPECTED_PAGE_DID_NOT_OPEN);
        installPINPage.enterPin(INVALID_PIN);
        assertFalse(pinCreatedPage.isPINCreatedImageDisplayed(),
                        "Произошел переход на страницу 'PIN создан' при неполном введении PIN");
    }

    @Test
    @DisplayName("C5868387 Несовпадающие PIN в полях 'Создайте PIN' и 'Подтвердите PIN'")
    public void notEqualsPINsErrorTest() {
        installPINPage.enterPin(VALID_PIN);
        assertTrue(installPINPage.isPINConfirmPageTitleDisplayed(), EXPECTED_PAGE_DID_NOT_OPEN);
        installPINPage.enterPin(DIFFERENT_VALID_PIN);
        assertEquals("PIN коды не совпадают", installPINPage.getPinPageErrorText(),
                ERROR_MESSAGE_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT);
    }

    @Test
    @DisplayName("C5868388 Проверка исчезновения ошибки 'PIN-коды не совпадают'")
    public void notEqualsPINsErrorDisappearingTest() {
        installPINPage.enterPin(VALID_PIN);
        installPINPage.enterPin(DIFFERENT_VALID_PIN);
        assertEquals("PIN коды не совпадают", installPINPage.getPinPageErrorText(),
                ERROR_MESSAGE_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT);
        installPINPage.backspaceButtonClick();
        assertAll("Проверка исчезновения сообщения об ошибке и удаления символов",
                () -> assertFalse(installPINPage.isPinPageErrorTextDisplayed(), ERROR_MESSAGE_DISPLAYED),
                () -> assertTrue(installPINPage.isPinInputTextDeleted(),
                        "Символ не удалился")
        );
    }

    @Test
    @DisplayName("C5868389 Проверка реакции кнопок на цифровой клавиатуре при нажатии")
    public void clickPinPadButtonsTest() {
        assertTrue(installPINPage.isClickPinPadButtonsDisplayed(),
                "Нажатия цифровых кнопок" + NOT_VISIBLE + "в поле ввода PIN");
    }

    @Test
    @Disabled("Issue: AFI-2744")
    @DisplayName("C5868391 Проверка работы кнопки 'Назад'")
    public void backButtonTest() {
        installPINPage.enterPin(VALID_PIN);
        assertTrue(installPINPage.isPINConfirmPageTitleDisplayed(), EXPECTED_PAGE_DID_NOT_OPEN);
        installPINPage.enterPin(VALID_PIN);
        assertTrue(pinCreatedPage.isPINCreatedImageDisplayed(), EXPECTED_PAGE_DID_NOT_OPEN);
        basePage.clickOnBackButton();
        assertAll("Проверка отображения PIN после нажатия кнопки 'Назад'",
                () -> assertTrue(installPINPage.isPINCreatePageTitleDisplayed(), EXPECTED_PAGE_DID_NOT_OPEN),
                () -> assertTrue(installPINPage.getPinInputText().isEmpty(), INPUT_WAS_NOT_CLEARED)
        );
        basePage.clickOnBackButton();
        assertTrue(enterLoginDataPage.isEnterDataTitleTextDisplayed(), EXPECTED_PAGE_DID_NOT_OPEN);
    }

    @AfterEach
    public void restartRegistrationPage() {
        mainPage.restartApp();
    }
}