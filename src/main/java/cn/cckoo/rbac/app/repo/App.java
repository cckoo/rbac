package cn.cckoo.rbac.app.repo;

import cn.cckoo.rbac.common.util.Date;
import cn.cckoo.rbac.common.util.RandomString;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
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

    @NotNull
    @Min(0)
    private long operateId;

    @Setter(AccessLevel.NONE)
    private Timestamp createTime;

    @PrePersist
    private void appPrePersist() {
        createTime = Date.getNowTimeStamp();
    }

    public void generateToken() {
        token = RandomString.generateWithLength(TOKEN_LENGTH);
    }
}
