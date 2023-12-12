package epic_1_registrationAuthorizationSecurity.us_1_1_registration.us_1_1_8_privacyPolicy;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static constants.ErrorMessages.BURGER_MENU_BUTTON_NOT_DISPLAYED;
import static constants.ErrorMessages.ELEMENT_NOT_CLICKABLE;
import static constants.ErrorMessages.EXPECTED_PAGE_DID_NOT_OPEN;
import static constants.ErrorMessages.HEADER_INCORRECT_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED;
import static constants.ErrorMessages.PRIMARY_BUTTON_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT;
import static constants.ErrorMessages.VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED;
import static data.UserData.RU_PHONE_NUMBER;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Epic-1 Регистрация/Авторизация/Безопасность")
@Feature("US-1.1 Регистрация")
@Story("US-1.1.8 Политика конфиденциальности")
public class PrivacyPolicyTest extends BaseTest {

    @BeforeAll
    public void openRegistrationPage() {
        mainPage.clickOnSignUpButton();
        enterPhoneForRegistrationPage.clickOnSkipButton();
    }

    @Test
    @DisplayName("C5872231 Проверка формы 'Политика конфиденциальности'")
    @Description("В тесте не проверяется отображение скролл-бара " +
            "(скролл-бар тут - визуальный элемент, с ним нельзя взаимодействовать)")
    public void privacyPolicyFormTest() {
        assertEquals("true", enterPhoneForRegistrationPage.isPrivacyPolicyNoticeClickable(),
                ELEMENT_NOT_CLICKABLE);
        enterPhoneForRegistrationPage.clickOnPrivacyPolicyNotice();
        assertAll("Проверка экрана 'Политика конфиденциальности'",
                () -> assertEquals("Регистрация", enterPhoneForRegistrationPage.getRegistrationPageHeaderText(),
                        HEADER_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertEquals("Политика конфиденциальности", privacyPolicyPage.getPrivacyPolicyTitleText(),
                        DESCRIPTION_TEXT_INCORRECT_OR_NOT_DISPLAYED),
                () -> assertEquals("Вернуться назад", privacyPolicyPage.getPrimaryButtonText(),
                        PRIMARY_BUTTON_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT),
                () -> assertTrue(privacyPolicyPage.isBurgerMenuButtonDisplayed(),
                        BURGER_MENU_BUTTON_NOT_DISPLAYED)
        );
        privacyPolicyPage.clickOnBackButton();
    }

    @Test
    @DisplayName("C5872233 Проверка кнопки 'Вернуться назад' на странице 'Политика конфиденциальности'")
    public void backButtonOnPrivacyPolicyPageTest() {
        enterPhoneForRegistrationPage.clickOnPrivacyPolicyNotice();
        assertEquals("Политика конфиденциальности", privacyPolicyPage.getPrivacyPolicyTitleText(),
                EXPECTED_PAGE_DID_NOT_OPEN);
        privacyPolicyPage.clickOnPrimaryButton();
        assertEquals("Введите номер телефона", enterPhoneForRegistrationPage.getEnterPhoneNumberTitleText(),
                EXPECTED_PAGE_DID_NOT_OPEN);
    }

    @Test
    @DisplayName("C5872234 Проверка сохранения данных в поле 'Введите номер телефона' " +
            "при нажатии кнопки 'Вернуться назад'  на странице 'Политика конфиденциальности'")
    @Disabled("Issue: AFI-2612")
    public void phoneNumberRemainedAfterReturnFromPrivacyPolicyPageTest() {
        enterPhoneForRegistrationPage.enterPhoneNumber(RU_PHONE_NUMBER);
        assertEquals("+7 777 444 9884", enterPhoneForRegistrationPage.getPhoneNumberInputText(),
                VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED);
        enterPhoneForRegistrationPage.clickOnPrivacyPolicyNotice();
        assertEquals("Политика конфиденциальности", privacyPolicyPage.getPrivacyPolicyTitleText(),
                EXPECTED_PAGE_DID_NOT_OPEN);
        privacyPolicyPage.clickOnPrimaryButton();
        assertEquals("Введите номер телефона", enterPhoneForRegistrationPage.getEnterPhoneNumberTitleText(),
                EXPECTED_PAGE_DID_NOT_OPEN);
        assertEquals("+7 777 444 9884", enterPhoneForRegistrationPage.getPhoneNumberInputText(),
                VALUE_INCORRECTLY_DISPLAYED_OR_NOT_DISPLAYED);
    }
}
