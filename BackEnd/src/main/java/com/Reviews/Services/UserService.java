package com.Reviews.Services;

import com.Reviews.Security.User.User;
import com.Reviews.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String deleteUser(Long id_user){
        Optional<User> user = userRepository.findById(id_user);
        if (user.isPresent()){
            userRepository.delete(user.get());
            return "User deleted";
        }
        return "User not found";
    }

    public List<User> findallusers() {
        return userRepository.findAll();
    }
}
