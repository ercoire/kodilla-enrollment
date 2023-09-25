package com.example.kodillaenrollment.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WsdcFindResultDto {

    @JsonProperty("short_dominate_role")
    private String dominantRole;

    @JsonProperty("dominate_allowed")
    private String allowedLevel;
}
