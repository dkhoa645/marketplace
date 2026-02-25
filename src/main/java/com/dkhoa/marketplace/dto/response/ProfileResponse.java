package com.dkhoa.marketplace.dto.response;

import com.dkhoa.marketplace.entity.Province;
import com.dkhoa.marketplace.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileResponse {
    UUID id;
    String fullName;
    String email;
    String phoneNumber;
    String address;
    LocalDate dob;
    ProvinceResponse province;
    String avatar;
    Instant createdAt;
    Instant updatedAt;
}
