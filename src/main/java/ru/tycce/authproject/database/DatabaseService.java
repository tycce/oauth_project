package ru.tycce.authproject.database;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.tycce.authproject.Util.JsonUtil;
import ru.tycce.authproject.database.entity.UserInfo;
import ru.tycce.authproject.database.repository.UserInfoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseService extends AbstractDatabase{

    private final UserInfoRepository userInfoRepository;

    public ObjectNode saveOrUpdateUserInfo(UserInfo userInfo) {
        return JsonUtil.getObjectNode(saveOrUpdateEntity(userInfo, userInfoRepository));
    }

    public ObjectNode removeUserInfoById(int id) {
        return JsonUtil.getObjectNode(removeEntityById(id, userInfoRepository));
    }

    public ObjectNode getUserInfoById(int id) {
        return JsonUtil.getObjectNode(getEntityById(id, userInfoRepository));
    }

    public ObjectNode getListUserInfo(){
        return JsonUtil.getObjectNode(getListEntity(userInfoRepository));
    }

}
