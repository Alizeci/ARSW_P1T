package eci.arsw.covidanalyzer.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Service;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;

@Service
public class ICovidAggregateServiceStub implements ICovidAggregateService{
	ArrayList<ResultType> testsResults = new ArrayList<>();

	@Override
	public void aggregateResult(Result result, ResultType type) {
		result.setResultado(type);
		testsResults.add(result.getResultado());
	}

	@Override
	public ArrayList<ResultType> getResult(ResultType type) {
		return testsResults;
	}

	@Override
	public void upsertPersonWithMultipleTests(UUID id, ResultType type) {
		// TODO Auto-generated method stub
		
	}

}
