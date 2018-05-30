package cn.cckoo.yu.app.repo;

import cn.cckoo.yu.common.util.Date;
import cn.cckoo.yu.common.util.SerialNumber;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "app")
@Setter
@Getter
public class App {
    private static final int TOKEN_LENGTH = 8;

    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String describe;

    @NotBlank
    private String token;

    private long operateId;

    @Setter(AccessLevel.NONE)
    private Timestamp createTime;

    @PrePersist
    private void appPrePersist() {
        createTime = Date.getNowTimeStamp();
    }

    public void generateToken() {
        token = SerialNumber.randomString(TOKEN_LENGTH);
    }
}
