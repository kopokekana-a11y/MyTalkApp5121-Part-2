/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.talkapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kopok
 */
public class MessageTest {
    
    public MessageTest() {
    }

    @Test
    public void testGenerateMessageID() {
        String messageID = Message.generateMessageID();

        assertNotNull(messageID);
        assertTrue(messageID.matches("\\d{10}"));
    }
    
     @Test
    public void testCheckMessageID() {
        Message validMessage = new Message(
                "1234567890", 1, "+2781234567", "Hello world");

        assertTrue(validMessage.checkMessageID());
    }
    
     @Test
    public void testCheckRecipientCell() {
        Message validMessage = new Message(
                "1234567890", 1, "+2781234567", "Hello world");

        Message invalidMessage = new Message(
                "1234567890", 1, "2781234567", "Hello world");

        assertTrue(validMessage.checkRecipientCell());
        assertFalse(invalidMessage.checkRecipientCell());
    }
    
    @Test
    public void testCheckMessageLength() {
        Message validMessage = new Message(
                "1234567890", 1, "+2781234567", "Hello world");

        Message invalidMessage = new Message(
                "1234567890", 1, "+2781234567", "A".repeat(251));

        assertEquals(
                "Message ready to send.",
                validMessage.checkMessageLength());

        assertTrue(
                invalidMessage.checkMessageLength()
                        .contains("exceeds 250 characters"));
    }
    
}
