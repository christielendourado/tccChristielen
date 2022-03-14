package com.christielen.informacoescadastrais.entity;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ageGroup")
@Getter
public class AgeGroup {

	@Id
	private ObjectId id;

	private String description;

	private Integer initialAge;
	private Integer finalAge;
}
