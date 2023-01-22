package com.example.miraigraalvm.service;

import com.example.miraigraalvm.config.RobotConfig;
import com.example.miraigraalvm.handler.FriendMessageHandler;
import lombok.RequiredArgsConstructor;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.utils.BotConfiguration;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RobotService implements ApplicationListener<ApplicationStartedEvent> {
    private final RobotConfig robotConfig;
    private final FriendMessageHandler friendMessageHandler;


    @Override
    public void onApplicationEvent(@NotNull ApplicationStartedEvent event) {
        BotConfiguration botConfiguration = new BotConfiguration();
        botConfiguration.setProtocol(BotConfiguration.MiraiProtocol.IPAD);

        botConfiguration.fileBasedDeviceInfo("deviceInfo.json");
//        botConfiguration.noNetworkLog();
//        botConfiguration.noBotLog();

        Bot bot = BotFactory.INSTANCE
                .newBot(robotConfig.getAccount(), robotConfig.getPassword(), botConfiguration);
        bot.getEventChannel()
                .subscribeAlways(FriendMessageEvent.class, friendMessageHandler::handle);
        try {
            bot.login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
