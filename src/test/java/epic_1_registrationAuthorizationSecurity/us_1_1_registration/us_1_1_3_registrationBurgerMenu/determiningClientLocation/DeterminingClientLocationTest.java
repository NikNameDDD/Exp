package epic_1_registrationAuthorizationSecurity.us_1_1_registration.us_1_1_3_registrationBurgerMenu.determiningClientLocation;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static constants.ErrorMessages.EXPECTED_PAGE_DID_NOT_OPEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static constants.ErrorMessages.LOCATION_PIN_NOT_DISPLAYED;

@Epic("Epic-1 Регистрация/Авторизация/Безопасность")
@Feature("US-1.1 Регистрация")
@Story("US-1.1.3 Регистрация (Бургерное меню)")
public class DeterminingClientLocationTest extends BaseTest {

    @BeforeEach
    public void goToMapPage() {
        mainPage.clickOnBurgerMenuButton();
        burgerMenuPage.clickOnATMsAndBranchesButton();
        allowAccessToGeoPositionPage.clickOnPrimaryButton();
    }

    @DisplayName("C5888883 Проверка логики 'Отклонить' в попапе доступа к местоположению")
    @Test
    public void denyButtonTest() {
        atmsAndBranchesMapPage.clickOnDenyButton();
        atmsAndBranchesMapPage.clickOnDetermineLocationButton();
        atmsAndBranchesMapPage.isMessageOnAbsenceDisplayed();
    }

    @DisplayName("C5888895, C5888900 Проверка логики 'Разрешить только сейчас'" +
            "в попапе доступа к местоположению")
    @Test
    @Disabled("Issue: AFI-2485")
    public void onlyThisTimeButtonTest() {
        atmsAndBranchesMapPage.clickOnOnlyThisTimeButton();
        atmsAndBranchesMapPage.scrollMap();
        assertTrue(atmsAndBranchesMapPage.isLocationPinDisplayed(), LOCATION_PIN_NOT_DISPLAYED);
        basePage.restartAppViaAppIcon();
        mainPage.clickOnBurgerMenuButton();
        burgerMenuPage.clickOnATMsAndBranchesButton();
        assertEquals("Разрешите доступ к вашей геопозиции", allowAccessToGeoPositionPage.getShortDescriptionText(),
                EXPECTED_PAGE_DID_NOT_OPEN);
    }

    @DisplayName("C5888898, C5888902 Проверка логики 'Разрешить только при использовании приложения'" +
            "в попапе доступа к местоположению")
    @Test
    public void whileUsingTheAppButtonTest() {
        atmsAndBranchesMapPage.clickOnWhileUsingTheAppButton();
        atmsAndBranchesMapPage.scrollMap();
        assertTrue(atmsAndBranchesMapPage.isLocationPinDisplayed(), LOCATION_PIN_NOT_DISPLAYED);
        basePage.restartAppViaAppIcon();
        mainPage.clickOnBurgerMenuButton();
        burgerMenuPage.clickOnATMsAndBranchesButton();
        assertTrue(atmsAndBranchesMapPage.isMapLinearLayoutDisplayed(), EXPECTED_PAGE_DID_NOT_OPEN);
    }

    @AfterEach
    public void restartApplication() {
        atmsAndBranchesMapPage.restartApp();
    }
}