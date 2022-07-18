/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idetect;

import java.nio.FloatBuffer;
import java.sql.*;
import com.mysql.jdbc.PreparedStatement;
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
public class HistoryDA {

    static final String db_driver = "com.mysql.jdbc.Driver";
//    static final String db_url = "jdbc:mysql://localhost:3306/idetect";
    public static String db_url ;
  //  static final String user_name = "root";
    public static String user_name ;
   // static final String password = "barcelona19";
    public static String password;
    public Connection con;

    public HistoryDA() {
        try {
            Class.forName(db_driver);
            //con = DriverManager.getConnection(db_url, user_name, password);
        } catch (Exception e) {
            
        }
    }
   /* public HistoryDA(String userName,String password,String port,String host){
        this.db_url = "jdbc:mysql://"+host+":"+port+"/idetect";
        this.user_name = userName;
        this.password = password;
    }*/

    public void transferFromWebsiteToHistory(int urlID, String URL, String active, String isPhished, int foreign) {
        this.openConnection();
        String Sql = "INSERT INTO history(websiteID_foreign,URL,active,isPhished,time_date_foreign) VALUES (?,?,?,?,?);";
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(Sql);
            pr.setInt(1, urlID);
            pr.setString(2, URL);
            pr.setString(3, active);
            pr.setString(4, isPhished);
            pr.setInt(5, foreign);
            pr.executeUpdate();
        } catch (Exception ex) {
            
        }
        this.closeConnection();
    }

    public void runTransferFromWebsiteToHistory(ArrayList<ArrayList> save) {

        ArrayList<String> row = new ArrayList<String>();
        for (int i = 0; i < save.size(); i++) {
            row = save.get(i);
            if (row.size() != 0) {
                this.transferFromWebsiteToHistory(Integer.parseInt(row.get(4)), row.get(0), row.get(1), row.get(2), Integer.parseInt(row.get(3)));
            }
        }
    }

    public int countWebsites() {
        this.openConnection();
        String sql = "SELECT COUNT(historyID) AS number FROM history ;";
        ResultSet rs = null;
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                return rs.getInt("number");
            }
        } catch (Exception ex) {
           

        }
        this.closeConnection();
        return -1;
    }

    public void getResults(DefaultTableModel df, int number) {
       this.openConnection();
        String sql = "SELECT h.URL,h.active , h.isPhished, year,month,day,hour,minute , w.URL FROM history AS h ,website AS w,time_date WHERE h.websiteID_foreign = w.websiteID AND h.time_date_foreign = time_dateID ORDER BY h.historyID DESC LIMIT 0,?;";
        ResultSet rs = null;
        java.util.Vector<String> v = new java.util.Vector<String>();
        v.add("URL");
        v.add("Online?");
        v.add("Valid?");
        v.add("Checking Time");
        v.add("Original Website");
        df.setColumnIdentifiers(v);
        ResultSetMetaData rms = null;
        int count = 0;
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, number);
            rs = pr.executeQuery();
            rms = rs.getMetaData();
            count = rms.getColumnCount();
            Object[] o = new Object[count];
            rs.beforeFirst();
            while (rs.next()) {
                o[0] = rs.getObject("URL");
                o[1] = rs.getObject("active");
                o[2] = rs.getObject("isPhished");
                String time = String.valueOf(rs.getInt("hour")) + ":" + String.valueOf(rs.getInt("minute")) + "  " + String.valueOf(rs.getInt("day")) + "/" + String.valueOf(rs.getInt("month")) + "/" + String.valueOf(rs.getInt("year"));
                o[3] = (Object) time;
                o[4] = rs.getObject(9);
                df.addRow(o);
            }
        } catch (Exception ex) {
           

        }
        this.closeConnection();
    }

    public ArrayList<String> getWebsites(JTable JT, String url) {
        this.openConnection();
        String sql = "SELECT h.URL,h.active , h.isPhished, year,month,day,hour,minute , w.URL FROM history AS h ,website AS w,time_date WHERE websiteID_foreign = w.websiteID AND h.time_date_foreign = time_dateID AND h.URL like ? ;";
        ArrayList<String> save = new ArrayList<String>();
        ResultSet rs = null;
        DefaultTableModel df = (DefaultTableModel) JT.getModel();
        java.util.Vector<String> v = new java.util.Vector<String>();
        v.add("URL");
        v.add("Online?");
        v.add("Valid?");
        v.add("Checking Time");
        v.add("Original Website");
        df.setColumnIdentifiers(v);
        ResultSetMetaData rms = null;
        int count = 0;
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            url = "%" + url + "%";
            pr.setString(1, url);
            rs = pr.executeQuery();
            rms = rs.getMetaData();
            count = rms.getColumnCount();
            Object[] o = new Object[count];
            rs.beforeFirst();
            while (rs.next()) {
                o[0] = rs.getObject("URL");
                o[1] = rs.getObject("active");
                o[2] = rs.getObject("isPhished");
                save.add("isPhished");
                String time = String.valueOf(rs.getInt("hour")) + ":" + String.valueOf(rs.getInt("minute")) + "  " + String.valueOf(rs.getInt("day")) + "/" + String.valueOf(rs.getInt("month")) + "/" + String.valueOf(rs.getInt("year"));
                o[3] = (Object) time;
                o[4] = rs.getObject(9);
                df.addRow(o);
            }
            JT.setModel(df);
        } catch (Exception ex) {
            


        }
        this.closeConnection();
        return save;
    }
    
    public void openConnection(){
        try {
            con = DriverManager.getConnection(db_url, user_name, password);
        } catch (SQLException ex) {
            Logger.getLogger(WebSiteDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void closeConnection(){
        try {
            con.close();
        } catch (SQLException ex) {
            //Logger.getLogger(WebSiteDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
