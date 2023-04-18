package com.Reviews.Controller;
import com.Reviews.Security.User.User;
import com.Reviews.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/user")
public class UserController {
    @Autowired
    private UserService userService;
    @DeleteMapping("/{id_user}")
    public String deleteUserById(@PathVariable Long id_user){
        return userService.deleteUser(id_user);
    }
    @GetMapping("/list")
    public List<User> getUsers(){
        return userService.findallusers();
    }
    @GetMapping("/{id_user}")
    public User getUser(@PathVariable Long id_user){
        return userService.getUser(id_user).orElseThrow();
    }
}
