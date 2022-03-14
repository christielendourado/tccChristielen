package com.christielen.informacoescadastrais.repository;

import com.christielen.informacoescadastrais.entity.Convened;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ConvenedRepository extends MongoRepository<Convened, ObjectId> {

    @Query(value="{ 'user.$id' : ?0 }")
    public Convened findByUser(ObjectId id);
}
