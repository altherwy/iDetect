/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idetect;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author youssef 19
 */
public class WebSite {

    private String url;
    private String topLevel;
    private boolean isHacked;
    private boolean isPhishied;
    private boolean active;
    private boolean forHacking;
    private boolean forPhishing;
    private int titleHashValue;
    private String detectionType = "";
    private int websiteID;
    private String sourceCode = null;
    private ArrayList<WebSite> webSite = new ArrayList<WebSite>();
    public ArrayList<String> thread1 = new ArrayList<String>();
    public ArrayList<String> thread2 = new ArrayList<String>();
    public ArrayList<String> thread3 = new ArrayList<String>();
    public ArrayList<String> thread4 = new ArrayList<String>();
    public Connection c = new Connection();
    public final WebSiteDA wda = new WebSiteDA();

    public WebSite(String url, String topLevel, boolean isPhishied, boolean active, boolean forPhishing) {
        this.setUrl(url);
        this.setIsHacked(isHacked);
        this.setIsPhishied(isPhishied);
        this.setActive(active);
        this.setForHacking(forHacking);
        this.setForPhishing(forPhishing);
        this.setTopLevel(topLevel);
    }

    public WebSite(String url, boolean isHacked, boolean forHacking, int hach) {
        this.url = url;
        this.isHacked = isHacked;
        this.forHacking = forHacking;
        this.titleHashValue = hach;
        try {
            this.sourceCode = getContentHTMLString(url);
        } catch (IOException ex) {
            Logger.getLogger(WebSite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public WebSite() {
        this.setUrl("");
        this.setIsHacked(false);
        this.setIsPhishied(false);
        this.setForHacking(false);
        this.setForPhishing(true);
        this.setActive(false);
    }

    public WebSite(String url, String category_name, String autorun) {
        try {
            if (getWebSiteIDFromDB(url) == -1) {
                this.url = url;
                this.isHacked = false;

                if (checkActivity().equals("offline")) {
                    this.sourceCode = "offline";
                    this.active = false;
                    this.titleHashValue = 0;
                    insertWebSite("offline", category_name, autorun);
                    this.websiteID = getWebSiteIDFromDB(this.url);
                } else {
                    try {
                        this.sourceCode = getContentHTMLString(this.url);
                    } catch (Exception ex) {
                        //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
                    }
                    this.active = true;
                    this.titleHashValue = computeTitleHashValue();
                    insertWebSite("online", category_name, autorun);
                    this.websiteID = getWebSiteIDFromDB(this.url);
                }
            } else {
                this.websiteID = getWebSiteIDFromDB(url);
                this.url = url;
                this.isHacked = false;
                if (checkActivity().equals("offline")) {
                    this.sourceCode = "offline";
                    this.active = false;
                    this.titleHashValue = 0;
                    updateWebSite("offline");

                } else {
                    try {
                        this.sourceCode = getContentHTMLString(this.url);
                    } catch (Exception ex) {
                        //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
                    }
                    this.active = true;
                    this.titleHashValue = computeTitleHashValue();
                    updateWebSite("online");
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
        }
        //JOptionPane.showMessageDialog(null, "Done save website(s)","Inform message",1);



    }

    public ArrayList<String> generateEnumeration() {
        /*
         * Used to call Phishing Possiblity methods to generate enumerations
         */
        Phishing_Possiblity phishingEnumeration = new Phishing_Possiblity("", false, false, this.getUrl());
        ArrayList<String> ar = new ArrayList<String>();
        ArrayList<String> enumeration = phishingEnumeration.dublicateTechnique(this.getUrl(), this.getTopLevel());
        enumeration = phishingEnumeration.commonReplacement(this.getUrl(), this.getTopLevel());
        enumeration = phishingEnumeration.QWERTYMisType(this.getUrl(), getTopLevel());
        enumeration = phishingEnumeration.dropCharTechnique(this.getUrl(), getTopLevel());
        enumeration = phishingEnumeration.urlPlural(this.getUrl(), getTopLevel());
        enumeration = phishingEnumeration.WWWWithUrl(this.getUrl(), this.getTopLevel());
        enumeration = phishingEnumeration.topLevelDomainManipulation(this.getTopLevel(), this.getUrl());
        enumeration = phishingEnumeration.deleteRepeatedEnumeration(enumeration);
        ar = phishingEnumeration.addSlash(enumeration);
        return ar;
    }

    public String checkingTitleTag(String html) {
        /*
         * Extract the <title> tag from the web page source code
         */

        String save = html;
        String start = "";
        String end = "";
        int startIndex = save.indexOf("<title");
        int endIndex = save.indexOf("</title>");
        save = save.substring(startIndex, endIndex);
        int index = save.indexOf(">");
        save = save.substring(index + 1);
        if (save.contains("&") && save.contains(";")) {
            startIndex = save.indexOf("&");
            endIndex = save.indexOf(";");
            if (endIndex - startIndex <= 5) {
                start = save.substring(0, startIndex);
                end = save.substring(endIndex + 1);
                save = start + end;
            }
        }
        save = save.trim();
        // JOptionPane.showMessageDialog(null, save);
        return save;
    }

    public String formatGoogleQuery(String title) {
        /*
         * Prepare the title for get request of Google
         */
        String save = title.replace(" ", "+");
        return save;
    }

    public boolean sourceCodeTechnique(String html, String phishingURL) { /// html Google Search Result page ///
        /*
         * Extract First website from Google result page and Compare it with given URL
         */
        Connection c = new Connection();
        String save = html;
        int searchResultIndex = save.indexOf("Search Result");
        save = save.substring(searchResultIndex);
        int hrefIndex = save.indexOf("<a href=");
        save = save.substring(hrefIndex);
        int endIndex = save.indexOf("class");
        save = save.substring(9, endIndex - 2); //// 1st website URL  with Slash "\"///
        //// (END) ////
        //char cc = save.charAt(endIndex-2);
        if (save.endsWith("dir=rt")) {
            int endIndex2 = save.indexOf("dir=rt");
            save = save.substring(0, endIndex2 - 2);
        }
        c.getAnySourceCode(save);
        save = String.valueOf(c.responceAnyURL);
        if (phishingURL.equals(save)) {
            return false;
        }
        if (!save.startsWith("http://www.") && !save.startsWith("https://www.")) {
            if (save.startsWith("http://")) {
                save = save.substring(7);
                save = "http://www." + save;
            } else {
                if (save.startsWith("https://")) {
                    save = save.substring(8);
                    save = "https://www." + save;
                }
            }
        }
        if (!phishingURL.startsWith("http://www.") && !phishingURL.startsWith("https://www.")) {
            if (phishingURL.startsWith("http://")) {
                phishingURL = phishingURL.substring(7);
                phishingURL = "http://www." + phishingURL;
            } else {
                if (phishingURL.startsWith("https://")) {
                    phishingURL = phishingURL.substring(8);
                    phishingURL = "https://www." + phishingURL;
                }
            }
        }

        //System.out.println("   phi  " + phishingURL);
        if (phishingURL.equals(save)) {
            return false;
        }
        String firstURL = this.bringMainDomain(phishingURL); /// Home Page for Phishing website /////
        String secondURL = this.bringMainDomain(save); /// Home Page for 1st website in Google result page /////
        if (firstURL.equals(secondURL)) {
            return false;
        }


        return true; //// that's doesn't mean completly Phishing wesite /////
    }

    public boolean secondSourceCodeTechnique(String html, String phishingURL) {
        /*
         * Extract First website from Google result page and Compare it with given URL when URL doesn't containe www
         */
        Connection c = new Connection();
        String save = html;
        ///// (START)   Extract First website from Google result page ////
        int searchResultIndex = save.indexOf("Search Result");
        save = save.substring(searchResultIndex);
        int hrefIndex = save.indexOf("<a href=");
        save = save.substring(hrefIndex);
        int endIndex = save.indexOf("class");
        save = save.substring(9, endIndex - 2); //// 1st website URL  with Slash "\"///
        //// (END) ////
        Character cc = save.charAt(10);
        if (!cc.equals('.')) {
            phishingURL = phishingURL.substring(11);
            phishingURL = "http://" + phishingURL;
        }
        if (save.endsWith("dir=rt")) {
            int endIndex2 = save.indexOf("dir=rt");
            save = save.substring(0, endIndex2 - 2);
        }
        c.getAnySourceCode(save);
        save = String.valueOf(c.responceAnyURL);
        if (phishingURL.equals(save)) {
            return false;
        }
        if (!save.startsWith("http://www.") && !save.startsWith("https://www.")) {
            if (save.startsWith("http://")) {
                save = save.substring(7);
                save = "http://www." + save;
            } else {
                if (save.startsWith("https://")) {
                    save = save.substring(8);
                    save = "https://www." + save;
                }
            }
        }
        if (!phishingURL.startsWith("http://www.") && !phishingURL.startsWith("https://www.")) {
            if (phishingURL.startsWith("http://")) {
                phishingURL = phishingURL.substring(7);
                phishingURL = "http://www." + phishingURL;
            } else {
                if (phishingURL.startsWith("https://")) {
                    phishingURL = phishingURL.substring(8);
                    phishingURL = "https://www." + phishingURL;
                }
            }
        }
        if (phishingURL.equals(save)) {
            return false;
        }
        String firstURL = this.bringMainDomain(phishingURL); /// Home Page for Phishing website /////
        String secondURL = this.bringMainDomain(save); /// Home Page for 1st website in Google result page /////
        if (firstURL.equals(secondURL)) {
            return false;
        }
        return true;
    }

    public String runSourceCodeTechnique(String enumeration, String original, String list_name, String website_type) throws Exception {
        /*
         * Detection method of Phishing with sourceCodeTechnique method
         */
///Original the original website for enumeration ///
        //website_type either "Enumeration" or "Original"//
        Time_Date TD = new Time_Date();
        String time = TD.systemDateTime();
        int year = TD.extracYeartFromSystemdate(time);
        int month = TD.extractMonthFromSystemdate(time);
        int day = TD.extractDayFromSystemdate(time);
        int hour = TD.extractHourFromSystemdate(time);
        int minute = TD.extractMinuteFromSystemdate(time);
        String html = c.getAnySourceCode(enumeration);
        TD.insertDetectingTime(minute, hour, day, month, year, "Detecting");
        int ID = TD.getTime_DateID(minute, hour, day, month, year, "Detecting");
        String googleSerchResultPage = "";
        boolean avalible = false;
        if (html.equals("Offline")) {
            avalible = wda.checkAvailablity(enumeration, website_type);
            if (avalible == false) {
                synchronized (wda) {
                    wda.insertOfflineWebsite(enumeration, website_type, original, ID);
                    return "Null";
                }

            } else {
                synchronized (wda) {
                    wda.updateOfflineWebsite(enumeration, website_type, original, ID);
                    return "Null";
                }

            }
            //return false;
        }
        if (!html.contains("<title") && !html.contains("<TITLE")) {
            avalible = wda.checkAvailablity(enumeration, website_type);
            if (avalible == true) {
                synchronized (wda) {
                    wda.updateWithoutSourceCode(enumeration, "Unknown", "Online", website_type, original, ID);
                }
                return "Unknown";
            } else {
                synchronized (wda) {
                    wda.insertWithoutSourceCode(enumeration, "Unknown", "Online", website_type, original, list_name, ID);
                    return "Unknown";
                }
            }
            //return false;
        }

        enumeration = c.responceAnyURL.toString();
        String title = this.checkingTitleTag(html);
        if (title.contains("Blocked URL")) {
            synchronized (wda) {
                wda.insertWithoutSourceCode(enumeration, "Blocked", "Online", website_type, original, list_name, ID);
                //return false;
                return "Blocked";
            }
        }
        
        title = this.formatGoogleQuery(title);
        String Google = c.GOOGLE_QUERY + title;
        c.getSourceCode(Google);
        Object content = c.content;
        String MIME = c.MIMEtype;
        if (MIME.equals("text/html") && content instanceof String) {
            googleSerchResultPage = (String) content;
        }
        if (googleSerchResultPage.contains("Search Result")) {
            
            boolean active = this.sourceCodeTechnique(googleSerchResultPage, enumeration);
            if (active == true) {
                active = this.secondSourceCodeTechnique(googleSerchResultPage, enumeration);
            }
            if (active == false) {
                avalible = wda.checkAvailablity(enumeration, website_type);
                if (avalible == false) {
                    synchronized (wda) {
                        wda.insertWithoutSourceCode(enumeration, "Valid", "Online", website_type, original, list_name, ID);

                        return "Valid";
                    }
                } else {
                    synchronized (wda) {
                        wda.updateWithoutSourceCode(enumeration, "Valid", "Online", website_type, original, ID);
                        return "Valid";
                    }
                }
            } else {
                avalible = wda.checkAvailablity(enumeration, website_type);
                if (avalible == false) {
                    synchronized (wda) {
                        wda.insertWithoutSourceCode(enumeration, "Suspicious", "Online", website_type, original, list_name, ID);
                        return "Suspicious";
                    }
                } else {
                    synchronized (wda) {
                        wda.updateWithoutSourceCode(enumeration, "Suspicious", "Online", website_type, original, ID);
                        return "Suspicious";
                    }
                }
            }
        } else {
            avalible = wda.checkAvailablity(enumeration, website_type);
            if (avalible == false) {
                synchronized (wda) {
                    wda.insertWithoutSourceCode(enumeration, "Suspicious", "Online", website_type, original, list_name, ID);
                    return "Suspicious";
                }
            } else {
                synchronized (wda) {
                    wda.updateWithoutSourceCode(enumeration, "Suspicious", "Online", website_type, original, ID);
                    return "Suspicious";
                }
            }
            //return true;
        }
        //return false;


    } //// dont care about what this method return , the real data will be in the database /////

    public String secondRunSourceCodeTechnique(String enumeration, String original, String list_name, String website_type) throws Exception {
        /*
         * Detection method of Phishing with SecondSourceCodeTechnique method
         */

        Time_Date TD = new Time_Date();
        int ID = -1;
        String time = TD.systemDateTime();
        int year = TD.extracYeartFromSystemdate(time);
        int month = TD.extractMonthFromSystemdate(time);
        int day = TD.extractDayFromSystemdate(time);
        int hour = TD.extractHourFromSystemdate(time);
        int minute = TD.extractMinuteFromSystemdate(time);
        String html = c.getAnySourceCode(enumeration);
        String googleSerchResultPage = "";
        TD.insertDetectingTime(minute, hour, day, month, year, "Detecting");
        ID = TD.getTime_DateID(minute, hour, day, month, year, "Detecting");
        boolean avalible = false;
        if (html.equals("Offline")) {
            avalible = wda.checkAvailablity(enumeration, website_type);
            if (avalible == false) {
                synchronized (wda) {
                    wda.insertOfflineWebsite(enumeration, website_type, original, ID);
                }
            } else {
                synchronized (wda) {
                    wda.updateOfflineWebsite(enumeration, website_type, original, ID);
                }
            }
            return "Null";
        }
        if (!html.contains("<title")) {
            avalible = wda.checkAvailablity(enumeration, website_type);
            if (avalible == true) {
                synchronized (wda) {
                    wda.updateWithoutSourceCode(enumeration, "Unknown", "Online", website_type, original, ID);
                }
            } else {
                synchronized (wda) {
                    wda.insertWithoutSourceCode(enumeration, "Unknown", "Online", website_type, original, list_name, ID);
                }
                return "Unknown";
            }
        }
        enumeration = c.responceAnyURL.toString();
        String Google = c.GOOGLE_QUERY + enumeration;
        c.getSourceCode(Google);
        Object content = c.content;
        String MIME = c.MIMEtype;
        if (MIME.equals("text/html") && content instanceof String) {
            googleSerchResultPage = (String) content;
        }
        if (googleSerchResultPage.contains("Search Result")) {
            boolean active = this.sourceCodeTechnique(googleSerchResultPage, enumeration);
            if (active == true) {
                active = this.secondSourceCodeTechnique(googleSerchResultPage, enumeration);
            }
            if (active == false) {
                avalible = wda.checkAvailablity(enumeration, website_type);
                if (avalible == false) {
                    synchronized (wda) {
                        wda.insertWithoutSourceCode(enumeration, "Valid", "Online", website_type, original, list_name, ID);
                        return "Valid";
                    }
                } else {
                    synchronized (wda) {
                        wda.updateWithoutSourceCode(enumeration, "Valid", "Online", website_type, original, ID);
                        return "Valid";
                    }
                }
            } else {
                avalible = wda.checkAvailablity(enumeration, website_type);
                if (avalible == false) {
                    synchronized (wda) {
                        wda.insertWithoutSourceCode(enumeration, "Suspicious", "Online", website_type, original, list_name, ID);
                        return "Suspicious";
                    }
                } else {
                    synchronized (wda) {
                        wda.updateWithoutSourceCode(enumeration, "Suspicious", "Online", website_type, original, ID);
                        return "Suspicious";
                    }
                }
            }
        } else {
            avalible = wda.checkAvailablity(enumeration, website_type);
            if (avalible == false) {
                synchronized (wda) {
                    wda.insertWithoutSourceCode(enumeration, "Suspicious", "Online", website_type, original, list_name, ID);
                    return "Suspicious";
                }
            } else {
                synchronized (wda) {
                    wda.updateWithoutSourceCode(enumeration, "Suspicious", "Online", website_type, original, ID);
                    return "Suspicious";
                }
            }
            //return true;
        }
    }

    public String bringMainDomain(String URL) {
        /*
         * Extract main domain from the given URL
         */
        String save = URL;
        if (save.startsWith("http://")) {
            save = save.substring(7);
            if (save.contains("/")) {
                int index = save.indexOf("/");
                save = save.substring(0, index + 1);
                return "http://" + save;

            }
            return "http://" + save + "/";
        }
        if (save.startsWith("https://")) {
            save = save.substring(8);
            if (save.contains("/")) {
                int index = save.indexOf("/");
                save = save.substring(0, index + 1);
                return "https://" + save;

            }
            return "https://" + save + "/";
        }
        return "InValid URL";

    }

    public String checkURL(String URL) { //// For generate enumeration ///
        /*
         * Check if the given URL in right format
         */
        URL = URL.toLowerCase();
        String save = URL.trim(); //// remove spaces /////
        String numbers = "1234567890";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Character temp = '?';
        String temp2 = "";
        int index = 0;
        int hypthen = 0;
        String http = "http://";
        String slash = "/";
        String https = "https://";
        String space = " ";
        String WWW = "www.";
        try {
            if (save.contains(http) || save.contains(slash) || save.contains(https) || save.contains(space) || save.startsWith(WWW)) {
                throw new Exception("Enter valid URL");
            }
            if (save.startsWith("-") || save.endsWith("-") || save.startsWith(".") || save.endsWith(".")) {
                throw new Exception("Valid URL doesn't start with '-','.' or end with it");
            }
            while (index < save.length()) {
                temp = save.charAt(index);
                temp2 = temp.toString();
                if (!(numbers.contains(temp2) || alphabet.contains(temp2) || temp2.equals("."))) {
                    if (temp == '-' && hypthen < 1) {
                        hypthen++;
                    } else {
                        throw new Exception("Enter valid URL");
                    }
                }
                index++;

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "InvalidURL", 0);

        }

        return save;
    }

    public boolean checkURLsForOriginal(String save) {
        /*
         * Another method for checking if the given URL in right format
         */


        if (save == null) {
            return false;
        }
        if (!(save.startsWith("http://") || save.startsWith("https://"))) {
            return false;
        }
        return true;
    }

    public void splitEnumerationArray(ArrayList<String> enumeration) {
        /*
         * Devide the enumeration Array into 4 arrays
         */
        for (int i = 0; i < enumeration.size(); i = i + 4) {

            thread1.add(enumeration.get(i));
            if (i + 1 < enumeration.size()) {
                thread2.add(enumeration.get(i + 1));
            }
            if (i + 2 < enumeration.size()) {
                thread3.add(enumeration.get(i + 2));
            }
            if (i + 3 < enumeration.size()) {
                thread4.add(enumeration.get(i + 3));
            }

        }
    }

    public String extractTopLevelDomain(String URL) {
        /*
         * Extract top level domain from given URL
         */
        String temp = this.bringMainDomain(URL);
        String tempTopLevel = "null";
        if (!temp.equals("InValid URL")) {
            String save = temp;
            int size5 = save.length() - 5;
            int size4 = save.length() - 4;
            int size7 = save.length() - 7;
            int size8 = save.length() - 8;
            if (save.charAt(size5) == '.') {
                tempTopLevel = save.substring(size5 + 1, save.length() - 1);
            }
            if (save.charAt(size4) == '.') {
                if (save.charAt(size8) == '.') {
                    return save.substring(size8 + 1, size8 + 4);
                }
                if (save.charAt(size7) == '.') {
                    return save.substring(size7 + 1, size7 + 3);
                }
                tempTopLevel = save.substring(size4 + 1, save.length() - 1);
            }
        }

        return tempTopLevel;
    }

    public String addHttp(String url) {
        /*
         * add http for the given URL (in case given URL doesn't containe http
         */
        String save = url;
        if (!save.startsWith("http://") && !save.startsWith("https://")) {
            return "http://" + save;
        }
        return save;
    }

    public String extractMainDomain(String URL) {
        /*
         * Run bringMainDomain method
         */
        String temp = this.bringMainDomain(URL);
        String save = temp;
        String mainDomain = "null";
        if (!temp.equals("InValid URL")) {
            if (save.startsWith("http://")) {
                if (save.charAt(save.length() - 5) == '.') {
                    mainDomain = save.substring(7, save.length() - 5);
                }
                if (save.charAt(save.length() - 4) == '.') {
                    mainDomain = save.substring(7, save.length() - 4);
                }
                if (save.charAt(save.length() - 4) == '.' && save.charAt(save.length() - 7) == '.') {
                    mainDomain = save.substring(7, save.length() - 7);
                }
                if (save.charAt(save.length() - 4) == '.' && save.charAt(save.length() - 8) == '.') {
                    mainDomain = save.substring(7, save.length() - 8);
                }

            }

            if (save.startsWith("https://")) {
                if (save.charAt(save.length() - 5) == '.') {
                    mainDomain = save.substring(8, save.length() - 5);
                }
                if (save.charAt(save.length() - 4) == '.') {
                    mainDomain = save.substring(8, save.length() - 4);
                }
                if (save.charAt(save.length() - 4) == '.' && save.charAt(save.length() - 7) == '.') {
                    mainDomain = save.substring(8, save.length() - 7);
                }
                if (save.charAt(save.length() - 4) == '.' && save.charAt(save.length() - 8) == '.') {
                    mainDomain = save.substring(8, save.length() - 8);
                }


            }
            if (mainDomain.startsWith("www.")) {
                return mainDomain.substring(4);
            }
        }
        return mainDomain;

    }

    public int runDetectWithoutEnumeration(java.util.Vector<String> URLs, String list_name) { /// Detect without enumeration ///
        /*
         * Run phishing detection methods without generate enumerations
         */

//        PhishTankDataBase pd = new PhishTankDataBase();
        ArrayList<String> save = new ArrayList<String>();
        WebSiteDA WDA = new WebSiteDA();
        HistoryDA HDA = new HistoryDA();
        int number = HDA.countWebsites();
        Time_Date TD = new Time_Date();
        String time = TD.systemDateTime();
        int year = TD.extracYeartFromSystemdate(time);
        int month = TD.extractMonthFromSystemdate(time);
        int day = TD.extractDayFromSystemdate(time);
        int hour = TD.extractHourFromSystemdate(time);
        int minute = TD.extractMinuteFromSystemdate(time);
        int count = URLs.size();
        int i = 0;
        boolean inPhishTank = false;
        SaxParser SP = new SaxParser();
        try {
            SP.list();
        } catch (Exception ex) {
          // Logger.getLogger(PhishingPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (i < count) {
            try {
                String s = URLs.get(i);
                if (s == null) {
                    throw new Exception();
                }
                
                boolean check = this.checkURLsForOriginal(s);
                if (!check == false) {
                    list_name = this.runGetCategoryForWebsite(s);
                    inPhishTank = SP.verifyPhishInPhishTank(s);
                    if (!inPhishTank) {
                        ///// My tech //////
                        String phishingState = this.runSourceCodeTechnique(s, "None", list_name, "Original");
                        if (phishingState.equals("Suspicious")) {
                            this.secondRunSourceCodeTechnique(s, "None", list_name, "Original");
                        }
                    } else {
                        //Time_Date tD = new Time_Date();
                        TD.insertDetectingTime(minute, hour, day, month, year, "Detecting");
                        synchronized (wda) {
                            WDA.insertWithoutSourceCode(s, "InValid", "Online", "Original", "None", list_name, TD.getTime_DateID(minute, hour, day, month, year, "Detecting"));
                        }
                    }
                    Connection con = new Connection();
                    String ss = con.getAnySourceCode(s);
                    if (!ss.equals("Offline")) {
                        save = WDA.getOriginalWebsite(String.valueOf(con.responceAnyURL));
                    } else {
                        save = WDA.getOriginalWebsite(s);
                    }
                    int ID = WDA.getWebsiteID(save.get(0), "Original");
                    HDA.transferFromWebsiteToHistory(ID, save.get(0), save.get(1), save.get(2), Integer.parseInt(save.get(3)));

                }
            } catch (Exception e) {
                // Logger.getLogger(PhishingPage.class.getName()).log(Level.SEVERE, null, e);
            }
            i++;
        }
        return HDA.countWebsites() - number;
    }

    public void runRetriveCategoryDetectingResults(DefaultTableModel df, String list_name) {
        /*
         * Get detecting results
         */

        new WebSiteDA().getDetectingCategoryWebsites(df, list_name);
    }
    public void runPhishingWebsitesForURL(DefaultTableModel df,String URL){
        /*
         * Return number of Phishing websits for specific website
         */
        new WebSiteDA().phishingWebsitesForURL(df, URL);
    }

    public void runGetAutoRunResults(DefaultTableModel df, String list_name) {
        /*
         * Get phishing results provided by auto run
         */
        new WebSiteDA().getAutoRunResults(df, list_name);

    }

    public int runDetectingWithEnumeration(java.util.Vector<String> URLs, String list_name) {/// run detecting for list_name with Enumeration ///
        /*
         * Run phishing detection methods with generate enumerations
         */
        WebSiteDA WDA = new WebSiteDA();
        HistoryDA HDA = new HistoryDA();
        int number = HDA.countWebsites();
        ArrayList<ArrayList> Columns = new ArrayList<ArrayList>();
        //java.util.Vector<String> save = WDA.getOriginalURLs(list_name);
        int count = 0;
        iDetectThreads.count = 4;
        while (count < URLs.size()) {
            if (iDetectThreads.count > 3) {
                iDetectThreads.count = 0;
                WebSite w = new WebSite();
                //w.extractTopLevelDomain(URLs.get(count))
                WebSite website = new WebSite(w.extractMainDomain(URLs.get(count)), "com", false, false, false);
                ArrayList<String> ar = website.generateEnumeration();
                website.splitEnumerationArray(ar);
                iDetectThreads thread1 = new iDetectThreads("1st Thread", website.thread1, URLs.get(count), list_name, "Enumeration");
                iDetectThreads thread2 = new iDetectThreads("2nd thread", website.thread2, URLs.get(count), list_name, "Enumeration");
                iDetectThreads thread3 = new iDetectThreads("3rd thread", website.thread3, URLs.get(count), list_name, "Enumeration");
                iDetectThreads thread4 = new iDetectThreads("4th thread", website.thread4, URLs.get(count), list_name, "Enumeration");
                Thread t1 = new Thread(thread1);
                Thread t2 = new Thread(thread2);
                Thread t3 = new Thread(thread3);
                Thread t4 = new Thread(thread4);
                t1.start();
                t2.start();
                t3.start();
                t4.start();
                count++;
            }
        }
        
        while (iDetectThreads.secondCount < URLs.size() * 4) {

        }
        for (int j = 0; j < URLs.size(); j++) {
            Columns = WDA.getEnumerationWebsites(URLs.get(j));
            HDA.runTransferFromWebsiteToHistory(Columns);
            WDA.deleteEnumerationWebsite(URLs.get(j));
        }
        number = HDA.countWebsites() - number;
        return number;

    }

    public java.util.Vector<String> runGetCategoryNames(String type) {
        /*
         * bring all Category names
         */
        return new WebSiteDA().getCategoryNames(type);
    }

    public java.util.Vector<String> getAllCategoryWebsite(String category_name, String type) {
        /*
         *  get all non enumeration websites
         */
        return new WebSiteDA().getOriginalURLs(category_name, type);
    }

    public void runSaveWebsites(String list_name, JTable JT) {
        /*
         * Take website(s) from Jtable then pass it individualy to saveWebsite with Category name
         */
        int i = 0;
        while (i < JT.getRowCount()) {

            String Url = (String) JT.getValueAt(i, 0);
            String autorun = (String) JT.getValueAt(i, 1);
            if (Url != null) {
                Url = this.addHttp(Url);
                if (!new WebSiteDA().checkAvailablity(Url, "Original")) {
                    new WebSiteDA().saveWebsites(list_name, Url, autorun);
                }
            }
            i++;
        }
    }

    public void runModifyCategory(JTable jt, Vector<String> oldValue, String list_name, String type) {
        /*
         *  update URL in DB
         */
        int j = 0;
        for (int i = 0; i < jt.getRowCount(); i++) {
            if (!jt.getValueAt(i, 0).toString().isEmpty()) {
                if (jt.getValueAt(i, 0) != oldValue.get(j)) {
                    new WebSiteDA().updateOriginalURLs((String) jt.getValueAt(i, 0), oldValue.get(j), list_name);

                }
                j++;
            } else {
                if (type.equals("Hacking")) {
                    new WebSiteDA().deleteWebsite(oldValue.get(j), type, list_name);
                } else {
                        if (jt.getValueAt(i, 0) != oldValue.get(j)) {
                            new WebSiteDA().updateOriginalURLs((String) jt.getValueAt(i, 0), oldValue.get(j), list_name);

                        }
                }
                j++;
            }

        }
    }

    public ArrayList<String> runModifyWebsiteValue(DefaultTableModel df, String url, String type) {
        /*
         *  modify website isPhished value
         */
        return new WebSiteDA().getWebsite(df, url, type);
    }

    public void runModifyCategoryWebsitesValue(DefaultTableModel df, String category_name, String type) {
        /*
         * Get the information(Valid?,Online... etc) for specific URL
         */
        new WebSiteDA().getCategoryAllWebsites(df, category_name, type);
    }

    public void runGetAllWebsitesByState(DefaultTableModel df, String state, String type) {
        new WebSiteDA().getAllWebsitesByState(df, state, type);
    }

    public java.util.Vector<String> runPrepareDetectList(String type) {
        /*
         * Get all websites
         */
        return new WebSiteDA().getAllWebsites(type);
    }

    public void runUpdatePhishingState(String Url, String phishingState) {
        /*
         * Update Phishing State for given URL
         */
        new WebSiteDA().updatePhishingState(Url, phishingState);
    }
    public boolean  runGetAllEnumeration(String first,String second){
         /*
         * Find out if given replacments found in DB
         */
        return new WebSiteDA().getAllEnumeration(first, second);
    }

    public void runPhishingCustomization(String first, String second) {
        new WebSiteDA().saveEnumeration(first, second);
    }

    public Vector<Character> runFirstPhishingCustomization() {
        return new WebSiteDA().getFirstEnumeration();
    }

    public Vector<Character> runSecondPhishingCustomization() {
        return new WebSiteDA().getSecondEnumeration();
    }

    public void runSpeceficPeriodQuery(DefaultTableModel df, int year, int month, int day, String type) {
        /*
         * Get all websites information in given period for statistical purpose
         */
        new WebSiteDA().specificPeriodQuery(df, year, month, day, type);
    }

    public void runMostAttackedQuery(DefaultTableModel df) {
        /*
         * Get most attacked websites for statistical purpose
         */
        new WebSiteDA().mostAttackedQuery(df);
    }

    public void runMostActivePeriodQuery(DefaultTableModel df, String type) {
        /*
         * Get most active period for statistical purpose
         */
        new WebSiteDA().mostActivePeriodQuery(df, type);
    }

    public void runEnumerationNumberQuery(DefaultTableModel df) {
        /*
         * Get number of enumeration for statistical purpose
         */
        new WebSiteDA().enumerationNumber(df);
    }

    public Vector<String> runAutoRun(String type) {
        /*
         * Related to auto run technique
         */
        return new WebSiteDA().getAutoRunWebsites(type);
    }

    public String runGetCategoryForWebsite(String URL) {
        /*
         * Get Category name for given URL
         */
        return new WebSiteDA().getCategoryForWebsite(URL);
    }

    public void prepareExternalFile(File file, String type) {
        /*
         * Insert into DB websites given by external file
         */
        new WebSiteDA().saveWebsitesFromExternalFile(file, type);
    }
    public void runPeriodQuery(DefaultTableModel df, int startYear, int startMonth, int endYear, int endMonth, String type){
        /*
         * Get websites in given duration
         */
        new WebSiteDA().periodQuery(df, startYear, startMonth, endYear, endMonth, type);
    }

////////////////////////////////////////// OSAMA PART ////////////////////////////////////////////////
    public String removeWWW(String URL) {
        /*
         * remove www. from given URL
         */
        if (URL.contains("://www.")) {
            if (URL.startsWith("http://www.")) {
                return "http://" + URL.substring(11);
            }
            if (URL.startsWith("https://www.")) {
                return "https://" + URL.substring(12);
            }
        }
        return URL;
    }

    public void runUpdateHackingState(String url, String is_hacked) {
        WebSiteDA.updateHackingState(url, is_hacked);
    }

    public String checkActivity() {
        /*
         * To check this website if it is online or offline and return a String (online or offline).
         */
        String result = "online", temp;
        try {
            temp = getContentHTMLString(this.getUrl());
        } catch (Exception ex) {
            result = "offline";
            this.setActive(false);
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);

        }
        return result;
    }

    public ArrayList<String> runModifyHackingWebsiteValue(DefaultTableModel df, String url, String type) {
        return WebSiteDA.getHackingWebsite(df, url, type);
    }

    public void reloadSourceCode() {
        /*
         * To reload the current content of the source code for this website.
         */
        try {
            this.setSourceCode(getContentHTMLString(this.getUrl()));
            this.setActive(true);
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
            this.setSourceCode("offline");
            this.setActive(false);
        }

    }

    public String getContentHTMLString(String url) throws IOException {
        /*
         * To retrive the content of the source code for this website as a String.
         */

        String html = "";



        URL url2 = new URL(url);
        InputStream stream = url2.openStream();
        html = convertStreamToString(stream);


        return html;
    }

    public String convertStreamToString(InputStream is) throws IOException {
        /*
         * To convert the InputStream to String we use the BufferedReader.readLine()
         * method. We iterate until the BufferedReader return null which means
         * there's no more data to read. Each line will appended to a StringBuilder
         * and returned as String.
         */
        if (is != null) {
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } finally {
                is.close();
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    public void OpenGoogleServiceWebpage() {
        /*
         * To open Google safebrowsing webpage service for checking this website if it is listed as
         * suspicious one or not.
         */
        Desktop myNewBrowserDesktop = Desktop.getDesktop();
        URI myNewLocation = null;
        String updatedURL = "";
        if (this.getUrl().indexOf("https://") != -1) {
            int starthttp = this.getUrl().indexOf("https://" + 8);
            updatedURL = this.getUrl().substring(starthttp);

        } else {
            int starthttp = this.getUrl().indexOf("http://");
            updatedURL = this.getUrl().substring(starthttp + 7);
        }
        try {
            myNewLocation = new URI("http://www.google.com/safebrowsing/diagnostic?site=" + updatedURL);
            myNewBrowserDesktop.browse(myNewLocation);
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
        }


    }

    public int computeTitleHashValue() {
        /*
         * To compute the current hash value of this website's title and return the result as int.
         */
        int result = 0;
        String currentTitle = "";
        if (isActive()) {
            if (this.getSourceCode().indexOf("<title>") != -1) {
                int start = this.getSourceCode().indexOf("<title>");
                int end = this.getSourceCode().indexOf("</title>");
                currentTitle = this.getSourceCode().substring(start, end + 8);
                try {
                    result = HashFunction.computeHash(currentTitle, "utf8", "MD5");
                } catch (Exception ex) {
                    //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
                }
            } else {
                if (this.getSourceCode().indexOf("<TITLE>") != -1) {
                    int start = this.getSourceCode().indexOf("<TITLE>");
                    int end = this.getSourceCode().indexOf("</TITLE>");

                    currentTitle = this.getSourceCode().substring(start, end + 8);
                    try {
                        result = HashFunction.computeHash(currentTitle, "utf8", "MD5");
                    } catch (Exception ex) {
                        //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
                    }
                }
            }
        }

        return result;
    }

    public void confirmTitle() {
        /*
         * This function will ask the user to confirm this website's title by showing the current
         * title and compute its hash value and determine if it is HACKED or NOT.
         */
        String currentTitle = "";
        reloadSourceCode();
        if (!this.sourceCode.equals("offline")) {
            if (this.getSourceCode().indexOf("<title>") != -1) {
                int start = this.getSourceCode().indexOf("<title>");
                int end = this.getSourceCode().indexOf("</title>");
                currentTitle = this.getSourceCode().substring(start, end + 8);
                int input = JOptionPane.showConfirmDialog(null, "Is this the right title ( " + currentTitle + " ) for this URL ( " + this.getUrl() + " )", "Confirm Title", 0);
                if (input == 0) {
                    try {
                        this.setTitleHashValue(HashFunction.computeHash(currentTitle, "utf8", "MD5"));
                        changeTitle_Hash_Value(this.getTitleHashValue());
                        changeIs_HackedValue("safe", "");

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
                    }
                } else {
                    this.setIsHacked(true);
                    this.setTitleHashValue(-1);
                    changeTitle_Hash_Value(this.getTitleHashValue());
                    if (this.getDetectionType().equals("")) {
                        this.setDetectionType("Confirm Title");
                    } else {
                        this.setDetectionType(this.getDetectionType() + " + Confirm Title");
                    }
                    changeIs_HackedValue("hacked", this.getDetectionType());
                }
            } else {
                if (this.getSourceCode().indexOf("<TITLE>") != -1) {
                    int start = this.getSourceCode().indexOf("<TITLE>");
                    int end = this.getSourceCode().indexOf("</TITLE>");

                    currentTitle = this.getSourceCode().substring(start, end + 8);
                    int input = JOptionPane.showConfirmDialog(null, "Is this the right title ( " + currentTitle + " ) for this URL ( " + this.getUrl() + " )", "Confirm Title", 0);
                    if (input == 0) {
                        try {
                            this.setTitleHashValue(HashFunction.computeHash(currentTitle, "utf8", "MD5"));
                            changeTitle_Hash_Value(this.getTitleHashValue());
                            changeIs_HackedValue("safe", "");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
                        }
                    } else {
                        this.setIsHacked(true);
                        this.setTitleHashValue(-1);
                        changeTitle_Hash_Value(this.getTitleHashValue());
                        if (this.getDetectionType().equals("")) {
                            this.setDetectionType("Confirm Title");
                        } else {
                            this.setDetectionType(this.getDetectionType() + " + Confirm Title");
                        }
                        changeIs_HackedValue("hacked", this.getDetectionType());
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "This URL ( " + this.getUrl() + " ) is offline !!", "Confirm Title Eception", 0);
            this.setTitleHashValue(0);
            changeTitle_Hash_Value(this.getTitleHashValue());
        }

    }

    public boolean checkWebsiteByZoneH() {
        /*
         * To check if this website is HACKED or not by searching about it in Zone-h website and
         * return a boolean value (true if it is hacked otherwise false).
         */
        int indexZoneWebsite = -1;
        boolean hacked = false;

        String html = "", updatedURL = "";

        if (this.getUrl().indexOf("https://") != -1) {
            int starthttp = this.getUrl().indexOf("https://" + 8);
            updatedURL = this.getUrl().substring(starthttp);


        } else {
            int starthttp = this.getUrl().indexOf("http://");
            updatedURL = this.getUrl().substring(starthttp + 7);

        }

        for (int i = 1; i <= 50 && indexZoneWebsite == -1; i++) {
            try {
                html = getContentHTMLString("http://zone-h.org/archive/published=0/page=" + Integer.toString(i));
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
            }

            indexZoneWebsite = html.indexOf(updatedURL);
            if (indexZoneWebsite != -1) {
                hacked = true;
                if (this.getDetectionType().equals("")) {
                    this.setDetectionType("Zone-h.org");
                } else {
                    this.setDetectionType(this.getDetectionType() + " + Zone-h.org");
                }
            }
        }
        if (indexZoneWebsite == -1) {

            hacked = false;
        }

        return hacked;

    }

    public boolean checkByGoogleService() {
        /*
         * To check if this website is HACKED or not by using Google safe browsing service and
         * return a boolean value (true if it is hacked otherwise false).
         */

        String status = "", html = "", updatedURL = "";
        boolean hacked = false;
        if (this.getUrl().indexOf("https://") != -1) {
            int starthttp = this.getUrl().indexOf("https://" + 8);
            updatedURL = this.getUrl().substring(starthttp);

        } else {
            int starthttp = this.getUrl().indexOf("http://");
            updatedURL = this.getUrl().substring(starthttp + 7);
        }

        try {
            html = getContentHTMLString("http://www.google.com/safebrowsing/diagnostic?site=" + updatedURL);
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "Eception Error", 0);
        }
        int indexOfStartStatus = html.indexOf("<blockquote><p>");
        int indexOfEndtStatus = html.indexOf("</p>", indexOfStartStatus + 1);

        status = html.substring(indexOfStartStatus + 15, indexOfEndtStatus);
        if (!status.contains("is not")) {
            hacked = true;
            if (this.getDetectionType().equals("")) {
                this.setDetectionType("Google Service");
            } else {
                this.setDetectionType(this.getDetectionType() + " + Google Service");
            }
        }
        return hacked;



    }

    public boolean checkByHashFunction() {
        /*
         * To check if this website is HACKED or not by comparing between the hash value for
         * the current title and the hash value for the same website in the database. if they are
         * not equal so this website is hacked and the (true) value will be returned, otherwise
         * the (false) value will be returned.
         */

        int temp_value = 0;
        boolean hacked = false;

        temp_value = computeTitleHashValue();

        if (temp_value != 0) {
            if (this.getTitleHashValue() != temp_value) {
                hacked = true;
                if (this.getDetectionType().equals("")) {
                    this.setDetectionType("Hash Function");
                } else {
                    this.setDetectionType(this.getDetectionType() + " + Hash Function");
                }
            }
        }
        return hacked;

    }

    public boolean checkHeadExisting() {
        /*
         * To check if this website is HACKED or not by looking for HEAD part in the source code
         * if it is not exist, this website is HACKED and the (true) value will be returned, otherwise
         * the (false) value will be returned.
         */
        boolean hacked = false;

        if (this.getSourceCode().indexOf("<head") == -1) {
            if (this.getSourceCode().indexOf("<HEAD") == -1) {
                hacked = true;
                if (this.getDetectionType().equals("")) {
                    this.setDetectionType("Head Existing");
                } else {
                    this.setDetectionType(this.getDetectionType() + " + Head Existing");
                }
            }
        }


        return hacked;
    }

    public boolean checkBodyExisting() {
        /*
         * To check if this website is HACKED or not by looking for BODY part in the source code
         * if it is not exist, this website is HACKED and the (true) value will be returned, otherwise
         * the (false) value will be returned.
         */
        boolean hacked = false;

        if (this.getSourceCode().indexOf("<body") == -1) {
            if (this.getSourceCode().indexOf("<BODY") == -1) {
                hacked = true;
                if (this.getDetectionType().equals("")) {
                    this.setDetectionType("Body Existing");
                } else {
                    this.setDetectionType(this.getDetectionType() + " + Body Existing");
                }
            }
        }


        return hacked;

    }

    public boolean checkHackedKeyword() {
        /*
         * To check if this website is HACKED or not by looking for a specific KEYWORD in the source
         * code. If we found it then this website is HACKED and the (true) value
         * will be returned, otherwise the (false) value will be returned.
         * (the KEYWORD like {hacked, defaced, HacKed..ect} wich they have been written
         * by THE HACKER himself. Many of these KEYWORDs are stored in hacked_keywords table in the database)
         */
        hacked_keywordsDA keyword = new hacked_keywordsDA();
        Vector<String> ar = keyword.getHackedKeywords();
        int i = 0;
        boolean hacked = false;

        if (!ar.isEmpty()) {
            for (i = 0; i < ar.size() && hacked == false; i++) {
                if (this.getSourceCode().indexOf(ar.get(i)) != -1) {
                    hacked = true;
                    if (this.getDetectionType().equals("")) {
                        this.setDetectionType("Hacked Keyword");
                    } else {
                        this.setDetectionType(this.getDetectionType() + " + Hacked Keyword");
                    }
                }
            }
        }

        return hacked;

    }

    public boolean checkHackerNicknames() {
        /*
         * To check if this website is HACKED or not by looking for a specific NICKNAME in the source
         * code. If we found it then this website is HACKED and the (true) value
         * will be returned, otherwise the (false) value will be returned.
         * (the NICKNAME is belong to some HACKER wich they have been written by THE HACKER himself.
         * Many of these NICKNAMEs are stored in hacker_nicknames table in the database).
         */
        hacker_nicknamesDA nickname = new hacker_nicknamesDA();
        Vector<String> ar = nickname.getHackerNicknames();
        int i = 0;
        boolean hacked = false;

        if (!ar.isEmpty()) {
            for (i = 0; i < ar.size() && hacked == false; i++) {
                if (this.getSourceCode().indexOf(ar.get(i)) != -1) {
                    hacked = true;
                    if (this.getDetectionType().equals("")) {
                        this.setDetectionType("Hacker Nickname");
                    } else {
                        this.setDetectionType(this.getDetectionType() + " + Hacker Nickname");
                    }
                }
            }
        }

        return hacked;

    }

    public ArrayList<String> GetAllJavaScript() {
        /*
         * To retrieve an ArralyList<String> that contain all JAVASCRIPTs in the source code for this websit.
         */

        ArrayList<String> allJavaScript = null;
        if (!this.sourceCode.equals(null)) {

            allJavaScript = new ArrayList<String>();
            boolean add = false;
            int startScriptIndex, endScriptIndex = -1;
            String script = null;

            if (this.getSourceCode().indexOf("<script") != -1) {

                startScriptIndex = this.getSourceCode().indexOf("<script");
                endScriptIndex = this.getSourceCode().indexOf("</script>", startScriptIndex);
                script = this.getSourceCode().substring(startScriptIndex, endScriptIndex + 9);
                add = allJavaScript.add(script);

                while (this.getSourceCode().indexOf("<script", startScriptIndex + 1) != -1) {
                    startScriptIndex = this.getSourceCode().indexOf("<script", startScriptIndex + 1);
                    endScriptIndex = this.getSourceCode().indexOf("</script>", startScriptIndex + 1);
                    script = this.getSourceCode().substring(startScriptIndex, endScriptIndex + 9);
                    add = allJavaScript.add(script);
                }

            }
            if (this.getSourceCode().indexOf("<SCRIPT") != -1) {

                startScriptIndex = endScriptIndex = -1;

                startScriptIndex = this.getSourceCode().indexOf("<SCRIPT");
                endScriptIndex = this.getSourceCode().indexOf("</SCRIPT>", startScriptIndex);
                script = this.getSourceCode().substring(startScriptIndex, endScriptIndex + 9);
                add = allJavaScript.add(script);

                while (this.getSourceCode().indexOf("<SCRIPT", startScriptIndex + 1) != -1) {
                    startScriptIndex = this.getSourceCode().indexOf("<SCRIPT", startScriptIndex + 1);
                    endScriptIndex = this.getSourceCode().indexOf("</SCRIPT>", startScriptIndex + 1);
                    script = this.getSourceCode().substring(startScriptIndex, endScriptIndex + 9);
                    add = allJavaScript.add(script);
                }




            }
        } else {
            JOptionPane.showMessageDialog(null, "The Content of The Source Code For This Webpage ( " + this.getUrl() + " ) Is NULL!!", "Report of All Javascripts", 0);
        }


        return allJavaScript;
    }

    public ArrayList<String> GetAllIframe() {
        /*
         * To retrieve an ArralyList<String> that contain all IFRAMEs in the source code for this websit.
         */
        ArrayList<String> allIframe = null;
        if (!this.sourceCode.equals(null)) {
            allIframe = new ArrayList<String>();
            boolean add = false;
            int startIframeIndex, endIframeIndex = -1;
            String iframe = null;

            if (this.getSourceCode().indexOf("<iframe") != -1) {

                startIframeIndex = this.getSourceCode().indexOf("<iframe");
                if (this.getSourceCode().indexOf("</iframe>", startIframeIndex + 1) != -1) {
                    endIframeIndex = this.getSourceCode().indexOf("</iframe>", startIframeIndex + 1);
                } else {
                    endIframeIndex = this.getSourceCode().indexOf(">", startIframeIndex + 1);
                }

                iframe = this.getSourceCode().substring(startIframeIndex, endIframeIndex + 9);
                add = allIframe.add(iframe);

                while (this.getSourceCode().indexOf("<iframe", startIframeIndex + 1) != -1) {
                    startIframeIndex = this.getSourceCode().indexOf("<iframe", startIframeIndex + 1);
                    if (this.getSourceCode().indexOf("</iframe>", startIframeIndex + 1) != -1) {
                        endIframeIndex = this.getSourceCode().indexOf("</iframe>", startIframeIndex + 1);
                    } else {
                        endIframeIndex = this.getSourceCode().indexOf(">", startIframeIndex + 1);
                    }

                    iframe = this.getSourceCode().substring(startIframeIndex, endIframeIndex + 9);
                    add = allIframe.add(iframe);
                }

            }
            if (this.getSourceCode().indexOf("<IFRAME") != -1) {

                startIframeIndex = endIframeIndex = -1;

                startIframeIndex = this.getSourceCode().indexOf("<IFRAME");
                if (this.getSourceCode().indexOf("</IFRAME>", startIframeIndex + 1) != -1) {
                    endIframeIndex = this.getSourceCode().indexOf("</IFRAME>", startIframeIndex + 1);
                } else {
                    endIframeIndex = this.getSourceCode().indexOf(">", startIframeIndex + 1);
                }

                iframe = this.getSourceCode().substring(startIframeIndex, endIframeIndex + 9);
                add = allIframe.add(iframe);

                while (this.getSourceCode().indexOf("<IFRAME", startIframeIndex + 1) != -1) {
                    startIframeIndex = this.getSourceCode().indexOf("<IFRAME", startIframeIndex + 1);
                    if (this.getSourceCode().indexOf("</IFRAME>", startIframeIndex + 1) != -1) {
                        endIframeIndex = this.getSourceCode().indexOf("</IFRAME>", startIframeIndex + 1);
                    } else {
                        endIframeIndex = this.getSourceCode().indexOf(">", startIframeIndex + 1);
                    }

                    iframe = this.getSourceCode().substring(startIframeIndex, endIframeIndex + 9);
                    add = allIframe.add(iframe);
                }

            }


        } else {
            JOptionPane.showMessageDialog(null, "The Content of The Source Code For This Webpage ( " + this.getUrl() + " ) Is NULL!!", "Report of All Iframe", 0);
        }

        return allIframe;
    }

    public ArrayList<String> GetAllMeta() {
        /*
         * To retrieve an ArralyList<String> that contain all METAs in the source code for this websit.
         */
        ArrayList<String> allIMeta = null;
        if (!this.sourceCode.equals(null)) {
            allIMeta = new ArrayList<String>();
            boolean add = false;
            int startMetaIndex, endMetaIndex = -1;
            String meta = null;

            if (this.getSourceCode().indexOf("<meta") != -1) {

                startMetaIndex = this.getSourceCode().indexOf("<meta");
                endMetaIndex = this.getSourceCode().indexOf(">", startMetaIndex);
                meta = this.getSourceCode().substring(startMetaIndex, endMetaIndex + 1);
                add = allIMeta.add(meta);

                while (this.getSourceCode().indexOf("<meta", startMetaIndex + 1) != -1) {
                    startMetaIndex = this.getSourceCode().indexOf("<meta", startMetaIndex + 1);
                    endMetaIndex = this.getSourceCode().indexOf(">", startMetaIndex + 1);
                    meta = this.getSourceCode().substring(startMetaIndex, endMetaIndex + 1);
                    add = allIMeta.add(meta);
                }

            }
            if (this.getSourceCode().indexOf("<META") != -1) {

                startMetaIndex = endMetaIndex = -1;

                startMetaIndex = this.getSourceCode().indexOf("<META");
                endMetaIndex = this.getSourceCode().indexOf(">", startMetaIndex);
                meta = this.getSourceCode().substring(startMetaIndex, endMetaIndex + 1);
                add = allIMeta.add(meta);

                while (this.getSourceCode().indexOf("<META", startMetaIndex + 1) != -1) {
                    startMetaIndex = this.getSourceCode().indexOf("<META", startMetaIndex + 1);
                    endMetaIndex = this.getSourceCode().indexOf(">", startMetaIndex + 1);
                    meta = this.getSourceCode().substring(startMetaIndex, endMetaIndex + 1);
                    add = allIMeta.add(meta);
                }

            }


        } else {
            JOptionPane.showMessageDialog(null, "The Content of The Source Code For This Webpage ( " + this.getUrl() + " ) Is NULL!!", "Report of All Meta", 0);
        }

        return allIMeta;
    }

    public void insertWebSite(String active, String category_name, String autorun) {
        /*
         * This function receive a String represent the activity of this website(online or offline)
         * and insert this website into the (website) table in the database.
         */

        WebSiteDA.insertWebSiteDA(this.getUrl(), active, this.getTitleHashValue(), category_name, autorun);

    }

    public void updateWebSite(String active) {
        /*
         * This function receive a String represent the status of this website(online or offline)
         * and update the (website) table in the database with this website data.
         */

        WebSiteDA.updateWebSiteDA(this.getWebsiteID(), active, this.getTitleHashValue());

    }

    public void changeIs_HackedValue(String is_hacked, String DetectionTechnique) {
        /*
         * This function receive a String represent the status of this website(hacked or safe)
         * and the detection type. Then update the (website) table in the database with this data.
         */
        WebSiteDA.changeIs_HackedValue(this.getWebsiteID(), is_hacked, DetectionTechnique);
    }

    public void changeTitle_Hash_Value(int hash_value) {
        /*
         * This function receive an int contain the hash value of the title of this website
         * and update the (website) table in the database with this data.
         */
        WebSiteDA.changeTitle_Hash_Value(this.getWebsiteID(), hash_value);
    }

    public static int getWebSiteIDFromDB(String url) {
        /*
         * To retrieve websiteID from the database for a given url.
         */
        return WebSiteDA.getWebSiteID(url);
    }

    public void writeAllScripts() {
        /*
         * To write all java script in the database for this website or update the previous ones.
         */
        if (WebSiteDA.existOfwebsitIDInScript(this.getWebsiteID()) == -1) {
            WebSiteDA.writeNewScripts(this.getWebsiteID(), GetAllJavaScript());
        } else {
            WebSiteDA.updateScripts(this.getWebsiteID(), GetAllJavaScript());
        }
    }

    public void writeAllIframes() {
        /*
         * To write all iframe in the database for this website or update the previous ones.
         */
        if (WebSiteDA.existOfwebsitIDInIframe(this.getWebsiteID()) == -1) {
            WebSiteDA.writeNewIframes(this.getWebsiteID(), GetAllIframe());
        } else {
            WebSiteDA.updateIframes(this.getWebsiteID(), GetAllIframe());
        }
    }

    public void writeAllMetas() {
        /*
         * To write all meta in the database for this website or update the previous ones.
         */
        if (WebSiteDA.existOfwebsitIDInMeta(this.getWebsiteID()) == -1) {
            WebSiteDA.writeNewMetas(this.getWebsiteID(), GetAllMeta());
        } else {
            WebSiteDA.updateMetas(this.getWebsiteID(), GetAllMeta());
        }
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the topLevel
     */
    public String getTopLevel() {
        return topLevel;
    }

    /**
     * @param topLevel the topLevel to set
     */
    public void setTopLevel(String topLevel) {
        this.topLevel = topLevel;
    }

    /**
     * @return the isHacked
     */
    public boolean isIsHacked() {
        return isHacked;
    }

    /**
     * @param isHacked the isHacked to set
     */
    public void setIsHacked(boolean isHacked) {
        this.isHacked = isHacked;
    }

    /**
     * @return the isPhishied
     */
    public boolean isIsPhishied() {
        return isPhishied;
    }

    /**
     * @param isPhishied the isPhishied to set
     */
    public void setIsPhishied(boolean isPhishied) {
        this.isPhishied = isPhishied;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the forHacking
     */
    public boolean isForHacking() {
        return forHacking;
    }

    /**
     * @param forHacking the forHacking to set
     */
    public void setForHacking(boolean forHacking) {
        this.forHacking = forHacking;
    }

    /**
     * @return the forPhishing
     */
    public boolean isForPhishing() {
        return forPhishing;
    }

    /**
     * @param forPhishing the forPhishing to set
     */
    public void setForPhishing(boolean forPhishing) {
        this.forPhishing = forPhishing;
    }

    /**
     * @return the titleHashValue
     */
    public int getTitleHashValue() {
        return titleHashValue;
    }

    /**
     * @param titleHashValue the titleHashValue to set
     */
    public void setTitleHashValue(int titleHashValue) {
        this.titleHashValue = titleHashValue;
    }

    /**
     * @return the detectionType
     */
    public String getDetectionType() {
        return detectionType;
    }

    /**
     * @param detectionType the detectionType to set
     */
    public void setDetectionType(String detectionType) {
        this.detectionType = detectionType;
    }

    /**
     * @return the websiteID
     */
    public int getWebsiteID() {
        return websiteID;
    }

    /**
     * @param websiteID the websiteID to set
     */
    public void setWebsiteID(int websiteID) {
        this.websiteID = websiteID;
    }

    /**
     * @return the sourceCode
     */
    public String getSourceCode() {
        return sourceCode;
    }

    /**
     * @param sourceCode the sourceCode to set
     */
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }
}
