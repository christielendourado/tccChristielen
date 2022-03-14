package com.christielen.informacoescadastrais.entity;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Getter
public class User {

	@Id
	private ObjectId id;

	private String username;
	private String password;

	@DBRef
	private UserType userType;
}
