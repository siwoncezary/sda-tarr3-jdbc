package adressbook;

import repository.Repository;

import java.util.stream.Stream;

public interface PersonRepository extends Repository<Person, Long> {
    Stream<Person> findByName(String name);
    Stream<Person> findByPhone(String phone);
    //to należałoby zdefiniować w serwisie
    void saveWithAddress(Person person, PersonAddress personAddress);
}
