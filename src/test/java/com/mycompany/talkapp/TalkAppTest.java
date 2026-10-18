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
public class TalkAppTest {
    
    @Test
        public void testCheckUserName() { 
            TalkAppExtClass user =new TalkAppExtClass("kyl_1", "Ch&&set@ke99!", "+27838968976");
           assertTrue(user.checkUserName()); }
    

        @Test
        public void testInvalidUserName() { 
        TalkAppExtClass user = new TalkAppExtClass("kyle !!!!!!!", "Ch&&set@ke99!", "+27838968976"); 
        assertFalse(user.checkUserName()); 
        }
        
        @Test 
        public void testCheckPasswordComplexity() { 
            TalkAppExtClass user = new TalkAppExtClass("kyl_1", "Ch&&set@ke99!", "+27838968976"); 
            assertTrue(user.checkPasswordComplexity()); }
        
        @Test 
        public void testInvalidPassword() { 
            TalkAppExtClass user = new TalkAppExtClass("kyl_1", "password", "+27838968976"); 
            assertFalse(user.checkPasswordComplexity()); }
        
        @Test 
        public void testCheckCellPhoneNumber() { 
            TalkAppExtClass user = new TalkAppExtClass("kyl_1", "Ch&&set@ke99!", "+27838968976"); 
            assertTrue(user.checkCellPhoneNumber()); }
        
        @Test 
        public void testInvalidCellPhoneNumber() { 
            TalkAppExtClass user = new TalkAppExtClass("kyl_1", "Ch&&set@ke99!", "08966553"); 
            assertFalse(user.checkCellPhoneNumber()); }
        
        @Test 
        public void testRegisterUser() { 
            TalkAppExtClass user = new TalkAppExtClass("kyl_1", "Ch&&set@ke99!", "+27838968976"); 
            assertEquals( "User has been registered successfully.", user.registerUser() ); 
        }
        
        @Test 
        public void testRegisterUserInvalidUsername() { 
            TalkAppExtClass user = new TalkAppExtClass("kyle !!!!!!!", "Ch&&set@ke99!", "+27838968976"); 
            assertEquals( "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", user.registerUser() ); 
        }
        
        @Test 
        public void testRegisterUserInvalidPassword() { 
            TalkAppExtClass user = new TalkAppExtClass("kyl_1", "password", "+27838968976"); 
            assertEquals( "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", user.registerUser() ); 
        }
        
        @Test 
        public void testRegisterUserInvalidCellPhone() { 
            TalkAppExtClass user = new TalkAppExtClass("kyl_1", "Ch&&set@ke99!", "08966553"); 
            assertEquals( "Cell phone number is incorrectly formatted or does not contain an international code; please correct the number and try again.", user.registerUser() ); 
        }
        
        @Test 
        public void testLoginUser() { 
            TalkAppExtClass user = new TalkAppExtClass("kyl_1", "Ch&&set@ke99!", "+27838968976"); 
            assertTrue( user.loginUser("kyl_1", "Ch&&set@ke99!") ); 
        }
        
        @Test 
        public void testInvalidLogin() { 
            TalkAppExtClass user = new TalkAppExtClass("kyl_1", "Ch&&set@ke99!", "+27838968976"); 
            assertFalse( user.loginUser("wrong_user", "wrong_password") ); 
        }
        
       @Test public void testReturnLoginStatusSuccessful() { 
           TalkAppExtClass user = new TalkAppExtClass("kyl_1", "Ch&&set@ke99!", "+27838968976"); 
           assertEquals( "Welcome Kyle Smith, it is great to finally meet you again.", user.returnLoginStatus(true, "Kyle", "Smith") ); 
       } 
       
        @Test public void testReturnLoginStatusUnsuccessful() { 
            TalkAppExtClass user = new TalkAppExtClass("kyl_1", "Ch&&set@ke99!", "+27838968976"); 
            assertEquals( "Username or password is incorrect, please try again later.", user.returnLoginStatus(false, "Kyle", "Smith") ); 
        }
    
}
