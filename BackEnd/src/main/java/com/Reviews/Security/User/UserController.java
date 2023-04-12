package com.Reviews.Security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/")
public class UserController {
    @Autowired
    private UserService userService;
    @DeleteMapping("user/{id_user}")
    public String deleteUserById(@PathVariable Long id_user){
        return userService.deleteUser(id_user);
    }
    @GetMapping("user/list")
    public List<User> getUsers(){
        return userService.findallusers();
    }
}
