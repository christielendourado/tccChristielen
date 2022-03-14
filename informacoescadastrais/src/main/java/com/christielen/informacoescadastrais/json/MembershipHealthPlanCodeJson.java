package com.christielen.informacoescadastrais.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MembershipHealthPlanCodeJson implements Serializable {

	@JsonProperty("objectId")
	private String objectId;

	@JsonProperty("code")
	private String code;
}