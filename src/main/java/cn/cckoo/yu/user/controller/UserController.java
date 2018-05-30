package cn.cckoo.yu.user.controller;

import cn.cckoo.yu.common.Validator.PhoneNumber;
import cn.cckoo.yu.common.Validator.Unique;
import cn.cckoo.yu.common.exception.DataNotExistException;
import cn.cckoo.yu.common.respond.Respond;
import cn.cckoo.yu.common.respond.SuccessRespond;
import cn.cckoo.yu.user.domain.Users;
import cn.cckoo.yu.user.repo.User;
import cn.cckoo.yu.wechat.domain.WeChats;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    Users users;
    @Autowired
    WeChats weChats;

    @PostMapping("/getUserInfo")
    public Respond getUserInfo(@Valid @NotNull @NotBlank String openId) throws DataNotExistException {
        return new SuccessRespond(users.fetchByOpenId(openId));
    }

    @PostMapping("/create")
    public Respond create(@Valid @ModelAttribute User user) {
        return new SuccessRespond(users.save(user));
    }

    @PostMapping("/getSessionKey")
    public Respond getSessionKey(@Valid @NotNull @NotBlank String code) throws IOException {
        return new SuccessRespond(weChats.getSessionKey(code));
    }

    @PostMapping("/completePhoneNum")
    public Respond completePhoneNUm(long id,
            @PhoneNumber @Unique(message = "电话号码已经被注册", fieldCheck = Users.class) String phoneNum) {
        User user = users.find(id);
        user.setPhoneNum(phoneNum);
        return new SuccessRespond(users.save(user));
    }
}
