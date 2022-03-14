package com.christielen.informacoescadastrais.entity;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "healthPlan")
@Getter
public class HealthPlan {

	@Id
	private ObjectId id;

	private String code;

	private String name;

	@DBRef
	private PlanType planType;

	@DBRef
	private List<AgeGroupValues> ageGroupValues;
}
