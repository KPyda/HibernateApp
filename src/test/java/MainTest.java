import junit.framework.TestCase;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Praktykant on 09.09.14.
 */

public class MainTest {
    public static void main(String args[]) {
        TestDB testDB = new TestDB("test");
        testDB.testGetFilesFromTag();
        testDB.testGetTagsFromFile();
        testDB.testUpdateTag();
        testDB.testUpdateFile();
        testDB.printFilesFromTag(2);
        testDB.printTagsFromFile(14);

        System.exit(0);
    }

    private static class TestDB extends TestCase {

        public TestDB(String name) {
            super(name);
        }

        public void testGetFilesFromTag() {
            DatabaseInterface dbi = new DatabaseInterface();
            Set<DFile> files = new HashSet<DFile>();
            files = dbi.getFilesFromTag(2);
            assertNotNull(files);
            for (DFile file : files) {
                assertEquals(file.getExtension(), "pdf");
            }
        }

        public void testGetTagsFromFile() {
            DatabaseInterface dbi = new DatabaseInterface();
            Set<Tag> tags = new HashSet<Tag>();
            tags = dbi.getTagsFromFile(14);
            assertNotNull(tags);
            for (Tag tag : tags) {
                assertEquals(tag.getId(), 2);
            }
        }

        public void testUpdateTag() {
            DatabaseInterface dbi = new DatabaseInterface();
            Tag tag = dbi.getTag(1);
            tag.setName("new name");
            dbi.updateTag(tag);
            tag = dbi.getTag(1);
            assertEquals(tag.getName(), "new name");
        }

        public void testUpdateFile() {
            DatabaseInterface dbi = new DatabaseInterface();
            DFile dfile = dbi.getFile(3);
            dfile.setName("new name");
            dbi.updateFile(dfile);
            dfile = dbi.getFile(3);
            assertEquals(dfile.getName(), "new name");
        }

        public void printFilesFromTag(int id) {
            System.out.println("Files for tag (id = " + id + ")");
            DatabaseInterface dbi = new DatabaseInterface();
            Set<DFile> files = dbi.getFilesFromTag(id);
            for (DFile file : files) {
                System.out.println(file.getName());
            }
        }

        public void printTagsFromFile(int id) {
            System.out.println("Tags for file (id = " + id + ")");
            DatabaseInterface dbi = new DatabaseInterface();
            Set<Tag> tags = dbi.getTagsFromFile(id);
            for (Tag tag : tags) {
                System.out.println(tag.getName());
            }
        }
    }
}


