package com.bbsw.bitboxer2.practica.restcontroller;

import com.bbsw.bitboxer2.practica.dto.UserDTO;
import com.bbsw.bitboxer2.practica.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Validated @RequestBody UserDTO userDTO) {
        if (!authService.loginUser(userDTO.getUsername(), userDTO.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect username or password");
        }
        return ResponseEntity.ok("Login successful");
    }

}
