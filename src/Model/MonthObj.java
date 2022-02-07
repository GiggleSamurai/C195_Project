/**
 * @class MonthObj.java
 * @author Louis Wong
 */

package Model;

public class MonthObj {
    private static int jan = 0;
    private static int feb = 0;
    private static int mar = 0;
    private static int apr = 0;
    private static int may = 0;
    private static int jun = 0;
    private static int jul = 0;
    private static int aug = 0;
    private static int sep = 0;
    private static int oct = 0;
    private static int nov = 0;
    private static int dec = 0;

    /**
     *
     * @param month the month that add to
     */
    public static void add1to(int month){
        if (month == 1){
            jan += 1;
        }
        else if (month == 2) {
            feb += 1;
        }
        else if (month == 3) {
            mar += 1;
        }
        else if (month == 4) {
            apr += 1;
        }
        else if (month == 5) {
            may += 1;
        }
        else if (month == 6) {
            jun += 1;
        }
        else if (month == 7) {
            jul += 1;
        }
        else if (month == 8) {
            aug += 1;
        }
        else if (month == 9) {
            sep += 1;
        }
        else if (month == 10) {
            oct += 1;
        }
        else if (month == 11) {
            nov += 1;
        }
        else if (month == 12) {
            dec += 1;
        }
    }

    /**
     * clear the count to 0
     */
    public static void clearCount(){
        jan = 0;
        feb = 0;
        mar = 0;
        apr = 0;
        may = 0;
        jun = 0;
        jul = 0;
        aug = 0;
        sep = 0;
        oct = 0;
        nov = 0;
        dec = 0;
    }

    /**
     *
     * @return string of counted report
     */
    public static String getResult(){
        String resultString = "-------------------- Total Numbers of Customer Appointment By Month --------------------";
        resultString += "\nJanuary: " + Integer.toString(jan);
        resultString += "\nFebruary: " + Integer.toString(feb);
        resultString += "\nMarch: " + Integer.toString(mar);
        resultString += "\nApril: " + Integer.toString(apr);
        resultString += "\nMay: " + Integer.toString(may);
        resultString += "\nJune: " + Integer.toString(jun);
        resultString += "\nJuly: " + Integer.toString(jul);
        resultString += "\nAugust: " + Integer.toString(aug);
        resultString += "\nSeptember: " + Integer.toString(sep);
        resultString += "\nOctober: " + Integer.toString(oct);
        resultString += "\nNovember: " + Integer.toString(nov);
        resultString += "\nDecember: " + Integer.toString(dec);

        return resultString;
    }
}
