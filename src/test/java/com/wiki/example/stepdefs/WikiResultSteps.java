package com.wiki.example.stepdefs;

import com.wiki.example.framework.BaseContext;
import com.wiki.example.framework.ContextKey;
import com.wiki.example.framework.SearchKey;
import com.wiki.example.pages.SearchResultPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static com.wiki.example.framework.DateTimeHelper.getFullMonthName;
import static com.wiki.example.framework.DateTimeHelper.getTomorrow;

public class WikiResultSteps {
    private static final Logger logger = LoggerFactory.getLogger(WikiResultSteps.class);
    private final SearchResultPage searchResultPage = new SearchResultPage();

    @When("^I wait for search result page loaded$")
    public void waitSearchResultsPageloaded() {
        SearchKey monthDay = (SearchKey) BaseContext.getValue(ContextKey.SEARCH_KEY);
        searchResultPage.waiForPageLoaded(monthDay);
    }

    @When("^I collect links from search result page$")
    public void collectLinksFromSearchResultsPage() {
        searchResultPage.getAllLinksMap();
    }

    @When("^I look for continents on page$")
    public void lookForContinentsLinksFromSearchResultsPage() {
        Map<String, List<String>> linksMap = (Map<String, List<String>>) BaseContext.getValue(ContextKey.LINKS_MAP);
        List<String> links = linksMap.get(searchResultPage.getSelectedDay());
        List<String> continents = searchResultPage.getContinents().getContinents();
        int foundContinents = (int) continents.stream()
                .filter(links::contains)
                .count();
        List<Integer> list = searchResultPage.getFoundContinentsResults();
        list.add(foundContinents);
        BaseContext.setValue(ContextKey.FOUND_CONTINENTS, list);
    }

    @When("^I look for countries on page$")
    public void lookForCountriesLinksFromSearchResultsPage() {
        Map<String, List<String>> linksMap = (Map<String, List<String>>) BaseContext.getValue(ContextKey.LINKS_MAP);
        List<String> links = linksMap.get(searchResultPage.getSelectedDay());
        List<String> countries = searchResultPage.getIsoCountries();
        int foundCountries = (int) countries.stream()
                .filter(links::contains)
                .count();
        List<Integer> list = searchResultPage.getFoundCountriesResults();
        list.add(foundCountries);
        BaseContext.setValue(ContextKey.FOUND_COUNTRIES, list);
    }

    @When("^I look for cities on page$")
    public void lookForCitiesLinksFromSearchResultsPage() {
        Map<String, List<String>> linksMap = (Map<String, List<String>>) BaseContext.getValue(ContextKey.LINKS_MAP);
        List<String> links = linksMap.get(searchResultPage.getSelectedDay());
        List<String> cities = searchResultPage.getCities().getCities();
        int foundCities = (int) cities.stream()
                .filter(links::contains)
                .count();
        List<Integer> list = searchResultPage.getFoundCitiesResults();
        list.add(foundCities);
        BaseContext.setValue(ContextKey.FOUND_CITIES, list);
    }

    @When("^I click on tomorrow date on Calendar$")
    public void clickTomorrowDateOnCalendarOnSearchResultsPage() {
        LocalDateTime tomorrow = getTomorrow();
        int day = tomorrow.getDayOfMonth();
        String monthName = getFullMonthName(tomorrow.getMonthOfYear());
        searchResultPage.clickDate(monthName, day);
    }

    @Then("^I compare found continents$")
    public void compareFoundContinents() {
        List<Integer> foundContinents = (List<Integer>) BaseContext.getValue(ContextKey.FOUND_CONTINENTS);
        System.out.println(String.format("Today was found on wiki %s continents", foundContinents.get(0)));
        System.out.println(String.format("Tomorrow was found on wiki %s continents", foundContinents.get(1)));
    }

    @Then("^I compare found countries$")
    public void compareFoundCountries() {
        List<Integer> foundCountries = (List<Integer>) BaseContext.getValue(ContextKey.FOUND_COUNTRIES);
        System.out.println(String.format("Today was found on wiki %s countries", foundCountries.get(0)));
        System.out.println(String.format("Tomorrow was found on wiki %s countries", foundCountries.get(1)));
    }

    @Then("^I compare found cities$")
    public void compareFoundCities() {
        List<Integer> foundCities = (List<Integer>) BaseContext.getValue(ContextKey.FOUND_COUNTRIES);
        System.out.println(String.format("Today was found on wiki %s cities", foundCities.get(0)));
        System.out.println(String.format("Tomorrow was found on wiki %s cities", foundCities.get(1)));
    }
}
