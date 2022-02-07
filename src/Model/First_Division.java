/**
 * @class First_Division.java
 * @author Louis Wong
 */

package Model;

public class First_Division {
    private int division_id;
    private String division;


    public First_Division(int division_id, String division) {
        this.division_id = division_id;
        this.division = division;

    }

    /**
     * @return the division_id
     */
    public int getDivision_Id () {
        return division_id;
    }

    /**
     * @param division_id the division_id to set
     */
    public void setDivision_Id(int division_id) {
        this.division_id =  division_id;
    }

    /**
     * @return the country_name
     */
    public String getCountry_Name () {
        return division;
    }

    /**
     * @param division the division to set
     */
    public void setCountry_Name(String division) {
        this.division =  division;
    }

    /**
     *
     * @return override the string method, to display properly in the combo box
     */
    @Override
    public String toString() {
        return ("#" + Integer.toString(division_id) + " " + division);
    }

}
