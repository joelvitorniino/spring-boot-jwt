package com.joelvitorniino.resource;

import com.joelvitorniino.dto.UserDTO;
import com.joelvitorniino.model.User;
import com.joelvitorniino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserResource {
    @Autowired
    public UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream().map(UserDTO::new).toList();

        return ResponseEntity.ok().body(listDto);
    }
}
