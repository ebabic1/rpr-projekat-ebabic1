package ba.unsa.etf.rpr.controller;
import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.RoomException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

/**
 * JavaFX controller class for room management
 * @author Eldar Babić
 */
public class RoomMgmtController {
    public Button editButton;
    public Button addButton;
    public Button deleteButton;
    public TextField searchTextField;
    public TableView roomsTable;
    public TableColumn idColumn;
    public TableColumn priceColumn;
    public TableColumn maxPersonsColumn;
    public TableColumn descriptionColumn;
    public TableColumn availableColumn;
    public TableColumn roomNumberColumn;
    public Button searchButton;
    private Integer rId;
    private RoomManager roomManager = new RoomManager();
    @FXML
    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        maxPersonsColumn.setCellValueFactory(new PropertyValueFactory<>("maxPersons"));
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        refreshRooms();
    }

    /**
     * Opens window for room creation or alteration
     * @param rId room ID
     */
    private void addupdateScene(Integer rId) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addroom.fxml"));
            loader.setController(new AddUpdateRoomController(rId));
            Parent root = loader.load();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.setOnHiding(event -> {
                refreshRooms();
            } );
            if(rId != null) {
                stage.setTitle("Edit a room");
            }
            else stage.setTitle("Add a room");
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Edit button event handler
     * @param actionEvent
     */
    public void editButtonPressed(ActionEvent actionEvent) {
        Room selectedRoom = (Room) roomsTable.getSelectionModel().getSelectedItem();
        Integer rId = selectedRoom.getId();
        if(rId != null) {
            addupdateScene(rId);
        }
    }

    /**
     * Add button event handler
     * @param actionEvent
     */
    public void addButtonPressed(ActionEvent actionEvent) {
        addupdateScene(null);
    }

    /**
     * Delete button event handler
     * @param actionEvent
     */
    public void deleteButtonPressed(ActionEvent actionEvent) {
        Room selectedRoom = (Room) roomsTable.getSelectionModel().getSelectedItem();
        System.out.println(selectedRoom.getId());
        Integer uId = selectedRoom.getId();
        try {
            roomManager.delete(uId);
            refreshRooms();
        } catch (RoomException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
        }
    }

    /**
     * Refreshes rooms table based on search field contents
     */
    private void refreshRooms() {
        try {
            if(searchTextField.getText().trim().isEmpty()) roomsTable.setItems(FXCollections.observableList(DaoFactory.roomDao().getAll()));
            else {
                try {
                    Integer broj = Integer.parseInt(searchTextField.getText());
                    roomsTable.setItems(FXCollections.observableList(DaoFactory.roomDao().searchByNumber(broj)));

                } catch (NumberFormatException e) {

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Search button event handler
     * @param actionEvent
     */
    public void searchButtonPressed(ActionEvent actionEvent) {
        refreshRooms();
    }
}
