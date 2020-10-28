package com.ipl;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
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
		Comparator<BattingCSV> statComparator = Comparator.comparing(stat -> (stat.strikeRate) );
		List<BattingCSV> strikeRate = getBestBattingAverage();
		return this.sort(strikeRate.stream().limit(20).collect(Collectors.toList()), statComparator.reversed());
	}
	public <E>List getMaximumRunsWithBestAverage() throws StatisticsAnalyserException {
		Comparator<BattingCSV> statComparator = Comparator.comparing(stat -> (stat.runs) );
		Comparator<BattingCSV> statAvgComparator = Comparator.comparing(stat -> (stat.avg) );
		List<BattingCSV> maximumRuns = this.sort(battingStatsList, statComparator.reversed());
		return this.sort(maximumRuns.stream().limit(20).collect(Collectors.toList()), statAvgComparator.reversed());
	}
	public <E>List getBestBowlingAverage() throws StatisticsAnalyserException {
		Comparator<BowlingCSV> statComparator = Comparator.comparing(stat -> (stat.avg) );
		bowlingStatsList.removeIf(stat -> stat.avg == 0);
		return this.sort(bowlingStatsList, statComparator);
	}
	public <E>List getBestBowlingStrikeRate() throws StatisticsAnalyserException {
		Comparator<BowlingCSV> statComparator = Comparator.comparing(stat -> (stat.strikeRate) );
		bowlingStatsList.removeIf(stat -> stat.strikeRate == 0);
		return this.sort(bowlingStatsList, statComparator);
	}
	public <E>List getBestBowlingEconomy() throws StatisticsAnalyserException {
		Comparator<BowlingCSV> statComparator = Comparator.comparing(stat -> (stat.economy) );
		return this.sort(bowlingStatsList, statComparator);
	}
	public <E>List getBestBowlingStrikeRateWithHauls() throws StatisticsAnalyserException {
		Comparator<BowlingCSV> statComparator = Comparator.comparing(stat -> (stat.strikeRate) );
		bowlingStatsList.removeIf(stat -> (stat.strikeRate == 0 || (stat.fourWkts == 0 && stat.fiveWkts == 0)));
		return this.sort(bowlingStatsList, statComparator);
	}
	public <E>List getBestBowlingAverageAndStrikeRate() throws StatisticsAnalyserException {
		Comparator<BowlingCSV> statComparator = Comparator.comparing(stat -> (stat.strikeRate) );
		List<BowlingCSV> strikeRate = getBestBowlingAverage();
		return this.sort(strikeRate.stream().limit(20).collect(Collectors.toList()), statComparator);
	}
	public <E>List getMaximumWicketsWithBestAverage() throws StatisticsAnalyserException {
		Comparator<BowlingCSV> statComparator = Comparator.comparing(stat -> (stat.wickets) );
		Comparator<BowlingCSV> statAvgComparator = Comparator.comparing(stat -> (stat.avg) );
		List<BowlingCSV> maximumWickets = this.sort(bowlingStatsList, statComparator.reversed());
		return this.sort(maximumWickets.stream().limit(20).collect(Collectors.toList()), statAvgComparator);
	}
	public <E>List getBestBattingAndBowlingAverage() throws StatisticsAnalyserException {
		List<String> stat = new ArrayList<String>();
		List<BattingCSV> battingAverage = getBestBattingAverage();
		battingAverage = battingAverage.stream().limit(50).collect(Collectors.toList());
		List<BowlingCSV> bowlingAverage = getBestBowlingAverage();
		bowlingAverage = bowlingAverage.stream().limit(50).collect(Collectors.toList());
		for(BattingCSV b: battingAverage ) {
			for(int i = 0;i < bowlingAverage.size(); i++) {
				if(b.playerName.equals(bowlingAverage.get(i).playerName)) {
					stat.add(b.playerName);
				}
			}
		}
		return stat;
	}
	public <E>List getMaximumHundredsWithBestAverage() throws StatisticsAnalyserException {
		battingStatsList.removeIf(stat -> stat.noOfHundreds==0);
		Comparator<BattingCSV> statComparator = Comparator.comparing(stat -> (stat.noOfHundreds) );
		Comparator<BattingCSV> statAvgComparator = Comparator.comparing(stat -> (stat.avg) );
		List<BattingCSV> maximumHundreds = this.sort(battingStatsList, statComparator.reversed());
		return this.sort(maximumHundreds, statAvgComparator.reversed());
	}
	public <E>List getAllRounder() throws StatisticsAnalyserException {
		List<String> stats = new ArrayList<String>();
		Comparator<BowlingCSV> statBowlComparator = Comparator.comparing(stat -> (stat.wickets));
		Comparator<BattingCSV> statBatComparator = Comparator.comparing(stat -> (stat.runs));
		List<BattingCSV> battingAverage = this.sort(battingStatsList, statBatComparator);
		battingAverage = battingAverage.stream().limit(50).collect(Collectors.toList());
		List<BowlingCSV> bowlingAverage = this.sort(bowlingStatsList, statBowlComparator);
		bowlingAverage = bowlingAverage.stream().limit(50).collect(Collectors.toList());
		for(BattingCSV b: battingAverage ) {
			for(int i = 0;i < bowlingAverage.size(); i++) {
				if(b.playerName.equals(bowlingAverage.get(i).playerName)) {
					stats.add(b.playerName);
				}
			}
		}
		return stats;
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
