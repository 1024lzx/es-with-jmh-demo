package com.bigbaldy.elasticsearchdemo;

import com.bigbaldy.elasticsearchdemo.po.RpBugInfoPO;
import com.bigbaldy.elasticsearchdemo.repository.BugInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangjinzhao on 2018/8/31
 */
@Slf4j
@Warmup(iterations = 2, time = 1)
@Measurement(iterations = 5, time = 1)
@State(Scope.Benchmark)
public class ElasticsearchBenchmark {
    private ConfigurableApplicationContext applicationContext;

    private List<RpBugInfoPO> rpBugInfoPOList = new ArrayList<>();

    private BugInfoRepository bugInfoRepository;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ElasticsearchBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        if (applicationContext == null) {
            applicationContext = SpringApplication.run(ElasticsearchDemoApplication.class);
        }
        bugInfoRepository = applicationContext.getBean(BugInfoRepository.class);

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
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void _1_saveAll() {
        bugInfoRepository.saveAll(rpBugInfoPOList);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void _2_find() {
        bugInfoRepository.findById(5555L).orElseGet(RpBugInfoPO::new);
    }

    @TearDown
    public void tearDown() {
        //bugInfoRepository.deleteAll();
        if (applicationContext != null) {
            applicationContext.close();
        }
    }
}
