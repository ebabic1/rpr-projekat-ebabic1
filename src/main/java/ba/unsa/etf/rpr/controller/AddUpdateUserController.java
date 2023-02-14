package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.UserException;
import ba.unsa.etf.rpr.model.UserModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.commons.validator.routines.EmailValidator;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * JavaFX controller class for creation and alteration of User objects
 * @author Eldar Babić
 */
public class AddUpdateUserController {
    public TextField firstNameField;
    public TextField lastNameField;
    public ComboBox cityComboBox;
    public ComboBox countryComboBox;
    public TextField emailField;
    public TextField usernameField;
    public TextField passwordField;
    public TextField phoneField;
    private final UserManager userManager = new UserManager();
    public GridPane addupdateGridPane;
    private UserModel userModel = new UserModel();
    private Integer uId;
    private void exit(ActionEvent actionEvent){
        Node n = (Node) actionEvent.getSource();
        Stage s1 = (Stage) n.getScene().getWindow();
        s1.close();
    }
    public Integer getuId() {
        return uId;
    }
    public void setuId(Integer uId) {
        this.uId = uId;
    }
    public AddUpdateUserController() {
        uId = null;
    }
    public AddUpdateUserController(Integer uId) {
        this.uId = uId;
    }

    @FXML
    public void initialize() {
        firstNameField.textProperty().bindBidirectional(userModel.firstNameFieldProperty());
        lastNameField.textProperty().bindBidirectional(userModel.lastNameFieldProperty());
        cityComboBox.valueProperty().bindBidirectional(userModel.cityComboBoxProperty());
        countryComboBox.valueProperty().bindBidirectional(userModel.countryComboBoxProperty());
        emailField.textProperty().bindBidirectional(userModel.emailFieldProperty());
        usernameField.textProperty().bindBidirectional(userModel.usernameFieldProperty());
        passwordField.textProperty().bindBidirectional(userModel.passwordFieldProperty());
        phoneField.textProperty().bindBidirectional(userModel.phoneFieldProperty());
        try {
            Set<String> set1 = new HashSet<String>();
            Set<String> set2 = new HashSet<String>();
            List<User> list = DaoFactory.userDao().getAll();
            list.stream().forEach(entry -> set1.add(entry.getCity()));
            list.stream().forEach(entry -> set2.add(entry.getCountry()));
            cityComboBox.getItems().addAll(FXCollections.observableSet(set1));
            countryComboBox.getItems().addAll(FXCollections.observableSet(set2));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (uId != null) {
            try {
                userModel.fromUser(userManager.getById(uId));
            } catch (UserException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage(), ButtonType.OK).show();
            }
        }
    }

    /**
     * OK button event handler
     * @param actionEvent
     */
    public void okPressed(ActionEvent actionEvent) {
        User u = userModel.toUser();
        try {
            if (uId != null) {
                u.setId(uId);
                userManager.update(u);
                addupdateGridPane.getScene().getWindow().hide();
            } else {
                if(EmailValidator.getInstance().isValid(emailField.textProperty().getValue())) {
                    u.setAdmin(0);
                    userManager.add(u);
                    addupdateGridPane.getScene().getWindow().hide();
                }
                else{
                    emailField.getStyleClass().removeAll("poljeIspravno");
                    emailField.getStyleClass().add("poljeNijeIspravno");
                }
            }

        } catch (UserException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
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

