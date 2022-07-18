/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idetect;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author youssef 19
 */
public class SaxParser {

    ArrayList<String> URLArray = new ArrayList<String>();
    ArrayList<String> onlineArray = new ArrayList<String>();
    ArrayList<String> verifiedArray = new ArrayList<String>();
    public String name = "";
    PhishTankDataBase pt = new PhishTankDataBase();

    class SaxParserHelper extends DefaultHandler {
        /*
         * Used to process XML file
         */

        boolean url = false;
        boolean online = false;
        boolean verified = false;
        String s;

        @Override
        public void startElement(String nsURI, String strippedName,
                String tagName, Attributes attributes) throws SAXException {
            /*
             * Used to determine the start tag in XML file
             */
            if (tagName.equalsIgnoreCase("url")) {
                url = true;
            }
            if (tagName.equalsIgnoreCase("online")) {
                online = true;
            }
            if (tagName.equalsIgnoreCase("verified")) {
                verified = true;
            }
        }

        public void characters(char[] ch, int start, int length) {
            if (url) {
                URLArray.add(new String(ch, start, length));
                url = false;
            } else if (online) {
                onlineArray.add(new String(ch, start, length));
                s = s + new String(ch, start, length);
                online = false;
            } else if (verified) {
                verifiedArray.add(new String(ch, start, length));
                verified = false;
            }
        }
    }

    public void list() throws Exception {
        //XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.crimson.parser.XMLReaderImpl");
        //Refer to the comments below for the explanation for commenting above line and putting a new one below.
        /*
         * Used to start SAX Parser
         */
        XMLReader parser = XMLReaderFactory.createXMLReader();
        parser.setContentHandler(new SaxParserHelper());
        name = pt.recreateFileName();
        parser.parse(name);
        

    }

    public  boolean verifyPhishInPhishTank(String phishing) {
        /*
         * Determine if the given URL found in the XML file
         */
        String save = phishing;
        if (this.URLArray.equals(save)) {
            return true;
        }
        return false;
    }
}
