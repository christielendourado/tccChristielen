package com.christielen.informacoescadastrais.entity;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "convened")
@Getter
public class Convened {

    @Id
    private ObjectId id;

    @DBRef
    private User user;
}
