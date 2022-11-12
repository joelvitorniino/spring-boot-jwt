package com.joelvitorniino.service;

import com.joelvitorniino.model.User;
import com.joelvitorniino.repository.UserRepository;
import com.joelvitorniino.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User insert(User obj) {
        return repository.save(obj);
    }

    public void deleteById(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    public Optional<User> findById(Integer id) {
        Optional<User> user = repository.findById(id);

        if(user.isPresent()) {
            return user;
        } else {
            user.orElseThrow(() -> new UserNotFoundException("User is not found"));
        }
    }
}
