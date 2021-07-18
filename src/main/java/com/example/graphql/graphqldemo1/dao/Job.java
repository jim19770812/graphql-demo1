package com.example.graphql.graphqldemo1.dao;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(value = "job")
public class Job {
    @Id
    private String jobId;
    @Field("job_name")
    @Accessors(chain = true)
    private String jobName;
    @Field("job_salary")
    @Accessors(chain = true)
    private BigDecimal salary;
}
