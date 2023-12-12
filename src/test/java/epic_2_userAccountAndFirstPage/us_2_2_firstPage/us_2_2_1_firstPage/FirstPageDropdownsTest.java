package epic_2_userAccountAndFirstPage.us_2_2_firstPage.us_2_2_1_firstPage;

import baseTest.BaseTest;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static data.UserData.RU_PASSWORD;
import static data.UserData.RU_PHONE_NUMBER;
import static constants.ErrorMessages.FIRST_PAGE_ELEMENT_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT;
import static constants.TestConstants.VALID_PIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Epic-2 Личный кабинет/Основное меню")
@Feature("US-2.2 Основное меню")
@Story("US-2.2.1 Основное меню")
public class FirstPageDropdownsTest extends BaseTest {

    @BeforeAll
    public static void authorizeInApp() {
        mainPage.clickOnLoginButton();
        enterLoginDataPage
                .chooseLoginTypeByRUPhoneNumber()
                .enterPhoneNumber(RU_PHONE_NUMBER)
                .enterPassword(RU_PASSWORD)
                .clickOnPrimaryButton();
        installPINPage
                .enterPin(VALID_PIN)
                .enterPin(VALID_PIN);
        pinCreatedPage.clickOnPrimaryButton();
    }

    @Test
    @DisplayName("C5883576 Проверка открытия сразу нескольких аккордеонов")
    public void severalOpenDropdownsTest() {
        assertTrue(firstPage.isCardsContentsDisplayed(),
                FIRST_PAGE_ELEMENT_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT);
        firstPage.getDepositsDropdown().click();
        firstPage.getLoansDropdown().click();
        firstPage.getInsuranceDropdown().click();
        firstPage.getCardsDropdown().click();
        assertEquals(3, firstPage.countOpenDropdowns(),
                "Проблема с одновременным открытием нескольких дропдаунов");
    }
}