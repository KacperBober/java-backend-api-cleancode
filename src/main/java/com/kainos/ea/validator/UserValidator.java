package com.kainos.ea.validator;

import com.kainos.ea.model.User;
import com.sanctionco.jmail.JMail;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class UserValidator {

    public boolean isValidUser(User user) {

//      CHECK FOR VALID EMAIL
        if (!JMail.isValid(user.getEmail())) {
            return false;
        }

//        CHECK FOR VALID PASSWORD
        if (!Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$").matcher(user.getPassword()).matches()) {
            return false;
        }

//        CHECK FOR EMPTY ROLE
        if (user.getRole() == 0) {
            return false;
        }
        
//        CHECK FOR EMPTY SALT
        if (StringUtils.isBlank(user.getSalt())) {
            return false;
        }

        return true;
    }
}

