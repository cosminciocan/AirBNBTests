package com.pages;

import com.utils.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebElement;


public class MoreFiltersPage extends BasePage {

    @FindBy(css = "[data-testid='filterItem-rooms_and_beds-stepper-min_bedrooms-0-increase-button']")
    private WebElementFacade increaseBedroomsButton;

    @FindBy(css = "[data-testid='filterItem-facilities-checkbox-amenities-7']")
    private WebElement hasPoolInput;

    @FindBy(css = "button[data-testid='more-filters-modal-submit-button']")
    private WebElementFacade showStaysButton;


    public void clickIncreaseBedroomsButton() {
        clickOn(increaseBedroomsButton);
    }

    public void setNumberOfBedrooms(int number) {
        for (int i = 0; i < number; i++) {
            clickIncreaseBedroomsButton();
        }
    }

    public void setHasPoolInput() {
        clickOn(hasPoolInput);
    }

    public void clickShowStaysButton() {
        clickOn(showStaysButton);
        getDriver().navigate().refresh();
    }

}
