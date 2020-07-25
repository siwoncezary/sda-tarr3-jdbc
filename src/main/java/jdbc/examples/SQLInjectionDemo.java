package jdbc.examples;

import jdbc.connection.Connections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQLInjectionDemo {
    public static void main(String[] args) throws SQLException {
        Connection con = Connections.EMPLOYEES.getConnection();
        Scanner scanner = new Scanner(System.in);
        String emp_no = scanner.nextLine();
        //UWAGA!!! TAK NIE ROBIMY!!!
        String selectSQl = "select * from employees where emp_no = " + emp_no;
        Statement statement = con.createStatement();
        ResultSet set = statement.executeQuery(selectSQl);
        while(set.next()){
            System.out.println(set.getString("last_name"));
        }
    }
}
