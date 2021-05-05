package com.cping.project.dao;

import com.cping.project.bean.MainMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {
    public List<MainMenu> getMenus();
}
