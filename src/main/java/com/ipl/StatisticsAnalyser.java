package com.ipl;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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
	public <E>List getBestBattingAverage() throws StatisticsAnalyserException {
		Comparator<BattingCSV> statComparator = Comparator.comparing(stat -> (stat.avg) );
		return this.sort(battingStatsList, statComparator.reversed());
	}
	public <E>List getBestStrikeRate() throws StatisticsAnalyserException {
		Comparator<BattingCSV> statComparator = Comparator.comparing(stat -> (stat.strikeRate) );
		return this.sort(battingStatsList, statComparator.reversed());
	}
	public <E>List getMostBoundaries() throws StatisticsAnalyserException {
		Comparator<BattingCSV> statComparator = Comparator.comparing(stat -> (stat.noOfFours + stat.noOfSixes) );
		return this.sort(battingStatsList, statComparator.reversed());
	}
	public <E>List getBestStrikeRateWithBoundaries() throws StatisticsAnalyserException {
		battingStatsList.removeIf(s->(s.noOfFours+s.noOfSixes)==0);
		Comparator<BattingCSV> statComparator = Comparator.comparing(stat -> (stat.bF / (stat.noOfFours + stat.noOfSixes)));
		return this.sort(battingStatsList,  statComparator);
	}
	public <E>List getBestAverageAndStrikeRate() throws StatisticsAnalyserException {
		Comparator<BattingCSV> statComparator = Comparator.comparing(stat -> (stat.avg) );
		List<BattingCSV> strikeRate = this.sort(battingStatsList, statComparator.reversed());
		return this.sort(strikeRate.stream().limit(20).collect(Collectors.toList()), statComparator.reversed());
	}
	private <E> List sort(List<E> statList, Comparator<E> statComparator) throws StatisticsAnalyserException {
		if(statList == null || statList.size() == 0) {
			throw new StatisticsAnalyserException("No Census Data", StatisticsAnalyserException.ExceptionType.NO_STATISTICS_DATA);
		}
		for (int i = 0; i < statList.size(); i++) {
			for (int j = 0; j < statList.size() - i - 1; j++) {
				E stat1 =  statList.get(j);
				E stat2 =  statList.get(j + 1);
				if (statComparator.compare(stat1, stat2) > 0) {
					statList.set(j, stat2);
					statList.set(j + 1, stat1);
				}
			}
		}
		return statList;
	}
}
