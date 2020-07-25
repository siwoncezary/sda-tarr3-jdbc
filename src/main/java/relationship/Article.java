package relationship;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String content;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="article_tag",
            joinColumns = {@JoinColumn(name = "articles_id")},
            inverseJoinColumns = {@JoinColumn (name = "tags_id")}
    )
    private Set<Tag> tags = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag){
        if (tags == null){
            tags = new HashSet<>();
        }
        tags.add(tag);
    }

    public void removeTag(Tag tag){
        if (tags == null){
            return;
        }
        tags.remove(tag);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", tags=" + tags +
                '}';
    }
}
