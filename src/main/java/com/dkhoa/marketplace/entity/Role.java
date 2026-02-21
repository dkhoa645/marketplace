package com.dkhoa.marketplace.entity;

import com.dkhoa.marketplace.entity.Enum.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "roles")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Enumerated(EnumType.STRING)
    RoleEnum role;

    @ManyToMany(mappedBy = "roles")
    Set<User> user;
}
