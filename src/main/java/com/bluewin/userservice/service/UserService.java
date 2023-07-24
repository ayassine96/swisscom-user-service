package com.bluewin.userservice.service;

import com.bluewin.userservice.exceptions.UserNotFoundException;
import com.bluewin.userservice.model.User;
import com.bluewin.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

// This class will contain the business logic for the user operations. For our purposes we only implement updating and retrieving user quota.
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public int getUserQuota(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isPresent()){
            User user = userOptional.get();
            return user.getEmailQuota();
        } else {
            // Throw the UserNotFoundException if the user is not found
            throw new UserNotFoundException("User with id " + id + " not found");
        }
    }


    public User updateUserQuota(Long id, int newQuota) {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isPresent()){
            User user = userOptional.get();

            // Here we add the logic for calculating the updated price after the quota change
            // Assuming 1GB (free tier) to 10GB ($1 per month) according to given
            if (newQuota == 1) {
                user.setEmailQuota(newQuota);
                user.setMonthlyCharge(0);
            } else if (newQuota == 10) {
                user.setEmailQuota(newQuota);
                user.setMonthlyCharge(1);
            } else {
                // Throw an exception if the newQuota is invalid
                throw new IllegalArgumentException("Invalid quota: " + newQuota);
            }

            userRepository.save(user);

            return user;
        } else {
            // Throw the UserNotFoundException if the user is not found
            throw new UserNotFoundException("User with id " + id + " not found");
        }
    }

    // To enable Testing with postman
    public User createUser(User user) {
        return userRepository.save(user);
    }

}
