package com.christielen.informacoescadastrais.entity;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "ageGroupValues")
@Getter
public class AgeGroupValues {

	@Id
	private ObjectId id;

	@DBRef
	private AgeGroup ageGroup;

	private BigDecimal value;
}
