package com.ssafy.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.file.FileDto;
import com.ssafy.file.service.fileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins= {"*"})
@RestController
@Slf4j
@RequestMapping("/file")
@Api("파일 관련 API테스트")
public class RestFileController {
	private fileService service;

	public RestFileController(fileService service) {
		super();
		this.service = service;
	}
	
	@ApiOperation(value = "파일 업로드, 파일 입력", notes = "파일을 입력한다.")
	@PostMapping
	public ResponseEntity<?> fileUpload(@RequestPart @ApiParam(value = "파일 입력", required = true) MultipartFile[] files)throws Exception{
		try {
			log.debug("file 업로드={}",files);
			System.out.println(files[0]);
//			service.fileUpload(files);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
