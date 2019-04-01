package com.bigbaldy.elasticsearchdemo.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * Created by wangjinzhao on 2018/8/31
 */
@Document(indexName = "codesafe", type = "test")
@Data
public class TestPO {
    @Id
    private Long id;

    @Field
    private String name;
}
