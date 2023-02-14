package ba.unsa.etf.rpr;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.UserDaoSQLImplementation;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.ReservationException;
import ba.unsa.etf.rpr.exceptions.RoomException;
import ba.unsa.etf.rpr.exceptions.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Mock
    UserDaoSQLImplementation userDao;
    @Mock
    UserManager uMan;
    @Mock
    ReservationManager rMan;
    @BeforeEach
    public void setup() throws SQLException {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests duplicate surrogate-key room number
     * @throws RoomException
     */
    @Test
    public void testRoomDuplicateEntry() throws RoomException {
        Room r = new Room();
        r.setDescription("");
        r.setAvailable(1);
        r.setRoomNumber(123);
        r.setId(1);
        r.setPrice(321.00);
        r.setMaxPersons(2);
        RoomManager roomManager = new RoomManager();
        assertThrows(RoomException.class,()->roomManager.add(r));
    }

    /**
     * Tests behaviour when wanted room is not in the database
     */
    @Test
    public void testNonExistentRoom()  {
        RoomManager roomManager = new RoomManager();
        assertThrows(RoomException.class,()->roomManager.getById(1234));
    }

    /**
     * Tests behaviour when wanted reservation is not in the database
     */
    @Test
    public void testNonExistentReservation(){
        ReservationManager reservationManager = new ReservationManager();
        assertThrows(ReservationException.class,()->reservationManager.getById(123213));
    }
    /**
     * Tests behaviour when wanted user is not in the database
     */
    @Test
    public void testNonExistentUser(){
        UserManager userManager = new UserManager();
        assertThrows(UserException.class,()->userManager.getById(123123));
    }

    /**
     * Reserving an already reserved room
     */
    @Test
    public void testRoomAlreadyReserved(){
        Reservation r = new Reservation();
        try {
            r.setRoom(DaoFactory.roomDao().getByNumber(123));
            r.setUser(DaoFactory.userDao().getById(1));
        }
        catch (SQLException e){

        }
        r.setAdditionalInfo("");
        r.setId(5);
        r.setArrivalDate(Date.valueOf(LocalDate.now()));
        r.setLeaveDate(Date.valueOf(LocalDate.now().plus(Period.ofDays(2))));
        ReservationManager reservationManager = new ReservationManager();
        assertThrows(ReservationException.class,()->reservationManager.add(r));
    }

    /**
     * A user cannot reserve multiple rooms
     */
    @Test
    public void testMultipleReservationGuest(){
        Reservation r = new Reservation();
        ReservationManager reservationManager = new ReservationManager();
        try {
            r.setRoom(DaoFactory.roomDao().getByNumber(1));
            r.setUser(DaoFactory.userDao().getById(1));
            r.setAdditionalInfo("");
            r.setId(5);
            r.setArrivalDate(Date.valueOf(LocalDate.now()));
            r.setLeaveDate(Date.valueOf(LocalDate.now().plus(Period.ofDays(2))));
            reservationManager.add(r);
        }
        catch (SQLException e){

        } catch (ReservationException e) {

        }
        try {
            r.setRoom(DaoFactory.roomDao().getByNumber(11));
        } catch (SQLException e) {

        }
        assertThrows(ReservationException.class,()->reservationManager.add(r));
    }
    /**
     * Tests duplicate User entry, database should support multiple users of the same name
     * @throws RoomException
     */
    @Test
    public void testUserDuplicateEntry() throws RoomException, UserException {
        User u = new User();
        u.setFirstName("A");
        u.setLastName("B");
        u.setId(1);
        u.setCity("Sarajevo");
        u.setCountry("Bosna i Hercegovina");
        u.setEmail("email@email.com");
        u.setPassword("pass");
        u.setPhone("061123123");
        u.setUsername("uname");
        UserManager userManager = new UserManager();
        userManager.add(u);
    }
    /**
     * Tests date validation, the arrival date is after the leave date in this case.
     * @throws ReservationException
     */
    @Test
    public void testDateValidation() throws ReservationException {
        Reservation r = new Reservation();
        r.setArrivalDate(Date.valueOf(LocalDate.now()));
        r.setLeaveDate(Date.valueOf(LocalDate.now().minus(Period.ofDays(1))));
        doCallRealMethod().when(rMan).validateDate(r);
        assertThrows(ReservationException.class,()->rMan.validateDate(r));
    }
    /**
     * Tests name validation
     * @throws UserException
     */
    @Test
    public void testNameValidation() throws UserException {
        User u = new User();
        u.setFirstName("       ");
        doCallRealMethod().when(uMan).validate(u);
        Answer<User> answer = new Answer<>() {
            @Override
            public User answer(InvocationOnMock invocationOnMock) throws Throwable {
                uMan.validate(u);
                return u;
            }
        };
        when(uMan.add(u)).thenAnswer(answer);
        assertThrows(UserException.class,()->uMan.add(u));
    }
    /**
     * Verifies invocation of mocked DAO class
     * @throws SQLException
     */
    @Test
    public void testMockInv() throws SQLException {
        User u = new User();
        u.setFirstName("Mock");
        when(userDao.getById(1)).thenReturn(u);
        User u1 = userDao.getById(1);
        verify(userDao).getById(1);
    }
}
