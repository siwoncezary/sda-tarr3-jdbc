package jdbc.examples;

import jdbc.connection.Connections;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ResultSetInsertDemo {
    public static void main(String[] args) throws SQLException {
        Connection con = Connections.MARIADB.getConnection();
        ResultSet set = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery("select * from sellers");
        boolean isMarek = false;
        while(set.next()){
            String firstName = set.getString("first_name");
            //wstawiamy nowy rekord jeśli nie ma w tabeli Marka
            if ("Marek".equals(firstName)){
                isMarek = true;
            }
            //testujemy, czy ostani wiersz i nie ma w tabeli Marka
            if (set.isLast() && !isMarek){
                set.moveToInsertRow();

                set.updateString("first_name","Marek");
                set.updateString("last_name","Marek");
                set.updateDate("birth_date",new Date(LocalDate.of(1995,10,10).toEpochDay()*3600*25*1000));
                set.updateInt("salary", 4000);

                set.insertRow();
            }
        }
    }
}
