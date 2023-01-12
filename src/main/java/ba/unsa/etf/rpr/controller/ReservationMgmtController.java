package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.exceptions.ReservationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class ReservationMgmtController {
    public Button editButton;
    public Button addButton;
    public Button deleteButton;
    public TextField searchTextField;
    public TableView reservationsTable;
    public TableColumn idColumn;
    public TableColumn arrivalDateColumn;
    public TableColumn leaveDateColumn;
    public TableColumn additionalInfoColumn;
    public TableColumn userIdColumn;
    public TableColumn roomIdColumn;
    private ReservationManager reservationManager = new ReservationManager();
    private void addupdateScene(Integer rId, Integer uId){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addreservation.fxml"));
            loader.setController(new AddUpdateReservationController(rId,uId));
            Parent root = loader.load();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.setOnHiding(event -> {
                refreshReservations();
            } );
            if(rId != null) {
                stage.setTitle("Edit a reservation");
            }
            else stage.setTitle("Add a reservation");
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void refreshReservations() {
        try {
            if(searchTextField.getText().trim().isEmpty()) reservationsTable.setItems(FXCollections.observableList(DaoFactory.reservationDao().getAll()));
            else {
                ObservableList<Reservation> observableList = FXCollections.observableArrayList();
                try {
                    Integer broj = Integer.parseInt(searchTextField.getText());
                    try {
                        Reservation reservation = DaoFactory.reservationDao().getById(broj);
                        observableList.add(reservation);

                    } catch (SQLException e) {

                    }
                    reservationsTable.setItems(observableList);
                } catch (NumberFormatException e) {

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        arrivalDateColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalDate"));
        leaveDateColumn.setCellValueFactory(new PropertyValueFactory<>("leaveDate"));
        additionalInfoColumn.setCellValueFactory(new PropertyValueFactory<>("additionalInfo"));
        roomIdColumn.setCellValueFactory(new PropertyValueFactory<>("room"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
        refreshReservations();

    }
    public void editButtonPressed(ActionEvent actionEvent) {
        Reservation selectedReservation = (Reservation) reservationsTable.getSelectionModel().getSelectedItem();
        Integer rId = selectedReservation.getId();
        Integer uId = selectedReservation.getUser().getId();
        if(rId != null){
            addupdateScene(rId,uId);
        }
    }

    public void addButtonPressed(ActionEvent actionEvent) {

    }

    public void deleteButtonPressed(ActionEvent actionEvent) {
        Reservation selectedReservation = (Reservation) reservationsTable.getSelectionModel().getSelectedItem();
        Integer rId = selectedReservation.getId();
        try {
            reservationManager.delete(rId);
            refreshReservations();
        } catch (ReservationException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
        }
    }

    public void searchButtonPressed(ActionEvent actionEvent) {
        refreshReservations();
    }
}
