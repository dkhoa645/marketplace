package com.dkhoa.marketplace.dto.request;

import com.dkhoa.marketplace.entity.Enum.RoleEnum;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 4, message = "USERNAME_INVALID")
    String userName;
    @Size(min = 6, message = "PASSWORD_INVALID")
    String password;
}
