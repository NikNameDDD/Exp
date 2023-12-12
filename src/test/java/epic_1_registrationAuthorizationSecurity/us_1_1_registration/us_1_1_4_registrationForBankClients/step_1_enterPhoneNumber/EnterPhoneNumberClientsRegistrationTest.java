package epic_1_registrationAuthorizationSecurity.us_1_1_registration.us_1_1_4_registrationForBankClients.step_1_enterPhoneNumber;

import baseTest.BaseTest;
import data.Country;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.PhoneNumberConverter;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static data.UserData.BY_PHONE_NUMBER;
import static data.UserData.RU_PHONE_NUMBER;
import static constants.ErrorMessages.DROPDOWN_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT;
import static constants.ErrorMessages.INCORRECT_DROPDOWN_ITEM;
import static constants.ErrorMessages.INPUT_NOT_DISPLAYED;
import static constants.ErrorMessages.INPUT_NOT_DISPLAYED_OR_PLACEHOLDER_INCORRECT;
import static constants.ErrorMessages.HEADER_INCORRECT_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.BACK_BUTTON_NOT_DISPLAYED;
import static constants.ErrorMessages.BURGER_MENU_BUTTON_NOT_DISPLAYED;
import static constants.ErrorMessages.PRIMARY_BUTTON_NOT_ENABLED;
import static constants.ErrorMessages.PRIMARY_BUTTON_ENABLED;
import static constants.ErrorMessages.EXPECTED_PAGE_DID_NOT_OPEN;
import static constants.ErrorMessages.PRIMARY_BUTTON_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT;
import static constants.ErrorMessages.PRIVACY_POLICY_NOTICE_INCORRECT_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED;

@Epic("Epic-1 Регистрация/Авторизация/Безопасность")
@Feature("US-1.1 Регистрация")
@Story("US-1.1.4 Регистрация (для клиентов банка)")
public class EnterPhoneNumberClientsRegistrationTest extends BaseTest {

    @BeforeEach
    public void openRegistrationPage() {
        mainPage.clickOnSignUpButton();
        enterPhoneForRegistrationPage.clickOnSkipButton();
    }

    @Test
    @DisplayName("C5869889 Проверка формы регистрации (для клиентов банка)")
    public void clientsRegistrationFormTest() {
        assertAll("Проверка отображения элементов формы регистрации (для клиентов банка)",
                () -> assertEquals("Регистрация",
                        enterPhoneForRegistrationPage.getRegistrationPageHeaderText(),
                        HEADER_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertEquals("Введите номер телефона",
                        enterPhoneForRegistrationPage.getEnterPhoneNumberTitleText(),
                        DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertEquals("+7 xxx xxx xxxx",
                        enterPhoneForRegistrationPage.getPhoneNumberInputText(),
                        INPUT_NOT_DISPLAYED_OR_PLACEHOLDER_INCORRECT),
                () -> assertEquals("Выберите код страны",
                        enterPhoneForRegistrationPage.getSelectCountryCodeDropdownText(),
                        DROPDOWN_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT),
                () -> assertEquals("Нажав кнопку “Продолжить”, вы соглашаетесь с Политикой конфиденциальности " +
                                "и даете согласие на сбор, обработку и хранение Ваших персональных данных",
                        enterPhoneForRegistrationPage.getPrivacyPolicyNoticeText(),
                        PRIVACY_POLICY_NOTICE_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertTrue(enterPhoneForRegistrationPage.isBackButtonDisplayed(), BACK_BUTTON_NOT_DISPLAYED),
                () -> assertEquals("Продолжить", enterPhoneForRegistrationPage.getPrimaryButtonText(),
                        PRIMARY_BUTTON_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT),
                () -> assertTrue(mainPage.isBurgerMenuButtonDisplayed(), BURGER_MENU_BUTTON_NOT_DISPLAYED)
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

    @Test
    @DisplayName("C5854386 Проверка ввода в поле номер телефона пустого значения (Для клиентов банка)")
    public void emptyNumberTest() {
        assertFalse(enterPhoneForRegistrationPage.isPrimaryButtonEnabled(), PRIMARY_BUTTON_ENABLED);
        enterPhoneForRegistrationPage.switchCountryCodeTo(Country.BY);
        assertFalse(enterPhoneForRegistrationPage.isPrimaryButtonEnabled(), PRIMARY_BUTTON_ENABLED);
    }

    @Test
    @DisplayName("C5854397 Проверка ввода в поле номер телефона " +
            "неполного значения номера телефона (Для клиентов банка)")
    public void incompletePhoneNumberClientsRegistrationTest() {
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

    @Test
    @DisplayName("C5854394 Проверка ввода в поле номер телефона " +
            "значение более 10 цифр (Для клиентов банка)")
    public void exceedingPhoneNumberClientsRegistrationTest() {
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

    @Test
    @DisplayName("C5973760 Проверка кнопки 'Политика конфиденциальности' (Для клиентов банка)")
    public void privacyPolicyTitleTest() {
        enterPhoneForRegistrationPage.clickOnPrivacyPolicyNotice();
        assertEquals("Политика конфиденциальности", privacyPolicyPage.getPrivacyPolicyTitleText(),
                EXPECTED_PAGE_DID_NOT_OPEN);
        privacyPolicyPage.clickOnBackButton();
    }

    @AfterEach
    public void closeRegistrationPage() {
        enterPhoneForRegistrationPage.restartApp();
    }
}