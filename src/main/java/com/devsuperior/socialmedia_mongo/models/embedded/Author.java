package com.devsuperior.socialmedia_mongo.models.embedded;

import com.devsuperior.socialmedia_mongo.models.entities.Post;
import com.devsuperior.socialmedia_mongo.models.entities.User;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Author {

    private String id;
    private String name;

    @DBRef(lazy = true)
    private List<Post> posts = new ArrayList<>();

    public Author() {
    }

    public Author(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(User entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
