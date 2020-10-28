package com.ipl;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;

import CSVBuilder.CSVBuilderException;
import CSVBuilder.CSVBuilderFactory;
import CSVBuilder.ICSVBuilder;

public class StatisticsAnalyser {
	List<BattingCSV> battingStatsList = null;
	List<BowlingCSV> bowlingStatsList = null;
	public int loadBattingStatsData(String csvFile) throws StatisticsAnalyserException, IOException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFile));
			ICSVBuilder<BattingCSV> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			battingStatsList = csvBuilder.getCSVFileList(reader, BattingCSV.class);
			return battingStatsList.size();
		} 
		catch(CSVBuilderException exception) {
			throw new StatisticsAnalyserException(exception.getMessage(), StatisticsAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
		catch (IOException exception) {
			throw new StatisticsAnalyserException(exception.getMessage(), StatisticsAnalyserException.ExceptionType.INCORRECT_FILE);
		}
	}
	public int loadBowlingStatsData(String csvFile) throws StatisticsAnalyserException, IOException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFile));
			ICSVBuilder<BowlingCSV> csvBuilder = CSVBuilderFactory.createCSVBuilder();
		    bowlingStatsList = csvBuilder.getCSVFileList(reader, BowlingCSV.class);
			return bowlingStatsList.size();
		} 
		catch(CSVBuilderException exception) {
			throw new StatisticsAnalyserException(exception.getMessage(), StatisticsAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
		catch (IOException exception) {
			throw new StatisticsAnalyserException(exception.getMessage(), StatisticsAnalyserException.ExceptionType.INCORRECT_FILE);
		}
	}
}
