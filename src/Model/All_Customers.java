package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class All_Customers {
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    /**
     *
     * @param Customer the part add to all customers array list
     */
    public static void addCustomer(Customer Customer) {
        allCustomers.add(Customer);
    }

    /**
     *
     * @return the observable list of all the all customers
     */
    public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }

    /**
     *
     * @return clear the observable list of all customers
     */
    public static void clearAllCustomers() {
        allCustomers.clear();
    }

}
