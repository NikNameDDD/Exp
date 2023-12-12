package epic_2_userAccountAndFirstPage.us_2_1_userAccount.us_2_1_3_notificationsSection;

import baseTest.BaseTest;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.User;

import static constants.ErrorMessages.DOES_NOT_MATCH_WITH_EXPECTED;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Epic-2 Личный кабинет/Основное меню (первая страница при входе в приложение)")
@Feature("US-2.1 Личный кабинет")
@Story("US-2.1.3 Раздел 'Уведомления'")
public class NotificationsSettingsNetworkTest extends BaseTest {

    private static User user;
    private static String userId;

    @Test
    @DisplayName("C5880395 Проверка работы включенных уведомлений при отсутствии подключения к интернету")
    public void switchNotificationsWithoutNetworkTest() {
        user = userDataProvider.getUserWithEmailFromPropertyFile();
        userId = appOperations.createNonExistentClient(user);
        appOperations.openPersonalDataMenu(user);
        personalDataMenu.clickNotificationsButton();
        notificationsPage.switchSmsNotifications(true);
        notificationsPage.switchPushNotifications(true);
        notificationsPage.switchEmailNotifications(true);
        basePage.switchDeviceNetworkConnections();
        notificationsPage.clickSmsNotificationsSwitcher();
        notificationsPage.clickPushNotificationsSwitcher();
        notificationsPage.clickEmailNotificationsSwitcher();
        assertAll("Проверка состояний свитчеров при попытке их отключения с выключенным интернетом",
                () -> assertTrue(notificationsPage.isSmsNotificationsSwitcherOn(), DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertTrue(notificationsPage.isPushNotificationsSwitcherOn(), DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertTrue(notificationsPage.isEmailNotificationsSwitcherOn(), DOES_NOT_MATCH_WITH_EXPECTED)
        );
    }

    @AfterEach
    public void restartRegistrationPage() {
        basePage.switchDeviceNetworkConnections();
        mainPage.restartApp();
    }
}
