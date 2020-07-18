package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum Connections {
    MARIADB("jdbc:mysql://localhost:3306/pointofsale","root","1234"),
    EMPLOYEES("jdbc:mysql://localhost:3306/employees","root","1234");
    private Connection connection;

    Connections(String url, String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
