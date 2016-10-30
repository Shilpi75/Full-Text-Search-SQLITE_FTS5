import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

/**
 * Created by shilpi on 29/10/16.
 */
public class Query {
    static Connection c = null;
    static Statement stmt = null;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        int choice;

        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:test.db");
        System.out.println("Opened database successfully");

        stmt = c.createStatement();

        System.out.println("Menu: \n 1.Free Text Query\n2.Phrase Query\n");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            choice = Integer.parseInt(br.readLine());

            System.out.println("Enter query: ");
            String query=br.readLine().toString();

            switch (choice) {
                case 1:
                    freeTextQuery(query);
                    break;
                case 2:
                    phraseQuery(query);
                    break;
                default:
                    System.out.println("Wrong Choice");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void freeTextQuery(String query)
    {
        System.out.println(System.currentTimeMillis());

        String words[]=query.split(" ");
        String query2= String.join(" OR ", words);
        String sql="SELECT * FROM music WHERE music MATCH \' "+query2+"\' ORDER BY rank;";
        System.out.println(sql+"\n");
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while ( rs.next() ) {
                String title=rs.getString("title");
                String artist=rs.getString("artist");
                String album=rs.getString("album");

                System.out.println( "Title:  " + title );
                System.out.println( "Artist: " + artist);
                System.out.println( "Album: " + album);
                System.out.println();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }



        System.out.println(query2);


        System.out.println(System.currentTimeMillis());


    }



    public static  void  phraseQuery(String query)
    {
        System.out.println(System.currentTimeMillis());

        String sql="SELECT * FROM music WHERE music MATCH \' \""+query+"\" \' ORDER BY rank;";
        System.out.println(sql+"\n");
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while ( rs.next() ) {
                String title=rs.getString("title");
                String artist=rs.getString("artist");
                String album=rs.getString("album");

                System.out.println( "Title:  " + title );
                System.out.println( "Artist: " + artist);
                System.out.println( "Album: " + album);
                System.out.println();
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis());

    }
}
