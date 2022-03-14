package com.christielen.informacoescadastrais.repository;

import com.christielen.informacoescadastrais.entity.Status;
import com.christielen.informacoescadastrais.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatusRepository extends MongoRepository<Status, ObjectId> {

    Status findByDescription(String description);
}
