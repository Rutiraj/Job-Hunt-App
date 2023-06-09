package com.stackroute.userservices.service;

//import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stackroute.userservices.exceptions.UserAlreadyExistException;
import com.stackroute.userservices.exceptions.UserNotFoundException;
import com.stackroute.userservices.exceptions.UserServicesException;
import com.stackroute.userservices.model.User;
import com.stackroute.userservices.model.UserHelper;
import com.stackroute.userservices.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) throws UserAlreadyExistException {
        User existingUser = userRepository.findByuserName(user.getUserName());
        if (existingUser != null) {
            throw new UserAlreadyExistException();
        }
        return userRepository.save(user);
    }

    @Override
    public User login(String userName, String password) {
        User user = userRepository.findByuserNameAndPassword(userName, password);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;

    }

    @Override
    public User updateProfile(User user,String userName) throws UserServicesException {
    User exsistinguser = userRepository.findByuserName(user.getUserName());
    if(exsistinguser!=null){
         exsistinguser.copyWithoutNotNull(user);
        return userRepository.save(exsistinguser);
    }
    return null;
    }

    @Override
    public String changePassword(UserHelper userHelper) throws UserServicesException {
        User existingUser = userRepository.findByuserName(userHelper.getUserName());
        if(existingUser.getPassword().equals(userHelper.getOldPassword())){
            existingUser.setPassword(userHelper.getNewPassword());
            userRepository.save(existingUser);
            return "Password updated successfully";
        }
        return "Pls enter correct Old password";
    }

    @Override
    public User getProfile(String userName) throws UserServicesException {
    User exsistinguser = userRepository.findByuserName(userName);
    if(exsistinguser!=null){
         return exsistinguser;
    }
    return null;
    }

    

    


}
