package com.hesias.weatherapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
    private Double latitude;
    private Double longitude;
    private Double kmRadius;
}
