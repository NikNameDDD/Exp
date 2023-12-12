package epic_1_registrationAuthorizationSecurity.us_1_1_registration.us_1_1_5_registrationForNonBankClients.step_2_enterSMSCode;

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
import org.junit.jupiter.params.provider.MethodSource;
import utils.PhoneNumberConverter;

import java.util.stream.Stream;

import static constants.ErrorMessages.BACK_BUTTON_NOT_DISPLAYED;
import static constants.ErrorMessages.BURGER_MENU_BUTTON_NOT_DISPLAYED;
import static constants.ErrorMessages.DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.ERROR_MESSAGE_DISPLAYED;
import static constants.ErrorMessages.ERROR_MESSAGE_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT;
import static constants.ErrorMessages.HEADER_INCORRECT_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.INPUT_WAS_NOT_CLEARED;
import static constants.ErrorMessages.PRIMARY_BUTTON_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT;
import static constants.ErrorMessages.INPUT_NOT_DISPLAYED;
import static constants.ErrorMessages.PRIMARY_BUTTON_ENABLED;
import static constants.ErrorMessages.PRIMARY_BUTTON_NOT_ENABLED;
import static constants.ErrorMessages.PROGRESS_BAR_NOT_DISPLAYED;
import static constants.ErrorMessages.EXPECTED_PAGE_DID_NOT_OPEN;
import static constants.ErrorMessages.TITLE_INCORRECT_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.DataGenerator.generatePhoneNumber;

@Epic("Epic-1 Регистрация/Авторизация/Безопасность")
@Feature("US-1.1 Регистрация")
@Story("US-1.1.5 Регистрация (для не клиентов банка)")
public class EnterSMSCodeNonClientsRegistrationTest extends BaseTest {
    private static final String ruPhone = generatePhoneNumber(Country.RU);
    private static final String byPhone = generatePhoneNumber(Country.BY);
    private final String incorrectSMSCode = "000000";

    static Stream<String> argsProviderFactory() {
        return Stream.of(ruPhone, byPhone);
    }

    @BeforeEach
    public void goToEnterSMSCodePage() {
        mainPage.clickOnSignUpButton();
        enterPhoneForRegistrationPage.clickOnSkipButton();
    }

    @ParameterizedTest
    @DisplayName("C5865011 Проверка ввода в поле код из SMS валидного кода (для не клиентов банка)")
    @MethodSource("argsProviderFactory")
    public void correctSMSCodeTest(String phone) {
        enterPhoneForRegistrationPage.switchCountryCodeTo(phone);
        enterPhoneForRegistrationPage.enterPhoneNumber(phone);
        enterPhoneForRegistrationPage.clickOnPrimaryButton();
        enterSMSCodePage.enterSMSCode(databaseHandler.getSMSCode(phone));
        enterSMSCodePage.clickOnPrimaryButton();
        assertEquals("Заполните следующую информацию", enterDataPage.getEnterDataTitleText(),
                EXPECTED_PAGE_DID_NOT_OPEN);
        enterSMSCodePage.clickOnDeviceBackButton();
    }

    @DisplayName("C5865012, C5865021 Проверка двукратного ввода в поле код из SMS неправильного кода (для не клиентов банка)")
//    @ParameterizedTest
    @Test
    @Disabled("Issues: AFI-2704, AFI-2707")
    //Аннотация @Test сохранена временно до устранения багов для того, чтобы тест в Allure-отчете был виден как Disabled.
    //Если вместо @Test указывать @ParameterizedTest, тест не будет виден в Allure-отчете и в Run.
    //После устранения багов удалить аннотацию @Test, раскомментировать аннотацию @ParameterizedTest.
    public void incorrectSMSCode2TimesTest() {
        enterPhoneForRegistrationPage.switchCountryCodeTo(ruPhone);
        enterPhoneForRegistrationPage.enterPhoneNumber(ruPhone);
        enterPhoneForRegistrationPage.clickOnPrimaryButton();
        enterSMSCodePage.enterSMSCode(incorrectSMSCode);
        assertEquals(incorrectSMSCode, enterSMSCodePage.getSMSCodeInputText(),
                VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED);
        enterSMSCodePage.clickOnPrimaryButton();
        assertAll("Проверка экрана после попытки ввести неверный смс-код первый раз",
                () -> assertEquals("Неверный код, у вас осталось 2 попытки",
                        enterSMSCodePage.getIncorrectSMSCodeErrorMessageText(),
                        ERROR_MESSAGE_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT),
                () -> assertTrue(enterSMSCodePage.isPrimaryButtonEnabled(), PRIMARY_BUTTON_NOT_ENABLED)
        );
        enterSMSCodePage.clearSMSCodeInput();
        assertAll("Проверка экрана после очистки поля ввода смс-кода",
                () -> assertTrue(enterSMSCodePage.getSMSCodeInputText().isEmpty(),
                        INPUT_WAS_NOT_CLEARED),
                () -> assertFalse(enterSMSCodePage.isIncorrectSMSCodeErrorMessageDisplayed(),
                        ERROR_MESSAGE_DISPLAYED)
        );
        enterSMSCodePage.enterSMSCode(incorrectSMSCode);
        assertEquals(incorrectSMSCode, enterSMSCodePage.getSMSCodeInputText(),
                VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED);
        enterSMSCodePage.clickOnPrimaryButton();
        assertAll("Проверка экрана после попытки ввести неверный смс-код второй раз",
                () -> assertEquals("Неверный код, у вас осталась 1 попытка",
                        enterSMSCodePage.getIncorrectSMSCodeErrorMessageText(),
                        ERROR_MESSAGE_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT),
                () -> assertTrue(enterSMSCodePage.isPrimaryButtonEnabled(), PRIMARY_BUTTON_NOT_ENABLED)
        );
        enterSMSCodePage.clearSMSCodeInput();
        assertAll("Проверка экрана после очистки поля ввода смс-кода",
                () -> assertTrue(enterSMSCodePage.getSMSCodeInputText().isEmpty(),
                        INPUT_WAS_NOT_CLEARED),
                () -> assertFalse(enterSMSCodePage.isIncorrectSMSCodeErrorMessageDisplayed(),
                        ERROR_MESSAGE_DISPLAYED)
        );
    }

    @Test
    @DisplayName("C5865022 Проверка трехкратного ввода в поле код из SMS неправильного кода (для не клиентов банка)")
    @Disabled("Issues: AFI-2588, AFI-2704, AFI-2707, AFI-2708, AFI-2709")
    public void incorrectSMSCode3TimesTest() {
        enterPhoneForRegistrationPage.switchCountryCodeTo(byPhone);
        enterPhoneForRegistrationPage.enterPhoneNumber(byPhone);
        enterPhoneForRegistrationPage.clickOnPrimaryButton();
        enterSMSCodePage.enterSMSCode(incorrectSMSCode);
        enterSMSCodePage.clickOnPrimaryButton();
        assertAll("Проверка экрана после попытки ввести неверный смс-код первый раз",
                () -> assertEquals("Неверный код, у вас осталось 2 попытки",
                        enterSMSCodePage.getIncorrectSMSCodeErrorMessageText(),
                        ERROR_MESSAGE_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT),
                () -> assertTrue(enterSMSCodePage.isPrimaryButtonEnabled(), PRIMARY_BUTTON_NOT_ENABLED)
        );
        enterSMSCodePage.clickOnPrimaryButton();
        assertAll("Проверка экрана после попытки ввести неверный смс-код второй раз",
                () -> assertEquals("Неверный код, у вас осталась 1 попытка",
                        enterSMSCodePage.getIncorrectSMSCodeErrorMessageText(),
                        ERROR_MESSAGE_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT),
                () -> assertTrue(enterSMSCodePage.isPrimaryButtonEnabled(), PRIMARY_BUTTON_NOT_ENABLED)
        );
        enterSMSCodePage.clickOnPrimaryButton();
        assertEquals("Вы совершили слишком много попыток ввода кода. Пожалуйста, попробуйте через 10 минут.",
                enterSMSCodePage.getTooManyAttemptsPopupText(), ERROR_MESSAGE_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT);
        enterSMSCodePage.clickOnOKPopupButton();
        assertAll("Проверка экрана после попытки ввести неверный смс-код третий раз",
                () -> assertFalse(enterSMSCodePage.isTooManyAttemptsPopupDisplayed(),
                        ERROR_MESSAGE_DISPLAYED),
                () -> assertEquals("Введите код из SMS", enterSMSCodePage.getEnterSMSCodeTitleText(),
                        TITLE_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertEquals("10:00", enterSMSCodePage.getSMSCodeTimerText(),
                        "Таймер отсчитывает не 10:00 минут"),
                () -> assertEquals("Запросите SMS-код через", enterSMSCodePage.getRequestSMSCodeInText(),
                        DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertFalse(enterSMSCodePage.isPrimaryButtonEnabled(), PRIMARY_BUTTON_ENABLED)
        );
        enterSMSCodePage.clickOnSendSMSAgainButton();
        assertEquals("Введите код из SMS", enterSMSCodePage.getEnterSMSCodeTitleText(),
                TITLE_INCORRECT_OR_NOT_DISPLAYED);
    }

    //    @ParameterizedTest
    @Test
    @MethodSource("argsProviderFactory")
    @DisplayName("C5870036 Проверка формы 'Введите код из SMS ' (для не клиентов банка)")
    @Disabled("Issues: AFI-2588, AFI-2673")
    //Аннотация @Test сохранена временно до устранения багов для того, чтобы тест в Allure-отчете был виден как Disabled.
    //Если вместо @Test указывать @ParameterizedTest, тест не будет виден в Allure-отчете и в Run.
    //После устранения багов удалить аннотацию @Test, раскомментировать аннотацию @ParameterizedTest.
    public void enterSMSCodeFormForNonClientsTest(String phone) {
        enterPhoneForRegistrationPage.switchCountryCodeTo(phone);
        enterPhoneForRegistrationPage.enterPhoneNumber(phone);
        enterPhoneForRegistrationPage.clickOnPrimaryButton();
        assertAll("Проверка отображения элементов формы 'Введите код из SMS'",
                () -> assertEquals("Регистрация", enterSMSCodePage.getRegistrationPageHeaderText(),
                        HEADER_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertEquals("Введите код из SMS", enterSMSCodePage.getEnterSMSCodeTitleText(),
                        TITLE_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertEquals("Код отправлен на номер", enterSMSCodePage.getCodeSentToNumberText(),
                        DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertEquals(PhoneNumberConverter.convertPhoneNumber(phone), enterSMSCodePage.getPhoneNumberCodeSentTo(),
                        "Номер телефона, на который отправлен смс-код, не отображается или некорректный"),
                () -> assertTrue(enterSMSCodePage.isSMSCodeInputDisplayed(), INPUT_NOT_DISPLAYED),
                () -> assertEquals("Номер телефона указан неверно?", enterSMSCodePage.getWrongPhoneNumberText(),
                        DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertEquals("Изменить номер телефона", enterSMSCodePage.getChangePhoneNumberButtonText(),
                        "Не отображается кнопка 'Изменить номер телефона'"),
                () -> assertEquals("0:30", enterSMSCodePage.getSMSCodeTimerText(),
                        "Таймер отсчитывает не 30 секунд"),
                () -> assertEquals("Отправить СМС с кодом еще раз", enterSMSCodePage.getSendSMSAgainButtonText(),
                        "Не отображается кнопка 'Отправить SMS с кодом ещё раз'"),
                () -> assertEquals("Продолжить", enterSMSCodePage.getPrimaryButtonText(),
                        PRIMARY_BUTTON_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT),
                () -> assertTrue(enterSMSCodePage.isBackButtonDisplayed(), BACK_BUTTON_NOT_DISPLAYED),
                () -> assertTrue(enterSMSCodePage.isBurgerMenuButtonDisplayed(), BURGER_MENU_BUTTON_NOT_DISPLAYED),
                () -> assertTrue(enterSMSCodePage.isProgressBarDisplayed(), PROGRESS_BAR_NOT_DISPLAYED)
        );
    }

    @Test
    @DisplayName("C5865029 Проверка повторной отправки SMS (для не клиентов банка)")
    public void sendSMSCodeAgainTest() {
        enterPhoneForRegistrationPage.switchCountryCodeTo(byPhone);
        enterPhoneForRegistrationPage.enterPhoneNumber(byPhone);
        enterPhoneForRegistrationPage.clickOnPrimaryButton();
        enterSMSCodePage.clickOnSendSMSAgainButtonWhenTimerExpires();
        enterSMSCodePage.enterSMSCode(databaseHandler.getSMSCode(byPhone));
        assertEquals(databaseHandler.getSMSCode(byPhone), enterSMSCodePage.getSMSCodeInputText(),
                VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED);
        enterSMSCodePage.clickOnPrimaryButton();
        assertEquals("Заполните следующую информацию", enterDataPage.getEnterDataTitleText(),
                EXPECTED_PAGE_DID_NOT_OPEN);
        enterSMSCodePage.clickOnDeviceBackButton();
    }

    @Test
    @DisplayName("C5865046 Проверка повторной отправки SMS после неверного ввода кода (для не клиентов банка)")
    @Disabled("Issues: AFI-2704")
    public void sendSMSCodeAgainAfterIncorrectCodeEnteringTest() {
        enterPhoneForRegistrationPage.switchCountryCodeTo(ruPhone);
        enterPhoneForRegistrationPage.enterPhoneNumber(ruPhone);
        enterPhoneForRegistrationPage.clickOnPrimaryButton();
        enterSMSCodePage.enterSMSCode(incorrectSMSCode);
        enterSMSCodePage.clickOnPrimaryButton();
        assertAll("Проверка экрана после попытки ввести неверный смс-код первый раз",
                () -> assertEquals("Неверный код, у вас осталось 2 попытки",
                        enterSMSCodePage.getIncorrectSMSCodeErrorMessageText(),
                        ERROR_MESSAGE_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT),
                () -> assertTrue(enterSMSCodePage.isPrimaryButtonEnabled(), PRIMARY_BUTTON_NOT_ENABLED)
        );
        enterSMSCodePage.clickOnSendSMSAgainButtonWhenTimerExpires();
        enterSMSCodePage.clearSMSCodeInput();
        enterSMSCodePage.enterSMSCode(databaseHandler.getSMSCode(ruPhone));
        assertEquals(databaseHandler.getSMSCode(ruPhone), enterSMSCodePage.getSMSCodeInputText(),
                VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED);
        enterSMSCodePage.clickOnPrimaryButton();
        assertEquals("Заполните следующую информацию", enterDataPage.getEnterDataTitleText(),
                EXPECTED_PAGE_DID_NOT_OPEN);
        enterSMSCodePage.clickOnDeviceBackButton();
    }

    @AfterEach
    public void backToMainScreenAndDatabaseDataReset() {
        enterSMSCodePage.restartApp();
        databaseHandler.resetPossibilityToReceiveSMS(ruPhone);
        databaseHandler.resetPossibilityToReceiveSMS(byPhone);
    }
}