/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.talkapp;


import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


/**
 *
 * @author kopok
 */
public class MessageManager {

    // Arrays for storing messages
    private Message[] sentMessages;
    private Message[] disregardedMessages;
    private Message[] storedMessages;

    // Arrays for storing message information
    private String[] messageHashes;
    private String[] messageIDs;

    // Counters for tracking the number of messages
    private int sentCount;
    private int disregardedCount;
    private int storedCount;
    private int hashCount;
    private int idCount;

    // Constructor
    public MessageManager(int capacity) {

        sentMessages = new Message[capacity];
        disregardedMessages = new Message[capacity];
        storedMessages = new Message[capacity];

        messageHashes = new String[capacity];
        messageIDs = new String[capacity];

        sentCount = 0;
        disregardedCount = 0;
        storedCount = 0;
        hashCount = 0;
        idCount = 0;
    }
    
    
    // Adds a message to the sent messages array
    public boolean addSentMessage(Message message) {

        if (message == null) {
            return false;
        }
        
        ensureCapacity();

        addMessageDetails(message);
        sentMessages[sentCount] = message;
        sentCount++;

        return true;
    }

    // Adds a message to the disregarded messages array
    public boolean addDisregardedMessage(Message message) {

        if (message == null) {
            return false;
        }
        
        ensureCapacity();

        addMessageDetails(message);
        disregardedMessages[disregardedCount] = message;
        disregardedCount++;

        return true;
    }

    // Adds a message to the stored messages array
    public boolean addStoredMessage(Message message) {

        if (message == null) {
            return false;
        }
        
        ensureCapacity();

        addMessageDetails(message);
        storedMessages[storedCount] = message;
        storedCount++;

        return true;
    }

    // Saves the message ID and hash in their respective arrays
    private void addMessageDetails(Message message) {

        String hash = message.getMessageHash();

        if (hash == null || hash.isEmpty()) {
            hash = message.createMessageHash();
        }

        messageHashes[hashCount] = hash;
        messageIDs[idCount] = message.getMessageID();

        hashCount++;
        idCount++;
    }
    
    
    // Populates the arrays with the official assignment test data
    public void populateTestData() {

        // Test Message 1 - Sent
        Message message1 = new Message(
                "Test Message 1",
                1,
                "+27834557896",
                "Did you get the cake?"
        );
        message1.createMessageHash();
        addSentMessage(message1);

        // Test Message 2 - Stored
        Message message2 = new Message(
                "Test Message 2",
                2,
                "+27838884567",
                "Where are you? You are late! I have asked you to be on time."
        );
        message2.createMessageHash();
        addStoredMessage(message2);

        // Test Message 3 - Disregarded
        Message message3 = new Message(
                "Test Message 3",
                3,
                "+27834484567",
                "Yohhoooo, I am at your gate."
        );
        message3.createMessageHash();
        addDisregardedMessage(message3);

        // Test Message 4 - Sent
        // The assignment lists this number under Developer.
        Message message4 = new Message(
                "Test Message 4",
                4,
                "0838884567",
                "It is dinner time !"
        );
        message4.createMessageHash();
        addSentMessage(message4);

        // Test Message 5 - Stored
        Message message5 = new Message(
                "Test Message 5",
                5,
                "+27838884567",
                "Ok, I am leaving without you."
        );
        message5.createMessageHash();
        addStoredMessage(message5);
    }
    
    
    // Displays all sent messages
    public void displaySentMessages() {

        System.out.println("\n--- SENT MESSAGES ---");

        if (sentCount == 0) {
            System.out.println("No sent messages available.");
            return;
        }

        for (int i = 0; i < sentCount; i++) {
            System.out.println(sentMessages[i].printMessages());
            System.out.println("----------------------");
        }
    }

    // Displays all stored messages
public void displayStoredMessages() {

    System.out.println("\n--- STORED MESSAGES ---");

    if (storedCount == 0) {
        System.out.println("No stored messages available.");
        return;
    }

    for (int i = 0; i < storedCount; i++) {

        Message message = storedMessages[i];

        System.out.println("Message ID: " + message.getMessageID());
        System.out.println("Message Hash: " + message.getMessageHash());

        String sender = message.getSender();

        if (sender == null || sender.isEmpty()) {
            sender = "Sender information unavailable";
        }

        System.out.println("Sender: " + sender);
        System.out.println("Recipient: " + message.getRecipient());
        System.out.println("Message: " + message.getMessage());

        System.out.println("----------------------");
    }
}

    // Displays all disregarded messages
    public void displayDisregardedMessages() {

        System.out.println("\n--- DISREGARDED MESSAGES ---");

        if (disregardedCount == 0) {
            System.out.println("No disregarded messages available.");
            return;
        }

        for (int i = 0; i < disregardedCount; i++) {
            System.out.println(disregardedMessages[i].printMessages());
            System.out.println("----------------------");
        }
    }
    
    
    // Finds and returns the longest stored message
    public String getLongestStoredMessage() {

        if (storedCount == 0) {
            return "No stored messages available.";
        }

        Message longestMessage = storedMessages[0];

        for (int i = 1; i < storedCount; i++) {

            if (storedMessages[i].getMessage().length()
                    > longestMessage.getMessage().length()) {

                longestMessage = storedMessages[i];
            }
        }

        return longestMessage.getMessage();
    }

    // Displays the longest stored message
    public void displayLongestStoredMessage() {

        System.out.println("\n--- LONGEST STORED MESSAGE ---");

        System.out.println(getLongestStoredMessage());
    }
    
    
    // Searches for a stored message using its message ID
    public String searchStoredMessageByID(String searchID) {

        for (int i = 0; i < storedCount; i++) {

            if (storedMessages[i].getMessageID().equals(searchID)) {

                return "Message ID: "
                        + storedMessages[i].getMessageID()
                        + "\nRecipient: "
                        + storedMessages[i].getRecipient()
                        + "\nMessage: "
                        + storedMessages[i].getMessage();
            }
        }

        return "Message ID not found.";
    }
    
    
    // Searches sent and stored messages by recipient number
    public String searchMessagesByRecipient(String recipient) {

        String results = "";
        boolean found = false;

        // Search through sent messages
        for (int i = 0; i < sentCount; i++) {

            if (sentMessages[i].getRecipient().equals(recipient)) {

                results += sentMessages[i].printMessages() + "\n";
                found = true;
            }
        }

        // Search through stored messages
        for (int i = 0; i < storedCount; i++) {

            if (storedMessages[i].getRecipient().equals(recipient)) {

                results += storedMessages[i].printMessages() + "\n";
                found = true;
            }
        }

        if (!found) {
            return "No messages found for recipient: " + recipient;
        }

        return results;
    }
    
        // Deletes a stored message using its message hash
    public String deleteStoredMessage(String searchHash) {

        for (int i = 0; i < storedCount; i++) {

            if (storedMessages[i].getMessageHash().equals(searchHash)) {

                String deletedMessage =
                        storedMessages[i].getMessage();

                String deletedID =
                        storedMessages[i].getMessageID();

                // Remove the message from the stored array
                for (int j = i; j < storedCount - 1; j++) {
                    storedMessages[j] = storedMessages[j + 1];
                }

                storedMessages[storedCount - 1] = null;
                storedCount--;

                // Remove the matching hash and ID
                for (int j = 0; j < hashCount; j++) {

                    if (messageHashes[j].equals(searchHash)) {

                        for (int k = j; k < hashCount - 1; k++) {
                            messageHashes[k] = messageHashes[k + 1];
                            messageIDs[k] = messageIDs[k + 1];
                        }

                        messageHashes[hashCount - 1] = null;
                        messageIDs[idCount - 1] = null;

                        hashCount--;
                        idCount--;
                        break;
                    }
                }

                return "Message \"" + deletedMessage
                        + "\" successfully deleted.";
            }
        }

        return "Message hash not found.";
    }
    
    
    // Generates a report of all sent messages
    public String displayMessageReport() {

        if (sentCount == 0) {
            return "No sent messages available.";
        }

        String report = "--- SENT MESSAGE REPORT ---\n";

        for (int i = 0; i < sentCount; i++) {

            report += "\nMessage Hash: "
                    + sentMessages[i].getMessageHash();

            report += "\nRecipient: "
                    + sentMessages[i].getRecipient();

            report += "\nMessage: "
                    + sentMessages[i].getMessage();

            report += "\n--------------------------\n";
        }

        return report;
    }
    
    
    // Reads stored messages from the JSON file
    public int loadStoredMessagesFromJSON() {

        Gson gson = new Gson();
        int loadedCount = 0;

        try (BufferedReader reader =
                new BufferedReader(new FileReader("messages.json"))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                Message message = gson.fromJson(line, Message.class);

                if (message != null) {

                    if (addStoredMessage(message)) {
                        loadedCount++;
                    } else {
                        System.out.println(
                                "Array is full. Some messages could not be loaded."
                        );
                        break;
                    }
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Error reading messages.json: " + e.getMessage()
            );
        }

        return loadedCount;
    }
    
    private void ensureCapacity() {

    if (hashCount >= messageHashes.length) {

        int newCapacity = messageHashes.length == 0
                ? 1 : messageHashes.length * 2;

        sentMessages = Arrays.copyOf(sentMessages, newCapacity);
        disregardedMessages = Arrays.copyOf(
                disregardedMessages, newCapacity);
        storedMessages = Arrays.copyOf(
                storedMessages, newCapacity);

        messageHashes = Arrays.copyOf(
                messageHashes, newCapacity);
        messageIDs = Arrays.copyOf(
                messageIDs, newCapacity);
    }
}
}