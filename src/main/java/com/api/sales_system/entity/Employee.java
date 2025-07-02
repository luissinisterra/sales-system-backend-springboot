package com.api.sales_system.entity;

import com.api.sales_system.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio.")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio.")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull(message = "El rol es obligatorio.")
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @NotBlank(message = "El nombre de usuario es obligatorio.")
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @NotBlank(message = "La contraseña es obligatoria.")
    @Column(name = "password", nullable = false)
    private String password;

}
