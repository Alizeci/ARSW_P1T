package eci.arsw.covidanalyzer.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Service;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import eci.arsw.covidanalyzer.persistence.CovidPersistenceException;

@Service
public class ICovidAggregateServiceStub implements ICovidAggregateService{
	ArrayList<Result> testsResults = new ArrayList<>();

	@Override
	public void aggregateResult(Result result, ResultType type) throws CovidPersistenceException{
		result.setResultado(type);
		for (Result res : testsResults) {
			if (res.getId().equals(result.getId())) {
				throw new CovidPersistenceException("Resultado registrado!");
			}
		}
		testsResults.add(result);
	}

	@Override
	public ArrayList<Result> getResult(ResultType type) throws CovidPersistenceException {
		ArrayList<Result> newResults = new ArrayList<>();
		for (Result res : testsResults) {
			if (res.getResultado().equals(type)) {
				newResults.add(res);
			}
		}
		
		if (newResults.isEmpty()) {
			throw new CovidPersistenceException("No hay registros con Resultado: "+type.toString());
		}
		return newResults;
	}

	@Override
	public void upsertPersonWithMultipleTests(UUID id, ResultType type) {
		// TODO Auto-generated method stub
		
	}

}
