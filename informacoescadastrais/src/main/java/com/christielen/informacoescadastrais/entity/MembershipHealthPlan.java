package com.christielen.informacoescadastrais.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "membershipHealthPlan")
@Getter
@Setter
public class MembershipHealthPlan {

	@Id
	private ObjectId id;

	private String code;

	@DBRef
	private Associate associate;

	@DBRef
	private Status status;

	@DBRef
	private HealthPlan healthPlan;

	private BigDecimal value;

	private Boolean dental;

	private String healthPlanCard;
}