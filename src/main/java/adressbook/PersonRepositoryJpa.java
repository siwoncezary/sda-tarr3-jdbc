package adressbook;

import repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Stream;

public class PersonRepositoryJpa extends JpaRepository implements PersonRepository{
    @Override
    public Stream<Person> findByName(String name) {
        return em.createQuery("from Person p where p.name = :name", Person.class)
                .setParameter("name", name)
                .getResultStream();
    }

    @Override
    public Stream<Person> findByPhone(String phone) {
        return em.createQuery("from Person p where p.phone = :phone", Person.class)
                .setParameter("phone", phone)
                .getResultStream();
    }

    @Override
    public void saveWithAddress(Person person, PersonAddress personAddress) {
        em.getTransaction().begin();
        person.setAddress(personAddress);
        em.persist(person);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public void save(Person obj) {
        em.persist(obj);
        em.close();
    }

    @Override
    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(em.find(Person.class, id));
    }

    @Override
    public Stream<Person> findAll() {
        return em.createQuery("from Person", Person.class).getResultStream();
    }

    @Override
    public Optional<Person> update(Person obj) {
        return Optional.empty();
    }

    @Override
    public Optional<Person> remove(Long id) {
        return Optional.empty();
    }
}
