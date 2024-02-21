package com.proyectointegrador.Proyecto.controller.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.proyectointegrador.Proyecto.exception.UserNotFoundException;
import com.proyectointegrador.Proyecto.repository.User;
import com.proyectointegrador.Proyecto.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users/")
public class UsersController {

    private final UsersService usersService;

    public UsersController(@Autowired UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println("--------------------" + user.getId());
        URI createdUserUri = URI.create("/v1/users/" + usersService.save(user).getId());
        return ResponseEntity.created(createdUserUri).body(user);

    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = usersService.all();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable("id") String id) {
        Optional<User> user = usersService.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user1) {
        Optional<User> optionalUser = usersService.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.update(user1);
            User updatedUser = usersService.save(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        if (usersService.findById(id).isPresent()) {
            usersService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            throw new UserNotFoundException(id);
        }

    }
}
