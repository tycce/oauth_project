package ru.tycce.authproject.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tycce.authproject.database.entity.UserInfo;
import ru.tycce.authproject.database.repository.UserInfoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseService extends AbstractDatabaseService{

    private final UserInfoRepository userInfoRepository;

    public UserInfo getUserInfoById(int id) {
        return  getEntityById(id, userInfoRepository);
    }

    public List<UserInfo> getAllUsers(){
        return getListEntity(userInfoRepository);
    }

    public UserInfo removeUserInfoById(int id) {
        return removeEntityBy(id, userInfoRepository);
    }

    public UserInfo saveOrUpdateUserInfo(UserInfo userInfo) {
        return saveOrUpdateEntity(userInfo, userInfoRepository);
    }
}
