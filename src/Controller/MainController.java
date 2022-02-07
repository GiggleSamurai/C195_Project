/**
 * @class MainController.java
 * @author Louis Wong
 */

package Controller;

import DAO.UserDaoImpl;
import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.*;
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

    public static Customer selectedCustomer;
    public static Appointment selectedAppointment;

    /**
     * Initialize elements when this FXML form is load
     * @LambdaExpression void function for initializing element for main scene
     */
    public void initialize(URL location, ResourceBundle resources){
        FunctionalInterface mainInitialize = ()-> {
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
        };

        mainInitialize.initializeLambda();

    }

    /**
     *
     * @param actionEvent load customer add scene
     * @throws IOException
     */
    public void AddCustomerButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/CustomerAdd.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @param actionEvent load update customer scene
     * @throws IOException
     */
    public void UpdateCustomerButton(ActionEvent actionEvent) throws IOException {

        selectedCustomer = (Customer) CustomersTable.getSelectionModel().getSelectedItem();
        if(selectedCustomer == null) {
            return;
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("/View/CustomerUpdate.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     *
     * @param actionEvent delete customer if there is no appointment, otherwise pop an alert
     * @throws Exception
     */
    public void DeleteCustomerButton(ActionEvent actionEvent) throws Exception {
        Customer SelectedCustomer = (Customer) CustomersTable.getSelectionModel().getSelectedItem();

        if(SelectedCustomer == null) {
            return;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Customer");
            alert.setHeaderText("Delete");
            alert.setContentText("Do you want to delete this customer?");
            if (alert.showAndWait().get()== ButtonType.OK) {
                boolean hasAppointment = false;
                for (Appointment thisAppointment : All_Appointments.getAllAppointments()){
                    if (SelectedCustomer.getCustomer_Id() == thisAppointment.getCustomer_Id()){
                        hasAppointment = true;

                    }
                }
                if (hasAppointment == false) {
                    UserDaoImpl.SqlDeleteCustomer(SelectedCustomer);

                    Parent root = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setX(450);
                    stage.setY(150);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else{
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Delete Error");
                    errorAlert.setHeaderText("Customer Delete Error");
                    errorAlert.setContentText("Cannot delete customer with appointment.");
                    errorAlert.show();
                }

            } else {return;}
        }
    }

    /**
     *
     * @param actionEvent load add appointment scene
     * @throws IOException
     */
    public void AddAppointmentButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/AppointmentAdd.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @param actionEvent load update appointment scene
     * @throws IOException
     */
    public void UpdateAppointmentButton(ActionEvent actionEvent) throws IOException {
        selectedAppointment = (Appointment) AppointmentsTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            return;
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("/View/AppointmentUpdate.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }

    /**
     *
     * @param actionEvent delete appointment if no error
     * @throws Exception
     */
    public void DeleteAppointmentButton(ActionEvent actionEvent) throws Exception {
        Appointment SelectedAppointment = (Appointment) AppointmentsTable.getSelectionModel().getSelectedItem();

        if(SelectedAppointment == null) {
            return;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Appointment");
            alert.setHeaderText("Delete");
            alert.setContentText("Do you want to delete this Appointment?");
            if (alert.showAndWait().get()== ButtonType.OK) {
                UserDaoImpl.SqlDeleteAppointment(SelectedAppointment);

                Parent root = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setX(450);
                stage.setY(150);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {return;}
        }

    }

    /**
     *
     * @param actionEvent load view schedules scene
     * @throws IOException
     */
    public void View_Schedules_Button(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/ViewByMonth.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setX(450);
        stage.setY(150);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @param actionEvent get report on information alert
     * @throws IOException
     */
    public void ReportButton(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Report");
        alert.setHeaderText("Report");
        alert.getDialogPane().setPrefSize(800,800);
        alert.setContentText(All_Appointments.getTypeReport() +
                "\n\n"+ All_Appointments.getMonthReport()+
                "\n\n"+ All_Appointments.getContactReport()+
                "\n\n"+ "-------------------- Last 10 Login Activity --------------------" +getLastLoginData(10)
                );
        alert.show();
    }

    /**
     *
     * @param lastNumberOfLine number of line last login wanted
     * @return string of login data
     * @throws IOException
     */
    public static String getLastLoginData(int lastNumberOfLine) throws IOException {
        File login_log = new File("login_activity.txt");
        BufferedReader br = new BufferedReader(new FileReader(login_log));
        String longString = "";

        String line = br.readLine();
        while (line != null) {
            longString += "\n" + line;
            line = br.readLine();
        }

        String[] stringArray = longString.split("\n");
        String outputString = "";

        for (int i = 1; i < lastNumberOfLine+1; i++) {
            try{
                if (stringArray[stringArray.length - i] != null){
                    outputString += "\n" + stringArray[stringArray.length - i];
                }
            } catch (Exception e){
                break;
            }
        }
        return outputString;
    }


}
