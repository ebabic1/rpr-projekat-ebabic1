package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.UserException;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    private List<User> userList = new ArrayList<>();
    private final UserManager userManager = new UserManager();
    private FilteredList<User> filteredList = new FilteredList<>(FXCollections.observableList(userList),b->true);
    private void exit(ActionEvent actionEvent){
        Node n = (Node) actionEvent.getSource();
        Stage s1 = (Stage) n.getScene().getWindow();
        s1.close();
    }
    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<User,String>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("lastName"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<User,String>("city"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<User,String>("country"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<User,String>("phone"));
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(user -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String keyword = newValue.toLowerCase();
                if ((user.getFirstName() + user.getLastName()).toLowerCase().contains(keyword.replaceAll(" ", "")))
                    return true;
                return false;
            });
        });
        refreshGuests();
    }

    private void addupdateScene(Integer uId){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adduser.fxml"));
            loader.setController(new AddUpdateUserController(uId));
            Parent root = loader.load();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.setOnHiding(event -> {
                refreshGuests();
            } );
            if(uId != null) {
                stage.setTitle("Edit a guest");
            }
            else stage.setTitle("Add a guest");
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void refreshGuests()  {
        try {
            userList = DaoFactory.userDao().getAll();
            //SortedList<User> userSortedList = new SortedList<>(filteredList);
            //userSortedList.comparatorProperty().bind(guestsTabela.comparatorProperty());
            guestsTabela.setItems(FXCollections.observableList(userList));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editButtonPressed(ActionEvent actionEvent) {
        User selectedGuest = (User) guestsTabela.getSelectionModel().getSelectedItem();
        Integer uId = selectedGuest.getId();
        if(uId == null) return;
        addupdateScene(uId);
    }

    public void deleteButtonPressed(ActionEvent actionEvent) {
        User selectedGuest = (User) guestsTabela.getSelectionModel().getSelectedItem();
        System.out.println(selectedGuest.getId());
        Integer uId = selectedGuest.getId();
        try {
            userManager.delete(uId);
            refreshGuests();
        } catch (UserException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void addButtonPressed(ActionEvent actionEvent) {
        addupdateScene(null);
    }

    public void checkInButtonPressed(ActionEvent actionEvent) {

    }
}
