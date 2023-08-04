package learnjdbc;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LearnJDBC {
    public static void main(String[] args) {
        // Go to Libraries in Project Section and add jar file (your database driver in this case mysql connector)
        try {
            // For loading the driver in memory
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // or
            //DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            
            // Connector, to see your mysql port login to mysql and type "\s"
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb","root","Password@123");
            
            String query = "select * from sampletable";
            Statement stmt = con.createStatement();
            ResultSet set = stmt.executeQuery(query);
            while(set.next()){
//                System.out.println(set.);
            }
            
            // close the connection
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(LearnJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    