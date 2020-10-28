package com.ipl;

public class StatisticsAnalyserException extends Exception {
	public enum ExceptionType {
		NO_FILE, INCORRECT_FILE, UNABLE_TO_PARSE, NO_STATISTICS_DATA
	}
	public ExceptionType type;
	public StatisticsAnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}
}
