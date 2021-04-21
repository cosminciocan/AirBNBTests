package com.pages;

import com.entity.Property;
import com.utils.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class SearchResultsPage extends BasePage {


    @FindBy(css = "div[data-testid='little-search'] [data-index='0'] div")
    private WebElementFacade topLocationButton;

    @FindBy(css = "div[data-testid='little-search'] [data-index='1'] div")
    private WebElementFacade topDatesButton;

    @FindBy(css = "div[data-testid='little-search'] [data-index='2'] div")
    private WebElementFacade topGuestsButton;

    @FindBy(css = "#ExploreLayoutController section div:nth-child(1)")
    private WebElementFacade searchResultsHeaderDiv;

    @FindBy(css = "[itemprop='itemListElement'] div[role='group'] > div:nth-of-type(2) > div:nth-child(3) > span:nth-child(1)")
    private List<WebElementFacade> numberOfGuestsPerPropertyLabel;

    @FindBy(css = "#menuItemButton-dynamicMoreFilters button")
    private WebElementFacade moreFiltersButton;

    @FindBy(css = "[itemprop='itemListElement'] div[role='group'] > div:nth-of-type(2) > div:nth-child(3) > span:nth-child(3)")
    private List<WebElementFacade> numberOfBedroomsPerPropertyLabel;

    @FindBy(css = "div[itemprop='itemListElement']")
    private List<WebElementFacade> listOfProperties;

    @FindBy(css = "div[style*='z-index: 9999'] button")
    private WebElement activePropertyOnMap;

    public String getTopSelectedLocation() {
        waitFor(topLocationButton);
        return topLocationButton.getText();
    }


    public String getSelectedTopDates() {
        waitFor(topDatesButton);
        return topDatesButton.getText();
    }


    public String getTopGuetsValue() {
        waitFor(topGuestsButton);
        return topGuestsButton.getText();
    }

    public String getSearchResultsHeaderWithoutNoOfStays() {
        waitFor(searchResultsHeaderDiv);
        return searchResultsHeaderDiv.getText().replaceAll(".+ stays · ", "");
    }

    public boolean isNumberOfOccupantsCorrect(int expectedMinimum) {
        return isNumberCorrectPerElement(numberOfGuestsPerPropertyLabel, expectedMinimum);
    }

    public void clickMoreFiltersButton() {
        clickOn(moreFiltersButton);
    }

    public boolean isNumberOfBedroomsCorrect(int expectedMinimum) {
        return isNumberCorrectPerElement(numberOfBedroomsPerPropertyLabel, expectedMinimum);
    }

    private boolean isNumberCorrectPerElement(List<WebElementFacade> element, int number) {
        Assert.assertTrue(element.size() > 0);
        for (WebElementFacade elementF : element) {
            int noOfGuests = Integer.parseInt(elementF.getText().replaceAll(" guests", "").replaceAll(" bedrooms", ""));
            if (noOfGuests < number) {
                return false;
            }
        }
        return true;
    }

    public void clickFirstProperty() {
        listOfProperties.get(0).click();
    }

    public Property getPropertyDetails(int propertyPosition) {
        Property property = new Property();
        property.setName(listOfProperties.get(propertyPosition).findElement(By.cssSelector("[id^='title_'")).getText());
        property.setPricePerNight(getPriceForListing(listOfProperties.get(propertyPosition)));
        return property;
    }

    public void hoverOverProperty() {
        Actions action = new Actions(getDriver());
        action.moveToElement(listOfProperties.get(0)).perform();
    }

    public String getPriceForListing(WebElementFacade element) {
        return element.findBy(By.xpath("//div[@role=\"group\"]//div[@aria-hidden]/span[1][contains(.,'lei')]")).getText();
    }

    public void clickActivePropertyOnMap() {
        clickOn(activePropertyOnMap);
    }

    private String getPropertyDisplayedNameFromModal(String propertyName) {
        return getDriver()
                .findElement(By.xpath("//*[@aria-label='Map']//a[@aria-label='" + propertyName + "'][@rel]/../div[2]//span[contains(@style, 'display')]"))
                .getText();
    }

    private String getPropertyDisplayedPriceFromModal(String propertyName) {
        return getDriver()
                .findElement(By.xpath("//*[@aria-label='Map']//a[@aria-label='" + propertyName + "'][@rel]/../div[2]//div[@aria-hidden]/span[1]"))
                .getText();
    }

    public Property getDisplayedPropertyDetailsMap(String propertyName) {
        Property property = new Property();
        property.setName(getPropertyDisplayedNameFromModal(propertyName));
        property.setPricePerNight(getPropertyDisplayedPriceFromModal(propertyName));
        return property;
    }
}
