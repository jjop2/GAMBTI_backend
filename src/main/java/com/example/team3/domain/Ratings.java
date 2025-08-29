package com.example.team3.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ratings {
	
	@JsonProperty("steam_germany")
	private SteamGermany steamGermany; 
}
