package com.example.springsecurity.Controller;


import com.example.springsecurity.Model.User;
import com.example.springsecurity.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user) {

        authService.register(user);

        return ResponseEntity.status(200).body(user);

    }


    @PutMapping("/update/{username}")
    public ResponseEntity update(@PathVariable String username , @RequestBody @Valid User user) {

        return ResponseEntity.status(200).body(authService.updateUser(username, user));

    }



    @DeleteMapping("/delete/{usernameAdmin}/{usernameCustomer}")
    public ResponseEntity deleteUser(@PathVariable String usernameAdmin  ,@PathVariable String usernameCustomer) {
     return ResponseEntity.status(200).body(authService.delete(usernameAdmin,usernameCustomer));
    }



    @GetMapping("/get/{username}")
    public ResponseEntity getAllUser(@PathVariable String username){
        return ResponseEntity.status(200).body(authService.getAllUsers(username));
    }
}
