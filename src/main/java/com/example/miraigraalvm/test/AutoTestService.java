package com.example.miraigraalvm.test;

import cn.hutool.core.io.resource.ClassPathResource;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/*
    一个自动测试类，通过设定 -DautoTest=true 自动触发
    当程序处于 Tracing Agent 模式时，它能让 GraalVM 更好地发现、注册程序运行所需资源
 */
@Slf4j
@Service
public class AutoTestService implements ApplicationListener<ApplicationStartedEvent> {
    @Value("${autoTest:#{false}}")
    private boolean enableAutoTest;


    @Override
    public void onApplicationEvent(@NotNull ApplicationStartedEvent event) {
        if (enableAutoTest) {
            log.info("******AotAutoTestService.onApplicationEvent：开启 自动测试");

            loadPics();

            log.info("******AotAutoTestService.onApplicationEvent：自动测试完毕");
        }
    }

    private void loadPics() {
        ClassPathResource resource = new ClassPathResource("photo.png");
    }
}
