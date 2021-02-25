package eci.arsw.covidanalyzer.persistence;

public class CovidPersistenceException extends Exception {
	public CovidPersistenceException(String message) {
	    super(message);
	}
	
	public CovidPersistenceException(String message, Throwable cause) {
	    super(message, cause);
	}
}
