package com.ssafy.detail.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DetailDto {

    public Integer detail_id;
    public Integer article_no;
    public String place_name;
    public double latitude;
    public double longitude;
    public String category;

}




