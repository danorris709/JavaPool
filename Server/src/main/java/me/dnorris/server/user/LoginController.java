package me.dnorris.server.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserDatabaseController userDatabase;

    public LoginController(UserDatabaseController userDatabase) {
        this.userDatabase = userDatabase;
    }

    @GetMapping("/get/user/attempt_login")
    public boolean attemptLogin(@RequestParam(name = "username") String username,
                                @RequestParam(name = "password") String encodedPassword,
                                Model model) {
        return this.userDatabase.getPassword(username).equals(encodedPassword);
    }

}
