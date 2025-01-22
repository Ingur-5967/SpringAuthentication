package ru.solomka.authentication.repository;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.solomka.authentication.service.user.details.enums.UserRole;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Size(min = 5, max = 15)
    private String username;

    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}