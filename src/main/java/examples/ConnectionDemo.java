package examples;

import com.mysql.cj.jdbc.JdbcConnection;
import connection.Connections;

import java.sql.Connection;

public class ConnectionDemo {
    public static void main(String[] args) {
        Connection connection = Connections.MARIADB.getConnection();
    }
}
