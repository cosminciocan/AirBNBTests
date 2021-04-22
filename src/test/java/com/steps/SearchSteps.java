package com.steps;

import com.pages.HomePage;
import com.pages.PropertyPage;
import com.pages.SearchResultsPage;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchSteps {

    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private PropertyPage propertyPage;

    @Step
    public void openHomepage() {
        homePage.open();
    }

    @Step
    public void setLocationSearchField(String value) {
        homePage.setLocationSearchField(value);
    }

    @Step
    public void selectLocationFromDropdown(String value) {
        assertTrue(homePage.selectLocationDropdownOption(value));
    }

    @Step
    public void setLocationRome() {
        setLocationSearchField("Rome");
        selectLocationFromDropdown("Rome, Italy");
    }

    @Step
    public void setDateByFutureWeeks(long noOfWeeks) {
        homePage.selectDateByWeeks(noOfWeeks);
    }

    @Step
    public void setTripDates() {
        setDateByFutureWeeks(1);
        setDateByFutureWeeks(2);
    }

    @Step
    public void setNumberOfAdults(int number) {
        homePage.setAdultsNumber(number);
    }

    @Step
    public void clickAddGuests() {
        homePage.clickAddGuests();
    }

    @Step
    public void setNumberOfChildren(int number) {
        homePage.setChildrenNumber(number);
    }

    @Step
    public void setGuests() {
        clickAddGuests();
        setNumberOfAdults(2);
        setNumberOfChildren(1);
    }

    @Step
    public void clickSearch() {
        homePage.clickSearch();
    }

    @Step
    public void verifyTopFilterLocationIsRome() {
        assertEquals("Rome", searchResultsPage.getTopSelectedLocation());
    }


    @Step
    public void verifyTopFilterDatesNextWeek() {
        String expectedCheckin = searchResultsPage.getDateNextWeeks("MMM d", 1);
        String expectedCheckout = searchResultsPage.getDateNextWeeks("MMM d", 2);
        String expectedDatesInterval = expectedCheckin + " – " + expectedCheckout;
        assertEquals(expectedDatesInterval, searchResultsPage.getSelectedTopDates());
    }

    @Step
    public void verifyTopFilterGuestsNumber() {
        assertEquals("3 guests", searchResultsPage.getTopGuetsValue());
    }

    @Step
    public void verifyTopPageFilterDetails() {
        verifyTopFilterLocationIsRome();
        verifyTopFilterDatesNextWeek();
        verifyTopFilterGuestsNumber();
    }

    @Step
    public void verifySearchResultsHeader() {
        String expectedCheckin = searchResultsPage.getDateNextWeeks("MMM d", 1);
        String expectedCheckout = searchResultsPage.getDateNextWeeks("MMM d", 2);
        String expectedHeader = expectedCheckin + " - " + expectedCheckout + " · 3 guests";
        assertEquals(expectedHeader, searchResultsPage.getSearchResultsHeaderWithoutNoOfStays());
    }

    @Step
    public void verifyPropertiesMinimumNumberOfGuests() {
        assertTrue(searchResultsPage.isNumberOfOccupantsCorrect(3));
    }

    @Step
    public void performSearchForRomeWithGuests() {
        setLocationRome();
        setTripDates();
        setGuests();
        clickSearch();
    }


    @Step
    public void getPropertyNameAndPrice() {
        Serenity.getCurrentSession().put("property", searchResultsPage.getPropertyDetails(0));
    }

    @Step
    public void searchForPropertyWithLowestPrice() {
        Serenity.getCurrentSession().put("lowestProperty", searchResultsPage.getLowestPricePropertyDetails());
       searchResultsPage.hoverOverLowestProperty();
       searchResultsPage.clickActivePropertyOnMap();
       searchResultsPage.clickDisplayedNameFromMapModal();
        searchResultsPage.switchToNewWindow();
    }

    @Step
    public void verifyDetailsOnPropertyPage(){
        String expexted = Serenity.getCurrentSession().get("lowestProperty").toString();
        String actual = propertyPage.getPagePropertyDetails().toString();
        assertEquals(expexted, actual);
    }

}
