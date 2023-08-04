package learnjdbc;

import java.sql.*;
import java.util.*;


public class BooksManager {
    private static Connection con;
    public static void main(String[] args){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","Password@123");
            Scanner sc = new Scanner(System.in);
            System.out.println("!!!!! WELCOME TO THE BOOK STORE !!!!!");
            while(true){
                System.out.println("What you want to do with Books. Press-");
                System.out.println("1 to Insert, 2 to Get, 3 to Update, 4 to Delete, 9 to exit");
                int inp = Integer.parseInt(sc.nextLine());
                if(inp == 1){
                    System.out.println("Enter Book's name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter price of Book (in Rs.)");
                    int price = Integer.parseInt(sc.nextLine());
                    if(add(name, price)){
                      System.out.println("Insert Succesfully");
                    }else{
                        System.out.println("Failed!!");
                    }
                }else if(inp == 2){
                    System.out.println("Reading all books \n");
                    printBooks();
                }else if(inp == 3){
                    System.out.println("Enter id of the book: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.println("What you want to update?  1 for Name, 2 for Price");
                    int ch = Integer.parseInt(sc.nextLine());
                    if(ch == 1){
                        System.out.println(updateName(id, sc.nextLine()) ? "Successfully Updated" : "No such book exist");
                    }else if(ch == 2){
                        System.out.println(updatePrice(id, Integer.parseInt(sc.nextLine())) ? "Successfully Updated" : "No such book exist");
                    }
                }else if(inp == 4){
                    System.out.println("Entery id of the book to be deleted: ");
                    System.out.println(deleteBook(Integer.parseInt(sc.nextLine())) ? "Book Deleted!" : "No such book found!");
                }else if(inp == 9){
                    System.out.println("Bye!");
                    break;
                }
            }
        }catch(SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }
    private static boolean add(String name, int price) throws SQLException{
        String query = "INSERT INTO books(title, price) VALUES(?,?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setInt(2, price);
        return pstmt.executeUpdate() > 0;
    }
    private static void printBooks() throws SQLException{
        Statement stmt = con.createStatement();
        String query = "SELECT * FROM books";
        ResultSet set = stmt.executeQuery(query);
        System.out.println("Id | Name | Price");
        System.out.println("------------------------");
        while(set.next()){
            System.out.println(set.getInt(1) + " | " +set.getString(2) + " | " + set.getInt(3));
        }
        System.out.println();
    }
    private static boolean updateName(int id, String name) throws SQLException{
        String query = "UPDATE books SET title = ? WHERE id = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setInt(2, id);
        return pstmt.executeUpdate() > 0;
    }
    private static boolean updatePrice(int id, int price) throws SQLException{
        String query = "UPDATE books SET price = ? WHERE id = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, price);
        pstmt.setInt(2, id);
        return pstmt.executeUpdate() > 0;
    }
    private static boolean deleteBook(int id) throws SQLException{
        String query = "DELETE FROM books WHERE id = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, id);
        return pstmt.executeUpdate() > 0;
    }
}

