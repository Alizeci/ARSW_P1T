package eci.arsw.covidanalyzer;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CovidAnalyzerThread extends Thread{
	
	private ResultAnalyzer resultAnalyzer;
    private TestReader testReader;
    private int amountOfFilesTotal;
    private AtomicInteger amountOfFilesProcessed;
	private List<File> resultFiles;
    
    public CovidAnalyzerThread(List<File> resultFiles, ResultAnalyzer resultAnalyzer, TestReader testReader, AtomicInteger amountOfFilesProcessed) {
    	this.resultFiles = resultFiles;
    	this.resultAnalyzer = resultAnalyzer;
    	this.testReader = testReader;
    	this.amountOfFilesProcessed = amountOfFilesProcessed;
    }
    
    @Override
    public void run() {
    	amountOfFilesProcessed.set(0);
    	
		 for (File resultFile : resultFiles) {
	         List<Result> results = testReader.readResultsFromFile(resultFile);
	         for (Result result : results) {
	             resultAnalyzer.addResult(result);
	         }
	         amountOfFilesProcessed.incrementAndGet();
	     }
    }
    

}
