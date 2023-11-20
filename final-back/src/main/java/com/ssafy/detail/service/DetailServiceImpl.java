package com.ssafy.detail.service;

import com.ssafy.detail.dto.DetailDto;
import com.ssafy.detail.repository.DetailRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DetailServiceImpl implements DetailService{
    private DetailRepository detailRepository;

    public DetailServiceImpl(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    @Override
    public void registerDetail(DetailDto detailDto) throws SQLException {
        // List 형태로 받은 값을 반복문을 통해서 등록함.
        detailRepository.registerDetail(detailDto);
    }

    @Override
    public void modifyDetail(DetailDto detailDto) throws SQLException {
        detailRepository.modifyDetail(detailDto);
    }

    @Override
    public DetailDto findDetail(String place_name) throws SQLException {
        DetailDto detail = detailRepository.findDetail(place_name);
        return detail;
    }

    @Override
    public List<DetailDto> listDetail(int article_no) throws SQLException {
        List<DetailDto> list = detailRepository.listDetail(article_no);
        return list;
    }

    @Override
    public void deleteDetail(String place_name) throws SQLException {
        detailRepository.deleteDetail(place_name);
    }
}
