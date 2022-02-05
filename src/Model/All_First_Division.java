package Model;

import DAO.UserDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class All_First_Division {

    private static ObservableList<First_Division> allFirst_Division = FXCollections.observableArrayList();

    /**
     *
     * @param First_Division the part add to all first division array list
     */
    public static void addFirst_Division(First_Division First_Division) {
        allFirst_Division.add(First_Division);
    }

    /**
     *
     * @return the observable list of all the all first division
     */
    public static ObservableList<First_Division> getAllFirst_Division() {
        return allFirst_Division;
    }

    /**
     *
     * @return clear the observable list of all first division
     */
    public static void clearAllFirst_Division() {
        allFirst_Division.clear();
    }

    /**
     *
     * @return refresh the observable list of all first division
     */
    public static void refreshAllCustomers() throws Exception {
        clearAllFirst_Division();
        UserDaoImpl.SqlAllFirst_Division();
    }

}
