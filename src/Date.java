package collectionmanager;
import java.util.Calendar;
/**
 * Represents a date with the day, month, and year
 * This class is uses a date input by the user to determine if the date is valid based on the boundaries.
 * this class is also used to compare dates and return which comes before or after.
 * @author Joon Song, Connor Powell
 */
public class Date implements Comparable<Date>{
    private int day;
    private int month;
    private int year;

    private final int JANUARY = 1;
    private final int FEBRUARY = 2;
    private final int MARCH = 3;
    private final int APRIL = 4;
    private final int MAY = 5;
    private final int JUNE = 6;
    private final int JULY = 7;
    private final int AUGUST = 8;
    private final int SEPTEMBER = 9;
    private final int OCTOBER = 10;
    private final int NOVEMBER = 11;
    private final int DECEMBER = 12;

    Calendar today = Calendar.getInstance();
    int currYear = today.get(Calendar.YEAR);
    int currMonth = today.get(Calendar.MONTH) + 1;
    int currDayMonth = today.get(Calendar.DAY_OF_MONTH);


    /**
     * Constructor for the date class
     * @param day of the month
     * @param month of the year
     * @param year that it took place
     */
    public Date(int month, int day, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Checks to see if date is a valid date.
     * @return true if date is valid, false otherwise.
     */
    public boolean isValid() {
        boolean tr = true;
        if(year < 1900)
            return false;
        if(todayOrAfter())
            return false;
        if(month < JANUARY || month > DECEMBER)
            tr = false;
        if((month == JANUARY || month == MARCH || month == MAY || month == JULY || month == AUGUST
                || month == OCTOBER || month == DECEMBER) && (day < 1 || day > 31))
            tr = false;
        if((month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER) && (day < 1 || day > 30))
            tr = false;
        if(month == FEBRUARY) {
            boolean leapYear = isLeapYear(year);
            if(leapYear) {
                if (day < 1 || day > 29)
                    tr = false;
            }
            else {
                if (day < 1 || day > 28)
                    tr = false;
            }
        }
        return tr;
    }

    /**
     * helper method to determine if the date is today or after
     * @return true if date is after, false otherwise
     */
    private boolean todayOrAfter() {
        if (year < currYear)
            return false;
        if (year==currYear && month < currMonth)
            return false;
        if (year == currYear && month == currMonth && day < currDayMonth) {
            return false;
        }
        return true;
    }
    /**
     * Helper method to determine leap year
     * @param year to determine if leap year or not
     * @return true if leap year, false otherwise
     */
    private boolean isLeapYear(int year) {
        final int QUADRENNIAL = 4;
        final int CENTENNIAL = 100;
        final int QUADRACENTENNIAL = 400;
        boolean tr = true;
        if(year % QUADRENNIAL == 0) {
            if(year % CENTENNIAL == 0) {
                tr = year % QUADRACENTENNIAL == 0;
            }
        }
        else
            tr = false;
        return tr;
    }

    /**
     * compares 2 dates
     * @param date to be compared.
     * @return 1 if date is after, -1 if date is before, 0 otherwise
     */
    @Override
    public int compareTo(Date date) {
        if(this.year < date.year)
            return 1;
        if(this.year > date.year)
            return -1;
        else {
            if(this.month < date.month)
                return 1;
            if(this.month > date.month)
                return -1;
            else {
                if(this.day < date.day)
                    return 1;
                if(this.day > date.day)
                    return -1;
            }
        }
        return 0;
    }

    /**
     * getter method
     * @return day
     */
    public int getDay() {
        return day;
    }
    /**
     * getter method
     * @return month
     */
    public int getMonth() {
        return month;
    }
    /**
     * getter method
     * @return year
     */
    public int getYear() {
        return year;
    }
    /**
     * setter method
     * @param day new day
     */
    public void setDay(int day) {
        this.day = day;
    }
    /**
     * setter method
     * @param month new month
     */
    public void setMonth(int month) {
        this.month = month;
    }
    /**
     * setter method
     * @param year new year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * converts input date into a string
     * @return date as a string
     */
    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }


    /**
     * Testbed main
     * @param args input
     */
    public static void main(String [] args) {
        febNonLeap();
        febQuarterCentennial();
        febMillennial();
        febEveryFourYears();
        before1900();
        afterOrCurrentDay();
        testThirtyThirtyOne();
    }

    /**
     * test1
     */
    private static void febNonLeap(){
        Date date = new Date(2,29,2015);
        boolean expected = false;
        boolean actual = date.isValid();
        System.out.println("Testcase 1: # of days in February in a non-leap year is 28");
        testResult(date, expected, actual);
    }
    /**
     * test1
     */
    private static void febQuarterCentennial(){
        Date date = new Date(2,29,2000);
        boolean expected = true;
        boolean actual = date.isValid();
        System.out.println("Testcase 2: # of days in February in a quartercentennial year is 29");
        testResult(date, expected, actual);
    }
    /**
     * test2
     */
    private static void febMillennial(){
        Date date = new Date(2,29,1900);
        boolean expected = false;
        boolean actual = date.isValid();
        System.out.println("Testcase 3: # of days in February in a millennial year is 28");
        testResult(date, expected, actual);
    }
    /**
     * test3
     */
    private static void febEveryFourYears(){
        Date date = new Date(2,29,2012);
        boolean expected = true;
        boolean actual = date.isValid();
        System.out.println("Testcase 4: # of days in February every 4 years is 29");
        testResult(date, expected, actual);
    }
    /**
     * test4
     */
    private static void before1900(){
        Date date = new Date(3,16,1899);
        boolean expected = false;
        boolean actual = date.isValid();
        System.out.println("Testcase 5: # date before 1900 is not accepted");
        testResult(date, expected, actual);
    }
    /**
     * test5
     */
    private static void afterOrCurrentDay(){
        Date date = new Date(2,10,2025);
        boolean expected = false;
        boolean actual = date.isValid();
        System.out.println("Testcase 6: Can't be current date or after");
        testResult(date, expected, actual);
    }
    /**
     * test6
     */
    private static void testThirtyThirtyOne(){
        Date date = new Date(6,31,2016);
        boolean expected = false;
        boolean actual = date.isValid();
        System.out.println("Testcase 7: # of days in June is 30");
        testResult(date, expected, actual);
    }
    /**
     * test7
     */
    private static void testResult(Date date, boolean expected, boolean actual){
        System.out.println("Test input: "+ date.toString());
        System.out.println("Expected output: " + expected);
        System.out.println("Actual output: " + actual);
        if(expected!=actual){
            System.out.println("fail\n");
        }else{
            System.out.println("pass\n");
        }
    }
}
