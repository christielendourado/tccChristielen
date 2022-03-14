package com.christielen.informacoescadastrais.repository;

import com.christielen.informacoescadastrais.entity.MembershipHealthPlan;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MembershipHealthPlanRepository extends MongoRepository<MembershipHealthPlan, ObjectId> {

    @Query(value="{ 'associate.$id' : ?0 }")
    public MembershipHealthPlan findByAssociate(ObjectId id);
}
