package com.devsuperior.socialmedia_mongo.service;

import com.devsuperior.socialmedia_mongo.models.DTO.UserDTO;
import com.devsuperior.socialmedia_mongo.models.entities.User;
import com.devsuperior.socialmedia_mongo.repositories.UserRepository;
import com.devsuperior.socialmedia_mongo.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserDTO> findAll() {
        List<User> list = repository.findAll();
        return list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
    }

    public UserDTO findById(String id) {
        User user = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User não localizado: " + id));
        return new UserDTO(user);
    }
}
