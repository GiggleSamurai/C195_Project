/**
 * @class Customer.java
 * @author Louis Wong
 */

package Model;

public class Customer {
    private int customer_id;
    private String customer_name;
    private String address;
    private String postal_code;
    private String phone;
    private int division_id;
    private String division;
    private String country;

    public Customer(int customer_id, String customer_name, String address, String postal_code, String phone,String country ,String division,int division_id) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.address = address;
        this.postal_code = postal_code;
        this.phone = phone;
        this.division_id = division_id;
        this.division = division;
        this.country = country;

    }

    /**
     * @return the customer_id
     */
    public int getCustomer_Id () {
        return customer_id;
    }

    /**
     * @param customer_id the customer_id to set
     */
    public void setCustomer_Id(int customer_id) {
        this.customer_id =  customer_id;
    }

    /**
     * @return the customer_name
     */
    public String getCustomer_Name () {
        return customer_name;
    }

    /**
     * @param customer_name the customer_name to set
     */
    public void setCustomer_Name(String customer_name) {
        this.customer_name =  customer_name;
    }

    /**
     * @return the address
     */
    public String getAddress () {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address =  address;
    }

    /**
     * @return the postal_code
     */
    public String getPostal_Code () {
        return postal_code;
    }

    /**
     * @param postal_code the postal_code to set
     */
    public void setPostal_Code(String postal_code) {
        this.postal_code =  postal_code;
    }

    /**
     * @return the phone
     */
    public String getPhone () {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone =  phone;
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
     * @return the division
     */
    public String getDivision () {
        return division;
    }

    /**
     * @param division the division to set
     */
    public void setDivision(String division) {
        this.division =  division;
    }

    /**
     * @return the country
     */
    public String getCountry () {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country =  country;
    }

}
