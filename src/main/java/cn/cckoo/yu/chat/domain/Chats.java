package cn.cckoo.yu.chat.domain;

import cn.cckoo.yu.chat.config.ChatConfig;
import cn.cckoo.yu.chat.repo.Chat;
import cn.cckoo.yu.chat.repo.ChatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class Chats {
    @Autowired
    ChatRepo chatRepo;

    public void save(Chat chat) {
        chatRepo.save(chat);
    }

    @Transactional
    public List<Chat> getUnreadMessages(String phoneNumber) {

        List<Chat> unreadMessages = chatRepo.findAllByPhoneNumAndStatus(phoneNumber, ChatConfig.STATUS_UNREAD);
        for (Chat chat :
                unreadMessages) {
            chat.setStatus(ChatConfig.STATUS_READ);
        }
        chatRepo.save(unreadMessages);
        return unreadMessages;
    }
}
