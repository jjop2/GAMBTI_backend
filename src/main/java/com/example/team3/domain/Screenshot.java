package com.example.team3.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Screenshot {
    private String path_thumbnail;
    private String path_full;
}
