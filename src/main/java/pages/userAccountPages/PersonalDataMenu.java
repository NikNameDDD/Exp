package pages.userAccountPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import pages.BasePage;
import pages.userAccountPages.notificationPages.NotificationsPage;
import pages.userAccountPages.securityPages.SecuritySettingsPage;

public class PersonalDataMenu extends BasePage {

    @AndroidFindBy(xpath = "//*[@text = 'Безопасность']")
    private MobileElement securityButton;

    @AndroidFindBy(xpath = "//*[@text = 'Уведомления']")
    private MobileElement notificationsButton;

    @AndroidFindBy(xpath = "//*[@text = 'Настройки приложения']")
    private MobileElement appSettingsButton;

    @AndroidFindBy(xpath = "//*[@text = 'Контакты']")
    private MobileElement contactsButton;

    @AndroidFindBy(xpath = "//*[@text = 'Банкоматы и отделения']")
    private MobileElement atmsAndBranchesButton;

    @AndroidFindBy(xpath = "//*[@text = 'О приложении']")
    private MobileElement aboutApp;

    public PersonalDataMenu(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Нажимаем на кнопку 'Уведомления'")
    public NotificationsPage clickNotificationsButton() {
        notificationsButton.click();
        return new NotificationsPage(driver);
    }

    @Step("Нажимаем на кнопку 'Уведомления'")
    public SecuritySettingsPage clickSecurityButton() {
        securityButton.click();
        return new SecuritySettingsPage(driver);
    }
}
