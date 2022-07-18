/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idetect;

import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author youssef 19
 */
public class Phishing_Possiblity {

    private String phishingEnumeration;
    private final String HTTP = "http://www.";
    private boolean legitimate;
    private boolean online;
    private WebSite webSite = new WebSite();
    public ArrayList<String> saveWebSites = new ArrayList<String>();
    //public ArrayList<Boolean> webSiteStatues = new ArrayList<Boolean>();
    public char[] replace1 ;
    public char[] replace2 ;
    public Vector<Character> first = new Vector<Character>();
    public Vector<Character> second = new Vector<Character>();
    public char[] QWERTY1 = {'a', 'b', 'b', 'c', 'c', 'd', 'd', 'e', 'e', 'f', 'g', 'h', 'i', 'i', 'j', 'k', 'm', 'o', 'o', 'q', 'r'
        + 't', 'u', 'x'};
    public char[] QWERTY2 = {'s', 'n', 'v', 'x', 'v', 'f', 's', 'r', 'w', 'g', 'h', 'j', 'u', 'o', 'k', 'l', 'n', 'p', 'i', 'w', 't'
        + 'y', 'y', 'z'};
    public String[] topLevelDomain = {"cm", "co", "cc", "mc", "mo", "om", "de", "ee", "eu", "ne", "tn", "et", "gr", "ro", "com", "edu", "net", "org"};
    public Vector<Character> replaceList1 = new Vector<Character>();
    public Vector<Character> replaceList2 = new Vector<Character>();

    public Phishing_Possiblity(String phishingEnumeration, boolean legitimate, boolean online, String url) {
        this.setPhishingEnumeration(phishingEnumeration);
        this.setLegitimate(legitimate);
        this.setOnline(online);
        this.webSite.setUrl(url);
        replaceList1 = new WebSiteDA().getFirstEnumeration();
        replaceList2 = new WebSiteDA().getSecondEnumeration();


    }
    public Phishing_Possiblity(){
        this.setPhishingEnumeration("");
        this.setLegitimate(false);
        this.setOnline(false);
        this.webSite.setUrl("");
    }
    ////// Dublicate given URL letters //////

    public ArrayList<String> dublicateTechnique(String URL, String topLevel) {
        /*
         * Using to duplicate each character in the given URL from the start to the end
         */
        String save = URL;

        for (int i = 0; i < URL.length(); i++) {
            char c = URL.charAt(i);
            String first = URL.substring(0, i);
            String second = URL.substring(i);
            save = HTTP + first + c + second + "."+topLevel;
            saveWebSites.add(save);
            save = URL;
        }
        return saveWebSites;
    }
    ////// Drop a letter from given URL //////

    public ArrayList<String> dropCharTechnique(String URL, String topLevel) {
        /*
         * Using to drop character from the given URL from the start to the end
         */
        String save = URL;
        for (int i = 0; i < URL.length(); i++) {
            char c = URL.charAt(i);
            String firstString = URL.substring(0, i);
            String secondString = URL.substring(i + 1);
            save = HTTP+firstString + secondString + "."+topLevel;
            saveWebSites.add(save);
            save = URL;
        }
        return saveWebSites;

    }
    ////// Delete repeated websites in the ArrayList //////

    public ArrayList<String> deleteRepeatedEnumeration(ArrayList<String> ar) {
        /*
         * Using to delete similar enumeration websites
         */
        ArrayList<String> saveArrayList = new ArrayList<String>();
        for (int i = 0; i < ar.size(); i++) {
            if (!saveArrayList.contains(ar.get(i))) {
                saveArrayList.add(ar.get(i));
            }
        }
        return saveArrayList;
    }
    ////// Implement common replacement technique //////

    public ArrayList<String> commonReplacement(String URL, String topLevel) {
        /*
         * Using the common replacement table to Generate enumeration
         */
        String temp = URL;
        //this.initialReplaceList(replace1,replaceList1);
        //this.initialReplaceList(replace2, replaceList2);
        int i = 0;
        while (i < temp.length()) {
            char c = temp.charAt(i);
            for (int j = 0; j < replaceList1.size(); j++) {

                if (c == replaceList1.get(j)) {
                    temp = Myreplace(replaceList2.get(j), temp, i);
                    saveWebSites.add(HTTP+temp + "."+topLevel);
                    temp = URL;
                }
            }
            i++;
        }
        temp = URL;
        i = 0;
        while (i < temp.length()) {
            char c = temp.charAt(i);
            for (int j = 0; j < replaceList2.size(); j++) {

                if (c == replaceList2.get(j)) {
                    temp = Myreplace(replaceList1.get(j), temp, i);
                    saveWebSites.add(HTTP+temp + "."+topLevel);
                    temp = URL;
                }
            }
            i++;
        }
        return saveWebSites;

    }
    ////// My own version of String.replace //////

    public String Myreplace(char newChar, String word, int index) {
        /*
         * (replace) method instead of String.replace
         */
        int y = word.length();
        char[] c = new char[y];
        for (int i = 0; i < word.length(); i++) {
            c[i] = word.charAt(i);
        }
        c[index] = newChar;
        word = String.copyValueOf(c);
        return word;

    }
    ////// Implement QWERTY mistype technique //////

    public ArrayList<String> QWERTYMisType(String URL, String topLevel) {
        /*
         * Generate enumeration using QWERTY technique
         */
        String save = URL;
        int i = 0;
        while (i < save.length()) {
            char c = save.charAt(i);
            for (int j = 0; j < QWERTY1.length; j++) {

                if (c == QWERTY1[j]) {
                    save = Myreplace(QWERTY2[j], save, i);
                    saveWebSites.add(HTTP+save + "."+topLevel);
                    save = URL;
                }
            }
            save = URL;
            i++;
        }
        save = URL;
        i = 0;
        while (i < save.length()) {
            char c = save.charAt(i);
            for (int j = 0; j < QWERTY2.length; j++) {

                if (c == QWERTY2[j]) {
                    save = Myreplace(QWERTY1[j], save, i);
                    saveWebSites.add(HTTP+save + "."+topLevel);
                    save = URL;
                }
            }
            save = URL;
            i++;

        }
        return saveWebSites;
    }
    ////// Add "s" at the end of given url //////

    public ArrayList<String> urlPlural(String URL, String topLevel) {
        /*
         * Add (s) to the end of the given URL
         */
        String save = URL;
        save = save + "s";
        saveWebSites.add(HTTP+save + "."+topLevel);
        return saveWebSites;
    }
    

    public ArrayList<String> WWWWithUrl(String URL, String topLevel) {
        /*
         * Compine WWW with URL 
         */
        String save = URL;
        save = "www" + save;
        saveWebSites.add(HTTP+save + "."+topLevel);
        return saveWebSites;
    }
    ////// Top level domain manipulation //////

    public ArrayList<String> topLevelDomainManipulation(String topDomain, String URL) {
        /*
         * Change the current top level domain by many different top level domains
         */
        String save = URL;
        String temp;
        String topDomainSave[] = topLevelDomain;
        for (int i = 0; i < topLevelDomain.length; i++) {
            temp = topLevelDomain[i];
            if (temp.equals(topDomain)) {
                topDomainSave[i] = "";
                temp = topDomainSave[i];
            }
            if (!temp.equals("")) {
                save = save + "." + topDomainSave[i];
                this.saveWebSites.add(HTTP+save);
                save = URL;
            }
        }
        return saveWebSites;

    }
    public ArrayList<String> addSlash(ArrayList<String> ar){
        /*
         * Add slash to the end of the URL
         */
        ArrayList<String> save = new ArrayList<String>();
        String slash = "/";
        for(int i=0;i<ar.size();i++){
            save.add(ar.get(i)+slash);
        }
        return save;
    }
   /* public void initialReplaceList(char [] list,Vector<Character> charList){
        /*
         * Used to initial common replacment tables
         *
        charList.clear();
        for(int i=0;i<list.length;i++){
            charList.add(list[i]);
        }

    }*/

    /**
     * @return the phishingEnumeration
     */
    public String getPhishingEnumeration() {
        return phishingEnumeration;
    }

    /**
     * @param phishingEnumeration the phishingEnumeration to set
     */
    public void setPhishingEnumeration(String phishingEnumeration) {
        this.phishingEnumeration = phishingEnumeration;
    }

    /**
     * @return the legitimate
     */
    public boolean isLegitimate() {
        return legitimate;
    }

    /**
     * @param legitimate the legitimate to set
     */
    public void setLegitimate(boolean legitimate) {
        this.legitimate = legitimate;
    }

    /**
     * @return the online
     */
    public boolean isOnline() {
        return online;
    }

    /**
     * @param online the online to set
     */
    public void setOnline(boolean online) {
        this.online = online;
    }
}
