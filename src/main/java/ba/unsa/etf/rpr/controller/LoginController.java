package ba.unsa.etf.rpr.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class LoginController {

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
    public void loginClicked(MouseEvent mouseEvent) throws IOException {
        if (usernameField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty()){
            return;
        }
        Node n = (Node) mouseEvent.getSource();
        Stage s1 = (Stage) n.getScene().getWindow();
        s1.close();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dashboard.fxml"));
        Parent root = loader.load();
        DashboardController dashboardController = loader.getController();
        dashboardController.labelIme.setText(usernameField.getText());
        stage.setTitle("Dashboard");
        stage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        stage.showAndWait();
    }
}
