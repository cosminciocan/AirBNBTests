package com.steps;

import com.pages.MoreFiltersPage;
import com.pages.PropertyPage;
import com.pages.SearchResultsPage;
import net.thucydides.core.annotations.Step;

import static org.junit.Assert.assertTrue;

public class FilterSteps {
    private SearchResultsPage searchResultsPage;
    private MoreFiltersPage moreFiltersPage;
    private PropertyPage propertyPage;

    @Step
    public void clickMoreFilters() {
        searchResultsPage.clickMoreFiltersButton();
    }

    @Step
    public void setFiveBedroomProperties() {
        moreFiltersPage.setNumberOfBedrooms(5);
    }

    @Step
    public void setAmenityHasPool() {
        moreFiltersPage.setHasPoolInput();
    }

    @Step
    public void clickShowStays() {
        moreFiltersPage.clickShowStaysButton();
    }

    @Step
    public void verifyPropertiesHaveMinimumNumberOfBedrooms() {
        assertTrue(searchResultsPage.isNumberOfBedroomsCorrect(5));
    }

    @Step
    public void openFirstProperty() {
        searchResultsPage.clickFirstProperty();
        searchResultsPage.switchToNewWindow();
    }

    @Step
    public void verifyPropertyHasPool() {
        assertTrue(propertyPage.verifyAmenityInList("Pool"));
    }

    @Step
    public void checkPoolIsSelected() {
        propertyPage.clickViewAllAmenitiesButton();
        assertTrue(propertyPage.checkIfPoolIsDisplayed());
    }
}
