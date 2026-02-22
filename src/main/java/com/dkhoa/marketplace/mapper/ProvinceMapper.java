package com.dkhoa.marketplace.mapper;

import com.dkhoa.marketplace.dto.request.ProvinceCreationRequest;
import com.dkhoa.marketplace.dto.request.ProvinceUpdateRequest;
import com.dkhoa.marketplace.dto.response.ProvinceResponse;
import com.dkhoa.marketplace.entity.Province;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProvinceMapper {
    Province toEntity(ProvinceCreationRequest request);
    ProvinceCreationRequest toModel(Province province);
    ProvinceResponse toResponse(Province province);
    void updateProvince(@MappingTarget Province province, ProvinceUpdateRequest request);
}
