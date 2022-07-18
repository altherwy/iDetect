/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idetect;

import com.sun.net.httpserver.HttpContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author youssef 19
 */
public class Connection {

    public java.util.Map<String, java.util.List<String>> responseHeader;
    public java.net.URL responceURL;
    public java.net.URL responceAnyURL;
    public int responceCode;
    public String MIMEtype;
    public String charset;
    public Object content;
    public Boolean webSiteState;
    public final String GOOGLE_QUERY = "http://www.google.com/search?source=ig&hl=en&rlz=1G1GGLQ_ENXX247&q=";

    public Connection() {
        responseHeader = null;
        responceURL = null;
        responceCode = -1;
        MIMEtype = "";
        charset = null;
        content = null;
    }

    public boolean getSourceCode(String URL) {
        /*
         * Get the source code of a webpage  specially Google search resuls page
         */
        /////////// open httpconection ///////////////////////
        try {
            URL url = new URL(URL);
            URLConnection con = url.openConnection();
            HttpURLConnection httpcon = (HttpURLConnection) con;
            ///////////// end open httpconection ////////////////
            ///////// set up a request ////////////////////////
            httpcon.setConnectTimeout(15000);
            httpcon.setReadTimeout(15000);
            httpcon.setInstanceFollowRedirects(true);
            httpcon.setRequestProperty("User-agent", "spider");
            ///////// end set up a request ////////////////////////
            ///////// send the request ////////////////////////
            httpcon.connect();
            ///////// get the response ////////////////////////
            responseHeader = httpcon.getHeaderFields();
            responceCode = httpcon.getResponseCode();
            if (responceCode == HttpURLConnection.HTTP_OK) {
                webSiteState = Boolean.TRUE;
            } else {
                webSiteState = Boolean.FALSE;
            }
            responceURL = httpcon.getURL();
            final int length = httpcon.getContentLength();
            final String type = httpcon.getContentType();
            if (type != null) {
                final String[] parts = type.split(";");
                MIMEtype = parts[0].trim();
                for (int i = 1; i < parts.length && charset == null; i++) {
                    final String t = parts[i].trim();
                    final int index = t.toLowerCase().indexOf("charset=");
                    if (index != -1) {
                        charset = t.substring(index + 8);
                    }
                }
            }

            //////////////////// end get the response //////////
            final java.io.InputStream stream = httpcon.getErrorStream();
            if (stream != null) {
                content = readStream(length, stream);
            } else if ((content = httpcon.getContent()) != null
                    && content instanceof java.io.InputStream) {
                content = readStream(length, (java.io.InputStream) content);
            }
            httpcon.disconnect();
        } catch (Exception e) {
            webSiteState = Boolean.FALSE;
        }
        return webSiteState;
    }

    public String getAnySourceCode(String URL)  {
        /*
         * Get the source code of a webpage
         */
        try {
            URL url = null;
            StringBuilder sb = new StringBuilder();
            url = new URL(URL);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            in.close();
            URLConnection con = url.openConnection();
            HttpURLConnection httpcon = (HttpURLConnection) con;
            httpcon.setConnectTimeout(20000);
            httpcon.setReadTimeout(20000);
            httpcon.setInstanceFollowRedirects(true);
            httpcon.setRequestProperty("User-agent", "spider");
            httpcon.connect();
            responseHeader = httpcon.getHeaderFields();
            responceCode = httpcon.getResponseCode();
            responceAnyURL = httpcon.getURL();

            //System.out.println(responceAnyURL);
            return sb.toString();
        } catch (Exception ex) {
            return "Offline";
        }
    }

    private Object readStream(int length, InputStream inputStream) throws IOException {
        /*
         * this method used by getSourceCode to read the source code
         */

        final int buflen = Math.max(1024, Math.max(length, inputStream.available()));
        byte[] buf = new byte[buflen];
        byte[] bytes = null;

        for (int nRead = inputStream.read(buf); nRead != -1;
                nRead = inputStream.read(buf)) {
            if (bytes == null) {
                bytes = buf;
                buf = new byte[buflen];
                continue;
            }
            final byte[] newBytes = new byte[bytes.length + nRead];
            System.arraycopy(bytes, 0, newBytes, 0, bytes.length);
            System.arraycopy(buf, 0, newBytes, bytes.length, nRead);
            bytes = newBytes;
        }

        if (charset == null) {
            return bytes;
        }
        try {
            return new String(bytes, charset);
        } catch (java.io.UnsupportedEncodingException e) {
        }
        return bytes;
    }

    public ArrayList<Boolean> onlineOrNot(ArrayList<String> enumeration) throws java.net.MalformedURLException, java.io.IOException {
        /*
         * Determine if specific website online or not
         */
        ArrayList<Boolean> webSiteStatues1 = new ArrayList<Boolean>();
        int i = 0;
        int y;
        String tempUrl;
        while (i < enumeration.size()) {
            try {
                tempUrl = enumeration.get(i);

                URL url = new URL(tempUrl);
                URLConnection con = url.openConnection();
                HttpURLConnection httpcon = (HttpURLConnection) con;
                ///////////// end open httpconection ////////////////
                ///////// set up a request ////////////////////////
                httpcon.setConnectTimeout(15000);
                httpcon.setReadTimeout(15000);
                httpcon.setInstanceFollowRedirects(true);
                httpcon.setRequestProperty("User-agent", "spider");
                ///////// end set up a request ////////////////////////
                ///////// send the request ////////////////////////
                httpcon.connect();
                y = httpcon.getResponseCode();
                if (y == HttpURLConnection.HTTP_OK) {
                    webSiteStatues1.add(true);
                } else {
                    webSiteStatues1.add(false);
                }
                httpcon.disconnect();


                i++;
            } catch (Exception e) {
                webSiteStatues1.add(false);
            }
        }
        return webSiteStatues1;
    }

    public boolean oldOnlineOrNot(String webSiteName) throws java.net.MalformedURLException, java.io.IOException {
        /*
         * Another method to determine if specific website online or not
         */

        URL url = new URL(webSiteName);
        URLConnection con = url.openConnection();
        HttpURLConnection httpcon = (HttpURLConnection) con;
        int y;
        ///////////// end open httpconection ////////////////
        ///////// set up a request ////////////////////////
        httpcon.setConnectTimeout(15000);
        httpcon.setReadTimeout(15000);
        httpcon.setInstanceFollowRedirects(true);
        httpcon.setRequestProperty("User-agent", "spider");
        ///////// end set up a request ////////////////////////
        ///////// send the request ////////////////////////
        httpcon.connect();
        y = httpcon.getResponseCode();
        httpcon.disconnect();
        if (HttpURLConnection.HTTP_OK == y) {

            return true;

        } else {

            return false;
        }
    }
}


