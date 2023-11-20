package com.ssafy.file.service;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.file.FileDto;

public interface FileService {
	void fileUpload(FileDto filedto);
}
