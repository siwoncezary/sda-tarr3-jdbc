package repository;

import relationship.Address;
import relationship.User;

import java.util.stream.Stream;

public interface UserRepository extends Repository<User, String> {
    Stream<User> findByName(String name);
    Stream<User> findByAddress(Address address);

}
