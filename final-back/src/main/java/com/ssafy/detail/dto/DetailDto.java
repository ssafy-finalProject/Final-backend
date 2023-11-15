package com.ssafy.detail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class DetailDto {

    public Integer detail_id;
    public Integer article_no;
    public String place_name;
    public double latitude;
    public double longitude;
}
