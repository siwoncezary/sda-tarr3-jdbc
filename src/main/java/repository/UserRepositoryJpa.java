package repository;

import relationship.Address;
import relationship.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.management.ManagementFactory;
import java.util.Optional;
import java.util.stream.Stream;

public class UserRepositoryJpa extends JpaRepository implements UserRepository{
    public UserRepositoryJpa(String persistenceUnit) {
        super(persistenceUnit);
    }

    @Override
    public Stream<User> findByName(String name) {
       return em.createQuery("from User u where u.name = :name", User.class)
               .setParameter("name", name)
               .getResultStream();
    }

    @Override
    public Stream<User> findByAddress(Address address) {
        return em.createQuery("from User u where u.address = :address", User.class)
                .setParameter("address", address)
                .getResultStream();
    }

    @Override
    public void save(User obj) {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public Stream<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> update(User obj) {
        return Optional.empty();
    }

    @Override
    public Optional<User> remove(String id) {
        return Optional.empty();
    }
}
