package com.pages;

import com.utils.BasePage;
import com.utils.Constants;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DefaultUrl(Constants.BASE_URL)
public class HomePage extends BasePage {


    @FindBy(id = "bigsearch-query-detached-query-input")
    private WebElementFacade locationSearchField;

    @FindBy(css = "#bigsearch-query-detached-query-listbox li")
    private List<WebElementFacade> locationDropdownOptions;

    @FindBy(id = "tabs")
    private WebElementFacade activeDropdown;

    @FindBy(css = "div[data-testid='structured-search-input-field-guests-button']")
    private WebElementFacade addGuestsField;

    @FindBy(css = "button[data-testid='stepper-adults-increase-button']")
    private WebElementFacade increaseAdultsButton;

    @FindBy(css = "button[data-testid='stepper-children-increase-button']")
    private WebElementFacade increaseChildrenButton;

    @FindBy(css = "button[data-testid='structured-search-input-search-button']")
    private WebElementFacade searchButton;

    public void setLocationSearchField(String text) {
        waitFor(locationSearchField);
        typeInto(locationSearchField, text);
    }

    public boolean selectLocationDropdownOption(String text) {
        waitForAngularRequestsToFinish();
        waitFor(locationDropdownOptions.get(0));
        for (WebElementFacade element : locationDropdownOptions) {
            if (element.containsOnlyText(text)) {
                element.click();
                return true;
            }
        }
        return false;
    }

    public void selectDateByWeeks(long noOfWeeks) {
        String nextWeekDay = getDateNextWeeks("yyyy-MM-dd", noOfWeeks);
        waitFor(activeDropdown);
        activeDropdown.findBy(By.cssSelector("[data-testid='datepicker-day-" + nextWeekDay + "']")).click();
    }

    public void clickAddGuests() {
        clickOn(addGuestsField);
    }

    public void clickIncreaseAdultsButton() {
        clickOn(increaseAdultsButton);
    }

    public void clickIncreaseChildrenButton() {
        clickOn(increaseChildrenButton);
    }

    public void setAdultsNumber(int number) {
        for (int i = 0; i < number; i++) {
            waitFor(increaseAdultsButton);
            clickIncreaseAdultsButton();
        }
    }

    public void setChildrenNumber(int number) {
        for (int i = 0; i < number; i++) {
            waitFor(increaseChildrenButton);
            clickIncreaseChildrenButton();
        }
    }

    public void clickSearch() {
        clickOn(searchButton);
        waitABit(1500);
    }
}
