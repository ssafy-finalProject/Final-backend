package com.ssafy.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BoardListDto {
    private List<BoardDto> articles;
    private int currentPage;
    private int totalPageCount;
}
