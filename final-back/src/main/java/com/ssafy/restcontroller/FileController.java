//package com.ssafy.restcontroller;
//
//
//import com.ssafy.file.service.FileService;
//import io.swagger.annotations.Api;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@CrossOrigin("*")
//@RestController
//@Slf4j
//@RequestMapping("/file")
//@Api("파일 업로드 테스트")
//public class FileController {
//
//    private FileService fileService;
//
//    public FileController(FileService fileService) {
//        this.fileService = fileService;
//    }
//
//    // 업로드
//    @PostMapping
//    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
//        String uploadImage = fileService.uploadImage(file);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(uploadImage);
//    }
//
//    // 다운로드
//    @GetMapping("/{fileName}")
//    public ResponseEntity<?> downloadImage(@PathVariable("fileName") String fileName) {
//        byte[] downloadImage = storageService.downloadImage(fileName);
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf("image/png"))
//                .body(downloadImage);
//    }
//}
