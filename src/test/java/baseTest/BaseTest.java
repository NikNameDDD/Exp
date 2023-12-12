package baseTest;

import JDBC.DatabaseHandler;
import dataproviders.UserDataProvider;
import driverInitialization.Driver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import pages.AllowAccessToGeoPositionPage;
import pages.ATMsAndBranchesMapPage;
import pages.AppTourPage;
import pages.BasePage;
import pages.BurgerMenuPage;
import pages.ExchangeRatesPage;
import pages.authorizationPages.BtnEntMainPage;
import pages.authorizationPages.InstallPINPage;
import pages.authorizationPages.EnterWrongPIN;
import pages.userAccountPages.securityPages.ChangePasswordPage;
import pages.userAccountPages.securityPages.ChangeSecurityQuestionPage;
import pages.userAccountPages.FirstPage;
import pages.PrivacyPolicyPage;
import pages.registrationPages.EnterDataPage;
import pages.registrationPages.EnterPhoneForRegistrationPage;
import pages.MainPage;
import pages.authorizationPages.EnterPINPage;
import pages.authorizationPages.EnterLoginDataPage;
import pages.authorizationPages.PINCreatedPage;
import pages.registrationPages.EnterSMSCodePage;
import pages.registrationPages.SetPasswordPage;
import pages.userAccountPages.PersonalDataMenu;
import pages.userAccountPages.notificationPages.EmailEditPage;
import pages.userAccountPages.notificationPages.NotificationsPage;
import pages.userAccountPages.securityPages.SecuritySettingsPage;
import requests.RegistrationRequests;
import steps.AppOperations;
import utils.DataGenerator;

import static io.appium.java_client.remote.MobilePlatform.ANDROID;

public class BaseTest {

    protected static BasePage basePage;
    protected static MainPage mainPage;
    protected static BurgerMenuPage burgerMenuPage;
    protected static AllowAccessToGeoPositionPage allowAccessToGeoPositionPage;
    protected static ATMsAndBranchesMapPage atmsAndBranchesMapPage;
    protected static ExchangeRatesPage exchangeRatesPage;
    protected static EnterLoginDataPage enterLoginDataPage;
    protected static EnterPhoneForRegistrationPage enterPhoneForRegistrationPage;
    protected static EnterSMSCodePage enterSMSCodePage;
    protected static EnterDataPage enterDataPage;
    protected static SetPasswordPage setPasswordPage;
    protected static PrivacyPolicyPage privacyPolicyPage;
    protected static AppTourPage appTourPage;
    protected static InstallPINPage installPINPage;
    protected static PINCreatedPage pinCreatedPage;
    protected static FirstPage firstPage;
    protected static EnterPINPage enterPINPage;
    protected static NotificationsPage notificationsPage;
    protected static EmailEditPage emailEditPage;
    protected static PersonalDataMenu personalDataMenu;
    protected static SecuritySettingsPage securitySettingsPage;
    protected static ChangePasswordPage changePasswordPage;
    protected static ChangeSecurityQuestionPage changeSecurityQuestionPage;
    protected static DatabaseHandler databaseHandler;
    protected static RegistrationRequests registrationRequests;
    protected static UserDataProvider userDataProvider;
    protected static Driver driverService;
    protected static DataGenerator dataGenerator;
    protected static AppOperations appOperations;

    protected static BtnEntMainPage btnEntMainPage;//Mine C5870102
    protected static EnterWrongPIN CheckingForIncorrectEntryPIN;//Mine C5870815
    protected static PINCode CheckingForCorrectEntryPIN ;//Mine C5870103


    @BeforeAll
    public static void setUp() {
        driverService = Driver.getInstance();
        AppiumDriver<MobileElement> driver = driverService.getDriver(ANDROID);
        restAssuredSetUp();
        basePage = new BasePage(driver);
        mainPage = new MainPage(driver);
        burgerMenuPage = new BurgerMenuPage(driver);
        allowAccessToGeoPositionPage = new AllowAccessToGeoPositionPage(driver);
        atmsAndBranchesMapPage = new ATMsAndBranchesMapPage(driver);
        exchangeRatesPage = new ExchangeRatesPage(driver);
        enterLoginDataPage = new EnterLoginDataPage(driver);
        enterPhoneForRegistrationPage = new EnterPhoneForRegistrationPage(driver);
        enterSMSCodePage = new EnterSMSCodePage(driver);
        enterDataPage = new EnterDataPage(driver);
        setPasswordPage = new SetPasswordPage(driver);
        privacyPolicyPage = new PrivacyPolicyPage(driver);
        appTourPage = new AppTourPage(driver);
        burgerMenuPage = new BurgerMenuPage(driver);
        allowAccessToGeoPositionPage = new AllowAccessToGeoPositionPage(driver);
        exchangeRatesPage = new ExchangeRatesPage(driver);
        installPINPage = new InstallPINPage(driver);
        pinCreatedPage = new PINCreatedPage(driver);
        firstPage = new FirstPage(driver);
        enterPINPage = new EnterPINPage(driver);
        databaseHandler = DatabaseHandler.getInstance();
        registrationRequests = RegistrationRequests.getInstance();
        userDataProvider = UserDataProvider.getInstance();
        dataGenerator = DataGenerator.getInstance();
        personalDataMenu = new PersonalDataMenu(driver);
        notificationsPage = new NotificationsPage(driver);
        emailEditPage = new EmailEditPage(driver);
        appOperations = AppOperations.getInstance();
        changePasswordPage = new ChangePasswordPage(driver);
        changeSecurityQuestionPage = new ChangeSecurityQuestionPage(driver);
        securitySettingsPage = new SecuritySettingsPage(driver);

        btnEntMainPage = new BtnEntMainPage(driver);//Mine C5870102
        CheckingForIncorrectEntryPIN = new EnterWrongPIN(driver);//Mine C5870815
        CheckingForCorrectEntryPIN = new PINCode(driver) ;//Mine C5870103



    }

    public static void restAssuredSetUp() {
        RestAssured.config = RestAssured.config().sslConfig(new SSLConfig()
                .allowAllHostnames()
                .relaxedHTTPSValidation());
    }

    @AfterAll
    public static void tearDown() {
        Driver.getInstance().deleteWebDriver();
        AppiumDriverLocalService appiumDriverLocalService = EnvironmentService.getInstance().getAppiumLocalService();
        appiumDriverLocalService.clearOutPutStreams();
        appiumDriverLocalService.stop();
    }
}