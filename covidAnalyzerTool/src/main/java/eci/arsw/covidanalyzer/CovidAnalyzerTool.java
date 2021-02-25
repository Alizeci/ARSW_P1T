package eci.arsw.covidanalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A Camel Application
 */
public class CovidAnalyzerTool {

    private ResultAnalyzer resultAnalyzer;
    private TestReader testReader;
    private int amountOfFilesTotal;
    private AtomicInteger amountOfFilesProcessed;
    private ArrayList<CovidAnalyzerThread> threads;
    private static final int n = 5;

    public CovidAnalyzerTool() {
        resultAnalyzer = new ResultAnalyzer();
        testReader = new TestReader();
        amountOfFilesProcessed = new AtomicInteger();
        threads = new ArrayList<>();
    }

    public void processResultData() {
        amountOfFilesProcessed.set(0);
        List<File> resultFiles = getResultFileList();
        amountOfFilesTotal = resultFiles.size();
        
        for (int i = 1; i < n + 1; i++) {
        	int inicioSegmento = (resultFiles.size()/ n ) * (i - 1);
        	int finSegmento;
        	
            if (n == i) {
            	finSegmento = amountOfFilesTotal;
            }else {
            	finSegmento = resultFiles.size() / n * i;
            }

            CovidAnalyzerThread processingThread = new CovidAnalyzerThread(resultFiles.subList(inicioSegmento, finSegmento), resultAnalyzer, testReader, amountOfFilesProcessed);
            threads.add(processingThread);
            processingThread.start();     
        }
    }

    private List<File> getResultFileList() {
        List<File> csvFiles = new ArrayList<>();
        try (Stream<Path> csvFilePaths = Files.walk(Paths.get("src/main/resources/")).filter(path -> path.getFileName().toString().endsWith(".csv"))) {
            csvFiles = csvFilePaths.map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvFiles;
    }


    public Set<Result> getPositivePeople() {
        return resultAnalyzer.listOfPositivePeople();
    }

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        CovidAnalyzerTool covidAnalyzerTool = new CovidAnalyzerTool();
        covidAnalyzerTool.processResultData();
        Thread processingThread = new Thread(() -> covidAnalyzerTool.processResultData());
        
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (line.contains("exit"))
                break;
            if(line.contains("")) {
	            for (CovidAnalyzerThread h : covidAnalyzerTool.threads){
		             if (h.isPaused()) {
		                  System.out.println("... running Threads.");
		                  h.setPause(false);
		                  h.reactivate();
		             }
		             else {
		                  h.setPause(true);
		                  String message = "Processed %d out of %d files.\nFound %d positive people:\n%s";
		                  Set<Result> positivePeople = covidAnalyzerTool.getPositivePeople();
		                  String affectedPeople = positivePeople.stream().map(Result::toString).reduce("", (s1, s2) -> s1 + "\n" + s2);
		                  message = String.format(message, covidAnalyzerTool.amountOfFilesProcessed.get(), covidAnalyzerTool.amountOfFilesTotal, positivePeople.size(), affectedPeople);
		                  System.out.println(message);
		             }
                }
            }
        }
    }

}

