package com.ibm.pradeep.calendar;

/**
 * @author pradeep
 *
 */
public class WikiVacationPage {

	public static void main(String[] args) {
		printMonths(1, 2017);
	}
	
	public enum CalendarFormat {WIKI, HTML}
	private static CalendarFormat calFmt;
	
	private static int[] lastDayOfMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
	private static String[] namesOfMonth = {"January", "February", "March", 
											"April", "May", "June", "July",
											"August", "September", "October",
											"November", "December"};
	
	private static void printMonths(int startDay, int year) {
		calFmt = CalendarFormat.HTML;
		printMonths(1, startDay, 12, year);
	}
	
	private static void printMonths(int fromMonth, int startDay, int count, int year) {
		int nextFirstWeekDay = startDay;
		for (int i = 0; i < count; i++) {
			int month = i+fromMonth-1;
			int endDate = lastDayOfMonth[month];
			if (isFeb(month) && isLeap(year)) endDate += 1;
			if (i == 0) {
				printMonth(year, month, startDay, endDate);
			} else {
				printMonth(year, month, nextFirstWeekDay, endDate);
			}
			nextFirstWeekDay = (nextFirstWeekDay + endDate - 29) % 7 + 1;
		}
	}

	private static void printMonth(int year, int month, int startDay, int endDate) {
		switch (calFmt) {
		case WIKI:
			printMonthAsWiki(year, month, startDay, endDate);
			break;
		case HTML:
			printMonthAsHtml(year, month, startDay, endDate);
			break;
		}
	}

	private static boolean isLeap(int year) {
		return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
	}

	private static boolean isFeb(int month) {
		return month == 1;
	}

	/**
	 * @param year TODO
	 * @param month TODO
	 * @param startDay
	 * @param endDate
	 */
	private static void printMonthAsWiki(int year, int month, int startDay, int endDate) {
		System.out.println("h3. " + namesOfMonth[month] + " '" + String.valueOf(year%100));
		System.out.println();
		int startIndex = startDay - 1;
		int day = 0;
		System.out.println("||Sun||Mon||Tue||Wed||Thu||Fri||Sat||");
		for (int i=0; i<6 && day<endDate; i++) {
			StringBuffer datesOfWeek = new StringBuffer("|  ");
			for (int j=0; j<7; j++) {
				if (i == 0 && j < startIndex) {
					datesOfWeek.append("  ");
				} else if (day < endDate) {
					if (++day < 10) {
						datesOfWeek.append(' ');
					}
					datesOfWeek.append(day);
				} else {
					datesOfWeek.append("    ");
				}
				datesOfWeek.append("|");
				if (j<6 && day<endDate) {
					datesOfWeek.append("  ");
				}
			}
			System.out.println(datesOfWeek.toString());
		}
		System.out.println();
	}

	private static void printMonthAsHtml(int year, int month, int startDay, int endDate) {
		System.out.println("<h3 dir=\"ltr\"> " + namesOfMonth[month] + " '" + String.valueOf(year%100) + "</h3>");
		
		int startIndex = startDay - 1;
		int day = 0;
		
		System.out.println("<table class=\"withBorder\" dir=\"ltr\">");
		System.out.println("<tbody>");
		
		System.out.println("<tr><th>Sun</th><th>Mon</th><th>Tue</th><th>Wed</th><th>Thu</th><th>Fri</th><th>Sat</th></tr>");
		for (int i=0; i<6 && day<endDate; i++) {
			StringBuffer datesOfWeek = new StringBuffer("<tr>");
			for (int j=0; j<7; j++) {
				datesOfWeek.append("<td>");
				if (i == 0 && j < startIndex) {
					datesOfWeek.append("&nbsp;");
				} else if (day < endDate) {
					datesOfWeek.append(++day);
				} else {
					datesOfWeek.append("&nbsp;");
				}
				datesOfWeek.append("</td>");
			}
			datesOfWeek.append("</tr>");
			System.out.println(datesOfWeek.toString());
		}
		
		System.out.println("</tbody>");
		System.out.println("</table>");
		System.out.println("<p dir=\"ltr\">");
		System.out.println("&nbsp;</p>");
	}
}
