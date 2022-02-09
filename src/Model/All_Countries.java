/**
 * @class All_Countries.java
 * @author Louis Wong
 */

package Model;

import DAO.UserDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class All_Countries {

    private static ObservableList<Countries> all_Countries = FXCollections.observableArrayList();

    /**
     *
     * @param Country the part add to all countries array list
     */
    public static void add_Country(Countries Country) {
        all_Countries.add(Country);
    }

    /**
     *
     * @return the observable list of all the all countries
     */
    public static ObservableList<Countries> getAll_Countries() {
        return all_Countries;
    }

    /**
     *
     * @return clear the observable list of all countries
     */
    public static void clearAll_Country() {
        all_Countries.clear();
    }

    /**
     *
     * @return refresh the observable list of all countries
     */
    public static void refresh_Countries() throws Exception {
        clearAll_Country();
        UserDaoImpl.SqlAllCountries();
    }

    /**
     *
     * @param CountryID the country ID
     * @return the index of the country ID
     */
    public static int lookup_Country(int CountryID) {
        int CountryArrayIndex = -1;

        for(Countries foundObj : all_Countries){
            CountryArrayIndex += 1;
            if (foundObj.getCountry_Id() == CountryID) {
                return CountryArrayIndex;
            }
        }
        return 0;
    }

}