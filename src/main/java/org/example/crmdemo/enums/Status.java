package org.example.crmdemo.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
    @JsonProperty("In work")
    IN_WORK,
    New,
    Agreed,
    Disagreed,
    Dubbing
}
