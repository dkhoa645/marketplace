package com.dkhoa.marketplace.dto.response;

import com.dkhoa.marketplace.entity.Enum.RoleEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {
    RoleEnum name;
}
