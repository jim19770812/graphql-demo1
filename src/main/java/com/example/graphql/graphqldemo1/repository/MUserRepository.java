package com.example.graphql.graphqldemo1.repository;

import com.example.graphql.graphqldemo1.dao.MUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 定义用户Repository
 */
@Repository("userRepository")
public interface MUserRepository extends MongoRepository<MUser, String> {
}
