package repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface Repository<T, K> {
    void save(T obj);
    Optional<T> findById(K id);
    Stream<T> findAll();
    Optional<T> update(T obj);
    Optional<T> remove(K id);
}
