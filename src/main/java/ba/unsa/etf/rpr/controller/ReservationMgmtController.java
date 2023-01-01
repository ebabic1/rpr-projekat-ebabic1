package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.dao.DaoFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

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
    private void refreshReservations() {
        try {
            reservationsTable.setItems(FXCollections.observableList(DaoFactory.reservationDao().getAll()));
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
        roomIdColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        refreshReservations();

    }
    public void editButtonPressed(ActionEvent actionEvent) {

    }

    public void addButtonPressed(ActionEvent actionEvent) {
    }

    public void deleteButtonPressed(ActionEvent actionEvent) {
    }
}
