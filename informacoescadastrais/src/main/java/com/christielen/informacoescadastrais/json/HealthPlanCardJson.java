package com.christielen.informacoescadastrais.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class HealthPlanCardJson implements Serializable {

	@JsonProperty("membershipHealthPlanCode")
	private String membershipHealthPlanCode;

	@JsonProperty("objectId")
	private String objectId;
}