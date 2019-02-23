/**
 * CSCI 476 Assignment 3
 * Alexander Harry
 * Keely Weisbeck
 * Nate Tranel
 */

//uses crackstation's human password wordlist dictionary - https://crackstation.net/crackstation-wordlist-password-cracking-dictionary.htm

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        FileInputStream pwds = new FileInputStream("../pw.txt");   // Reads in encrypted passwords file
        Scanner s = new Scanner(pwds);
        ArrayList<String> passwords = new ArrayList<String>();
        while (s.hasNextLine()) {
            passwords.add(s.nextLine());         // Loops through the file lines, add current line to an array list of encrypted passwords
        }
        s.close();

        //uses dictionary attack to encrypt all passwords in dictionary and compare them to the MD5 hashes that exist
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            for(int i = 0; i < passwords.size(); i++) {
                long startTime = System.currentTimeMillis();
                FileInputStream pwdictionary = new FileInputStream("../dictionary.txt");
                Scanner sc = new Scanner(pwdictionary, "UTF-8");
                while (sc.hasNextLine()) {
                    String currentPlaintext = sc.nextLine();
                    md.update(currentPlaintext.getBytes());
                    byte[] digest = md.digest();
                    String currentHash = DatatypeConverter.printHexBinary(digest).toLowerCase();
                    //encrypt currentHash with md instance, compare that hash to the current password hash
                    if (currentHash.equals(passwords.get(i))) {
                        //if they match, then print that password plaintext
                        long stopTime = System.currentTimeMillis();
                        System.out.printf("The password for hash value %s is %s, it takes the program %d sec to recover this password\n", passwords.get(i), currentPlaintext, ((stopTime - startTime)/1000));
                        break;
                    }
                }
                sc.close();
            }
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("Algorithm not found.");
        }
    }
}
