package idetect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
public class hacked_keywordsDA {

   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
   //static final String DTATBASE_URL = "jdbc:mysql://localhost:3306/idetect";
   static String DTATBASE_URL;
   static String user_name ;
   static String password;
   Connection connection = null;
   Statement statement = null;

   public Vector<String> getHackedKeywords(){
     /*
     * To return an ArrayList<String> that contain all haked keywords from hacked_keywords table
     * in the database.
     */
       
       Vector<String> ar = new Vector<String>();

       try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DTATBASE_URL,user_name,password);
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM hacked_keywords");
                ResultSetMetaData metadata = resultSet.getMetaData();
                int numberOfColumens = metadata.getColumnCount();

                while(resultSet.next()) // get Next Row
                 {
                    for(int i=1; i <= numberOfColumens ; i++){
                        ar.add((String) resultSet.getObject(i));
                    }

                 }

       }
       catch (Exception ex ){
        }
        finally{
            try{
                statement.close();
                connection.close();
            }
            catch(Exception ex){
            }
        }



       return ar;

   }

   public void insertNewHackedKeyword(String keyword){
       /*
        * To insert a new nickname into hacked_keywords table in the database.
        */

       try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DTATBASE_URL,user_name,password);
                statement = connection.createStatement();
                String sql = "INSERT INTO hacked_keywords(keyword) VALUES (?);";
                java.sql.PreparedStatement pr = connection.prepareStatement(sql);
                pr.setString(1, keyword);
                int res = pr.executeUpdate();
       }
       catch (Exception ex ){
        }
        finally{
            try{
                statement.close();
                connection.close();
            }
            catch(Exception ex){
            }
        }

   }

   public void deleteHackedKeyword(String keyword){
       /*
        * To delete an exist keyword from hacked_keywords table in the database.
        */

       try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DTATBASE_URL,user_name,password);
                statement = connection.createStatement();
                String sql =("DELETE FROM hacked_keywords WHERE keyword = ?");
                java.sql.PreparedStatement pr = connection.prepareStatement(sql);
                pr.setString(1, keyword);
                int res = pr.executeUpdate();
                if(res != 0)
                    JOptionPane.showMessageDialog(null, "The DELETE operation is succesful", "DELETE HACKED KEYWORD", 1);
                else
                    throw new Exception("There is an ERROR in DELETE operation");

       }
       catch (Exception ex ){
        }
        finally{
            try{
                statement.close();
                connection.close();
            }
            catch(Exception ex){
            }
        }

   }

   public void updateHackedKeyword(String oldKeyword, String newKeyword){
       /*
        * To change an exist keyword with a new one from hacked_keywords table in the database.
        */

       try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DTATBASE_URL,user_name,password);
                statement = connection.createStatement();
                String sql =("UPDATE hacked_keywords SET keyword = ? WHERE keyword = ?");
                java.sql.PreparedStatement pr = connection.prepareStatement(sql);
                pr.setString(1, newKeyword);
                pr.setString(2, oldKeyword);
                int res = pr.executeUpdate();
                if(res != 0)
                    JOptionPane.showMessageDialog(null, "The UPDATE operation is succesful", "UPDATE HACKED KEYWORD", 1);
                else
                    throw new Exception("There is an ERROR in UPDATE operation");

       }
       catch (Exception ex ){
        }
        finally{
            try{
                statement.close();
                connection.close();
            }
            catch(Exception ex){
            }
        }

   }

}
