package com.devsuperior.socialmedia_mongo.service;

import com.devsuperior.socialmedia_mongo.models.DTO.PostDTO;
import com.devsuperior.socialmedia_mongo.models.DTO.UserDTO;
import com.devsuperior.socialmedia_mongo.models.entities.Post;
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

    public UserDTO insert(UserDTO dto) {
        User entity = new User();
        copyDtoToEntity(entity, dto);
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    public UserDTO update(String id, UserDTO dto) {
        User entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User não localizado: " + id));
        copyDtoToEntity(entity, dto);
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("User não localizado: " + id);
        }
        repository.deleteById(id);
    }

    public List<PostDTO> getUserPosts(String id) {
        User entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User não localizado: " + id));
        return entity.getPosts().stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
    }

    private void copyDtoToEntity(User entity, UserDTO dto) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
    }
}
