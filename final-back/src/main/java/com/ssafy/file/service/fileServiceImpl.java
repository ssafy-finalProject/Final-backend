package com.ssafy.file.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.file.FileDto;
import com.ssafy.file.repository.fileRepository;

@Service
public class fileServiceImpl implements fileService {
	SqlSession session;
	
	public fileServiceImpl(SqlSession session) {
		super();
		this.session = session;
	}

	@Override
	public void fileUpload(MultipartFile[] files) {
		// TODO Auto-generated method stub
		session.getMapper(fileRepository.class).fileUpload(files);
	}

}
