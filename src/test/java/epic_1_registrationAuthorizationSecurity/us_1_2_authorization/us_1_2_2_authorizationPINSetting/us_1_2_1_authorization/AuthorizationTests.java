package epic_1_registrationAuthorizationSecurity.us_1_2_authorization.us_1_2_2_authorizationPINSetting.us_1_2_1_authorization;

import baseTest.BaseTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.BasePage;
import pages.MainPage;
import pages.authorizationPages.EnterLoginDataPage;
import pojos.User;

import static constants.ErrorMessages.ACTIVE;
import static constants.ErrorMessages.DOES_NOT_MATCH_WITH_EXPECTED;
import static constants.ErrorMessages.NOT_ACTIVE;
import static constants.ErrorMessages.NOT_VISIBLE;
import static constants.ErrorMessages.VISIBLE;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Epic-1 Регистрация/Авторизация/Безопасность")
@Feature("US-1.2 Авторизация")
public class AuthorizationTests extends BaseTest {

    private static EnterLoginDataPage loginPage;
    private static User user;
    private static String userId;

    @BeforeAll
    public static void setTestBeforeAll() {
        AppiumDriver<MobileElement> driver = driverService.getDriver(ANDROID);
        user = userDataProvider.getUserWithEmailFromPropertyFile();
        userId = databaseHandler.getUserIdByPassportNumber(user.getPassportNumber());
        basePage = new BasePage(driver);
        mainPage = new MainPage(driver);
    }

    @BeforeEach
    public void beforeEach() {
        registrationRequests.saveNonExistentClientIntoApplicationProfiles(user);
        mainPage.startApp();
    }


    @ParameterizedTest
    @ValueSource(strings = {"          ", "", "7657867", "765786776554"})
    @DisplayName("C5867596 Авторизация. Невалидный номер телефона")
    public void authorizationByPhoneWhenInvalidPhoneTest(String phone) {
        loginPage = mainPage.clickOnLoginButton();
        loginPage.enterPhoneNumber(phone).enterPassword(user.getPassword());
        assertFalse(loginPage.isInvalidPasswordOrLoginMessageDisplayed(),
                "Сообщение 'неверный логин или пароль'" + VISIBLE);
    }

    @Test
    @DisplayName("C5867942 Авторизация. Невалидный номер паспорта")
    public void authorizationByPassportWhenInvalidPassportTest() {
        loginPage = mainPage.clickOnLoginButton().chooseLoginTypeByPassport();
        assertAll("Проверки ввода разных вариантов паспорта невалидного формата без клика на кнопку логина",
                () -> assertFalse(loginPage.enterPassport("")
                        .isUnacceptableSymbolsMessageDisplayed(), "Сообщение 'Недопустимые символы'" + VISIBLE),
                () -> assertFalse(loginPage.clearPassportInputField()
                                .enterPassport("        ").isUnacceptableSymbolsMessageDisplayed(),
                        "Сообщение 'Недопустимые символы'" + VISIBLE),
                () -> assertTrue(loginPage.clearPassportInputField()
                                .enterPassport("AB-1313").isUnacceptableSymbolsMessageDisplayed(),
                        "Сообщение 'Недопустимые символы'" + NOT_VISIBLE),
                () -> assertTrue(loginPage.clearPassportInputField()
                                .enterPassport("1111111111111111111111").isTooLongPassportMessageDisplayed(),
                        "Сообщение о превышении длины паспорта" + NOT_VISIBLE)
        );
    }

    @Test
    @DisplayName("C5867890 Ввод в поле 'Номер телефона' незарегистрированного валидного номера телефона")
    public void authorizationByPhoneWhenUnregisteredPhoneTest() {
        String unregisteredPhone = "1110020010";
        loginPage = mainPage.clickOnLoginButton();
        loginPage.chooseLoginTypeByRUPhoneNumber().enterPhoneNumber(unregisteredPhone).enterPassword(user.getPassword()).clickLoginButton();
        assertAll("Проверка поведения системы при вводе незарегистрированного телефона",
                () -> assertEquals("+7" + unregisteredPhone, loginPage.getPhoneNumberInputText().replaceAll(" ", ""),
                        "Текст в поле номера телефона" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals(dataGenerator.generateDots(user.getPassword().length()),
                        loginPage.getPasswordInputText(),
                        "Текст в поле пароля" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertTrue(loginPage.isInvalidPasswordOrLoginMessageDisplayed(),
                        "Сообщение 'неверный логин или пароль'" + NOT_VISIBLE),
                () -> assertFalse(loginPage.isLoginButtonActive(), "Кнопка 'Войти'" + NOT_ACTIVE),
                () -> assertFalse(loginPage.clearPhoneInputField().isInvalidPasswordOrLoginMessageDisplayed(),
                        "Сообщение 'неверный логин или пароль'" + VISIBLE)
        );
    }

    @Test
    @DisplayName("C5867946 Ввод в поле 'Номер паспорта' незарегистрированного валидного номера паспорта")
    public void authorizationByPassportWhenUnregisteredPassportTest() {
        String unregisteredPassport = "234387";
        loginPage = mainPage.clickOnLoginButton();
        loginPage.chooseLoginTypeByPassport().enterPassport(unregisteredPassport)
                .enterPassword(user.getPassword()).clickLoginButton();
        assertAll("Проверка поведения системы при вводе незарегистрированного паспорта",
                () -> assertEquals(unregisteredPassport, loginPage.getTextFromPassportField(),
                        "Текст в поле ввода паспорта" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals(dataGenerator.generateDots(user.getPassword().length()),
                        loginPage.getPasswordInputText(), "Текст в поле ввода пароля" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertTrue(loginPage.isInvalidPasswordOrLoginMessageDisplayed(),
                        "Сообщение 'неверный логин или пароль'" + NOT_VISIBLE),
                () -> assertFalse(loginPage.isLoginButtonActive(), "Кнопка 'Войти'" + ACTIVE),
                () -> assertFalse(loginPage.clearPassportInputField().isInvalidPasswordOrLoginMessageDisplayed(),
                        "Сообщение 'неверный логин или пароль'" + VISIBLE)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"123/", "@@@", "qwert/.y"})
    @DisplayName("C5867898 Авторизация. Невалидный пароль")
    public void authorizationWhenInvalidPassportFormatTest(String password) {
        loginPage = mainPage.clickOnLoginButton();
        loginPage.chooseLoginTypeByRUPhoneNumber().enterPhoneNumber(user.getMobilePhone())
                .enterPassport(password).clickLoginButton();
        assertAll("Проверка поведения системы при вводе паролей невалидных форматов",
                () -> assertEquals(dataGenerator.generateDots(password.length()),
                        loginPage.getPasswordInputText(), "Текст в поле пароля" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertTrue(loginPage.isInvalidPasswordOrLoginMessageDisplayed(),
                        "Сообщение 'неверный логин или пароль'" + NOT_VISIBLE),
                () -> assertFalse(loginPage.isLoginButtonActive(), "Кнопка 'Войти'" + ACTIVE),
                () -> assertFalse(loginPage.clearPasswordInputField().isInvalidPasswordOrLoginMessageDisplayed(),
                        "Сообщение 'неверный логин или пароль'" + VISIBLE)
        );
    }

    @Test
    @DisplayName("C5867920 Ввод в поле 'Пароль' корректного пароля, не соответствующего зарегистрированному " +
            "номеру телефона/паспорта")
    public void authorizationByPhoneWhenInvalidPassword() {
        String invalidPassword = "qw11QW";
        loginPage = mainPage.clickOnLoginButton();
        loginPage.chooseLoginTypeByRUPhoneNumber().enterPhoneNumber(user.getMobilePhone())
                .enterPassport(invalidPassword).clickLoginButton();
        assertAll("Проверка поведения системы при вводе и стирании неверного пароля",
                () -> assertEquals(dataGenerator.generateDots(invalidPassword.length()),
                        loginPage.getPasswordInputText(), "Текст в поле пароля" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertTrue(loginPage.isInvalidPasswordOrLoginMessageDisplayed(),
                        "Сообщение 'неверный логин или пароль'" + NOT_VISIBLE),
                () -> assertFalse(loginPage.isLoginButtonActive(), "Кнопка 'Войти'" + ACTIVE),
                () -> assertFalse(loginPage.clearPasswordInputField().isInvalidPasswordOrLoginMessageDisplayed(),
                        "Сообщение 'неверный логин или пароль'" + VISIBLE)
        );
    }

    @AfterEach
    public void setAfterEach() {
        loginPage.closeApp();
    }

    @AfterAll
    public static void setAfterAll() {
        databaseHandler.deleteUserFromUserServiceDataBaseById(userId);
    }
}