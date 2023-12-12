package epic_1_registrationAuthorizationSecurity.us_1_1_registration.us_1_1_4_registrationForBankClients.step_2_enterSMSCode;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import utils.PhoneNumberConverter;

import static data.UserData.RU_PHONE_NUMBER;
import static data.UserData.BY_PHONE_NUMBER;
import static constants.ErrorMessages.BACK_BUTTON_NOT_DISPLAYED;
import static constants.ErrorMessages.BURGER_MENU_BUTTON_NOT_DISPLAYED;
import static constants.ErrorMessages.DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.HEADER_INCORRECT_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.PRIMARY_BUTTON_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT;
import static constants.ErrorMessages.INPUT_NOT_DISPLAYED;
import static constants.ErrorMessages.PROGRESS_BAR_NOT_DISPLAYED;
import static constants.ErrorMessages.TITLE_INCORRECT_OR_NOT_DISPLAYED;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Epic-1 Регистрация/Авторизация/Безопасность")
@Feature("US-1.1 Регистрация")
@Story("US-1.1.4 Регистрация (для клиентов банка)")
public class EnterSMSCodeClientsRegistrationTest extends BaseTest {

    @BeforeAll
    public static void setClientStatusAsNotRegistered() {
        databaseHandler.resetClientStatusToNotRegistered(RU_PHONE_NUMBER);
        databaseHandler.resetClientStatusToNotRegistered(BY_PHONE_NUMBER);
    }

    @BeforeEach
    public void goToEnterSMSCodePage() {
        mainPage.clickOnSignUpButton();
        enterPhoneForRegistrationPage.clickOnSkipButton();
    }

    @DisplayName("C5869894 Проверка формы 'Код из SMS ' (для клиентов банка)")
//    @ParameterizedTest
    @Test
    @ValueSource(strings = {RU_PHONE_NUMBER, BY_PHONE_NUMBER})
    @Disabled("Issues: AFI-2588, AFI-2673")
    //Аннотация @Test сохранена временно до устранения багов для того, чтобы тест в Allure-отчете был виден как Disabled.
    //Если вместо @Test указывать @ParameterizedTest, тест не будет виден в Allure-отчете и в Run.
    //После устранения багов удалить аннотацию @Test, раскомментировать аннотацию @ParameterizedTest.
    public void enterSMSCodeFormForClientsTest(String phone) {
        enterPhoneForRegistrationPage.switchCountryCodeTo(phone);
        enterPhoneForRegistrationPage.enterPhoneNumber(phone);
        enterPhoneForRegistrationPage.clickOnPrimaryButton();
        assertAll("Проверка отображения элементов на экране ввода смс-кода",
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

    @AfterEach
    public void backToMainPage() {
        enterSMSCodePage.restartApp();
        databaseHandler.resetPossibilityToReceiveSMS(RU_PHONE_NUMBER);
        databaseHandler.resetPossibilityToReceiveSMS(BY_PHONE_NUMBER);
    }
}