/*
 * File:    DateTimeUtilityTest.java
 * Package: time
 * Author:  Zachary Gill
 */

package time;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of DateTimeUtility.
 *
 * @see DateTimeUtility
 */
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
     * JUnit test of dateToDateString.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#dateToDateString(String)
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
        Assert.assertEquals("12:59 AM", DateTimeUtility.timeToTimeString("00:59:00", true));
        Assert.assertEquals("12:00 AM", DateTimeUtility.timeToTimeString("00:00:00", true));
        Assert.assertEquals("11:59 PM", DateTimeUtility.timeToTimeString("23:59:00", true));
        Assert.assertEquals("3:08:59 PM", DateTimeUtility.timeToTimeString("15:08:59", true));
        Assert.assertEquals("1:01:58 PM", DateTimeUtility.timeToTimeString("13:01:58", true));
        
        //include seconds, no seconds
        Assert.assertEquals("11:15 AM", DateTimeUtility.timeToTimeString("11:15", true));
        Assert.assertEquals("11:15 PM", DateTimeUtility.timeToTimeString("23:15", true));
        Assert.assertEquals("12:59 AM", DateTimeUtility.timeToTimeString("00:59", true));
        Assert.assertEquals("12:00 AM", DateTimeUtility.timeToTimeString("00:00", true));
        Assert.assertEquals("11:59 PM", DateTimeUtility.timeToTimeString("23:59", true));
        Assert.assertEquals("3:08 PM", DateTimeUtility.timeToTimeString("15:08", true));
        Assert.assertEquals("1:01 PM", DateTimeUtility.timeToTimeString("13:01", true));
        
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
        
        //invalid time string
        Assert.assertEquals("11:15 AM", DateTimeUtility.timeToTimeString("11:15 AM"));
        Assert.assertEquals("a time", DateTimeUtility.timeToTimeString("a time"));
    }
    
    /**
     * JUnit test of timeToMilitaryTimeString.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#timeToMilitaryTimeString(String)
     */
    @Test
    public void testTimeToMilitaryTimeString() throws Exception {
        //valid
        Assert.assertEquals("1115 hours", DateTimeUtility.timeToMilitaryTimeString("11:15"));
        Assert.assertEquals("2315 hours", DateTimeUtility.timeToMilitaryTimeString("23:15"));
        Assert.assertEquals("0059 hours", DateTimeUtility.timeToMilitaryTimeString("00:59"));
        Assert.assertEquals("0000 hours", DateTimeUtility.timeToMilitaryTimeString("00:00"));
        Assert.assertEquals("2359 hours", DateTimeUtility.timeToMilitaryTimeString("23:59"));
        Assert.assertEquals("1508 hours", DateTimeUtility.timeToMilitaryTimeString("15:08"));
        Assert.assertEquals("1301 hours", DateTimeUtility.timeToMilitaryTimeString("13:01"));
        
        //seconds
        Assert.assertEquals("1115 hours", DateTimeUtility.timeToMilitaryTimeString("11:15:45"));
        Assert.assertEquals("2315 hours", DateTimeUtility.timeToMilitaryTimeString("23:15:01"));
        Assert.assertEquals("0059 hours", DateTimeUtility.timeToMilitaryTimeString("00:59:00"));
        Assert.assertEquals("0000 hours", DateTimeUtility.timeToMilitaryTimeString("00:00:00"));
        Assert.assertEquals("2359 hours", DateTimeUtility.timeToMilitaryTimeString("23:59:00"));
        Assert.assertEquals("1508 hours", DateTimeUtility.timeToMilitaryTimeString("15:08:59"));
        Assert.assertEquals("1301 hours", DateTimeUtility.timeToMilitaryTimeString("13:01:58"));
        
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
        Assert.assertEquals("11:15:5", DateTimeUtility.timeToMilitaryTimeString("11:15:5"));
        
        //invalid second range
        Assert.assertEquals("2315 hours", DateTimeUtility.timeToMilitaryTimeString("23:15:99"));
        
        //invalid time string
        Assert.assertEquals("11:15 AM", DateTimeUtility.timeToMilitaryTimeString("11:15 AM"));
        Assert.assertEquals("a time", DateTimeUtility.timeToMilitaryTimeString("a time"));
    }
    
    /**
     * JUnit test of dayOfWeek.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#dayOfWeek(int, int, int)
     */
    @Test
    public void testDayOfWeek() throws Exception {
        //valid date string
        Assert.assertEquals(DateTimeUtility.DayOfWeek.MONDAY, DateTimeUtility.dayOfWeek("1645-05-01"));
        Assert.assertEquals(DateTimeUtility.DayOfWeek.TUESDAY, DateTimeUtility.dayOfWeek("1734-02-16"));
        Assert.assertEquals(DateTimeUtility.DayOfWeek.WEDNESDAY, DateTimeUtility.dayOfWeek("2025-01-01"));
        Assert.assertEquals(DateTimeUtility.DayOfWeek.THURSDAY, DateTimeUtility.dayOfWeek("2017-03-16"));
        Assert.assertEquals(DateTimeUtility.DayOfWeek.SATURDAY, DateTimeUtility.dayOfWeek("1967-12-23"));
        Assert.assertEquals(DateTimeUtility.DayOfWeek.SUNDAY, DateTimeUtility.dayOfWeek("1759-07-08"));
        
        //leap year
        Assert.assertEquals(DateTimeUtility.DayOfWeek.MONDAY, DateTimeUtility.dayOfWeek("1600-07-31"));
        Assert.assertEquals(DateTimeUtility.DayOfWeek.SUNDAY, DateTimeUtility.dayOfWeek("1944-12-31"));
        Assert.assertEquals(DateTimeUtility.DayOfWeek.TUESDAY, DateTimeUtility.dayOfWeek("2000-02-29"));
        Assert.assertEquals(DateTimeUtility.DayOfWeek.SUNDAY, DateTimeUtility.dayOfWeek("2488-03-14"));
        
        //year edge case
        Assert.assertEquals(DateTimeUtility.DayOfWeek.SATURDAY, DateTimeUtility.dayOfWeek("10546-01-01"));
        Assert.assertEquals(DateTimeUtility.DayOfWeek.MONDAY, DateTimeUtility.dayOfWeek("1-01-01"));
        
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
        
        //invalid date string
        Assert.assertNull(DateTimeUtility.dayOfWeek("March 16, 2017"));
        Assert.assertNull(DateTimeUtility.dayOfWeek("a date"));
        
        //valid date
        Assert.assertEquals(DateTimeUtility.DayOfWeek.MONDAY, DateTimeUtility.dayOfWeek(5, 1, 1645));
        Assert.assertEquals(DateTimeUtility.DayOfWeek.TUESDAY, DateTimeUtility.dayOfWeek(2, 16, 1734));
        Assert.assertEquals(DateTimeUtility.DayOfWeek.WEDNESDAY, DateTimeUtility.dayOfWeek(1, 1, 2025));
        Assert.assertEquals(DateTimeUtility.DayOfWeek.THURSDAY, DateTimeUtility.dayOfWeek(3, 16, 2017));
        Assert.assertEquals(DateTimeUtility.DayOfWeek.SATURDAY, DateTimeUtility.dayOfWeek(12, 23, 1967));
        Assert.assertEquals(DateTimeUtility.DayOfWeek.SUNDAY, DateTimeUtility.dayOfWeek(7, 8, 1759));
        
        //leap year
        Assert.assertEquals(DateTimeUtility.DayOfWeek.MONDAY, DateTimeUtility.dayOfWeek(7, 31, 1600));
        Assert.assertEquals(DateTimeUtility.DayOfWeek.SUNDAY, DateTimeUtility.dayOfWeek(12, 31, 1944));
        Assert.assertEquals(DateTimeUtility.DayOfWeek.TUESDAY, DateTimeUtility.dayOfWeek(2, 29, 2000));
        Assert.assertEquals(DateTimeUtility.DayOfWeek.SUNDAY, DateTimeUtility.dayOfWeek(3, 14, 2488));
        
        //year edge case
        Assert.assertEquals(DateTimeUtility.DayOfWeek.SATURDAY, DateTimeUtility.dayOfWeek(1, 1, 10546));
        Assert.assertEquals(DateTimeUtility.DayOfWeek.MONDAY, DateTimeUtility.dayOfWeek(1, 1, 1));
        
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
     * JUnit test of validDate.
     *
     * @throws Exception When there is an exception.
     * @see DateTimeUtility#validDate(int, int, int)
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
