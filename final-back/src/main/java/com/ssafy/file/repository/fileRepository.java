package com.ssafy.file.repository;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.file.FileDto;

public interface FileRepository {
	void fileUpload(FileDto filedto);
}
