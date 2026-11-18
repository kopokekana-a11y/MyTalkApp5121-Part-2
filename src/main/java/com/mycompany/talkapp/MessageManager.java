/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.talkapp;

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

        if (message == null || sentCount >= sentMessages.length
                || hashCount >= messageHashes.length
                || idCount >= messageIDs.length) {
            return false;
        }

        addMessageDetails(message);
        sentMessages[sentCount] = message;
        sentCount++;

        return true;
    }

    // Adds a message to the disregarded messages array
    public boolean addDisregardedMessage(Message message) {

        if (message == null || disregardedCount >= disregardedMessages.length
                || hashCount >= messageHashes.length
                || idCount >= messageIDs.length) {
            return false;
        }

        addMessageDetails(message);
        disregardedMessages[disregardedCount] = message;
        disregardedCount++;

        return true;
    }

    // Adds a message to the stored messages array
    public boolean addStoredMessage(Message message) {

        if (message == null || storedCount >= storedMessages.length
                || hashCount >= messageHashes.length
                || idCount >= messageIDs.length) {
            return false;
        }

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


}