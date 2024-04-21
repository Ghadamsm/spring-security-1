package com.example.springsecurity.Service;


import com.example.springsecurity.Model.User;
import com.example.springsecurity.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;


    public void register(User user) {

        user.setRole("CUSTOMER");

        String hash = new BCryptPasswordEncoder().encode(user.getPassword());

        user.setPassword(hash);

        authRepository.save(user);

    }



    public String delete(String usernameAdmin ,String usernameCustomer ) {
        User userAdmin = authRepository.findUserByUsername(usernameAdmin);
        User userCustomer = authRepository.findUserByUsername(usernameCustomer);

        if(userAdmin.getRole().equals("ADMIN")) {
            authRepository.delete(userCustomer);
            return "Successfully deleted";
        }

        return "you are not authorized to delete this user";
    }




    public String updateUser(String username , User user) {
        User userCustomer = authRepository.findUserByUsername(username);

        if(userCustomer == null){
            return "you are not authorized to update this user";
        }

        userCustomer.setUsername(user.getUsername());
        userCustomer.setPassword(user.getPassword());

        authRepository.save(userCustomer);

        return "Successfully updated";
    }




    public List<User> getAllUsers(String username) {
        User user = authRepository.findUserByUsername(username);
        if(user.getRole().equals("ADMIN")){
            return authRepository.findAll();
        }
        return null;
    }



}
