package com.example.graphql.graphqldemo1.repository;

import com.example.graphql.graphqldemo1.dao.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("jobRepository")
public interface JobRepository extends MongoRepository<Job, String> {
}
