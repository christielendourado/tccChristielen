package com.christielen.informacoescadastrais.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AppointmentSchedulerPlanJson implements Serializable {

	@JsonProperty("exameCode")
	private String exameCode;

	@JsonProperty("associateCpf")
	private String associateCpf;

	@JsonProperty("healthPlanCard")
	private String healthPlanCard;
}