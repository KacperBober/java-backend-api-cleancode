package com.kainos.ea.validator;

import com.kainos.ea.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserValidatorTest {

    UserValidator userValidator = new UserValidator();

    @Test
    public void isValidUser_shouldReturnTrue_whenValidUser () {
        User user = new User("walshe97@gmail.com", "Password12!", 1,  "salt");

        assertTrue(userValidator.isValidUser(user));
    }

    @Test
    public void shouldReturnFalse_whenEmailInvalid () {
        User user = new User("walshe97mail.com", "Password12!", 1,  "salt");

        assertFalse(userValidator.isValidUser(user));
    }

    @Test
    public void shouldReturnFalse_whenPasswordMissingCapitalLetter () {
        User user = new User("walshe97@gmail.com", "password12!", 1,  "salt");

        assertFalse(userValidator.isValidUser(user));
    }

    @Test
    public void shouldReturnFalse_whenPasswordMissingLowercaseLetter () {
        User user = new User("walshe97@gmail.com", "PASSWORD12!", 1,  "salt");

        assertFalse(userValidator.isValidUser(user));
    }

    @Test
    public void shouldReturnFalse_whenPasswordMissingSpecialChar () {
        User user = new User("walshe97@gmail.com", "Password12", 1,  "salt");

        assertFalse(userValidator.isValidUser(user));
    }

    @Test
    public void shouldReturnFalse_whenPasswordMissingNumber () {
        User user = new User("walshe97@gmail.com", "Password!", 1,  "salt");

        assertFalse(userValidator.isValidUser(user));
    }

    @Test
    public void shouldReturnFalse_whenPasswordTooShort () {
        User user = new User("walshe97@gmail.com", "Pass1!", 1,  "salt");

        assertFalse(userValidator.isValidUser(user));
    }

    @Test
    public void shouldReturnFalse_whenRoleInvalid () {
        User user = new User("walshe97@gmail.com", "Password12!", 0,  "salt");

        assertFalse(userValidator.isValidUser(user));
    }

}
