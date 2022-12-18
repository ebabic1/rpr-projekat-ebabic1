package ba.unsa.etf.rpr.dao;

/**
 * Factory method for DAO implementations
 * @author Eldar Babić
 */
public class DaoFactory {
    private static final ReservationDao reservationDao = new ReservationDaoSQLImplementation();
    private static final UserDao userDao = new UserDaoSQLImplementation();
    private static final RoomDao roomDao = new RoomDaoSQLImplementation();
    private DaoFactory(){};
    public static RoomDao roomDao(){
        return roomDao;
    }
    public static ReservationDao reservationDao(){
        return reservationDao;
    }
    public static UserDao userDao(){
        return userDao;
    }
}
