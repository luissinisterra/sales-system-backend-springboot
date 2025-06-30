package com.api.sales_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio.")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio.")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "El número de celular es obligatorio.")
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Email(message = "Formato de correo electrónico inválido.")
    @NotBlank(message = "El correo electrónico es obligatorio.")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Min(value = 0, message = "La edad no puede ser negativa.")
    @Column(name = "age", nullable = false)
    private int age;

}
