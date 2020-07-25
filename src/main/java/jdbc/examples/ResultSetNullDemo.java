package jdbc.examples;
import jdbc.connection.Connections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetNullDemo {
    public static void main(String[] args) throws SQLException {
        Connection con = Connections.MARIADB.getConnection();
        ResultSet set = con.createStatement().executeQuery("select * from sellers");
        while(set.next()){
            String name = set.getString("last_name");
            int salary = set.getInt("salary");
            if (set.wasNull()) {
                System.out.println(name + " salary is null");
            } else {
                System.out.println(name + " " + salary);
            }
        }
       
    }
}
