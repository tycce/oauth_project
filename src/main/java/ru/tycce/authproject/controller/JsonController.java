package ru.tycce.authproject.controller;


import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.tycce.authproject.database.AbstractDatabase;
import ru.tycce.authproject.database.DatabaseService;
import ru.tycce.authproject.database.entity.UserInfo;
import ru.tycce.authproject.Util.JsonUtil;

@RestController
@RequiredArgsConstructor
public class JsonController {

    private final DatabaseService databaseService;

    @GetMapping()
    public ObjectNode getAllUsers(){
        return databaseService.getListUserInfo();
    }

    @GetMapping("/add")
    public ObjectNode addNewUserInfo(){
        return databaseService.saveOrUpdateUserInfo(UserInfo.getUserInfoWithRandomName());
    }

    @GetMapping("/delete/{id}")
    public ObjectNode addNewUserInfo(@PathVariable("id") int id){
        return databaseService.removeUserInfoById(id);
    }

    @GetMapping("/{id}")
    public ObjectNode getUserById(@PathVariable("id") int id) {
        return databaseService.getUserInfoById(id);
    }



}
