package com.pages;

import com.entity.Property;
import com.utils.BasePage;
import com.utils.Constants;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

@DefaultUrl("https://www.airbnb.com/s/Rome--Italy/homes?tab_id=home_tab&refinement_paths%5B%5D=%2Fhomes&flexible_trip_dates%5B%5D=april&flexible_trip_dates%5B%5D=may&flexible_trip_lengths%5B%5D=weekend_trip&date_picker_type=calendar&query=Rome%2C%20Italy&place_id=ChIJw0rXGxGKJRMRAIE4sppPCQM&checkin=2021-04-29&checkout=2021-05-06&adults=2&children=1&source=structured_search_input_header&search_type=autocomplete_click")
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

    @FindBy(css = "//div[@itemprop='itemListElement']")
    private List<WebElementFacade> listOfProperties;

    @FindBy(xpath = "//div[@itemprop='itemListElement']")
    private List<WebElementFacade> listOfPropertiesXpath;

    @FindBy(css = "div[style*='z-index: 9999'] button")
    private WebElementFacade activePropertyOnMap;

    @FindBy(xpath = "//*[@aria-label='Map']")
    private WebElementFacade mapArea;

    @FindBy(xpath = "//div[@itemprop='itemListElement']//div[@role=\"group\"]//div[@aria-hidden]/span[contains(.,'lei')]")
    private List<WebElementFacade> allPricesList;



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

    public String getPriceForListing(WebElement element) {
        List<WebElement> elements = element.findElements(By.xpath("//div[@role=\"group\"]//div[@aria-hidden]/span[contains(.,'lei')]"));
        if (elements.size() > 1)
            return elements.get(1).getText();

        return elements.get(0).getText();
    }

    public void clickActivePropertyOnMap() {
        clickOn(activePropertyOnMap);
    }

    private String getPropertyDisplayedNameFromModal(String propertyName) {
        return mapArea
                .findElement(By.xpath("//a[@aria-label='" + propertyName + "'][@rel]/../div[2]//span[contains(@style, 'display')]"))
                .getText();
    }

    private String getPropertyDisplayedPriceFromModal(String propertyName) {
        return mapArea
                .findElement(By.xpath("//a[@aria-label='" + propertyName + "'][@rel]/../div[2]//div[@aria-hidden]/span[1]"))
                .getText();
    }

    public Property getDisplayedPropertyDetailsMap(String propertyName) {
        Property property = new Property();
        property.setName(getPropertyDisplayedNameFromModal(propertyName));
        property.setPricePerNight(getPropertyDisplayedPriceFromModal(propertyName));
        return property;
    }

    private int getPriceInt(String text) {
        return Integer.parseInt(text.replace("lei ", "").replaceAll(",",""));
    }

    private int getLowestPriceOnPage(){
        waitABit(1500);
        int lowest = getPriceInt(allPricesList.get(0).getText());
        for (WebElementFacade element : allPricesList){
            if (getPriceInt(element.getText()) < lowest)
                lowest = getPriceInt(element.getText());
        }
        return lowest;
    }

    public void hoverPropertyByPrice(int price){
        Actions action = new Actions(getDriver());
        action.moveToElement( getDriver().findElement(By.xpath("//div[@itemprop='itemListElement'][contains(.,'lei "+price+"')]"))).perform();
    }

    public String hoverOverLowestProperty(){
        WebElement element = getDriver().findElement(By.xpath("//div[@itemprop='itemListElement'][contains(.,'lei "+getLowestPriceOnPage()+"')]"));
        Actions action = new Actions(getDriver());
        action.moveToElement(element).perform();
        return  element.findElement(By.cssSelector("[id^='title_'")).getText();
    }

    public void clickDisplayedNameFromMapModal(){
        clickOn(mapArea.findElement(By.xpath("//*[@aria-label='Map']//div/span//a")));
    }

    public Property getLowestPricePropertyDetails() {
        WebElement element = getDriver().findElement(By.xpath("//div[@itemprop='itemListElement'][contains(.,'lei "+getLowestPriceOnPage()+"')]"));
        Property property = new Property();
        property.setName(element.findElement(By.cssSelector("[id^='title_'")).getText());
//        property.setPricePerNight(getPriceForListing(element));
        return property;
    }

}
