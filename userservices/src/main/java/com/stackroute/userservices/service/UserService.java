package com.stackroute.userservices.service;

import com.stackroute.userservices.exceptions.UserAlreadyExistException;
import com.stackroute.userservices.exceptions.UserNotFoundException;
import com.stackroute.userservices.exceptions.UserServicesException;
import com.stackroute.userservices.model.User;
import com.stackroute.userservices.model.UserHelper;

public interface UserService {
    User register(User user) throws UserAlreadyExistException;

    User login(String userName, String password) throws UserNotFoundException;

    User updateProfile(User user,String userName) throws UserServicesException;

    String changePassword(UserHelper userHelper) throws UserServicesException;

    User getProfile(String userName) throws UserServicesException;

}

