package com.wiki.example.framework.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cities {

    @JsonProperty("cities")
    private List<String> cities = null;

    @JsonProperty("cities")
    public List<String> getCities() {
        return cities;
    }

    @JsonProperty("cities")
    public void setCities(List<String> cities) {
        this.cities = cities;
    }
}
