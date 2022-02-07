/**
 * @class ViewByWeekController.java
 * @author Louis Wong
 */
package Controller;

import DAO.UserDaoImpl;
import Model.All_Appointments;
import Model.Appointment;
import Model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ViewByWeekController  implements Initializable {

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
    public ToggleGroup MonthRadioGroup;

    /**
     * Initialize elements when this FXML form is load
     */
    public void initialize(URL location, ResourceBundle resources){
        try {
            UserDaoImpl.SqlAllAppointments();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            AppointmentsTable.setItems(All_Appointments.getThisWeekdayAppointments(1));
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    /**
     *
     * @param actionEvent radio button of week day to filter appointment on
     * @throws Exception
     */
    public void day1Button(ActionEvent actionEvent) throws Exception {
        AppointmentsTable.setItems(All_Appointments.getThisWeekdayAppointments(1));

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

    /**
     *
     * @param actionEvent radio button of week day to filter appointment on
     * @throws Exception
     */
    public void day2Button(ActionEvent actionEvent) throws Exception {
        AppointmentsTable.setItems(All_Appointments.getThisWeekdayAppointments(2));
    }

    /**
     *
     * @param actionEvent radio button of week day to filter appointment on
     * @throws Exception
     */
    public void day3Button(ActionEvent actionEvent) throws Exception {
        AppointmentsTable.setItems(All_Appointments.getThisWeekdayAppointments(3));
    }

    /**
     *
     * @param actionEvent radio button of week day to filter appointment on
     * @throws Exception
     */
    public void day4Button(ActionEvent actionEvent) throws Exception {
        AppointmentsTable.setItems(All_Appointments.getThisWeekdayAppointments(4));
    }

    /**
     *
     * @param actionEvent radio button of week day to filter appointment on
     * @throws Exception
     */
    public void day5Button(ActionEvent actionEvent) throws Exception {
        AppointmentsTable.setItems(All_Appointments.getThisWeekdayAppointments(5));
    }

    /**
     *
     * @param actionEvent radio button of week day to filter appointment on
     * @throws Exception
     */
    public void day6Button(ActionEvent actionEvent) throws Exception {
        AppointmentsTable.setItems(All_Appointments.getThisWeekdayAppointments(6));
    }

    /**
     *
     * @param actionEvent radio button of week day to filter appointment on
     * @throws Exception
     */
    public void day7Button(ActionEvent actionEvent) throws Exception {
        AppointmentsTable.setItems(All_Appointments.getThisWeekdayAppointments(7));
    }

    /**
     *
     * @param actionEvent load filter by month scene
     * @throws IOException
     */
    public void ViewByMonth_Button(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/ViewByMonth.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setX(450);
        stage.setY(150);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @param actionEvent load main scene
     * @throws IOException
     */
    public void BacktoMain_Button(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setX(450);
        stage.setY(150);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
