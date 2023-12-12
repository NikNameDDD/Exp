package epic_1_registrationAuthorizationSecurity.us_1_1_registration.us_1_1_3_registrationBurgerMenu.mapSection;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static constants.ErrorMessages.ELEMENT_NOT_SELECTED;
import static constants.ErrorMessages.ELEMENT_SELECTED;

@Epic("Epic-1 Регистрация/Авторизация/Безопасность")
@Feature("US-1.1 Регистрация")
@Story("US-1.1.3 Регистрация (Бургерное меню)")
public class MapLinearLayoutTest extends BaseTest {
    @BeforeEach
    public void burgerToolbarButtonAndATMsAndBranchesButtonClick() {
        mainPage.clickOnBurgerMenuButton();
        burgerMenuPage.clickOnATMsAndBranchesButton();
        allowAccessToGeoPositionPage.clickOnPrimaryButton();
    }

    @DisplayName("Проверка кнопки 'Карта' на странице 'Карта' (Бургер) C5888906")
    @Test
    public void mapButtonSelectionTest() {
        atmsAndBranchesMapPage.clickOnOnlyThisTimeButton();
        atmsAndBranchesMapPage.mapLinearLayoutClick();
        assertAll("Проверка отображения карты после клика на кнопку 'Карта'",
                () -> assertTrue(atmsAndBranchesMapPage.isMapLinearLayoutDisplayed(), "Не отображается кнопка 'Карта'"),
                () -> assertTrue(atmsAndBranchesMapPage.listLinearLayoutChecking(), "Не отображается кнопка 'Список'"),
                () -> assertTrue(atmsAndBranchesMapPage.mapLinearLayoutSelectedChecking(), ELEMENT_NOT_SELECTED),
                () -> assertFalse(atmsAndBranchesMapPage.listLinearLayoutSelectedChecking(), ELEMENT_SELECTED));
    }
}
