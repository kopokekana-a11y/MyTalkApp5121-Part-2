/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.talkapp;

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
}
