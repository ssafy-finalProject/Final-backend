package com.ssafy.file;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class FileDto {
	private String path;
	private String originalFileName;
	private String savedFileName;
}
