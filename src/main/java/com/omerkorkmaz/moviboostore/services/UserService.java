package com.omerkorkmaz.moviboostore.services;

import com.omerkorkmaz.moviboostore.model.User;
import com.omerkorkmaz.moviboostore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user){
        userRepository.save(user);
    }

    public User getUserByEmailAndPassword(String email, String password){
        return userRepository.findUserByEmailAndPassword(email, password);
    }

}
