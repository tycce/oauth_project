package ru.tycce.authproject.Util;

import org.junit.jupiter.api.Test;
import ru.tycce.authproject.database.AbstractDatabase;
import ru.tycce.authproject.database.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;

class JsonUtilTest {

    @Test
    void getObjectNode() {
        UserInfo user1 = new UserInfo();
        user1.setName("1");
        user1.setId(1);
        System.out.println(JsonUtil.getObjectNode(user1));
    }

    @Test
    void getArrayNode() {
        List<UserInfo> list = new ArrayList<>();

        list.add(UserInfo.getUserInfoWithRandomName());
        list.add(UserInfo.getUserInfoWithRandomName());

        System.out.println(JsonUtil.getArrayNode(list));
    }

    @Test
    void getJsonResponse() {
        AbstractDatabase.JsonResponse response = new AbstractDatabase.JsonResponse(true, 200, UserInfo.getUserInfoWithRandomName());

        System.out.println(JsonUtil.getJsonResponse(response));
    }

}