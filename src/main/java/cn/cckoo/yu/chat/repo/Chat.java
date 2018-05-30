package cn.cckoo.yu.chat.repo;

import cn.cckoo.yu.chat.config.ChatConfig;
import cn.cckoo.yu.common.Validator.PhoneNumber;
import cn.cckoo.yu.common.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "chat")
@Getter
@Setter
public class Chat {
    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;

    @NotNull
    @JsonIgnore
    private long fromId;

    @NotBlank
    @PhoneNumber
    @JsonIgnore
    private String phoneNum;

    @NotBlank
    private String message;

    @JsonIgnore
    private short status;

    @JsonIgnore
    private Timestamp createTime;

    @PrePersist
    private void appPrePersist() {
        status = ChatConfig.STATUS_UNREAD;
        createTime = Date.getNowTimeStamp();
    }
}
