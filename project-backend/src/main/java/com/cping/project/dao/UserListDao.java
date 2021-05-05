package com.cping.project.dao;
import com.cping.project.bean.UserList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserListDao {

    public List<UserList> getAllUser();

    public int addUser(UserList userList);


    public int deleteUser(int user_id);

    public UserList getUpdateUser(int user_id);

    public int editUser(UserList userList);


}
