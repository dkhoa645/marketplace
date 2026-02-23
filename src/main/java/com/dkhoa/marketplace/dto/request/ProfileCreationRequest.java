package com.dkhoa.marketplace.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileCreationRequest {
    @NotBlank
    String fullName;
    @Email(message = "EMAIL_INVALID")
    @NotBlank
    String email;
    @Pattern(regexp = "^(0[0-9]{9})$", message = "PHONE_INVALID")
    String phoneNumber;
    @NotBlank
    String address;
    LocalDate dob;
    Long provinceId;
}
