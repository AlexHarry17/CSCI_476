/*
Alexander Harry
Keely Weisbeck
Nate Tranel
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("/home/alex/Documents/Montana State/2019/Spring/CSCI_476/Assignment_2/memorydump.dmp"));   // Reads in .dmp file.
        String st = "";
        String current;
        while((current = input.readLine()) != null) {
            st += current;         // Loops through the file lines.  Appends each line into one string "st".
        }
        StringTokenizer mt = new StringTokenizer(st, "%?;");
        List<String> tracks = new ArrayList<>();
        ArrayList<String> unencryptedInfo = new ArrayList<>();

        while (mt.hasMoreTokens()) {
            String track = mt.nextToken();
            if (track.startsWith("B") && track.contains("^") && track.contains("/")) {
                //System.out.println("MT " + track);
                tracks.add(track);
            }
        }
        for (String t : tracks)
            unencryptedInfo = getCardInfo(t, unencryptedInfo); // Valid unencrypted info is added to the list.
        if (!unencryptedInfo.isEmpty()) {
            printCardInfo(unencryptedInfo); //Prints if unencrypted info exists.
        } else {
            System.out.println("All info is encrypted.");
        }
    }

    private static ArrayList getCardInfo(String track, ArrayList<String> unencryptedInfo) { // Method to get card info.

        List<String> cardInfo = new ArrayList<>();
        StringTokenizer info = new StringTokenizer(track, "^");
        while (info.hasMoreTokens()) {
            cardInfo.add(info.nextToken());
        }
        if (cardInfo.size() == 3) {
            String[] name_temp = cardInfo.get(1).split("/");    // Splits the string where the name is broken up by a "/"
            String name = name_temp[0] + name_temp[1];  // Concatenates the name string.
            String cardNum = cardInfo.get(0).substring(1);  // Gets the card info from the string.
            String cardNum_spaced = "";
            for (int i = 0; i < cardNum.length(); i += 4) { // Adds spaces to the credit card number to help with readability.
                cardNum_spaced += cardNum.substring(i, i + 4) + " ";
            }
            String expirationDate = cardInfo.get(2).substring(2, 4) + "/20" + cardInfo.get(2).substring(0, 2); // Gets expiration date from string.
            String cvc = cardInfo.get(2).substring(4, 7);   //Gets cvc info from string.
            unencryptedInfo.add("Cardholder’s Name: " + name + "\n" + "Card Number: " + cardNum_spaced + "\n" + "Expiration Date: " + expirationDate + "\nCVC Number: " + cvc + "\n");

        }
        return unencryptedInfo;
    }

    private static void printCardInfo(ArrayList<String> unencryptedInfo) {  // Method to print card info.
        Map<Integer, String> number_endings = Map.of( //Dictionary to make our print statement grammatically correct for values less than 8.
                1, "st",
                2, "nd",
                3, "rd",
                4, "th",
                5, "th",
                6, "th",
                7, "th",
                8, "th"
        );
        System.out.println("There are " + unencryptedInfo.size() + " track I record's in the memory data");
        for (int i = 0; i < unencryptedInfo.size(); i++) {
            System.out.println("<Information of the " + (i + 1) + number_endings.get(i + 1) + " record>");
            System.out.println(unencryptedInfo.get(i));

        }
    }

}
