/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.talkapp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;


/**
 *
 * @author kopok
 */
public class Message {
     private String messageID;
    private int numMessagesSent;
    private String recipient;
    private String message;
    private String messageHash;

    // Constructor
    public Message(String messageID, int numMessagesSent,
                   String recipient, String message) {
        this.messageID = messageID;
        this.numMessagesSent = numMessagesSent;
        this.recipient = recipient;
        this.message = message;
    }
    
    // Check that the message ID is no more than 10 characters
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // Check that the recipient's cell number is correctly formatted
    public boolean checkRecipientCell() {
        if (recipient == null || !recipient.startsWith("+")) {
            return false;
        }

        String numberWithoutCode = recipient.substring(1);

        return numberWithoutCode.matches("\\d+")
                && numberWithoutCode.length() <= 12;
    }
    
    // Create a hash for the message
    public String createMessageHash() {

        String firstTwoDigits = messageID.substring(0, 2);

        String[] words = message.trim().split("\\s+");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        messageHash = (firstTwoDigits + ":"
                + numMessagesSent + ":"
                + firstWord + lastWord).toUpperCase();

        return messageHash;
    }
    
    // Allow the user to send, discard, or store a message
    public String SentMessage(int choice) {

        if (choice == 1) {
            return "Message successfully sent.";
        } 
        else if (choice == 2) {
            return "Press 0 to delete the message.";
        } 
        else if (choice == 3) {
            return "Message successfully stored.";
        } 
        else {
            return "Invalid option. Please select 1, 2, or 3.";
        }
    }
    
     // Return the details of a message
    public String printMessages() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + message;
    }
    
     // Return the total number of messages sent
    public int returnTotalMessages() {
        return numMessagesSent;
    }
    
    // Escape special characters for JSON storage
    private String escapeJson(String value) {
        return value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
    
    // Store the message in a JSON file
    public String storeMessage() {
        try (FileWriter writer = new FileWriter("messages.json", true)) {

            writer.write("{"
                    + "\"messageID\":\"" + escapeJson(messageID) + "\","
                    + "\"messageHash\":\"" + escapeJson(
                        messageHash == null ? "" : messageHash) + "\","
                    + "\"recipient\":\"" + escapeJson(recipient) + "\","
                    + "\"message\":\"" + escapeJson(message) + "\""
                    + "}\n");

            return "Message successfully stored.";

        } catch (IOException e) {
            return "Error storing message: " + e.getMessage();
        }
    }
    
    // Check the message length
    public String checkMessageLength() {
        if (message == null || message.trim().isEmpty()) {
            return "Please enter a message.";
        }

        if (message.length() > 250) {
            return "Message exceeds 250 characters by "
                    + (message.length() - 250)
                    + ", please reduce size.";
        }

        return "Message ready to send.";
    }
    
     // Generate a random ten-digit message ID
    public static String generateMessageID() {
        return String.valueOf(
            ThreadLocalRandom.current()
                .nextLong(1_000_000_000L, 10_000_000_000L)
        );
    }
}
