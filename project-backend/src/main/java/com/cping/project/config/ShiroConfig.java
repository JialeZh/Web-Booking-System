package com.cping.project.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // third
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);

        bean.setLoginUrl("/api/login");
        bean.setSuccessUrl("/api/calendar");
        bean.setUnauthorizedUrl("/noauth");
        Map<String, String> filterMap = new LinkedHashMap<>();
        //filterMap.put("user", "roles[admin]");
        //filterMap.put("/login","anon");
       // filterMap.put("/api/appointment/**","anon");
       // filterMap.put("/appointment/**","authc");
       // filterMap.put("/calendar/**", "authc");
       // filterMap.put("/**","authc");
        //filterMap.put("/welcome", "roles[admin,user]");


        System.out.println("Shiro filter factory execute");
        bean.setFilterChainDefinitionMap(filterMap);


        return bean;
    }
    //second
    //DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager securityManager(Realm myRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //SecurityUtils.setSecurityManager(securityManager);
        defaultWebSecurityManager.setRealm(myRealm);
        return defaultWebSecurityManager;
    }
    //first
    @Bean
    public MyRealm myRealm(){
        return new MyRealm();
    }
}
