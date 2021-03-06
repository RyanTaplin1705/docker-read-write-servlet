package server.database;

import oracle.jdbc.driver.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;
import utils.properties.DatabaseProperties;

import java.sql.SQLException;

public class OracleConnectionFactory implements ConnectionFactory {

    private DatabaseProperties properties;

    public OracleConnectionFactory(DatabaseProperties properties) {
        this.properties = properties;
    }

    public OracleConnection create() {
        OracleDataSource dataSource = new OracleDataSourceFactory(properties).build();

        try {
            return (OracleConnection) dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("There was a problem connecting to the server.database: " + properties.databaseURL());
        }
    }
}
