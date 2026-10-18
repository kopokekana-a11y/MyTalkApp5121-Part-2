/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.talkapp;

/**
 *
 * @author kopok
 */
class TalkAppExtClass {
    
    private String username;
    private String password;
    private String cellPhoneNumber;

    // Parameterized constructor
    public TalkAppExtClass(String username, String password, String cellPhoneNumber) {
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    // Check username
    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    // Check password complexity
    public boolean checkPasswordComplexity() {

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        if (password.length() < 8) {
            return false;
        }

        for (char c : password.toCharArray()) {

            if (Character.isUpperCase(c)) {
                hasCapital = true;

            } else if (Character.isDigit(c)) {
                hasNumber = true;

            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        return hasCapital && hasNumber && hasSpecial;
    }

    // Check South African cell phone number
    public boolean checkCellPhoneNumber() {
        return cellPhoneNumber.startsWith("+27")
                && cellPhoneNumber.length() == 12;
    }

    // Register user
    public String registerUser() {

        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell phone number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        return "User has been registered successfully.";
    }

    // Login user
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return username.equals(enteredUsername)
                && password.equals(enteredPassword);
    }

    // Return login status
    public String returnLoginStatus(boolean loginStatus, String firstName, String lastName) {

        if (loginStatus) {
            return "Welcome " + firstName + " " + lastName
                    + ", it is great to finally meet you again.";
        } else {
            return "Username or password is incorrect, please try again later.";
        }
    }
}

