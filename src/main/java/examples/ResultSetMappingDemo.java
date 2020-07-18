package examples;

import connection.Connections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

enum SellerMapper{
    INSTANCE;
    public List<Seller> from(ResultSet set) throws SQLException {
        ResultSetMetaData meta = set.getMetaData();
        boolean isValidSet = false;
        for (int i = 1; i < meta.getColumnCount(); i++){
            //TODO napisać kod sprawdzający, czy w set są wszsytkie kolumny z tabeli sellers
        }
        if (isValidSet){
            List<Seller> sellers = new LinkedList<>();
            while(set.next()){
                String firstName = set.getString("first_name");
                String lastName = set.getString("first_name");
                int id = set.getInt("id");
                Date birth = set.getDate("birth_date");
                int salary = set.getInt("salary");

                //TODO.. pozostałę kolumny
                Seller seller = new Seller(id, firstName, lastName, birth, salary);
                sellers.add(seller);
            }
            return sellers;
        }
        return Collections.EMPTY_LIST;

    }
}

class Seller{
    private final long id;
    private final String firstName;
    private final String lastName;
    private final Date birthDate;
    private final int salary;

    public Seller(long id, String firstName, String lastName, Date birthDate, int salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public int getSalary() {
        return salary;
    }
}


public class ResultSetMappingDemo {
    public static void main(String[] args) throws SQLException {
        Connection con = Connections.MARIADB.getConnection();
        ResultSet set = con.createStatement().executeQuery("select * from sellers");
        List<Seller> sellers = SellerMapper.INSTANCE.from(set);

    }
}
