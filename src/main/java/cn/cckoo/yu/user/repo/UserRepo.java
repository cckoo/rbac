package cn.cckoo.yu.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long>{
    User findByOpenId(String openId);


    boolean existsByPhoneNum(String phoneNum);
}
