/*
 * File:    DateTimeUtilityTest.java
 * Package: commons.time
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.time;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of DateTimeUtility.
 *
 * @see DateTimeUtility
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({DateTimeUtility.class})
public class DateTimeUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(DateTimeUtilityTest.class);
    
    
    //Initialization
    
    /**
     * The JUnit class setup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @BeforeClass
    public static void setupClass() throws Exception {
    }
    
    /**
     * The JUnit class cleanup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @AfterClass
    public static void cleanupClass() throws Exception {
    }
    
    /**
     * The JUnit setup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @Before
    public void setup() throws Exception {
    }
    
    /**
     * The JUnit cleanup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @After
    public void cleanup() throws Exception {
    }
    
    
    //Tests
    
    /**
     * JUnit test of constants.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#MINIMUM_YEAR
     * @see DateTimeUtility#MAXIMUM_YEAR
     * @see DateTimeUtility#MINIMUM_MONTH
     * @see DateTimeUtility#MAXIMUM_MONTH
     * @see DateTimeUtility#MINIMUM_DAY
     * @see DateTimeUtility#MAXIMUM_DAY
     * @see DateTimeUtility#MINIMUM_HOUR
     * @see DateTimeUtility#MAXIMUM_HOUR
     * @see DateTimeUtility#MINIMUM_MINUTE
     * @see DateTimeUtility#MAXIMUM_MINUTE
     * @see DateTimeUtility#MINIMUM_SECOND
     * @see DateTimeUtility#MAXIMUM_SECOND
     * @see DateTimeUtility#DATE_PATTERN
     * @see DateTimeUtility#TIME_PATTERN
     */
    @Test
    public void testConstants() throws Exception {
        //constants
        Assert.assertEquals(1, DateTimeUtility.MINIMUM_YEAR);
        Assert.assertEquals(Integer.MAX_VALUE - 1, DateTimeUtility.MAXIMUM_YEAR);
        Assert.assertEquals(1, DateTimeUtility.MINIMUM_MONTH);
        Assert.assertEquals(12, DateTimeUtility.MAXIMUM_MONTH);
        Assert.assertEquals(1, DateTimeUtility.MINIMUM_DAY);
        Assert.assertEquals(31, DateTimeUtility.MAXIMUM_DAY);
        Assert.assertEquals(0, DateTimeUtility.MINIMUM_HOUR);
        Assert.assertEquals(23, DateTimeUtility.MAXIMUM_HOUR);
        Assert.assertEquals(0, DateTimeUtility.MINIMUM_MINUTE);
        Assert.assertEquals(59, DateTimeUtility.MAXIMUM_MINUTE);
        Assert.assertEquals(0, DateTimeUtility.MINIMUM_SECOND);
        Assert.assertEquals(59, DateTimeUtility.MAXIMUM_SECOND);
        
        //patterns
        Assert.assertEquals("(?<year>\\d+)-(?<month>\\d{2})-(?<day>\\d{2})", DateTimeUtility.DATE_PATTERN.pattern());
        Assert.assertEquals("(?<hour>\\d{2}):(?<minute>\\d{2})(:(?<second>\\d{2}))?", DateTimeUtility.TIME_PATTERN.pattern());
    }
    
    /**
     * JUnit test of Month.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility.Month
     */
    @Test
    public void testMonth() throws Exception {
        Assert.assertEquals(12, DateTimeUtility.Month.values().length);
        Assert.assertEquals(DateTimeUtility.Month.JANUARY, DateTimeUtility.Month.values()[0]);
        Assert.assertEquals(DateTimeUtility.Month.FEBRUARY, DateTimeUtility.Month.values()[1]);
        Assert.assertEquals(DateTimeUtility.Month.MARCH, DateTimeUtility.Month.values()[2]);
        Assert.assertEquals(DateTimeUtility.Month.APRIL, DateTimeUtility.Month.values()[3]);
        Assert.assertEquals(DateTimeUtility.Month.MAY, DateTimeUtility.Month.values()[4]);
        Assert.assertEquals(DateTimeUtility.Month.JUNE, DateTimeUtility.Month.values()[5]);
        Assert.assertEquals(DateTimeUtility.Month.JULY, DateTimeUtility.Month.values()[6]);
        Assert.assertEquals(DateTimeUtility.Month.AUGUST, DateTimeUtility.Month.values()[7]);
        Assert.assertEquals(DateTimeUtility.Month.SEPTEMBER, DateTimeUtility.Month.values()[8]);
        Assert.assertEquals(DateTimeUtility.Month.OCTOBER, DateTimeUtility.Month.values()[9]);
        Assert.assertEquals(DateTimeUtility.Month.NOVEMBER, DateTimeUtility.Month.values()[10]);
        Assert.assertEquals(DateTimeUtility.Month.DECEMBER, DateTimeUtility.Month.values()[11]);
    }
    
    /**
     * JUnit test of Weekday.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility.Weekday
     */
    @Test
    public void testWeekday() throws Exception {
        Assert.assertEquals(7, DateTimeUtility.Weekday.values().length);
        Assert.assertEquals(DateTimeUtility.Weekday.MONDAY, DateTimeUtility.Weekday.values()[0]);
        Assert.assertEquals(DateTimeUtility.Weekday.TUESDAY, DateTimeUtility.Weekday.values()[1]);
        Assert.assertEquals(DateTimeUtility.Weekday.WEDNESDAY, DateTimeUtility.Weekday.values()[2]);
        Assert.assertEquals(DateTimeUtility.Weekday.THURSDAY, DateTimeUtility.Weekday.values()[3]);
        Assert.assertEquals(DateTimeUtility.Weekday.FRIDAY, DateTimeUtility.Weekday.values()[4]);
        Assert.assertEquals(DateTimeUtility.Weekday.SATURDAY, DateTimeUtility.Weekday.values()[5]);
        Assert.assertEquals(DateTimeUtility.Weekday.SUNDAY, DateTimeUtility.Weekday.values()[6]);
    }
    
    /**
     * JUnit test of dateToDateString.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#dateToDateString(String)
     * @see DateTimeUtility#dateToDateString(Date)
     */
    @Test
    public void testDateToDateString() throws Exception {
        //valid
        Assert.assertEquals("March 16th, 2017", DateTimeUtility.dateToDateString("2017-03-16"));
        Assert.assertEquals("January 1st, 2025", DateTimeUtility.dateToDateString("2025-01-01"));
        Assert.assertEquals("December 23rd, 1967", DateTimeUtility.dateToDateString("1967-12-23"));
        Assert.assertEquals("February 12th, 1732", DateTimeUtility.dateToDateString("1732-02-12"));
        
        //year edge case
        Assert.assertEquals("January 1st, 10546", DateTimeUtility.dateToDateString("10546-01-01"));
        Assert.assertEquals("January 1st, 1", DateTimeUtility.dateToDateString("1-01-01"));
        
        //invalid day in month
        Assert.assertEquals("2000-02-30", DateTimeUtility.dateToDateString("2000-02-30"));
        Assert.assertEquals("2001-02-29", DateTimeUtility.dateToDateString("2001-02-29"));
        Assert.assertEquals("1940-09-31", DateTimeUtility.dateToDateString("1940-09-31"));
        
        //invalid ranges
        Assert.assertEquals("0-01-01", DateTimeUtility.dateToDateString("0-01-01"));
        Assert.assertEquals("-154-01-01", DateTimeUtility.dateToDateString("-154-01-01"));
        Assert.assertEquals("2000-00-01", DateTimeUtility.dateToDateString("2000-00-01"));
        Assert.assertEquals("2000--11-01", DateTimeUtility.dateToDateString("2000--11-01"));
        Assert.assertEquals("2000-34-01", DateTimeUtility.dateToDateString("2000-34-01"));
        Assert.assertEquals("2000-01-00", DateTimeUtility.dateToDateString("2000-01-00"));
        Assert.assertEquals("2000-01--11", DateTimeUtility.dateToDateString("2000-01--11"));
        Assert.assertEquals("2000-01-57", DateTimeUtility.dateToDateString("2000-01-57"));
        
        //date
        Assert.assertEquals("March 16th, 2017", DateTimeUtility.dateToDateString(new SimpleDateFormat("yyyy-MM-dd").parse("2017-03-16")));
        Assert.assertEquals("January 1st, 2025", DateTimeUtility.dateToDateString(new SimpleDateFormat("yyyy-MM-dd").parse("2025-01-01")));
        Assert.assertEquals("December 23rd, 1967", DateTimeUtility.dateToDateString(new SimpleDateFormat("yyyy-MM-dd").parse("1967-12-23")));
        Assert.assertEquals("February 12th, 1732", DateTimeUtility.dateToDateString(new SimpleDateFormat("yyyy-MM-dd").parse("1732-02-12")));
        
        //invalid date string
        Assert.assertEquals("March 16, 2017", DateTimeUtility.dateToDateString("March 16, 2017"));
        Assert.assertEquals("a date", DateTimeUtility.dateToDateString("a date"));
    }
    
    /**
     * JUnit test of monthToMonthString.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#monthToMonthString(int)
     */
    @Test
    public void testMonthToMonthString() throws Exception {
        //valid
        Assert.assertEquals("January", DateTimeUtility.monthToMonthString(1));
        Assert.assertEquals("February", DateTimeUtility.monthToMonthString(2));
        Assert.assertEquals("March", DateTimeUtility.monthToMonthString(3));
        Assert.assertEquals("April", DateTimeUtility.monthToMonthString(4));
        Assert.assertEquals("May", DateTimeUtility.monthToMonthString(5));
        Assert.assertEquals("June", DateTimeUtility.monthToMonthString(6));
        Assert.assertEquals("July", DateTimeUtility.monthToMonthString(7));
        Assert.assertEquals("August", DateTimeUtility.monthToMonthString(8));
        Assert.assertEquals("September", DateTimeUtility.monthToMonthString(9));
        Assert.assertEquals("October", DateTimeUtility.monthToMonthString(10));
        Assert.assertEquals("November", DateTimeUtility.monthToMonthString(11));
        Assert.assertEquals("December", DateTimeUtility.monthToMonthString(12));
        
        //invalid month indices
        Assert.assertEquals("", DateTimeUtility.monthToMonthString(0));
        Assert.assertEquals("", DateTimeUtility.monthToMonthString(-1));
        Assert.assertEquals("", DateTimeUtility.monthToMonthString(13));
        Assert.assertEquals("", DateTimeUtility.monthToMonthString(999));
    }
    
    /**
     * JUnit test of dayToDayOfMonthString.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#dayToDayOfMonthString(int)
     */
    @Test
    public void testDayToDayOfMonthString() throws Exception {
        //valid
        Assert.assertEquals("1st", DateTimeUtility.dayToDayOfMonthString(1));
        Assert.assertEquals("2nd", DateTimeUtility.dayToDayOfMonthString(2));
        Assert.assertEquals("3rd", DateTimeUtility.dayToDayOfMonthString(3));
        Assert.assertEquals("4th", DateTimeUtility.dayToDayOfMonthString(4));
        Assert.assertEquals("5th", DateTimeUtility.dayToDayOfMonthString(5));
        Assert.assertEquals("6th", DateTimeUtility.dayToDayOfMonthString(6));
        Assert.assertEquals("7th", DateTimeUtility.dayToDayOfMonthString(7));
        Assert.assertEquals("8th", DateTimeUtility.dayToDayOfMonthString(8));
        Assert.assertEquals("9th", DateTimeUtility.dayToDayOfMonthString(9));
        Assert.assertEquals("10th", DateTimeUtility.dayToDayOfMonthString(10));
        Assert.assertEquals("11th", DateTimeUtility.dayToDayOfMonthString(11));
        Assert.assertEquals("12th", DateTimeUtility.dayToDayOfMonthString(12));
        Assert.assertEquals("13th", DateTimeUtility.dayToDayOfMonthString(13));
        Assert.assertEquals("14th", DateTimeUtility.dayToDayOfMonthString(14));
        Assert.assertEquals("15th", DateTimeUtility.dayToDayOfMonthString(15));
        Assert.assertEquals("16th", DateTimeUtility.dayToDayOfMonthString(16));
        Assert.assertEquals("17th", DateTimeUtility.dayToDayOfMonthString(17));
        Assert.assertEquals("18th", DateTimeUtility.dayToDayOfMonthString(18));
        Assert.assertEquals("19th", DateTimeUtility.dayToDayOfMonthString(19));
        Assert.assertEquals("20th", DateTimeUtility.dayToDayOfMonthString(20));
        Assert.assertEquals("21st", DateTimeUtility.dayToDayOfMonthString(21));
        Assert.assertEquals("22nd", DateTimeUtility.dayToDayOfMonthString(22));
        Assert.assertEquals("23rd", DateTimeUtility.dayToDayOfMonthString(23));
        Assert.assertEquals("24th", DateTimeUtility.dayToDayOfMonthString(24));
        Assert.assertEquals("25th", DateTimeUtility.dayToDayOfMonthString(25));
        Assert.assertEquals("26th", DateTimeUtility.dayToDayOfMonthString(26));
        Assert.assertEquals("27th", DateTimeUtility.dayToDayOfMonthString(27));
        Assert.assertEquals("28th", DateTimeUtility.dayToDayOfMonthString(28));
        Assert.assertEquals("29th", DateTimeUtility.dayToDayOfMonthString(29));
        Assert.assertEquals("30th", DateTimeUtility.dayToDayOfMonthString(30));
        Assert.assertEquals("31st", DateTimeUtility.dayToDayOfMonthString(31));
        
        //invalid day values
        Assert.assertEquals("", DateTimeUtility.dayToDayOfMonthString(0));
        Assert.assertEquals("", DateTimeUtility.dayToDayOfMonthString(-1));
        Assert.assertEquals("", DateTimeUtility.dayToDayOfMonthString(32));
        Assert.assertEquals("", DateTimeUtility.dayToDayOfMonthString(999));
    }
    
    /**
     * JUnit test of timeToTimeString.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#timeToTimeString(String, boolean)
     * @see DateTimeUtility#timeToTimeString(Date, boolean)
     * @see DateTimeUtility#timeToTimeString(String)
     * @see DateTimeUtility#timeToTimeString(Date)
     */
    @Test
    public void testTimeToTimeString() throws Exception {
        //valid
        Assert.assertEquals("11:15 AM", DateTimeUtility.timeToTimeString("11:15"));
        Assert.assertEquals("11:15 PM", DateTimeUtility.timeToTimeString("23:15"));
        Assert.assertEquals("12:59 AM", DateTimeUtility.timeToTimeString("00:59"));
        Assert.assertEquals("12:00 AM", DateTimeUtility.timeToTimeString("00:00"));
        Assert.assertEquals("11:59 PM", DateTimeUtility.timeToTimeString("23:59"));
        Assert.assertEquals("3:08 PM", DateTimeUtility.timeToTimeString("15:08"));
        Assert.assertEquals("1:01 PM", DateTimeUtility.timeToTimeString("13:01"));
        
        //seconds
        Assert.assertEquals("11:15 AM", DateTimeUtility.timeToTimeString("11:15:45"));
        Assert.assertEquals("11:15 PM", DateTimeUtility.timeToTimeString("23:15:01"));
        Assert.assertEquals("12:59 AM", DateTimeUtility.timeToTimeString("00:59:00"));
        Assert.assertEquals("12:00 AM", DateTimeUtility.timeToTimeString("00:00:00"));
        Assert.assertEquals("11:59 PM", DateTimeUtility.timeToTimeString("23:59:00"));
        Assert.assertEquals("3:08 PM", DateTimeUtility.timeToTimeString("15:08:59"));
        Assert.assertEquals("1:01 PM", DateTimeUtility.timeToTimeString("13:01:58"));
        
        //include seconds
        Assert.assertEquals("11:15:45 AM", DateTimeUtility.timeToTimeString("11:15:45", true));
        Assert.assertEquals("11:15:01 PM", DateTimeUtility.timeToTimeString("23:15:01", true));
        Assert.assertEquals("12:59:00 AM", DateTimeUtility.timeToTimeString("00:59:00", true));
        Assert.assertEquals("12:00:00 AM", DateTimeUtility.timeToTimeString("00:00:00", true));
        Assert.assertEquals("11:59:00 PM", DateTimeUtility.timeToTimeString("23:59:00", true));
        Assert.assertEquals("3:08:59 PM", DateTimeUtility.timeToTimeString("15:08:59", true));
        Assert.assertEquals("1:01:58 PM", DateTimeUtility.timeToTimeString("13:01:58", true));
        
        //include seconds, no seconds
        Assert.assertEquals("11:15:00 AM", DateTimeUtility.timeToTimeString("11:15", true));
        Assert.assertEquals("11:15:00 PM", DateTimeUtility.timeToTimeString("23:15", true));
        Assert.assertEquals("12:59:00 AM", DateTimeUtility.timeToTimeString("00:59", true));
        Assert.assertEquals("12:00:00 AM", DateTimeUtility.timeToTimeString("00:00", true));
        Assert.assertEquals("11:59:00 PM", DateTimeUtility.timeToTimeString("23:59", true));
        Assert.assertEquals("3:08:00 PM", DateTimeUtility.timeToTimeString("15:08", true));
        Assert.assertEquals("1:01:00 PM", DateTimeUtility.timeToTimeString("13:01", true));
        
        //invalid ranges
        Assert.assertEquals("-1:15", DateTimeUtility.timeToTimeString("-1:15"));
        Assert.assertEquals("1:15", DateTimeUtility.timeToTimeString("1:15"));
        Assert.assertEquals("24:15", DateTimeUtility.timeToTimeString("24:15"));
        Assert.assertEquals("124:59", DateTimeUtility.timeToTimeString("124:59"));
        Assert.assertEquals("00:-1", DateTimeUtility.timeToTimeString("00:-1"));
        Assert.assertEquals("00:9", DateTimeUtility.timeToTimeString("00:9"));
        Assert.assertEquals("23:78", DateTimeUtility.timeToTimeString("23:78"));
        Assert.assertEquals("-1:15:01", DateTimeUtility.timeToTimeString("-1:15:01"));
        Assert.assertEquals("1:15:45", DateTimeUtility.timeToTimeString("1:15:45"));
        Assert.assertEquals("24:15:45", DateTimeUtility.timeToTimeString("24:15:45"));
        Assert.assertEquals("124:59:31", DateTimeUtility.timeToTimeString("124:59:31"));
        Assert.assertEquals("00:-1:00", DateTimeUtility.timeToTimeString("00:-1:00"));
        Assert.assertEquals("00:9:00", DateTimeUtility.timeToTimeString("00:9:00"));
        Assert.assertEquals("23:78:44", DateTimeUtility.timeToTimeString("23:78:44"));
        Assert.assertEquals("11:15:-1", DateTimeUtility.timeToTimeString("11:15:-1"));
        Assert.assertEquals("23:15:99", DateTimeUtility.timeToTimeString("23:15:99"));
        
        //invalid ranges, include seconds
        Assert.assertEquals("-1:15", DateTimeUtility.timeToTimeString("-1:15", true));
        Assert.assertEquals("1:15", DateTimeUtility.timeToTimeString("1:15", true));
        Assert.assertEquals("24:15", DateTimeUtility.timeToTimeString("24:15", true));
        Assert.assertEquals("124:59", DateTimeUtility.timeToTimeString("124:59", true));
        Assert.assertEquals("00:-1", DateTimeUtility.timeToTimeString("00:-1", true));
        Assert.assertEquals("00:9", DateTimeUtility.timeToTimeString("00:9", true));
        Assert.assertEquals("23:78", DateTimeUtility.timeToTimeString("23:78", true));
        Assert.assertEquals("-1:15:01", DateTimeUtility.timeToTimeString("-1:15:01", true));
        Assert.assertEquals("1:15:45", DateTimeUtility.timeToTimeString("1:15:45", true));
        Assert.assertEquals("24:15:45", DateTimeUtility.timeToTimeString("24:15:45", true));
        Assert.assertEquals("124:59:31", DateTimeUtility.timeToTimeString("124:59:31", true));
        Assert.assertEquals("00:-1:00", DateTimeUtility.timeToTimeString("00:-1:00", true));
        Assert.assertEquals("00:9:00", DateTimeUtility.timeToTimeString("00:9:00", true));
        Assert.assertEquals("23:78:44", DateTimeUtility.timeToTimeString("23:78:44", true));
        Assert.assertEquals("11:15:-1", DateTimeUtility.timeToTimeString("11:15:-1", true));
        Assert.assertEquals("23:15:5", DateTimeUtility.timeToTimeString("23:15:5", true));
        Assert.assertEquals("23:15:99", DateTimeUtility.timeToTimeString("23:15:99", true));
        
        //date
        Assert.assertEquals("11:15 AM", DateTimeUtility.timeToTimeString(new SimpleDateFormat("HH:mm").parse("11:15")));
        Assert.assertEquals("11:15 PM", DateTimeUtility.timeToTimeString(new SimpleDateFormat("HH:mm").parse("23:15")));
        Assert.assertEquals("12:59 AM", DateTimeUtility.timeToTimeString(new SimpleDateFormat("HH:mm").parse("00:59")));
        Assert.assertEquals("12:00 AM", DateTimeUtility.timeToTimeString(new SimpleDateFormat("HH:mm").parse("00:00")));
        Assert.assertEquals("11:59 PM", DateTimeUtility.timeToTimeString(new SimpleDateFormat("HH:mm").parse("23:59")));
        Assert.assertEquals("3:08 PM", DateTimeUtility.timeToTimeString(new SimpleDateFormat("HH:mm").parse("15:08")));
        Assert.assertEquals("1:01 PM", DateTimeUtility.timeToTimeString(new SimpleDateFormat("HH:mm").parse("13:01")));
        
        //date, include seconds
        Assert.assertEquals("11:15:45 AM", DateTimeUtility.timeToTimeString(new SimpleDateFormat("HH:mm:ss").parse("11:15:45"), true));
        Assert.assertEquals("11:15:01 PM", DateTimeUtility.timeToTimeString(new SimpleDateFormat("HH:mm:ss").parse("23:15:01"), true));
        Assert.assertEquals("12:59:00 AM", DateTimeUtility.timeToTimeString(new SimpleDateFormat("HH:mm:ss").parse("00:59:00"), true));
        Assert.assertEquals("12:00:00 AM", DateTimeUtility.timeToTimeString(new SimpleDateFormat("HH:mm:ss").parse("00:00:00"), true));
        Assert.assertEquals("11:59:00 PM", DateTimeUtility.timeToTimeString(new SimpleDateFormat("HH:mm:ss").parse("23:59:00"), true));
        Assert.assertEquals("3:08:59 PM", DateTimeUtility.timeToTimeString(new SimpleDateFormat("HH:mm:ss").parse("15:08:59"), true));
        Assert.assertEquals("1:01:58 PM", DateTimeUtility.timeToTimeString(new SimpleDateFormat("HH:mm:ss").parse("13:01:58"), true));
        
        //invalid time string
        Assert.assertEquals("11:15 AM", DateTimeUtility.timeToTimeString("11:15 AM"));
        Assert.assertEquals("a time", DateTimeUtility.timeToTimeString("a time"));
    }
    
    /**
     * JUnit test of timeToMilitaryTimeString.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#timeToMilitaryTimeString(String, boolean)
     * @see DateTimeUtility#timeToMilitaryTimeString(Date, boolean)
     * @see DateTimeUtility#timeToMilitaryTimeString(String)
     * @see DateTimeUtility#timeToMilitaryTimeString(Date)
     */
    @Test
    public void testTimeToMilitaryTimeString() throws Exception {
        //valid
        Assert.assertEquals("11:15", DateTimeUtility.timeToMilitaryTimeString("11:15"));
        Assert.assertEquals("23:15", DateTimeUtility.timeToMilitaryTimeString("23:15"));
        Assert.assertEquals("00:59", DateTimeUtility.timeToMilitaryTimeString("00:59"));
        Assert.assertEquals("00:00", DateTimeUtility.timeToMilitaryTimeString("00:00"));
        Assert.assertEquals("23:59", DateTimeUtility.timeToMilitaryTimeString("23:59"));
        Assert.assertEquals("15:08", DateTimeUtility.timeToMilitaryTimeString("15:08"));
        Assert.assertEquals("13:01", DateTimeUtility.timeToMilitaryTimeString("13:01"));
        
        //seconds
        Assert.assertEquals("11:15", DateTimeUtility.timeToMilitaryTimeString("11:15:45"));
        Assert.assertEquals("23:15", DateTimeUtility.timeToMilitaryTimeString("23:15:01"));
        Assert.assertEquals("00:59", DateTimeUtility.timeToMilitaryTimeString("00:59:00"));
        Assert.assertEquals("00:00", DateTimeUtility.timeToMilitaryTimeString("00:00:00"));
        Assert.assertEquals("23:59", DateTimeUtility.timeToMilitaryTimeString("23:59:00"));
        Assert.assertEquals("15:08", DateTimeUtility.timeToMilitaryTimeString("15:08:59"));
        Assert.assertEquals("13:01", DateTimeUtility.timeToMilitaryTimeString("13:01:58"));
        
        //include seconds
        Assert.assertEquals("11:15:45", DateTimeUtility.timeToMilitaryTimeString("11:15:45", true));
        Assert.assertEquals("23:15:01", DateTimeUtility.timeToMilitaryTimeString("23:15:01", true));
        Assert.assertEquals("00:59:00", DateTimeUtility.timeToMilitaryTimeString("00:59:00", true));
        Assert.assertEquals("00:00:00", DateTimeUtility.timeToMilitaryTimeString("00:00:00", true));
        Assert.assertEquals("23:59:00", DateTimeUtility.timeToMilitaryTimeString("23:59:00", true));
        Assert.assertEquals("15:08:59", DateTimeUtility.timeToMilitaryTimeString("15:08:59", true));
        Assert.assertEquals("13:01:58", DateTimeUtility.timeToMilitaryTimeString("13:01:58", true));
        
        //include seconds, no seconds
        Assert.assertEquals("11:15:00", DateTimeUtility.timeToMilitaryTimeString("11:15", true));
        Assert.assertEquals("23:15:00", DateTimeUtility.timeToMilitaryTimeString("23:15", true));
        Assert.assertEquals("00:59:00", DateTimeUtility.timeToMilitaryTimeString("00:59", true));
        Assert.assertEquals("00:00:00", DateTimeUtility.timeToMilitaryTimeString("00:00", true));
        Assert.assertEquals("23:59:00", DateTimeUtility.timeToMilitaryTimeString("23:59", true));
        Assert.assertEquals("15:08:00", DateTimeUtility.timeToMilitaryTimeString("15:08", true));
        Assert.assertEquals("13:01:00", DateTimeUtility.timeToMilitaryTimeString("13:01", true));
        
        //invalid ranges
        Assert.assertEquals("-1:15", DateTimeUtility.timeToMilitaryTimeString("-1:15"));
        Assert.assertEquals("1:15", DateTimeUtility.timeToMilitaryTimeString("1:15"));
        Assert.assertEquals("24:15", DateTimeUtility.timeToMilitaryTimeString("24:15"));
        Assert.assertEquals("124:59", DateTimeUtility.timeToMilitaryTimeString("124:59"));
        Assert.assertEquals("00:-1", DateTimeUtility.timeToMilitaryTimeString("00:-1"));
        Assert.assertEquals("00:9", DateTimeUtility.timeToMilitaryTimeString("00:9"));
        Assert.assertEquals("23:78", DateTimeUtility.timeToMilitaryTimeString("23:78"));
        Assert.assertEquals("-1:15:01", DateTimeUtility.timeToMilitaryTimeString("-1:15:01"));
        Assert.assertEquals("1:15:45", DateTimeUtility.timeToMilitaryTimeString("1:15:45"));
        Assert.assertEquals("24:15:45", DateTimeUtility.timeToMilitaryTimeString("24:15:45"));
        Assert.assertEquals("124:59:31", DateTimeUtility.timeToMilitaryTimeString("124:59:31"));
        Assert.assertEquals("00:-1:00", DateTimeUtility.timeToMilitaryTimeString("00:-1:00"));
        Assert.assertEquals("00:9:00", DateTimeUtility.timeToMilitaryTimeString("00:9:00"));
        Assert.assertEquals("23:78:44", DateTimeUtility.timeToMilitaryTimeString("23:78:44"));
        Assert.assertEquals("11:15:-1", DateTimeUtility.timeToMilitaryTimeString("11:15:-1"));
        Assert.assertEquals("23:15:99", DateTimeUtility.timeToMilitaryTimeString("23:15:99"));
        
        //invalid ranges, include seconds
        Assert.assertEquals("-1:15", DateTimeUtility.timeToMilitaryTimeString("-1:15", true));
        Assert.assertEquals("1:15", DateTimeUtility.timeToMilitaryTimeString("1:15", true));
        Assert.assertEquals("24:15", DateTimeUtility.timeToMilitaryTimeString("24:15", true));
        Assert.assertEquals("124:59", DateTimeUtility.timeToMilitaryTimeString("124:59", true));
        Assert.assertEquals("00:-1", DateTimeUtility.timeToMilitaryTimeString("00:-1", true));
        Assert.assertEquals("00:9", DateTimeUtility.timeToMilitaryTimeString("00:9", true));
        Assert.assertEquals("23:78", DateTimeUtility.timeToMilitaryTimeString("23:78", true));
        Assert.assertEquals("-1:15:01", DateTimeUtility.timeToMilitaryTimeString("-1:15:01", true));
        Assert.assertEquals("1:15:45", DateTimeUtility.timeToMilitaryTimeString("1:15:45", true));
        Assert.assertEquals("24:15:45", DateTimeUtility.timeToMilitaryTimeString("24:15:45", true));
        Assert.assertEquals("124:59:31", DateTimeUtility.timeToMilitaryTimeString("124:59:31", true));
        Assert.assertEquals("00:-1:00", DateTimeUtility.timeToMilitaryTimeString("00:-1:00", true));
        Assert.assertEquals("00:9:00", DateTimeUtility.timeToMilitaryTimeString("00:9:00", true));
        Assert.assertEquals("23:78:44", DateTimeUtility.timeToMilitaryTimeString("23:78:44", true));
        Assert.assertEquals("11:15:-1", DateTimeUtility.timeToMilitaryTimeString("11:15:-1", true));
        Assert.assertEquals("23:15:5", DateTimeUtility.timeToMilitaryTimeString("23:15:5", true));
        Assert.assertEquals("23:15:99", DateTimeUtility.timeToMilitaryTimeString("23:15:99", true));
        
        //date
        Assert.assertEquals("11:15", DateTimeUtility.timeToMilitaryTimeString(new SimpleDateFormat("HH:mm").parse("11:15")));
        Assert.assertEquals("23:15", DateTimeUtility.timeToMilitaryTimeString(new SimpleDateFormat("HH:mm").parse("23:15")));
        Assert.assertEquals("00:59", DateTimeUtility.timeToMilitaryTimeString(new SimpleDateFormat("HH:mm").parse("00:59")));
        Assert.assertEquals("00:00", DateTimeUtility.timeToMilitaryTimeString(new SimpleDateFormat("HH:mm").parse("00:00")));
        Assert.assertEquals("23:59", DateTimeUtility.timeToMilitaryTimeString(new SimpleDateFormat("HH:mm").parse("23:59")));
        Assert.assertEquals("15:08", DateTimeUtility.timeToMilitaryTimeString(new SimpleDateFormat("HH:mm").parse("15:08")));
        Assert.assertEquals("13:01", DateTimeUtility.timeToMilitaryTimeString(new SimpleDateFormat("HH:mm").parse("13:01")));
        
        //date, include seconds
        Assert.assertEquals("11:15:45", DateTimeUtility.timeToMilitaryTimeString(new SimpleDateFormat("HH:mm:ss").parse("11:15:45"), true));
        Assert.assertEquals("23:15:01", DateTimeUtility.timeToMilitaryTimeString(new SimpleDateFormat("HH:mm:ss").parse("23:15:01"), true));
        Assert.assertEquals("00:59:00", DateTimeUtility.timeToMilitaryTimeString(new SimpleDateFormat("HH:mm:ss").parse("00:59:00"), true));
        Assert.assertEquals("00:00:00", DateTimeUtility.timeToMilitaryTimeString(new SimpleDateFormat("HH:mm:ss").parse("00:00:00"), true));
        Assert.assertEquals("23:59:00", DateTimeUtility.timeToMilitaryTimeString(new SimpleDateFormat("HH:mm:ss").parse("23:59:00"), true));
        Assert.assertEquals("15:08:59", DateTimeUtility.timeToMilitaryTimeString(new SimpleDateFormat("HH:mm:ss").parse("15:08:59"), true));
        Assert.assertEquals("13:01:58", DateTimeUtility.timeToMilitaryTimeString(new SimpleDateFormat("HH:mm:ss").parse("13:01:58"), true));
        
        //invalid time string
        Assert.assertEquals("11:15 AM", DateTimeUtility.timeToMilitaryTimeString("11:15 AM"));
        Assert.assertEquals("a time", DateTimeUtility.timeToMilitaryTimeString("a time"));
    }
    
    /**
     * JUnit test of timeToMilitaryHoursString.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#timeToMilitaryHoursString(String)
     * @see DateTimeUtility#timeToMilitaryHoursString(Date)
     */
    @Test
    public void testTimeToMilitaryHoursString() throws Exception {
        //valid
        Assert.assertEquals("1115 hours", DateTimeUtility.timeToMilitaryHoursString("11:15"));
        Assert.assertEquals("2315 hours", DateTimeUtility.timeToMilitaryHoursString("23:15"));
        Assert.assertEquals("0059 hours", DateTimeUtility.timeToMilitaryHoursString("00:59"));
        Assert.assertEquals("0000 hours", DateTimeUtility.timeToMilitaryHoursString("00:00"));
        Assert.assertEquals("2359 hours", DateTimeUtility.timeToMilitaryHoursString("23:59"));
        Assert.assertEquals("1508 hours", DateTimeUtility.timeToMilitaryHoursString("15:08"));
        Assert.assertEquals("1301 hours", DateTimeUtility.timeToMilitaryHoursString("13:01"));
        
        //seconds
        Assert.assertEquals("1115 hours", DateTimeUtility.timeToMilitaryHoursString("11:15:45"));
        Assert.assertEquals("2315 hours", DateTimeUtility.timeToMilitaryHoursString("23:15:01"));
        Assert.assertEquals("0059 hours", DateTimeUtility.timeToMilitaryHoursString("00:59:00"));
        Assert.assertEquals("0000 hours", DateTimeUtility.timeToMilitaryHoursString("00:00:00"));
        Assert.assertEquals("2359 hours", DateTimeUtility.timeToMilitaryHoursString("23:59:00"));
        Assert.assertEquals("1508 hours", DateTimeUtility.timeToMilitaryHoursString("15:08:59"));
        Assert.assertEquals("1301 hours", DateTimeUtility.timeToMilitaryHoursString("13:01:58"));
        
        //invalid ranges
        Assert.assertEquals("-1:15", DateTimeUtility.timeToMilitaryHoursString("-1:15"));
        Assert.assertEquals("1:15", DateTimeUtility.timeToMilitaryHoursString("1:15"));
        Assert.assertEquals("24:15", DateTimeUtility.timeToMilitaryHoursString("24:15"));
        Assert.assertEquals("124:59", DateTimeUtility.timeToMilitaryHoursString("124:59"));
        Assert.assertEquals("00:-1", DateTimeUtility.timeToMilitaryHoursString("00:-1"));
        Assert.assertEquals("00:9", DateTimeUtility.timeToMilitaryHoursString("00:9"));
        Assert.assertEquals("23:78", DateTimeUtility.timeToMilitaryHoursString("23:78"));
        Assert.assertEquals("-1:15:01", DateTimeUtility.timeToMilitaryHoursString("-1:15:01"));
        Assert.assertEquals("1:15:45", DateTimeUtility.timeToMilitaryHoursString("1:15:45"));
        Assert.assertEquals("24:15:45", DateTimeUtility.timeToMilitaryHoursString("24:15:45"));
        Assert.assertEquals("124:59:31", DateTimeUtility.timeToMilitaryHoursString("124:59:31"));
        Assert.assertEquals("00:-1:00", DateTimeUtility.timeToMilitaryHoursString("00:-1:00"));
        Assert.assertEquals("00:9:00", DateTimeUtility.timeToMilitaryHoursString("00:9:00"));
        Assert.assertEquals("23:78:44", DateTimeUtility.timeToMilitaryHoursString("23:78:44"));
        Assert.assertEquals("11:15:-1", DateTimeUtility.timeToMilitaryHoursString("11:15:-1"));
        Assert.assertEquals("11:15:5", DateTimeUtility.timeToMilitaryHoursString("11:15:5"));
        
        //invalid second range
        Assert.assertEquals("2315 hours", DateTimeUtility.timeToMilitaryHoursString("23:15:99"));
        
        //date
        Assert.assertEquals("1115 hours", DateTimeUtility.timeToMilitaryHoursString(new SimpleDateFormat("HH:mm").parse("11:15")));
        Assert.assertEquals("2315 hours", DateTimeUtility.timeToMilitaryHoursString(new SimpleDateFormat("HH:mm").parse("23:15")));
        Assert.assertEquals("0059 hours", DateTimeUtility.timeToMilitaryHoursString(new SimpleDateFormat("HH:mm").parse("00:59")));
        Assert.assertEquals("0000 hours", DateTimeUtility.timeToMilitaryHoursString(new SimpleDateFormat("HH:mm").parse("00:00")));
        Assert.assertEquals("2359 hours", DateTimeUtility.timeToMilitaryHoursString(new SimpleDateFormat("HH:mm").parse("23:59")));
        Assert.assertEquals("1508 hours", DateTimeUtility.timeToMilitaryHoursString(new SimpleDateFormat("HH:mm").parse("15:08")));
        Assert.assertEquals("1301 hours", DateTimeUtility.timeToMilitaryHoursString(new SimpleDateFormat("HH:mm").parse("13:01")));
        
        //invalid time string
        Assert.assertEquals("11:15 AM", DateTimeUtility.timeToMilitaryHoursString("11:15 AM"));
        Assert.assertEquals("a time", DateTimeUtility.timeToMilitaryHoursString("a time"));
    }
    
    /**
     * JUnit test of dayOfWeek.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#dayOfWeek(int, int, int)
     * @see DateTimeUtility#dayOfWeek(String)
     * @see DateTimeUtility#dayOfWeek(Date)
     */
    @Test
    public void testDayOfWeek() throws Exception {
        //valid date string
        Assert.assertEquals(DateTimeUtility.Weekday.MONDAY, DateTimeUtility.dayOfWeek("1645-05-01"));
        Assert.assertEquals(DateTimeUtility.Weekday.TUESDAY, DateTimeUtility.dayOfWeek("1734-02-16"));
        Assert.assertEquals(DateTimeUtility.Weekday.WEDNESDAY, DateTimeUtility.dayOfWeek("2025-01-01"));
        Assert.assertEquals(DateTimeUtility.Weekday.THURSDAY, DateTimeUtility.dayOfWeek("2017-03-16"));
        Assert.assertEquals(DateTimeUtility.Weekday.SATURDAY, DateTimeUtility.dayOfWeek("1967-12-23"));
        Assert.assertEquals(DateTimeUtility.Weekday.SUNDAY, DateTimeUtility.dayOfWeek("1759-07-08"));
        
        //leap year
        Assert.assertEquals(DateTimeUtility.Weekday.MONDAY, DateTimeUtility.dayOfWeek("1600-07-31"));
        Assert.assertEquals(DateTimeUtility.Weekday.SUNDAY, DateTimeUtility.dayOfWeek("1944-12-31"));
        Assert.assertEquals(DateTimeUtility.Weekday.TUESDAY, DateTimeUtility.dayOfWeek("2000-02-29"));
        Assert.assertEquals(DateTimeUtility.Weekday.SUNDAY, DateTimeUtility.dayOfWeek("2488-03-14"));
        
        //year edge case
        Assert.assertEquals(DateTimeUtility.Weekday.SATURDAY, DateTimeUtility.dayOfWeek("10546-01-01"));
        Assert.assertEquals(DateTimeUtility.Weekday.MONDAY, DateTimeUtility.dayOfWeek("1-01-01"));
        
        //invalid day in month
        Assert.assertNull(DateTimeUtility.dayOfWeek("2000-02-30"));
        Assert.assertNull(DateTimeUtility.dayOfWeek("2001-02-29"));
        Assert.assertNull(DateTimeUtility.dayOfWeek("1940-09-31"));
        
        //invalid ranges
        Assert.assertNull(DateTimeUtility.dayOfWeek("0-01-01"));
        Assert.assertNull(DateTimeUtility.dayOfWeek("-154-01-01"));
        Assert.assertNull(DateTimeUtility.dayOfWeek("2000-00-01"));
        Assert.assertNull(DateTimeUtility.dayOfWeek("2000--11-01"));
        Assert.assertNull(DateTimeUtility.dayOfWeek("2000-34-01"));
        Assert.assertNull(DateTimeUtility.dayOfWeek("2000-01-00"));
        Assert.assertNull(DateTimeUtility.dayOfWeek("2000-01--11"));
        Assert.assertNull(DateTimeUtility.dayOfWeek("2000-01-57"));
        
        //date
        Assert.assertEquals(DateTimeUtility.Weekday.MONDAY, DateTimeUtility.dayOfWeek(new SimpleDateFormat("yyyy-MM-dd").parse("1645-05-01")));
        Assert.assertEquals(DateTimeUtility.Weekday.TUESDAY, DateTimeUtility.dayOfWeek(new SimpleDateFormat("yyyy-MM-dd").parse("1734-02-16")));
        Assert.assertEquals(DateTimeUtility.Weekday.WEDNESDAY, DateTimeUtility.dayOfWeek(new SimpleDateFormat("yyyy-MM-dd").parse("2025-01-01")));
        Assert.assertEquals(DateTimeUtility.Weekday.THURSDAY, DateTimeUtility.dayOfWeek(new SimpleDateFormat("yyyy-MM-dd").parse("2017-03-16")));
        Assert.assertEquals(DateTimeUtility.Weekday.SATURDAY, DateTimeUtility.dayOfWeek(new SimpleDateFormat("yyyy-MM-dd").parse("1967-12-23")));
        Assert.assertEquals(DateTimeUtility.Weekday.SUNDAY, DateTimeUtility.dayOfWeek(new SimpleDateFormat("yyyy-MM-dd").parse("1759-07-08")));
        
        //invalid date string
        Assert.assertNull(DateTimeUtility.dayOfWeek("March 16, 2017"));
        Assert.assertNull(DateTimeUtility.dayOfWeek("a date"));
        
        //valid date
        Assert.assertEquals(DateTimeUtility.Weekday.MONDAY, DateTimeUtility.dayOfWeek(5, 1, 1645));
        Assert.assertEquals(DateTimeUtility.Weekday.TUESDAY, DateTimeUtility.dayOfWeek(2, 16, 1734));
        Assert.assertEquals(DateTimeUtility.Weekday.WEDNESDAY, DateTimeUtility.dayOfWeek(1, 1, 2025));
        Assert.assertEquals(DateTimeUtility.Weekday.THURSDAY, DateTimeUtility.dayOfWeek(3, 16, 2017));
        Assert.assertEquals(DateTimeUtility.Weekday.SATURDAY, DateTimeUtility.dayOfWeek(12, 23, 1967));
        Assert.assertEquals(DateTimeUtility.Weekday.SUNDAY, DateTimeUtility.dayOfWeek(7, 8, 1759));
        
        //leap year
        Assert.assertEquals(DateTimeUtility.Weekday.MONDAY, DateTimeUtility.dayOfWeek(7, 31, 1600));
        Assert.assertEquals(DateTimeUtility.Weekday.SUNDAY, DateTimeUtility.dayOfWeek(12, 31, 1944));
        Assert.assertEquals(DateTimeUtility.Weekday.TUESDAY, DateTimeUtility.dayOfWeek(2, 29, 2000));
        Assert.assertEquals(DateTimeUtility.Weekday.SUNDAY, DateTimeUtility.dayOfWeek(3, 14, 2488));
        
        //year edge case
        Assert.assertEquals(DateTimeUtility.Weekday.SATURDAY, DateTimeUtility.dayOfWeek(1, 1, 10546));
        Assert.assertEquals(DateTimeUtility.Weekday.MONDAY, DateTimeUtility.dayOfWeek(1, 1, 1));
        
        //invalid day in month
        Assert.assertNull(DateTimeUtility.dayOfWeek(2, 30, 2000));
        Assert.assertNull(DateTimeUtility.dayOfWeek(2, 29, 2001));
        Assert.assertNull(DateTimeUtility.dayOfWeek(9, 31, 1940));
        
        //invalid ranges
        Assert.assertNull(DateTimeUtility.dayOfWeek(1, 1, 0));
        Assert.assertNull(DateTimeUtility.dayOfWeek(1, 1, -154));
        Assert.assertNull(DateTimeUtility.dayOfWeek(0, 1, 2000));
        Assert.assertNull(DateTimeUtility.dayOfWeek(-11, 1, 2000));
        Assert.assertNull(DateTimeUtility.dayOfWeek(34, 1, 2000));
        Assert.assertNull(DateTimeUtility.dayOfWeek(1, 0, 2000));
        Assert.assertNull(DateTimeUtility.dayOfWeek(1, -11, 2000));
        Assert.assertNull(DateTimeUtility.dayOfWeek(1, 57, 2000));
    }
    
    /**
     * JUnit test of isLeapYear.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#isLeapYear(int)
     */
    @Test
    public void testIsLeapYear() throws Exception {
        //leap years
        Assert.assertTrue(DateTimeUtility.isLeapYear(1600));
        Assert.assertTrue(DateTimeUtility.isLeapYear(1644));
        Assert.assertTrue(DateTimeUtility.isLeapYear(1788));
        Assert.assertTrue(DateTimeUtility.isLeapYear(2000));
        Assert.assertTrue(DateTimeUtility.isLeapYear(2192));
        
        //not leap years
        Assert.assertFalse(DateTimeUtility.isLeapYear(1700));
        Assert.assertFalse(DateTimeUtility.isLeapYear(1787));
        Assert.assertFalse(DateTimeUtility.isLeapYear(1999));
        Assert.assertFalse(DateTimeUtility.isLeapYear(2001));
        Assert.assertFalse(DateTimeUtility.isLeapYear(2193));
        
        //year edge case
        Assert.assertFalse(DateTimeUtility.isLeapYear(1));
        Assert.assertTrue(DateTimeUtility.isLeapYear(4));
        Assert.assertTrue(DateTimeUtility.isLeapYear(200000));
        Assert.assertFalse(DateTimeUtility.isLeapYear(Integer.MAX_VALUE - 1));
        
        //invalid ranges
        Assert.assertFalse(DateTimeUtility.isLeapYear(0));
        Assert.assertFalse(DateTimeUtility.isLeapYear(-1));
        Assert.assertFalse(DateTimeUtility.isLeapYear(Integer.MAX_VALUE));
    }
    
    /**
     * JUnit test of durationToDurationString.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#durationToDurationString(long, boolean, boolean, boolean)
     * @see DateTimeUtility#durationToDurationString(long, boolean, boolean)
     * @see DateTimeUtility#durationToDurationString(long, boolean)
     * @see DateTimeUtility#durationToDurationString(long)
     */
    @Test
    public void testDurationToDurationString() throws Exception {
        //standard
        Assert.assertEquals("12 Hours 16 Minutes 3 Seconds 191 Milliseconds", DateTimeUtility.durationToDurationString(44163191L));
        Assert.assertEquals("11 Days 11 Hours 25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(991542231L));
        Assert.assertEquals("25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(1542231L));
        Assert.assertEquals("25 Minutes 42 Seconds 200 Milliseconds", DateTimeUtility.durationToDurationString(1542200L));
        Assert.assertEquals("9 Minutes 2 Seconds 877 Milliseconds", DateTimeUtility.durationToDurationString(542877L));
        Assert.assertEquals("1 Millisecond", DateTimeUtility.durationToDurationString(1L));
        Assert.assertEquals("10 Milliseconds", DateTimeUtility.durationToDurationString(10L));
        Assert.assertEquals("100 Milliseconds", DateTimeUtility.durationToDurationString(100L));
        Assert.assertEquals("999 Milliseconds", DateTimeUtility.durationToDurationString(999L));
        Assert.assertEquals("1 Second", DateTimeUtility.durationToDurationString(1000L));
        Assert.assertEquals("10 Seconds", DateTimeUtility.durationToDurationString(10000L));
        Assert.assertEquals("59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(59999L));
        Assert.assertEquals("1 Minute", DateTimeUtility.durationToDurationString(60000L));
        Assert.assertEquals("59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(3599999L));
        Assert.assertEquals("1 Hour", DateTimeUtility.durationToDurationString(3600000L));
        Assert.assertEquals("23 Hours 59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(86399999L));
        Assert.assertEquals("1 Day", DateTimeUtility.durationToDurationString(86400000L));
        
        //negative
        Assert.assertEquals("Negative 12 Hours 16 Minutes 3 Seconds 191 Milliseconds", DateTimeUtility.durationToDurationString(-44163191L));
        Assert.assertEquals("Negative 11 Days 11 Hours 25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(-991542231L));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(-1542231L));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds 200 Milliseconds", DateTimeUtility.durationToDurationString(-1542200L));
        Assert.assertEquals("Negative 9 Minutes 2 Seconds 877 Milliseconds", DateTimeUtility.durationToDurationString(-542877L));
        Assert.assertEquals("Negative 1 Millisecond", DateTimeUtility.durationToDurationString(-1L));
        Assert.assertEquals("Negative 10 Milliseconds", DateTimeUtility.durationToDurationString(-10L));
        Assert.assertEquals("Negative 100 Milliseconds", DateTimeUtility.durationToDurationString(-100L));
        Assert.assertEquals("Negative 999 Milliseconds", DateTimeUtility.durationToDurationString(-999L));
        Assert.assertEquals("Negative 1 Second", DateTimeUtility.durationToDurationString(-1000L));
        Assert.assertEquals("Negative 10 Seconds", DateTimeUtility.durationToDurationString(-10000L));
        Assert.assertEquals("Negative 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(-59999L));
        Assert.assertEquals("Negative 1 Minute", DateTimeUtility.durationToDurationString(-60000L));
        Assert.assertEquals("Negative 59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(-3599999L));
        Assert.assertEquals("Negative 1 Hour", DateTimeUtility.durationToDurationString(-3600000L));
        Assert.assertEquals("Negative 23 Hours 59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(-86399999L));
        Assert.assertEquals("Negative 1 Day", DateTimeUtility.durationToDurationString(-86400000L));
        
        //empty
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(0L));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-0L));
        
        //abbreviation on
        Assert.assertEquals("12 Hours 16 Minutes 3 Seconds 191 Milliseconds", DateTimeUtility.durationToDurationString(44163191L, true));
        Assert.assertEquals("11 Days 11 Hours 25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(991542231L, true));
        Assert.assertEquals("25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(1542231L, true));
        Assert.assertEquals("25 Minutes 42 Seconds 200 Milliseconds", DateTimeUtility.durationToDurationString(1542200L, true));
        Assert.assertEquals("9 Minutes 2 Seconds 877 Milliseconds", DateTimeUtility.durationToDurationString(542877L, true));
        Assert.assertEquals("1 Millisecond", DateTimeUtility.durationToDurationString(1L, true));
        Assert.assertEquals("10 Milliseconds", DateTimeUtility.durationToDurationString(10L, true));
        Assert.assertEquals("100 Milliseconds", DateTimeUtility.durationToDurationString(100L, true));
        Assert.assertEquals("999 Milliseconds", DateTimeUtility.durationToDurationString(999L, true));
        Assert.assertEquals("1 Second", DateTimeUtility.durationToDurationString(1000L, true));
        Assert.assertEquals("10 Seconds", DateTimeUtility.durationToDurationString(10000L, true));
        Assert.assertEquals("59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(59999L, true));
        Assert.assertEquals("1 Minute", DateTimeUtility.durationToDurationString(60000L, true));
        Assert.assertEquals("59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(3599999L, true));
        Assert.assertEquals("1 Hour", DateTimeUtility.durationToDurationString(3600000L, true));
        Assert.assertEquals("23 Hours 59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(86399999L, true));
        Assert.assertEquals("1 Day", DateTimeUtility.durationToDurationString(86400000L, true));
        Assert.assertEquals("Negative 12 Hours 16 Minutes 3 Seconds 191 Milliseconds", DateTimeUtility.durationToDurationString(-44163191L, true));
        Assert.assertEquals("Negative 11 Days 11 Hours 25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(-991542231L, true));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(-1542231L, true));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds 200 Milliseconds", DateTimeUtility.durationToDurationString(-1542200L, true));
        Assert.assertEquals("Negative 9 Minutes 2 Seconds 877 Milliseconds", DateTimeUtility.durationToDurationString(-542877L, true));
        Assert.assertEquals("Negative 1 Millisecond", DateTimeUtility.durationToDurationString(-1L, true));
        Assert.assertEquals("Negative 10 Milliseconds", DateTimeUtility.durationToDurationString(-10L, true));
        Assert.assertEquals("Negative 100 Milliseconds", DateTimeUtility.durationToDurationString(-100L, true));
        Assert.assertEquals("Negative 999 Milliseconds", DateTimeUtility.durationToDurationString(-999L, true));
        Assert.assertEquals("Negative 1 Second", DateTimeUtility.durationToDurationString(-1000L, true));
        Assert.assertEquals("Negative 10 Seconds", DateTimeUtility.durationToDurationString(-10000L, true));
        Assert.assertEquals("Negative 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(-59999L, true));
        Assert.assertEquals("Negative 1 Minute", DateTimeUtility.durationToDurationString(-60000L, true));
        Assert.assertEquals("Negative 59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(-3599999L, true));
        Assert.assertEquals("Negative 1 Hour", DateTimeUtility.durationToDurationString(-3600000L, true));
        Assert.assertEquals("Negative 23 Hours 59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(-86399999L, true));
        Assert.assertEquals("Negative 1 Day", DateTimeUtility.durationToDurationString(-86400000L, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(0L, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-0L, true));
        
        //abbreviation off
        Assert.assertEquals("12 Hours 16 Minutes 3 Seconds 191 Milliseconds", DateTimeUtility.durationToDurationString(44163191L, false));
        Assert.assertEquals("11 Days 11 Hours 25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(991542231L, false));
        Assert.assertEquals("25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(1542231L, false));
        Assert.assertEquals("25 Minutes 42 Seconds 200 Milliseconds", DateTimeUtility.durationToDurationString(1542200L, false));
        Assert.assertEquals("9 Minutes 2 Seconds 877 Milliseconds", DateTimeUtility.durationToDurationString(542877L, false));
        Assert.assertEquals("1 Millisecond", DateTimeUtility.durationToDurationString(1L, false));
        Assert.assertEquals("10 Milliseconds", DateTimeUtility.durationToDurationString(10L, false));
        Assert.assertEquals("100 Milliseconds", DateTimeUtility.durationToDurationString(100L, false));
        Assert.assertEquals("999 Milliseconds", DateTimeUtility.durationToDurationString(999L, false));
        Assert.assertEquals("1 Second 0 Milliseconds", DateTimeUtility.durationToDurationString(1000L, false));
        Assert.assertEquals("10 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(10000L, false));
        Assert.assertEquals("59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(59999L, false));
        Assert.assertEquals("1 Minute 0 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(60000L, false));
        Assert.assertEquals("59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(3599999L, false));
        Assert.assertEquals("1 Hour 0 Minutes 0 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(3600000L, false));
        Assert.assertEquals("23 Hours 59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(86399999L, false));
        Assert.assertEquals("1 Day 0 Hours 0 Minutes 0 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(86400000L, false));
        Assert.assertEquals("Negative 12 Hours 16 Minutes 3 Seconds 191 Milliseconds", DateTimeUtility.durationToDurationString(-44163191L, false));
        Assert.assertEquals("Negative 11 Days 11 Hours 25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(-991542231L, false));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(-1542231L, false));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds 200 Milliseconds", DateTimeUtility.durationToDurationString(-1542200L, false));
        Assert.assertEquals("Negative 9 Minutes 2 Seconds 877 Milliseconds", DateTimeUtility.durationToDurationString(-542877L, false));
        Assert.assertEquals("Negative 1 Millisecond", DateTimeUtility.durationToDurationString(-1L, false));
        Assert.assertEquals("Negative 10 Milliseconds", DateTimeUtility.durationToDurationString(-10L, false));
        Assert.assertEquals("Negative 100 Milliseconds", DateTimeUtility.durationToDurationString(-100L, false));
        Assert.assertEquals("Negative 999 Milliseconds", DateTimeUtility.durationToDurationString(-999L, false));
        Assert.assertEquals("Negative 1 Second 0 Milliseconds", DateTimeUtility.durationToDurationString(-1000L, false));
        Assert.assertEquals("Negative 10 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(-10000L, false));
        Assert.assertEquals("Negative 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(-59999L, false));
        Assert.assertEquals("Negative 1 Minute 0 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(-60000L, false));
        Assert.assertEquals("Negative 59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(-3599999L, false));
        Assert.assertEquals("Negative 1 Hour 0 Minutes 0 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(-3600000L, false));
        Assert.assertEquals("Negative 23 Hours 59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(-86399999L, false));
        Assert.assertEquals("Negative 1 Day 0 Hours 0 Minutes 0 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(-86400000L, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(0L, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-0L, false));
        
        //abbreviation on, show milliseconds on
        Assert.assertEquals("12 Hours 16 Minutes 3 Seconds 191 Milliseconds", DateTimeUtility.durationToDurationString(44163191L, true, true));
        Assert.assertEquals("11 Days 11 Hours 25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(991542231L, true, true));
        Assert.assertEquals("25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(1542231L, true, true));
        Assert.assertEquals("25 Minutes 42 Seconds 200 Milliseconds", DateTimeUtility.durationToDurationString(1542200L, true, true));
        Assert.assertEquals("9 Minutes 2 Seconds 877 Milliseconds", DateTimeUtility.durationToDurationString(542877L, true, true));
        Assert.assertEquals("1 Millisecond", DateTimeUtility.durationToDurationString(1L, true, true));
        Assert.assertEquals("10 Milliseconds", DateTimeUtility.durationToDurationString(10L, true, true));
        Assert.assertEquals("100 Milliseconds", DateTimeUtility.durationToDurationString(100L, true, true));
        Assert.assertEquals("999 Milliseconds", DateTimeUtility.durationToDurationString(999L, true, true));
        Assert.assertEquals("1 Second", DateTimeUtility.durationToDurationString(1000L, true, true));
        Assert.assertEquals("10 Seconds", DateTimeUtility.durationToDurationString(10000L, true, true));
        Assert.assertEquals("59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(59999L, true, true));
        Assert.assertEquals("1 Minute", DateTimeUtility.durationToDurationString(60000L, true, true));
        Assert.assertEquals("59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(3599999L, true, true));
        Assert.assertEquals("1 Hour", DateTimeUtility.durationToDurationString(3600000L, true, true));
        Assert.assertEquals("23 Hours 59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(86399999L, true, true));
        Assert.assertEquals("1 Day", DateTimeUtility.durationToDurationString(86400000L, true, true));
        Assert.assertEquals("Negative 12 Hours 16 Minutes 3 Seconds 191 Milliseconds", DateTimeUtility.durationToDurationString(-44163191L, true, true));
        Assert.assertEquals("Negative 11 Days 11 Hours 25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(-991542231L, true, true));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(-1542231L, true, true));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds 200 Milliseconds", DateTimeUtility.durationToDurationString(-1542200L, true, true));
        Assert.assertEquals("Negative 9 Minutes 2 Seconds 877 Milliseconds", DateTimeUtility.durationToDurationString(-542877L, true, true));
        Assert.assertEquals("Negative 1 Millisecond", DateTimeUtility.durationToDurationString(-1L, true, true));
        Assert.assertEquals("Negative 10 Milliseconds", DateTimeUtility.durationToDurationString(-10L, true, true));
        Assert.assertEquals("Negative 100 Milliseconds", DateTimeUtility.durationToDurationString(-100L, true, true));
        Assert.assertEquals("Negative 999 Milliseconds", DateTimeUtility.durationToDurationString(-999L, true, true));
        Assert.assertEquals("Negative 1 Second", DateTimeUtility.durationToDurationString(-1000L, true, true));
        Assert.assertEquals("Negative 10 Seconds", DateTimeUtility.durationToDurationString(-10000L, true, true));
        Assert.assertEquals("Negative 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(-59999L, true, true));
        Assert.assertEquals("Negative 1 Minute", DateTimeUtility.durationToDurationString(-60000L, true, true));
        Assert.assertEquals("Negative 59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(-3599999L, true, true));
        Assert.assertEquals("Negative 1 Hour", DateTimeUtility.durationToDurationString(-3600000L, true, true));
        Assert.assertEquals("Negative 23 Hours 59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(-86399999L, true, true));
        Assert.assertEquals("Negative 1 Day", DateTimeUtility.durationToDurationString(-86400000L, true, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(0L, true, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-0L, true, true));
        
        //abbreviation on, show milliseconds off
        Assert.assertEquals("12 Hours 16 Minutes 3 Seconds", DateTimeUtility.durationToDurationString(44163191L, true, false));
        Assert.assertEquals("11 Days 11 Hours 25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(991542231L, true, false));
        Assert.assertEquals("25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(1542231L, true, false));
        Assert.assertEquals("25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(1542200L, true, false));
        Assert.assertEquals("9 Minutes 2 Seconds", DateTimeUtility.durationToDurationString(542877L, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(1L, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(10L, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(100L, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(999L, true, false));
        Assert.assertEquals("1 Second", DateTimeUtility.durationToDurationString(1000L, true, false));
        Assert.assertEquals("10 Seconds", DateTimeUtility.durationToDurationString(10000L, true, false));
        Assert.assertEquals("59 Seconds", DateTimeUtility.durationToDurationString(59999L, true, false));
        Assert.assertEquals("1 Minute", DateTimeUtility.durationToDurationString(60000L, true, false));
        Assert.assertEquals("59 Minutes 59 Seconds", DateTimeUtility.durationToDurationString(3599999L, true, false));
        Assert.assertEquals("1 Hour", DateTimeUtility.durationToDurationString(3600000L, true, false));
        Assert.assertEquals("23 Hours 59 Minutes 59 Seconds", DateTimeUtility.durationToDurationString(86399999L, true, false));
        Assert.assertEquals("1 Day", DateTimeUtility.durationToDurationString(86400000L, true, false));
        Assert.assertEquals("Negative 12 Hours 16 Minutes 3 Seconds", DateTimeUtility.durationToDurationString(-44163191L, true, false));
        Assert.assertEquals("Negative 11 Days 11 Hours 25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(-991542231L, true, false));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(-1542231L, true, false));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(-1542200L, true, false));
        Assert.assertEquals("Negative 9 Minutes 2 Seconds", DateTimeUtility.durationToDurationString(-542877L, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-1L, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-10L, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-100L, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-999L, true, false));
        Assert.assertEquals("Negative 1 Second", DateTimeUtility.durationToDurationString(-1000L, true, false));
        Assert.assertEquals("Negative 10 Seconds", DateTimeUtility.durationToDurationString(-10000L, true, false));
        Assert.assertEquals("Negative 59 Seconds", DateTimeUtility.durationToDurationString(-59999L, true, false));
        Assert.assertEquals("Negative 1 Minute", DateTimeUtility.durationToDurationString(-60000L, true, false));
        Assert.assertEquals("Negative 59 Minutes 59 Seconds", DateTimeUtility.durationToDurationString(-3599999L, true, false));
        Assert.assertEquals("Negative 1 Hour", DateTimeUtility.durationToDurationString(-3600000L, true, false));
        Assert.assertEquals("Negative 23 Hours 59 Minutes 59 Seconds", DateTimeUtility.durationToDurationString(-86399999L, true, false));
        Assert.assertEquals("Negative 1 Day", DateTimeUtility.durationToDurationString(-86400000L, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(0L, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-0L, true, false));
        
        //abbreviation off, show milliseconds on
        Assert.assertEquals("12 Hours 16 Minutes 3 Seconds 191 Milliseconds", DateTimeUtility.durationToDurationString(44163191L, false, true));
        Assert.assertEquals("11 Days 11 Hours 25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(991542231L, false, true));
        Assert.assertEquals("25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(1542231L, false, true));
        Assert.assertEquals("25 Minutes 42 Seconds 200 Milliseconds", DateTimeUtility.durationToDurationString(1542200L, false, true));
        Assert.assertEquals("9 Minutes 2 Seconds 877 Milliseconds", DateTimeUtility.durationToDurationString(542877L, false, true));
        Assert.assertEquals("1 Millisecond", DateTimeUtility.durationToDurationString(1L, false, true));
        Assert.assertEquals("10 Milliseconds", DateTimeUtility.durationToDurationString(10L, false, true));
        Assert.assertEquals("100 Milliseconds", DateTimeUtility.durationToDurationString(100L, false, true));
        Assert.assertEquals("999 Milliseconds", DateTimeUtility.durationToDurationString(999L, false, true));
        Assert.assertEquals("1 Second 0 Milliseconds", DateTimeUtility.durationToDurationString(1000L, false, true));
        Assert.assertEquals("10 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(10000L, false, true));
        Assert.assertEquals("59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(59999L, false, true));
        Assert.assertEquals("1 Minute 0 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(60000L, false, true));
        Assert.assertEquals("59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(3599999L, false, true));
        Assert.assertEquals("1 Hour 0 Minutes 0 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(3600000L, false, true));
        Assert.assertEquals("23 Hours 59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(86399999L, false, true));
        Assert.assertEquals("1 Day 0 Hours 0 Minutes 0 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(86400000L, false, true));
        Assert.assertEquals("Negative 12 Hours 16 Minutes 3 Seconds 191 Milliseconds", DateTimeUtility.durationToDurationString(-44163191L, false, true));
        Assert.assertEquals("Negative 11 Days 11 Hours 25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(-991542231L, false, true));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(-1542231L, false, true));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds 200 Milliseconds", DateTimeUtility.durationToDurationString(-1542200L, false, true));
        Assert.assertEquals("Negative 9 Minutes 2 Seconds 877 Milliseconds", DateTimeUtility.durationToDurationString(-542877L, false, true));
        Assert.assertEquals("Negative 1 Millisecond", DateTimeUtility.durationToDurationString(-1L, false, true));
        Assert.assertEquals("Negative 10 Milliseconds", DateTimeUtility.durationToDurationString(-10L, false, true));
        Assert.assertEquals("Negative 100 Milliseconds", DateTimeUtility.durationToDurationString(-100L, false, true));
        Assert.assertEquals("Negative 999 Milliseconds", DateTimeUtility.durationToDurationString(-999L, false, true));
        Assert.assertEquals("Negative 1 Second 0 Milliseconds", DateTimeUtility.durationToDurationString(-1000L, false, true));
        Assert.assertEquals("Negative 10 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(-10000L, false, true));
        Assert.assertEquals("Negative 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(-59999L, false, true));
        Assert.assertEquals("Negative 1 Minute 0 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(-60000L, false, true));
        Assert.assertEquals("Negative 59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(-3599999L, false, true));
        Assert.assertEquals("Negative 1 Hour 0 Minutes 0 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(-3600000L, false, true));
        Assert.assertEquals("Negative 23 Hours 59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(-86399999L, false, true));
        Assert.assertEquals("Negative 1 Day 0 Hours 0 Minutes 0 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(-86400000L, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(0L, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-0L, false, true));
        
        //abbreviation off, show milliseconds off
        Assert.assertEquals("12 Hours 16 Minutes 3 Seconds", DateTimeUtility.durationToDurationString(44163191L, false, false));
        Assert.assertEquals("11 Days 11 Hours 25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(991542231L, false, false));
        Assert.assertEquals("25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(1542231L, false, false));
        Assert.assertEquals("25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(1542200L, false, false));
        Assert.assertEquals("9 Minutes 2 Seconds", DateTimeUtility.durationToDurationString(542877L, false, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(1L, false, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(10L, false, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(100L, false, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(999L, false, false));
        Assert.assertEquals("1 Second", DateTimeUtility.durationToDurationString(1000L, false, false));
        Assert.assertEquals("10 Seconds", DateTimeUtility.durationToDurationString(10000L, false, false));
        Assert.assertEquals("59 Seconds", DateTimeUtility.durationToDurationString(59999L, false, false));
        Assert.assertEquals("1 Minute 0 Seconds", DateTimeUtility.durationToDurationString(60000L, false, false));
        Assert.assertEquals("59 Minutes 59 Seconds", DateTimeUtility.durationToDurationString(3599999L, false, false));
        Assert.assertEquals("1 Hour 0 Minutes 0 Seconds", DateTimeUtility.durationToDurationString(3600000L, false, false));
        Assert.assertEquals("23 Hours 59 Minutes 59 Seconds", DateTimeUtility.durationToDurationString(86399999L, false, false));
        Assert.assertEquals("1 Day 0 Hours 0 Minutes 0 Seconds", DateTimeUtility.durationToDurationString(86400000L, false, false));
        Assert.assertEquals("Negative 12 Hours 16 Minutes 3 Seconds", DateTimeUtility.durationToDurationString(-44163191L, false, false));
        Assert.assertEquals("Negative 11 Days 11 Hours 25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(-991542231L, false, false));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(-1542231L, false, false));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(-1542200L, false, false));
        Assert.assertEquals("Negative 9 Minutes 2 Seconds", DateTimeUtility.durationToDurationString(-542877L, false, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-1L, false, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-10L, false, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-100L, false, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-999L, false, false));
        Assert.assertEquals("Negative 1 Second", DateTimeUtility.durationToDurationString(-1000L, false, false));
        Assert.assertEquals("Negative 10 Seconds", DateTimeUtility.durationToDurationString(-10000L, false, false));
        Assert.assertEquals("Negative 59 Seconds", DateTimeUtility.durationToDurationString(-59999L, false, false));
        Assert.assertEquals("Negative 1 Minute 0 Seconds", DateTimeUtility.durationToDurationString(-60000L, false, false));
        Assert.assertEquals("Negative 59 Minutes 59 Seconds", DateTimeUtility.durationToDurationString(-3599999L, false, false));
        Assert.assertEquals("Negative 1 Hour 0 Minutes 0 Seconds", DateTimeUtility.durationToDurationString(-3600000L, false, false));
        Assert.assertEquals("Negative 23 Hours 59 Minutes 59 Seconds", DateTimeUtility.durationToDurationString(-86399999L, false, false));
        Assert.assertEquals("Negative 1 Day 0 Hours 0 Minutes 0 Seconds", DateTimeUtility.durationToDurationString(-86400000L, false, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(0L, false, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-0L, false, false));
        
        //abbreviation on, show milliseconds on, abbreviate units on
        Assert.assertEquals("12h 16m 3s 191ms", DateTimeUtility.durationToDurationString(44163191L, true, true, true));
        Assert.assertEquals("11d 11h 25m 42s 231ms", DateTimeUtility.durationToDurationString(991542231L, true, true, true));
        Assert.assertEquals("25m 42s 231ms", DateTimeUtility.durationToDurationString(1542231L, true, true, true));
        Assert.assertEquals("25m 42s 200ms", DateTimeUtility.durationToDurationString(1542200L, true, true, true));
        Assert.assertEquals("9m 2s 877ms", DateTimeUtility.durationToDurationString(542877L, true, true, true));
        Assert.assertEquals("1ms", DateTimeUtility.durationToDurationString(1L, true, true, true));
        Assert.assertEquals("10ms", DateTimeUtility.durationToDurationString(10L, true, true, true));
        Assert.assertEquals("100ms", DateTimeUtility.durationToDurationString(100L, true, true, true));
        Assert.assertEquals("999ms", DateTimeUtility.durationToDurationString(999L, true, true, true));
        Assert.assertEquals("1s", DateTimeUtility.durationToDurationString(1000L, true, true, true));
        Assert.assertEquals("10s", DateTimeUtility.durationToDurationString(10000L, true, true, true));
        Assert.assertEquals("59s 999ms", DateTimeUtility.durationToDurationString(59999L, true, true, true));
        Assert.assertEquals("1m", DateTimeUtility.durationToDurationString(60000L, true, true, true));
        Assert.assertEquals("59m 59s 999ms", DateTimeUtility.durationToDurationString(3599999L, true, true, true));
        Assert.assertEquals("1h", DateTimeUtility.durationToDurationString(3600000L, true, true, true));
        Assert.assertEquals("23h 59m 59s 999ms", DateTimeUtility.durationToDurationString(86399999L, true, true, true));
        Assert.assertEquals("1d", DateTimeUtility.durationToDurationString(86400000L, true, true, true));
        Assert.assertEquals("- 12h 16m 3s 191ms", DateTimeUtility.durationToDurationString(-44163191L, true, true, true));
        Assert.assertEquals("- 11d 11h 25m 42s 231ms", DateTimeUtility.durationToDurationString(-991542231L, true, true, true));
        Assert.assertEquals("- 25m 42s 231ms", DateTimeUtility.durationToDurationString(-1542231L, true, true, true));
        Assert.assertEquals("- 25m 42s 200ms", DateTimeUtility.durationToDurationString(-1542200L, true, true, true));
        Assert.assertEquals("- 9m 2s 877ms", DateTimeUtility.durationToDurationString(-542877L, true, true, true));
        Assert.assertEquals("- 1ms", DateTimeUtility.durationToDurationString(-1L, true, true, true));
        Assert.assertEquals("- 10ms", DateTimeUtility.durationToDurationString(-10L, true, true, true));
        Assert.assertEquals("- 100ms", DateTimeUtility.durationToDurationString(-100L, true, true, true));
        Assert.assertEquals("- 999ms", DateTimeUtility.durationToDurationString(-999L, true, true, true));
        Assert.assertEquals("- 1s", DateTimeUtility.durationToDurationString(-1000L, true, true, true));
        Assert.assertEquals("- 10s", DateTimeUtility.durationToDurationString(-10000L, true, true, true));
        Assert.assertEquals("- 59s 999ms", DateTimeUtility.durationToDurationString(-59999L, true, true, true));
        Assert.assertEquals("- 1m", DateTimeUtility.durationToDurationString(-60000L, true, true, true));
        Assert.assertEquals("- 59m 59s 999ms", DateTimeUtility.durationToDurationString(-3599999L, true, true, true));
        Assert.assertEquals("- 1h", DateTimeUtility.durationToDurationString(-3600000L, true, true, true));
        Assert.assertEquals("- 23h 59m 59s 999ms", DateTimeUtility.durationToDurationString(-86399999L, true, true, true));
        Assert.assertEquals("- 1d", DateTimeUtility.durationToDurationString(-86400000L, true, true, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(0L, true, true, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-0L, true, true, true));
        
        //abbreviation on, show milliseconds on, abbreviate units off
        Assert.assertEquals("12 Hours 16 Minutes 3 Seconds 191 Milliseconds", DateTimeUtility.durationToDurationString(44163191L, true, true, false));
        Assert.assertEquals("11 Days 11 Hours 25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(991542231L, true, true, false));
        Assert.assertEquals("25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(1542231L, true, true, false));
        Assert.assertEquals("25 Minutes 42 Seconds 200 Milliseconds", DateTimeUtility.durationToDurationString(1542200L, true, true, false));
        Assert.assertEquals("9 Minutes 2 Seconds 877 Milliseconds", DateTimeUtility.durationToDurationString(542877L, true, true, false));
        Assert.assertEquals("1 Millisecond", DateTimeUtility.durationToDurationString(1L, true, true, false));
        Assert.assertEquals("10 Milliseconds", DateTimeUtility.durationToDurationString(10L, true, true, false));
        Assert.assertEquals("100 Milliseconds", DateTimeUtility.durationToDurationString(100L, true, true, false));
        Assert.assertEquals("999 Milliseconds", DateTimeUtility.durationToDurationString(999L, true, true, false));
        Assert.assertEquals("1 Second", DateTimeUtility.durationToDurationString(1000L, true, true, false));
        Assert.assertEquals("10 Seconds", DateTimeUtility.durationToDurationString(10000L, true, true, false));
        Assert.assertEquals("59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(59999L, true, true, false));
        Assert.assertEquals("1 Minute", DateTimeUtility.durationToDurationString(60000L, true, true, false));
        Assert.assertEquals("59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(3599999L, true, true, false));
        Assert.assertEquals("1 Hour", DateTimeUtility.durationToDurationString(3600000L, true, true, false));
        Assert.assertEquals("23 Hours 59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(86399999L, true, true, false));
        Assert.assertEquals("1 Day", DateTimeUtility.durationToDurationString(86400000L, true, true, false));
        Assert.assertEquals("Negative 12 Hours 16 Minutes 3 Seconds 191 Milliseconds", DateTimeUtility.durationToDurationString(-44163191L, true, true, false));
        Assert.assertEquals("Negative 11 Days 11 Hours 25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(-991542231L, true, true, false));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(-1542231L, true, true, false));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds 200 Milliseconds", DateTimeUtility.durationToDurationString(-1542200L, true, true, false));
        Assert.assertEquals("Negative 9 Minutes 2 Seconds 877 Milliseconds", DateTimeUtility.durationToDurationString(-542877L, true, true, false));
        Assert.assertEquals("Negative 1 Millisecond", DateTimeUtility.durationToDurationString(-1L, true, true, false));
        Assert.assertEquals("Negative 10 Milliseconds", DateTimeUtility.durationToDurationString(-10L, true, true, false));
        Assert.assertEquals("Negative 100 Milliseconds", DateTimeUtility.durationToDurationString(-100L, true, true, false));
        Assert.assertEquals("Negative 999 Milliseconds", DateTimeUtility.durationToDurationString(-999L, true, true, false));
        Assert.assertEquals("Negative 1 Second", DateTimeUtility.durationToDurationString(-1000L, true, true, false));
        Assert.assertEquals("Negative 10 Seconds", DateTimeUtility.durationToDurationString(-10000L, true, true, false));
        Assert.assertEquals("Negative 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(-59999L, true, true, false));
        Assert.assertEquals("Negative 1 Minute", DateTimeUtility.durationToDurationString(-60000L, true, true, false));
        Assert.assertEquals("Negative 59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(-3599999L, true, true, false));
        Assert.assertEquals("Negative 1 Hour", DateTimeUtility.durationToDurationString(-3600000L, true, true, false));
        Assert.assertEquals("Negative 23 Hours 59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(-86399999L, true, true, false));
        Assert.assertEquals("Negative 1 Day", DateTimeUtility.durationToDurationString(-86400000L, true, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(0L, true, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-0L, true, true, false));
        
        //abbreviation on, show milliseconds off, abbreviate units on
        Assert.assertEquals("12h 16m 3s", DateTimeUtility.durationToDurationString(44163191L, true, false, true));
        Assert.assertEquals("11d 11h 25m 42s", DateTimeUtility.durationToDurationString(991542231L, true, false, true));
        Assert.assertEquals("25m 42s", DateTimeUtility.durationToDurationString(1542231L, true, false, true));
        Assert.assertEquals("25m 42s", DateTimeUtility.durationToDurationString(1542200L, true, false, true));
        Assert.assertEquals("9m 2s", DateTimeUtility.durationToDurationString(542877L, true, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(1L, true, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(10L, true, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(100L, true, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(999L, true, false, true));
        Assert.assertEquals("1s", DateTimeUtility.durationToDurationString(1000L, true, false, true));
        Assert.assertEquals("10s", DateTimeUtility.durationToDurationString(10000L, true, false, true));
        Assert.assertEquals("59s", DateTimeUtility.durationToDurationString(59999L, true, false, true));
        Assert.assertEquals("1m", DateTimeUtility.durationToDurationString(60000L, true, false, true));
        Assert.assertEquals("59m 59s", DateTimeUtility.durationToDurationString(3599999L, true, false, true));
        Assert.assertEquals("1h", DateTimeUtility.durationToDurationString(3600000L, true, false, true));
        Assert.assertEquals("23h 59m 59s", DateTimeUtility.durationToDurationString(86399999L, true, false, true));
        Assert.assertEquals("1d", DateTimeUtility.durationToDurationString(86400000L, true, false, true));
        Assert.assertEquals("- 12h 16m 3s", DateTimeUtility.durationToDurationString(-44163191L, true, false, true));
        Assert.assertEquals("- 11d 11h 25m 42s", DateTimeUtility.durationToDurationString(-991542231L, true, false, true));
        Assert.assertEquals("- 25m 42s", DateTimeUtility.durationToDurationString(-1542231L, true, false, true));
        Assert.assertEquals("- 25m 42s", DateTimeUtility.durationToDurationString(-1542200L, true, false, true));
        Assert.assertEquals("- 9m 2s", DateTimeUtility.durationToDurationString(-542877L, true, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-1L, true, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-10L, true, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-100L, true, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-999L, true, false, true));
        Assert.assertEquals("- 1s", DateTimeUtility.durationToDurationString(-1000L, true, false, true));
        Assert.assertEquals("- 10s", DateTimeUtility.durationToDurationString(-10000L, true, false, true));
        Assert.assertEquals("- 59s", DateTimeUtility.durationToDurationString(-59999L, true, false, true));
        Assert.assertEquals("- 1m", DateTimeUtility.durationToDurationString(-60000L, true, false, true));
        Assert.assertEquals("- 59m 59s", DateTimeUtility.durationToDurationString(-3599999L, true, false, true));
        Assert.assertEquals("- 1h", DateTimeUtility.durationToDurationString(-3600000L, true, false, true));
        Assert.assertEquals("- 23h 59m 59s", DateTimeUtility.durationToDurationString(-86399999L, true, false, true));
        Assert.assertEquals("- 1d", DateTimeUtility.durationToDurationString(-86400000L, true, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(0L, true, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-0L, true, false, true));
        
        //abbreviation on, show milliseconds off, abbreviate units off
        Assert.assertEquals("12 Hours 16 Minutes 3 Seconds", DateTimeUtility.durationToDurationString(44163191L, true, false));
        Assert.assertEquals("11 Days 11 Hours 25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(991542231L, true, false));
        Assert.assertEquals("25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(1542231L, true, false));
        Assert.assertEquals("25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(1542200L, true, false));
        Assert.assertEquals("9 Minutes 2 Seconds", DateTimeUtility.durationToDurationString(542877L, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(1L, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(10L, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(100L, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(999L, true, false));
        Assert.assertEquals("1 Second", DateTimeUtility.durationToDurationString(1000L, true, false));
        Assert.assertEquals("10 Seconds", DateTimeUtility.durationToDurationString(10000L, true, false));
        Assert.assertEquals("59 Seconds", DateTimeUtility.durationToDurationString(59999L, true, false));
        Assert.assertEquals("1 Minute", DateTimeUtility.durationToDurationString(60000L, true, false));
        Assert.assertEquals("59 Minutes 59 Seconds", DateTimeUtility.durationToDurationString(3599999L, true, false));
        Assert.assertEquals("1 Hour", DateTimeUtility.durationToDurationString(3600000L, true, false));
        Assert.assertEquals("23 Hours 59 Minutes 59 Seconds", DateTimeUtility.durationToDurationString(86399999L, true, false));
        Assert.assertEquals("1 Day", DateTimeUtility.durationToDurationString(86400000L, true, false));
        Assert.assertEquals("Negative 12 Hours 16 Minutes 3 Seconds", DateTimeUtility.durationToDurationString(-44163191L, true, false));
        Assert.assertEquals("Negative 11 Days 11 Hours 25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(-991542231L, true, false));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(-1542231L, true, false));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(-1542200L, true, false));
        Assert.assertEquals("Negative 9 Minutes 2 Seconds", DateTimeUtility.durationToDurationString(-542877L, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-1L, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-10L, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-100L, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-999L, true, false));
        Assert.assertEquals("Negative 1 Second", DateTimeUtility.durationToDurationString(-1000L, true, false));
        Assert.assertEquals("Negative 10 Seconds", DateTimeUtility.durationToDurationString(-10000L, true, false));
        Assert.assertEquals("Negative 59 Seconds", DateTimeUtility.durationToDurationString(-59999L, true, false));
        Assert.assertEquals("Negative 1 Minute", DateTimeUtility.durationToDurationString(-60000L, true, false));
        Assert.assertEquals("Negative 59 Minutes 59 Seconds", DateTimeUtility.durationToDurationString(-3599999L, true, false));
        Assert.assertEquals("Negative 1 Hour", DateTimeUtility.durationToDurationString(-3600000L, true, false));
        Assert.assertEquals("Negative 23 Hours 59 Minutes 59 Seconds", DateTimeUtility.durationToDurationString(-86399999L, true, false));
        Assert.assertEquals("Negative 1 Day", DateTimeUtility.durationToDurationString(-86400000L, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(0L, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-0L, true, false));
        
        //abbreviation off, show milliseconds on, abbreviate units on
        Assert.assertEquals("12h 16m 3s 191ms", DateTimeUtility.durationToDurationString(44163191L, false, true, true));
        Assert.assertEquals("11d 11h 25m 42s 231ms", DateTimeUtility.durationToDurationString(991542231L, false, true, true));
        Assert.assertEquals("25m 42s 231ms", DateTimeUtility.durationToDurationString(1542231L, false, true, true));
        Assert.assertEquals("25m 42s 200ms", DateTimeUtility.durationToDurationString(1542200L, false, true, true));
        Assert.assertEquals("9m 2s 877ms", DateTimeUtility.durationToDurationString(542877L, false, true, true));
        Assert.assertEquals("1ms", DateTimeUtility.durationToDurationString(1L, false, true, true));
        Assert.assertEquals("10ms", DateTimeUtility.durationToDurationString(10L, false, true, true));
        Assert.assertEquals("100ms", DateTimeUtility.durationToDurationString(100L, false, true, true));
        Assert.assertEquals("999ms", DateTimeUtility.durationToDurationString(999L, false, true, true));
        Assert.assertEquals("1s 0ms", DateTimeUtility.durationToDurationString(1000L, false, true, true));
        Assert.assertEquals("10s 0ms", DateTimeUtility.durationToDurationString(10000L, false, true, true));
        Assert.assertEquals("59s 999ms", DateTimeUtility.durationToDurationString(59999L, false, true, true));
        Assert.assertEquals("1m 0s 0ms", DateTimeUtility.durationToDurationString(60000L, false, true, true));
        Assert.assertEquals("59m 59s 999ms", DateTimeUtility.durationToDurationString(3599999L, false, true, true));
        Assert.assertEquals("1h 0m 0s 0ms", DateTimeUtility.durationToDurationString(3600000L, false, true, true));
        Assert.assertEquals("23h 59m 59s 999ms", DateTimeUtility.durationToDurationString(86399999L, false, true, true));
        Assert.assertEquals("1d 0h 0m 0s 0ms", DateTimeUtility.durationToDurationString(86400000L, false, true, true));
        Assert.assertEquals("- 12h 16m 3s 191ms", DateTimeUtility.durationToDurationString(-44163191L, false, true, true));
        Assert.assertEquals("- 11d 11h 25m 42s 231ms", DateTimeUtility.durationToDurationString(-991542231L, false, true, true));
        Assert.assertEquals("- 25m 42s 231ms", DateTimeUtility.durationToDurationString(-1542231L, false, true, true));
        Assert.assertEquals("- 25m 42s 200ms", DateTimeUtility.durationToDurationString(-1542200L, false, true, true));
        Assert.assertEquals("- 9m 2s 877ms", DateTimeUtility.durationToDurationString(-542877L, false, true, true));
        Assert.assertEquals("- 1ms", DateTimeUtility.durationToDurationString(-1L, false, true, true));
        Assert.assertEquals("- 10ms", DateTimeUtility.durationToDurationString(-10L, false, true, true));
        Assert.assertEquals("- 100ms", DateTimeUtility.durationToDurationString(-100L, false, true, true));
        Assert.assertEquals("- 999ms", DateTimeUtility.durationToDurationString(-999L, false, true, true));
        Assert.assertEquals("- 1s 0ms", DateTimeUtility.durationToDurationString(-1000L, false, true, true));
        Assert.assertEquals("- 10s 0ms", DateTimeUtility.durationToDurationString(-10000L, false, true, true));
        Assert.assertEquals("- 59s 999ms", DateTimeUtility.durationToDurationString(-59999L, false, true, true));
        Assert.assertEquals("- 1m 0s 0ms", DateTimeUtility.durationToDurationString(-60000L, false, true, true));
        Assert.assertEquals("- 59m 59s 999ms", DateTimeUtility.durationToDurationString(-3599999L, false, true, true));
        Assert.assertEquals("- 1h 0m 0s 0ms", DateTimeUtility.durationToDurationString(-3600000L, false, true, true));
        Assert.assertEquals("- 23h 59m 59s 999ms", DateTimeUtility.durationToDurationString(-86399999L, false, true, true));
        Assert.assertEquals("- 1d 0h 0m 0s 0ms", DateTimeUtility.durationToDurationString(-86400000L, false, true, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(0L, false, true, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-0L, false, true, true));
        
        //abbreviation off, show milliseconds on, abbreviate units off
        Assert.assertEquals("12 Hours 16 Minutes 3 Seconds 191 Milliseconds", DateTimeUtility.durationToDurationString(44163191L, false, true, false));
        Assert.assertEquals("11 Days 11 Hours 25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(991542231L, false, true, false));
        Assert.assertEquals("25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(1542231L, false, true, false));
        Assert.assertEquals("25 Minutes 42 Seconds 200 Milliseconds", DateTimeUtility.durationToDurationString(1542200L, false, true, false));
        Assert.assertEquals("9 Minutes 2 Seconds 877 Milliseconds", DateTimeUtility.durationToDurationString(542877L, false, true, false));
        Assert.assertEquals("1 Millisecond", DateTimeUtility.durationToDurationString(1L, false, true, false));
        Assert.assertEquals("10 Milliseconds", DateTimeUtility.durationToDurationString(10L, false, true, false));
        Assert.assertEquals("100 Milliseconds", DateTimeUtility.durationToDurationString(100L, false, true, false));
        Assert.assertEquals("999 Milliseconds", DateTimeUtility.durationToDurationString(999L, false, true, false));
        Assert.assertEquals("1 Second 0 Milliseconds", DateTimeUtility.durationToDurationString(1000L, false, true, false));
        Assert.assertEquals("10 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(10000L, false, true, false));
        Assert.assertEquals("59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(59999L, false, true, false));
        Assert.assertEquals("1 Minute 0 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(60000L, false, true, false));
        Assert.assertEquals("59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(3599999L, false, true, false));
        Assert.assertEquals("1 Hour 0 Minutes 0 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(3600000L, false, true, false));
        Assert.assertEquals("23 Hours 59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(86399999L, false, true, false));
        Assert.assertEquals("1 Day 0 Hours 0 Minutes 0 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(86400000L, false, true, false));
        Assert.assertEquals("Negative 12 Hours 16 Minutes 3 Seconds 191 Milliseconds", DateTimeUtility.durationToDurationString(-44163191L, false, true, false));
        Assert.assertEquals("Negative 11 Days 11 Hours 25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(-991542231L, false, true, false));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds 231 Milliseconds", DateTimeUtility.durationToDurationString(-1542231L, false, true, false));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds 200 Milliseconds", DateTimeUtility.durationToDurationString(-1542200L, false, true, false));
        Assert.assertEquals("Negative 9 Minutes 2 Seconds 877 Milliseconds", DateTimeUtility.durationToDurationString(-542877L, false, true, false));
        Assert.assertEquals("Negative 1 Millisecond", DateTimeUtility.durationToDurationString(-1L, false, true, false));
        Assert.assertEquals("Negative 10 Milliseconds", DateTimeUtility.durationToDurationString(-10L, false, true, false));
        Assert.assertEquals("Negative 100 Milliseconds", DateTimeUtility.durationToDurationString(-100L, false, true, false));
        Assert.assertEquals("Negative 999 Milliseconds", DateTimeUtility.durationToDurationString(-999L, false, true, false));
        Assert.assertEquals("Negative 1 Second 0 Milliseconds", DateTimeUtility.durationToDurationString(-1000L, false, true, false));
        Assert.assertEquals("Negative 10 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(-10000L, false, true, false));
        Assert.assertEquals("Negative 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(-59999L, false, true, false));
        Assert.assertEquals("Negative 1 Minute 0 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(-60000L, false, true, false));
        Assert.assertEquals("Negative 59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(-3599999L, false, true, false));
        Assert.assertEquals("Negative 1 Hour 0 Minutes 0 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(-3600000L, false, true, false));
        Assert.assertEquals("Negative 23 Hours 59 Minutes 59 Seconds 999 Milliseconds", DateTimeUtility.durationToDurationString(-86399999L, false, true, false));
        Assert.assertEquals("Negative 1 Day 0 Hours 0 Minutes 0 Seconds 0 Milliseconds", DateTimeUtility.durationToDurationString(-86400000L, false, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(0L, false, true, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-0L, false, true, false));
        
        //abbreviation off, show milliseconds off, abbreviate units on
        Assert.assertEquals("12h 16m 3s", DateTimeUtility.durationToDurationString(44163191L, false, false, true));
        Assert.assertEquals("11d 11h 25m 42s", DateTimeUtility.durationToDurationString(991542231L, false, false, true));
        Assert.assertEquals("25m 42s", DateTimeUtility.durationToDurationString(1542231L, false, false, true));
        Assert.assertEquals("25m 42s", DateTimeUtility.durationToDurationString(1542200L, false, false, true));
        Assert.assertEquals("9m 2s", DateTimeUtility.durationToDurationString(542877L, false, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(1L, false, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(10L, false, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(100L, false, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(999L, false, false, true));
        Assert.assertEquals("1s", DateTimeUtility.durationToDurationString(1000L, false, false, true));
        Assert.assertEquals("10s", DateTimeUtility.durationToDurationString(10000L, false, false, true));
        Assert.assertEquals("59s", DateTimeUtility.durationToDurationString(59999L, false, false, true));
        Assert.assertEquals("1m 0s", DateTimeUtility.durationToDurationString(60000L, false, false, true));
        Assert.assertEquals("59m 59s", DateTimeUtility.durationToDurationString(3599999L, false, false, true));
        Assert.assertEquals("1h 0m 0s", DateTimeUtility.durationToDurationString(3600000L, false, false, true));
        Assert.assertEquals("23h 59m 59s", DateTimeUtility.durationToDurationString(86399999L, false, false, true));
        Assert.assertEquals("1d 0h 0m 0s", DateTimeUtility.durationToDurationString(86400000L, false, false, true));
        Assert.assertEquals("- 12h 16m 3s", DateTimeUtility.durationToDurationString(-44163191L, false, false, true));
        Assert.assertEquals("- 11d 11h 25m 42s", DateTimeUtility.durationToDurationString(-991542231L, false, false, true));
        Assert.assertEquals("- 25m 42s", DateTimeUtility.durationToDurationString(-1542231L, false, false, true));
        Assert.assertEquals("- 25m 42s", DateTimeUtility.durationToDurationString(-1542200L, false, false, true));
        Assert.assertEquals("- 9m 2s", DateTimeUtility.durationToDurationString(-542877L, false, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-1L, false, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-10L, false, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-100L, false, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-999L, false, false, true));
        Assert.assertEquals("- 1s", DateTimeUtility.durationToDurationString(-1000L, false, false, true));
        Assert.assertEquals("- 10s", DateTimeUtility.durationToDurationString(-10000L, false, false, true));
        Assert.assertEquals("- 59s", DateTimeUtility.durationToDurationString(-59999L, false, false, true));
        Assert.assertEquals("- 1m 0s", DateTimeUtility.durationToDurationString(-60000L, false, false, true));
        Assert.assertEquals("- 59m 59s", DateTimeUtility.durationToDurationString(-3599999L, false, false, true));
        Assert.assertEquals("- 1h 0m 0s", DateTimeUtility.durationToDurationString(-3600000L, false, false, true));
        Assert.assertEquals("- 23h 59m 59s", DateTimeUtility.durationToDurationString(-86399999L, false, false, true));
        Assert.assertEquals("- 1d 0h 0m 0s", DateTimeUtility.durationToDurationString(-86400000L, false, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(0L, false, false, true));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-0L, false, false, true));
        
        //abbreviation off, show milliseconds off, abbreviate units off
        Assert.assertEquals("12 Hours 16 Minutes 3 Seconds", DateTimeUtility.durationToDurationString(44163191L, false, false, false));
        Assert.assertEquals("11 Days 11 Hours 25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(991542231L, false, false, false));
        Assert.assertEquals("25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(1542231L, false, false, false));
        Assert.assertEquals("25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(1542200L, false, false, false));
        Assert.assertEquals("9 Minutes 2 Seconds", DateTimeUtility.durationToDurationString(542877L, false, false, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(1L, false, false, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(10L, false, false, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(100L, false, false, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(999L, false, false, false));
        Assert.assertEquals("1 Second", DateTimeUtility.durationToDurationString(1000L, false, false, false));
        Assert.assertEquals("10 Seconds", DateTimeUtility.durationToDurationString(10000L, false, false, false));
        Assert.assertEquals("59 Seconds", DateTimeUtility.durationToDurationString(59999L, false, false, false));
        Assert.assertEquals("1 Minute 0 Seconds", DateTimeUtility.durationToDurationString(60000L, false, false, false));
        Assert.assertEquals("59 Minutes 59 Seconds", DateTimeUtility.durationToDurationString(3599999L, false, false, false));
        Assert.assertEquals("1 Hour 0 Minutes 0 Seconds", DateTimeUtility.durationToDurationString(3600000L, false, false, false));
        Assert.assertEquals("23 Hours 59 Minutes 59 Seconds", DateTimeUtility.durationToDurationString(86399999L, false, false, false));
        Assert.assertEquals("1 Day 0 Hours 0 Minutes 0 Seconds", DateTimeUtility.durationToDurationString(86400000L, false, false, false));
        Assert.assertEquals("Negative 12 Hours 16 Minutes 3 Seconds", DateTimeUtility.durationToDurationString(-44163191L, false, false, false));
        Assert.assertEquals("Negative 11 Days 11 Hours 25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(-991542231L, false, false, false));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(-1542231L, false, false, false));
        Assert.assertEquals("Negative 25 Minutes 42 Seconds", DateTimeUtility.durationToDurationString(-1542200L, false, false, false));
        Assert.assertEquals("Negative 9 Minutes 2 Seconds", DateTimeUtility.durationToDurationString(-542877L, false, false, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-1L, false, false, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-10L, false, false, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-100L, false, false, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-999L, false, false, false));
        Assert.assertEquals("Negative 1 Second", DateTimeUtility.durationToDurationString(-1000L, false, false, false));
        Assert.assertEquals("Negative 10 Seconds", DateTimeUtility.durationToDurationString(-10000L, false, false, false));
        Assert.assertEquals("Negative 59 Seconds", DateTimeUtility.durationToDurationString(-59999L, false, false, false));
        Assert.assertEquals("Negative 1 Minute 0 Seconds", DateTimeUtility.durationToDurationString(-60000L, false, false, false));
        Assert.assertEquals("Negative 59 Minutes 59 Seconds", DateTimeUtility.durationToDurationString(-3599999L, false, false, false));
        Assert.assertEquals("Negative 1 Hour 0 Minutes 0 Seconds", DateTimeUtility.durationToDurationString(-3600000L, false, false, false));
        Assert.assertEquals("Negative 23 Hours 59 Minutes 59 Seconds", DateTimeUtility.durationToDurationString(-86399999L, false, false, false));
        Assert.assertEquals("Negative 1 Day 0 Hours 0 Minutes 0 Seconds", DateTimeUtility.durationToDurationString(-86400000L, false, false, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(0L, false, false, false));
        Assert.assertEquals("", DateTimeUtility.durationToDurationString(-0L, false, false, false));
    }
    
    /**
     * JUnit test of durationToDurationStamp.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#durationToDurationStamp(long, boolean, boolean)
     * @see DateTimeUtility#durationToDurationStamp(long, boolean)
     * @see DateTimeUtility#durationToDurationStamp(long)
     */
    @Test
    public void testDurationToDurationStamp() throws Exception {
        //standard
        Assert.assertEquals("12:16:03.191", DateTimeUtility.durationToDurationStamp(44163191L));
        Assert.assertEquals("275:25:42.231", DateTimeUtility.durationToDurationStamp(991542231L));
        Assert.assertEquals("00:25:42.231", DateTimeUtility.durationToDurationStamp(1542231L));
        Assert.assertEquals("00:25:42.200", DateTimeUtility.durationToDurationStamp(1542200L));
        Assert.assertEquals("00:09:02.877", DateTimeUtility.durationToDurationStamp(542877L));
        Assert.assertEquals("00:00:00.001", DateTimeUtility.durationToDurationStamp(1L));
        Assert.assertEquals("00:00:00.010", DateTimeUtility.durationToDurationStamp(10L));
        Assert.assertEquals("00:00:00.100", DateTimeUtility.durationToDurationStamp(100L));
        Assert.assertEquals("00:00:00.999", DateTimeUtility.durationToDurationStamp(999L));
        Assert.assertEquals("00:00:01.000", DateTimeUtility.durationToDurationStamp(1000L));
        Assert.assertEquals("00:00:10.000", DateTimeUtility.durationToDurationStamp(10000L));
        Assert.assertEquals("00:00:59.999", DateTimeUtility.durationToDurationStamp(59999L));
        Assert.assertEquals("00:01:00.000", DateTimeUtility.durationToDurationStamp(60000L));
        Assert.assertEquals("00:59:59.999", DateTimeUtility.durationToDurationStamp(3599999L));
        Assert.assertEquals("01:00:00.000", DateTimeUtility.durationToDurationStamp(3600000L));
        
        //negative
        Assert.assertEquals("-12:16:03.191", DateTimeUtility.durationToDurationStamp(-44163191L));
        Assert.assertEquals("-275:25:42.231", DateTimeUtility.durationToDurationStamp(-991542231L));
        Assert.assertEquals("-00:25:42.231", DateTimeUtility.durationToDurationStamp(-1542231L));
        Assert.assertEquals("-00:25:42.200", DateTimeUtility.durationToDurationStamp(-1542200L));
        Assert.assertEquals("-00:09:02.877", DateTimeUtility.durationToDurationStamp(-542877L));
        Assert.assertEquals("-00:00:00.001", DateTimeUtility.durationToDurationStamp(-1L));
        Assert.assertEquals("-00:00:00.010", DateTimeUtility.durationToDurationStamp(-10L));
        Assert.assertEquals("-00:00:00.100", DateTimeUtility.durationToDurationStamp(-100L));
        Assert.assertEquals("-00:00:00.999", DateTimeUtility.durationToDurationStamp(-999L));
        Assert.assertEquals("-00:00:01.000", DateTimeUtility.durationToDurationStamp(-1000L));
        Assert.assertEquals("-00:00:10.000", DateTimeUtility.durationToDurationStamp(-10000L));
        Assert.assertEquals("-00:00:59.999", DateTimeUtility.durationToDurationStamp(-59999L));
        Assert.assertEquals("-00:01:00.000", DateTimeUtility.durationToDurationStamp(-60000L));
        Assert.assertEquals("-00:59:59.999", DateTimeUtility.durationToDurationStamp(-3599999L));
        Assert.assertEquals("-01:00:00.000", DateTimeUtility.durationToDurationStamp(-3600000L));
        
        //empty
        Assert.assertEquals("00:00:00.000", DateTimeUtility.durationToDurationStamp(0L));
        Assert.assertEquals("00:00:00.000", DateTimeUtility.durationToDurationStamp(-0L));
        
        //abbreviate on
        Assert.assertEquals("12:16:03.191", DateTimeUtility.durationToDurationStamp(44163191L, true));
        Assert.assertEquals("275:25:42.231", DateTimeUtility.durationToDurationStamp(991542231L, true));
        Assert.assertEquals("25:42.231", DateTimeUtility.durationToDurationStamp(1542231L, true));
        Assert.assertEquals("25:42.2", DateTimeUtility.durationToDurationStamp(1542200L, true));
        Assert.assertEquals("9:02.877", DateTimeUtility.durationToDurationStamp(542877L, true));
        Assert.assertEquals("0.001", DateTimeUtility.durationToDurationStamp(1L, true));
        Assert.assertEquals("0.01", DateTimeUtility.durationToDurationStamp(10L, true));
        Assert.assertEquals("0.1", DateTimeUtility.durationToDurationStamp(100L, true));
        Assert.assertEquals("0.999", DateTimeUtility.durationToDurationStamp(999L, true));
        Assert.assertEquals("1", DateTimeUtility.durationToDurationStamp(1000L, true));
        Assert.assertEquals("10", DateTimeUtility.durationToDurationStamp(10000L, true));
        Assert.assertEquals("59.999", DateTimeUtility.durationToDurationStamp(59999L, true));
        Assert.assertEquals("1:00", DateTimeUtility.durationToDurationStamp(60000L, true));
        Assert.assertEquals("59:59.999", DateTimeUtility.durationToDurationStamp(3599999L, true));
        Assert.assertEquals("1:00:00", DateTimeUtility.durationToDurationStamp(3600000L, true));
        Assert.assertEquals("-12:16:03.191", DateTimeUtility.durationToDurationStamp(-44163191L, true));
        Assert.assertEquals("-275:25:42.231", DateTimeUtility.durationToDurationStamp(-991542231L, true));
        Assert.assertEquals("-25:42.231", DateTimeUtility.durationToDurationStamp(-1542231L, true));
        Assert.assertEquals("-25:42.2", DateTimeUtility.durationToDurationStamp(-1542200L, true));
        Assert.assertEquals("-9:02.877", DateTimeUtility.durationToDurationStamp(-542877L, true));
        Assert.assertEquals("-0.001", DateTimeUtility.durationToDurationStamp(-1L, true));
        Assert.assertEquals("-0.01", DateTimeUtility.durationToDurationStamp(-10L, true));
        Assert.assertEquals("-0.1", DateTimeUtility.durationToDurationStamp(-100L, true));
        Assert.assertEquals("-0.999", DateTimeUtility.durationToDurationStamp(-999L, true));
        Assert.assertEquals("-1", DateTimeUtility.durationToDurationStamp(-1000L, true));
        Assert.assertEquals("-10", DateTimeUtility.durationToDurationStamp(-10000L, true));
        Assert.assertEquals("-59.999", DateTimeUtility.durationToDurationStamp(-59999L, true));
        Assert.assertEquals("-1:00", DateTimeUtility.durationToDurationStamp(-60000L, true));
        Assert.assertEquals("-59:59.999", DateTimeUtility.durationToDurationStamp(-3599999L, true));
        Assert.assertEquals("-1:00:00", DateTimeUtility.durationToDurationStamp(-3600000L, true));
        Assert.assertEquals("0", DateTimeUtility.durationToDurationStamp(0L, true));
        Assert.assertEquals("0", DateTimeUtility.durationToDurationStamp(-0L, true));
        
        //abbreviate off
        Assert.assertEquals("12:16:03.191", DateTimeUtility.durationToDurationStamp(44163191L, false));
        Assert.assertEquals("275:25:42.231", DateTimeUtility.durationToDurationStamp(991542231L, false));
        Assert.assertEquals("00:25:42.231", DateTimeUtility.durationToDurationStamp(1542231L, false));
        Assert.assertEquals("00:25:42.200", DateTimeUtility.durationToDurationStamp(1542200L, false));
        Assert.assertEquals("00:09:02.877", DateTimeUtility.durationToDurationStamp(542877L, false));
        Assert.assertEquals("00:00:00.001", DateTimeUtility.durationToDurationStamp(1L, false));
        Assert.assertEquals("00:00:00.010", DateTimeUtility.durationToDurationStamp(10L, false));
        Assert.assertEquals("00:00:00.100", DateTimeUtility.durationToDurationStamp(100L, false));
        Assert.assertEquals("00:00:00.999", DateTimeUtility.durationToDurationStamp(999L, false));
        Assert.assertEquals("00:00:01.000", DateTimeUtility.durationToDurationStamp(1000L, false));
        Assert.assertEquals("00:00:10.000", DateTimeUtility.durationToDurationStamp(10000L, false));
        Assert.assertEquals("00:00:59.999", DateTimeUtility.durationToDurationStamp(59999L, false));
        Assert.assertEquals("00:01:00.000", DateTimeUtility.durationToDurationStamp(60000L, false));
        Assert.assertEquals("00:59:59.999", DateTimeUtility.durationToDurationStamp(3599999L, false));
        Assert.assertEquals("01:00:00.000", DateTimeUtility.durationToDurationStamp(3600000L, false));
        Assert.assertEquals("-12:16:03.191", DateTimeUtility.durationToDurationStamp(-44163191L, false));
        Assert.assertEquals("-275:25:42.231", DateTimeUtility.durationToDurationStamp(-991542231L, false));
        Assert.assertEquals("-00:25:42.231", DateTimeUtility.durationToDurationStamp(-1542231L, false));
        Assert.assertEquals("-00:25:42.200", DateTimeUtility.durationToDurationStamp(-1542200L, false));
        Assert.assertEquals("-00:09:02.877", DateTimeUtility.durationToDurationStamp(-542877L, false));
        Assert.assertEquals("-00:00:00.001", DateTimeUtility.durationToDurationStamp(-1L, false));
        Assert.assertEquals("-00:00:00.010", DateTimeUtility.durationToDurationStamp(-10L, false));
        Assert.assertEquals("-00:00:00.100", DateTimeUtility.durationToDurationStamp(-100L, false));
        Assert.assertEquals("-00:00:00.999", DateTimeUtility.durationToDurationStamp(-999L, false));
        Assert.assertEquals("-00:00:01.000", DateTimeUtility.durationToDurationStamp(-1000L, false));
        Assert.assertEquals("-00:00:10.000", DateTimeUtility.durationToDurationStamp(-10000L, false));
        Assert.assertEquals("-00:00:59.999", DateTimeUtility.durationToDurationStamp(-59999L, false));
        Assert.assertEquals("-00:01:00.000", DateTimeUtility.durationToDurationStamp(-60000L, false));
        Assert.assertEquals("-00:59:59.999", DateTimeUtility.durationToDurationStamp(-3599999L, false));
        Assert.assertEquals("-01:00:00.000", DateTimeUtility.durationToDurationStamp(-3600000L, false));
        Assert.assertEquals("00:00:00.000", DateTimeUtility.durationToDurationStamp(0L, false));
        Assert.assertEquals("00:00:00.000", DateTimeUtility.durationToDurationStamp(-0L, false));
        
        //abbreviate on, show milliseconds on
        Assert.assertEquals("12:16:03.191", DateTimeUtility.durationToDurationStamp(44163191L, true, true));
        Assert.assertEquals("275:25:42.231", DateTimeUtility.durationToDurationStamp(991542231L, true, true));
        Assert.assertEquals("25:42.231", DateTimeUtility.durationToDurationStamp(1542231L, true, true));
        Assert.assertEquals("25:42.2", DateTimeUtility.durationToDurationStamp(1542200L, true, true));
        Assert.assertEquals("9:02.877", DateTimeUtility.durationToDurationStamp(542877L, true, true));
        Assert.assertEquals("0.001", DateTimeUtility.durationToDurationStamp(1L, true, true));
        Assert.assertEquals("0.01", DateTimeUtility.durationToDurationStamp(10L, true, true));
        Assert.assertEquals("0.1", DateTimeUtility.durationToDurationStamp(100L, true, true));
        Assert.assertEquals("0.999", DateTimeUtility.durationToDurationStamp(999L, true, true));
        Assert.assertEquals("1", DateTimeUtility.durationToDurationStamp(1000L, true, true));
        Assert.assertEquals("10", DateTimeUtility.durationToDurationStamp(10000L, true, true));
        Assert.assertEquals("59.999", DateTimeUtility.durationToDurationStamp(59999L, true, true));
        Assert.assertEquals("1:00", DateTimeUtility.durationToDurationStamp(60000L, true, true));
        Assert.assertEquals("59:59.999", DateTimeUtility.durationToDurationStamp(3599999L, true, true));
        Assert.assertEquals("1:00:00", DateTimeUtility.durationToDurationStamp(3600000L, true, true));
        Assert.assertEquals("-12:16:03.191", DateTimeUtility.durationToDurationStamp(-44163191L, true, true));
        Assert.assertEquals("-275:25:42.231", DateTimeUtility.durationToDurationStamp(-991542231L, true, true));
        Assert.assertEquals("-25:42.231", DateTimeUtility.durationToDurationStamp(-1542231L, true, true));
        Assert.assertEquals("-25:42.2", DateTimeUtility.durationToDurationStamp(-1542200L, true, true));
        Assert.assertEquals("-9:02.877", DateTimeUtility.durationToDurationStamp(-542877L, true, true));
        Assert.assertEquals("-0.001", DateTimeUtility.durationToDurationStamp(-1L, true, true));
        Assert.assertEquals("-0.01", DateTimeUtility.durationToDurationStamp(-10L, true, true));
        Assert.assertEquals("-0.1", DateTimeUtility.durationToDurationStamp(-100L, true, true));
        Assert.assertEquals("-0.999", DateTimeUtility.durationToDurationStamp(-999L, true, true));
        Assert.assertEquals("-1", DateTimeUtility.durationToDurationStamp(-1000L, true, true));
        Assert.assertEquals("-10", DateTimeUtility.durationToDurationStamp(-10000L, true, true));
        Assert.assertEquals("-59.999", DateTimeUtility.durationToDurationStamp(-59999L, true, true));
        Assert.assertEquals("-1:00", DateTimeUtility.durationToDurationStamp(-60000L, true, true));
        Assert.assertEquals("-59:59.999", DateTimeUtility.durationToDurationStamp(-3599999L, true, true));
        Assert.assertEquals("-1:00:00", DateTimeUtility.durationToDurationStamp(-3600000L, true, true));
        Assert.assertEquals("0", DateTimeUtility.durationToDurationStamp(0L, true, true));
        Assert.assertEquals("0", DateTimeUtility.durationToDurationStamp(-0L, true, true));
        
        //abbreviate on, show milliseconds off
        Assert.assertEquals("12:16:03", DateTimeUtility.durationToDurationStamp(44163191L, true, false));
        Assert.assertEquals("275:25:42", DateTimeUtility.durationToDurationStamp(991542231L, true, false));
        Assert.assertEquals("25:42", DateTimeUtility.durationToDurationStamp(1542231L, true, false));
        Assert.assertEquals("25:42", DateTimeUtility.durationToDurationStamp(1542200L, true, false));
        Assert.assertEquals("9:02", DateTimeUtility.durationToDurationStamp(542877L, true, false));
        Assert.assertEquals("0", DateTimeUtility.durationToDurationStamp(1L, true, false));
        Assert.assertEquals("0", DateTimeUtility.durationToDurationStamp(10L, true, false));
        Assert.assertEquals("0", DateTimeUtility.durationToDurationStamp(100L, true, false));
        Assert.assertEquals("0", DateTimeUtility.durationToDurationStamp(999L, true, false));
        Assert.assertEquals("1", DateTimeUtility.durationToDurationStamp(1000L, true, false));
        Assert.assertEquals("10", DateTimeUtility.durationToDurationStamp(10000L, true, false));
        Assert.assertEquals("59", DateTimeUtility.durationToDurationStamp(59999L, true, false));
        Assert.assertEquals("1:00", DateTimeUtility.durationToDurationStamp(60000L, true, false));
        Assert.assertEquals("59:59", DateTimeUtility.durationToDurationStamp(3599999L, true, false));
        Assert.assertEquals("1:00:00", DateTimeUtility.durationToDurationStamp(3600000L, true, false));
        Assert.assertEquals("-12:16:03", DateTimeUtility.durationToDurationStamp(-44163191L, true, false));
        Assert.assertEquals("-275:25:42", DateTimeUtility.durationToDurationStamp(-991542231L, true, false));
        Assert.assertEquals("-25:42", DateTimeUtility.durationToDurationStamp(-1542231L, true, false));
        Assert.assertEquals("-25:42", DateTimeUtility.durationToDurationStamp(-1542200L, true, false));
        Assert.assertEquals("-9:02", DateTimeUtility.durationToDurationStamp(-542877L, true, false));
        Assert.assertEquals("-0", DateTimeUtility.durationToDurationStamp(-1L, true, false));
        Assert.assertEquals("-0", DateTimeUtility.durationToDurationStamp(-10L, true, false));
        Assert.assertEquals("-0", DateTimeUtility.durationToDurationStamp(-100L, true, false));
        Assert.assertEquals("-0", DateTimeUtility.durationToDurationStamp(-999L, true, false));
        Assert.assertEquals("-1", DateTimeUtility.durationToDurationStamp(-1000L, true, false));
        Assert.assertEquals("-10", DateTimeUtility.durationToDurationStamp(-10000L, true, false));
        Assert.assertEquals("-59", DateTimeUtility.durationToDurationStamp(-59999L, true, false));
        Assert.assertEquals("-1:00", DateTimeUtility.durationToDurationStamp(-60000L, true, false));
        Assert.assertEquals("-59:59", DateTimeUtility.durationToDurationStamp(-3599999L, true, false));
        Assert.assertEquals("-1:00:00", DateTimeUtility.durationToDurationStamp(-3600000L, true, false));
        Assert.assertEquals("0", DateTimeUtility.durationToDurationStamp(0L, true, false));
        Assert.assertEquals("0", DateTimeUtility.durationToDurationStamp(-0L, true, false));
        
        //abbreviate off, show milliseconds on
        Assert.assertEquals("12:16:03.191", DateTimeUtility.durationToDurationStamp(44163191L, false, true));
        Assert.assertEquals("275:25:42.231", DateTimeUtility.durationToDurationStamp(991542231L, false, true));
        Assert.assertEquals("00:25:42.231", DateTimeUtility.durationToDurationStamp(1542231L, false, true));
        Assert.assertEquals("00:25:42.200", DateTimeUtility.durationToDurationStamp(1542200L, false, true));
        Assert.assertEquals("00:09:02.877", DateTimeUtility.durationToDurationStamp(542877L, false, true));
        Assert.assertEquals("00:00:00.001", DateTimeUtility.durationToDurationStamp(1L, false, true));
        Assert.assertEquals("00:00:00.010", DateTimeUtility.durationToDurationStamp(10L, false, true));
        Assert.assertEquals("00:00:00.100", DateTimeUtility.durationToDurationStamp(100L, false, true));
        Assert.assertEquals("00:00:00.999", DateTimeUtility.durationToDurationStamp(999L, false, true));
        Assert.assertEquals("00:00:01.000", DateTimeUtility.durationToDurationStamp(1000L, false, true));
        Assert.assertEquals("00:00:10.000", DateTimeUtility.durationToDurationStamp(10000L, false, true));
        Assert.assertEquals("00:00:59.999", DateTimeUtility.durationToDurationStamp(59999L, false, true));
        Assert.assertEquals("00:01:00.000", DateTimeUtility.durationToDurationStamp(60000L, false, true));
        Assert.assertEquals("00:59:59.999", DateTimeUtility.durationToDurationStamp(3599999L, false, true));
        Assert.assertEquals("01:00:00.000", DateTimeUtility.durationToDurationStamp(3600000L, false, true));
        Assert.assertEquals("-12:16:03.191", DateTimeUtility.durationToDurationStamp(-44163191L, false, true));
        Assert.assertEquals("-275:25:42.231", DateTimeUtility.durationToDurationStamp(-991542231L, false, true));
        Assert.assertEquals("-00:25:42.231", DateTimeUtility.durationToDurationStamp(-1542231L, false, true));
        Assert.assertEquals("-00:25:42.200", DateTimeUtility.durationToDurationStamp(-1542200L, false, true));
        Assert.assertEquals("-00:09:02.877", DateTimeUtility.durationToDurationStamp(-542877L, false, true));
        Assert.assertEquals("-00:00:00.001", DateTimeUtility.durationToDurationStamp(-1L, false, true));
        Assert.assertEquals("-00:00:00.010", DateTimeUtility.durationToDurationStamp(-10L, false, true));
        Assert.assertEquals("-00:00:00.100", DateTimeUtility.durationToDurationStamp(-100L, false, true));
        Assert.assertEquals("-00:00:00.999", DateTimeUtility.durationToDurationStamp(-999L, false, true));
        Assert.assertEquals("-00:00:01.000", DateTimeUtility.durationToDurationStamp(-1000L, false, true));
        Assert.assertEquals("-00:00:10.000", DateTimeUtility.durationToDurationStamp(-10000L, false, true));
        Assert.assertEquals("-00:00:59.999", DateTimeUtility.durationToDurationStamp(-59999L, false, true));
        Assert.assertEquals("-00:01:00.000", DateTimeUtility.durationToDurationStamp(-60000L, false, true));
        Assert.assertEquals("-00:59:59.999", DateTimeUtility.durationToDurationStamp(-3599999L, false, true));
        Assert.assertEquals("-01:00:00.000", DateTimeUtility.durationToDurationStamp(-3600000L, false, true));
        Assert.assertEquals("00:00:00.000", DateTimeUtility.durationToDurationStamp(0L, false, true));
        Assert.assertEquals("00:00:00.000", DateTimeUtility.durationToDurationStamp(-0L, false, true));
        
        //abbreviate off, show milliseconds off
        Assert.assertEquals("12:16:03", DateTimeUtility.durationToDurationStamp(44163191L, false, false));
        Assert.assertEquals("275:25:42", DateTimeUtility.durationToDurationStamp(991542231L, false, false));
        Assert.assertEquals("00:25:42", DateTimeUtility.durationToDurationStamp(1542231L, false, false));
        Assert.assertEquals("00:25:42", DateTimeUtility.durationToDurationStamp(1542200L, false, false));
        Assert.assertEquals("00:09:02", DateTimeUtility.durationToDurationStamp(542877L, false, false));
        Assert.assertEquals("00:00:00", DateTimeUtility.durationToDurationStamp(1L, false, false));
        Assert.assertEquals("00:00:00", DateTimeUtility.durationToDurationStamp(10L, false, false));
        Assert.assertEquals("00:00:00", DateTimeUtility.durationToDurationStamp(100L, false, false));
        Assert.assertEquals("00:00:00", DateTimeUtility.durationToDurationStamp(999L, false, false));
        Assert.assertEquals("00:00:01", DateTimeUtility.durationToDurationStamp(1000L, false, false));
        Assert.assertEquals("00:00:10", DateTimeUtility.durationToDurationStamp(10000L, false, false));
        Assert.assertEquals("00:00:59", DateTimeUtility.durationToDurationStamp(59999L, false, false));
        Assert.assertEquals("00:01:00", DateTimeUtility.durationToDurationStamp(60000L, false, false));
        Assert.assertEquals("00:59:59", DateTimeUtility.durationToDurationStamp(3599999L, false, false));
        Assert.assertEquals("01:00:00", DateTimeUtility.durationToDurationStamp(3600000L, false, false));
        Assert.assertEquals("-12:16:03", DateTimeUtility.durationToDurationStamp(-44163191L, false, false));
        Assert.assertEquals("-275:25:42", DateTimeUtility.durationToDurationStamp(-991542231L, false, false));
        Assert.assertEquals("-00:25:42", DateTimeUtility.durationToDurationStamp(-1542231L, false, false));
        Assert.assertEquals("-00:25:42", DateTimeUtility.durationToDurationStamp(-1542200L, false, false));
        Assert.assertEquals("-00:09:02", DateTimeUtility.durationToDurationStamp(-542877L, false, false));
        Assert.assertEquals("-00:00:00", DateTimeUtility.durationToDurationStamp(-1L, false, false));
        Assert.assertEquals("-00:00:00", DateTimeUtility.durationToDurationStamp(-10L, false, false));
        Assert.assertEquals("-00:00:00", DateTimeUtility.durationToDurationStamp(-100L, false, false));
        Assert.assertEquals("-00:00:00", DateTimeUtility.durationToDurationStamp(-999L, false, false));
        Assert.assertEquals("-00:00:01", DateTimeUtility.durationToDurationStamp(-1000L, false, false));
        Assert.assertEquals("-00:00:10", DateTimeUtility.durationToDurationStamp(-10000L, false, false));
        Assert.assertEquals("-00:00:59", DateTimeUtility.durationToDurationStamp(-59999L, false, false));
        Assert.assertEquals("-00:01:00", DateTimeUtility.durationToDurationStamp(-60000L, false, false));
        Assert.assertEquals("-00:59:59", DateTimeUtility.durationToDurationStamp(-3599999L, false, false));
        Assert.assertEquals("-01:00:00", DateTimeUtility.durationToDurationStamp(-3600000L, false, false));
        Assert.assertEquals("00:00:00", DateTimeUtility.durationToDurationStamp(0L, false, false));
        Assert.assertEquals("00:00:00", DateTimeUtility.durationToDurationStamp(-0L, false, false));
    }
    
    /**
     * JUnit test of durationStampToDuration.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#durationStampToDuration(String)
     */
    @Test
    public void testDurationStampToDuration() throws Exception {
        //standard
        Assert.assertEquals(44163191L, DateTimeUtility.durationStampToDuration("12:16:03.191"));
        Assert.assertEquals(991542231L, DateTimeUtility.durationStampToDuration("275:25:42.231"));
        Assert.assertEquals(1542231L, DateTimeUtility.durationStampToDuration("00:25:42.231"));
        Assert.assertEquals(1542200L, DateTimeUtility.durationStampToDuration("00:25:42.200"));
        Assert.assertEquals(542877L, DateTimeUtility.durationStampToDuration("00:09:02.877"));
        Assert.assertEquals(1L, DateTimeUtility.durationStampToDuration("00:00:00.001"));
        Assert.assertEquals(10L, DateTimeUtility.durationStampToDuration("00:00:00.010"));
        Assert.assertEquals(100L, DateTimeUtility.durationStampToDuration("00:00:00.100"));
        Assert.assertEquals(999L, DateTimeUtility.durationStampToDuration("00:00:00.999"));
        Assert.assertEquals(1000L, DateTimeUtility.durationStampToDuration("00:00:01.000"));
        Assert.assertEquals(10000L, DateTimeUtility.durationStampToDuration("00:00:10.000"));
        Assert.assertEquals(59999L, DateTimeUtility.durationStampToDuration("00:00:59.999"));
        Assert.assertEquals(60000L, DateTimeUtility.durationStampToDuration("00:01:00.000"));
        Assert.assertEquals(3599999L, DateTimeUtility.durationStampToDuration("00:59:59.999"));
        Assert.assertEquals(3600000L, DateTimeUtility.durationStampToDuration("01:00:00.000"));
        
        //negative
        Assert.assertEquals(-44163191L, DateTimeUtility.durationStampToDuration("-12:16:03.191"));
        Assert.assertEquals(-991542231L, DateTimeUtility.durationStampToDuration("-275:25:42.231"));
        Assert.assertEquals(-1542231L, DateTimeUtility.durationStampToDuration("-00:25:42.231"));
        Assert.assertEquals(-1542200L, DateTimeUtility.durationStampToDuration("-00:25:42.200"));
        Assert.assertEquals(-542877L, DateTimeUtility.durationStampToDuration("-00:09:02.877"));
        Assert.assertEquals(-1L, DateTimeUtility.durationStampToDuration("-00:00:00.001"));
        Assert.assertEquals(-10L, DateTimeUtility.durationStampToDuration("-00:00:00.010"));
        Assert.assertEquals(-100L, DateTimeUtility.durationStampToDuration("-00:00:00.100"));
        Assert.assertEquals(-999L, DateTimeUtility.durationStampToDuration("-00:00:00.999"));
        Assert.assertEquals(-1000L, DateTimeUtility.durationStampToDuration("-00:00:01.000"));
        Assert.assertEquals(-10000L, DateTimeUtility.durationStampToDuration("-00:00:10.000"));
        Assert.assertEquals(-59999L, DateTimeUtility.durationStampToDuration("-00:00:59.999"));
        Assert.assertEquals(-60000L, DateTimeUtility.durationStampToDuration("-00:01:00.000"));
        Assert.assertEquals(-3599999L, DateTimeUtility.durationStampToDuration("-00:59:59.999"));
        Assert.assertEquals(-3600000L, DateTimeUtility.durationStampToDuration("-01:00:00.000"));
        
        //empty
        Assert.assertEquals(0L, DateTimeUtility.durationStampToDuration("00:00:00.000"));
        Assert.assertEquals(0L, DateTimeUtility.durationStampToDuration("-00:00:00.000"));
        
        //abbreviated
        Assert.assertEquals(44163191L, DateTimeUtility.durationStampToDuration("12:16:03.191"));
        Assert.assertEquals(991542231L, DateTimeUtility.durationStampToDuration("275:25:42.231"));
        Assert.assertEquals(1542231L, DateTimeUtility.durationStampToDuration("25:42.231"));
        Assert.assertEquals(1542200L, DateTimeUtility.durationStampToDuration("25:42.2"));
        Assert.assertEquals(542877L, DateTimeUtility.durationStampToDuration("9:02.877"));
        Assert.assertEquals(1L, DateTimeUtility.durationStampToDuration("0.001"));
        Assert.assertEquals(10L, DateTimeUtility.durationStampToDuration("0.01"));
        Assert.assertEquals(100L, DateTimeUtility.durationStampToDuration("0.1"));
        Assert.assertEquals(999L, DateTimeUtility.durationStampToDuration("0.999"));
        Assert.assertEquals(1000L, DateTimeUtility.durationStampToDuration("1"));
        Assert.assertEquals(10000L, DateTimeUtility.durationStampToDuration("10"));
        Assert.assertEquals(59999L, DateTimeUtility.durationStampToDuration("59.999"));
        Assert.assertEquals(60000L, DateTimeUtility.durationStampToDuration("1:00"));
        Assert.assertEquals(3599999L, DateTimeUtility.durationStampToDuration("59:59.999"));
        Assert.assertEquals(3600000L, DateTimeUtility.durationStampToDuration("1:00:00"));
        Assert.assertEquals(-44163191L, DateTimeUtility.durationStampToDuration("-12:16:03.191"));
        Assert.assertEquals(-991542231L, DateTimeUtility.durationStampToDuration("-275:25:42.231"));
        Assert.assertEquals(-1542231L, DateTimeUtility.durationStampToDuration("-25:42.231"));
        Assert.assertEquals(-1542200L, DateTimeUtility.durationStampToDuration("-25:42.2"));
        Assert.assertEquals(-542877L, DateTimeUtility.durationStampToDuration("-9:02.877"));
        Assert.assertEquals(-1L, DateTimeUtility.durationStampToDuration("-0.001"));
        Assert.assertEquals(-10L, DateTimeUtility.durationStampToDuration("-0.01"));
        Assert.assertEquals(-100L, DateTimeUtility.durationStampToDuration("-0.1"));
        Assert.assertEquals(-999L, DateTimeUtility.durationStampToDuration("-0.999"));
        Assert.assertEquals(-1000L, DateTimeUtility.durationStampToDuration("-1"));
        Assert.assertEquals(-10000L, DateTimeUtility.durationStampToDuration("-10"));
        Assert.assertEquals(-59999L, DateTimeUtility.durationStampToDuration("-59.999"));
        Assert.assertEquals(-60000L, DateTimeUtility.durationStampToDuration("-1:00"));
        Assert.assertEquals(-3599999L, DateTimeUtility.durationStampToDuration("-59:59.999"));
        Assert.assertEquals(-3600000L, DateTimeUtility.durationStampToDuration("-1:00:00"));
        Assert.assertEquals(0L, DateTimeUtility.durationStampToDuration("0"));
        Assert.assertEquals(0L, DateTimeUtility.durationStampToDuration("-0"));
        
        //no milliseconds
        Assert.assertEquals(44163000L, DateTimeUtility.durationStampToDuration("12:16:03"));
        Assert.assertEquals(991542000L, DateTimeUtility.durationStampToDuration("275:25:42"));
        Assert.assertEquals(1542000L, DateTimeUtility.durationStampToDuration("00:25:42"));
        Assert.assertEquals(1542000L, DateTimeUtility.durationStampToDuration("00:25:42"));
        Assert.assertEquals(542000L, DateTimeUtility.durationStampToDuration("00:09:02"));
        Assert.assertEquals(1000L, DateTimeUtility.durationStampToDuration("00:00:01"));
        Assert.assertEquals(10000L, DateTimeUtility.durationStampToDuration("00:00:10"));
        Assert.assertEquals(59000L, DateTimeUtility.durationStampToDuration("00:00:59"));
        Assert.assertEquals(60000L, DateTimeUtility.durationStampToDuration("00:01:00"));
        Assert.assertEquals(3599000L, DateTimeUtility.durationStampToDuration("00:59:59"));
        Assert.assertEquals(3600000L, DateTimeUtility.durationStampToDuration("01:00:00"));
        Assert.assertEquals(-44163000L, DateTimeUtility.durationStampToDuration("-12:16:03"));
        Assert.assertEquals(-991542000L, DateTimeUtility.durationStampToDuration("-275:25:42"));
        Assert.assertEquals(-1542000L, DateTimeUtility.durationStampToDuration("-00:25:42"));
        Assert.assertEquals(-1542000L, DateTimeUtility.durationStampToDuration("-00:25:42"));
        Assert.assertEquals(-542000L, DateTimeUtility.durationStampToDuration("-00:09:02"));
        Assert.assertEquals(-1000L, DateTimeUtility.durationStampToDuration("-00:00:01"));
        Assert.assertEquals(-10000L, DateTimeUtility.durationStampToDuration("-00:00:10"));
        Assert.assertEquals(-59000L, DateTimeUtility.durationStampToDuration("-00:00:59"));
        Assert.assertEquals(-60000L, DateTimeUtility.durationStampToDuration("-00:01:00"));
        Assert.assertEquals(-3599000L, DateTimeUtility.durationStampToDuration("-00:59:59"));
        Assert.assertEquals(-3600000L, DateTimeUtility.durationStampToDuration("-01:00:00"));
        Assert.assertEquals(0L, DateTimeUtility.durationStampToDuration("00:00:00"));
        Assert.assertEquals(0L, DateTimeUtility.durationStampToDuration("-00:00:00"));
        
        //abbreviated, no milliseconds
        Assert.assertEquals(44163000L, DateTimeUtility.durationStampToDuration("12:16:03"));
        Assert.assertEquals(991542000L, DateTimeUtility.durationStampToDuration("275:25:42"));
        Assert.assertEquals(1542000L, DateTimeUtility.durationStampToDuration("25:42"));
        Assert.assertEquals(1542000L, DateTimeUtility.durationStampToDuration("25:42"));
        Assert.assertEquals(542000L, DateTimeUtility.durationStampToDuration("9:02"));
        Assert.assertEquals(1000L, DateTimeUtility.durationStampToDuration("1"));
        Assert.assertEquals(10000L, DateTimeUtility.durationStampToDuration("10"));
        Assert.assertEquals(59000L, DateTimeUtility.durationStampToDuration("59"));
        Assert.assertEquals(60000L, DateTimeUtility.durationStampToDuration("1:00"));
        Assert.assertEquals(3599000L, DateTimeUtility.durationStampToDuration("59:59"));
        Assert.assertEquals(3600000L, DateTimeUtility.durationStampToDuration("1:00:00"));
        Assert.assertEquals(-44163000L, DateTimeUtility.durationStampToDuration("-12:16:03"));
        Assert.assertEquals(-991542000L, DateTimeUtility.durationStampToDuration("-275:25:42"));
        Assert.assertEquals(-1542000L, DateTimeUtility.durationStampToDuration("-25:42"));
        Assert.assertEquals(-1542000L, DateTimeUtility.durationStampToDuration("-25:42"));
        Assert.assertEquals(-542000L, DateTimeUtility.durationStampToDuration("-9:02"));
        Assert.assertEquals(-1000L, DateTimeUtility.durationStampToDuration("-1"));
        Assert.assertEquals(-10000L, DateTimeUtility.durationStampToDuration("-10"));
        Assert.assertEquals(-59000L, DateTimeUtility.durationStampToDuration("-59"));
        Assert.assertEquals(-60000L, DateTimeUtility.durationStampToDuration("-1:00"));
        Assert.assertEquals(-3599000L, DateTimeUtility.durationStampToDuration("-59:59"));
        Assert.assertEquals(-3600000L, DateTimeUtility.durationStampToDuration("-1:00:00"));
        Assert.assertEquals(0L, DateTimeUtility.durationStampToDuration("0"));
        Assert.assertEquals(0L, DateTimeUtility.durationStampToDuration("-0"));
    }
    
    /**
     * JUnit test of validDate.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#validDate(int, int, int)
     * @see DateTimeUtility#validDate(String)
     */
    @Test
    public void testValidDate() throws Exception {
        //valid date string
        Assert.assertTrue(DateTimeUtility.validDate("2017-03-16"));
        Assert.assertTrue(DateTimeUtility.validDate("2025-01-01"));
        Assert.assertTrue(DateTimeUtility.validDate("1967-12-23"));
        Assert.assertTrue(DateTimeUtility.validDate("1732-02-12"));
        
        //year edge case
        Assert.assertTrue(DateTimeUtility.validDate("10546-01-01"));
        Assert.assertTrue(DateTimeUtility.validDate("1-01-01"));
        
        //invalid day in month
        Assert.assertFalse(DateTimeUtility.validDate("2000-02-30"));
        Assert.assertFalse(DateTimeUtility.validDate("2001-02-29"));
        Assert.assertFalse(DateTimeUtility.validDate("1940-09-31"));
        
        //invalid ranges
        Assert.assertFalse(DateTimeUtility.validDate("0-01-01"));
        Assert.assertFalse(DateTimeUtility.validDate("-154-01-01"));
        Assert.assertFalse(DateTimeUtility.validDate("2000-00-01"));
        Assert.assertFalse(DateTimeUtility.validDate("2000--11-01"));
        Assert.assertFalse(DateTimeUtility.validDate("2000-34-01"));
        Assert.assertFalse(DateTimeUtility.validDate("2000-01-00"));
        Assert.assertFalse(DateTimeUtility.validDate("2000-01--11"));
        Assert.assertFalse(DateTimeUtility.validDate("2000-01-57"));
        
        //invalid date string
        Assert.assertFalse(DateTimeUtility.validDate("March 16, 2017"));
        Assert.assertFalse(DateTimeUtility.validDate("a date"));
        
        //valid date
        Assert.assertTrue(DateTimeUtility.validDate(3, 16, 2017));
        Assert.assertTrue(DateTimeUtility.validDate(1, 1, 2025));
        Assert.assertTrue(DateTimeUtility.validDate(12, 23, 1967));
        Assert.assertTrue(DateTimeUtility.validDate(2, 12, 1732));
        
        //year edge case
        Assert.assertTrue(DateTimeUtility.validDate(1, 1, 10546));
        Assert.assertTrue(DateTimeUtility.validDate(1, 1, 1));
        
        //invalid day in month
        Assert.assertFalse(DateTimeUtility.validDate(2, 30, 2000));
        Assert.assertFalse(DateTimeUtility.validDate(2, 29, 2001));
        Assert.assertFalse(DateTimeUtility.validDate(9, 31, 1940));
        
        //invalid ranges
        Assert.assertFalse(DateTimeUtility.validDate(1, 1, 0));
        Assert.assertFalse(DateTimeUtility.validDate(1, 1, -154));
        Assert.assertFalse(DateTimeUtility.validDate(0, 1, 2000));
        Assert.assertFalse(DateTimeUtility.validDate(-11, 1, 2000));
        Assert.assertFalse(DateTimeUtility.validDate(34, 1, 2000));
        Assert.assertFalse(DateTimeUtility.validDate(1, 0, 2000));
        Assert.assertFalse(DateTimeUtility.validDate(1, -11, 2000));
        Assert.assertFalse(DateTimeUtility.validDate(1, 57, 2000));
    }
    
    /**
     * JUnit test of validTime.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#validTime(int, int, int)
     * @see DateTimeUtility#validTime(String)
     */
    @Test
    public void testValidTime() throws Exception {
        //valid time string
        Assert.assertTrue(DateTimeUtility.validTime("11:15"));
        Assert.assertTrue(DateTimeUtility.validTime("23:15"));
        Assert.assertTrue(DateTimeUtility.validTime("00:59"));
        Assert.assertTrue(DateTimeUtility.validTime("00:00"));
        Assert.assertTrue(DateTimeUtility.validTime("23:59"));
        Assert.assertTrue(DateTimeUtility.validTime("15:08"));
        Assert.assertTrue(DateTimeUtility.validTime("13:01"));
        
        //seconds
        Assert.assertTrue(DateTimeUtility.validTime("11:15:45"));
        Assert.assertTrue(DateTimeUtility.validTime("23:15:01"));
        Assert.assertTrue(DateTimeUtility.validTime("00:59:00"));
        Assert.assertTrue(DateTimeUtility.validTime("00:00:00"));
        Assert.assertTrue(DateTimeUtility.validTime("23:59:00"));
        Assert.assertTrue(DateTimeUtility.validTime("15:08:59"));
        Assert.assertTrue(DateTimeUtility.validTime("13:01:58"));
        
        //invalid ranges
        Assert.assertFalse(DateTimeUtility.validTime("-1:15"));
        Assert.assertFalse(DateTimeUtility.validTime("1:15"));
        Assert.assertFalse(DateTimeUtility.validTime("24:15"));
        Assert.assertFalse(DateTimeUtility.validTime("124:59"));
        Assert.assertFalse(DateTimeUtility.validTime("00:-1"));
        Assert.assertFalse(DateTimeUtility.validTime("00:9"));
        Assert.assertFalse(DateTimeUtility.validTime("23:78"));
        Assert.assertFalse(DateTimeUtility.validTime("-1:15:01"));
        Assert.assertFalse(DateTimeUtility.validTime("1:15:45"));
        Assert.assertFalse(DateTimeUtility.validTime("24:15:45"));
        Assert.assertFalse(DateTimeUtility.validTime("124:59:31"));
        Assert.assertFalse(DateTimeUtility.validTime("00:-1:00"));
        Assert.assertFalse(DateTimeUtility.validTime("00:9:00"));
        Assert.assertFalse(DateTimeUtility.validTime("23:78:44"));
        Assert.assertFalse(DateTimeUtility.validTime("11:15:-1"));
        Assert.assertFalse(DateTimeUtility.validTime("23:15:99"));
        
        //invalid time string
        Assert.assertFalse(DateTimeUtility.validTime("11:15 AM"));
        Assert.assertFalse(DateTimeUtility.validTime("a time"));
        
        //valid time
        Assert.assertTrue(DateTimeUtility.validTime(11, 15, 0));
        Assert.assertTrue(DateTimeUtility.validTime(23, 15, 0));
        Assert.assertTrue(DateTimeUtility.validTime(0, 59, 0));
        Assert.assertTrue(DateTimeUtility.validTime(0, 0, 0));
        Assert.assertTrue(DateTimeUtility.validTime(23, 59, 0));
        Assert.assertTrue(DateTimeUtility.validTime(15, 8, 0));
        Assert.assertTrue(DateTimeUtility.validTime(13, 1, 0));
        
        //seconds
        Assert.assertTrue(DateTimeUtility.validTime(11, 15, 45));
        Assert.assertTrue(DateTimeUtility.validTime(23, 15, 1));
        Assert.assertTrue(DateTimeUtility.validTime(0, 59, 0));
        Assert.assertTrue(DateTimeUtility.validTime(0, 0, 0));
        Assert.assertTrue(DateTimeUtility.validTime(23, 59, 0));
        Assert.assertTrue(DateTimeUtility.validTime(15, 8, 59));
        Assert.assertTrue(DateTimeUtility.validTime(13, 1, 59));
        
        //invalid ranges
        Assert.assertFalse(DateTimeUtility.validTime(-1, 15, 0));
        Assert.assertFalse(DateTimeUtility.validTime(24, 15, 0));
        Assert.assertFalse(DateTimeUtility.validTime(124, 59, 0));
        Assert.assertFalse(DateTimeUtility.validTime(0, -1, 0));
        Assert.assertFalse(DateTimeUtility.validTime(23, 78, 0));
        Assert.assertFalse(DateTimeUtility.validTime(-1, 15, 1));
        Assert.assertFalse(DateTimeUtility.validTime(24, 15, 45));
        Assert.assertFalse(DateTimeUtility.validTime(124, 59, 31));
        Assert.assertFalse(DateTimeUtility.validTime(23, 78, 44));
        Assert.assertFalse(DateTimeUtility.validTime(11, 15, -1));
        Assert.assertFalse(DateTimeUtility.validTime(23, 15, 99));
    }
    
    /**
     * JUnit test of validYear.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#validYear(int)
     */
    @Test
    public void testValidYear() throws Exception {
        //valid
        Assert.assertTrue(DateTimeUtility.validYear(1744));
        Assert.assertTrue(DateTimeUtility.validYear(2000));
        Assert.assertTrue(DateTimeUtility.validYear(9999));
        
        //edge case
        Assert.assertTrue(DateTimeUtility.validYear(1));
        Assert.assertTrue(DateTimeUtility.validYear(Integer.MAX_VALUE - 1));
        
        //invalid
        Assert.assertFalse(DateTimeUtility.validYear(0));
        Assert.assertFalse(DateTimeUtility.validYear(-1));
        Assert.assertFalse(DateTimeUtility.validYear(Integer.MAX_VALUE));
    }
    
    /**
     * JUnit test of validMonth.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#validMonth(int)
     */
    @Test
    public void testValidMonth() throws Exception {
        //valid
        Assert.assertTrue(DateTimeUtility.validMonth(4));
        Assert.assertTrue(DateTimeUtility.validMonth(7));
        Assert.assertTrue(DateTimeUtility.validMonth(11));
        
        //edge case
        Assert.assertTrue(DateTimeUtility.validMonth(1));
        Assert.assertTrue(DateTimeUtility.validMonth(12));
        
        //invalid
        Assert.assertFalse(DateTimeUtility.validMonth(0));
        Assert.assertFalse(DateTimeUtility.validMonth(-1));
        Assert.assertFalse(DateTimeUtility.validMonth(13));
    }
    
    /**
     * JUnit test of validDay.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#validDay(int)
     */
    @Test
    public void testValidDay() throws Exception {
        //valid
        Assert.assertTrue(DateTimeUtility.validDay(8));
        Assert.assertTrue(DateTimeUtility.validDay(12));
        Assert.assertTrue(DateTimeUtility.validDay(25));
        
        //edge case
        Assert.assertTrue(DateTimeUtility.validDay(1));
        Assert.assertTrue(DateTimeUtility.validDay(31));
        
        //invalid
        Assert.assertFalse(DateTimeUtility.validDay(0));
        Assert.assertFalse(DateTimeUtility.validDay(-1));
        Assert.assertFalse(DateTimeUtility.validDay(32));
    }
    
    /**
     * JUnit test of validHour.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#validHour(int)
     */
    @Test
    public void testValidHour() throws Exception {
        //valid
        Assert.assertTrue(DateTimeUtility.validHour(2));
        Assert.assertTrue(DateTimeUtility.validHour(7));
        Assert.assertTrue(DateTimeUtility.validHour(11));
        
        //edge case
        Assert.assertTrue(DateTimeUtility.validHour(0));
        Assert.assertTrue(DateTimeUtility.validHour(23));
        
        //invalid
        Assert.assertFalse(DateTimeUtility.validHour(-1));
        Assert.assertFalse(DateTimeUtility.validHour(24));
    }
    
    /**
     * JUnit test of validMinute.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#validMinute(int)
     */
    @Test
    public void testValidMinute() throws Exception {
        //valid
        Assert.assertTrue(DateTimeUtility.validMinute(2));
        Assert.assertTrue(DateTimeUtility.validMinute(34));
        Assert.assertTrue(DateTimeUtility.validMinute(51));
        
        //edge case
        Assert.assertTrue(DateTimeUtility.validMinute(0));
        Assert.assertTrue(DateTimeUtility.validMinute(59));
        
        //invalid
        Assert.assertFalse(DateTimeUtility.validMinute(-1));
        Assert.assertFalse(DateTimeUtility.validMinute(60));
    }
    
    /**
     * JUnit test of validSecond.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#validSecond(int)
     */
    @Test
    public void testValidSecond() throws Exception {
        //valid
        Assert.assertTrue(DateTimeUtility.validSecond(2));
        Assert.assertTrue(DateTimeUtility.validSecond(34));
        Assert.assertTrue(DateTimeUtility.validSecond(51));
        
        //edge case
        Assert.assertTrue(DateTimeUtility.validSecond(0));
        Assert.assertTrue(DateTimeUtility.validSecond(59));
        
        //invalid
        Assert.assertFalse(DateTimeUtility.validSecond(-1));
        Assert.assertFalse(DateTimeUtility.validSecond(60));
    }
    
}
