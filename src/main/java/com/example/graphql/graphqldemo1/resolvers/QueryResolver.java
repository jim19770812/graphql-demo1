package com.example.graphql.graphqldemo1.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.graphql.graphqldemo1.beans.Job;
import com.example.graphql.graphqldemo1.beans.MUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryResolver implements GraphQLQueryResolver {
    private final MUserResolver userResolver;
    private final JobResolver jobResolver;
    public QueryResolver(MUserResolver userResolver, JobResolver jobResolver){
        this.userResolver=userResolver;
        this.jobResolver=jobResolver;
    }

    public MUser user(String usrName){
        return this.userResolver.user(usrName);
    }

    public List<MUser> users(){
        return this.userResolver.users();
    }

    public Job job(String name){
        return this.jobResolver.job(name);
    }
}
