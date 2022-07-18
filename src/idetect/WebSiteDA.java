/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idetect;

import java.nio.FloatBuffer;
import java.sql.*;
import com.mysql.jdbc.PreparedStatement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author youssef 19
 */
public class WebSiteDA {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //static final String DTATBASE_URL = "jdbc:mysql://localhost:3306/idetect";
    static String DTATBASE_URL ;
    //static final String user_name = "root";
    static  String user_name ;
    //static final String password = "barcelona19";
    static String password;
    public Connection con;
    static Connection connection = null;
    static Statement statement = null;

    public WebSiteDA() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (Exception e) {
            
        }
    }

    public static void insertWebSiteDA(String url, String active, int hash_value, String category_name, String autorun) {
        /*
         * To insert a new instance into website table in the database.
         */

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DTATBASE_URL, user_name, password);
            statement = connection.createStatement();

            String sql = "INSERT INTO website(URL,active,title_hash_value,list_name,type,autorun) VALUES (?,?,?,?,?,?);";
            java.sql.PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, url);
            pr.setString(2, active);
            pr.setInt(3, hash_value);
            pr.setString(4, category_name);
            pr.setString(5, "Hacking");
            pr.setString(6, autorun);
            int res = pr.executeUpdate();
            if (res == 0) {
                throw new Exception("There is an ERROR in inserting operation");
            }


        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
            
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
                
            }
        }

    }

    public static void updateWebSiteDA(int websiteID, String active, int hash_value) {
        /*
         * To update an exist website with new data.
         */

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DTATBASE_URL, user_name, password);
            statement = connection.createStatement();
            String sql = "UPDATE website SET active = ?, detection_technique = ?, title_hash_value = ? WHERE websiteID = ?;";
            String detectionType = "";
            java.sql.PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, active);
            pr.setString(2, detectionType);
            pr.setInt(3, hash_value);
            pr.setInt(4, websiteID);
            int res = pr.executeUpdate();
            if (res == 0) {
                throw new Exception("There is an ERROR in UPDATING operation");
            }


        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
            
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
                
            }
        }

    }

    public static void changeIs_HackedValue(int website_ID, String is_hacked, String DetectionType) {
        /*
         * To update an exist website with new data of (is_hacked) and (detectionType) values.
         */

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DTATBASE_URL,user_name, password);
            statement = connection.createStatement();

            Time_Date td = new Time_Date();
            String sysdate = td.systemDateTime();
            int minute = td.extractMinuteFromSystemdate(sysdate);
            int hour = td.extractHourFromSystemdate(sysdate);
            int day = td.extractDayFromSystemdate(sysdate);
            int mounth = td.extractMonthFromSystemdate(sysdate);
            int year = td.extracYeartFromSystemdate(sysdate);
            td.insertDetectingTime(minute, hour, day, mounth, year, "Detecting");
            int time_dateID = td.getTime_DateID(minute, hour, day, mounth, year, "Detecting");

            String sql = "UPDATE website SET is_hacked = ?, detection_technique = ?, time_date_foreign = ? WHERE websiteID = ?;";
            java.sql.PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, is_hacked);
            pr.setString(2, DetectionType);
            pr.setInt(3, time_dateID);
            pr.setInt(4, website_ID);
            int res = pr.executeUpdate();
            if (res == 0) {
                throw new Exception("There is an ERROR in UPDATING operation");
            }



        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
            
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
                
            }
        }


    }

    public static void updateHackingState(String url, String is_hacked) {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DTATBASE_URL, user_name, password);
            String sql = "UPDATE website SET is_hacked = ? WHERE URL = ?;";
            java.sql.PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, is_hacked);
            pr.setString(2, url);
            pr.executeUpdate();
        } catch (Exception ex) {
        }
        try {
            connection.close();
        } catch (SQLException ex) {
           // Logger.getLogger(WebSiteDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void changeTitle_Hash_Value(int website_ID, int hash_value) {
        /*
         * To update an exist website with new data of (hash_value) value.
         */

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DTATBASE_URL, user_name, password);
            statement = connection.createStatement();

            Time_Date td = new Time_Date();
            String sysdate = td.systemDateTime();
            int minute = td.extractMinuteFromSystemdate(sysdate);
            int hour = td.extractHourFromSystemdate(sysdate);
            int day = td.extractDayFromSystemdate(sysdate);
            int mounth = td.extractMonthFromSystemdate(sysdate);
            int year = td.extracYeartFromSystemdate(sysdate);
            td.insertDetectingTime(minute, hour, day, mounth, year, "Detecting");
            int time_dateID = td.getTime_DateID(minute, hour, day, mounth, year, "Detecting");

            String sql = "UPDATE website SET title_hash_value = ?, time_date_foreign = ? WHERE websiteID = ?;";
            java.sql.PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, hash_value);
            pr.setInt(2, time_dateID);
            pr.setInt(3, website_ID);
            int res = pr.executeUpdate();
            if (res == 0) {
                throw new Exception("There is an ERROR in UPDATING operation");
            }



        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
            
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
                
            }
        }


    }

    public static int getWebSiteID(String url) {
        /*
         * To retreive the websiteID for a given url.
         */
        int result = -1;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DTATBASE_URL, user_name, password);
            statement = connection.createStatement();
            String sql = "SELECT websiteID FROM website WHERE URL = ? AND type = 'Hacking';";
            java.sql.PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, url);
            ResultSet res = pr.executeQuery();
            res.beforeFirst();

            while (res.next()) {
                result = res.getInt("websiteID");
            }


        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
            
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
                
            }
        }

        return result;

    }

    public static int existOfwebsitIDInScript(int websiteID) {
        /*
         * To check if this websiteID is already exist in (javascript) table or not.
         */
        int result = -1;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DTATBASE_URL,user_name, password);
            statement = connection.createStatement();
            String sql = "SELECT websiteID FROM javascript WHERE websiteID = ?;";
            java.sql.PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, websiteID);
            ResultSet res = pr.executeQuery();
            res.beforeFirst();

            while (res.next()) {
                result = res.getInt("websiteID");
            }


        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
            
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
                
            }
        }

        return result;

    }

    public static int existOfwebsitIDInIframe(int websiteID) {
        /*
         * To check if this websiteID is already exist in (iframe) table or not.
         */
        int result = -1;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DTATBASE_URL, user_name, password);
            statement = connection.createStatement();
            String sql = "SELECT websiteID FROM iframe WHERE websiteID = ?;";
            java.sql.PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, websiteID);
            ResultSet res = pr.executeQuery();
            res.beforeFirst();

            while (res.next()) {
                result = res.getInt("websiteID");
            }


        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
            
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
                
            }
        }

        return result;

    }

    public static int existOfwebsitIDInMeta(int websiteID) {
        /*
         * To check if this websiteID is already exist in (meta) table or not.
         */
        int result = -1;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DTATBASE_URL, user_name, password);
            statement = connection.createStatement();
            String sql = "SELECT websiteID FROM meta WHERE websiteID = ?;";
            java.sql.PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, websiteID);
            ResultSet res = pr.executeQuery();
            res.beforeFirst();

            while (res.next()) {
                result = res.getInt("websiteID");
            }


        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
            
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
                //System.exit(1);
            }
        }

        return result;

    }

    public static void writeNewScripts(int website_ID, ArrayList<String> scripts) {
        /*
         * To write all SCRIPTs for a specific website(by using websiteID) into (script) table.
         */

        try {
            String allScripts = "";
            if (!scripts.isEmpty()) {
                int i = 0;
                do {
                    if (allScripts.equals("")) {
                        allScripts = " Script #" + Integer.toString(i) + " [" + scripts.get(i) + " ]";
                    }
                    allScripts += " Script #" + Integer.toString(i) + " [" + scripts.get(i) + " ]";
                    i++;

                } while (i < scripts.size());
            } else {
                allScripts = "There is no script!!";
            }
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DTATBASE_URL,user_name, password);
            statement = connection.createStatement();
            String sql = "INSERT INTO javascript(websiteID,script) VALUES (?,?);";
            java.sql.PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, website_ID);
            pr.setString(2, allScripts);
            int res = pr.executeUpdate();
            if (res == 0) {
                throw new Exception("There is an ERROR in inserting operation");
            }


        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
            
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
                
            }
        }
    }

    public static void updateScripts(int website_ID, ArrayList<String> scripts) {
        /*
         * To update (script) table with all new SCRIPTs for a specific website(by using websiteID).
         */

        try {
            String allScripts = "";
            if (!scripts.isEmpty()) {
                int i = 0;
                do {
                    if (allScripts.equals("")) {
                        allScripts = " Script #" + Integer.toString(i) + " [" + scripts.get(i) + " ]";
                    }
                    allScripts += " Script #" + Integer.toString(i) + " [" + scripts.get(i) + " ]";
                    i++;

                } while (i < scripts.size());
            } else {
                allScripts = "There is no script!!";
            }
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DTATBASE_URL, user_name, password);
            statement = connection.createStatement();
            String sql = "UPDATE javascript SET script = ? WHERE websiteID = ?;";
            java.sql.PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, allScripts);
            pr.setInt(2, website_ID);
            int res = pr.executeUpdate();
            if (res == 0) {
                throw new Exception("There is an ERROR in UPDATING operation");
            }


        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
            
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
                
            }
        }
    }

    public static void writeNewIframes(int website_ID, ArrayList<String> iframe) {
        /*
         * To write all IFRAMEs for a specific website(by using websiteID) into (iframe) table.
         */

        try {
            String allIframe = "";
            if (!iframe.isEmpty()) {
                int i = 0;
                do {
                    if (allIframe.equals("")) {
                        allIframe = " Iframe #" + Integer.toString(i) + " [" + iframe.get(i) + " ]";
                    }
                    allIframe += " Iframe #" + Integer.toString(i) + " [" + iframe.get(i) + " ]";
                    i++;

                } while (i < iframe.size());
            } else {
                allIframe = "There is no Iframe!!";
            }
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DTATBASE_URL, user_name, password);
            statement = connection.createStatement();
            String sql = "INSERT INTO iframe(websiteID,iframe) VALUES (?,?);";
            java.sql.PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, website_ID);
            pr.setString(2, allIframe);
            int res = pr.executeUpdate();
            if (res == 0) {
                throw new Exception("There is an ERROR in inserting operation");
            }


        } catch (Exception ex) {
           // JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
            
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
                
            }
        }
    }

    public static void updateIframes(int website_ID, ArrayList<String> iframe) {
        /*
         * To update (iframe) table with all new IFRAMEs for a specific website(by using websiteID).
         */

        try {
            String allIframe = "";
            if (!iframe.isEmpty()) {
                int i = 0;
                do {
                    if (allIframe.equals("")) {
                        allIframe = " Iframe #" + Integer.toString(i) + " [" + iframe.get(i) + " ]";
                    }
                    allIframe += " Iframe #" + Integer.toString(i) + " [" + iframe.get(i) + " ]";
                    i++;

                } while (i < iframe.size());
            } else {
                allIframe = "There is no Iframe!!";
            }
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DTATBASE_URL,user_name, password);
            statement = connection.createStatement();
            String sql = "UPDATE iframe SET iframe = ? WHERE websiteID = ?;";
            java.sql.PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, allIframe);
            pr.setInt(2, website_ID);
            int res = pr.executeUpdate();
            if (res == 0) {
                throw new Exception("There is an ERROR in UPDATING operation");
            }


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
            System.exit(1);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
                
            }
        }
    }

    public static void writeNewMetas(int website_ID, ArrayList<String> metas) {
        /*
         * To write all METAs for a specific website(by using websiteID) into (meta) table.
         */

        try {
            String allMetas = "";
            if (!metas.isEmpty()) {
                int i = 0;
                do {
                    if (allMetas.equals("")) {
                        allMetas = " Meta #" + Integer.toString(i) + " [" + metas.get(i) + " ]";
                    }
                    allMetas += " Meta #" + Integer.toString(i) + " [" + metas.get(i) + " ]";
                    i++;

                } while (i < metas.size());
            } else {
                allMetas = "There is no meta!!";
            }
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DTATBASE_URL, user_name, password);
            statement = connection.createStatement();
            String sql = "INSERT INTO meta(websiteID,meta) VALUES (?,?);";
            java.sql.PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, website_ID);
            pr.setString(2, allMetas);
            int res = pr.executeUpdate();
            if (res == 0) {
                throw new Exception("There is an ERROR in inserting operation");
            }


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
            System.exit(1);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
                
            }
        }
    }

    public static void updateMetas(int website_ID, ArrayList<String> metas) {
        /*
         * To update (meta) table with all new METAs for a specific website(by using websiteID).
         */

        try {
            String allMetas = "";
            if (!metas.isEmpty()) {
                int i = 0;
                do {
                    if (allMetas.equals("")) {
                        allMetas = " Meta #" + Integer.toString(i) + " [" + metas.get(i) + " ]";
                    }
                    allMetas += " Meta #" + Integer.toString(i) + " [" + metas.get(i) + " ]";
                    i++;

                } while (i < metas.size());
            } else {
                allMetas = "There is no meta!!";
            }
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DTATBASE_URL, user_name, password);
            statement = connection.createStatement();
            String sql = "UPDATE meta SET meta = ? WHERE websiteID = ?;";
            java.sql.PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, allMetas);
            pr.setInt(2, website_ID);
            int res = pr.executeUpdate();
            if (res == 0) {
                throw new Exception("There is an ERROR in UPDATING operation");
            }


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
            System.exit(1);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
                
            }
        }
    }

    public static boolean checkHackingAvailablity(String URL) {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DTATBASE_URL, user_name, password);
            String sql = "UPDATE website SET URL = ? where URL = ? and type = ?;";
            java.sql.PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, URL);
            pr.setString(2, "Hacking");
            int j = pr.executeUpdate();
            if (j > 0) {
                return true;
            }
        } catch (Exception ex) {
        }
        try {
            connection.close();
        } catch (SQLException ex) {
         //   Logger.getLogger(WebSiteDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public static ArrayList<String> getHackingWebsite(DefaultTableModel df, String url, String type) {
        ArrayList<String> isPhishedValues = new ArrayList<String>();
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DTATBASE_URL, user_name, password);
            String Url = "";
            String sql = "SELECT w.URL,w.active,w.is_hacked,w.title_hash_value,w.detection_technique,t.year,t.month,t.day,t.hour,t.minute FROM  time_date as t,website as w  WHERE t.time_dateID = w.time_date_foreign   AND w.URL like  ? AND w.type = ?;";
            java.util.Vector<String> v = new java.util.Vector<String>();
            v.add("URL");
            v.add("Online?");
            v.add("Safe?");
            v.add("Title hash value");
            v.add("Detection By");
            v.add("Checking Time");
            df.setColumnIdentifiers(v);
            ResultSet rs = null;
            java.sql.ResultSetMetaData md = null;
            int count = 0;

            java.sql.PreparedStatement pr = connection.prepareStatement(sql);
            if (url.equals("%%")) {
                Url = "%%";
            } else {
                Url = "%" + url + "%";
            }
            pr.setString(1, Url);
            pr.setString(2, type);
            rs = pr.executeQuery();
            rs.beforeFirst();
            md = rs.getMetaData();
            count = md.getColumnCount();
            Object[] o = new Object[count];
            while (rs.next()) {
                o[0] = (Object) rs.getString("URL");
                o[1] = (Object) rs.getString("active");
                o[2] = (Object) rs.getString(3);
                isPhishedValues.add(rs.getString(3)); /// return phishing value for modification purpose ////
                o[3] = (Object) rs.getString(4);
                o[4] = (Object) rs.getString(5);
                String time = String.valueOf(rs.getInt("hour")) + ":" + String.valueOf(rs.getInt("minute")) + "  " + String.valueOf(rs.getInt("day")) + "/" + String.valueOf(rs.getInt("month")) + "/" + String.valueOf(rs.getInt("year"));
                o[5] = (Object) time;
                df.addRow(o);
            }

            //jt.setModel(df);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        try {
            connection.close();
        } catch (SQLException ex) {
         //   Logger.getLogger(WebSiteDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isPhishedValues;
    }

    public static void getHackingResults(DefaultTableModel df, String category_name) {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DTATBASE_URL, user_name, password);
            String sql = "SELECT w.URL,w.active,w.is_hacked,w.title_hash_value,w.detection_technique,t.year,t.month,t.day,t.hour,t.minute FROM  time_date as t,website as w  WHERE t.time_dateID = w.time_date_foreign   AND w.list_name = ? AND type = 'Hacking';";
            java.util.Vector<String> v = new java.util.Vector<String>();
            v.add("URL");
            v.add("Online?");
            v.add("Safe?");
            v.add("Title hash value");
            v.add("Detection By");
            v.add("Checking Time");
            df.setColumnIdentifiers(v);
            ResultSet rs = null;
            java.sql.ResultSetMetaData md = null;
            int count = 0;
            java.sql.PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, category_name);
            rs = pr.executeQuery();
            rs.beforeFirst();
            md = rs.getMetaData();
            count = md.getColumnCount();
            Object[] o = new Object[count];
            while (rs.next()) {
                o[0] = (Object) rs.getString("URL");
                o[1] = (Object) rs.getString("active");
                o[2] = (Object) rs.getString(3);
                o[3] = (Object) rs.getString(4);
                o[4] = (Object) rs.getString(5);
                String time = String.valueOf(rs.getInt("hour")) + ":" + String.valueOf(rs.getInt("minute")) + "  " + String.valueOf(rs.getInt("day")) + "/" + String.valueOf(rs.getInt("month")) + "/" + String.valueOf(rs.getInt("year"));
                o[5] = (Object) time;
                df.addRow(o);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        try {
            connection.close();
        } catch (SQLException ex) {
           // Logger.getLogger(WebSiteDA.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
/////// Youssef part //////////

    public boolean checkAvailablity(String URL, String website_type) {
        this.openConnection();
        String sql = "UPDATE website SET URL = ? where URL = ? and website_type = ? ;";
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, URL);
            pr.setString(2, URL);
            pr.setString(3, website_type);
            int i = pr.executeUpdate();
            if (i >= 1) {
                return true;
            }
        } catch (Exception e) {
           // JOptionPane.showMessageDialog(null, e.getMessage());
        }
        this.closeConnection();
        return false;
    }

    public void insertOfflineWebsite(String URL, String website_type, String original_website, int foreign_Key) {
        this.openConnection();
        String sql = "INSERT INTO website(URL,active,isPhished,website_type,original_website,time_date_foreign,type) values (?,?,'Offline',?,?,?,'Phishing');";
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, URL);
            pr.setString(2, "Offline");
            //pr.setInt(3, day);
            // pr.setInt(4, month);
            // pr.setInt(5, year);
            pr.setString(3, website_type);
            pr.setString(4, original_website);
            pr.setInt(5, foreign_Key);
            //pr.setInt(8, mnute);
            //pr.setInt(9, hour);
            int i = pr.executeUpdate();
            if (i >= 1) {
                //   JOptionPane.showMessageDialog(null, "Done");
            }
        } catch (Exception e) {
           // JOptionPane.showMessageDialog(null, e.getMessage());

        }
        this.closeConnection();

    }

    public void updateOfflineWebsite(String URL, String phishing_type, String original_website, int foreign) {
        this.openConnection();
        String sql = "UPDATE website SET URL = ? , active = ?,website_type = ?,original_website = ? , time_date_foreign = ? , isPhished = 'Offline' WHERE URL = ?;";
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, URL);
            pr.setString(2, "Offline");
            // pr.setInt(3, day);
            //pr.setInt(4, month);
            //pr.setInt(5, year);
            pr.setString(3, phishing_type);
            pr.setString(4, original_website);
            pr.setInt(5, foreign);
            //pr.setInt(8, minute);
            //pr.setInt(9, hour);
            pr.setString(6, URL);
            int i = pr.executeUpdate();
            if (i == 1) {
                //   JOptionPane.showMessageDialog(null, "Data update");
            }
        } catch (Exception e) {
           // JOptionPane.showMessageDialog(null, e.getMessage());
        }
        this.closeConnection();
    }

    public void insertWithoutSourceCode(String URL, String phishing, String active, String website_type, String original_website, String list_name, int foreign) {
        this.openConnection();
        String sql = "INSERT INTO website(URL,isPhished,active,website_type,original_website,list_name,time_date_foreign,type) VALUES(?,?,?,?,?,?,?,'Phishing');";
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, URL);
            pr.setString(2, phishing);
            pr.setString(3, active);
            pr.setString(4, website_type);
            pr.setString(5, original_website);
            pr.setString(6, list_name);
            pr.setInt(7, foreign);
            int i = pr.executeUpdate();
            if (i >= 1) {
                //  JOptionPane.showMessageDialog(null, "Done");
            }
        } catch (Exception e) {
         //   JOptionPane.showMessageDialog(null, e.getMessage());
        }
        this.closeConnection();
    }

    public void updateWithoutSourceCode(String URL, String phishing, String active, String website_type, String original_website, int foreign) {
        this.openConnection();
        String sql = "UPDATE website SET URL = ? , isPhished = ? , active = ? , website_type = ?,original_website = ? ,time_date_foreign = ? WHERE URL = ? AND website_type = ?;";
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, URL);
            pr.setString(2, phishing);
            pr.setString(3, active);
            pr.setString(4, website_type);
            pr.setString(5, original_website);
            pr.setInt(6, foreign);
            pr.setString(7, URL);
            pr.setString(8, website_type);
            int i = pr.executeUpdate();
            if (i == 1) {
                //   JOptionPane.showMessageDialog(null, "Data Updated");
            }
        } catch (Exception e) {
           // JOptionPane.showMessageDialog(null, e.getMessage(), "Wrong entry", 0);
        }
        this.closeConnection();
    }

    public void deleteWebsite(String URL, String type, String list_name) {
        /*
         * Delete given website
         */
        this.openConnection();
        try {
            String sql = "DELETE FROM website WHERE URL  = ? AND type = ? AND list_name = ?;";
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, URL);
            pr.setString(2, type);
            pr.setString(3, list_name);
            pr.executeUpdate();
        } catch (Exception ex) {
           // JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.closeConnection();
    }

    public void updatePhishingState(String url, String isPhished) {
        /*
         * Update isPhished column in both tables(history,website) for specific URL
         */
        this.openConnection();
        String sql = "UPDATE website SET isPhished = ? WHERE URL = ?;";
        String SQL = "UPDATE history SET isPhished = ? WHERE URL = ?;";
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            java.sql.PreparedStatement pr1 = con.prepareStatement(SQL);
            pr.setString(1, isPhished);
            pr1.setString(1, isPhished);
            pr.setString(2, url);
            pr1.setString(2, url);
            pr.executeUpdate();
            pr1.executeUpdate();
        } catch (Exception ex) {
           // JOptionPane.showMessageDialog(null, ex);
        }
        this.closeConnection();
    }

    public String getWebsitePhishingState(String URL) {
        this.openConnection();
        String sql = "SELECT isPhished FROM website WHERE URL = ?;";
        String s = "";
        java.sql.ResultSet rs = null;
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, URL);
            rs = pr.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                s = rs.getString("isPhished");
            }
        } catch (Exception ee) {
           // JOptionPane.showMessageDialog(null, ee.getMessage());

        }
        this.closeConnection();
        return s;
    }

    public ArrayList<String> getWebsite(DefaultTableModel df, String url, String type) {
        /*
         * Get the information(Valid?,Online... etc) for specific URL
         */
        this.openConnection();
        String Url = "";
        String sql = "SELECT h.URL,h.active,h.isPhished,w.original_website,t.year,t.month,t.day,t.hour,t.minute FROM  time_date as t,website as w ,history as h WHERE t.time_dateID = h.time_date_foreign AND w.websiteID = h.websiteID_foreign  AND h.URL like  ? AND w.type = ? ORDER BY h.isPhished;";
        ArrayList<String> isPhishedValues = new ArrayList<String>();
        java.util.Vector<String> v = new java.util.Vector<String>();
        v.add("URL");
        v.add("Online?");
        v.add("Valid?");
        v.add("Original Website");
        v.add("Checking Time");
        df.setColumnIdentifiers(v);
        ResultSet rs = null;
        java.sql.ResultSetMetaData md = null;
        int count = 0;
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            if (url.equals("%%")) {
                Url = "%%";
            } else {
                Url = "%" + url + "%";
            }
            pr.setString(1, Url);
            pr.setString(2, type);
            rs = pr.executeQuery();
            rs.beforeFirst();
            md = rs.getMetaData();
            count = md.getColumnCount();
            Object[] o = new Object[count];
            while (rs.next()) {
                o[0] = (Object) rs.getString("URL");
                o[1] = (Object) rs.getString("active");
                o[2] = (Object) rs.getString("isPhished");
                isPhishedValues.add(rs.getString("isPhished")); /// return phishing value for modification purpose ////
                o[3] = (Object) rs.getString("original_website");
                String time = String.valueOf(rs.getInt("hour")) + ":" + String.valueOf(rs.getInt("minute")) + "  " + String.valueOf(rs.getInt("day")) + "/" + String.valueOf(rs.getInt("month")) + "/" + String.valueOf(rs.getInt("year"));
                o[4] = (Object) time;
                df.addRow(o);
            }

            //jt.setModel(df);
        } catch (Exception e) {
           // JOptionPane.showMessageDialog(null, e.getMessage());

        }
        this.closeConnection();
        return isPhishedValues;

    }

    public void getCategoryAllWebsites(DefaultTableModel df, String category_name, String type) {
        /*
         * Get the information(Valid?,Online... etc) for specific URL
         */
        this.openConnection();
        String sql = "";
        java.util.Vector<String> v = new java.util.Vector<String>();
        if (type.equals("Phishing")) {
            sql = "SELECT h.URL,h.active,h.isPhished,w.original_website,t.year,t.month,t.day,t.hour,t.minute FROM  time_date as t,website as w ,history as h WHERE t.time_dateID = h.time_date_foreign AND w.websiteID = h.websiteID_foreign  AND w.list_name =  ? AND w.type = ?;";
            v.add("URL");
            v.add("Online?");
            v.add("Valid?");
            v.add("Original Website");
            v.add("Checking Time");
        } else {
            sql = "SELECT w.URL,w.active,w.is_hacked,w.title_hash_value,w.detection_technique,t.year,t.month,t.day,t.hour,t.minute FROM  time_date as t,website as w  WHERE t.time_dateID = w.time_date_foreign  AND w.list_name = ? AND w.type = ?;";
            v.add("URL");
            v.add("Online?");
            v.add("Safe?");
            v.add("Title_hash_value");
            v.add("Detection by");
            v.add("Checking Time");
        }
        df.setColumnIdentifiers(v);
        ResultSet rs = null;
        java.sql.ResultSetMetaData md = null;
        int count = 0;
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, category_name);
            pr.setString(2, type);
            rs = pr.executeQuery();
            rs.beforeFirst();
            md = rs.getMetaData();
            count = md.getColumnCount();
            Object[] o = new Object[count];
            while (rs.next()) {
                if (type.equals("Phishing")) {
                    o[0] = (Object) rs.getString("URL");
                    o[1] = (Object) rs.getString("active");
                    o[2] = (Object) rs.getString("isPhished");
                    o[3] = (Object) rs.getString("original_website");
                    String time = String.valueOf(rs.getInt("hour")) + ":" + String.valueOf(rs.getInt("minute")) + "  " + String.valueOf(rs.getInt("day")) + "/" + String.valueOf(rs.getInt("month")) + "/" + String.valueOf(rs.getInt("year"));
                    o[4] = (Object) time;
                } else {
                    o[0] = (Object) rs.getString("URL");
                    o[1] = (Object) rs.getString("active");
                    o[2] = (Object) rs.getString(3);
                    o[3] = (Object) rs.getString(4);
                    o[4] = (Object) rs.getString(5);
                    String time = String.valueOf(rs.getInt("hour")) + ":" + String.valueOf(rs.getInt("minute")) + "  " + String.valueOf(rs.getInt("day")) + "/" + String.valueOf(rs.getInt("month")) + "/" + String.valueOf(rs.getInt("year"));
                    o[5] = (Object) time;
                }
                df.addRow(o);
            }
        } catch (Exception e) {
           // JOptionPane.showMessageDialog(null, e.getMessage());

        }
        this.closeConnection();
    }

    public void getAllWebsitesByState(DefaultTableModel df, String state, String type) {
        /*
         * Get the information(Valid?,Online... etc) for specific state
         */
        this.openConnection();
        String sql = "";
        java.util.Vector<String> v = new java.util.Vector<String>();
        if (type.equals("Phishing")) {
            sql = "SELECT h.URL,h.active,h.isPhished,w.original_website,t.year,t.month,t.day,t.hour,t.minute FROM  time_date as t,website as w ,history as h WHERE t.time_dateID = h.time_date_foreign AND w.websiteID = h.websiteID_foreign  AND h.isPhished =  ? AND w.type = ?;";
            v.add("URL");
            v.add("Online?");
            v.add("Valid?");
            v.add("Original Website");
            v.add("Checking Time");
        } else {
            sql = "SELECT w.URL,w.active,w.is_hacked,w.title_hash_value,w.detection_technique,t.year,t.month,t.day,t.hour,t.minute FROM  time_date as t,website as w  WHERE t.time_dateID = w.time_date_foreign  AND w.is_hacked = ? AND w.type = ?;";
            v.add("URL");
            v.add("Online?");
            v.add("Safe?");
            v.add("Title_hash_value");
            v.add("Detection by");
            v.add("Checking Time");
        }
        df.setColumnIdentifiers(v);
        ResultSet rs = null;
        java.sql.ResultSetMetaData md = null;
        int count = 0;
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, state);
            pr.setString(2, type);
            rs = pr.executeQuery();
            rs.beforeFirst();
            md = rs.getMetaData();
            count = md.getColumnCount();
            Object[] o = new Object[count];
            while (rs.next()) {
                if (type.equals("Phishing")) {
                    o[0] = (Object) rs.getString("URL");
                    o[1] = (Object) rs.getString("active");
                    o[2] = (Object) rs.getString("isPhished");
                    o[3] = (Object) rs.getString("original_website");
                    String time = String.valueOf(rs.getInt("hour")) + ":" + String.valueOf(rs.getInt("minute")) + "  " + String.valueOf(rs.getInt("day")) + "/" + String.valueOf(rs.getInt("month")) + "/" + String.valueOf(rs.getInt("year"));
                    o[4] = (Object) time;
                } else {
                    o[0] = (Object) rs.getString("URL");
                    o[1] = (Object) rs.getString("active");
                    o[2] = (Object) rs.getString(3);
                    o[3] = (Object) rs.getString(4);
                    o[4] = (Object) rs.getString(5);
                    String time = String.valueOf(rs.getInt("hour")) + ":" + String.valueOf(rs.getInt("minute")) + "  " + String.valueOf(rs.getInt("day")) + "/" + String.valueOf(rs.getInt("month")) + "/" + String.valueOf(rs.getInt("year"));
                    o[5] = (Object) time;
                }
                df.addRow(o);
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());

        }
        this.closeConnection();
    }

    public java.util.Vector<String> getOriginalURLs(String list_name, String type) {
        /*
         * return all websites for specific category
         */
        this.openConnection();
        String sql = "SELECT URL FROM website WHERE list_name = ? AND type = ?;";
        java.util.Vector<String> ar = new java.util.Vector<String>();
        ResultSet rs = null;
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, list_name);
            pr.setString(2, type);
            rs = pr.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                if (!rs.getString("URL").isEmpty()) {
                    ar.add(rs.getString("URL"));
                }
            }
        } catch (Exception e) {
           // JOptionPane.showMessageDialog(null, e.getMessage());

        }
        this.closeConnection();
        return ar;
    }

    public java.util.Vector<String> getAllWebsites(String type) {
        this.openConnection();
        String sql = "SELECT DISTINCT URL FROM website WHERE type = ? ;";
        java.util.Vector<String> URLs = new java.util.Vector<String>();
        ResultSet rs = null;
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, type);
            rs = pr.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                if (!rs.getString("URL").isEmpty()) {
                    URLs.add(rs.getString("URL"));
                }
            }
        } catch (Exception ex) {
        }
        this.closeConnection();
        return URLs;
    }

    public void updateOriginalURLs(String url, String oldUrl, String list_name) {
        /*
         * Update URL value
         */
        this.openConnection();
        String sql = "UPDATE website SET URL = ? WHERE URL = ? AND list_name = ?;";
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, url);
            pr.setString(2, oldUrl);
            pr.setString(3, list_name);
            pr.executeUpdate();
        } catch (Exception ex) {
        }
        this.closeConnection();
    }

    public ArrayList<ArrayList> getEnumerationWebsites(String original_website) {
        this.openConnection();
        ArrayList<ArrayList> saveCol = new ArrayList<ArrayList>();
        ArrayList<String> saveRow = new ArrayList<String>();
        ResultSet rs = null;
        String sql = "SELECT URL,active,isPhished,time_date_foreign FROM website WHERE website_type = ? and original_website = ?;";
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, "Enumeration");
            pr.setString(2, original_website);
            rs = pr.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                saveRow.add(rs.getString("URL"));
                saveRow.add(rs.getString("active"));
                saveRow.add(rs.getString("isPhished"));
                saveRow.add(String.valueOf(rs.getInt("time_date_foreign")));
                saveRow.add(String.valueOf(this.getWebsiteID(original_website, "Original")));
                saveCol.add((ArrayList) saveRow.clone());
                saveRow.clear();
            }
        } catch (Exception ex) {
        }
        this.closeConnection();
        return saveCol;
    }

    public ArrayList<String> getOriginalWebsite(String url) {
        this.openConnection();
        ArrayList<String> save = new ArrayList<String>();
        try {
            String sql = "SELECT URL,active,isPhished,time_date_foreign FROM website WHERE URL = ?;";
            ResultSet rs = null;
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, url);
            rs = pr.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                save.add(rs.getString("URL"));
                save.add(rs.getString("active"));
                save.add(rs.getString("isPhished"));
                save.add(String.valueOf(rs.getInt("time_date_foreign")));
                return save;
            }

        } catch (SQLException ex) {
           // Logger.getLogger(WebSiteDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConnection();
        return save;
    }

    public void deleteEnumerationWebsite(String original_website) {
        this.openConnection();
        String sql = "DELETE FROM website WHERE original_website = ?;";
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, original_website);
            pr.executeUpdate();
        } catch (Exception ex) {
            //Logger.getLogger(WebSiteDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConnection();
    }

    public int getWebsiteID(String URL, String website_type) {
        this.openConnection();
        String sql = "SELECT websiteID FROM website WHERE URL =? AND website_type = ?;";
        int websiteID = -1;
        ResultSet rs = null;
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, URL);
            pr.setString(2, website_type);
            rs = pr.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                websiteID = rs.getInt("websiteID");

            }
        } catch (Exception ex) {
        }
        this.closeConnection();
        return websiteID;
    }

    public void getDetectingCategoryWebsites(DefaultTableModel df, String list_name) {
        this.openConnection();
        String sql = "SELECT URL,active,isPhished,year,month,day,hour,minute FROM time_date JOIN website ON time_date_foreign = time_dateID WHERE list_name =? AND type = 'Phishing' AND active is not null;";
        ResultSet rs = null;


        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            java.util.Vector<String> v = new java.util.Vector<String>();
            v.add("URL");
            v.add("Online?");
            v.add("Valid?");
            v.add("Checking Time");
            df.setColumnIdentifiers(v);
            pr.setString(1, list_name);
            rs = pr.executeQuery();
            rs.beforeFirst();
            ResultSetMetaData rms = rs.getMetaData();
            Object[] o = new Object[rms.getColumnCount()];
            while (rs.next()) {
                if (!rs.getString(1).isEmpty()) {
                    o[0] = rs.getString(1);
                    o[1] = rs.getString(2);
                    o[2] = rs.getString(3);
                    o[3] = (Object) String.valueOf(rs.getInt("hour")) + ":" + String.valueOf(rs.getInt("minute")) + "  " + String.valueOf(rs.getInt("day")) + "/" + String.valueOf(rs.getInt("month")) + "/" + String.valueOf(rs.getInt("year"));
                    df.addRow(o);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(WebSiteDA.class.getName()).log(Level.SEVERE, null, ex);

        }
        this.closeConnection();

    }

    public void getAutoRunResults(DefaultTableModel df, String list_name) {
        /*
         * Get phishing results provided by auto run
         */
        this.openConnection();
        String sql = "SELECT URL,active,isPhished,year,month,day,hour,minute FROM time_date JOIN website ON time_date_foreign = time_dateID WHERE list_name =? AND type = 'Phishing' AND active is not null AND autorun = 'yes';";
        ResultSet rs = null;


        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            java.util.Vector<String> v = new java.util.Vector<String>();
            v.add("URL");
            v.add("Online?");
            v.add("Valid?");
            v.add("Checking Time");
            df.setColumnIdentifiers(v);
            pr.setString(1, list_name);
            rs = pr.executeQuery();
            rs.beforeFirst();
            ResultSetMetaData rms = rs.getMetaData();
            Object[] o = new Object[rms.getColumnCount()];
            while (rs.next()) {
                if (!rs.getString(1).isEmpty()) {
                    o[0] = rs.getString(1);
                    o[1] = rs.getString(2);
                    o[2] = rs.getString(3);
                    o[3] = (Object) String.valueOf(rs.getInt("hour")) + ":" + String.valueOf(rs.getInt("minute")) + "  " + String.valueOf(rs.getInt("day")) + "/" + String.valueOf(rs.getInt("month")) + "/" + String.valueOf(rs.getInt("year"));
                    df.addRow(o);
                }
            }

        } catch (Exception ex) {
           // Logger.getLogger(WebSiteDA.class.getName()).log(Level.SEVERE, null, ex);

        }
        this.closeConnection();
    }

    public java.util.Vector<String> getCategoryNames(String type) {
        java.util.Vector<String> v = new java.util.Vector<String>();
        try {
            this.openConnection();
            ResultSet rs = null;
            String sql = "SELECT DISTINCT list_name FROM website WHERE type = ?;";
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, type);
            rs = pr.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {

                v.add(rs.getString("list_name"));

            }
        } catch (SQLException ex) {
           // Logger.getLogger(WebSiteDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConnection();
        return v;

    }

    public void saveWebsites(String list_name, String URL, String autorun) {
        /*
         * insert website with category name to the DB
         */
        this.openConnection();
        String sql = "INSERT INTO website (URL,website_type,list_name,type,autorun) VALUES (?,?,?,?,?);";
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, URL);
            pr.setString(2, "Original");
            pr.setString(3, list_name);
            pr.setString(4, "Phishing");
            pr.setString(5, autorun);
            pr.executeUpdate();
        } catch (Exception ex) {
        }
        this.closeConnection();
    }

    public void saveEnumeration(String first, String second) {
        this.openConnection();
        try {
            String sql = "INSERT INTO customizeenumeration VALUES(?,?);";
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, first);
            pr.setString(2, second);
            pr.executeUpdate();
        } catch (Exception ex) {
        }
        this.closeConnection();
    }

    public java.util.Vector<Character> getFirstEnumeration() {
        this.openConnection();
        java.util.Vector<Character> v = new java.util.Vector<Character>();
        ResultSet rs = null;
        try {
            String sql = "SELECT firstReplace FROM customizeenumeration;";
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {

                v.add(rs.getString(1).charAt(0));
            }

        } catch (Exception ex) {
        }

        this.closeConnection();
        return v;
    }

    public java.util.Vector<Character> getSecondEnumeration() {
        this.openConnection();
        java.util.Vector<Character> v = new java.util.Vector<Character>();
        ResultSet rs = null;
        try {
            String sql = "SELECT secondReplace FROM customizeenumeration;";
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                v.add(rs.getString(1).charAt(0));
            }

        } catch (Exception ex) {
        }

        this.closeConnection();
        return v;
    }

    public void tryDataBase() throws SQLException {
        /*
         * Test if DB started or not
         */
        
            con = DriverManager.getConnection(DTATBASE_URL, user_name, password);
            ResultSet rs = null;
            String sql = "SELECT * FROM website;";
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
        
        this.closeConnection();
    }

    public boolean getAllEnumeration(String first, String second) {
        /*
         * Find out if given replacments found in DB
         */
        this.openConnection();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM customizeenumeration WHERE firstReplace = ? AND secondReplace = ? ;";
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, first);
            pr.setString(2, second);
            rs = pr.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                return true;
            }

        } catch (Exception ex) {
        }

        this.closeConnection();
        return false;
    }

    public void saveWebsitesFromExternalFile(File file, String type) {
        this.openConnection();
        String sql = "";
        int val = -1;
        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            try {
                String line = null;
                if (type.equals("Hacking")) {
                    sql = "INSERT INTO website (URL,list_name,type,autorun)VALUES (?,'None','Hacking','No');";
                } else { 
                    sql = "INSERT INTO website(URL,website_type,list_name,type,autorun) VALUES (?,'Original','None','Phishing','No');";
                }

                while ((line = input.readLine()) != null && val != 0) {
                    java.sql.PreparedStatement pr = con.prepareStatement(sql);
                    pr.setString(1, line);
                    val = pr.executeUpdate();
                }
            } finally {
                input.close();
            }

        } catch (Exception ex) {
        }
        this.closeConnection();
    }
    /////////////////////// Statistical queries ////////////////////////////

    public void specificPeriodQuery(DefaultTableModel df, int year, int month, int day, String type) {
        this.openConnection();
        String sql = "";
        ResultSet rs = null;
        java.util.Vector<String> v = new java.util.Vector<String>();
        try {
            if (type.equals("Phishing")) {

                sql = "SELECT URL,active,isPhished FROM history JOIN time_date ON time_date_foreign = time_dateID WHERE year = ? AND month  = ? AND day = ?;";
            } else {
                sql = "SELECT URL,active, is_hacked FROM website JOIN time_date ON time_date_foreign = time_dateID WHERE year = ? AND month  = ? AND day = ? AND type = 'Hacking'; ";
            }
            v.add("URL");
            v.add("Online?");
            v.add("Safe?");
            df.setColumnIdentifiers(v);
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, year);
            pr.setInt(2, month);
            pr.setInt(3, day);
            rs = pr.executeQuery();
            rs.beforeFirst();
            ResultSetMetaData rms = rs.getMetaData();
            Object[] o = new Object[rms.getColumnCount()];
            while (rs.next()) {
                o[0] = (Object) rs.getString(1);
                o[1] = (Object) rs.getString(2);
                o[2] = (Object) rs.getString(3);
                df.addRow(o);
            }
        } catch (Exception ex) {
        }
        this.closeConnection();
    }

    public void mostAttackedQuery(DefaultTableModel df) {
        this.openConnection();
        ResultSet rs = null;
        java.util.Vector<String> v = new java.util.Vector<String>();
        try {
            String sql = "SELECT URL,COUNT(websiteID_foreign) as attacked_times FROM history WHERE isPhished = 'Suspicious' OR isPhished = 'InValid' GROUP BY URL;";
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            v.add("URL");
            v.add("Attacked times");
            df.setColumnIdentifiers(v);
            rs = pr.executeQuery();
            rs.beforeFirst();
            ResultSetMetaData rms = rs.getMetaData();
            Object[] o = new Object[rms.getColumnCount()];
            while (rs.next()) {
                o[0] = (Object) rs.getString(1);
                o[1] = (Object) rs.getInt(2);
                df.addRow(o);
            }

        } catch (Exception ex) {
        }
        this.closeConnection();
    }

    public void periodQuery(DefaultTableModel df, int startYear, int startMonth, int endYear, int endMonth, String type) {
        /*
         * Return websites in specific duration
         */
        this.openConnection();
        java.util.Vector<String> v = new java.util.Vector<String>();
        v.add("URL");
        v.add("Online?");
        v.add("Safe?");
        df.setColumnIdentifiers(v);
        try {
            String sql;
            String sql2 = "";
            java.sql.PreparedStatement pr;
            java.sql.PreparedStatement pr2;
            ResultSet rs = null;
            ResultSet rs2 = null;
            ResultSetMetaData rms = null;
            if (startYear == endYear) {
                if (type.equals("Phishing")) {
                    sql = "select URL,active,isPhished from history join time_date on time_date_foreign = time_dateID where year = ? and month < ? and month > ?;";
                } else {
                    sql = "select URL,active,is_hacked from website join time_date on time_date_foreign = time_dateID where type = 'Hacking' AND year = ? and month < ? and month > ?;";
                }
                pr = con.prepareStatement(sql);
                pr.setInt(1, startYear);
                pr.setInt(2, endMonth);
                pr.setInt(3, startMonth);
            } else {
                if (type.equals("Phishing")) {
                    sql = "select URL,active,isPhished from history join time_date on time_date_foreign = time_dateID where year = ? and month > ?;";
                    sql2 = "select URL,active,isPhished from history join time_date on time_date_foreign = time_dateID where year = ? and month < ?;";
                    pr = con.prepareStatement(sql);
                    pr.setInt(1, startYear);
                    pr.setInt(2, startMonth);
                    pr2 = con.prepareStatement(sql2);
                    pr2.setInt(1, endYear);
                    pr2.setInt(2, endMonth);
                    rs2 = pr2.executeQuery();
                    rs2.beforeFirst();
                } else {
                    sql = "select URL,active,is_hacked from website join time_date on time_date_foreign = time_dateID where type = 'Hacking' AND year = ? and month > ?  union select URL,active,is_hacked from website join time_date on time_date_foreign = time_dateID where type = 'Hacking' AND year = ? and month < ?;";
                    pr = con.prepareStatement(sql);
                    pr.setInt(1, startYear);
                    pr.setInt(2, startMonth);
                    pr.setInt(3, endYear);
                    pr.setInt(4, endMonth);
                }
            }


            rs = pr.executeQuery();
            rs.beforeFirst();
            rms = rs.getMetaData();
            Object[] o = new Object[rms.getColumnCount()];
            while (rs.next()) {
                o[0] = rs.getString(1);
                o[1] = rs.getString(2);
                o[2] = rs.getString(3);
                df.addRow(o);
                if(startYear != endYear && type.equals("Phishing")){
                if (rs2.next()) {
                    o[0] = rs.getString(1);
                    o[1] = rs.getString(2);
                    o[2] = rs.getString(3);
                    df.addRow(o);
                }
                }
            }
        } catch (Exception ex) {
            //Logger.getLogger(WebSiteDA.class.getName()).log(Level.SEVERE, null, ex);

        }
        this.closeConnection();
    }

    public void mostActivePeriodQuery(DefaultTableModel df, String type) {
        this.openConnection();
        String sql = "";
        java.util.Vector<String> v = new java.util.Vector<String>();
        ResultSet rs = null;
        try {
            if (type.equals("Phishing")) {
                sql = "SELECT type,year,COUNT(year) FROM time_date JOIN website ON time_dateID = time_date_foreign WHERE type = 'Phishing' GROUP BY year;";
            } else {
                sql = "SELECT type,year,COUNT(year) FROM time_date JOIN website ON time_dateID = time_date_foreign WHERE type = 'Hacking' GROUP BY year;";
            }
            v.add("Type");
            v.add("Year");
            v.add("Detection times");
            df.setColumnIdentifiers(v);
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            rs.beforeFirst();
            ResultSetMetaData rms = rs.getMetaData();
            Object[] o = new Object[rms.getColumnCount()];
            while (rs.next()) {
                o[0] = (Object) rs.getString(1);
                o[1] = (Object) rs.getInt(2);
                o[2] = (Object) rs.getInt(3);
                df.addRow(o);
            }


        } catch (Exception ex) {
        }
        this.closeConnection();
    }

    public void phishingWebsitesForURL(DefaultTableModel df, String URL) {
        /*
         * Return number of Phishing websits for specific website
         */
        this.openConnection();
        ResultSet rs = null;
        java.util.Vector<String> v = new java.util.Vector<String>();
        v.add("URL");
        v.add("# of Phishing");
        df.setColumnIdentifiers(v);
        try {
            String sql = "SELECT URL, COUNT(isPhished) FROM history WHERE URL like ? AND isPhished= 'InValid' OR URL like ? AND isPhished = 'Suspicious' GROUP BY URL;";
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            URL = "%" + URL + "%";
            pr.setString(1, URL);
            pr.setString(2, URL);
            rs = pr.executeQuery();
            rs.beforeFirst();
            Object o[] = new Object[2];
            while (rs.next()) {
                o[0] = rs.getString(1);
                o[1] = rs.getInt(2);
                df.addRow(o);
            }
        } catch (Exception ex) {
        }
        this.closeConnection();
    }

    public void enumerationNumber(DefaultTableModel df) {
        this.openConnection();
        java.util.Vector<String> v = new java.util.Vector<String>();
        ResultSet rs = null;
        try {
            String sql = "SELECT w.URL,COUNT(h.websiteID_foreign) FROM website AS w JOIN history AS h ON w.websiteID = h.websiteID_foreign GROUP BY w.URL; ";
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            v.add("URL");
            v.add("Number of enumerations");
            df.setColumnIdentifiers(v);
            rs = pr.executeQuery();
            rs.beforeFirst();
            ResultSetMetaData rms = rs.getMetaData();
            Object[] o = new Object[rms.getColumnCount()];
            int number = 0;
            while (rs.next()) {
                if (!rs.getString(1).isEmpty()) {
                    number = rs.getInt(2) - 1;
                    o[0] = (Object) rs.getString(1);
                    o[1] = (Object) number;
                    df.addRow(o);
                }
            }
        } catch (Exception ex) {
        }
        this.closeConnection();
    }

    public java.util.Vector<String> getAutoRunWebsites(String type) {
        this.openConnection();
        java.util.Vector<String> v = new java.util.Vector<String>();
        ResultSet rs = null;
        try {
            String sql = "";
            if (type.equals("Phishing")) {
                sql = "SELECT URL FROM website WHERE autorun = 'Yes' AND type = 'Phishing';";
            } else {
                sql = "SELECT URL FROM website WHERE autorun = 'Yes' AND type = 'Hacking';";
            }
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                if (!rs.getString(1).isEmpty()) {
                    v.add(rs.getString(1));
                }
            }

        } catch (Exception ex) {
        }

        this.closeConnection();
        return v;
    }

    public String getCategoryForWebsite(String URL) {
        this.openConnection();
        ResultSet rs = null;
        try {
            String sql = "SELECT list_name FROM website WHERE URL = ?;";
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, URL);
            rs = pr.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception ex) {
        }

        this.closeConnection();
        return "None";
    }

    public void openConnection() {
        try {
            con = DriverManager.getConnection(DTATBASE_URL, user_name, password);
        } catch (SQLException ex) {
            Logger.getLogger(WebSiteDA.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(WebSiteDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
