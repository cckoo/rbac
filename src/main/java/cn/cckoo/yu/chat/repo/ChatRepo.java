package cn.cckoo.yu.chat.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepo extends JpaRepository<Chat, Long> {
    List<Chat> findAllByPhoneNumAndStatus(String phoneNum, short status);
}
