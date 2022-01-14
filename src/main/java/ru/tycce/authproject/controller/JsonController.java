package ru.tycce.authproject.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.tycce.authproject.database.DatabaseService;
import ru.tycce.authproject.database.entity.UserInfo;

@RestController
@RequiredArgsConstructor
public class JsonController {

    private final DatabaseService databaseService;

    @GetMapping()
    public ResponseEntity<?> getResponse(){
        return ResponseEntity.ok(databaseService.getAllUsers());
    }

    @GetMapping("/add")
    public ResponseEntity<?> addNewUserInfo(){
        return ResponseEntity.ok(databaseService.saveOrUpdateUserInfo(UserInfo.getUserInfoWithRandomName()));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> addNewUserInfo(@PathVariable("id") int id){
        return ResponseEntity.ok(databaseService.removeUserInfoById(id));
    }



}
