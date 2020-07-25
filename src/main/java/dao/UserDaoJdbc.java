package dao;

import jdbc.connection.Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Stream;

public class UserDaoJdbc implements Dao<User, String>{
    private static final Connection connection = Connections.MARIADB.getConnection();

    public UserDaoJdbc() {
        try {
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS user_app(user_name varchar(15), email varchar(25) PRIMARY KEY, password varchar(15))");
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean save(User obj) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user_app VALUES(?, ?, ?)");
            statement.setString(1, obj.getName());
            statement.setString(2,obj.getEmail());
            statement.setString(3, obj.getPassword());
            return statement.executeUpdate() == 1;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public Optional<User> findBy(String id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM user_app WHERE email = ?");
            statement.setString(1, id);
            ResultSet set = statement.executeQuery();
            User user = new User();
            boolean isEmpty = true;
            while(set.next()){
                isEmpty = false;
                user.setEmail(set.getString("email"));
                user.setName(set.getString("user_name"));
                user.setPassword(set.getString("password"));
            }
            return isEmpty ? Optional.empty() : Optional.of(user);
        } catch (SQLException throwables) {
            return Optional.empty();
        }
    }

    @Override
    public Stream<User> findAll() {
        //TODO zaimplementować zwracanie wszystkich userów
        return null;
    }

    @Override
    public Optional<User> remove(String id) {
        //TODO zaimplementować usunięcie po id
        return Optional.empty();
    }

    @Override
    public boolean update(User obj) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE user_app set user_name = ?, password = ? where email = ?");
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getPassword());
            statement.setString(3, obj.getEmail());
            return statement.executeUpdate() == 1;
        } catch (SQLException throwables) {
            return false;
        }
    }
}
