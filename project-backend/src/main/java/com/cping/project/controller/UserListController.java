package com.cping.project.controller;
import com.cping.project.bean.UserList;
import com.cping.project.common.Result;
import com.cping.project.dao.UserListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserListController {
    @Autowired
    UserListDao userlistDao;

    @PostMapping("userList")
    public Result insertAddUser(@RequestBody UserList userList) {
        int i = userlistDao.addUser(userList);
        System.out.println(i);
        return Result.succ(200, "success", null);
    }
    @GetMapping("allUser")
    public Result getAllUser() {
        List<UserList> allUser = userlistDao.getAllUser();
        return Result.succ(allUser);
    }

    @DeleteMapping("/deleteUser/{user_id}")
    public String deleteUser(@PathVariable("user_id") int user_id) {
        int i = userlistDao.deleteUser(user_id);
        return 1 > 0 ? "success" : "error";

    }

    @RequestMapping("/getUser/{user_id}")
    public Result getUpdateUser(@PathVariable("user_id") int user_id){
        UserList userList = userlistDao.getUpdateUser(user_id);
        return Result.succ(userList);
    }

    @PutMapping("/editUser")
    public String editUser(@RequestBody UserList userList) {
        int i = userlistDao.editUser(userList);
        return i > 0 ? "success" : "error";
    }

}
