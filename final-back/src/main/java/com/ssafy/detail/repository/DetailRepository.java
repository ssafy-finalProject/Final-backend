package com.ssafy.detail.repository;

import com.ssafy.detail.dto.DetailDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface DetailRepository {

    void registerDetail(DetailDto detailDto) throws SQLException;
    void modifyDetail(DetailDto detailDto) throws SQLException;
    DetailDto findDetail(String place_name) throws SQLException;
    List<DetailDto> listDetail(int article_no) throws SQLException;
    void deleteDetail(String place_name) throws SQLException;

}
