package com.dkhoa.marketplace.mapper;

import com.dkhoa.marketplace.dto.response.RoleResponse;
import com.dkhoa.marketplace.entity.Role;
import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResponse toRoleResponse(Role role);
}
