package com.joelvitorniino.service;

import com.joelvitorniino.dto.UserDTO;
import com.joelvitorniino.model.User;
import com.joelvitorniino.repository.UserRepository;
import com.joelvitorniino.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User insert(User obj) {
        obj.setPassword(encoder.encode(obj.getPassword()));

        return repository.save(obj);
    }

    public Boolean validatePassword(String username, String password) {
        Optional<User> optUser = repository.findByUsername(username);

        if(optUser.isEmpty()) {
            throw new UserNotFoundException("Username is not found!");
        };

        User user = optUser.get();
        boolean valid = encoder.matches(password, user.getPassword());

        return valid;
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

        return user;
    }

    public User update(User obj) {
        User newObj = repository.findById(obj.getId()).get();
        updateData(newObj, obj);

        return repository.save(newObj);
    }

    private void updateData(User newObj, User obj) {
        newObj.setUsername(obj.getUsername());
        newObj.setPassword(obj.getPassword());
    }

    public User fromDTO(UserDTO objDto) {
        return new User(objDto.getId(), objDto.getUsername(), objDto.getPassword());
    }
}
