package com.ssafy.file.service;

import org.springframework.web.multipart.MultipartFile;

public interface fileService {
	void fileUpload(MultipartFile[] files);
}
