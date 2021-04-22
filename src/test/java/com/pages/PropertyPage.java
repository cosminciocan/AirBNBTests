package com.pages;

import com.entity.Property;
import com.utils.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;

import java.util.List;

public class PropertyPage extends BasePage {

    @FindBy(css = "div[data-section-id='AMENITIES_DEFAULT']  div:nth-child(2) > div > div > div:nth-child(1)")
    private List<WebElementFacade> listOfAmenities;

    @FindBy(css = "a[href*='amenities']")
    private WebElementFacade viewAllAmenitiesButton;

    @FindBy(xpath = "//div[@class='_aujnou']/div[.='Pool']")
    private WebElementFacade poolAmenitiesLabel;

    @FindBy(css = "[data-section-id='TITLE_DEFAULT'] h1")
    private WebElementFacade pageTitle;

    @FindBy(xpath = "//div[@data-section-id=\"BOOK_IT_SIDEBAR\"]//span/span[contains(.,'lei ')][@aria-hidden='true']")
    private WebElementFacade pricePerNightLabel;


    public boolean verifyAmenityInList(String amenity) {
        Assert.assertTrue(listOfAmenities.size() != 0);
        for (WebElementFacade element : listOfAmenities)
            if (element.getText().equalsIgnoreCase(amenity))
                return true;

        return false;
    }

    public void clickViewAllAmenitiesButton() {
        clickOn(viewAllAmenitiesButton);
    }

    public boolean checkIfPoolIsDisplayed() {
        waitFor(poolAmenitiesLabel);
        return poolAmenitiesLabel.isDisplayed();
    }


    public Property getPagePropertyDetails(){
        Property property = new Property();
        property.setName(pageTitle.getText());
//        property.setPricePerNight(pricePerNightLabel.getText());
        return property;

    }
}
