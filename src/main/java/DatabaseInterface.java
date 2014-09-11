import org.apache.commons.io.FilenameUtils;
import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Praktykant on 09.09.14.
 */
public class DatabaseInterface {
    private SessionFactory factory;

    public DatabaseInterface() {
        factory = (SessionFactory) new AnnotationConfiguration().configure()
                .buildSessionFactory();
    }

    public Integer addFile(File file, String description, String author) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer id = null;
        try {
            tx = session.beginTransaction();

            String name = file.getName();
            String extension = FilenameUtils.getExtension(name);
            String filePath = file.getPath();
            Timestamp dateAdded = new Timestamp(new GregorianCalendar().getTimeInMillis());
            int size = (int) file.length();
            DFile dfile = new DFile(name, extension, description, author, dateAdded, dateAdded, size, filePath);

            id = (Integer) session.save(dfile);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }

    public Integer addTag(String name, String description) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer id = null;
        try {
            tx = session.beginTransaction();
            Tag tag = new Tag(name, description, new Timestamp(new GregorianCalendar().getTimeInMillis()));
            id = (Integer) session.save(tag);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }

    public DFile getFile(Integer file_id) {
        Session session = factory.openSession();
        Transaction tx = null;
        DFile dfile = null;
        try {
            tx = session.beginTransaction();
            dfile = (DFile) session.get(DFile.class, file_id);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return dfile;
    }

    public Tag getTag(Integer tag_id) {
        Session session = factory.openSession();
        Transaction tx = null;
        Tag tag = null;
        try {
            tx = session.beginTransaction();
            tag = (Tag) session.get(Tag.class, tag_id);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tag;
    }

    public void addTagToFile(Integer tag_id, Integer file_id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            DFile dFile = (DFile) session.get(DFile.class, file_id);
            Tag tag = (Tag) session.get(Tag.class, tag_id);
            dFile.getTags().add(tag);
            session.save(dFile);
            session.save(tag);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void removeTag(Integer tag_id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Tag tag = (Tag) session.get(Tag.class, tag_id);
            session.delete(tag);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void removeFile(Integer file_id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            DFile dFile = (DFile) session.get(DFile.class, file_id);
            session.delete(dFile);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Set<Tag> getTagsFromFile(Integer file_id) {
        Set<Tag> tags = new HashSet<Tag>();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            DFile dFile = (DFile) session.get(DFile.class, file_id);
            tags.addAll(dFile.getTags());
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tags;
    }

    public Set<DFile> getFilesFromTag(Integer tag_id) {
        Set<DFile> files = new HashSet<DFile>();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Tag tag = (Tag) session.get(Tag.class, tag_id);
            files.addAll(tag.getFiles());
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return files;
    }

    public void updateFile(DFile dFile) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(dFile);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateTag(Tag tag) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(tag);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public ArrayList<Tag> getTags(){
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList<Tag> tags = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM Tag";
            Query query  = session.createQuery(hql);
            tags = (ArrayList<Tag>) query.list();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tags;
    }

}
