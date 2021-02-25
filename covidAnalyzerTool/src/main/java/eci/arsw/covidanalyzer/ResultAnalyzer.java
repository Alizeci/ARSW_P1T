package eci.arsw.covidanalyzer;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ResultAnalyzer {

    public static final double MIN_TEST_SPECIFY = 0.90;
    private Set<Result> positivePeople;

    public ResultAnalyzer() {
        positivePeople = new HashSet<>();
    }

    public synchronized void addResult(Result result) {
        if (result.isResult()) {
            if (result.getTestSpecifity() > MIN_TEST_SPECIFY) {
                this.positivePeople.add(result);
                System.out.println("positiva");
                //TestReporter.report(result, TestReporter.TRUE_POSITIVE);
            } else {
            	System.out.println("falso positivo");
                //TestReporter.report(result, TestReporter.FALSE_POSITIVE);
            }
        } else {
            if (result.getTestSpecifity() > MIN_TEST_SPECIFY) {
            	System.out.println("negativo");
                //TestReporter.report(result, TestReporter.TRUE_NEGATIVE);
            } else {
            	System.out.println("falso negativo");
                //TestReporter.report(result, TestReporter.FALSE_NEGATIVE);
            }
        }
    }

    public Set<Result> listOfPositivePeople() {
        return positivePeople;
    }

}
