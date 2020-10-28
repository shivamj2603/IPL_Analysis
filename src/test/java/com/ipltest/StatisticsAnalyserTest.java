package com.ipltest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.ipl.BattingCSV;
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
		sortedByMaximumRunsAndBestAverage.forEach(s->System.out.println(s.playerName));
		assertEquals("MS Dhoni", sortedByMaximumRunsAndBestAverage.get(0).playerName);
		}
}
