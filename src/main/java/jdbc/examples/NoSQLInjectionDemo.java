package jdbc.examples;

import jdbc.connection.Connections;

import java.sql.*;
import java.util.Scanner;

public class NoSQLInjectionDemo {
    public static void main(String[] args) throws SQLException {
        Connection con = Connections.EMPLOYEES.getConnection();
        Scanner scanner = new Scanner(System.in);
        int emp_no = scanner.nextInt();
        //UWAGA!!! TYLKO TAK ROBIMY POLECENIA PARAMETRYCZNE!!!
        String selectSQl = "select * from employees where emp_no = ?";
        //w miejscu pytajnika zostanie wstrzyknięty parametr
        PreparedStatement statement = con.prepareStatement(selectSQl);
        //wstrzykujemy w miejscu pierwszego pytajnika wartość int ze zmiennej emp_no
        statement.setInt(1, emp_no);
        ResultSet set = statement.executeQuery();
        while(set.next()){
            System.out.println(set.getString("last_name"));
        }
        //TODO Napisać własny program wyszukujący pracowników na podstawie imienia
    }
}
