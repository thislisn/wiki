package com.wiki.example.stepdefs;

import com.wiki.example.framework.BaseContext;
import com.wiki.example.framework.ContextKey;
import com.wiki.example.framework.SearchKey;
import com.wiki.example.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.wiki.example.framework.DateTimeHelper.getFullMonthName;
import static com.wiki.example.framework.DateTimeHelper.getToday;

public class WikiSearchSteps {
    private static final Logger logger = LoggerFactory.getLogger(WikiSearchSteps.class);
    private final HomePage homePage = new HomePage();


    @Given("^I open home wiki page$")
    public void openHomePage() {
        homePage.open();
    }

    @When("^I search for today date$")
    public void searchForDate() {
        LocalDateTime today = getToday();
        int day = today.getDayOfMonth();
        String monthName = getFullMonthName(today.getMonthOfYear());
        SearchKey searchKey = new SearchKey(monthName, day);
        String searchkey = String.format("%s %s", searchKey.getFullMonthName(), searchKey.getDay());
        homePage.searchFor(searchkey);
        BaseContext.setValue(ContextKey.SEARCH_KEY, searchKey);
    }
}
