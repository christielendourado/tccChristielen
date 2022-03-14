package com.christielen.informacoescadastrais.repository;

import com.christielen.informacoescadastrais.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}
