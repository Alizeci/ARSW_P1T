package eci.arsw.covidanalyzer;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CovidAnalyzerThread extends Thread{
	
	private ResultAnalyzer resultAnalyzer;
    private TestReader testReader;
    private AtomicInteger amountOfFilesProcessed;
	private List<File> resultFiles;
	private boolean pause;
    
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
			 while (isPaused()){
				 try {
					 wait();                                
				 } catch (InterruptedException ex) {
					 ex.printStackTrace();
                 }
         }
		 List<Result> results = testReader.readResultsFromFile(resultFile);
		 for (Result result : results) {
		     resultAnalyzer.addResult(result);
		 }
		 amountOfFilesProcessed.incrementAndGet();
		 }
    }

	public boolean isPaused() {
		// TODO Auto-generated method stub
		return pause;
	}

	public void setPause(boolean b) {
		this.pause = b;
		
	}
	
	public synchronized void reactivate() {
		// TODO Auto-generated method stub
		this.setPause(false);
		notify();
	}
}
