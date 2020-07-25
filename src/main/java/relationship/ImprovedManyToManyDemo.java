package relationship;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Set;

public class ImprovedManyToManyDemo {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("pos");
    public static void main(String[] args) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Article article = new Article();
        article.setTitle("JDBC i Hibernate");
        article.setContent("BBB BBBB b");
        Tag tag = em.find(Tag.class, 2L);
        article.addTag(tag);

        Tag newTag = new Tag();
        newTag.setName("database");
        article.addTag(newTag);

        em.persist(article);

        Article javaArticle = em.find(Article.class, 1L);
        Set<Tag> tags = javaArticle.getTags();
        System.out.println(javaArticle.getTags());
        javaArticle.removeTag(em.find(Tag.class, 1L));
        System.out.println(tags);
        System.out.println(javaArticle.getTags());
        em.getTransaction().commit();
    }
}
