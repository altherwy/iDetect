/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idetect;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author youssef 19
 */
public class PhishTankDataBase {

    PhisTankDBDA ptda = new PhisTankDBDA();

    public PhishTankDataBase() {
    }

    public void downloadPhishTankDB() {
        /*
         * Download PhishTank DB from PhishTank.com
         */
        String downloadTime = "";
        int day = -1;
        int month = -1;
        int year = -1;
        int hour = -1;
        boolean b = false;
        BufferedOutputStream bout = null;

        // TODO code application logic here
        Time_Date TD = new Time_Date();
        downloadTime = TD.systemDateTime();
        ///// call the 4 methods here //////
        hour = TD.extractHourFromSystemdate(downloadTime);
        day = TD.extractDayFromSystemdate(downloadTime);
        month = TD.extractMonthFromSystemdate(downloadTime);
        year = TD.extracYeartFromSystemdate(downloadTime);
        ///// call check download method here /////
        b = this.checkDownloadTime(hour, day, month, year);
        if (b == true) {
            BufferedInputStream bfi = null;
            try {
                bfi = new BufferedInputStream(new URL("http://data.phishtank.com/data/ed2af9ca8e32c51c7303cbfd5268ba4372f0dca6c23467f9bf5c03c6bca06765/online-valid.xml").openStream());
                String name = "PhishTankDB" + downloadTime + ".xml";
                FileOutputStream fb = new FileOutputStream(name);
                bout = new BufferedOutputStream(fb, 512);
                byte[] data = new byte[512];
                int x = 0;
                while ((x = bfi.read(data, 0, 512)) >= 0) {
                    bout.write(data, 0, x);
                }
                this.deletePhishTankDatabase(this.recreateFileName());
                ptda.insertDownloadTime(hour, day, month, year);
                bout.close();
                bfi.close();
            } catch (IOException ex) {
                this.deletePhishTankDatabase(this.recreateFileName());
                ptda.insertDownloadTime(hour, day, month, year);
            } finally {
                try {
                    bout.close();
                    bfi.close();
                } catch (IOException ex) {
                  //  Logger.getLogger(PhishTankDataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            
            return;
        }



        
    }

    public boolean checkDownloadTime(int hour, int day, int month, int year) {
        /*
         * Check if it's the right time to download PhishTank DB
         */
        ArrayList<Integer> ar = ptda.getLastHourDayMonthYear();
        int dbHour = -1;
        int dbDay = -1;
        int dbMonth = -1;
        int dbYear = -1;
        if (ar.size() == 4) {
            dbHour = ar.get(0);
            dbDay = ar.get(1);
            dbMonth = ar.get(2);
            dbYear = ar.get(3);
        } else {
            return true;
        }
        if (dbYear == year) {
            if (month == dbMonth) {
                if (day == dbDay) {
                    if (hour > dbHour) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;



    }

    public boolean deletePhishTankDatabase(String fileName) {
        /*
         * Delete PhishTank DB from the system
         */
        boolean b = false;
        if (!fileName.equals("")) {
            File f = new File(fileName);
            b = f.delete();
        }
        return b;
    }

    public String recreateFileName() {
        ArrayList<Integer> ar = ptda.getLastHourDayMonthYear();
        String save = "";
        if (ar.size() == 4) {
            int hour = ar.get(0);
            int day = ar.get(1);
            int month = ar.get(2);
            int year = ar.get(3);
            save = "PhishTankDB" + String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day) + "_" + String.valueOf(hour) + ".xml";
        }
        return save;
    }
}
