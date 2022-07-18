/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idetect;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author youssef 19
 */
public class Time_Date implements Runnable {

    private Time time;
    private Date date;
    private WebSite url;
    static final String db_driver = "com.mysql.jdbc.Driver";
    //static final String db_url = "jdbc:mysql://localhost:3306/idetect";
    static  String db_url;
    //static final String user_name = "root";
    static String user_name;
    //static final String password = "barcelona19";
    static String password;
    public Connection con;
    public java.util.Vector<String> URLs = new java.util.Vector<String>();
    public String type;

    public Time_Date() {
        time = null;
        date = null;
        url = null;
        try {
            Class.forName(db_driver);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Time_Date(Vector<String> v, String type) {
        this.URLs = v;
        this.type = type;
    }

    public String systemDateTime() {
        /*
         * To retreive the system date as a String.
         */
        String systemDateTime = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-M-d_H_m");
        Date datee = new Date();
        systemDateTime = dateFormat.format(datee);
        return systemDateTime;
    }

    public int extractMinuteFromSystemdate(String time) {
        /*
         * To extract the minute from a given time as a (String) an return the result as (int).
         */
        String temp = time;
        int index = temp.indexOf("_");
        temp = temp.substring(index + 1);
        index = temp.indexOf("_");
        temp = temp.substring(index + 1);
        int minute = Integer.parseInt(temp);
        return minute;
    }

    public int extractHourFromSystemdate(String time) {
        /*
         * To extract the hour from a given time as a (String) an return the result as (int).
         */
        String temp = time;
        int index = temp.indexOf("_");
        temp = temp.substring(index + 1);
        int endIndex = temp.indexOf("_");
        temp = temp.substring(0, endIndex);
        int hour = Integer.parseInt(temp);
        return hour;
    }

    public int extractDayFromSystemdate(String time) {
        /*
         * To extract the day from a given time as a (String) an return the result as (int).
         */
        String temp = time;
        int index = temp.indexOf("-");
        int endIndex = temp.indexOf("_");
        temp = temp.substring(index + 1, endIndex);
        index = temp.indexOf("-");
        temp = temp.substring(index + 1);
        int day = Integer.parseInt(temp);
        return day;
    }

    public int extractMonthFromSystemdate(String time) {
        /*
         * To extract the month from a given time as a (String) an return the result as (int).
         */
        String temp = time;
        int StartIndex = temp.indexOf("-");
        temp = temp.substring(StartIndex + 1);
        StartIndex = temp.indexOf("-");
        temp = temp.substring(0, StartIndex);
        int month = Integer.parseInt(temp);
        return month;
    }

    public int extracYeartFromSystemdate(String time) {
        /*
         * To extract the year from a given time as a (String) an return the result as (int).
         */
        String temp = time;
        temp = temp.substring(0, 4);
        int year = Integer.parseInt(temp);
        return year;
    }

    public void insertDetectingTime(int minute, int hour, int day, int month, int year, String timing_Purpose) {
        /*
         * To insert a new instance into (time_date) table in the database with these data.
         */
        
        if (this.checkTimeAvailablity(minute, hour, day, month, year, timing_Purpose) != true) {
            String sql = "INSERT INTO time_date(minute,hour,day,month,year,purpose)VALUES(?,?,?,?,?,?);";
            try {
                this.openConnection();
                java.sql.PreparedStatement pr = con.prepareStatement(sql);
                pr.setInt(1, minute);
                pr.setInt(2, hour);
                pr.setInt(3, day);
                pr.setInt(4, month);
                pr.setInt(5, year);
                pr.setString(6, timing_Purpose);
                pr.executeUpdate();
            } catch (Exception ee) {

            }
        }
        this.closeConnection();
    }

    public boolean checkTimeAvailablity(int minute, int hour, int day, int month, int year, String timing_Purpose) {
        /*
         * to check the existing of time_date record with these given data.
         */
        this.openConnection();
        String sql = "SELECT minute FROM time_date WHERE minute = ? AND hour = ? AND day = ? AND month = ? AND year = ? AND purpose = ? ;";
        ResultSet rs = null;
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, minute);
            pr.setInt(2, hour);
            pr.setInt(3, day);
            pr.setInt(4, month);
            pr.setInt(5, year);
            pr.setString(6, timing_Purpose);
            rs = pr.executeQuery();
            rs.beforeFirst();
            if (rs.next() == true) {
                return true;
            }

        } catch (Exception ex) {
            Logger.getLogger(Time_Date.class.getName()).log(Level.SEVERE, null, ex);

        }
        this.closeConnection();
        return false;
    }
    public java.util.Vector<String> getCheckingTimes(){
        /*
         * Get all times specified for auto run
         */
        this.openConnection();
        java.util.Vector<String> times = new java.util.Vector<String>();
        ResultSet rs = null;
        try{
            String sql = "SELECT day,hour FROM time_date WHERE purpose = 'Checking';";
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            rs.beforeFirst();
            while(rs.next()){
                if(rs.getInt("day") == 1)
                times.add(String.valueOf("Sunday :"+String.valueOf(rs.getInt("hour"))));
                if(rs.getInt("day") == 2)
                    times.add(String.valueOf("Monday :"+String.valueOf(rs.getInt("hour"))));
                if(rs.getInt("day") == 3)
                    times.add(String.valueOf("Tuesday :"+String.valueOf(rs.getInt("hour"))));
                if(rs.getInt("day") == 4)
                    times.add(String.valueOf("Wednesday :"+String.valueOf(rs.getInt("hour"))));
                if(rs.getInt("day") == 5)
                    times.add(String.valueOf("Thursday :"+String.valueOf(rs.getInt("hour"))));
                if(rs.getInt("day") == 6)
                    times.add(String.valueOf("Friday :"+String.valueOf(rs.getInt("hour"))));
                if(rs.getInt("day") == 7)
                    times.add(String.valueOf("Saturday :"+String.valueOf(rs.getInt("hour"))));
            }
        }
        catch(Exception ex){

        }
        this.closeConnection();
        return times;
    }
    public void deleteCheckingTimes(int day,int hour){
        /*
         * Delete specific time from checking list of times
         */
        this.openConnection();
        String sql = "DELETE FROM time_date WHERE day = ? AND hour = ? AND purpose = 'Checking';";
        try{
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, day);
            pr.setInt(2, hour);
            pr.executeUpdate();
        }
        catch(Exception ex){

        }
        this.closeConnection();
    }

    public java.util.Vector<Integer> getDay() {
        /*
         * return all days specified for auto run 
         */
        this.openConnection();
        String sql = "SELECT day FROM time_date WHERE purpose = 'Checking' ORDER BY day ASC; ";
        java.util.Vector<Integer> days = new java.util.Vector<Integer>();
        ResultSet rs = null;
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                days.add(rs.getInt("day"));
            }
        } catch (Exception ex) {
        }
        this.closeConnection();
        return days;
    }

    public java.util.Vector<Integer> getHour(int day) {
        /*
         * return all hours specified for auto run and belong to given day
         */
        this.openConnection();
        String sql = "SELECT hour FROM time_date WHERE purpose = 'Checking' AND day = ? ORDER BY hour ASC; ";
        java.util.Vector<Integer> hours = new java.util.Vector<Integer>();
        ResultSet rs = null;
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, day);
            rs = pr.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                hours.add(rs.getInt("hour"));
            }
        } catch (Exception ex) {
        }
        this.closeConnection();
        return hours;
    }

    public int getTime_DateID(int minute, int hour, int day, int month, int year, String timing_purpose) {
        /*
         * To retreive the time_dateID for a given data from time_date table.
         */
        this.openConnection();
        String sql = "SELECT DISTINCT time_dateID FROM time_date WHERE minute = ? AND hour = ? AND day = ? AND month = ? AND year = ? AND purpose = ?;";
        ResultSet rs = null;
        int ID = -1;
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, minute);
            pr.setInt(2, hour);
            pr.setInt(3, day);
            pr.setInt(4, month);
            pr.setInt(5, year);
            pr.setString(6, timing_purpose);
            rs = pr.executeQuery();
            rs.beforeFirst();
            if (rs.next() == true) {
                ID = rs.getInt("time_dateID");
                return ID;
            }
        } catch (Exception ee) {
        }
        this.closeConnection();
        return ID;
    }

    public void insertCheckingTime(int day, int hour) {
        /*
         * Add time for auto run purpose
         */
        this.openConnection();
        String sql = "INSERT INTO time_date(hour,day,purpose) VALUES (?,?,'Checking');";
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, hour);
            pr.setInt(2, day);
            pr.executeUpdate();
        } catch (Exception ex) {
        }
        this.closeConnection();

    }

    public boolean checkingTimeAvailablity(int day, int hour) {
        /*
         * Check if given day,hour found in DB or not
         */
        this.openConnection();
        String sql = "UPDATE time_date SET day = ?, hour = ? WHERE day = ? AND hour = ?;";
        try {
            java.sql.PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, day);
            pr.setInt(2, hour);
            pr.setInt(3, day);
            pr.setInt(4, hour);
            int i = pr.executeUpdate();
            if (i > 0) {
                return true;
            }

        } catch (Exception ex) {
        }

        this.closeConnection();
        return false;
    }

    public void run() {

        java.util.Vector<Integer> days;
        java.util.Vector<Integer> hours = new java.util.Vector<Integer>();
        int hour = -1;
        int count = 0;
        boolean b = true;
        Calendar c = Calendar.getInstance();
        int currentDay = 0;
        days = this.getDay();
        while (true) {
            currentDay = c.get(Calendar.DAY_OF_WEEK);
            for (int i = 0; i < days.size(); i++) {
                count = 0;
                while (currentDay == days.get(i)) {
                    if(count < 1){
                    hours = this.getHour(currentDay);
                    count ++;
                    }
                    
                    for (int j = 0; j < hours.size(); j++) {
                        hour = this.extractHourFromSystemdate(this.systemDateTime());
                        if (hour == hours.get(j)) {
                            //// Here start detecting ////
                            if (type.equals("Phishing")) {
                                new WebSite().runDetectWithoutEnumeration(URLs, "None");
                            } else {
                                for (int jj = 0; jj < URLs.size(); jj++) {
                                    WebSite wb = new WebSite(URLs.get(jj), "None","yes");
                                    boolean t1, t2, t3 = false, t4, t5, t6, t7 = false;
                                    wb.reloadSourceCode();
                                    if (wb.isActive()) {
                                        wb.writeAllScripts();
                                        wb.writeAllIframes();
                                        wb.writeAllMetas();
                                        t1 = wb.checkBodyExisting();
                                        t2 = wb.checkByGoogleService();
                                        t3 = wb.checkByHashFunction();
                                        t4 = wb.checkHackedKeyword();
                                        t5 = wb.checkHackerNicknames();
                                        t6 = wb.checkHeadExisting();
                                        t7 = wb.checkWebsiteByZoneH();
                                        if (t4 || t5 || t1 || t6 || t7) {
                                            wb.changeIs_HackedValue("hacked", wb.getDetectionType());
                                        } else {
                                            if (t3) {
                                                wb.changeIs_HackedValue("hacked", wb.getDetectionType());
                                            }
                                            if (t2) {
                                                wb.changeIs_HackedValue("suspicious", wb.getDetectionType());
                                            }
                                            wb.changeIs_HackedValue("safe", "");
                                        }
                                    }
                                }
                            }
                            //// here wait ///
                            while(hour == hours.get(j)){
                                hour = this.extractHourFromSystemdate(this.systemDateTime());
                                ///// stay in this while until hour change ////
                            }
                        }
                    }
                }
            }
        }

    }

    public void openConnection() {
        try {
            con = DriverManager.getConnection(db_url, user_name, password);
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
