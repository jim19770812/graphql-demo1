package com.example.graphql.graphqldemo1.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.graphql.graphqldemo1.beans.MUser;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MUserResolver implements GraphQLResolver<MUser> {
    @Resource
    private MongoTemplate mongoTemplate;

    public MUser user(String usrName){
        Query query=new Query(Criteria.where("usr_name").is(usrName));
        //this.engine.getHbaseAccessor().remove(new Query(Criteria.where("_id").is(new ObjectId(interactiveInfo.id()))), IPExecutionInteractiveInfo.class);
        MUser result=this.mongoTemplate.findOne(query, MUser.class);
        return result;
    }

    public List<MUser> users() {
        List<MUser> result=this.mongoTemplate.findAll(MUser.class);
        return result;
    }
}
