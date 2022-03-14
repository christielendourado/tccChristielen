package com.christielen.integrativo.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MembershipHealthPlanCodeJson implements Serializable {

	@JsonProperty("dental")
	private String objectId;

	@JsonProperty("code")
	private String code;
}