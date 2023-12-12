package epic_1_registrationAuthorizationSecurity.us_1_2_authorization.us_1_2_2_authorizationPINSetting.us_1_2_1_authorization;

import baseTest.BaseTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.BasePage;
import pages.MainPage;
import pages.authorizationPages.EnterLoginDataPage;
import pojos.User;

import static constants.ErrorMessages.DOES_NOT_MATCH_WITH_EXPECTED;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Epic-1 Регистрация/Авторизация/Безопасность")
@Feature("US-1.2 Авторизация")
public class AuthorizationFirstTimeTests extends BaseTest {

    private static EnterLoginDataPage loginPage;
    private static User user;
    private static String userId;

    @BeforeAll
    public static void setTestBeforeAll() {
        AppiumDriver<MobileElement> driver = driverService.getDriver(ANDROID);
        user = userDataProvider.getUserWithEmailFromPropertyFile();
        basePage = new BasePage(driver);
        mainPage = new MainPage(driver);
    }

    @BeforeEach
    public void beforeEach() {
        registrationRequests.saveNonExistentClientIntoApplicationProfiles(user);
        userId = databaseHandler.getUserIdByPassportNumber(user.getPassportNumber());
        mainPage.startApp();
    }

    @Test
    @DisplayName("C5867457 Авторизация. Верный телефон + верный пароль. 1-я авторизация")
    public void firstAuthorizationByPhoneAfterRegistrationPositiveTest() {
        loginPage = mainPage.clickOnLoginButton();
        String phone = user.getMobilePhone().substring(1);
        assertAll("Проверка поведения системы при логине через телефон в первый раз",
                () -> assertEquals("+" + user.getMobilePhone().replaceAll(" ", ""),
                        loginPage.enterPhoneNumber(phone).getPhoneNumberInputText().replaceAll(" ", ""),
                        "Текст в поле ввода телефона" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals(dataGenerator.generateDots(user.getPassword().length()),
                        loginPage.enterPassword(user.getPassword()).getPasswordInputText(),
                        "Текст в поле ввода пароля" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("Создайте PIN", loginPage.clickLoginButtonFirstTimeAfterRegistration()
                        .getTitleText(), "Экран ввода PIN не открылся")
        );
    }

    @Test
    @DisplayName("C5867513 Авторизация. Верный номер паспорта + верный пароль. 1-я авторизация")
    public void firstAuthorizationByPassportAfterRegistrationPositiveTest() {
        loginPage = mainPage.clickOnLoginButton().chooseLoginTypeByPassport();
        assertAll("Проверка поведения системы при логине через паспорт в первый раз",
                () -> assertEquals(user.getPassportNumber(), loginPage
                        .enterPassport(user.getPassportNumber()).getTextFromPassportField(),
                        "Текст в поле ввода паспорта" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals(dataGenerator.generateDots(user.getPassword().length()),
                        loginPage.enterPassword(user.getPassword()).getPasswordInputText(),
                        "Текст в поле ввода пароля" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("Создайте PIN", loginPage.clickLoginButtonFirstTimeAfterRegistration()
                        .getTitleText(), "Экран ввода PIN не открылся")
        );
    }

    @AfterEach
    public void setAfterEach() {
        databaseHandler.deleteUserFromUserServiceDataBaseById(userId);
        loginPage.closeApp();
    }
}