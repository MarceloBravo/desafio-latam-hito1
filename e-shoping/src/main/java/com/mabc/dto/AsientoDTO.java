package com.mabc.dto;

import com.mabc.enums.enumType;

public class AsientoDTO {
    private int id;
    private enumType type = enumType.seat;
    private boolean disponible = true;

    // Constructors
    public AsientoDTO() {
    }

    public AsientoDTO(int id, enumType type, boolean disponible) {
        this.id = id;
        this.type = type;
        this.disponible = disponible;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public enumType getType() {
        return type;
    }

    public void setType(enumType type) {
        this.type = type;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}