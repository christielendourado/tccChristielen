package com.christielen.informacoescadastrais.repository;

import com.christielen.informacoescadastrais.entity.HealthPlan;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HealthPlanRepository extends MongoRepository<HealthPlan, ObjectId> {

    HealthPlan findByCode(String code);
}
