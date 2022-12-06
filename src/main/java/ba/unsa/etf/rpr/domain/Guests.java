package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Holds guest details
 * @author Eldar Babič
 */
public class Guests {
    private int guestId;
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String email;
    private String phone;

    @Override
    public String toString() {
        return "Guests{" +
                "guestId=" + guestId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guests)) return false;
        Guests guests = (Guests) o;
        return getGuestId() == guests.getGuestId() && getFirstName().equals(guests.getFirstName()) && getLastName().equals(guests.getLastName()) && getCity().equals(guests.getCity()) && getCountry().equals(guests.getCountry()) && Objects.equals(getEmail(), guests.getEmail()) && getPhone().equals(guests.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGuestId(), getFirstName(), getLastName(), getCity(), getCountry(), getEmail(), getPhone());
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
