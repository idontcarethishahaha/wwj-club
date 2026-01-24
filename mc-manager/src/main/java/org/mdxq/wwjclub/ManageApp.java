package org.mdxq.wwjclub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 类说明：管理端项目启动类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 10:16
 */
@SpringBootApplication
@MapperScan(basePackages = {"org.mdxq.wwjclub.rms.dao"})
@EnableScheduling // 开启人物调度
@EnableRetry // 乐观锁失败重试
public class ManageApp {
    public static void main(String[] args) {
        SpringApplication.run(ManageApp.class, args);
    }
}
