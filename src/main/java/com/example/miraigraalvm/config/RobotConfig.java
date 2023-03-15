package com.example.miraigraalvm.config;

import com.example.miraigraalvm.dto.BotInfo;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

import java.util.Comparator;
import java.util.TreeSet;

@Getter
@Configuration
@ConfigurationProperties(prefix = "mirai-graalvm")
public class RobotConfig {

    @NestedConfigurationProperty
    private final TreeSet<BotInfo> robots = new TreeSet<>(Comparator.comparingLong(BotInfo::getPriority));

}
