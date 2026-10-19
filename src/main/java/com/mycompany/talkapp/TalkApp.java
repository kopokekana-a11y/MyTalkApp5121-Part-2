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
                    System.out.println("3) Quit");
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

                                System.out.println(
                                        "Message successfully sent.");
                                System.out.println(
                                        newMessage.printMessages());

                            } else if (action.equals("2")) {

                                System.out.println(
                                        "Message discarded.");

                            } else if (action.equals("3")) {

                                newMessage.createMessageHash();

                                System.out.println(
                                        newMessage.storeMessage());

                            } else {

                                System.out.println(
                                        "Invalid option. Message not processed.");
                            }
                        }

                        System.out.println(
                                "\nTotal messages sent: "
                                + totalMessagesSent);

                    } else if (option.equals("2")) {

                        System.out.println("Coming Soon");

                    } else if (option.equals("3")) {

                        running = false;

                        System.out.println(
                                "Thank you for using QuickChat!");

                    } else {

                        System.out.println(
                                "Invalid option. Please select 1, 2, or 3.");
                    }
                }
            }
        }

        input.close();
    }
}
