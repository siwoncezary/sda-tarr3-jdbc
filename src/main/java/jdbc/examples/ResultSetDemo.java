package jdbc.examples;

import jdbc.connection.Connections;

import java.sql.*;

public class ResultSetDemo {
    public static void main(String[] args) throws SQLException {
        Connection con = Connections.EMPLOYEES.getConnection();
        //możliwość skrolowania w obie strony w trybie tylko do odczytu
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees limit 100");
        while(resultSet.next()){
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            Date birthDate = resultSet.getDate("birth_date");
            String gender = resultSet.getString("gender");
            System.out.println(firstName +" "+ lastName +" " + birthDate +" " + gender);
        }
        while(resultSet.previous()){
            String gender = resultSet.getString("gender");
            System.out.println(gender);
        }
    }
}
