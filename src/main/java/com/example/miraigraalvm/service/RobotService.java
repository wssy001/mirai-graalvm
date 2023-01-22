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

        /*
            当程序运行在 Native 模式下访问程序外部文件需要在程序编译前主动告知 GraalVM 目
            标文件的信息（即 将目标文件信息写入 resource-config.json ）。
            在本示例代码中当用户开启 Tracing Agent 模式，相关程序会自动发现外部
            deviceInfo.json 文件并告知 GraalVM。
            若使用 loadDeviceInfoJson() 则不需要关心这点。
         */
        botConfiguration.fileBasedDeviceInfo("deviceInfo.json");
//        botConfiguration.loadDeviceInfoJson(deviceInfo);
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
