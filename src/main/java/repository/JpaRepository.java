package repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

abstract public class JpaRepository {
    private final EntityManagerFactory factory;
    protected final EntityManager em;

    public JpaRepository(String persistenceUnit) {
        this.factory = Persistence.createEntityManagerFactory(persistenceUnit);
        this.em = factory.createEntityManager();
    }

    protected JpaRepository(){
        factory = null;
        em = null;
    }
}
