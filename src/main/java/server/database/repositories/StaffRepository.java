package server.database.repositories;

import server.database.Database;
import server.database.repositories.model.Staff;
import org.json.JSONArray;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.SQLException;

public class StaffRepository implements Repository {

    private String TABLE_NAME = "staff";
    private Database database;

    public StaffRepository(Database database) {
        this.database = database;
    }

    public JSONArray getAll() throws SQLException {
        return database.query(String.format("SELECT * FROM %s", TABLE_NAME));
    }

    public JSONArray find(StaffColumn column, String criteria) throws SQLException {
        return database.query(String.format("SELECT * FROM %s WHERE %s=%s", TABLE_NAME, column, criteria));
    }

    public void insert(Staff order) throws SQLException {
        if (order.notComplete()) throw new IllegalArgumentException("order is incomplete");

        database.update(String.format("INSERT INTO %s (staff_id, title, firstname, surname) VALUES\n" +
                "(5, '%s', '%s', '%s')", TABLE_NAME, order.title, order.firstname, order.surname));
    }

    public void removeById(int id) {
        throw new NotImplementedException();
    }
}
