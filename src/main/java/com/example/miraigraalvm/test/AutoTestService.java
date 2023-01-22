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


    /*
        native-image 打包工具默认是不会打包 classpath 目录下的 资源文件（如：resources/xxx.jpg），
        通常需要用户在程序编译前目标文件的信息加入配置文件（即 将目标文件信息写入 resource-config.json ）。
        当用户启用 Tracing Agent 模式后可以让程序自动配置，这也是 loadPics() 的目的。
     */
    private void loadPics() {
        ClassPathResource resource = new ClassPathResource("photo.png");
    }
}
