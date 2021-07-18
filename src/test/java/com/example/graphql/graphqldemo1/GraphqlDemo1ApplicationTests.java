package com.example.graphql.graphqldemo1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.GsonBuilderUtils;

@SpringBootTest
class GraphqlDemo1ApplicationTests {

    public Gson gson(){
        Gson result=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return result;
    }

    @Test
    void contextLoads() {
//        JsonObject jsonObject=new JsonObject();
//        jsonObject.addProperty("name", "jim");
//        jsonObject.addProperty("gender", "male");
//        jsonObject.addProperty("age", "20");
//        jsonObject.addProperty("joinDate", "2020-2-14 10:10:10");
//        String schema=jsonObject.toString();
//        String schema= "type Query(hello:String) schema(query:Query)";
//        SchemaParser schemaParser=new SchemaParser();
//        TypeDefinitionRegistry typeDefinitionRegistry=schemaParser.parse(schema);
//
//        RuntimeWiring runtimeWiring = new RuntimeWiring()
//                .type("Query", builder -> builder.dataFetcher("hello", new StaticDataFetcher("world")))
//                .build();
    }
}
