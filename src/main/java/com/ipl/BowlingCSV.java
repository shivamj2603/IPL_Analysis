package com.ipl;
import com.opencsv.bean.CsvBindByName;

public class BowlingCSV {
	@CsvBindByName(column = "POS")
	public Integer position;
	@CsvBindByName(column = "PLAYER")
	public String playerName;
	@CsvBindByName(column = "Mat")
	public Integer matches;
	@CsvBindByName(column = "Inns")
	public Integer innings;
	@CsvBindByName(column = "Ov")
	public Float over;	
	@CsvBindByName(column = "Runs")
	public Integer runs;
	@CsvBindByName(column = "Wkts")
	public Integer wickets;
	@CsvBindByName(column = "Avg")
	public Float avg;
	@CsvBindByName(column = "Econ")
	public Float economy;
	@CsvBindByName(column = "SR")
	public Float strikeRate;
	@CsvBindByName(column = "4w")
	public Integer fourWkts;
	@CsvBindByName(column = "5w")
	public Integer fiveWkts;
}
