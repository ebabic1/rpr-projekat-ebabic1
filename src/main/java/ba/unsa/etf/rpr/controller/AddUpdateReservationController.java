package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.ReservationException;
import ba.unsa.etf.rpr.exceptions.RoomException;
import ba.unsa.etf.rpr.exceptions.UserException;
import ba.unsa.etf.rpr.model.ReservationModel;
import ba.unsa.etf.rpr.model.UserModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AddUpdateReservationController {
    public Label firstNameLabel;
    public Label lastNameLabel;
    public Label cityLabel;
    public Label countryLabel;
    public Label emailLabel;
    public Label phoneLabel;
    public Button okButton;
    public GridPane addupdateGridPane;
    private UserModel userModel= new UserModel();
    private UserManager userManager = new UserManager();
    private RoomManager roomManager = new RoomManager();
    private ReservationManager reservationManager = new ReservationManager();
    private ReservationModel reservationModel = new ReservationModel();
    private Integer rId;
    private Integer uId;
    private Integer roomId;
    public TextArea additionalInfoTextArea;
    public DatePicker arrivalDatePicker;
    public DatePicker leaveDatePicker;
    public Label priceLabel;
    public ComboBox<Room> roomNumberComboBox;
    public AddUpdateReservationController() {rId = null; uId = null;}
    public AddUpdateReservationController(Integer rId,Integer uId) {this.rId = rId; this.uId = uId;}
    @FXML
    public void initialize() {
        try {
            roomNumberComboBox.setItems(FXCollections.observableList(roomManager.getAll()));
            userModel.fromUser(userManager.getById(uId));
        } catch (UserException e) {
            throw new RuntimeException(e);
        } catch (RoomException e) {
            throw new RuntimeException(e);
        }

        firstNameLabel.textProperty().bindBidirectional(userModel.firstNameFieldProperty());
        lastNameLabel.textProperty().bindBidirectional(userModel.lastNameFieldProperty());
        cityLabel.textProperty().bindBidirectional(userModel.cityComboBoxProperty());
        countryLabel.textProperty().bindBidirectional(userModel.countryComboBoxProperty());
        emailLabel.textProperty().bindBidirectional(userModel.emailFieldProperty());
        phoneLabel.textProperty().bindBidirectional(userModel.phoneFieldProperty());
        additionalInfoTextArea.textProperty().bindBidirectional(reservationModel.additionalInfoProperty());
        arrivalDatePicker.valueProperty().bindBidirectional(reservationModel.arrivalDateProperty());
        leaveDatePicker.valueProperty().bindBidirectional(reservationModel.leaveDateProperty());
        arrivalDatePicker.setValue(LocalDate.now());
        leaveDatePicker.setValue(LocalDate.now());
       arrivalDatePicker.valueProperty().addListener((obs,o,n) ->{
            long daysBetween = ChronoUnit.DAYS.between(arrivalDatePicker.getValue(),leaveDatePicker.getValue());
           if(roomId != null && (daysBetween < 0 || ChronoUnit.DAYS.between(arrivalDatePicker.getValue(),LocalDate.now()) > 0 || ChronoUnit.DAYS.between(leaveDatePicker.getValue(),LocalDate.now())>0))  {
                priceLabel.getStyleClass().removeAll("poljeIspravno");
                priceLabel.getStyleClass().add("poljeNijeIspravno");
                priceLabel.setText("Dates incorrect!");
            }
            else{
                try {
                    if(roomId != null)
                        priceLabel.setText(String.valueOf(daysBetween* DaoFactory.roomDao().getById(roomId).getPrice()));
                    priceLabel.getStyleClass().removeAll("poljeNijeIspravno");
                    priceLabel.getStyleClass().add("poljeIspravno");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
       });
        leaveDatePicker.valueProperty().addListener((obs,o,n) ->{
            long daysBetween = ChronoUnit.DAYS.between(arrivalDatePicker.getValue(),leaveDatePicker.getValue());
            if(roomId != null && (daysBetween < 0 || ChronoUnit.DAYS.between(arrivalDatePicker.getValue(),LocalDate.now()) > 0 || ChronoUnit.DAYS.between(leaveDatePicker.getValue(),LocalDate.now())>0))  {
                priceLabel.getStyleClass().removeAll("poljeIspravno");
                priceLabel.getStyleClass().add("poljeNijeIspravno");
                priceLabel.setText("Dates incorrect!");
            }
            else{
                try {
                    if(roomId != null)
                        priceLabel.setText(String.valueOf(daysBetween* DaoFactory.roomDao().getById(roomId).getPrice()));
                    priceLabel.getStyleClass().removeAll("poljeNijeIspravno");
                    priceLabel.getStyleClass().add("poljeIspravno");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        roomNumberComboBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                try {
                    roomId = DaoFactory.roomDao().getByNumber(Integer.parseInt(newValue)).getId();
                    System.out.println(roomId);
                } catch (NumberFormatException e){
                    throw new RoomException("Invalid room number");
                } catch (SQLException e) {
                    throw new RoomException("Invalid room number");
                }
                Room room = roomManager.getById(roomId);
                if(!priceLabel.textProperty().toString().trim().isEmpty()){
                    long daysBetween = ChronoUnit.DAYS.between(arrivalDatePicker.getValue(),leaveDatePicker.getValue());
                    if(roomId != null && (daysBetween < 0 || ChronoUnit.DAYS.between(arrivalDatePicker.getValue(),LocalDate.now()) > 0 || ChronoUnit.DAYS.between(leaveDatePicker.getValue(),LocalDate.now())>0)){
                        priceLabel.getStyleClass().removeAll("poljeIspravno");
                        priceLabel.getStyleClass().add("poljeNijeIspravno");
                        priceLabel.setText("Dates incorrect!");
                    } else {
                        try {
                            priceLabel.setText(String.valueOf(daysBetween * DaoFactory.roomDao().getById(roomId).getPrice()));
                            priceLabel.getStyleClass().removeAll("poljeNijeIspravno");
                            priceLabel.getStyleClass().add("poljeIspravno");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            } catch (RoomException e) {
                priceLabel.getStyleClass().removeAll("poljeIspravno");
                priceLabel.getStyleClass().add("poljeNijeIspravno");
                priceLabel.setText(e.getMessage());
                roomId = null;
            }
        });
        if(rId != null) {
            try {
                reservationModel.fromReservation(reservationManager.getById(rId));
            } catch (ReservationException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage(), ButtonType.OK).show();
            }
        }
    }
    public void okPressed(ActionEvent actionEvent) {
        if(roomId != null)
        try {
            reservationModel.setRoom(roomManager.getById(roomId));
            reservationModel.setUser(userManager.getById(uId));
            Reservation r = reservationModel.toReservation();
                if(rId!=null){
                    r.setId(rId);
                    reservationManager.update(r);
                } else {
                    reservationManager.add(r);
                }
                addupdateGridPane.getScene().getWindow().hide();
        } catch (RoomException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
        } catch (ReservationException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
        } catch (UserException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
        }
    }

    public void cancelPressed(ActionEvent actionEvent) {
        addupdateGridPane.getScene().getWindow().hide();
    }
}
