package epic_1_registrationAuthorizationSecurity.us_1_1_registration.us_1_1_5_registrationForNonBankClients.step_1_enterPhoneNumber;

import baseTest.BaseTest;
import data.Country;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.PhoneNumberConverter;

import static data.UserData.BY_PHONE_NUMBER;
import static data.UserData.RU_PHONE_NUMBER;
import static constants.ErrorMessages.BACK_BUTTON_NOT_DISPLAYED;
import static constants.ErrorMessages.BURGER_MENU_BUTTON_NOT_DISPLAYED;
import static constants.ErrorMessages.DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.DROPDOWN_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT;
import static constants.ErrorMessages.ERROR_MESSAGE_DISPLAYED;
import static constants.ErrorMessages.ERROR_MESSAGE_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT;
import static constants.ErrorMessages.HEADER_INCORRECT_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.INCORRECT_DROPDOWN_ITEM;
import static constants.ErrorMessages.PRIMARY_BUTTON_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT;
import static constants.ErrorMessages.INPUT_NOT_DISPLAYED;
import static constants.ErrorMessages.INPUT_NOT_DISPLAYED_OR_PLACEHOLDER_INCORRECT;
import static constants.ErrorMessages.PRIMARY_BUTTON_ENABLED;
import static constants.ErrorMessages.PRIMARY_BUTTON_NOT_ENABLED;
import static constants.ErrorMessages.PRIVACY_POLICY_NOTICE_INCORRECT_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Epic-1 Регистрация/Авторизация/Безопасность")
@Feature("US-1.1 Регистрация")
@Story("US-1.1.5 Регистрация (для не клиентов банка)")
public class EnterPhoneNumberNonClientsRegistrationTest extends BaseTest {

    @BeforeEach
    public void openRegistrationPage() {
        mainPage.clickOnSignUpButton();
        enterPhoneForRegistrationPage.clickOnSkipButton();
    }

    @DisplayName("C5870035 Проверка формы регистрации (Для не клиентов банка)")
    @Test
    public void registrationFormDisplayTest() {
        assertAll("Проверка отображения заголовков",
                () -> assertEquals("Регистрация",
                        enterPhoneForRegistrationPage.getRegistrationPageHeaderText(),
                        HEADER_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertTrue(enterPhoneForRegistrationPage.isEnterPhoneNumberTitleDisplayed(),
                        DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertEquals("Введите номер телефона",
                        enterPhoneForRegistrationPage.getEnterPhoneNumberTitleText(),
                        DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED)
        );
        assertAll("Проверка отображения поля для ввода номера",
                () -> assertTrue(enterPhoneForRegistrationPage.isPhoneNumberInputDisplayed(),
                        INPUT_NOT_DISPLAYED),
                () -> assertEquals("+7 xxx xxx xxxx", enterPhoneForRegistrationPage.getPhoneNumberInputText(),
                        INPUT_NOT_DISPLAYED_OR_PLACEHOLDER_INCORRECT)
        );
        assertEquals("Выберите код страны", enterPhoneForRegistrationPage.getSelectCountryCodeDropdownText(),
                DROPDOWN_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT);
        assertAll("Проверка отображения уведомления о конфиденциальности",
                () -> assertTrue(enterPhoneForRegistrationPage.isPrivacyPolicyNoticeDisplayed(),
                        PRIVACY_POLICY_NOTICE_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertEquals("Нажав кнопку “Продолжить”, вы соглашаетесь с Политикой конфиденциальности " +
                                "и даете согласие на сбор, обработку и хранение Ваших персональных данных",
                        enterPhoneForRegistrationPage.getPrivacyPolicyNoticeText(),
                        PRIVACY_POLICY_NOTICE_INCORRECT_OR_NOT_DISPLAYED)
        );
        assertAll("Проверка отображения кнопок",
                () -> assertTrue(enterPhoneForRegistrationPage.isBackButtonDisplayed(), BACK_BUTTON_NOT_DISPLAYED),
                () -> assertFalse(enterPhoneForRegistrationPage.isPrimaryButtonEnabled(), PRIMARY_BUTTON_ENABLED),
                () -> assertEquals("Продолжить", enterPhoneForRegistrationPage.getPrimaryButtonText(),
                        PRIMARY_BUTTON_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT),
                () -> assertTrue(enterPhoneForRegistrationPage.isBurgerMenuButtonDisplayed(), BURGER_MENU_BUTTON_NOT_DISPLAYED)
        );
        enterPhoneForRegistrationPage.clickOnSelectCountryCodeDropdown();
        assertAll("Проверка отображения дропдауна 'Выберите код страны'",
                () -> assertEquals("(+7) Россия", enterPhoneForRegistrationPage.getRussianNumberDropdownItemText(),
                        INCORRECT_DROPDOWN_ITEM),
                () -> assertEquals("(+375) Р. Беларусь", enterPhoneForRegistrationPage.getBelarusianNumberDropdownItemText(),
                        INCORRECT_DROPDOWN_ITEM)
        );
        enterPhoneForRegistrationPage.clickOnBelarusianNumberDropdownItem();
        assertAll("Проверка экрана ввода телефона после выбора варианта регистрации с белорусским номером",
                () -> assertTrue(enterPhoneForRegistrationPage.isPhoneNumberInputDisplayed(),
                        INPUT_NOT_DISPLAYED),
                () -> assertEquals("+375 xx xxx xx xx", enterPhoneForRegistrationPage.getPhoneNumberInputText(),
                        INPUT_NOT_DISPLAYED_OR_PLACEHOLDER_INCORRECT)
        );
        enterPhoneForRegistrationPage.switchCountryCodeTo(Country.RU);
        assertAll("Проверка экрана ввода телефона после выбора варианта регистрации с российским номером",
                () -> assertTrue(enterPhoneForRegistrationPage.isPhoneNumberInputDisplayed(),
                        INPUT_NOT_DISPLAYED),
                () -> assertEquals("+7 xxx xxx xxxx", enterPhoneForRegistrationPage.getPhoneNumberInputText(),
                        INPUT_NOT_DISPLAYED_OR_PLACEHOLDER_INCORRECT)
        );
    }

    @DisplayName("C5863456 Проверка ввода в поле номер телефона пустого значения (Для не клиентов банка)")
    @Test
    public void emptyPhoneNumberInputTest() {
        assertTrue(enterPhoneForRegistrationPage.isEnterPhoneNumberTitleDisplayed(),
                DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED);
        enterPhoneForRegistrationPage.clickOnPrivacyPolicyNotice();
        basePage.clickOnPrimaryButton();
        assertTrue(enterPhoneForRegistrationPage.isEnterPhoneNumberTitleDisplayed(),
                DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED);
        assertFalse(enterPhoneForRegistrationPage.isPrimaryButtonEnabled(), PRIMARY_BUTTON_ENABLED);
    }

    @DisplayName("C5865004 Проверка ввода в поле номер телефона неполного значение номера телефона (Для не клиентов банка)")
    @Test
    public void incompletePhoneNumberNonClientsRegistrationTest() {
        assertTrue(enterPhoneForRegistrationPage.isEnterPhoneNumberTitleDisplayed(),
                DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED);
        enterPhoneForRegistrationPage.enterPhoneNumber("999456");
        assertAll("Проверка экрана ввода телефона после ввода неполного значения российского номера",
                () -> assertEquals("+7 999 456 xxxx", enterPhoneForRegistrationPage.getPhoneNumberInputText(),
                        VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED),
                () -> assertFalse(enterPhoneForRegistrationPage.isPrimaryButtonEnabled(), PRIMARY_BUTTON_ENABLED)
        );
        enterPhoneForRegistrationPage.switchCountryCodeTo(Country.BY);
        enterPhoneForRegistrationPage.enterPhoneNumber("299456");
        assertAll("Проверка экрана ввода телефона после ввода неполного значения белорусского номера",
                () -> assertEquals("+375 29 945 6x xx", enterPhoneForRegistrationPage.getPhoneNumberInputText(),
                        VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED),
                () -> assertFalse(enterPhoneForRegistrationPage.isPrimaryButtonEnabled(), PRIMARY_BUTTON_ENABLED)
        );
    }

    @DisplayName("C5865010 Проверка не сохранения данных в поле телефона при нажатии кнопки 'Назад' (Для не клиентов)")
    @Test
    public void phoneNonSaveAfterBackButtonClickTest() {
        enterPhoneForRegistrationPage.enterPhoneNumber(RU_PHONE_NUMBER);
        assertEquals(PhoneNumberConverter.convertPhoneNumber(RU_PHONE_NUMBER),
                enterPhoneForRegistrationPage.getPhoneNumberInputText(),
                VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED);
        enterPhoneForRegistrationPage.clickOnBackButton();
        mainPage.clickOnSignUpButton();
        enterPhoneForRegistrationPage.clickOnSkipButton();
        assertEquals("+7 xxx xxx xxxx", enterPhoneForRegistrationPage.getPhoneNumberInputText(),
                VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED);
        enterPhoneForRegistrationPage.switchCountryCodeTo(Country.BY);
        enterPhoneForRegistrationPage.enterPhoneNumber(BY_PHONE_NUMBER);
        assertEquals(PhoneNumberConverter.convertPhoneNumber(BY_PHONE_NUMBER),
                enterPhoneForRegistrationPage.getPhoneNumberInputText(),
                VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED);
        enterPhoneForRegistrationPage.clickOnBackButton();
        mainPage.clickOnSignUpButton();
        enterPhoneForRegistrationPage.clickOnSkipButton();
        assertEquals("+7 xxx xxx xxxx", enterPhoneForRegistrationPage.getPhoneNumberInputText(),
                VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED);
    }

    @DisplayName("C5865009 Проверка ввода зарегистрированного номера телефона (Для не клиентов банка)")
    @Test
    @Disabled("Issue: AFI-2668")
    public void registeredPhoneNumberTest() {
        databaseHandler.resetClientStatusToNotActive(RU_PHONE_NUMBER);
        enterPhoneForRegistrationPage.enterPhoneNumber(RU_PHONE_NUMBER);
        enterPhoneForRegistrationPage.clickOnPrimaryButton();
        assertAll("Проверка экрана ввода телефона после ввода российского номера уже зарегистрированного пользователя",
                () -> assertEquals(PhoneNumberConverter.convertPhoneNumber(RU_PHONE_NUMBER),
                        enterPhoneForRegistrationPage.getPhoneNumberInputText(),
                        VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED),
                () -> assertEquals("Пользователь с таким номером телефона уже зарегистрирован в системе. " +
                                "Позвоните по номеру телефона +7 321 321 5690 или обратитесь в ближайшее отделение Банка",
                        enterPhoneForRegistrationPage.getExistingClientErrorMessageText(),
                        ERROR_MESSAGE_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT),
                () -> assertFalse(enterPhoneForRegistrationPage.isPrimaryButtonEnabled(),
                        PRIMARY_BUTTON_ENABLED)
        );
        enterPhoneForRegistrationPage.clearPhoneNumberInput();
        assertFalse(enterPhoneForRegistrationPage.isExistingClientErrorMessageDisplayed(),
                ERROR_MESSAGE_DISPLAYED);
        enterPhoneForRegistrationPage.switchCountryCodeTo(Country.BY);
        databaseHandler.resetClientStatusToNotActive(BY_PHONE_NUMBER);
        enterPhoneForRegistrationPage.enterPhoneNumber(BY_PHONE_NUMBER);
        enterPhoneForRegistrationPage.clickOnPrimaryButton();
        assertAll("Проверка экрана ввода телефона после ввода белорусского номера уже зарегистрированного пользователя",
                () -> assertEquals(PhoneNumberConverter.convertPhoneNumber(BY_PHONE_NUMBER),
                        enterPhoneForRegistrationPage.getPhoneNumberInputText(),
                        VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED),
                () -> assertEquals("Пользователь с таким номером телефона уже зарегистрирован в системе. " +
                                "Позвоните по номеру телефона +7 321 321 5690 или обратитесь в ближайшее отделение Банка",
                        enterPhoneForRegistrationPage.getExistingClientErrorMessageText(),
                        ERROR_MESSAGE_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT),
                () -> assertFalse(enterPhoneForRegistrationPage.isPrimaryButtonEnabled(),
                        PRIMARY_BUTTON_ENABLED)
        );
        enterPhoneForRegistrationPage.clearPhoneNumberInput();
        assertFalse(enterPhoneForRegistrationPage.isExistingClientErrorMessageDisplayed(),
                ERROR_MESSAGE_DISPLAYED);
    }

    @DisplayName("C5865007, C5863448 Проверка ввода невалидных символов для номера телефона (Для не клиентов банка)")
    @ParameterizedTest
    @ValueSource(strings = {"space", "dot, comma, dash"})
    public void invalidSymbolsInPhoneNumberInputTest(String androidKey) {
        enterPhoneForRegistrationPage.fillPhoneNumberInputWithInvalidSymbols(androidKey);
        assertAll("Проверка экрана ввода телефона после попытки ввода невалидных символов",
                () -> assertEquals("+7 xxx xxx xxxx", enterPhoneForRegistrationPage.getPhoneNumberInputText(),
                        VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED),
                () -> assertFalse(enterPhoneForRegistrationPage.isPrimaryButtonEnabled(), PRIMARY_BUTTON_ENABLED)
        );
    }

    @DisplayName("C5863460 Проверка ввода в поле номера телефона значение, превышающее допустимое (Для не клиентов банка)")
    @Test
    public void exceedingPhoneNumberNonClientsRegistrationTest() {
        enterPhoneForRegistrationPage.enterPhoneNumber(RU_PHONE_NUMBER + "33");
        assertAll("Проверка экрана ввода телефона после ввода значения российского номера, превышающего допустимое",
                () -> assertEquals(PhoneNumberConverter.convertPhoneNumber(RU_PHONE_NUMBER),
                        enterPhoneForRegistrationPage.getPhoneNumberInputText(),
                        VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED),
                () -> assertTrue(enterPhoneForRegistrationPage.isPrimaryButtonEnabled(), PRIMARY_BUTTON_NOT_ENABLED)
        );
        enterPhoneForRegistrationPage.switchCountryCodeTo(Country.BY);
        enterPhoneForRegistrationPage.enterPhoneNumber(BY_PHONE_NUMBER + "33");
        assertAll("Проверка экрана ввода телефона после ввода значения белорусского номера, превышающего допустимое",
                () -> assertEquals(PhoneNumberConverter.convertPhoneNumber(BY_PHONE_NUMBER),
                        enterPhoneForRegistrationPage.getPhoneNumberInputText(),
                        VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED),
                () -> assertTrue(enterPhoneForRegistrationPage.isPrimaryButtonEnabled(), PRIMARY_BUTTON_NOT_ENABLED)
        );
    }

    @AfterEach
    public void burgerMenuBackButtonClick() {
        enterPhoneForRegistrationPage.restartApp();
    }
}
