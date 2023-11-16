package com.ssafy.file;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//@Getter
//@Setter
//@NoArgsConstructor
public class FileDto {
	private int file_id;
	private int article_no;
	private String path;
	private String originalFileName;
	private String savedFileName;
	
	public FileDto(String originalFileName, String savedFileName) {
		this.originalFileName = originalFileName;
		this.savedFileName = savedFileName;
	}

	public int getFile_id() {
		return file_id;
	}

	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}

	public int getArticle_no() {
		return article_no;
	}

	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getSavedFileName() {
		return savedFileName;
	}

	public void setSavedFileName(String savedFileName) {
		this.savedFileName = savedFileName;
	}

	public FileDto(int file_id, int article_no, String path, String originalFileName, String savedFileName) {
		super();
		this.file_id = file_id;
		this.article_no = article_no;
		this.path = path;
		this.originalFileName = originalFileName;
		this.savedFileName = savedFileName;
	}

	public FileDto() {
		super();
	}

	@Override
	public String toString() {
		return "FileDto [file_id=" + file_id + ", article_no=" + article_no + ", path=" + path + ", originalFileName="
				+ originalFileName + ", savedFileName=" + savedFileName + "]";
	}
	
	
}
