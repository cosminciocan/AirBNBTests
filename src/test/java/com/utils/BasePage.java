package com.utils;

import net.serenitybdd.core.pages.PageObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class BasePage extends PageObject {


    public String getDateNextWeeks(String format, long noOfWeeksInFuture) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now().plusWeeks(noOfWeeksInFuture);
        return dtf.format(now);
    }

    public void switchToNewWindow() {
        Set<String> allHandles = getDriver().getWindowHandles();
        String currHandle = getDriver().getWindowHandle();

        for (String handle : allHandles) {
            if (!handle.contentEquals(currHandle)) {
                getDriver().switchTo().window(handle);
                break;
            }
        }

    }
}
