package com.major.TimeTable.user;

import com.major.TimeTable.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User createStaff(@RequestBody User user) {
        return userService.createUser(user);
    }
    @PutMapping(value = "/filter", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response filterUser(@RequestBody UserFilter filter) {
        return new Response(userService.filterUsers(filter));
    }
    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
    @PutMapping(value = "/change-password/admin")
    public String changePassword(@RequestParam String email, @RequestParam String password) {
        return userService.changePassword(email, password);
    }
    @DeleteMapping(value = "")
    public String deleteUser(@RequestParam UUID id) {
        return userService.deleteUser(id);
    }
}

