package dao;

import java.util.Optional;
import java.util.stream.Stream;

public interface Dao<T, K> {
    boolean save(T obj);
    Optional<T> findBy(K id);
    Stream<T> findAll();
    Optional<T> remove(K id);
    boolean update(T obj);
}
