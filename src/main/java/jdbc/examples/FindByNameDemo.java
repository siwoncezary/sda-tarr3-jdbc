package jdbc.examples;

import jdbc.connection.Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FindByNameDemo {
    public static void main(String[] args) throws SQLException {
        Connection con = Connections.EMPLOYEES.getConnection();
        Scanner scanner = new Scanner(System.in);
        String first_name = scanner.next();
        String selectSQl = "select * from employees where first_name = ?";
        PreparedStatement statement = con.prepareStatement(selectSQl);
        statement.setString(1, first_name);
        ResultSet set = statement.executeQuery();
        while(set.next()){
            System.out.println(set.getString("last_name"));
        }
    }
}
