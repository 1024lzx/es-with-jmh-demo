package com.bigbaldy.elasticsearchdemo.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "codesafe",type = "bugInfo")
public class RpBugInfoPO {
    @Id
    private Long BugId;
    private Long batchId;
    private Long projectId;
    private long instanceId;
    private int originalLevel;
    private int bugLevel;
    private String ruleCode;
    private String fileId;
    private String filePath;
    private String md5;
    private String bugFunc;
    private int beginLine;
    private int beginCol;
    private int endLine;
    private int endCol;
    private int auditResult;
    private String auditMemo;
    private Long auditUser;
    private Date auditTime;
    private String svnGitInfo;
    private int tool;
    private String ruleName;
    private String spoint;
    private int auditStatus = 1;
    private int language;
    private String frontPermission = "test";
    private int preAudit = 2;
}