package jdbc.examples;

import jdbc.connection.Connections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionDemo {
    public static void main(String[] args) throws SQLException {
        Connection con = Connections.EMPLOYEES.getConnection();
        con.setAutoCommit(false);
        con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        String select = "select * from employees where emp_no=500000";
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(select);
        while(result.next()){
            System.out.println(result.getString("last_name"));
        }
    }
}
