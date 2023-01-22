package com.example.miraigraalvm.service;

import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.utils.ExternalResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PictureService {

    public void reply(long userId) {
        try (ExternalResource resource = ExternalResource.create(new ClassPathResource("photo.png").getInputStream())) {
            Bot bot = Bot.getInstances()
                    .get(0);
            Friend friend = bot.getFriend(userId);
            if (friend == null) return;

            Image image = friend.uploadImage(resource);
            MessageChainBuilder builder = new MessageChainBuilder();
            builder.add(image);
            friend.sendMessage(builder.build());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("******PictureService.reply：{}", e.getMessage());
        }
    }
}
