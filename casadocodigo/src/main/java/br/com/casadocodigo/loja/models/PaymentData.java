package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

public class PaymentData {
	//Define o atributo com o nome value, justamente para que o SpringMVC
	//possa pegar o objeto e gerar o JSON com as chaves corretas.
	private BigDecimal value;

	public PaymentData() {
		super();
	}

	public PaymentData(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}
}//class PaymentData
