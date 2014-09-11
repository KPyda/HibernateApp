import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Praktykant on 09.09.14.
 */
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue
    @Column(name = "tag_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created")
    private Timestamp created;

    @ManyToMany(mappedBy = "tags")
     private Set<DFile> files = new HashSet<DFile>();

    public Tag() {}

    public Tag(String name, String description, Timestamp created) {
        this.name = name;
        this.description = description;
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<DFile> getFiles() {
        return files;
    }

    public void setFiles(Set<DFile> files) {
        this.files = files;
    }
}
