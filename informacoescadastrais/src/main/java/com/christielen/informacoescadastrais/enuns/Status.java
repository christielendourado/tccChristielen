package com.christielen.informacoescadastrais.enuns;

import lombok.Getter;

@Getter
public enum Status {

    ATIVO("Ativo"),
    SUSPENSO("Suspenso"),
    INATIVO("Inativo");

    public String description;

    Status(String description){
        this.description = description;
    }
}
