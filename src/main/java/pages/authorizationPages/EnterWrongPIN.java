package pages.authorizationPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import pages.BasePage;

public class EnterWrongPIN extends BasePage {//C5870815

    public EnterWrongPIN(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @AndroidFindBy(id = " ru.andersen.afinny:id/afinny_widget_pinpad_button_1")
    private EnterWrongPIN unCorPIN;

    @Step("Ввод некоректного пинКода №1" )
    public String EnterWrongPIN(){
        unCorPIN.cl
    }

    }


