package com.steps;

import com.entity.Property;
import com.pages.SearchResultsPage;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

public class MapSteps {

    private SearchResultsPage searchResultsPage;

    @Step
    public void hoverOverFirstProperty() {
        searchResultsPage.hoverOverProperty();
    }

    @Step
    public void selectActivePropertyOnMap() {
        searchResultsPage.clickActivePropertyOnMap();
    }

    @Step
    public void verifyDisplayedDetailsFromMap() {
        Property expected = (Property) Serenity.getCurrentSession().get("property");
        Property actual = searchResultsPage.getDisplayedPropertyDetailsMap(expected.getName());
        Assert.assertEquals(expected.toString(), actual.toString());
    }

}
