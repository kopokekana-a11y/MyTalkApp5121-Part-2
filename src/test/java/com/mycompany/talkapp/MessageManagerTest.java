/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.talkapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author kopok
 */
public class MessageManagerTest {

    private MessageManager messageManager;

    @BeforeEach
    public void setUp() {
        messageManager = new MessageManager(2);
    }

    // Creates a message for testing
    private Message createTestMessage(
            String id, int number, String recipient, String content) {

        Message message = new Message(
                id, number, recipient, content, "+27831234567");

        message.createMessageHash();

        return message;
    }

    @Test
    public void testAddSentMessage() {

        Message message = createTestMessage(
                "1234567890", 1, "+27834567890", "Hello there");

        assertTrue(messageManager.addSentMessage(message));

        assertTrue(messageManager.displayMessageReport()
                .contains("Hello there"));
    }

    @Test
    public void testAddStoredMessage() {

        Message message = createTestMessage(
                "1234567891", 1, "+27834567890", "Stored message");

        assertTrue(messageManager.addStoredMessage(message));

        assertTrue(messageManager.searchStoredMessageByID(
                "1234567891").contains("Stored message"));
    }

    @Test
    public void testAddDisregardedMessage() {

        Message message = createTestMessage(
                "1234567892", 1, "+27834567890", "Disregarded message");

        assertTrue(messageManager.addDisregardedMessage(message));

        assertEquals("Disregarded message",
                messageManager.getLongestMessage());
    }

    @Test
    public void testAddNullMessage() {

        assertFalse(messageManager.addSentMessage(null));
        assertFalse(messageManager.addStoredMessage(null));
        assertFalse(messageManager.addDisregardedMessage(null));
    }

    @Test
    public void testSearchStoredMessageByID() {

        Message message = createTestMessage(
                "1234567893", 1, "+27834567890", "Find this message");

        messageManager.addStoredMessage(message);

        assertTrue(messageManager.searchStoredMessageByID(
                "1234567893").contains("Find this message"));

        assertEquals("Message ID not found.",
                messageManager.searchStoredMessageByID("9999999999"));
    }

    @Test
    public void testSearchMessagesByRecipient() {

        String recipient = "+27834567890";

        Message sentMessage = createTestMessage(
                "1234567894", 1, recipient, "Sent message");

        Message storedMessage = createTestMessage(
                "1234567895", 2, recipient, "Stored message");

        messageManager.addSentMessage(sentMessage);
        messageManager.addStoredMessage(storedMessage);

        String results =
                messageManager.searchMessagesByRecipient(recipient);

        assertTrue(results.contains("Sent message"));
        assertTrue(results.contains("Stored message"));
    }

    @Test
    public void testDeleteStoredMessage() {

        Message message = createTestMessage(
                "1234567896", 1, "+27834567890", "Delete this message");

        messageManager.addStoredMessage(message);

        String hash = message.getMessageHash();

        assertTrue(messageManager.deleteStoredMessage(hash)
                .contains("successfully deleted"));

        assertEquals("Message ID not found.",
                messageManager.searchStoredMessageByID("1234567896"));
    }

    @Test
    public void testGetLongestStoredMessage() {

        messageManager.addStoredMessage(createTestMessage(
                "1234567897", 1, "+27834567890", "Short"));

        messageManager.addStoredMessage(createTestMessage(
                "1234567898", 2, "+27834567890",
                "This is the longest stored message"));

        assertEquals("This is the longest stored message",
                messageManager.getLongestStoredMessage());
    }

    @Test
    public void testGetLongestMessageAcrossAllCategories() {

        messageManager.addSentMessage(createTestMessage(
                "1234567890", 1, "+27834567890", "Short"));

        messageManager.addStoredMessage(createTestMessage(
                "1234567891", 2, "+27834567890", "Medium message"));

        messageManager.addDisregardedMessage(createTestMessage(
                "1234567892", 3, "+27834567890",
                "This is the longest message of all three categories"));

        assertEquals(
                "This is the longest message of all three categories",
                messageManager.getLongestMessage());
    }

    @Test
    public void testDisplayMessageReport() {

        assertEquals("No sent messages available.",
                messageManager.displayMessageReport());

        messageManager.addSentMessage(createTestMessage(
                "1234567893", 1, "+27834567890", "Report test"));

        String report = messageManager.displayMessageReport();

        assertTrue(report.contains("Report test"));
        assertTrue(report.contains("Message Hash:"));
        assertTrue(report.contains("Recipient:"));
    }

    @Test
    public void testAutomaticArrayExpansion() {

        messageManager.addSentMessage(createTestMessage(
                "1234567894", 1, "+27834567890", "Message one"));

        messageManager.addSentMessage(createTestMessage(
                "1234567895", 2, "+27834567890", "Message two"));

        messageManager.addSentMessage(createTestMessage(
                "1234567896", 3, "+27834567890", "Message three"));

        String report = messageManager.displayMessageReport();

        assertTrue(report.contains("Message one"));
        assertTrue(report.contains("Message two"));
        assertTrue(report.contains("Message three"));
    }

    @Test
    public void testEmptyMessageCollections() {

        assertEquals("No stored messages available.",
                messageManager.getLongestStoredMessage());

        assertEquals("No messages available.",
                messageManager.getLongestMessage());
    }
}