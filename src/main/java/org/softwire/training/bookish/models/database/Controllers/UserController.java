package org.softwire.training.bookish.models.database.Controllers;

import org.softwire.training.bookish.models.database.Models.User;
import org.softwire.training.bookish.models.database.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/checkUser", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public boolean checkUser(@ModelAttribute User user){
        return userService.assertUser(user);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public boolean addNewUser(@ModelAttribute User user) throws NoSuchAlgorithmException {
        return userService.addUser(user);
    }

}
