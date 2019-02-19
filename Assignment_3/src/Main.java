/**
 * CSCI 476 Assignment 3
 * Alexander Harry
 * Keely Weisbeck
 * Nate Tranel
 */

//uses crackstation's human password wordlist dictionary - https://crackstation.net/crackstation-wordlist-password-cracking-dictionary.htm

import java.io.*;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader input = new BufferedReader(new FileReader("../pw.txt"));   // Reads in encrypted passwords file.
        String current = null;
        ArrayList<String> passwords = null;
        while((current = input.readLine()) != null) {
            passwords.add(current);         // Loops through the file lines, add current line to an array list of encrypted passwords
        }
        //uses dictionary attack to encrypt all passwords in dictionary and compare them to the MD5 hashes that exist
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("Algorithm not found.");
        }
    }
}
