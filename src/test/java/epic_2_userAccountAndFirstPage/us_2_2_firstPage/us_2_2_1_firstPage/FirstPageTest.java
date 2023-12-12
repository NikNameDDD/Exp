package epic_2_userAccountAndFirstPage.us_2_2_firstPage.us_2_2_1_firstPage;

import baseTest.BaseTest;

import io.appium.java_client.MobileElement;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static data.UserData.RU_PASSWORD;
import static data.UserData.RU_PHONE_NUMBER;
import static constants.ErrorMessages.FIRST_PAGE_ELEMENT_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT;
import static constants.TestConstants.VALID_PIN;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Epic-2 Личный кабинет/Основное меню")
@Feature("US-2.2 Основное меню")
@Story("US-2.2.1 Основное меню")
//Аннотация Order добавлена, чтобы сохранить тесты независимыми и при этом
// сократить время их выполнения. Если тест со скроллом выполняется первым,
// то возникает необходимость добавить метод AfterEach, который будет после
// каждого теста скроллить первую страницу к изначальному состоянию. Если
// первым выполняется тест без скролла, то необходимости в AfterEach нет
// и за счет этого все тесты класса выполняются быстрее.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FirstPageTest extends BaseTest {

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

    @ParameterizedTest
    @MethodSource
    @DisplayName("C5883127 Проверка формы 'Основное меню' - отображение элементов, которые видны без скролла")
    @Order(1)
    public void firstPageElementsTest(MobileElement element) {
        assertTrue(element.isDisplayed(), FIRST_PAGE_ELEMENT_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT);
    }

    Stream<MobileElement> firstPageElementsTest() {
        return Stream.of(
                firstPage.getUserName(),
                firstPage.getAvatar(),

                firstPage.getCardsTextButton(),
                firstPage.getCardsDropdown(),
                firstPage.getDepositsTextButton(),
                firstPage.getDepositsDropdown(),
                firstPage.getLoansTextButton(),
                firstPage.getLoansDropdown(),
                firstPage.getInsuranceTextButton(),
                firstPage.getInsuranceDropdown(),
                firstPage.getInvestmentTextButton(),
                firstPage.getInvestmentDropdown(),
                firstPage.getPromotionsAndRecommendationsTextButton(),
                firstPage.getPromotionsAndRecommendationsDropdown(),

                firstPage.getAddItemButton(),
                firstPage.getDepositingMobileTopMenuButton(),
                firstPage.getDepositingMobileTopMenuIcon(),
                firstPage.getMoneyTransferToCardTopMenuButton(),
                firstPage.getMoneyTransferToCardTopMenuIcon(),
                firstPage.getUtilityServicesTopMenuButton(),
                firstPage.getUtilityServicesTopMenuIcon(),

                firstPage.getMainPageNavigationBarButton(),
                firstPage.getMainPageNavigationBarIcon(),
                firstPage.getPaymentsNavigationBarButton(),
                firstPage.getPaymentsNavigationBarIcon(),
                firstPage.getTransfersNavigationBarButton(),
                firstPage.getTransfersNavigationBarIcon(),
                firstPage.getPaymentRecordsNavigationBarButton(),
                firstPage.getPaymentRecordsNavigationBarIcon(),
                firstPage.getMoreNavigationBarButton(),
                firstPage.getMoreNavigationBarIcon()
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("C5883127 Проверка формы 'Основное меню' - отображение элементов, которые видны после скролла")
    @Order(2)
    public void firstPageElementsDisplayedAfterScrollTest(MobileElement element) {
        firstPage.scrollMainMenuDown();
        firstPage.scrollTopMenuToRight();
        assertTrue(element.isDisplayed(), FIRST_PAGE_ELEMENT_NOT_DISPLAYED_OR_HAS_INCORRECT_TEXT);
    }

    Stream<MobileElement> firstPageElementsDisplayedAfterScrollTest() {
        return Stream.of(
                firstPage.getExchangeRatesTextButton(),
                firstPage.getExchangeRatesDropdown(),
                firstPage.getAnalyticsTextButton(),
                firstPage.getAnalyticsDropdown(),
                firstPage.getCurrencyExchangeTopMenuButton(),
                firstPage.getCurrencyExchangeTopMenuIcon()
        );
    }
}