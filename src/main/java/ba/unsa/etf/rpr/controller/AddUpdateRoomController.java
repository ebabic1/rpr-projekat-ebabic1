package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.RoomException;
import ba.unsa.etf.rpr.model.RoomModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.NumberStringConverter;
/**
 * JavaFX controller class for creation and alteration of Room objects
 * @author Eldar Babić
 */
public class AddUpdateRoomController {

    public Button okButton;
    public Button cancelButton;
    public TextField priceField;
    public TextField maxPersonsField;
    public TextField roomNumberField;
    public TextArea descriptionField;
    public GridPane addupdateGridPane;
    private Integer rId;
    private RoomModel roomModel = new RoomModel();
    private RoomManager roomManager = new RoomManager();
    @FXML
    public void initialize() {
        Bindings.bindBidirectional(priceField.textProperty(),roomModel.priceFieldProperty(),new NumberStringConverter());
        maxPersonsField.textProperty().bindBidirectional(roomModel.maxPersonsFieldProperty(),new NumberStringConverter());
        descriptionField.textProperty().bindBidirectional(roomModel.descriptionFieldProperty());
        roomNumberField.textProperty().bindBidirectional(roomModel.roomNumberFieldProperty(),new NumberStringConverter());
        if (rId != null) {
            try {
                roomModel.fromRoom(roomManager.getById(rId));
            } catch (RoomException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage(), ButtonType.OK).show();
            }
        }
    }
    public AddUpdateRoomController(Integer rId){this.rId = rId;}
    public Integer getrId() {
        return rId;
    }

    /**
     * OK button event handler
     * @param actionEvent
     */
    public void okPressed(ActionEvent actionEvent) {
        Room r = roomModel.toRoom();
        try {
            if(rId != null){
                r.setId(rId);
                roomManager.update(r);
            }
            else {
                r.setAvailable(1);
                roomManager.add(r);
            }
            addupdateGridPane.getScene().getWindow().hide();
        } catch (RoomException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * cancel button event handler
     * @param actionEvent
     */
    public void cancelPressed(ActionEvent actionEvent) {
        addupdateGridPane.getScene().getWindow().hide();
    }
}
