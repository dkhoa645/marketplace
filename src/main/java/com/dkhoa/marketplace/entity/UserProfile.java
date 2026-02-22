package com.dkhoa.marketplace.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Table(name = "user_profile")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String fullName;
    String email;
    String phoneNumber;
    String avatar;
    String address;
    @ManyToOne(cascade = CascadeType.ALL)
    Province province;
    @OneToOne
    User user;

}
