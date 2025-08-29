package com.example.team3.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameData {
    private String name;
    private String header_image;
    private PcRequirements pc_requirements;
    private ReleaseDate release_date;
    private Ratings ratings;
    private SteamGermany steamGermany;
    private Webm webm;
    private List<Genre> genres;
    private List<Screenshot> screenshots;
    private List<Movies> movies;
}
