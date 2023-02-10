package com.example.miraigraalvm.handler;

import com.example.miraigraalvm.service.PictureService;
import com.example.miraigraalvm.service.TextMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageContent;
import net.mamoe.mirai.message.data.PlainText;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FriendMessageHandler {
    private final PictureService pictureService;
    private final TextMessageService textMessageService;


    public void handle(@NotNull FriendMessageEvent event) {
        MessageChain message = event.getMessage();
        MessageContent content = message.get(PlainText.Key);
        if (content == null) return;

        String key = content.toString()
                .trim();
        log.info("******FriendMessageHandler.handle：key：{}", key);
        long userId = event.getSender()
                .getId();
        if (key.equals("图片消息")) {
            pictureService.reply(userId);
        } else {
            textMessageService.reply(userId);
        }
    }
}
