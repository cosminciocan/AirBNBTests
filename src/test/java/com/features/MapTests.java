package com.features;

import com.steps.MapSteps;
import com.steps.SearchSteps;
import com.utils.BaseTest;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;

public class MapTests extends BaseTest {

    @Steps
    private SearchSteps searchSteps;
    @Steps
    private MapSteps mapSteps;

    @Test
    public void verifyPropertyDisplayedOnMap(){
        searchSteps.openHomepage();
        searchSteps.performSearchForRomeWithGuests();
        searchSteps.getPropertyNameAndPrice();
        mapSteps.hoverOverFirstProperty();
        mapSteps.selectActivePropertyOnMap();
        mapSteps.verifyDisplayedDetailsFromMap();
    }

}
