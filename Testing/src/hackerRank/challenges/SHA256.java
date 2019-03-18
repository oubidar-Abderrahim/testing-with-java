package hackerRank.challenges;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SHA256 {

    public static String getMd5(String input) 
    { 
        try { 
  
            // Static getInstance method is called with hashing "SHA-256" 
            MessageDigest md = MessageDigest.getInstance("SHA-256"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            // the hash code is a 64-digit hexadecimal number.
            while (hashtext.length() < 64) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String wordToHash = scan.nextLine();
        scan.close();
        System.out.println(getMd5(wordToHash));
    }
}
