import java.io.*;
import java.sql.*;

/**
 * Created by shilpi on 28/10/16.
 */
public class Index {

    public static void main(String[] args) {
        Connection c = null;
        Statement stmt = null;
        int i = 0;
        try {
            System.out.println(System.currentTimeMillis());
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            File folder = new File("/home/shilpi/Desktop/music");
            File[] listOfFiles = folder.listFiles();
            String query = "INSERT INTO music(title, artist, album, lyrics) VALUES(?,?,?,?);";


            for (File file : listOfFiles) {
                if (file.isFile()) {
                    BufferedReader br = new BufferedReader(new FileReader(file));

                    String text[] = br.readLine().toString().split(":");
                    String title = text[1];
                    br.readLine();
                    String text2[] = br.readLine().toString().split(":");
                    String artist = text2[1];
                    br.readLine();
                    String text3[] = br.readLine().toString().split(":");
                    String album = text3[1];
                    br.readLine();
                    String text4[] = br.readLine().toString().split(":");
                    String lyrics = text4[1];


                    try (PreparedStatement pstmt = c.prepareStatement(query)) {
                        {
                            pstmt.setString(1, title);
                            pstmt.setString(2, artist);
                            pstmt.setString(3, album);
                            pstmt.setString(4, lyrics);
                            pstmt.executeUpdate();
                            System.out.println("inserted"+i++);
                        }
                    }
                }
            }

            stmt.close();
            System.out.println(System.currentTimeMillis());


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
