package dao;

import relationship.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;
import java.util.stream.Stream;

public class UserDaoJpa implements Dao<User, String>{
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("pos");

    public EntityManager getEntityManager(){
        return factory.createEntityManager();
    }

    @Override
    public boolean save(User obj) {
        //Ta implentacja jest poprawna tylko dla naszej klasy User, w której kluczem
        //głównym jest email i my nadajemy te klucze
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(obj);
        User savedUser = em.find(User.class, obj.getEmail());
        em.getTransaction().commit();
        em.close();
        return savedUser.getEmail().equals(obj.getEmail());
    }

    @Override
    public Optional<User> findBy(String id) {
        EntityManager em = getEntityManager();
        Optional<User> ouser = Optional.ofNullable(em.find(User.class, id));
        em.close();
        return ouser;
    }

    @Override
    public Stream<User> findAll() {
        EntityManager em = getEntityManager();
        Stream<User> result = em.createQuery("from User s").getResultStream();
        em.close();
        return result;
    }

    @Override
    public Optional<User> remove(String id) {
        //TODO zaimplementuj metodę remove
        return Optional.empty();
    }

    @Override
    public boolean update(User obj) {
        //TODO zaimplementuj metodę update
        /*
        1. utworzyć managera
        2. rozpocząć transakcję
        3. pobrać encję z bazy na podstawie klucza  obj
        4. przepisać wszystkie pola z obj do pobranej encji
        5. zamknąć transakcję
        6. zamknąć mangera
         */
        return false;
    }
}
