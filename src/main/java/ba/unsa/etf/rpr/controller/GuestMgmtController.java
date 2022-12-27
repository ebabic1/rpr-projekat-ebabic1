package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class GuestMgmtController {
    public Button editButton;
    public Button addButton;
    public TextField searchTextField;
    public Button deleteButton;
    public TableColumn<User,String> idColumn;
    public TableColumn<User,String> firstNameColumn;
    public TableColumn<User,String> lastNameColumn;
    public TableColumn<User,String> cityColumn;
    public TableColumn<User,String> countryColumn;
    public TableColumn<User,String> emailColumn;
    public TableColumn<User,String> phoneColumn;
    public TableView guestsTabela;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<User,String>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("lastName"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<User,String>("city"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<User,String>("country"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<User,String>("phone"));
        refreshGuests();
    }
    public void createUpdateGuest(int id){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adduser.fxml"));
            Parent root = loader.load();
            stage.setTitle("Add a guest");
            stage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        }catch (Exception e) {
            new Alert(Alert.AlertType.NONE,e.getMessage(),ButtonType.OK).show();
        }
    }
    public void addOpenWindow(ActionEvent actionEvent) throws IOException {

    }
    private void refreshGuests(){
        guestsTabela.setItems(FXCollections.observableList(DaoFactory.userDao().getAll()));
    }
}
