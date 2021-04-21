package com.features;

import com.steps.SearchSteps;
import com.utils.BaseTest;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;


public class SearchTests extends BaseTest {

    @Steps
    private SearchSteps steps;

    @Test
    public void verifyResultsMatchSearchCriteriaTest(){
        steps.openHomepage();
        steps.setLocationRome();
        steps.setTripDates();
        steps.setGuests();
        steps.clickSearch();
        steps.verifyTopPageFilterDetails();
        steps.verifySearchResultsHeader();
        steps.verifyPropertiesMinimumNumberOfGuests();
    }





}
