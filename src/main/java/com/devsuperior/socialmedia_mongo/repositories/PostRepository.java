package com.devsuperior.socialmedia_mongo.repositories;

import com.devsuperior.socialmedia_mongo.models.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}
