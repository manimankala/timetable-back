package com.major.TimeTable.loginCred;

import com.major.TimeTable.common.Response;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/login")
public class LoginController {
    private final LoginDetailsRepo loginDetailsRepo;

    private static final String secretKey = "89yjhabvdhjavsdjabsduyasdguyasdutaudyAhvvhvvhjvhjvhgchg";
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(LoginDetailsRepo loginDetailsRepo) {
        this.loginDetailsRepo = loginDetailsRepo;
    }

    @PostMapping(value = "/validate")
    public Response validateLogin(@RequestBody LoginDetails loginDetails, HttpSession session) {
        Response response = new Response();
        LoginDetails ld = loginDetailsRepo.findByUsername(loginDetails.getUsername());
        if (ld != null) {
            if (passwordEncoder.matches(loginDetails.getPassword(), ld.getPassword())) {
                session.setAttribute("user", ld); // Store user in session
                response.setData(ld);
            } else {
                response.setData("Incorrect Password");
            }
        } else {
            response.setData("No User Found");
        }
        return response;
    }

    @GetMapping
    public List<LoginDetails> getAllLoginDetails() {
        return loginDetailsRepo.findAll();
    }
}