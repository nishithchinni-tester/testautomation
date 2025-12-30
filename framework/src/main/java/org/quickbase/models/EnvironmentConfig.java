package org.quickbase.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnvironmentConfig {
    @JsonProperty("env")
    private String env;
    @JsonProperty("URL")
    private String URL;
}