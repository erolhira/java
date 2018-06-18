package basics;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static java.time.temporal.TemporalAdjusters.*;
import static java.time.DayOfWeek.*;
import static java.time.temporal.TemporalField.*;

import java.time.Duration;
import java.time.Instant;

import org.junit.Test;
import static java.time.temporal.ChronoUnit.*;

public class LocalDateAndTime {
	
	@Test
	public void testLocalDate(){
		
		LocalDate now = LocalDate.now();
		System.out.println("now: " + now);
		
		LocalDate javaBirthDate = LocalDate.of(1995, 5, 23);
		System.out.println("javaBirthDate: " + javaBirthDate);
		
		System.out.println("javaBirthDate is before now: " + javaBirthDate.isBefore(now));
		System.out.println("nowPlusMonth: " + now.plusMonths(2));
		System.out.println("nextTuesday: " + now.with(next(TUESDAY)));		
	}
	
	@Test
	public void testLocalTime(){
		
		LocalTime now = LocalTime.now();
		System.out.println("now: " + now);		
		
		System.out.println("1 hour 15 min later: " + now.plusHours(1).plusMinutes(15));
		System.out.println("minutes truncated from now: " + now.truncatedTo(MINUTES));
		System.out.println("It is the " + now.toSecondOfDay()/60 + " th minute");
		
		LocalTime lunch = LocalTime.of(12, 30);
		System.out.println("lunch is coming: " + lunch.isAfter(now));
		
		LocalTime bedTime = LocalTime.of(22, 30);
		System.out.println("hours to bedTime: " + now.until(bedTime, HOURS));
	}
	
	@Test
	public void testLocalDateTime(){
		
		LocalDateTime now = LocalDateTime.now();
		System.out.println("now: " + now);
		System.out.println("now is day of week: " + now.getDayOfWeek());
		
		LocalDate flightDate = LocalDate.of(2014, Month.MARCH, 31);
		LocalTime flightTime = LocalTime.of(21, 45);		
		LocalDateTime flight = LocalDateTime.of(flightDate, flightTime);
		System.out.println("flight: " + flight);
		
		System.out.println("3 days after flight: " + flight.plusDays(3).plusHours(8));
	}
	
	@Test
	public void testZoneId(){
		
		ZoneId nyZoneId = ZoneId.of("America/New_York");
		System.out.println("ny zoneId: " + nyZoneId);
		
		ZoneOffset istanbulOffset = ZoneOffset.of("+2");
		ZoneId istZoneId = ZoneId.ofOffset("UTC", istanbulOffset);
		System.out.println("istZoneId: " + istZoneId);
		
		LocalDate date = LocalDate.of(2014, Month.MARCH, 23);
		LocalTime time = LocalTime.of(9, 30);
		LocalDateTime localTime = LocalDateTime.of(date, time);
		ZonedDateTime zoneTime = ZonedDateTime.of(date, time, istZoneId);
		System.out.println("localTime: " + localTime);
		System.out.println("zoneTime: " + zoneTime);
	}
	
	@Test
	public void testInstant() throws InterruptedException{
		
		Instant now = Instant.now();
		Thread.sleep(0, 1);
		Instant later = Instant.now();
		System.out.println("now is before later: " + now.isBefore(later));
	}
	
	@Test
	public void testPeriod(){
		
		Period oneDay = Period.ofDays(1);
		System.out.println("Period of one day: " + oneDay);
		
		LocalDateTime beforeDST = LocalDateTime.of(2014, Month.MARCH, 8, 12, 00);
		ZonedDateTime newYorkTime = ZonedDateTime.of(beforeDST, ZoneId.of("America/New_York"));
		
		//The time is preserved, because only "days" are added.
		System.out.println("Before: " + newYorkTime);
		System.out.println("After : " + newYorkTime.plus(oneDay));
	}
	
	@Test
	public void testDuration(){
		
		Duration one24hourDay = Duration.ofDays(1);
		System.out.println("Duration of one day: " + one24hourDay);
		
		LocalDateTime beforeDST = LocalDateTime.of(2014, Month.MARCH, 8, 12, 00);
		ZonedDateTime newYorkTime = ZonedDateTime.of(beforeDST, ZoneId.of("America/New_York"));
		
		//The time is not preserved because 24 hours are added.
		System.out.println("Before: " + newYorkTime);
		System.out.println("After: " + newYorkTime.plus(one24hourDay));
	}
	
	@Test
	public void calculatingBetweenDays(){
		
		LocalDate todate = LocalDate.of(2017, Month.DECEMBER, 25);
		LocalDate today = LocalDate.now();
		long days = DAYS.between(today, todate);
		System.out.println("There are " + days + " between dates");
		
		//Period also provides a between method
		Period tilXMas = Period.between(today, todate);
		System.out.println("There are " + tilXMas.getMonths() + " months and " + tilXMas.getDays() + " days til todate");			
	}
	
	@Test
	public void testFluent() {
		
		// Not very readable - is this June 11 or November 6th?
		LocalDate myBday = LocalDate.of(1970, 6, 11);
		
		// A fluent approach
		myBday = Year.of(1970).atMonth(Month.JUNE).atDay(11);
		
		// Schedule a meeting fluently
		LocalDateTime meeting = LocalDate.of(2014, Month.MARCH, 25).atTime(12, 30);
		
		// Schedule that meeting using the London timezone
		ZonedDateTime meetingUK = meeting.atZone(ZoneId.of("Europe/London"));
		
		// What time is it in San Francisco for that meeting?
		ZonedDateTime earlyMeeting = meetingUK.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
	}
	
}
