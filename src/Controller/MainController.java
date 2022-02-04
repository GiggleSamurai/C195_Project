package Controller;

import DAO.UserDaoImpl;
import Model.All_Appointments;
import Model.All_Customers;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController  implements Initializable {
    public TableView CustomersTable;
    public TableColumn Customer_ID;
    public TableColumn Customer_Name;
    public TableColumn Address;
    public TableColumn Postal_Code;
    public TableColumn Phone;
    public TableColumn Division_ID;

    public TableView AppointmentsTable;
    public TableColumn Appointment_ID;
    public TableColumn Title;
    public TableColumn Description;
    public TableColumn Location;
    public TableColumn Contact;
    public TableColumn Type;
    public TableColumn Start;
    public TableColumn End;
    public TableColumn appointmentCustomer_ID;
    public TableColumn User_ID;

    /**
     * Initialize when main form FXML is load
     */
    public void initialize(URL location, ResourceBundle resources){
        try {
            UserDaoImpl.SqlAllCustomers();
            UserDaoImpl.SqlAllAppointments();
        } catch (Exception e) {
            e.printStackTrace();
        }
        CustomersTable.setItems(All_Customers.getAllCustomers());
        Customer_ID.setCellValueFactory(new PropertyValueFactory<>("customer_Id"));
        Customer_Name.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
        Address.setCellValueFactory(new PropertyValueFactory<>("address"));
        Postal_Code.setCellValueFactory(new PropertyValueFactory<>("postal_Code"));
        Phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        Division_ID.setCellValueFactory(new PropertyValueFactory<>("division_Id"));

        AppointmentsTable.setItems(All_Appointments.getAllAppointments());
        Appointment_ID.setCellValueFactory(new PropertyValueFactory<>("appointment_ID"));
        Title.setCellValueFactory(new PropertyValueFactory<>("title"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Location.setCellValueFactory(new PropertyValueFactory<>("location"));
        Contact.setCellValueFactory(new PropertyValueFactory<>("contact_Name"));
        Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        Start.setCellValueFactory(new PropertyValueFactory<>("start_Datetime"));
        End.setCellValueFactory(new PropertyValueFactory<>("end_Datetime"));
        appointmentCustomer_ID.setCellValueFactory(new PropertyValueFactory<>("customer_Id"));
        User_ID.setCellValueFactory(new PropertyValueFactory<>("user_Id"));


    }
}
