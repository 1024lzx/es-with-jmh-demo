package com.bigbaldy.elasticsearchdemo.repository;

import com.bigbaldy.elasticsearchdemo.po.RpBugInfoPO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by wangjinzhao on 2018/8/31
 */
public interface BugInfoRepository extends ElasticsearchRepository<RpBugInfoPO,Long> {
}
