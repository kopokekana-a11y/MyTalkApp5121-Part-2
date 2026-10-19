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
    
}
