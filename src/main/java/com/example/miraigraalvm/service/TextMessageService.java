package com.example.miraigraalvm.service;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Friend;
import org.springframework.stereotype.Component;

@Component
public class TextMessageService {

    public void reply(long userId){
        Bot bot = Bot.getInstances()
                .get(0);
        Friend friend = bot.getFriend(userId);
        if (friend == null) return;
        friend.sendMessage("您输入的指令未找到，Sorry");
    }
}
