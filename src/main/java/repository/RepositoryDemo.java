package repository;

import relationship.Address;
import relationship.User;

import java.util.Optional;

public class RepositoryDemo {
    static UserRepository repository = new UserRepositoryJpa("pos");
    public static void main(String[] args) {
        Optional<User> user = repository.findById("aa@op.pl");
        if (user.isPresent()){
            Address address = user.get().getAddress();
            repository.findByAddress(address).forEach(u -> System.out.println(u.getEmail()));
        }
    }
}
