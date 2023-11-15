package com.ssafy.file.repository;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.file.FileDto;

@Repository
public class fileRepositoryImpl implements fileRepository{
	SqlSession session;
	ServletContext servletContext;
	
	@Autowired
	public fileRepositoryImpl(SqlSession session) {
		super();
		this.session = session;
	}
	
	String ns = "com.ssafy.file.repository.fileRepository.";
	@Override
	public void fileUpload(MultipartFile[] files) {
		String realPath = servletContext.getRealPath("/upload");
		System.out.println(realPath);
		String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String saveFolder = realPath+File.separator+today;
		File folder = new File(saveFolder);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		for (MultipartFile mfile : files) {
			FileDto fileDto = new FileDto();
			fileDto.setOriginalFileName(mfile.getOriginalFilename());
			fileDto.setPath(folder.toString());
			session.insert(ns+"fileUpload",fileDto);
		}
	}


	

	
}
