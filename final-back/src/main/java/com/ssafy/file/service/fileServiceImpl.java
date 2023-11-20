package com.ssafy.file.service;

import com.ssafy.file.repository.FileRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.file.FileDto;

@Service
public class fileServiceImpl implements FileService {
	SqlSession session;
	@Autowired
	public fileServiceImpl(SqlSession session) {
		super();
		this.session = session;
	}

	@Override
	public void fileUpload(FileDto filedto) {
		// TODO Auto-generated method stub
		session.getMapper(FileRepository.class).fileUpload(filedto);
	}

}
