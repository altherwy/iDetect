/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idetect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author youssef 19
 */
public class PhisTankDBDA {

    static final String db_driver = "com.mysql.jdbc.Driver";
    //static final String db_url = "jdbc:mysql://localhost:3306/test";
    public static String db_url;
    //static final String user_name = "root";
    static String user_name ;
    //static final String password = "barcelona19";
    static  String password;
    public Connection con;

    public PhisTankDBDA() {
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_url, user_name, password);
        } catch (Exception e) {
        }
    }

    public void insertDownloadTime(int hour, int day, int month, int year) {
        this.openConnection();
        String sql = "INSERT INTO phishtankdb(xml_download_time_hour,xml_download_time_day,xml_download_time_month,xml_download_time_year)VALUES(?,?,?,?);";
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, hour);
            pr.setInt(2, day);
            pr.setInt(3, month);
            pr.setInt(4, year);
            int i = pr.executeUpdate();
            if (i == 1) {
            }
        } catch (Exception e) {
        }
        this.closeConnection();

    }

    public ArrayList<Integer> getLastHourDayMonthYear() {
        this.openConnection();
        ResultSet rs = null;
        String sql = "SELECT xml_download_time_year,xml_download_time_month,xml_download_time_day,xml_download_time_hour FROM phishtankdb;";
        int hour = -1;
        int day = -1;
        int month = -1;
        int year = -1;
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            boolean lastRow = rs.last();
            if (lastRow == true) {
                hour = rs.getInt("xml_download_time_hour");
                day = rs.getInt("xml_download_time_day");
                month = rs.getInt("xml_download_time_month");
                year = rs.getInt("xml_download_time_year");
            } else {
                
            }
        } catch (Exception e) {
        }
        ArrayList<Integer> ar = new ArrayList<Integer>();
        ar.add(hour);
        ar.add(day);
        ar.add(month);
        ar.add(year);
        this.closeConnection();
        return ar;
    }
     public void openConnection() {
        try {
            con = DriverManager.getConnection(db_url, user_name, password);
        } catch (SQLException ex) {
        }

    }

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
        }
    }

}
