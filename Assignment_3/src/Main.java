/**
 * CSCI 476 Assignment 3
 * Alexander Harry
 * Keely Weisbeck
 * Nate Tranel
 *
 * Program to read in a list of hashed MD5 passwords and a dictionary and crack those hashed passwords. It reads in a password dictionary
 * as a text file, hashes each line, and compares it to the hashes in the password file. This uses a simple method of cracking passwords
 * and could be improved with search optimization in the dictionary file. We had success using the WPA2 word list available on Github
 * at `https://github.com/kennyn510/wpa2-wordlists`, which is simply a collection of commonly used passwords that were leaked in 2016.
 * To use this dictionary, it is necessary to clone the Github repository, unzip the files in each directory, concatenate them, and then
 * sort the resulting file for uniqueness. This can all be done easily within the UNIX bash. This dictionary took about 30 minutes to
 * find all the passwords for this assignment, but the benefit here is that it can find a lot of passwords.
 *
 * Alternatively, we have included file that is much smaller contains the most common 1,000,000 passwords from a list of 10,000,000 leaked
 * passwords and contains those that are present for this assignment. The other dictionary was too large to submit through D2L so we provided
 * the link above. This dictionary took less than 1 minute for all of the passwords to be found, since is was a much smaller file. However,
 * it would not be able to find the same number of passwords that the above dictionary would be able to.
 */



import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        FileInputStream pwds = new FileInputStream("../resources/pw.txt");               // Reads in encrypted passwords file
        Scanner s = new Scanner(pwds);
        ArrayList<String> passwords = new ArrayList<String>();
        while (s.hasNextLine()) {
            passwords.add(s.nextLine());                                    // Loops through the file lines, add current line to an array list of hashed passwords
        }
        s.close();

        // uses dictionary attack to hash all passwords in dictionary with MD5 algorithm and compare them to the MD5 hashes that exist
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            for(int i = 0; i < passwords.size(); i++) {                 // loop through each provided hashed password
                float startTime = (float) System.nanoTime();            // log start time
                boolean found = false;
                FileInputStream pwdictionary = new FileInputStream("../resources/dictionary.txt");
                Scanner sc = new Scanner(pwdictionary, "UTF-8");
                while (sc.hasNextLine()) {                              // loop through each line in dictionary,
                    String currentPlaintext = sc.nextLine();            // record plaintext
                    md.update(currentPlaintext.getBytes());             // hash plaintext
                    byte[] digest = md.digest();
                    String currentHash = DatatypeConverter.printHexBinary(digest).toLowerCase();
                    if (currentHash.equals(passwords.get(i))) {         // if both hashes match, print plaintext and log stop time, break out of loop and jump to next hash to be found
                        float stopTime = (float) System.nanoTime();
                        System.out.printf("The password for hash value %s is %s, it takes the program %.5f sec to recover this password\n", passwords.get(i), currentPlaintext, ((stopTime - startTime)/1000000000));
                        found = true;
                        break;
                    }
                }
                // if the password is not found in the dictionary, alert the user
                if (!found) {
                    System.out.printf("Password %d not found; try using another dictionary.\n", i+1);
                }
                sc.close();
            }
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("Algorithm not found.");
        }
    }
}
