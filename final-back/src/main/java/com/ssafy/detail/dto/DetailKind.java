package com.ssafy.detail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailKind {

    private DetailDto markers;
    private List<DetailDto> stopover;
    private DetailDto destination;
}
