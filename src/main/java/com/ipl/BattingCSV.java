package com.ipl;
import com.opencsv.bean.CsvBindByName;

public class BattingCSV {
	@CsvBindByName(column = "POS")
	public Integer position;
	@CsvBindByName(column = "PLAYER")
	public String playerName;
	@CsvBindByName(column = "Mat")
	public Integer matches;
	@CsvBindByName(column = "Inns")
	public Integer innings;
	@CsvBindByName(column = "NO")
	public Integer notOut;	
	@CsvBindByName(column = "Runs")
	public Integer runs;
	@CsvBindByName(column = "HS")
	public Integer hS;
	@CsvBindByName(column = "Avg")
	public Float avg;
	@CsvBindByName(column = "BF")
	public Integer bF;
	@CsvBindByName(column = "SR")
	public Float strikeRate;
	@CsvBindByName(column = "100")
	public Integer noOfHundreds;
	@CsvBindByName(column = "50")
	public Integer noOfFifties;
	@CsvBindByName(column = "4s")
	public Integer noOfFours;
	@CsvBindByName(column = "6s")
	public Integer noOfSixes;
}
