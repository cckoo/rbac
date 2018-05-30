package cn.cckoo.yu.chat.controller;

import cn.cckoo.yu.chat.domain.Chats;
import cn.cckoo.yu.chat.repo.Chat;
import cn.cckoo.yu.common.respond.Respond;
import cn.cckoo.yu.common.respond.SuccessRespond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    Chats chats;

    @PostMapping("/sendMessage")
    public Respond sendMessage(Chat chat) {
        chats.save(chat);
        return new SuccessRespond();
    }

    @PostMapping("/getUnreadMessages")
    public Respond getUnreadMessages(String phoneNum) {
        return new SuccessRespond(chats.getUnreadMessages(phoneNum));
    }
}
