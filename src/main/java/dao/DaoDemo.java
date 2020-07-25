package dao;

import java.util.Optional;

public class DaoDemo {
    private static Dao<User, String> userDao = new UserDaoJdbc();
    public static void main(String[] args) {
        User user = new User();
        user.setPassword("1224");
        user.setName("ADAM");
        user.setEmail("adam@gmail.com");
        boolean isSaved = userDao.save(user);
        if(isSaved)
            System.out.println("User zapisany");
        Optional<User> ouser = userDao.findBy("adam@gmail.com");
        System.out.println(ouser.map(User::getName).orElse("BRAK"));
        user.setPassword("abcd");
        userDao.update(user);
        System.out.println(userDao.findBy(user.getEmail()).map(User::getPassword).orElse("BRAK ZMIANY"));
    }
}
