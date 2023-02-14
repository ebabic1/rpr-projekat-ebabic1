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
                Boolean flag = true;
                System.out.println(firstNameField.textProperty().getValue());
                if (firstNameField.textProperty().getValue().trim().length() == 0) {
                    firstNameField.getStyleClass().removeAll("poljeIspravno");
                    firstNameField.getStyleClass().add("poljeNijeIspravno");
                    flag = false;
                }
                if (lastNameField.textProperty().getValue().trim().length() == 0) {
                    lastNameField.getStyleClass().removeAll("poljeIspravno");
                    lastNameField.getStyleClass().add("poljeNijeIspravno");
                    flag = false;
                }
                if (usernameField.textProperty().getValue().trim().length() == 0) {
                    usernameField.getStyleClass().removeAll("poljeIspravno");
                    usernameField.getStyleClass().add("poljeNijeIspravno");
                    flag = false;
                }
                if (passwordField.textProperty().getValue().trim().length() == 0) {
                    passwordField.getStyleClass().removeAll("poljeIspravno");
                    passwordField.getStyleClass().add("poljeNijeIspravno");
                    flag = false;
                }
                if (cityComboBox.valueProperty().getValue().toString().trim().length() == 0) {
                    cityComboBox.getStyleClass().removeAll("poljeIspravno");
                    cityComboBox.getStyleClass().add("poljeNijeIspravno");
                    flag = false;
                }
                if (countryComboBox.valueProperty().getValue().toString().trim().length() == 0) {
                    countryComboBox.getStyleClass().removeAll("poljeIspravno");
                    countryComboBox.getStyleClass().add("poljeNijeIspravno");
                    flag = false;
                }
                if (phoneField.textProperty().getValue().trim().length() == 0) {
                    phoneField.getStyleClass().removeAll("poljeIspravno");
                    phoneField.getStyleClass().add("poljeNijeIspravno");
                    flag = false;
                }
                if(!EmailValidator.getInstance().isValid(emailField.textProperty().getValue())) {
                    emailField.getStyleClass().removeAll("poljeIspravno");
                    emailField.getStyleClass().add("poljeNijeIspravno");
                    flag = false;
                }
                if (flag){
                    u.setAdmin(0);
                    userManager.add(u);
                    addupdateGridPane.getScene().getWindow().hide();
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

