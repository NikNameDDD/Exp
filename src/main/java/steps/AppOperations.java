package steps;

import JDBC.DatabaseHandler;
import driverInitialization.Driver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import pages.MainPage;
import pages.authorizationPages.EnterLoginDataPage;
import pages.authorizationPages.InstallPINPage;
import pages.authorizationPages.PINCreatedPage;
import pages.userAccountPages.FirstPage;
import pages.userAccountPages.PersonalDataMenu;
import pojos.User;
import requests.RegistrationRequests;

import static constants.TestConstants.VALID_PIN;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

public class AppOperations {

    private static RegistrationRequests registrationRequests;
    private static DatabaseHandler databaseHandler;
    private static Driver driverService;
    private static String userId;

    private static AppOperations instance;

    private AppOperations() {}

    public static AppOperations getInstance() {
        if(instance == null) {
            instance = new AppOperations();
        }
        return instance;
    }

    public String createNonExistentClient(User user) {
        registrationRequests = RegistrationRequests.getInstance();
        databaseHandler = DatabaseHandler.getInstance();
        registrationRequests.saveNonExistentClientIntoApplicationProfiles(user);
        userId = databaseHandler.getUserIdByPassportNumber(user.getPassportNumber());
        return userId;
    }

    public void openPersonalDataMenu(User user) {
        driverService = Driver.getInstance();
        AppiumDriver<MobileElement> driver = driverService.getDriver(ANDROID);
        MainPage mainPage =new MainPage(driver);
        EnterLoginDataPage enterLoginDataPage = new EnterLoginDataPage(driver);
        InstallPINPage installPINPage = new InstallPINPage(driver);
        PINCreatedPage pinCreatedPage = new PINCreatedPage(driver);
        FirstPage firstPage = new FirstPage(driver);
        mainPage.clickOnLoginButton();
        enterLoginDataPage.chooseLoginTypeByPassport();
        enterLoginDataPage.enterPassport(user.getPassportNumber());
        enterLoginDataPage.enterPassword(user.getPassword());
        enterLoginDataPage.clickOnPrimaryButton();
        installPINPage.enterPin(VALID_PIN);
        installPINPage.enterPin(VALID_PIN);
        pinCreatedPage.clickOnPrimaryButton();
        firstPage.clickUserAvatar();
    }
}
