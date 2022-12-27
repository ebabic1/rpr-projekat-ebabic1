package ba.unsa.etf.rpr.model;

import ba.unsa.etf.rpr.domain.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Model class for 2 way data binding with add/update form
 *
 * @autor Eldar Babić
 */
public class UserModel {
    public SimpleStringProperty firstNameField = new SimpleStringProperty("");
    public SimpleStringProperty  lastNameField = new SimpleStringProperty("");
    public SimpleStringProperty  cityComboBox = new SimpleStringProperty("");
    public SimpleStringProperty  countryComboBox = new SimpleStringProperty("");
    public SimpleStringProperty  emailField = new SimpleStringProperty("");
    public SimpleStringProperty  usernameField = new SimpleStringProperty("");
    public SimpleStringProperty  passwordField = new SimpleStringProperty("");
    public SimpleStringProperty  phoneField = new SimpleStringProperty("");
    public void fromUser(User u) {
        this.firstNameField.set(u.getFirstName());
        this.lastNameField.set(u.getLastName());
        this.cityComboBox.set(u.getCity());
        this.countryComboBox.set(u.getCountry());
        this.emailField.set(u.getEmail());
        this.usernameField.set(u.getUsername());
        this.passwordField.set(u.getPassword());
        this.phoneField.set(u.getPhone());
    }
    public User toUser(){
        User u = new User();
        u.setFirstName(this.firstNameField.getValue());
        u.setLastName(this.lastNameField.getValue());
        u.setCity(this.cityComboBox.getValue());
        u.setCountry(this.countryComboBox.getValue());
        u.setEmail(this.emailField.getValue());
        u.setUsername(this.usernameField.getValue());
        u.setPassword(this.passwordField.getValue());
        u.setPhone(this.phoneField.getValue());
        return u;
    }
    public String getFirstNameField() {
        return firstNameField.get();
    }

    public SimpleStringProperty firstNameFieldProperty() {
        return firstNameField;
    }

    public void setFirstNameField(String firstNameField) {
        this.firstNameField.set(firstNameField);
    }

    public String getLastNameField() {
        return lastNameField.get();
    }

    public SimpleStringProperty lastNameFieldProperty() {
        return lastNameField;
    }

    public void setLastNameField(String lastNameField) {
        this.lastNameField.set(lastNameField);
    }

    public String getCityComboBox() {
        return cityComboBox.get();
    }

    public SimpleStringProperty cityComboBoxProperty() {
        return cityComboBox;
    }

    public void setCityComboBox(String cityComboBox) {
        this.cityComboBox.set(cityComboBox);
    }

    public String getCountryComboBox() {
        return countryComboBox.get();
    }

    public SimpleStringProperty countryComboBoxProperty() {
        return countryComboBox;
    }

    public void setCountryComboBox(String countryComboBox) {
        this.countryComboBox.set(countryComboBox);
    }

    public String getEmailField() {
        return emailField.get();
    }

    public SimpleStringProperty emailFieldProperty() {
        return emailField;
    }

    public void setEmailField(String emailField) {
        this.emailField.set(emailField);
    }

    public String getUsernameField() {
        return usernameField.get();
    }

    public SimpleStringProperty usernameFieldProperty() {
        return usernameField;
    }

    public void setUsernameField(String usernameField) {
        this.usernameField.set(usernameField);
    }

    public String getPasswordField() {
        return passwordField.get();
    }

    public SimpleStringProperty passwordFieldProperty() {
        return passwordField;
    }

    public void setPasswordField(String passwordField) {
        this.passwordField.set(passwordField);
    }

    public String getPhoneField() {
        return phoneField.get();
    }

    public SimpleStringProperty phoneFieldProperty() {
        return phoneField;
    }

    public void setPhoneField(String phoneField) {
        this.phoneField.set(phoneField);
    }
}
