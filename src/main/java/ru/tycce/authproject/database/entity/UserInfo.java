package ru.tycce.authproject.database.entity;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Data
@NoArgsConstructor
public class UserInfo {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    private String name;

    private LocalDateTime crTime;

    public static UserInfo getUserInfoWithRandomName(){
        UserInfo newUserInfo = new UserInfo();
        newUserInfo.setCrTime(LocalDateTime.now());
        newUserInfo.setName(new Faker().name().fullName());
        return newUserInfo;
    }
}
