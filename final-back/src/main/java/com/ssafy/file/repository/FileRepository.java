package com.ssafy.file.repository;

import com.ssafy.file.dto.FileDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileRepository {

    void saveImage(FileDto fileDto);

    FileDto findByName(@Param("fileName") String fileName);
}
