package epic_1_registrationAuthorizationSecurity.us_1_2_authorization.us_1_2_2_authorizationPINSetting.us_1_2_1_authorization.authorizationFormCheck;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static constants.ErrorMessages.BUTTON_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT;
import static constants.ErrorMessages.DOES_NOT_MATCH_WITH_EXPECTED;
import static constants.ErrorMessages.DROPDOWN_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT;
import static constants.ErrorMessages.NOT_VISIBLE;
import static constants.ErrorMessages.PRIMARY_BUTTON_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT;
import static constants.ErrorMessages.INPUT_NOT_DISPLAYED_OR_PLACEHOLDER_INCORRECT;
import static constants.ErrorMessages.VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.VISIBLE;
import static data.UserData.RU_PASSWORD;
import static data.UserData.RU_PHONE_NUMBER;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static constants.ErrorMessages.DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.INCORRECT_DROPDOWN_ITEM;
import static constants.ErrorMessages.HEADER_INCORRECT_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.BACK_BUTTON_NOT_DISPLAYED;
import static constants.ErrorMessages.BURGER_MENU_BUTTON_NOT_DISPLAYED;
import static constants.ErrorMessages.PASSWORD_VISIBLE_TOGGLE_NOT_DISPLAYED;

@Epic("Epic-1 Регистрация/Авторизация/Безопасность")
@Feature("US-1.2 Авторизация")
@Story("US-1.2.1 Авторизация (Проверка формы)")
public class AuthorizationFormTest extends BaseTest {

    @BeforeEach
    public void openAuthorizationPage() {
        mainPage.startApp();
        mainPage.clickOnLoginButton();
    }

    @Test
    @DisplayName("C5867580 Проверка формы 'Авторизация'")
    @Disabled("AFI-2797")
    public void authorizationPageTest() {
        assertAll("Проверка формы 'Авторизация' на отображение всех элементов",
                () -> assertEquals("Авторизация", enterLoginDataPage.getAuthorizationPageHeaderText(),
                        HEADER_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertEquals("Введите данные для входа", enterLoginDataPage.getEnterDataTitleText(),
                        DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertEquals("Номер телефона", enterLoginDataPage.getSelectLoginTypeDropdownText(),
                        DROPDOWN_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT),
                () -> assertEquals("Номер телефона", enterLoginDataPage.getPhoneNumberInputText(),
                        INPUT_NOT_DISPLAYED_OR_PLACEHOLDER_INCORRECT),
                () -> assertEquals("Пароль", enterLoginDataPage.getPasswordInputText(),
                        INPUT_NOT_DISPLAYED_OR_PLACEHOLDER_INCORRECT),
                () -> assertEquals("Забыли пароль?", enterLoginDataPage.getForgotPasswordButtonText(),
                        BUTTON_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT),
                () -> assertEquals("Войти", enterLoginDataPage.getPrimaryButtonText(),
                        PRIMARY_BUTTON_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT),
                () -> assertTrue(basePage.isBackButtonDisplayed(), BACK_BUTTON_NOT_DISPLAYED),
                () -> assertTrue(basePage.isBurgerMenuButtonDisplayed(), BURGER_MENU_BUTTON_NOT_DISPLAYED),
                () -> assertTrue(enterLoginDataPage.isPasswordVisibleToggleDisplayed(),
                        PASSWORD_VISIBLE_TOGGLE_NOT_DISPLAYED)
        );
        enterLoginDataPage.clickOnSelectLoginTypeDropdown();
        assertAll("Проверка отображения элементов дропдауна выбора кода номера телефона",
                () -> assertEquals("Номер телефона:\n(+7) Россия",
                        enterLoginDataPage.getLoginTypeByRUPhoneNumberText(), INCORRECT_DROPDOWN_ITEM),
                () -> assertEquals("Номер телефона:\n(+375) Р. Беларусь",
                        enterLoginDataPage.getLoginTypeByBYPhoneNumberText(), INCORRECT_DROPDOWN_ITEM),
                () -> assertEquals("Номер паспорта", enterLoginDataPage.getLoginTypeByPassportText(),
                        INCORRECT_DROPDOWN_ITEM)
        );
    }

    @Test
    @DisplayName("C5867583 Проверка работы Dropdown")
    public void dropDownTest() {
        enterLoginDataPage.clickOnSelectLoginTypeDropdown();
        assertAll("Проверка отображения элементов дропдауна выбора кода номера телефона",
                () -> assertEquals("Номер телефона:\n(+7) Россия",
                        enterLoginDataPage.getLoginTypeByRUPhoneNumberText(), INCORRECT_DROPDOWN_ITEM),
                () -> assertEquals("Номер телефона:\n(+375) Р. Беларусь",
                        enterLoginDataPage.getLoginTypeByBYPhoneNumberText(), INCORRECT_DROPDOWN_ITEM),
                () -> assertEquals("Номер паспорта", enterLoginDataPage.getLoginTypeByPassportText(),
                        INCORRECT_DROPDOWN_ITEM)
        );
        enterLoginDataPage.clickLoginTypeByPassport();
        assertAll("Проверка отображения элементов дропдауна выбора кода номера телефона",
                () -> assertTrue(enterLoginDataPage.isPassportNumberInputDisplayed(),
                        "Поле ввода паспорта" + NOT_VISIBLE),
                () -> assertTrue(enterLoginDataPage.isPasswordInputDisplayed(),
                        "Поле ввода пароля" + NOT_VISIBLE)
        );
        enterLoginDataPage.chooseLoginTypeByRUPhoneNumber();
        assertAll("Проверка отображения элементов дропдауна выбора кода номера телефона",
                () -> assertTrue(enterLoginDataPage.isPhoneNumberInputDisplayed(),
                        "Поле ввода телефона" + NOT_VISIBLE),
                () -> assertTrue(enterLoginDataPage.isPasswordInputDisplayed(),
                        "Поле ввода пароля" + NOT_VISIBLE)
        );
    }

    @Test
    @DisplayName("C5867534 Проверка появления телефонной клавиатуры, курсора, " +
            "лэйбла при нажатии на пустое поле 'Номер телефона'/'Номер паспорта', 'Пароль'")
    public void inputFieldsKeyboardTest() {
        enterLoginDataPage.clickPhoneNumberInputField();
        assertAll("Проверка отображения лейбла над полем ввода 'Номер телефона', плейсхолдера '+7 xxx xxx xxxx', " +
                        "курсора ввода текста и телефонной клавиатуры",
                () -> assertTrue(enterLoginDataPage.isPhoneNumberLabelTextDisplayed(),
                        "Лейбл над полем ввода 'Номер телефона'" + NOT_VISIBLE),
                () -> assertEquals("+7 xxx xxx xxxx", enterLoginDataPage.getPhoneNumberInputText(),
                        "Плейсхолдер поля ввода 'Номер телефона'" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertTrue(enterLoginDataPage.isPhoneNumberInputFieldCursorDisplayed(),
                        "Курсор ввода текста" + NOT_VISIBLE),
                () -> assertTrue(basePage.isDeviceKeyboardShown(), "Телефонная клавиатура" + NOT_VISIBLE)
        );
        enterLoginDataPage.chooseLoginTypeByBYPhoneNumber();
        enterLoginDataPage.clickPhoneNumberInputField();
        assertAll("Проверка отображения лейбла над полем ввода 'Номер телефона', плейсхолдера '+375 xx xxx xx xx', " +
                        "курсора ввода текста и телефонной клавиатуры",
                () -> assertTrue(enterLoginDataPage.isPhoneNumberLabelTextDisplayed(),
                        "Лейбл над полем ввода 'Номер телефона'" + NOT_VISIBLE),
                () -> assertEquals("+375 xx xxx xx xx", enterLoginDataPage.getPhoneNumberInputText(),
                        "Плейсхолдер поля ввода 'Номер телефона'" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertTrue(enterLoginDataPage.isPhoneNumberInputFieldCursorDisplayed(),
                        "Курсор ввода текста" + NOT_VISIBLE),
                () -> assertTrue(basePage.isDeviceKeyboardShown(), "Телефонная клавиатура" + NOT_VISIBLE)
        );
        enterLoginDataPage.chooseLoginTypeByPassport();
        enterLoginDataPage.clickPassportInputField();
        assertAll("Проверка отображения лейбла над полем ввода 'Номер паспорта', плейсхолдера 'Номер паспорта', " +
                        "курсора ввода текста и телефонной клавиатуры",
                () -> assertTrue(enterLoginDataPage.isPasswordOrPassportLabelTextDisplayed(),
                        "Лейбл над полем ввода 'Номер паспорта'" + NOT_VISIBLE),
                () -> assertTrue(enterLoginDataPage.getTextFromPassportField().isEmpty(),
                        "Плейсхолдер поля ввода 'Номер паспорта'" + VISIBLE),
                () -> assertTrue(enterLoginDataPage.isPassportInputFieldCursorDisplayed(),
                        "Курсор ввода текста" + NOT_VISIBLE),
                () -> assertTrue(basePage.isDeviceKeyboardShown(), "Телефонная клавиатура" + NOT_VISIBLE)
        );
        enterLoginDataPage.chooseLoginTypeByRUPhoneNumber();
        enterLoginDataPage.clickPasswordInputField();
        assertAll("Проверка отображения лейбла над полем ввода 'Пароль', плейсхолдера 'Пароль', " +
                        "курсора ввода текста и телефонной клавиатуры",
                () -> assertTrue(enterLoginDataPage.isPasswordOrPassportLabelTextDisplayed(),
                        "Лейбл над полем ввода 'Пароль'" + NOT_VISIBLE),
                () -> assertTrue(enterLoginDataPage.getPasswordInputText().isEmpty(),
                        "Плейсхолдер поля ввода 'Пароль'" + VISIBLE),
                () -> assertTrue(enterLoginDataPage.isPasswordInputFieldCursorDisplayed(),
                        "Курсор ввода текста" + NOT_VISIBLE),
                () -> assertTrue(basePage.isDeviceKeyboardShown(), "Телефонная клавиатура" + NOT_VISIBLE)
        );
    }

    @Test
    @DisplayName("C5867554 Проверка работы кнопки 'Назад' на телефонной клавиатуре")
    public void backButtonTest() {
        enterLoginDataPage.clickPhoneNumberInputField();
        basePage.clickOnDeviceBackButton();
        assertFalse(basePage.isDeviceKeyboardShown(), "Телефонная клавиатура" + VISIBLE);
        enterLoginDataPage.clickPasswordInputField();
        assertAll("Проверка отображения лейбла над полем ввода 'Пароль', плейсхолдера 'Пароль', " +
                        "курсора ввода текста и телефонной клавиатуры",
                () -> assertTrue(enterLoginDataPage.isPasswordOrPassportLabelTextDisplayed(),
                        "Лейбл над полем ввода 'Пароль'" + NOT_VISIBLE),
                () -> assertTrue(enterLoginDataPage.getPasswordInputText().isEmpty(),
                        "Плейсхолдер поля ввода 'Пароль'" + VISIBLE),
                () -> assertTrue(enterLoginDataPage.isPasswordInputFieldCursorDisplayed(),
                        "Курсор ввода текста" + NOT_VISIBLE),
                () -> assertTrue(basePage.isDeviceKeyboardShown(), "Телефонная клавиатура" + NOT_VISIBLE)
        );
        enterLoginDataPage.clickAnywhere();
        assertAll("Проверка отображения курсора ввода текста и телефонной клавиатуры после нажатия в любое место " +
                        "кроме полей ввода",
                () -> assertFalse(enterLoginDataPage.isPhoneNumberInputFieldCursorDisplayed(),
                        "Курсор ввода текста" + VISIBLE),
                () -> assertFalse(basePage.isDeviceKeyboardShown(), "Телефонная клавиатура" + VISIBLE)
        );
    }

    @Test
    @DisplayName("C5872229 Проверка исчезновения ошибки 'Неверный логин или пароль'")
    public void errorMessagesTest() {
        enterLoginDataPage.enterPhoneNumber(RU_PHONE_NUMBER);
        enterLoginDataPage.enterPassword("}invalid_password123");
        enterLoginDataPage.clickOnPrimaryButton();
        assertTrue(enterLoginDataPage.isInvalidPasswordOrLoginMessageDisplayed(),
                "Сообщения об ошибке" + NOT_VISIBLE);
        enterLoginDataPage.clearPasswordInputField().clearPhoneInputField();
        assertFalse(enterLoginDataPage.isInvalidPasswordOrLoginMessageDisplayed());
    }

    @Test
    @DisplayName("C5867585 Проверка появления и работы кнопки 'Посмотреть пароль' в поле 'Пароль'")
    public void seePasswordButtonTest() {
        enterLoginDataPage.enterPassword(RU_PASSWORD);
        assertAll("Проверка отображения введенных символов поле ввода пароля и кнопки 'Посмотреть пароль'",
                () -> assertEquals(dataGenerator.generateDots(RU_PASSWORD.length()), enterLoginDataPage.getPasswordInputText(),
                        VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED),
                () -> assertTrue(enterLoginDataPage.isPasswordVisibleToggleDisplayed(),
                        PASSWORD_VISIBLE_TOGGLE_NOT_DISPLAYED)
        );
        enterLoginDataPage.toggleVisiblePasswordClick();
        assertEquals(RU_PASSWORD, enterLoginDataPage.getPasswordInputText(),
                VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED);
        enterLoginDataPage.toggleVisiblePasswordClick();
        assertEquals(dataGenerator.generateDots(RU_PASSWORD.length()), enterLoginDataPage.getPasswordInputText(),
                VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED);
    }

    @AfterEach
    public void setAfterEach() {
        enterLoginDataPage.closeApp();
    }
}