package com.christielen.informacoescadastrais.entity;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "plantype")
@Getter
public class PlanType {

	@Id
	private ObjectId id;

	private String description;
	private Boolean dental;
	private Boolean coparticipation;
}
