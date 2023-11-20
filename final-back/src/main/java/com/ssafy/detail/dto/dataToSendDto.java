package com.ssafy.detail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class dataToSendDto {

    private List<markers> markers;
    private List<stopover> stopover;
    private List<destination> destination;
}
