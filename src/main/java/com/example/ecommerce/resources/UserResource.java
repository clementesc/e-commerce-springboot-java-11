package com.example.ecommerce.resources;

import com.example.ecommerce.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping("/findAll")
    public ResponseEntity<User> findAll(){
        User u = new User(1, "Maria", "maria@gmail.com", "49-123456", "123456");
        return ResponseEntity.ok().body(u);
    }
}
