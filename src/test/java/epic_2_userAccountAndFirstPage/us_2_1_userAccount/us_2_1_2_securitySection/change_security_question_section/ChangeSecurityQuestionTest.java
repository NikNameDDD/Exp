package epic_2_userAccountAndFirstPage.us_2_1_userAccount.us_2_1_2_securitySection.change_security_question_section;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.User;

import static constants.ErrorMessages.DOES_NOT_MATCH_WITH_EXPECTED;
import static constants.ErrorMessages.ERROR_MESSAGE_NOT_DISPLAYED;
import static constants.ErrorMessages.NOT_VISIBLE;
import static constants.ErrorMessages.PRIMARY_BUTTON_ENABLED;
import static constants.ErrorMessages.PRIMARY_BUTTON_NOT_ENABLED;
import static constants.ErrorMessages.VISIBLE;
import static constants.TestConstants.INVALID_SECURITY_ANSWER;
import static constants.TestConstants.VALID_SECURITY_ANSWER;
import static constants.TestConstants.VALID_SECURITY_QUESTION;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Epic-2 Личный кабинет/Основное меню")
@Feature("US-2.1 Личный кабинет")
@Story("US-2.1.2 Настройка безопасности")
public class ChangeSecurityQuestionTest extends BaseTest {

    private static User user;
    private static String userId;

    @BeforeAll
    public static void setTestBeforeAll() {
        user = userDataProvider.getUserWithoutEmailFromPropertyFile();
        userId = appOperations.createNonExistentClient(user);
        appOperations.openPersonalDataMenu(user);
        personalDataMenu.clickSecurityButton();
    }

    @BeforeEach
    public void setBeforeEach() {
        securitySettingsPage.clickChangeSecurityQuestionOption();
    }

    @Test
    @DisplayName("C5885527 Ввод пустого значения в поля 'Напишите контрольный вопрос' и 'Ответ на контрольный вопрос'")
    @Flaky()
    //"TODO: изучить возможности нажатия элементов не через координаты"
    public void enterEmptyValueTest() {
        String letter  = "d";
        changeSecurityQuestionPage.clickSecurityQuestionDropdown();
        changeSecurityQuestionPage.clickWriteOwnSecurityQuestionVariant();
        changeSecurityQuestionPage.clickCreateSecurityQuestionField();
        assertAll("Проверка отображения плейсхолдера, лейбла, курсора ввода текста и " +
                        "счетчика введенных символов поля 'Напишите контрольный вопрос'",
                () -> assertTrue(changeSecurityQuestionPage.getCreateSecurityQuestionFieldText().isEmpty(),
                        "Плейсхолдер поля ввода 'Напишите контрольный вопрос'" + VISIBLE),
                () -> assertTrue(changeSecurityQuestionPage.isCreateSecurityQuestionFieldLabelTextDisplayed(),
                        "Лейбл над полем ввода 'Напишите контрольный вопрос'" + NOT_VISIBLE),
                () -> assertTrue(changeSecurityQuestionPage.isCreateSecurityQuestionInputFieldCursorDisplayed(),
                        "Курсор ввода текста" + NOT_VISIBLE),
                () -> assertTrue(changeSecurityQuestionPage.enterCreateSecurityQuestionFieldText(letter).isTextSymbolsCounterDisplayed(),
                        "Счетчик введённых символов" + NOT_VISIBLE)
        );
        changeSecurityQuestionPage.clearCreateSecurityQuestionField();
        assertFalse(changeSecurityQuestionPage.isPrimaryButtonEnabled(), PRIMARY_BUTTON_ENABLED);
        changeSecurityQuestionPage.clickAnswerSecurityQuestionField();
        assertAll("Проверка отображения плейсхолдера, лейбла и курсора ввода текста " +
                        "поля 'Напишите ответ на контрольный вопрос'",
                () -> assertTrue(changeSecurityQuestionPage.getAnswerSecurityQuestionFieldText().isEmpty(),
                        "Плейсхолдер поля ввода 'Напишите ответ на контрольный вопрос'" + VISIBLE),
                () -> assertTrue(changeSecurityQuestionPage.isAnswerSecurityQuestionInputFieldLabelTextDisplayed(),
                        "Лейбл над полем ввода 'Напишите ответ на контрольный вопрос'" + NOT_VISIBLE),
                () -> assertTrue(changeSecurityQuestionPage.isAnswerSecurityQuestionInputFieldCursorDisplayed(),
                        "Курсор ввода текста" + NOT_VISIBLE)
        );
        changeSecurityQuestionPage.enterAnswerSecurityQuestionFieldText(letter).clickOnDeviceBackButton();
        assertTrue(changeSecurityQuestionPage.isTextSymbolsCounterDisplayed(),
                        "Счетчик введённых символов" + NOT_VISIBLE);
        changeSecurityQuestionPage.clearAnswerSecurityQuestionField();
        assertFalse(changeSecurityQuestionPage.isPrimaryButtonEnabled(), PRIMARY_BUTTON_ENABLED);
    }

    @Test
    @DisplayName("C5885514 Валидный контрольный вопрос + валидный ответ")
    @Flaky()
    //"TODO: изучить возможности нажатия элементов не через координаты"
    public void enterValidQuestionAndAnswerTest() {
        changeSecurityQuestionPage.clickSecurityQuestionDropdown();
        changeSecurityQuestionPage.clickFavouriteBookVariant();
        changeSecurityQuestionPage.enterTextInputFieldText(VALID_SECURITY_ANSWER);
        assertAll("Проверка ввода валидных данных в поле 'Напишите ответ на контрольный вопрос'",
                () -> assertEquals(VALID_SECURITY_ANSWER, changeSecurityQuestionPage.getTextInputFieldText(),
                        "Текст в поле ввода" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertTrue(changeSecurityQuestionPage.isPrimaryButtonEnabled(), PRIMARY_BUTTON_NOT_ENABLED)
        );
        changeSecurityQuestionPage.clickOnPrimaryButton();
        assertAll("Проверка сохранения изменений в базе данных",
                () -> assertEquals(VALID_SECURITY_QUESTION, databaseHandler.getSecurityQuestionByClientId(userId),
                        "Контрольный вопрос" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals(VALID_SECURITY_ANSWER, databaseHandler.getSecurityAnswerByClientId(userId),
                        "Ответ на контрольный вопрос" + DOES_NOT_MATCH_WITH_EXPECTED)
        );
    }

    @Test
    @DisplayName("C5885516 Валидный контрольный вопрос + невалидный ответ")
    @Flaky()
    //"TODO: изучить возможности нажатия элементов не через координаты"
    public void enterValidQuestionAndInvalidAnswerTest() {
        changeSecurityQuestionPage.clickSecurityQuestionDropdown();
        changeSecurityQuestionPage.clickFavouriteBookVariant();
        changeSecurityQuestionPage.enterTextInputFieldText(INVALID_SECURITY_ANSWER);
        assertAll("Проверка отображения сообщения об ошибке и активности кнопки 'Изменить'",
                () -> assertTrue(changeSecurityQuestionPage.isErrorMessageTextDisplayed(), ERROR_MESSAGE_NOT_DISPLAYED),
                () -> assertFalse(changeSecurityQuestionPage.isPrimaryButtonEnabled(), PRIMARY_BUTTON_ENABLED)
        );
    }

    @AfterEach
    public void setAfterEach() {
        changeSecurityQuestionPage.clickOnBackButton();
    }

    @AfterAll
    public static void setAfterAll() {
        databaseHandler.deleteUserFromUserServiceDataBaseById(userId);
        mainPage.closeApp();
    }
}
