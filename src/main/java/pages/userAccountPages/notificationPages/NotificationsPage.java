package pages.userAccountPages.notificationPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import pages.BasePage;

import java.util.Objects;

public class NotificationsPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Уведомления']")
    private MobileElement notificationsPageTitle;

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_settings_notifications_enter_email_pen")
    private MobileElement emailEditButton;

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_settings_notifications_switch_sms")
    private MobileElement smsNotificationsSwitcher;

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_settings_notifications_switch_push")
    private MobileElement pushNotificationsSwitcher;

    @AndroidFindBy(id = "ru.andersen.afinny:id/fragment_settings_notifications_switch_email")
    private MobileElement emailNotificationsSwitcher;

    public NotificationsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Отображение заголовка страницы 'Уведомления'")
    public boolean isNotificationsPageTitleDisplayed() {
        return isElementDisplayed(notificationsPageTitle);
    }

    @Step("Нажимаем на кнопку 'Редактировать Email'")
    public EmailEditPage clickEmailEditButton() {
        emailEditButton.click();
        return new EmailEditPage(driver);
    }

    @Step("Нажимаем на свитчер Email-рассылка")
    public void clickEmailNotificationsSwitcher() {
        emailNotificationsSwitcher.click();
    }

    @Step("Активность свитчера Email-рассылки")
    public boolean isEmailSwitcherNotificationsEnabled() {
        return emailNotificationsSwitcher.isEnabled();
    }

    @Step("Отображение включения свитчера Email-рассылки")
    public boolean isEmailNotificationsSwitcherOn() {
        return Objects.equals(emailNotificationsSwitcher
                .getAttribute("checked"), "true");
    }

    @Step("Отображение состояния свитчера Email-рассылки и включение/выключение по необходимости")
    public void switchEmailNotifications(boolean state) {
        if((!isEmailNotificationsSwitcherOn() && state) || (isEmailNotificationsSwitcherOn() && !state)){
            clickEmailNotificationsSwitcher();
        }
    }

    @Step("Нажимаем на свитчер Sms-уведомления")
    public void clickSmsNotificationsSwitcher() {
        smsNotificationsSwitcher.click();
    }

    @Step("Отображение включения свитчера Sms-уведомлений")
    public boolean isSmsNotificationsSwitcherOn() {
        return Objects.equals(smsNotificationsSwitcher
                .getAttribute("checked"), "true");
    }

    @Step("Отображение состояния свитчера Sms-уведомления и включение/выключение по необходимости")
    public void switchSmsNotifications(boolean state) {
        if((!isSmsNotificationsSwitcherOn() && state) || (isSmsNotificationsSwitcherOn() && !state)){
            clickSmsNotificationsSwitcher();
        }
    }

    @Step("Нажимаем на свитчер Push-уведомления")
    public void clickPushNotificationsSwitcher() {
        pushNotificationsSwitcher.click();
    }

    @Step("Отображение включения свитчера Push-уведомлений")
    public boolean isPushNotificationsSwitcherOn() {
        return Objects.equals(pushNotificationsSwitcher
                .getAttribute("checked"), "true");
    }

    @Step("Отображение состояния свитчера Push-уведомления и включение/выключение по необходимости")
    public void switchPushNotifications(boolean state) {
        if((!isPushNotificationsSwitcherOn() && state) || (isPushNotificationsSwitcherOn() && !state)){
            clickPushNotificationsSwitcher();
        }
    }
}
