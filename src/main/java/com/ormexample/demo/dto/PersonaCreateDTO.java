package com.ormexample.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PersonaCreateDTO {

    @NotBlank(message = "Nombre no puede estar vacío")
    @Size(max = 100, message = "Nombre no puede exceder 100 caracteres")
    private String nombre;

    @NotNull(message = "Edad es obligatoria")
    @Min(value = 0, message = "Edad no puede ser negativa")
    private Integer edad;

    // Constructor vacío
    public PersonaCreateDTO() {}

    // Constructor con parámetros
    public PersonaCreateDTO(String nombre, Integer edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
}
