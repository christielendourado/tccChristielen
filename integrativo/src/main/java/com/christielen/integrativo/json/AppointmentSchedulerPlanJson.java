package com.christielen.integrativo.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AppointmentSchedulerPlanJson implements Serializable {

	@JsonProperty("exameCode")
	private String exameCode;

	@JsonProperty("associateCpf")
	private String associateCpf;

	@JsonProperty("healthPlanCard")
	private String healthPlanCard;
}