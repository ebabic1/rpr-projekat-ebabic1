package ba.unsa.etf.rpr.controller;

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
import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class DashboardController {
    public GridPane mainPane;
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
}
