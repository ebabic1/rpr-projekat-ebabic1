package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.dao.DaoFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class DashboardController {
    @FXML
    public GridPane mainPane;
    @FXML
    public Label totalGuestsLabel;
    @FXML
    public Label availableRoomsLabel;
    @FXML
    private Button dashboardButton;

    @FXML
    private Button gManButton;

    @FXML
    private GridPane gManGridPane;

    @FXML
    public Label labelIme;

    @FXML
    private Button logoutButton;

    @FXML
    private Button rManButton;

    @FXML
    private GridPane rManGridPane;

    @FXML
    private BorderPane mainBorderPane;
    @FXML
    public void initialize(){
        try {
            totalGuestsLabel.setText(String.valueOf(DaoFactory.userDao().getAll().size()));
            availableRoomsLabel.setText(String.valueOf(DaoFactory.roomDao().getAll().size() - DaoFactory.reservationDao().getAll().size()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void logoutClicked(MouseEvent mouseEvent) throws IOException {
        Node n = (Node) mouseEvent.getSource();
        Stage s1 = (Stage) n.getScene().getWindow();
        s1.close();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent root = loader.load();
        stage.setTitle("Hello!");
        stage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        stage.show();
    }

    public void gManPressed(ActionEvent mouseEvent) throws IOException {
        GridPane gridPane = FXMLLoader.load(getClass().getResource("/fxml/guestmgmt.fxml"));
        gridPane.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        mainBorderPane.setCenter(gridPane);
    }

    public void rManPressed(ActionEvent mouseEvent) throws IOException {
        GridPane gridPane = FXMLLoader.load(getClass().getResource("/fxml/roommgmt.fxml"));
        gridPane.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        mainBorderPane.setCenter(gridPane);
    }

    public void resManPressed(ActionEvent actionEvent) throws IOException {
        GridPane gridPane = FXMLLoader.load(getClass().getResource("/fxml/reservationmgmt.fxml"));
        gridPane.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        mainBorderPane.setCenter(gridPane);
    }

    public void dBrdPressed(ActionEvent actionEvent) {
        try {
            totalGuestsLabel.setText(String.valueOf(DaoFactory.userDao().getAll().size()));
            availableRoomsLabel.setText(String.valueOf(DaoFactory.roomDao().getAll().size() - DaoFactory.reservationDao().getAll().size()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        mainBorderPane.setCenter(mainPane);
    }
}
