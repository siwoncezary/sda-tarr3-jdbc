package examples;

import connection.Connections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetUpdatableDemo {
    public static void main(String[] args) throws SQLException {
        Connection con = Connections.MARIADB.getConnection();
        ResultSet set = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("select * from sellers");
        while(set.next()){
            String firstName = set.getString("first_name");
            if (firstName == null){
                set.updateString("first_name", "KAROL");
                set.updateString("last_name","NOWAK");
                set.updateInt("salary",3000);
                set.updateRow();
            }
        }
        //przewinięcie resultSet na początek
        set.beforeFirst();
        while(set.next()){
            String firstName = set.getString("first_name");
            System.out.println(firstName);
        }

    }
}
