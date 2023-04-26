package com.Reviews.Controller;
import com.Reviews.Security.User.User;
import com.Reviews.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {
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
    @GetMapping("user/{id_user}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Long id_user){
        return ResponseEntity.ok(userService.getUser(id_user));
    }
    // Feed for all reviews from users


}
