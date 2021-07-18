package com.example.graphql.graphqldemo1;

import com.example.graphql.graphqldemo1.dao.Job;
import com.example.graphql.graphqldemo1.dao.MUser;
import com.example.graphql.graphqldemo1.enums.Gender;
import com.example.graphql.graphqldemo1.repository.JobRepository;
import com.example.graphql.graphqldemo1.repository.MUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Slf4j
public class GraphqlTests {
    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private MUserRepository userRepository;
    @Resource
    private JobRepository jobRepository;

    @BeforeEach
    void before(){
        Assertions.assertNotNull(this.mongoTemplate);
        Assertions.assertNotNull(this.userRepository);
        Assertions.assertNotNull(this.jobRepository);
    }

    @Test
    void test1(){
        Query query=new Query(Criteria.where("usrName").is("吕布"));
        List<MUser> uList=this.mongoTemplate.find(query, MUser.class);
        if (uList.isEmpty()){
            MUser u=new MUser();
            u.setTitles(new String[]{"武将", "厨师"}).setJoinDate(new Date()).setUsrGender(Gender.MALE).setUsrName("吕布").setUsrAge(20)
                    .setJob(new Job().setJobName("打击山贼").setSalary(BigDecimal.valueOf(200000)));
            //this.userRepository.save(u);
            u=this.mongoTemplate.save(u);
            System.out.println(u);
            Assertions.assertNotNull(u);
            return;
        }
        uList.parallelStream().forEach(System.out::println);
    }
}
