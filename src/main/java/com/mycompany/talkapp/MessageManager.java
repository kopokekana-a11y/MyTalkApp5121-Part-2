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
}