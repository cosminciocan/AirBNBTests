package com.features;

import com.steps.FilterSteps;
import com.steps.SearchSteps;
import com.utils.BaseTest;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;


public class FilterTests extends BaseTest {


    @Steps
    private SearchSteps searchSteps;
    @Steps
    private FilterSteps filterSteps;

    @Test
    public void verifyResultsAndDetailsPageMatchExtraFilters(){
        searchSteps.openHomepage();
        searchSteps.performSearchForRomeWithGuests();
        filterSteps.clickMoreFilters();
        filterSteps.setFiveBedroomProperties();
        filterSteps.setAmenityHasPool();
        filterSteps.clickShowStays();
        filterSteps.verifyPropertiesHaveMinimumNumberOfBedrooms();
        filterSteps.openFirstProperty();
        filterSteps.verifyPropertyHasPool();
        filterSteps.checkPoolIsSelected();
    }
}
