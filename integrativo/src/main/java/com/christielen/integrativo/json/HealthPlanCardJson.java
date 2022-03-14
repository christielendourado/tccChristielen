package com.christielen.integrativo.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class HealthPlanCardJson implements Serializable {

	@JsonProperty("membershipHealthPlanCode")
	private String membershipHealthPlanCode;

	@JsonProperty("objectId")
	private String objectId;
}