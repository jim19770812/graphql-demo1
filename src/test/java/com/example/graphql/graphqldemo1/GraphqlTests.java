package com.example.graphql.graphqldemo1;

import com.example.graphql.graphqldemo1.beans.Job;
import com.example.graphql.graphqldemo1.beans.MUser;
import com.example.graphql.graphqldemo1.enums.Gender;
import com.example.graphql.graphqldemo1.repository.JobRepository;
import com.example.graphql.graphqldemo1.repository.MUserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import graphql.schema.idl.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import java.util.Map;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = GraphqlTests.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
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
    void testMongodb1(){
        Query query=new Query(Criteria.where("usrName").is("吕布"));
        List<MUser> uList=this.mongoTemplate.find(query, MUser.class);
        if (uList.isEmpty()){
            MUser u=new MUser();
            u.setTitles(new String[]{"武将", "厨师"}).setJoinDate(new Date()).setUsrGender(Gender.MALE).setUsrName("吕布").setUsrAge(20)
                    .setJob(new Job().setName("打击山贼").setJobSalary(200000.0));
            u=this.mongoTemplate.save(u);
            System.out.println(u);
            Assertions.assertNotNull(u);
            return;
        }
        uList.parallelStream().forEach(System.out::println);
    }

//    @Test
//    @SneakyThrows
//    void testGraphQl1(){
//        String schema = "type Query{hello: String} schema{query: Query}";
//
//        SchemaParser schemaParser = new SchemaParser();
//        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);
//
//        RuntimeWiring.newRuntimeWiring().type("Query", builder -> {
//            new TypeRuntimeWiring.Builder().typeName("Query")
//                    .dataFetcher("", new StaticDataFetcher(""))
//                    .dataFetcher("", new StaticDataFetcher(""))
//                    .dataFetcher("", new StaticDataFetcher(""))
//                    .dataFetcher("", new StaticDataFetcher(""))
//                    .dataFetcher("", new StaticDataFetcher(""))
//                    .dataFetcher("", new StaticDataFetcher(""));
//            return builder;
//        })
//        TypeRuntimeWiring typeRuntimeWiring=TypeRuntimeWiring.newTypeWiring("Query")
//                .ty
//                .build();
//        RuntimeWiring runtimeWiring = new RuntimeWiring(builder)
//                .type("Query", builder -> builder .dataFetcher("hello", new StaticDataFetcher("world")))
//                .build();
//
//        SchemaGenerator schemaGenerator = new SchemaGenerator();
//        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
//
//        GraphQL build = GraphQL.newGraphQL(graphQLSchema).build();
//        ExecutionResult executionResult = build.execute("{hello}");
//
//        System.out.println(executionResult.getData().toString());
//        // Prints: {hello=world}
//    }


    /**
     * 通过StaticDataFetcher可以把静态内容查询并输出，
     * 适合固定内容，比如一部分永远不变的基础数据就可以这样做
     */
    @Test
    @SneakyThrows
    void testGraphql2(){
        //这里是定义schema 的字符串，定义了一个名为hello的查询，返回的数据类型是String
        //schema除了直接通过String字符串定义之外，还可以通过SDL文件（后缀为*.graphqls的文件）或编码的方式定义。
        String schema = "type Query{hello: String}";

        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);

        //实例化一个RuntimeWiring对象，这个对象中关联了一个DataFetcher对象，DataFetcher对象是用来获取数据的，获取数据的方式需要开发人员根据实际情况实现，它只关心返回结果
        //这里是将名为hello的查询关联到一个简单的StaticDataFetcher对象，它返回一个字符串"world"
        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", builder -> builder.dataFetcher("hello", new StaticDataFetcher("world")))
                .build();

        SchemaGenerator schemaGenerator = new SchemaGenerator();

        //将schema定义与RuntimeWiring绑定，生产可执行的schema
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

        GraphQL build = GraphQL.newGraphQL(graphQLSchema).build();

        //执行hello查询
        ExecutionResult executionResult = build.execute("{hello}");

        //输出查询结果，结果为{hello=world},默认是Map格式的数据
        System.out.println(executionResult.getData().toString());
        Map<String, Object> data=executionResult.getData();
        String jsonStr=new Gson().toJson(data);
        System.out.println(jsonStr);
    }
}