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
	private static final Comparator<BattingCSV> battingAverageComparator = Comparator.comparing(stat -> (stat.avg));
	private static final Comparator<BowlingCSV> bowlingAverageComparator = Comparator.comparing(stat -> (stat.avg));
	private static final Comparator<BattingCSV> battingStrikeRateComparator = Comparator.comparing(stat -> (stat.strikeRate));
	private static final Comparator<BowlingCSV> bowlingStrikeRateComparator = Comparator.comparing(stat -> (stat.strikeRate));
	private static final Comparator<BattingCSV> maximumRunsComparator = Comparator.comparing(stat -> (stat.runs));
	private static final Comparator<BowlingCSV> maximumWicketsComparator = Comparator.comparing(stat -> (stat.wickets) );
	/**
	 * Load Batting Statistics
	 * @param csvFile
	 * @return
	 * @throws StatisticsAnalyserException
	 * @throws IOException
	 */
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
	/**
	 * Load Bowling Statistics
	 * @param csvFile
	 * @return
	 * @throws StatisticsAnalyserException
	 * @throws IOException
	 */
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
	/**
	 * UseCase 1
	 * Function returns players with the best batting averages
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E>List getBestBattingAverage() throws StatisticsAnalyserException {
		return this.sort(battingStatsList, battingAverageComparator.reversed());
	}
	/**
	 * Usecase 2
	 * Function returns players with the best batting strike rates
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E>List getBestStrikeRate() throws StatisticsAnalyserException {
		return this.sort(battingStatsList, battingStrikeRateComparator.reversed());
	}
	/**
	 * Usecase 3
	 * Function returns players with most boundaries
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E>List getMostBoundaries() throws StatisticsAnalyserException {
		Comparator<BattingCSV> comparator = Comparator.comparing(stat -> (stat.noOfFours + stat.noOfSixes) );
		return this.sort(battingStatsList, comparator.reversed());
	}
	/**
	 * Usecase 4
	 * Function returns players with best batting strike rates with boundaries
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E>List getBestStrikeRateWithBoundaries() throws StatisticsAnalyserException {
		battingStatsList.removeIf(s -> (s.noOfFours+s.noOfSixes) == 0);
		Comparator<BattingCSV> statComparator = Comparator.comparing(stat -> (stat.bF / (stat.noOfFours + stat.noOfSixes)));
		return this.sort(battingStatsList,  statComparator);
	}
	/**
	 * Usecase 5
	 * Function returns players with best batting averages and best strike rate
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E>List getBestAverageAndStrikeRate() throws StatisticsAnalyserException {
		List<BattingCSV> strikeRate = getBestBattingAverage();
		return this.sort(strikeRate.stream().limit(20).collect(Collectors.toList()), battingStrikeRateComparator.reversed());
	}
	/**
	 * Usecase 6
	 * Function returns players scoring maximum runs with best averages
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E>List getMaximumRunsWithBestAverage() throws StatisticsAnalyserException {
		List<BattingCSV> maximumRuns = this.sort(battingStatsList, maximumRunsComparator.reversed());
		return this.sort(maximumRuns.stream().limit(20).collect(Collectors.toList()), battingAverageComparator.reversed());
	}
	/**
	 * Usecase 7
	 * Function returns players with the best bowling averages
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E>List getBestBowlingAverage() throws StatisticsAnalyserException {
		bowlingStatsList.removeIf(stat -> stat.avg == 0);
		return this.sort(bowlingStatsList, bowlingAverageComparator);
	}
	/**
	 * Usecase 8
	 * Function returns players with the best bowling strike rates
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E>List getBestBowlingStrikeRate() throws StatisticsAnalyserException {
		bowlingStatsList.removeIf(stat -> stat.strikeRate == 0);
		return this.sort(bowlingStatsList, bowlingStrikeRateComparator);
	}
	/**
	 * Usecase 9
	 * Function returns players with the best economy
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E>List getBestBowlingEconomy() throws StatisticsAnalyserException {
		Comparator<BowlingCSV> statComparator = Comparator.comparing(stat -> (stat.economy));
		return this.sort(bowlingStatsList, statComparator);
	}
	/**
	 * Usecase 10
	 * Function returns players with the best strike rates and having 4 or 5 wicket hauls
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E>List getBestBowlingStrikeRateWithHauls() throws StatisticsAnalyserException {
		bowlingStatsList.removeIf(stat -> (stat.strikeRate == 0 || (stat.fourWkts == 0 && stat.fiveWkts == 0)));
		return this.sort(bowlingStatsList, bowlingStrikeRateComparator);
	}
	/**
	 * Usecase 11
	 * Function returns players with the best bowling averages and strike rates
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E>List getBestBowlingAverageAndStrikeRate() throws StatisticsAnalyserException {
		List<BowlingCSV> strikeRate = getBestBowlingAverage();
		return this.sort(strikeRate.stream().limit(20).collect(Collectors.toList()), bowlingStrikeRateComparator);
	}
	/**
	 * Usecase 12
	 * Function returns players with maximum wickets and best averages
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E>List getMaximumWicketsWithBestAverage() throws StatisticsAnalyserException {
		List<BowlingCSV> maximumWickets = this.sort(bowlingStatsList, maximumWicketsComparator.reversed());
		return this.sort(maximumWickets.stream().limit(20).collect(Collectors.toList()), bowlingAverageComparator);
	}
	/**
	 * Usecase 13
	 * Function returns players with the best bowling and batting averages
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E>List getBestBattingAndBowlingAverage() throws StatisticsAnalyserException {
		List<BattingCSV> battingAverage = (List<BattingCSV>) getBestBattingAverage().stream().limit(50).collect(Collectors.toList());
		List<BowlingCSV> bowlingAverage = (List<BowlingCSV>) getBestBowlingAverage().stream().limit(50).collect(Collectors.toList());
		return getHybridPerformance(battingAverage, bowlingAverage);
	}
	/**
	 * Usecase 14
	 * Function returns players with best allround performance i.e runs + wickets
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E>List getAllRounder() throws StatisticsAnalyserException {
		List<BattingCSV> runs = (List<BattingCSV>) this.sort(battingStatsList, maximumRunsComparator).stream().limit(50).collect(Collectors.toList());
		List<BowlingCSV> wickets = (List<BowlingCSV>) this.sort(bowlingStatsList, maximumWicketsComparator).stream().limit(50).collect(Collectors.toList());
		return getHybridPerformance(runs, wickets);
	}
	private List<String> getHybridPerformance(List<BattingCSV> bat, List<BowlingCSV> bowl) {
		List<String> stats = new ArrayList<String>();
		for(BattingCSV b: bat ) {
			for(int i = 0;i < bowl.size(); i++) {
				if(b.playerName.equals(bowl.get(i).playerName)) {
					stats.add(b.playerName);
				}
			}
		}
		return stats;
	}
	/**
	 * Function returns players with maximum hundreds and having best averages
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E>List getMaximumHundredsWithBestAverage() throws StatisticsAnalyserException {
		battingStatsList.removeIf(stat -> stat.noOfHundreds==0);
		Comparator<BattingCSV> statComparator = Comparator.comparing(stat -> (stat.noOfHundreds) );
		List<BattingCSV> maximumHundreds = this.sort(battingStatsList, statComparator.reversed());
		return this.sort(maximumHundreds, battingAverageComparator.reversed());
	}
	/**
	 * Usecase 16
	 * Function returns players with the best batting averages without scoring a fifty/hundred
	 * @param <E>
	 * @return
	 * @throws StatisticsAnalyserException
	 */
	public <E>List getBestAverageWithoutHundredsOrFifties() throws StatisticsAnalyserException {
		battingStatsList.removeIf(stat -> (stat.noOfHundreds !=0 || stat.noOfFifties != 0));
		return this.sort(battingStatsList, battingAverageComparator.reversed());
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
