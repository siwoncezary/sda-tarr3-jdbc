package relationship;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;

public class ManyToManyDemo {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("pos");

    public static void main(String[] args) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Article article = new Article();
        article.setTitle("Java dla opornych");
        article.setContent("AA AA aAAA");

        Tag javaTag = new Tag();
        javaTag.setName("Java");
        em.persist(javaTag);

        Tag programmingTag = new Tag();
        programmingTag.setName("Programming");
        em.persist(programmingTag);

        Set<Tag> tags = new HashSet<>();
        tags.add(javaTag);
        tags.add(programmingTag);
        article.setTags(tags);
        System.out.println("Przed utrwaleniem: " + article.getId());
        em.persist(article);
        em.getTransaction().commit();
        System.out.println("Po utrwaleniu: " + article.getId());
        Article articleFromBase = em.find(Article.class, article.getId());
        System.out.println(articleFromBase);
        Tag tagFromBase = em.find(Tag.class, 2L);
        System.out.println(tagFromBase);
    }
}
