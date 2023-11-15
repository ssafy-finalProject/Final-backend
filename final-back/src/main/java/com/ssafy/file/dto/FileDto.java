package com.ssafy.file.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FileDto {

    private Integer file_id;
    private Integer article_no;
    private String name;
    private String type;

    private byte[] image_data;

    @Builder
    public FileDto(String name, String type, byte[] image_data) {
        this.name = name;
        this.type = type;
        this.image_data = image_data;
    }
}
