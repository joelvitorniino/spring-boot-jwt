package com.joelvitorniino.services;

import com.joelvitorniino.data.DetailUserData;
import com.joelvitorniino.model.User;
import com.joelvitorniino.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetailUserServiceImpl implements UserDetailsService {
    @Autowired
    private final UserRepository repository;

    public DetailUserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);

        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User [" + username + "] not found!");
        }

        return new DetailUserData(user);
    }
}
