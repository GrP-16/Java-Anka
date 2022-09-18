import java.sql.*;
public class DB {
    /**
     * 
     * @param params
     * @return Statement
     */
    public static Statement connect(String params[]){
        Statement st = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully...");
            try{
                String url = "jdbc:mysql://localhost:3306/"+ params[0];
                
              Connection conn =  DriverManager.getConnection(url, params[1], params[2] );
               st = conn.createStatement();
              return st;

            } catch(SQLException sqlEx){
                System.out.printf("Connection error: %s",sqlEx.getMessage());
            }

        } catch (ClassNotFoundException ex){
            System.out.printf("Driver Error: %s",ex.getMessage());
        }
        return st;
    }
    /**
     * 
     * @param {Statement } st
     * @param {String } query
     */
    public static void makeQuery(Statement st , String query){
        try{
         st.execute(query);
         System.out.println("\n> Query executed ");
        } catch(SQLException sql){
            System.out.printf("Sql error %s", sql.getMessage());
        }
    }
/**
 * 
 * @param s
 * @param query
 * @return
 */
    public static ResultSet getData(Statement s, String query){
        ResultSet result = null;
        try{
          result = s.executeQuery(query);
          
        } catch(SQLException ex){
            System.out.println("ResultSet error: "+ex.getMessage());
        }
        return result;
    }
}
