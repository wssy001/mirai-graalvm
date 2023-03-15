package com.example.miraigraalvm.service;

import cn.hutool.core.collection.CollUtil;
import com.example.miraigraalvm.config.RobotConfig;
import com.example.miraigraalvm.dto.BotInfo;
import com.example.miraigraalvm.handler.FriendMessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.network.LoginFailedException;
import net.mamoe.mirai.utils.BotConfiguration;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import xyz.cssxsh.mirai.tool.FixProtocolVersion;

import java.util.TreeSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class RobotService implements ApplicationListener<ApplicationStartedEvent> {
    private final RobotConfig robotConfig;
    private final FriendMessageHandler friendMessageHandler;


    @Override
    public void onApplicationEvent(@NotNull ApplicationStartedEvent event) {
        FixProtocolVersion.update();

        TreeSet<BotInfo> robots = robotConfig.getRobots();
        if (CollUtil.isEmpty(robots)) {
            log.error("******RobotService.onApplicationEvent：机器人配置文件为空");
            return;
        }

        BotConfiguration botConfiguration = new BotConfiguration();
        botConfiguration.setProtocol(BotConfiguration.MiraiProtocol.ANDROID_PHONE);
        botConfiguration.fileBasedDeviceInfo("deviceInfo.json");
//        botConfiguration.noNetworkLog();
//        botConfiguration.noBotLog();

        long account;
        for (BotInfo botInfo : robots) {
            account = botInfo.getAccount();
            Bot bot = BotFactory.INSTANCE
                    .newBot(account, botInfo.getPassword(), botConfiguration);
            bot.getEventChannel()
                    .subscribeAlways(FriendMessageEvent.class, friendMessageHandler::handle);
            try {
                bot.login();
                log.info("******RobotService.onApplicationEvent QQ：{} 登陆成功", account);
                break;
            } catch (LoginFailedException e) {
                bot.close();
                log.error("******RobotService.onApplicationEvent：Bot 登录失败，原因：{}，QQ：{}", e.getMessage(), account);
            }
        }

    }
}
