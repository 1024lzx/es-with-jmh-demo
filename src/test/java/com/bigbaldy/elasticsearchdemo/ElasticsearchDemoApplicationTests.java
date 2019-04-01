package com.bigbaldy.elasticsearchdemo;

import com.bigbaldy.elasticsearchdemo.po.RpBugInfoPO;
import com.bigbaldy.elasticsearchdemo.repository.BugInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchDemoApplication.class)
@Slf4j
public class ElasticsearchDemoApplicationTests {
    @Autowired
    private BugInfoRepository bugInfoRepository;

    @Test
    public void insert() {
        List<RpBugInfoPO> rpBugInfoPOList = new ArrayList<>();
        for (long i = 1; i < 10000; i++) {
            RpBugInfoPO rpBugInfoPO = RpBugInfoPO.builder()
                    .BugId(i)
                    .auditMemo("memo" + i)
                    .auditResult(1)
                    .auditStatus(1)
                    .auditUser((i % 10))
                    .batchId((i % 100))
                    .build();
            rpBugInfoPOList.add(rpBugInfoPO);
        }
        long start = System.currentTimeMillis();
        bugInfoRepository.saveAll(rpBugInfoPOList);
        long end = System.currentTimeMillis();
        log.info("insert time:" + (end - start));
    }

    @Test
    public void find() {
        long start = System.currentTimeMillis();
        RpBugInfoPO result = bugInfoRepository.findById(5555L).orElseGet(RpBugInfoPO::new);
        long end = System.currentTimeMillis();
        log.info("find time:" + (end - start));
        Assert.assertEquals(result.getAuditMemo(), "memo5555");
    }

    @Test
    public void clear() {
        bugInfoRepository.deleteAll();
    }

}
