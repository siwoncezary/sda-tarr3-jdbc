package jdbc.examples;

import jdbc.connection.Connections;

import java.sql.Connection;

public class ConnectionDemo {
    public static void main(String[] args) {
        Connection connection = Connections.MARIADB.getConnection();
    }
}
