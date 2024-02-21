package com.proyectointegrador.Proyecto.service;


import com.proyectointegrador.Proyecto.repository.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServiceMap implements UsersService {

    HashMap<String, User> users = new HashMap<String, User>();

    @Override
    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<User> all() {
        return users.values().stream().collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public User update(User user, String userId) {
        return null;
    }
}