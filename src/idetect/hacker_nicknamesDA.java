package idetect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
public class hacker_nicknamesDA {

static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//static final String DTATBASE_URL = "jdbc:mysql://localhost:3306/idetect";
static String DTATBASE_URL;
static String user_name;
static String password;
Connection connection = null;
Statement statement = null;

public Vector<String> getHackerNicknames(){
    /*
     * To return an ArrayList<String> that contain all haker nicknames from hacker_nicknames table
     * in the database.
     */
       
       Vector<String> ar = new Vector<String>();

       try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DTATBASE_URL,user_name,password);
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM hacker_nicknames");
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
public void insertNewHackerNickname(String nickname){
    /*
     * To insert a new nickname into hacker_nicknames table in the database.
     */

           try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DTATBASE_URL,user_name,password);
                statement = connection.createStatement();
                String sql = "INSERT INTO hacker_nicknames(nickname) VALUES (?);";
                java.sql.PreparedStatement pr = connection.prepareStatement(sql);
                pr.setString(1, nickname);
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
public void deleteHackerNickname(String nickname){
    /*
     * To delete an exist nickname from hacker_nicknames table in the database.
     */

       try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DTATBASE_URL,user_name,password);
                statement = connection.createStatement();
                String sql =("DELETE FROM hacker_nicknames WHERE nickname = ?");
                java.sql.PreparedStatement pr = connection.prepareStatement(sql);
                pr.setString(1, nickname);
                int res = pr.executeUpdate();
                if(res != 0)
                    JOptionPane.showMessageDialog(null, "The DELETE operation is succesful", "DELETE HACKER NICKNAME", 1);
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

public void updateHackerNickname(String oldNickname, String newNickname){
    /*
     * To change an exist nickname with a new one from hacker_nicknames table in the database.
     */

       try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DTATBASE_URL,user_name,password);
                statement = connection.createStatement();
                String sql =("UPDATE hacker_nicknames SET nickname = ? WHERE nickname = ?");
                java.sql.PreparedStatement pr = connection.prepareStatement(sql);
                pr.setString(1, newNickname);
                pr.setString(2, oldNickname);
                int res = pr.executeUpdate();
                if(res != 0)
                    JOptionPane.showMessageDialog(null, "The UPDATE operation is succesful", "UPDATE HACKER NICKNAME", 1);
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
