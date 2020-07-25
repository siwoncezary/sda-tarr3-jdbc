package jdbc.examples;

import jdbc.connection.Connections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class ResultSetMetaDemo {
    public static void main(String[] args) throws SQLException {
        Connection con = Connections.MARIADB.getConnection();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz polecenie select dla tabeli sellers");
        String select = scanner.nextLine();
        ResultSet set = con.createStatement().executeQuery(select);
        ResultSetMetaData meta = set.getMetaData();
        while(set.next()){
            for(int i = 1; i <= meta.getColumnCount(); i++){
                switch (meta.getColumnTypeName(i)) {
                    case "INT":{
                        System.out.print(set.getInt(i) + " ");
                    }
                    break;
                    case "VARCHAR": {
                        System.out.print(set.getString(i) + " ");
                    }
                    break;
                    case "DATE":{
                        System.out.print(set.getDate(i) + " ");
                    }
                }
            }
            System.out.println();
        }
    }
}
