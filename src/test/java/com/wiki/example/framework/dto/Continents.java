package com.wiki.example.framework.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Continents {

    @JsonProperty("continents")
    private List<String> continents = null;

    @JsonProperty("continents")
    public List<String> getContinents() {
        return continents;
    }

    @JsonProperty("continents")
    public void setContinents(List<String> continents) {
        this.continents = continents;
    }

}
