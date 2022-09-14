package com.example.graphql.graphqldemo1.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.graphql.graphqldemo1.beans.Job;
import com.example.graphql.graphqldemo1.repository.JobRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobResolver implements GraphQLResolver<Job> {
    private MongoTemplate mongoTemplate;
    public JobResolver() {
    }

    public Job job(String name){
        return null;
    }
}
