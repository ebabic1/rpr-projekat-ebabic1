package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Holds guest details
 * @author Eldar Babić
 */
public class Guest {
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
        if (!(o instanceof Guest)) return false;
        Guest guest = (Guest) o;
        return getGuestId() == guest.getGuestId() && getFirstName().equals(guest.getFirstName()) && getLastName().equals(guest.getLastName()) && getCity().equals(guest.getCity()) && getCountry().equals(guest.getCountry()) && Objects.equals(getEmail(), guest.getEmail()) && getPhone().equals(guest.getPhone());
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
