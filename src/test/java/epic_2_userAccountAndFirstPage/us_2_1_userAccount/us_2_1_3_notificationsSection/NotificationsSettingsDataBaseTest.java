package epic_2_userAccountAndFirstPage.us_2_1_userAccount.us_2_1_3_notificationsSection;

import baseTest.BaseTest;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.User;

import static constants.ErrorMessages.ACTIVE;
import static constants.ErrorMessages.DOES_NOT_MATCH_WITH_EXPECTED;
import static constants.ErrorMessages.ERROR_MESSAGE_DISPLAYED;
import static constants.ErrorMessages.ERROR_MESSAGE_NOT_DISPLAYED;
import static constants.ErrorMessages.EXPECTED_PAGE_DID_NOT_OPEN;
import static constants.ErrorMessages.NOT_ACTIVE;
import static constants.ErrorMessages.NOT_VISIBLE;
import static constants.ErrorMessages.VISIBLE;
import static constants.TestConstants.VALID_EMAIL;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Epic-2 Личный кабинет/Основное меню (первая страница при входе в приложение)")
@Feature("US-2.1 Личный кабинет")
@Story("US-2.1.3 Раздел 'Уведомления'")
public class NotificationsSettingsDataBaseTest extends BaseTest {

    private static User user;
    private static String userId;

    @Test
    @DisplayName("C5880338 Проверка логики работы телефонной клавиатуры, курсора, лейбла")
    public void emailInputKeyboardTest() {
        user = userDataProvider.getUserWithoutEmailFromPropertyFile();
        userId = appOperations.createNonExistentClient(user);
        appOperations.openPersonalDataMenu(user);
        personalDataMenu.clickNotificationsButton();
        notificationsPage.clickEmailEditButton();
        emailEditPage.clickEmailInputField();
        assertAll("Проверка отображения лейбла над полем ввода Email, маски с текстом 'Email', " +
                        "курсора ввода текста и телефонной клавиатуры",
                () -> assertTrue(emailEditPage.isEmailLabelTextDisplayed(),
                        "Лейбл над полел ввода Email" + NOT_VISIBLE),
                () -> assertTrue(emailEditPage.getEmailInputFieldText().isEmpty(),
                        "Плейсхолдер поля ввода Email" + VISIBLE),
                () -> assertTrue(emailEditPage.isEmailInputFieldCursorDisplayed(),
                        "Курсор ввода текста" + NOT_VISIBLE),
                () -> assertTrue(basePage.isDeviceKeyboardShown(), "Телефонная клавиатура" + NOT_VISIBLE)
        );
        basePage.clickOnDeviceBackButton();
        assertFalse(basePage.isDeviceKeyboardShown(), "Телефонная клавиатура" + VISIBLE);
        emailEditPage.clickEmailInputField();
        assertAll("Проверка отображения лейбла над полем ввода Email, маски с текстом 'Email', " +
                        "курсора ввода текста и телефонной клавиатуры",
                () -> assertTrue(emailEditPage.isEmailLabelTextDisplayed(),
                        "Лейбл над полел ввода Email" + NOT_VISIBLE),
                () -> assertTrue(emailEditPage.getEmailInputFieldText().isEmpty(),
                        "Плейсхолдер поля ввода Email" + VISIBLE),
                () -> assertTrue(emailEditPage.isEmailInputFieldCursorDisplayed(),
                        "Курсор ввода текста" + NOT_VISIBLE),
                () -> assertTrue(basePage.isDeviceKeyboardShown(), "Телефонная клавиатура" + NOT_VISIBLE)
        );
        emailEditPage.clickAnywhere();
        assertFalse(basePage.isDeviceKeyboardShown(), "Телефонная клавиатура" + VISIBLE);
    }

    @Test
    @DisplayName("C5880344 Ввод пустого значения в поле 'E-mail'")
    public void emailFieldEmptyInputTest() {
        user = userDataProvider.getUserWithoutEmailFromPropertyFile();
        userId = appOperations.createNonExistentClient(user);
        appOperations.openPersonalDataMenu(user);
        personalDataMenu.clickNotificationsButton();
        notificationsPage.clickEmailEditButton();
        assertFalse(emailEditPage.isPrimaryButtonEnabled(),
                "Кнопка 'Сохранить'" + ACTIVE + " при невведенном Email");
    }

    @Test
    @DisplayName("C5880346 Проверка сброса данных при нажатии кнопки 'Назад' в окне 'E-mail'")
    public void saveEmailInputWhenToAnotherPageTest() {
        user = userDataProvider.getUserWithoutEmailFromPropertyFile();
        userId = appOperations.createNonExistentClient(user);
        appOperations.openPersonalDataMenu(user);
        personalDataMenu.clickNotificationsButton();
        notificationsPage.clickEmailEditButton();
        assertTrue(emailEditPage.isEmailEditPageTitleDisplayed(), EXPECTED_PAGE_DID_NOT_OPEN);
        emailEditPage.enterEmail(VALID_EMAIL);
        assertEquals(VALID_EMAIL, emailEditPage.getEmailInputFieldText(), DOES_NOT_MATCH_WITH_EXPECTED);
        basePage.clickOnBackButton();
        assertTrue(notificationsPage.isNotificationsPageTitleDisplayed(), EXPECTED_PAGE_DID_NOT_OPEN);
        notificationsPage.clickEmailEditButton();
        assertNotEquals(VALID_EMAIL, emailEditPage.getEmailInputFieldText(), DOES_NOT_MATCH_WITH_EXPECTED);
    }

    @Test
    @DisplayName("C5880266 Проверка включения/отключения 'Email-рассылки', если E-mail еще не введен пользователем")
    public void switchEmailNotificationsWithoutEmailTest() {
        user = userDataProvider.getUserWithoutEmailFromPropertyFile();
        userId = appOperations.createNonExistentClient(user);
        appOperations.openPersonalDataMenu(user);
        personalDataMenu.clickNotificationsButton();
        notificationsPage.clickEmailEditButton();
        emailEditPage.clickEmailInputField();
        assertAll("Проверка активновности кнопки 'Сохранить' и сообщения об ошибке",
                () -> assertFalse(emailEditPage.isPrimaryButtonEnabled(),
                        "Кнопка 'Сохранить'" + ACTIVE + " при невведенном Email"),
                () -> assertTrue(emailEditPage.isErrorTextDisplayed(), ERROR_MESSAGE_NOT_DISPLAYED)
        );
        emailEditPage.enterEmail(VALID_EMAIL);
        assertAll("Проверка ввода валидного Email, активновности кнопки 'Сохранить' и сообщения об ошибке",
                () -> assertEquals(VALID_EMAIL, emailEditPage.getEmailInputFieldText(), DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertTrue(emailEditPage.isPrimaryButtonEnabled(),
                        "Кнопка 'Сохранить'" + NOT_ACTIVE+ " при введенном Email"),
                () -> assertFalse(emailEditPage.isErrorTextDisplayed(), ERROR_MESSAGE_DISPLAYED)
        );
        emailEditPage.clickOnPrimaryButton();
        assertAll("Проверка сохранения Email в базе данных и активности свитчера 'Email-рассылка'",
                () -> assertTrue(notificationsPage.isNotificationsPageTitleDisplayed(), EXPECTED_PAGE_DID_NOT_OPEN),
                () -> assertTrue(notificationsPage.isEmailSwitcherNotificationsEnabled(),
                        "Свитчер 'Email-рассылка'" + NOT_ACTIVE),
                () -> assertEquals(VALID_EMAIL, databaseHandler.getEmailByClientId(userId))
        );
        notificationsPage.switchEmailNotifications(false);
        notificationsPage.clickEmailNotificationsSwitcher();
        assertAll("Проверка включения свитчера 'Email-рассылка' и изменения статуса в базе данных",
                () -> assertTrue(notificationsPage.isEmailNotificationsSwitcherOn(), DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals(true, databaseHandler.getEmailNotificationsStatusByClientId(userId),
                        "Состояние свитчера Email-рассылки в базе данных" + DOES_NOT_MATCH_WITH_EXPECTED)
        );
        notificationsPage.clickEmailNotificationsSwitcher();
        assertAll("Проверка выключения свитчера 'Email-рассылка' и изменения статуса в базе данных",
                () -> assertFalse(notificationsPage.isEmailNotificationsSwitcherOn(), DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals(false, databaseHandler.getEmailNotificationsStatusByClientId(userId),
                        "Состояние свитчера Email-рассылки в базе данных" + DOES_NOT_MATCH_WITH_EXPECTED)
        );
    }

    @Test
    @DisplayName("C5880348 Проверка включения/отключения 'Email-рассылки', если Email добавлен пользователем ранее")
    public void switchEmailNotificationsWithEmailTest() {
        user = userDataProvider.getUserWithEmailFromPropertyFile();
        userId = appOperations.createNonExistentClient(user);
        appOperations.openPersonalDataMenu(user);
        personalDataMenu.clickNotificationsButton();
        notificationsPage.switchEmailNotifications(false);
        notificationsPage.clickEmailNotificationsSwitcher();
        assertAll("Проверка включения свитчера 'Email-рассылка' и изменения статуса в базе данных",
                () -> assertTrue(notificationsPage.isEmailNotificationsSwitcherOn(), DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals(true, databaseHandler.getEmailNotificationsStatusByClientId(userId),
                        "Состояние свитчера Email-рассылки в базе данных" + DOES_NOT_MATCH_WITH_EXPECTED)
        );
        notificationsPage.clickEmailNotificationsSwitcher();
        assertAll("Проверка выключения свитчера 'Email-рассылка' и изменения статуса в базе данных",
                () -> assertFalse(notificationsPage.isEmailNotificationsSwitcherOn(), DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals(false, databaseHandler.getEmailNotificationsStatusByClientId(userId),
                        "Состояние свитчера Email-рассылки в базе данных" + DOES_NOT_MATCH_WITH_EXPECTED)
        );
    }

    @Test
    @DisplayName("C5880393 Проверка включения/отключения 'SMS-уведомления' и 'Push-уведомления'")
    public void switchSmsAndPushNotificationsTest() {
        user = userDataProvider.getUserWithoutEmailFromPropertyFile();
        userId = appOperations.createNonExistentClient(user);
        appOperations.openPersonalDataMenu(user);
        personalDataMenu.clickNotificationsButton();
        notificationsPage.switchSmsNotifications(false);
        notificationsPage.clickSmsNotificationsSwitcher();
        assertAll("Проверка включения свитчера 'Sms-уведомлений' и изменения статуса в базе данных",
                () -> assertTrue(notificationsPage.isSmsNotificationsSwitcherOn(), DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals(true, databaseHandler.getSmsNotificationsStatusByClientId(userId),
                        "Состояние свитчера 'Sms-уведомлений' в базе данных" + DOES_NOT_MATCH_WITH_EXPECTED)
        );
        notificationsPage.clickSmsNotificationsSwitcher();
        assertAll("Проверка выключения свитчера 'Sms-уведомлений' и изменения статуса в базе данных",
                () -> assertFalse(notificationsPage.isSmsNotificationsSwitcherOn(), DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals(false, databaseHandler.getSmsNotificationsStatusByClientId(userId),
                        "Состояние свитчера 'Sms-уведомлений' в базе данных" + DOES_NOT_MATCH_WITH_EXPECTED)
        );
        notificationsPage.switchPushNotifications(false);
        notificationsPage.clickPushNotificationsSwitcher();
        assertAll("Проверка включения свитчера 'Push-уведомлений' и изменения статуса в базе данных",
                () -> assertTrue(notificationsPage.isPushNotificationsSwitcherOn(), DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals(true, databaseHandler.getPushNotificationsStatusByClientId(userId),
                        "Состояние свитчера 'Push-уведомлений' в базе данных" + DOES_NOT_MATCH_WITH_EXPECTED)
        );
        notificationsPage.clickPushNotificationsSwitcher();
        assertAll("Проверка выключения свитчера 'Push-уведомлений' и изменения статуса в базе данных",
                () -> assertFalse(notificationsPage.isPushNotificationsSwitcherOn(), DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals(false, databaseHandler.getPushNotificationsStatusByClientId(userId),
                        "Состояние свитчера 'Push-уведомлений' в базе данных" + DOES_NOT_MATCH_WITH_EXPECTED)
        );
    }

    @AfterEach
    public void restartRegistrationPage() {
        databaseHandler.deleteUserFromUserServiceDataBaseById(userId);
        mainPage.restartApp();
    }
}
