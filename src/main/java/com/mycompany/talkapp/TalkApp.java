/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.talkapp;

import java.util.Scanner;

/**
 *
 * @author kopok
 */
public class TalkApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        
        MessageManager messageManager = new MessageManager(5);

        int loadedMessages = messageManager.loadStoredMessagesFromJSON();

        System.out.println(loadedMessages + " stored messages loaded.");
        
        

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter South African cell number: ");
        String cellPhoneNumber = input.nextLine();

        System.out.print("Enter first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();

        TalkAppExtClass user = new TalkAppExtClass(
                username, password, cellPhoneNumber);

        String registrationMessage = user.registerUser();
        System.out.println(registrationMessage);

        if (registrationMessage.equals(
                "User has been registered successfully.")) {

            System.out.print("Enter username to login: ");
            String enteredUsername = input.nextLine();

            System.out.print("Enter password to login: ");
            String enteredPassword = input.nextLine();

            boolean loginStatus = user.loginUser(
                    enteredUsername, enteredPassword);

            System.out.println(user.returnLoginStatus(
                    loginStatus, firstName, lastName));

            if (loginStatus) {

                System.out.println("\nWelcome to TalkApp.");

                boolean running = true;
                int totalMessagesSent = 0;

                while (running) {

                    System.out.println("\n1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Stored Messages");
                    System.out.print("4) Quit");
                    
                    System.out.print("Choose an option: ");

                    String option = input.nextLine();

                    if (option.equals("1")) {

                        System.out.print(
                                "How many messages would you like to send? ");

                        int numberOfMessages;

                        try {
                            numberOfMessages = Integer.parseInt(
                                    input.nextLine());

                            if (numberOfMessages <= 0) {
                                System.out.println(
                                        "Please enter a number greater than zero.");
                                continue;
                            }

                        } catch (NumberFormatException e) {
                            System.out.println(
                                    "Please enter a valid number.");
                            continue;
                        }

                        for (int i = 1; i <= numberOfMessages; i++) {

                            System.out.println(
                                    "\nMessage " + i + " of "
                                    + numberOfMessages);

                            String messageID =
                                    Message.generateMessageID();

                            System.out.println(
                                    "Message ID: " + messageID);

                            System.out.print(
                                    "Enter recipient number with international code: ");
                            String recipient = input.nextLine();

                            System.out.print("Enter your message: ");
                            String messageText = input.nextLine();

                            Message newMessage = new Message(
                                    messageID,
                                    totalMessagesSent + 1,
                                    recipient,
                                    messageText);

                            if (!newMessage.checkMessageID()) {
                                System.out.println(
                                        "Invalid message ID.");
                                continue;
                            }

                            if (!newMessage.checkRecipientCell()) {
                                System.out.println(
                                        "Invalid recipient number. "
                                        + "Include the international code.");
                                continue;
                            }

                            String lengthResult =
                                    newMessage.checkMessageLength();

                            if (!lengthResult.equals(
                                    "Message ready to send.")) {
                                System.out.println(lengthResult);
                                continue;
                            }

                            System.out.println("\nChoose an action:");
                            System.out.println("1) Send Message");
                            System.out.println("2) Discard Message");
                            System.out.println("3) Store Message");
                            System.out.print("Your choice: ");

                            String action = input.nextLine();

                            if (action.equals("1")) {

                                totalMessagesSent++;
                                newMessage.createMessageHash();
                                
                                messageManager.addSentMessage(newMessage);

                                System.out.println(
                                        "Message successfully sent.");
                                System.out.println(
                                        newMessage.printMessages());

                            } else if (action.equals("2")) {
                                
                                messageManager.addDisregardedMessage(newMessage);

                                System.out.println(
                                        "Message discarded.");

                            } else if (action.equals("3")) {

                                newMessage.createMessageHash();

                                String storageResult = newMessage.storeMessage();

                                System.out.println(storageResult);

                                if (storageResult.equals("Message successfully stored.")) {
                                                messageManager.addStoredMessage(newMessage);
                                }
                            } else {

                                System.out.println(
                                        "Invalid option. Message not processed.");
                            }
                        }

                        System.out.println(
                                "\nTotal messages sent: "
                                + totalMessagesSent);

                    } else if (option.equals("2")) {

                        System.out.println("\\n--- SENT MESSAGES ---");
                        
                         messageManager.displaySentMessages();

                    }  else if (option.equals("3")) {

                        boolean storedMenuRunning = true;

                        while (storedMenuRunning) {

                    System.out.println("\n--- STORED MESSAGES MENU ---");
                    System.out.println("1) Display stored messages");
                    System.out.println("2) Display longest stored message");
                    System.out.println("3) Search stored message by ID");
                    System.out.println("4) Search messages by recipient");
                    System.out.println("5) Delete stored message by hash");
                    System.out.println("6) Display message report");
                    System.out.println("7) Return to main menu");

                    System.out.print("Choose an option: ");

                    String storedOption = input.nextLine();

                    if (storedOption.equals("1")) {

                        messageManager.displayStoredMessages();

                    } else if (storedOption.equals("2")) {

                        messageManager.displayLongestStoredMessage();

                    } else if (storedOption.equals("3")) {

                        System.out.print("Enter message ID: ");
                        String searchID = input.nextLine();

                        System.out.println(
                        messageManager.searchStoredMessageByID(searchID));

                    } else if (storedOption.equals("4")) {

                    System.out.print("Enter recipient number: ");
                        String recipient = input.nextLine();

                    System.out.println(
                        messageManager.searchMessagesByRecipient(recipient));

                    } else if (storedOption.equals("5")) {

                    System.out.print("Enter message hash: ");
                    String searchHash = input.nextLine();

                    System.out.println(
                        messageManager.deleteStoredMessage(searchHash));

                    } else if (storedOption.equals("6")) {

                    System.out.println(
                        messageManager.displayMessageReport());

                    } else if (storedOption.equals("7")) {

                    storedMenuRunning = false;

                    } else {

                    System.out.println("Invalid option. Please try again.");
        }
    }
}
                }
            }
        }

        input.close();
    }
}
