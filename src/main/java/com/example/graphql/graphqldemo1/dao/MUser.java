package com.example.graphql.graphqldemo1.dao;

import com.example.graphql.graphqldemo1.enums.Gender;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户Bean，需要定义好Mongo @Document和@Field
 */
@Getter @Setter
@ToString
@EqualsAndHashCode
@Document(value = "m_user")
public class MUser {
    @Id
    private String usrId;
    @Indexed
    @Field("user_name")
    @Accessors(chain = true)
    private String usrName;
    @Field("gender")
    @Accessors(chain = true)
    private Gender usrGender;
    @Field("age")
    @Accessors(chain = true)
    private Integer usrAge;
    @Field("join_date")
    @Accessors(chain = true)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    private Date joinDate;
    @Field("titles")
    @Accessors(chain = true)
    private String[] titles;
    @Field("job")
    @Accessors(chain = true)
    private Job job=new Job();
}
