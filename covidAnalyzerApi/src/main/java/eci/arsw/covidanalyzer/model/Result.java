package eci.arsw.covidanalyzer.model;

import java.util.UUID;

public class Result {
	private ResultType resultado;
	private UUID id;
	
	public Result(UUID id, ResultType test) {
		this.id = id;
		this.resultado = test;
	}
	
	public ResultType getResultado() {
		return resultado;
	}

	public void setResultado(ResultType resultado) {
		this.resultado = resultado;
	}

	public UUID getId() {
		return id;
	}
}
