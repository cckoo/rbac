package cn.cckoo.yu.user.repo;

import cn.cckoo.yu.common.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "user")
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    @NotNull
    private String openId;

    private String phoneNum = "";

    @NotNull
    private String nickName;

    @Max(2)
    @Min(0)
    private short gender;

    @NotNull
    private String city;

    @NotNull
    private String province;

    @NotNull
    private String country;

    @JsonIgnore
    private Timestamp createTime;

    @PrePersist
    private void appPrePersist() {
        createTime = Date.getNowTimeStamp();
    }
}
