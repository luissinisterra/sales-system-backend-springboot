package com.api.sales_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @NotBlank(message = "El documento es obligatorio.")
    @Pattern(regexp = "^[0-9]{11,}$", message = "El documento debe contener solo números y tener más de 10 dígitos.")
    @Column(name = "id", unique = true, nullable = false, length = 20)
    private String id;

    @NotBlank(message = "El nombre es obligatorio.")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio.")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "El número de celular es obligatorio.")
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @NotBlank(message = "El correo electrónico es obligatorio.")
    @Email(message = "Formato de correo electrónico inválido.")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Min(value = 0, message = "La edad no puede ser negativa.")
    @Column(name = "age", nullable = false)
    private int age;

}
