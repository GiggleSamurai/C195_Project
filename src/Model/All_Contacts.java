package Model;

import DAO.UserDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class All_Contacts {

    private static ObservableList<Contact> allContact = FXCollections.observableArrayList();

    /**
     *
     * @param Contact the part add to all contact array list
     */
    public static void addContact(Contact Contact) {
        allContact.add(Contact);
    }

    /**
     *
     * @return the observable list of all the all contact
     */
    public static ObservableList<Contact> getAllContact() {
        return allContact;
    }

    /**
     *
     * @return clear the observable list of all contact
     */
    public static void clearAllContact() {
        allContact.clear();
    }

    /**
     *
     * @return refresh the observable list of all contact
     */
    public static void refreshContact() throws Exception {
        clearAllContact();
        UserDaoImpl.SqlAllContact();
    }

    public static int lookupContact(int ContactID) {
        int ContactArrayIndex = -1;

        for(Contact foundObj : allContact){
            ContactArrayIndex += 1;
            if (foundObj.getContact_Id() == ContactID) {
                return ContactArrayIndex;
            }
        }
        return 0;
    }

}
