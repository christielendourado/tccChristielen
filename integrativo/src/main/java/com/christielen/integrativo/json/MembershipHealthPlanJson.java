package com.christielen.integrativo.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class MembershipHealthPlanJson implements Serializable {

	@JsonProperty("healthPlanCode")
	private String healthPlanCode;

	@JsonProperty("dental")
	private Boolean dental;

	@JsonProperty("associado")
	private String associado;

	@JsonProperty("value")
	private BigDecimal value;

	@JsonProperty("objectId")
	private String objectId;
}