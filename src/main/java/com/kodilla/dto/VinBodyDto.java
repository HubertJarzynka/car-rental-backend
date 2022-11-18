package com.kodilla.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VinBodyDto {

    @JsonProperty("Make")
    private String manufacturer;

    @JsonProperty("Model")
    private String model;

    @JsonProperty("ModelYear")
    private String productYear;

    @JsonProperty("VehicleType")
    private String vehicleType;
}