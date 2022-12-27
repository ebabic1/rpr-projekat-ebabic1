package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.UserDaoSQLImplementation;
import ba.unsa.etf.rpr.domain.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class LoginController {
    public Label wrongPasswordLabel;
    public Label userNotfoundLabel;
    public PasswordField passwordField;
    public TextField usernameField;
    public Button loginButton;
    @FXML
    public void initialize(){
        passwordField.getStyleClass().add("poljeNijeIspravno");
        usernameField.getStyleClass().add("poljeNijeIspravno");
        usernameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (usernameField.getText().trim().isEmpty()){
                    usernameField.getStyleClass().removeAll("poljeIspravno");
                    usernameField.getStyleClass().add("poljeNijeIspravno");
                }
                else{
                    usernameField.getStyleClass().removeAll("poljeNijeIspravno");
                    usernameField.getStyleClass().add("poljeIspravno");
                }
            }
        });
        passwordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (passwordField.getText().trim().isEmpty()){
                    passwordField.getStyleClass().removeAll("poljeIspravno");
                    passwordField.getStyleClass().add("poljeNijeIspravno");
                }
                else{
                    passwordField.getStyleClass().removeAll("poljeNijeIspravno");
                    passwordField.getStyleClass().add("poljeIspravno");
                }
            }
        });
    }
    public void loginClicked(ActionEvent actionEvent) throws IOException {
        /*if (usernameField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty()){
            return;
        }
        UserDaoSQLImplementation uDao = new UserDaoSQLImplementation();
        User u = DaoFactory.userDao().getByUsername(usernameField.getText());
        if (u == null){
            System.out.println("Korisnik nije nađen");
            userNotfoundLabel.setVisible(true);
            usernameField.getStyleClass().removeAll("poljeIspravno");
            usernameField.getStyleClass().add("poljeNijeIspravno");
            return;
        }
        else {
            userNotfoundLabel.setVisible(false);
        }
        if( !u.getPassword().equals(passwordField.getText())){
            wrongPasswordLabel.setVisible(true);
            passwordField.getStyleClass().removeAll("poljeIspravno");
            passwordField.getStyleClass().add("poljeNijeIspravno");
            return;
        }
        else {
            wrongPasswordLabel.setVisible(false);
        }
        if(u.getAdmin() == 0){
            Alert warning = new Alert(Alert.AlertType.ERROR);
            warning.setTitle("Missing privileges");
            passwordField.getStyleClass().removeAll("poljeIspravno");
            passwordField.getStyleClass().add("poljeNijeIspravno");
            usernameField.getStyleClass().removeAll("poljeIspravno");
            usernameField.getStyleClass().add("poljeNijeIspravno");
            warning.showAndWait();
            return;
        }*/
        Node n = (Node) actionEvent.getSource();
        Stage s1 = (Stage) n.getScene().getWindow();
        s1.close();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dashboard.fxml"));
        Parent root = loader.load();
        DashboardController dashboardController = loader.getController();
        dashboardController.labelIme.setText(usernameField.getText());
        dashboardController.mainPane.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        stage.setTitle("Dashboard");
        stage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        stage.setMinWidth(500);
        stage.setMinHeight(550);
        stage.show();
    }
}
