package com.cping.project.controller;

import com.alibaba.fastjson.JSON;
import com.cping.project.bean.MainMenu;
import com.cping.project.dao.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MenuController {
    @Autowired
    MenuDao menuDao;

    @RequestMapping("/menus")
    public String getAllMenus(){
        System.out.println("visit success");
        HashMap<String, Object> data = new HashMap<>();
        List<MainMenu> menus = menuDao.getMenus();
        if (menus != null) {
            data.put("menus", menus);
            data.put("flag", 200);
        } else {
            data.put("flag", 404);
        }
        return JSON.toJSONString(data);
    }
}
