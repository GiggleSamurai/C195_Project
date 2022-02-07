/**
 * @class All_First_Division.java
 * @author Louis Wong
 */

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
    public static void refreshFirst_Division() throws Exception {
        clearAllFirst_Division();
        UserDaoImpl.SqlAllFirst_Division();
    }

    /**
     *
     * @param First_DivisionID the first division ID
     * @return the index of the first division ID
     */
    public static int lookupFirst_Division(int First_DivisionID) {
        int First_DivisionArrayIndex = -1;

        for(First_Division foundObj : allFirst_Division){
            First_DivisionArrayIndex += 1;
            if (foundObj.getDivision_Id() == First_DivisionID) {
                return First_DivisionArrayIndex;
            }
        }
        return 0;
    }

}
