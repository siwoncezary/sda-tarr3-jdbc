package examples;

import connection.Connections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ResultSetDeleteDemo {
    public static void main(String[] args) throws SQLException {
        Connection con = Connections.MARIADB.getConnection();
        ResultSet set = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery("select * from sellers");
        while(set.next()){
            String firstName = set.getString("first_name");
            if (Objects.equals("Marek", firstName)){
                set.deleteRow();
            }
        }
    }
}
