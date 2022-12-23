package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.IDable;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public abstract class AbstractDao<T extends IDable> implements Dao<T> {
    private Connection connection;
    private String tableName;
    public AbstractDao(String tableName) {
        this.tableName = tableName;
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
    private Map.Entry<String,String> prepareInsertParts(Map<String,Object> row) {
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();
        int counter = 0;
        for(Map.Entry<String, Object> entry : row.entrySet()) {
            counter++;
            if(entry.getKey().equals("id")) continue;
            columns.append(entry.getKey());
            questions.append("?");
            if(row.size() != counter) {
                columns.append(",");
                columns.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<String,String>(columns.toString(),questions.toString());
    }
    @Override
    public T getById(int id) {
        String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()){
                T object = rowToObject(resultSet);
                resultSet.close();
                return object;
            }
            return null;

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public abstract T rowToObject(ResultSet resultSet) throws SQLException;
    public abstract Map<String,Object> objectToRow(T object) throws SQLException;
    @Override
    public T add(T item) throws SQLException {
        Map<String,Object> row = objectToRow(item);
        Map.Entry<String,String> columns = prepareInsertParts(row);
        String query = "INSERT INTO " + tableName + " (" +columns.getKey() + ") VALUES ("+columns.getValue() +")";
        try {
            PreparedStatement stmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            int counter = 1;
            for(Map.Entry<String,Object> entry : row.entrySet()){
                stmt.setObject(counter,entry.getValue());
                counter++;
            }
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
    @Override
    public T update(T item) throws SQLException {
        Map<String,Object> row = objectToRow(item);
        String updateColumns = prepareUpdateParts(row);
        String query = "UPDATE " + tableName + " SET " + updateColumns + "WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        int counter = 1;
        for(Map.Entry<String,Object> entry : row.entrySet()) {
            if (entry.getKey().equals("id")) continue;
            stmt.setObject(counter, entry.getValue());
            counter++;
        }
        stmt.setObject(counter+1,item.getId());
        stmt.executeUpdate();
        return item;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM " + tableName + "WHERE id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<T> getAll() {
        String query = "SELECT * FROM " + tableName;
        List<T> results = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                T object = rowToObject(resultSet);
                results.add(object);
            }
            resultSet.close();
            return results;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Method for creating a query for UPDATE
     * @param row
     * @return String in id=?,column1=?... format
     */
    private String prepareUpdateParts(Map<String, Object> row) {
        StringBuilder columns = new StringBuilder();
        int counter = 0;
        for(Map.Entry<String, Object> entry : row.entrySet()){
            counter++;
            if (entry.getKey().equals("id")) continue;
            columns.append(entry.getKey()).append("= ?");
            if (row.size() != counter) {
                columns.append(",");
            }
        }
        return columns.toString();
    }
}