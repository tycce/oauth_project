package ru.tycce.authproject.database.repository;

import org.springframework.data.repository.CrudRepository;
import ru.tycce.authproject.database.entity.UserInfo;

public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {
}
