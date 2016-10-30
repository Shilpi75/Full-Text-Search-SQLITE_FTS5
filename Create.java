/**
 * Created by shilpi on 28/10/16.
 */
import java.sql.*;

public class Create {


    public static void main(String[] args) {
        System.out.println("hello world");

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
//      String dropTable="DROP TABLE music";
//           stmt.executeQuery(dropTable);

            String createTable="CREATE VIRTUAL TABLE music USING fts5(title, artist, album, lyrics, tokenize=\'ascii\'); ";

            stmt.executeUpdate(createTable);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
}


