package relationship;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OneToOneDemo {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("pos");
    public static void main(String[] args) {
        User user = new User();
        user.setEmail("aa@op.pl");
        user.setPassword("1234");
        Address userAddress = new Address();
        userAddress.setCity("Toruń");
        userAddress.setStreet("Józefa Bema 6");
        userAddress.setZipCode("87-100");
        user.setAddress(userAddress);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
}
