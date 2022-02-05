package Model;

public class Contact {
    private int contact_id;
    private String contact_name;


    public Contact(int contact_id, String contact_name) {
        this.contact_id = contact_id;
        this.contact_name = contact_name;

    }

    /**
     * @return the contact id
     */
    public int getContact_Id () {
        return contact_id;
    }

    /**
     * @param contact_id the contact id to set
     */
    public void setContact_Id(int contact_id) {
        this.contact_id =  contact_id;
    }

    /**
     * @return the contact name
     */
    public String getContact_Name () {
        return contact_name;
    }

    /**
     * @param contact_name the contact name to set
     */
    public void setContact_Name(String contact_name) {
        this.contact_name =  contact_name;
    }


    @Override
    public String toString() {
        return ("#" + Integer.toString(contact_id) + " " + contact_name);
    }

}
