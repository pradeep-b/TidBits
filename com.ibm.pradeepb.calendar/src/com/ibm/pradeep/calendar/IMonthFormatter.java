package com.ibm.pradeep.calendar;

public interface IMonthFormatter {
	
	public void header(StringBuffer sb);
	
	public void firstRow(StringBuffer sb);
	
	public void startRow(StringBuffer sb);
	
	public void endRow(StringBuffer sb);
	
	public void footer(StringBuffer sb);
	
	public void space(StringBuffer sb);
	
	public void space(StringBuffer sb, int count);

}
