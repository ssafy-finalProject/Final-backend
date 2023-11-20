package com.ssafy.detail.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class markers {
    public Integer detail_id;
    public Integer article_no;
    public String place_name;
    public double latitude;
    public double longitude;
    public String category;
}
