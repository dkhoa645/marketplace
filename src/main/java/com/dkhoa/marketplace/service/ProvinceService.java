package com.dkhoa.marketplace.service;

import com.dkhoa.marketplace.dto.request.ProvinceCreationRequest;
import com.dkhoa.marketplace.dto.request.ProvinceUpdateRequest;
import com.dkhoa.marketplace.dto.response.ProvinceResponse;
import com.dkhoa.marketplace.entity.Province;
import com.dkhoa.marketplace.exception.AppException;
import com.dkhoa.marketplace.exception.ErrorCode;
import com.dkhoa.marketplace.mapper.ProvinceMapper;
import com.dkhoa.marketplace.repository.ProvinceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProvinceService {
    ProvinceRepository provinceRepository;
    ProvinceMapper provinceMapper;

    public void deleteProvince(Long id) {
        Province province = provinceRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.PROVINCE_NOT_FOUND));
        provinceRepository.delete(province);
    }

    public ProvinceResponse createProvince(ProvinceCreationRequest request) {
        Province province = provinceMapper.toEntity(request);
        if(provinceRepository.existsByName(province.getName()))
            throw new AppException(ErrorCode.PROVINCE_EXISTED);
        return  provinceMapper.toResponse(provinceRepository.save(province));
    }

    public List<ProvinceResponse> getAllProvince() {
        return provinceRepository.findAll().stream()
                .map(provinceMapper::toResponse)
                .toList();
    }

    public ProvinceResponse updateProvince(Long id, ProvinceUpdateRequest request) {
        Province province = provinceRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.PROVINCE_NOT_FOUND));
        provinceMapper.updateProvince(province,request);
        return provinceMapper.toResponse(provinceRepository.save(province));
    }
}
