/**
 * @class Appointment.java
 * @author Louis Wong
 */
package Model;

import java.time.LocalDateTime;


public class Appointment {
    private int appointment_id;
    private String title;
    private String description;
    private String location;
    private String contact_name;
    private String type;
    private LocalDateTime start_datetime;
    private LocalDateTime end_datetime;
    private int customer_id;
    private int user_id;

    public Appointment(int appointment_id, String title, String description, String location, String contact_name, String type, LocalDateTime start_datetime, LocalDateTime end_datetime, int customer_id, int user_id) {
        this.appointment_id = appointment_id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact_name = contact_name;
        this.type = type;
        this.start_datetime = start_datetime;
        this.end_datetime = end_datetime;
        this.customer_id = customer_id;
        this.user_id = user_id;

    }

    /**
     * @return the appointment_id
     */
    public int getAppointment_ID () {
        return appointment_id;
    }

    /**
     * @param appointment_id the appointment_id to set
     */
    public void setAppointment_ID(int appointment_id) {
        this.appointment_id =  appointment_id;
    }

    /**
     * @return the title
     */
    public String getTitle () {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title =  title;
    }

    /**
     * @return the description
     */
    public String getDescription () {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description =  description;
    }

    /**
     * @return the location
     */
    public String getLocation () {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location =  location;
    }

    /**
     * @return the contact_name
     */
    public String getContact_Name () {
        return contact_name;
    }

    /**
     * @param contact_name the contact_name to set
     */
    public void setContact_Name(String contact_name) {
        this.contact_name =  contact_name;
    }

    /**
     * @return the type
     */
    public String getType () {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type =  type;
    }

    /**
     * @return the start_datetime
     */
    public LocalDateTime getStart_Datetime () {
        return start_datetime;
    }

    /**
     * @param start_datetime the start_datetime to set
     */
    public void setStart_Datetime(LocalDateTime start_datetime) {
        this.start_datetime =  start_datetime;
    }

    /**
     * @return the end_datetime
     */
    public LocalDateTime getEnd_Datetime () {
        return end_datetime;
    }

    /**
     * @param end_datetime the end_datetime to set
     */
    public void setEnd_Datetime(LocalDateTime end_datetime) {
        this.end_datetime =  end_datetime;
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
     * @return the user_id
     */
    public int getUser_Id () {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_Id(int user_id) {
        this.user_id =  user_id;
    }

    /**
     *
     * @param setStartDateTime the start datetime
     * @return boolean statement if is overlap this appointment
     */
    public boolean isOverlap(LocalDateTime setStartDateTime) {
        if (setStartDateTime.isAfter(start_datetime) && setStartDateTime.isBefore(end_datetime) || setStartDateTime.equals(start_datetime)) {
            return true;
        }
        return false;
    }


}
