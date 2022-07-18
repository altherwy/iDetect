/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idetect;

import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author youssef 19
 */
public class ProgressThread implements Runnable {

    public java.util.Vector<String> URLs = new java.util.Vector<String>();
    public java.util.Vector<String> Categoriez = new java.util.Vector<String>();
    public String list_name;
    public DefaultTableModel df;
    public InfiniteProgressPanel pane;
    public JTable table;
    public JScrollPane j3;
    public String type;
    public boolean enumeration;

    public ProgressThread(java.util.Vector<String> URLs, String list_name, DefaultTableModel df, JTable table, JScrollPane j3, InfiniteProgressPanel pane, String type, boolean enumeration) {
        this.URLs = URLs;
        this.list_name = list_name;
        this.df = df;
        this.table = table;
        this.j3 = j3;
        this.pane = pane;
        this.type = type;
        this.enumeration = enumeration;

    }

    public ProgressThread(java.util.Vector<String> URLs, DefaultTableModel df, JTable table, JScrollPane j3, InfiniteProgressPanel pane, String type, boolean website) {
        this.enumeration = website;
        this.URLs = URLs;
        this.df = df;
        this.table = table;
        this.j3 = j3;
        this.pane = pane;
        this.type = type;
    }

    public void run() {
        try{
        if (type.equals("Hacking")) {
            if (enumeration) {
                //// website ////
                for (int j = 0; j < URLs.size(); j++) {
                    WebSite wb = new WebSite(URLs.get(j), "None", "no");
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
                WebSiteDA.getHackingResults(df, "None");
                
            } else {
                //// category ///
                for (int j = 0; j < URLs.size(); j++) {
                    Categoriez = new WebSite().getAllCategoryWebsite(URLs.get(j), "Hacking");
                    int ii = 0;
                    while (ii < Categoriez.size()) {
                        WebSite wb = new WebSite(Categoriez.get(ii), URLs.get(j), "no");
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
                        ii++;

                    }
                    WebSiteDA.getHackingResults(df, URLs.get(j));
                }
            }
        } else {
            if (!enumeration) {
                if (type.equals("Website")) {
                    new WebSite().runDetectWithoutEnumeration(URLs, list_name);
                    new WebSite().runRetriveCategoryDetectingResults(df, list_name);
                } else {
                    for (int j = 0; j < URLs.size(); j++) {
                        Categoriez = new WebSite().getAllCategoryWebsite(URLs.get(j), "Phishing");
                        new WebSite().runDetectWithoutEnumeration(Categoriez, URLs.get(j));
                        new WebSite().runRetriveCategoryDetectingResults(df, URLs.get(j));
                    }
                }
            } else {
                if (type.equals("EnumerationWebsite")) {
                    int number = new WebSite().runDetectingWithEnumeration(URLs, "None");
                    new HistoryDA().getResults(df, number);
                } else {
                    for (int j = 0; j < URLs.size(); j++) {
                        Categoriez = new WebSite().getAllCategoryWebsite(URLs.get(j), "Phishing");
                        int number = new WebSite().runDetectingWithEnumeration(Categoriez, URLs.get(j));
                        new HistoryDA().getResults(df, number);
                    }
                }
            }
        }
        }
        catch(Exception ex){
            
        }


        pane.stop();
        table.setModel(df);
        j3.setVisible(true);

    }
}
