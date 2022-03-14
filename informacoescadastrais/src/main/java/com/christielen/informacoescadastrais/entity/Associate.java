package com.christielen.informacoescadastrais.entity;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "associate")
@Getter
public class Associate {

    @Id
    private ObjectId id;

    private String cpf;
    private String birthDate;

    @DBRef
    private User user;
}