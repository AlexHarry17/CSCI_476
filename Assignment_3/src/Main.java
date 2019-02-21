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
        BufferedReader input = new BufferedReader(new FileReader("/home/brick/Desktop/CSCI_476/Assignment_3/pw.txt"));   // Reads in encrypted passwords file
        String current = input.readLine();
        ArrayList<String> passwords = new ArrayList<String>();
        while(current != null) {
            passwords.add(current);         // Loops through the file lines, add current line to an array list of encrypted passwords
        }

        //uses dictionary attack to encrypt all passwords in dictionary and compare them to the MD5 hashes that exist
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            for(int i = 0; i < passwords.size(); i++) {
                BufferedReader pwdictionary = new BufferedReader(new FileReader("/home/brick/Desktop/CSCI_476/Assignment_3/dictionary.txt"));
                String currentPlaintext = pwdictionary.readLine();
                String currentHash = "Encrypted version of plaintext --placeholder";
                //encrypt currentHash with md instance, compare that hash to the current password hash
                if (currentHash != null && currentHash.equals(current)) {
                    //if they match, then return that password plaintext
                }
            }
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("Algorithm not found.");
        }
    }
}
