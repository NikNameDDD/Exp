package epic_2_userAccountAndFirstPage.us_2_1_userAccount.us_2_1_2_securitySection.change_password_section;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.User;

import static constants.ErrorMessages.ACTIVE;
import static constants.ErrorMessages.DOES_NOT_MATCH_WITH_EXPECTED;
import static constants.ErrorMessages.NOT_ACTIVE;
import static constants.ErrorMessages.NOT_VISIBLE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Epic-2 Личный кабинет/Основное меню")
@Feature("US-2.1 Личный кабинет")
@Story("US-2.1.2 Настройка безопасности")
public class ChangePasswordTests extends BaseTest {

    private static User user;
    private static String userId;

    @BeforeAll
    public static void setTestBeforeAll() {
        user = userDataProvider.getUserWithEmailFromPropertyFile();
        userId = appOperations.createNonExistentClient(user);
        appOperations.openPersonalDataMenu(user);
        personalDataMenu.clickSecurityButton();
    }

    @BeforeEach
    public void setBeforeEach() {
       securitySettingsPage.clickChangePasswordOption();
    }

    @DisplayName("C5895118 Проверка ввода некорректного пароля в поле 'Введите старый пароль'")
    @Test
    public void changePasswordWhenIncorrectOldPasswordTest() {
        String password  = "qq!Port43";
        assertAll(
                "Поверка поведения системы при вводе некорректного старого пароля",
                () -> assertEquals(dataGenerator.generateDots(password.length()),
                        changePasswordPage.inputOldPassword(password).getTextFromOldPasswordInputField(),
                        "текст в поле ввода старого пароля" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("Введите новый пароль",
                        changePasswordPage.inputNewPassword(password).getEnterNewPasswordLabelText(),
                        "текст лейбла над полем ввода нового пароля" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertTrue(changePasswordPage.isPasswordToggleInNewPasswordFieldVisible(),
                        "кнопка 'Показать пароль в поле нового пароля'" + NOT_VISIBLE),
                () -> assertEquals(dataGenerator.generateDots(password.length()),
                        changePasswordPage.getTextFromNewPasswordInputField(),
                        "текст в поле ввода нового пароля" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("Есть заглавные латинские буквы",
                        changePasswordPage.getTextFromPasswordUpperCaseChecker(),
                        "текст в проверке наличия верхнего регистра" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("Есть строчные латинские буквы",
                        changePasswordPage.getTextFromPasswordLowerCaseChecker(),
                        "текст в проверке наличия нижнего регистра" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("Есть цифры",
                        changePasswordPage.getTextFromPasswordDigitsChecker(),
                        "текст в проверке наличия цифр" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("Есть специальные символы !  $ & ' ( ) * + , - . : ; = [  ] ^ _` { | } ~",
                        changePasswordPage.getTextFromPasswordSpecialSymbolsChecker(),
                        "текст в проверке наличия специальных символов" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("Подтвердите новый пароль",
                        changePasswordPage.inputConfirmPassword(password).getConfirmPasswordLabelText(),
                        "текст лейбла над полем ввода подтверждения пароля" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals(dataGenerator.generateDots(password.length()),
                        changePasswordPage.getTextFromConfirmPasswordInputField(),
                        "текст в поле ввода подтверждения пароля" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertTrue(changePasswordPage.isChangePasswordButtonActive(), "кнопка 'Изменить'" + NOT_ACTIVE),
                () -> assertEquals("Некорректный старый пароль. Попробуйте еще раз.",
                        changePasswordPage.clickChangePasswordButton().getTextFromOldPasswordErrorMessage(),
                        "текст о неверном старом пароле" + DOES_NOT_MATCH_WITH_EXPECTED)
                );
    }

    @Test
    @DisplayName("C5894856 Проверка ввода несовпадающих валидных паролей с разным количеством символов в полях " +
            "'Введите новый пароль' и 'Подтвердите пароль'")
    public void changePasswordWhenNewPasswordAndConfirmationDoNotMatchInLength() {
        String password  = "qq!Port43";
        changePasswordPage.inputOldPassword(user.getPassword());
        assertAll(
                "Поверка поведения системы при вводе несовпадающих по длине нового пароля и подтверждения",
                () -> assertEquals("Введите новый пароль",
                        changePasswordPage.inputNewPassword(password).getEnterNewPasswordLabelText(),
                        "текст лейбла над полем ввода нового пароля" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals(dataGenerator.generateDots(password.length()),
                        changePasswordPage.getTextFromNewPasswordInputField(),
                        "текст в поле ввода нового пароля" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals("Подтвердите новый пароль",
                        changePasswordPage.inputConfirmPassword(password + "!").getConfirmPasswordLabelText(),
                        "текст лейбла над полем ввода подтверждения пароля" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertEquals(dataGenerator.generateDots(password.length() + 1),
                        changePasswordPage.getTextFromConfirmPasswordInputField(),
                        "текст в поле ввода подтверждения пароля" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertFalse(changePasswordPage.inputNewPassword("").clickPageHeadline()
                        .isChangePasswordButtonActive(), "кнопка 'Изменить'" + ACTIVE),
                () -> assertEquals("Пароли не совпадают", changePasswordPage.getErrorMessageText(),
                        "текст о несовпадении пароля подтверждения" + DOES_NOT_MATCH_WITH_EXPECTED)
        );
    }

    @Test
    @DisplayName("C6304130 Проверка ввода пароля в поле 'Подтвердите пароль', отличающегося по содержанию от нового")
    public void changePasswordWhenNewPasswordAndConfirmationDoNotMatchInContent() {
        String password  = "qwerty12!";
        changePasswordPage.inputOldPassword(user.getPassword());
        changePasswordPage.inputNewPassword(password);
        assertAll(
                "Поверка поведения системы при вводе несовпадающих по содержанию нового пароля и подтверждения",
                () -> assertEquals(dataGenerator.generateDots(password.length()),
                        changePasswordPage.inputConfirmPassword(new StringBuilder(password).reverse().toString())
                                .getTextFromConfirmPasswordInputField(),
                        "текст в поле ввода подтверждения пароля" + DOES_NOT_MATCH_WITH_EXPECTED),
                () -> assertFalse(changePasswordPage.inputNewPassword("").clickPageHeadline()
                        .isChangePasswordButtonActive(), "кнопка 'Изменить'" + ACTIVE),
                () -> assertEquals("Пароли не совпадают", changePasswordPage.getErrorMessageText(),
                        "текст о несовпадении пароля подтверждения" + DOES_NOT_MATCH_WITH_EXPECTED)
        );
    }

    @AfterEach
    public void setAfterEach() {
        changePasswordPage.clickOnBackButton();
    }

    @AfterAll
    public static void setAfterAll() {
        databaseHandler.deleteUserFromUserServiceDataBaseById(userId);
        mainPage.closeApp();
    }
}