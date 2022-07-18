/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idetect;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;

/**
 *
 * @author youssef 19
 */
public class iDetectThreads implements Runnable {

    public String original_website;
    public String list_name;
    public String website_type;
    public static int count = 0;
    public static int secondCount = 0;
    public Thread thread;
    public ArrayList<String> enumerationArray = new ArrayList<String>();
    public Connection connection;
    public SaxParser SP = new SaxParser();
    public WebSite website = new WebSite();
    public final WebSiteDA WDA = new WebSiteDA();
    public PhishTankDataBase pd = new PhishTankDataBase();

    public iDetectThreads(String threadName, ArrayList<String> threadArray,String original_website,String list_name,String website_type) {
        thread = new Thread(this, threadName);
        this.enumerationArray = threadArray;
        this.original_website = original_website;
        this.list_name = list_name;
        this.website_type = website_type;
    }

    public void run() {
     Time_Date TD = new Time_Date();
        String time = TD.systemDateTime();
        int year = TD.extracYeartFromSystemdate(time);
        int month = TD.extractMonthFromSystemdate(time);
        int day = TD.extractDayFromSystemdate(time);
        int hour = TD.extractHourFromSystemdate(time);
        //int minute = Integer.parseInt(pd.systemDateMinute());
        int minute  = TD.extractMinuteFromSystemdate(time);
        boolean inPhishTank = false;
        for (int i = 0; i < enumerationArray.size(); i++) {
            try {
                //SP.list();
                inPhishTank = SP.verifyPhishInPhishTank(enumerationArray.get(i));
                if (!inPhishTank) {
                    ///// My tech //////
                    String phishingState = website.runSourceCodeTechnique(enumerationArray.get(i),this.original_website,"",this.website_type);
                    if (phishingState.equals("Suspicious")) {
                        website.secondRunSourceCodeTechnique(enumerationArray.get(i),this.original_website,"",this.website_type);
                    }
                } else {
                    Time_Date tD = new Time_Date();
                    TD.insertDetectingTime(minute, hour, day, month, year, "Detecting");
                    synchronized(WDA){
                    WDA.insertWithoutSourceCode(enumerationArray.get(i), "InValid", "Online",this.website_type,this.original_website,"",tD.getTime_DateID(minute, hour, day, month, year, "Detecting"));
                    }
                    
                }
            } catch (Exception ex) {
                //Logger.getLogger(iDetectThreads.class.getName()).log(Level.SEVERE, null, ex);
                
            }


        }
        count ++;
        secondCount++;
            

    }
}
