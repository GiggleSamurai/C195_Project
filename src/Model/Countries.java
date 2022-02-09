/**
 * @class Countries.java
 * @author Louis Wong
 */

package Model;

public class Countries {
    private int country_id;
    private String country;


    public Countries(int country_id, String country) {
        this.country_id = country_id;
        this.country = country;

    }

    /**
     * @return the country_id
     */
    public int getCountry_Id () {
        return country_id;
    }

    /**
     * @param country_id the country_id to set
     */
    public void setCountry_Id(int country_id) {
        this.country_id =  country_id;
    }

    /**
     * @return the country_name
     */
    public String getCountry_Name () {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry_Name(String country) {
        this.country =  country;
    }

    /**
     *
     * @return override the string method, to display properly in the combo box
     */
    @Override
    public String toString() {
        return ("#" + Integer.toString(country_id) + " " + country);
    }

}
