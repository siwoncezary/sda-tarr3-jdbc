package examples;

import connection.Connections;

import java.sql.SQLException;
import java.sql.Statement;

public class StatementDemo {
    public static void main(String[] args) {
        try {
            Statement statement = Connections.MARIADB.getConnection().createStatement();
            String createSellersTable = "create or replace table sellers (" +
                    "id int primary key auto_increment," +
                    "first_name varchar(20)," +
                    "last_name varchar(50)," +
                    "birth_date date)";
            int result = statement.executeUpdate(createSellersTable);
            System.out.println(result == 0 ? "Success" : "Fault");
            statement.close();
            //Dodać jeden rekord
            String insertSeller = "insert into sellers values(" +
                    "1, 'ADAM', 'KOWAL','2000-10-10'" +
                    ")";
            statement = Connections.MARIADB.getConnection().createStatement();
            result = statement.executeUpdate(insertSeller);
            System.out.println(result == 1 ? "Success" : "Fault");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
