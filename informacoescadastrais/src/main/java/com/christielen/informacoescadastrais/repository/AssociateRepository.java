package com.christielen.informacoescadastrais.repository;

import com.christielen.informacoescadastrais.entity.Associate;
import com.christielen.informacoescadastrais.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AssociateRepository extends MongoRepository<Associate, ObjectId> {

    @Query(value="{ 'user.$id' : ?0 }")
    public Associate findByUser(ObjectId id);

    public Associate findByCpf(String cpf);
}
