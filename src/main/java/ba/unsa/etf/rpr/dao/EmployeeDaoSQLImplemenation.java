package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Guest;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class EmployeeDaoSQLImplemenation implements EmployeeDAO {

    private Connection connection;
    public EmployeeDaoSQLImplemenation(){
        try {
            final Properties login = new Properties();
            login.load(new FileInputStream("src/main/java/ba/unsa/etf/rpr/login.properties"));
            connection = DriverManager.getConnection(login.getProperty("connection.string"), login.getProperty("username"), login.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Employee getById(int id){
    String query = "SELECT * FROM Employees WHERE employeeId = ?";
        try {
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()){
            Employee e = new Employee();
            e.setPassword(resultSet.getString("password"));
            e.setUsername(resultSet.getString("username"));
            e.setFirstName(resultSet.getString("firstName"));
            e.setLastName(resultSet.getString("lastName"));
            resultSet.close();
            return e;
        }
        return null;

    }catch(SQLException e) {
        e.printStackTrace();
    }
        return null;
}

    @Override
    public Employee add(Employee item) {
        String query = "INSERT INTO Employees (firstName,lastName,username,password) VALUES (?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,item.getFirstName());
            stmt.setString(2,item.getLastName());
            stmt.setString(3,item.getUsername());
            stmt.setString(4,item.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    @Override
    public Employee update(Employee item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Employee> getAll() {
        return null;
    }
}
