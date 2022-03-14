package com.christielen.integrativo.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class HealthPlanCardCodeJson implements Serializable {

	@JsonProperty("healthCard")
	private String healthCard;

	@JsonProperty("objectId")
	private String objectId;
}