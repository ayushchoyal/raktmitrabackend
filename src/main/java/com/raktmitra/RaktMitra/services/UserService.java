package com.raktmitra.RaktMitra.services;

import com.raktmitra.RaktMitra.entity.Bank;
import com.raktmitra.RaktMitra.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public User registerUser(User user);

    User findByEmail(String email);


}
