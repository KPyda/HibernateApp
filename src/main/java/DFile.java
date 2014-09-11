import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Praktykant on 09.09.14.
 */
@Entity
@Table(name="file")
public class DFile {
    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "extension")
    private String extension;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;

    @Column(name = "dateModified")
    private Timestamp dateModified;

    @Column(name = "dateAdded")
    private Timestamp dateAdded;

    @Column(name = "size")
    private int size;

    @Column(name = "filePath")
    private String filePath;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "file_tag", joinColumns = { @JoinColumn(name = "file_id") }, inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private Set<Tag> tags = new HashSet<Tag>();

    public DFile () {}

    public DFile(String name, String extension, String description, String author, Timestamp dateModified, Timestamp dateAdded, int size, String filePath) {
        this.name = name;
        this.extension = extension;
        this.description = description;
        this.author = author;
        this.dateModified = dateModified;
        this.dateAdded = dateAdded;
        this.size = size;
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
