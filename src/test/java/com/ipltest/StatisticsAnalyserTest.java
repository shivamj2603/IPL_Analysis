package com.ipltest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.ipl.BattingCSV;
import com.ipl.BowlingCSV;
import com.ipl.StatisticsAnalyser;
import com.ipl.StatisticsAnalyserException;
import java.util.List;

import CSVBuilder.CSVBuilderException;

public class StatisticsAnalyserTest {
	private static final String BATTING_STATISTICS_CSVFILE = "C:\\Users\\shiva\\eclipse-workspace\\IPLAnalysis\\IPL2019FactsheetMostRuns3.csv";
	private static final String BOWLING_STATISTICS_CSVFILE = "C:\\Users\\shiva\\eclipse-workspace\\IPLAnalysis\\IPL2019FactsheetMostWkts3.csv";
	@Test
	public void givenBattingStatisticsCSVFile_ifMatchesTotalNumberOfRecords_ShouldReturnTrue() throws IOException {
		StatisticsAnalyser analyser = new StatisticsAnalyser();
		int count = 0;
		try {
			count = analyser.loadBattingStatsData(BATTING_STATISTICS_CSVFILE);
		} catch (StatisticsAnalyserException exception) {
			exception.printStackTrace();
		}
		assertEquals(101, count);
	}
	@Test
	public void givenBowlingStatisticsCSVFile_ifMatchesTotalNumberOfRecords_ShouldReturnTrue() throws IOException {
		StatisticsAnalyser analyser = new StatisticsAnalyser();
		int count = 0;
		try {
			count = analyser.loadBowlingStatsData(BOWLING_STATISTICS_CSVFILE);
		} catch (StatisticsAnalyserException exception) {
			exception.printStackTrace();
		}
		assertEquals(99, count);
	}
	@Test
	public void givenBattingStatistics_WhenSortedByAverage_ShouldReturnSortedResult()
			throws IOException, StatisticsAnalyserException, CSVBuilderException {
		StatisticsAnalyser analyser = new StatisticsAnalyser();
		analyser.loadBattingStatsData(BATTING_STATISTICS_CSVFILE);
		List<BattingCSV> sortedByAverage = analyser.getBestBattingAverage();
		assertEquals("MS Dhoni", sortedByAverage.get(0).playerName);
		}
	@Test
	public void givenBattingStatistics_WhenSortedByStrikeRate_ShouldReturnSortedResult()
			throws IOException, StatisticsAnalyserException, CSVBuilderException {
		StatisticsAnalyser analyser = new StatisticsAnalyser();
		analyser.loadBattingStatsData(BATTING_STATISTICS_CSVFILE);
		List<BattingCSV> sortedByStrikeRate = analyser.getBestStrikeRate();
		assertEquals("Ishant Sharma", sortedByStrikeRate.get(0).playerName);
		}
	@Test
	public void givenBattingStatistics_WhenSortedByMostBoundaries_ShouldReturnSortedResult()
			throws IOException, StatisticsAnalyserException, CSVBuilderException {
		StatisticsAnalyser analyser = new StatisticsAnalyser();
		analyser.loadBattingStatsData(BATTING_STATISTICS_CSVFILE);
		List<BattingCSV> sortedByBoundaries = analyser.getMostBoundaries();
		assertEquals("Andre Russell", sortedByBoundaries.get(0).playerName);
		}
	@Test
	public void givenBattingStatistics_WhenSortedByBestStrikeRatesWithBoundaries_ShouldReturnSortedResult()
			throws IOException, StatisticsAnalyserException, CSVBuilderException {
		StatisticsAnalyser analyser = new StatisticsAnalyser();
		analyser.loadBattingStatsData(BATTING_STATISTICS_CSVFILE);
		List<BattingCSV> sortedByStrikeRateAndBoundaries = analyser.getBestStrikeRateWithBoundaries();
		assertEquals("Ishant Sharma", sortedByStrikeRateAndBoundaries.get(0).playerName);
		}
	@Test
	public void givenBattingStatistics_WhenSortedByBestBattingAverageWithBestBattingStrikeRate_ShouldReturnSortedResult()
			throws IOException, StatisticsAnalyserException, CSVBuilderException {
		StatisticsAnalyser analyser = new StatisticsAnalyser();
		analyser.loadBattingStatsData(BATTING_STATISTICS_CSVFILE);
		List<BattingCSV> sortedByStrikeRateAndBestAverage = analyser.getBestAverageAndStrikeRate();
		assertEquals("Andre Russell", sortedByStrikeRateAndBestAverage.get(0).playerName);
		}
	@Test
	public void givenBattingStatistics_WhenSortedByMaximumRunsAndBestBattingAverage_ShouldReturnSortedResult()
			throws IOException, StatisticsAnalyserException, CSVBuilderException {
		StatisticsAnalyser analyser = new StatisticsAnalyser();
		analyser.loadBattingStatsData(BATTING_STATISTICS_CSVFILE);
		List<BattingCSV> sortedByMaximumRunsAndBestAverage = analyser.getMaximumRunsWithBestAverage();
		assertEquals("MS Dhoni", sortedByMaximumRunsAndBestAverage.get(0).playerName);
		}
	@Test
	public void givenBowlingStatistics_WhenSortedByAverage_ShouldReturnSortedResult()
			throws IOException, StatisticsAnalyserException, CSVBuilderException {
		StatisticsAnalyser analyser = new StatisticsAnalyser();
		analyser.loadBowlingStatsData(BOWLING_STATISTICS_CSVFILE);
		List<BowlingCSV> sortedByAverage = analyser.getBestBowlingAverage();
		assertEquals("Anukul Roy", sortedByAverage.get(0).playerName);
		}
	@Test
	public void givenBowlingStatistics_WhenSortedByStrikeRate_ShouldReturnSortedResult()
			throws IOException, StatisticsAnalyserException, CSVBuilderException {
		StatisticsAnalyser analyser = new StatisticsAnalyser();
		analyser.loadBowlingStatsData(BOWLING_STATISTICS_CSVFILE);
		List<BowlingCSV> sortedByStrikeRate = analyser.getBestBowlingStrikeRate();
		assertEquals("Alzarri Joseph", sortedByStrikeRate.get(0).playerName);
		}
	@Test
	public void givenBowlingStatistics_WhenSortedByEconomy_ShouldReturnSortedResult()
			throws IOException, StatisticsAnalyserException, CSVBuilderException {
		StatisticsAnalyser analyser = new StatisticsAnalyser();
		analyser.loadBowlingStatsData(BOWLING_STATISTICS_CSVFILE);
		List<BowlingCSV> sortedByEconomy = analyser.getBestBowlingEconomy();
		assertEquals("Shivam Dube", sortedByEconomy.get(0).playerName);
		}
	@Test
	public void givenBowlingStatistics_WhenSortedByStrikeRateAndHauls_ShouldReturnSortedResult()
			throws IOException, StatisticsAnalyserException, CSVBuilderException {
		StatisticsAnalyser analyser = new StatisticsAnalyser();
		analyser.loadBowlingStatsData(BOWLING_STATISTICS_CSVFILE);
		List<BowlingCSV> sortedByStrikeRateAndHauls = analyser.getBestBowlingStrikeRateWithHauls();
		assertEquals("Alzarri Joseph", sortedByStrikeRateAndHauls.get(0).playerName);
		}
	@Test
	public void givenBowlingStatistics_WhenSortedByBestAverageWithBestStrikeRate_ShouldReturnSortedResult()
			throws IOException, StatisticsAnalyserException, CSVBuilderException {
		StatisticsAnalyser analyser = new StatisticsAnalyser();
		analyser.loadBowlingStatsData(BOWLING_STATISTICS_CSVFILE);
		List<BowlingCSV> sortedByStrikeRateAndBestAverage = analyser.getBestBowlingAverageAndStrikeRate();
		assertEquals("Alzarri Joseph", sortedByStrikeRateAndBestAverage.get(0).playerName);
		}
	@Test
	public void givenBowlingStatistics_WhenSortedByMaximumWicketsWithBestAverage_ShouldReturnSortedResult()
			throws IOException, StatisticsAnalyserException, CSVBuilderException {
		StatisticsAnalyser analyser = new StatisticsAnalyser();
		analyser.loadBowlingStatsData(BOWLING_STATISTICS_CSVFILE);
		List<BowlingCSV> sortedByMaximumWicketsAndBestAverage = analyser.getMaximumWicketsWithBestAverage();
		assertEquals("Kagiso Rabada", sortedByMaximumWicketsAndBestAverage.get(0).playerName);
		}
	@Test
	public void givenStatistics_WhenSortedByBowlingAndBattingAverage_ShouldReturnSortedResult()
			throws IOException, StatisticsAnalyserException, CSVBuilderException {
		StatisticsAnalyser analyser = new StatisticsAnalyser();
		analyser.loadBowlingStatsData(BOWLING_STATISTICS_CSVFILE);
		analyser.loadBattingStatsData(BATTING_STATISTICS_CSVFILE);
		List average = analyser.getBestBattingAndBowlingAverage();
        assertEquals("Andre Russell", average.get(0));
		}
	@Test
	public void givenStatistics_WhenSortedByMaximumRunsAndWickets_ShouldReturnSortedResult()
			throws IOException, StatisticsAnalyserException, CSVBuilderException {
		StatisticsAnalyser analyser = new StatisticsAnalyser();
		analyser.loadBowlingStatsData(BOWLING_STATISTICS_CSVFILE);
		analyser.loadBattingStatsData(BATTING_STATISTICS_CSVFILE);
		List average = analyser.getAllRounder();
        assertEquals("Shakib Al Hasan", average.get(0));
		}
	@Test
	public void givenBattingStatistics_WhenSortedByMaximumHundredsAndBestBattingAverage_ShouldReturnSortedResult()
			throws IOException, StatisticsAnalyserException, CSVBuilderException {
		StatisticsAnalyser analyser = new StatisticsAnalyser();
		analyser.loadBattingStatsData(BATTING_STATISTICS_CSVFILE);
		List<BattingCSV> sortedByMaximumHundredsAndBestAverage = analyser.getMaximumHundredsWithBestAverage();
		assertEquals("David Warner ", sortedByMaximumHundredsAndBestAverage.get(0).playerName);
		}
}
