package com.ssafy.file.service;

import com.ssafy.file.dto.FileDto;
import com.ssafy.file.repository.FileRepository;
import com.ssafy.util.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        log.info("upload file: {}", file); // file을 올린다

        fileRepository.saveImage(
                FileDto.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .image_data(ImageUtils.compressImage(file.getBytes()))
                        .build());

        log.info("file uploaded successfully: {}", file.getOriginalFilename());
        return "file uploaded successfully: " + file.getOriginalFilename();
    }

    // 이미지 파일로 압축하기
    public byte[] downloadFile(String fileName) {
        FileDto fileDto = fileRepository.findByName(fileName);

        if (fileDto == null) {
            throw new RuntimeException("File not found");
        }

        log.info("download fileDto: {}", fileDto);

        return ImageUtils.decompressImage(fileDto.getImage_data());
    }
}
