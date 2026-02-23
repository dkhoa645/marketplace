package com.dkhoa.marketplace.dto.request;

import com.dkhoa.marketplace.validator.DobConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileCreationRequest {
    @Size(min = 1)
    String fullName;
    @Email(message = "EMAIL_INVALID")
    @NotBlank
    String email;
    @Pattern(regexp = "^(0[0-9]{9})$", message = "PHONE_INVALID")
    String phoneNumber;
    @NotBlank
    String address;

    @DobConstraint(min = 18, message = "INVALID_DOB")
    LocalDate dob;

    Long provinceId;
}
