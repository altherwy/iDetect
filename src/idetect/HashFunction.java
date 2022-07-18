package idetect;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class HashFunction {

    static final String HEXES = "0123456789ABCDEF";

      public static String toHexString( byte [] input )
     {
         /*
          * function that receive an array of bytes and convert its content to String
          * in hexadecimal and return this String.
          */
         if ( input == null )
         {
             return null;
         }
         final StringBuilder hex = new StringBuilder( 2 * input.length );
         for ( final byte b : input )
         {
             hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
         }

         return hex.toString();
    }

     public static final int changeArrayBytesToInteger(byte[] b)
    {
         /*
          * function that receive an array of bytes and convert its content to int
          * and return this int.
          */
         int result = 0;
        for (int i = 0; i < b.length; i++) {
            result |= b[0] & 0xFF;
            result <<= 8;

        }
         return result;
    }


     // hashtype value:MD5,SHA-1,SHA-256,SHA-512....
     public static java.security.MessageDigest getMessageDigest(String hashType)
             throws NoSuchAlgorithmException
     {
         java.security.MessageDigest messageDigest = java.security.MessageDigest.getInstance(hashType);
         messageDigest.reset();


         return messageDigest;
     }

     // encooding value: utf8,unicodelitlleendian,......iso 676
     public static int computeHash(String string,String encooding,String hashType)
             throws UnsupportedEncodingException, NoSuchAlgorithmException
     {
         /*
          * function that receive a String that we want to compute its hash value, String contain the
          * type of encoding and String that repersent the hash type. this function will compute the hash
          * value and return the result as an int.
          */
                 byte[] stringBytes = string.getBytes(encooding);
                 byte[] hashValue = getMessageDigest(hashType).digest(stringBytes);
                 String hex = toHexString(hashValue);
                 byte[] hexInBytes = hex.getBytes();
                 int hashValueInteger = changeArrayBytesToInteger(hexInBytes);
                 return hashValueInteger;
     }

}
