package cn.cckoo.yu.user.domain;

import cn.cckoo.yu.common.Validator.FieldCheck;
import cn.cckoo.yu.common.exception.DataNotExistException;
import cn.cckoo.yu.user.repo.User;
import cn.cckoo.yu.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Users implements FieldCheck<String> {
    @Autowired
    private UserRepo userRepo;

    public User fetchByOpenId(String openId) throws DataNotExistException {
        User user = userRepo.findByOpenId(openId);

        if (user == null) {
            throw new DataNotExistException("该用户不存在");
        }

        return user;
    }

    public User save(User user) {
        return userRepo.save(user);
    }

    public User find(long id) {
        return userRepo.findOne(id);
    }

    @Override
    public boolean isValueUnique(String phoneNum) {
        return !userRepo.existsByPhoneNum(phoneNum);
    }
}
