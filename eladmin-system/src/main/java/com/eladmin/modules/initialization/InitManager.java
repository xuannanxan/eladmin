package com.eladmin.modules.initialization;

import com.eladmin.modules.system.domain.User;
import com.eladmin.modules.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.eladmin.jsonconfig.Config;
import javax.annotation.PostConstruct;


/**
     初始化用户
    * */

@RequiredArgsConstructor
@Component
public class InitManager {
    private static final Logger log = LoggerFactory.getLogger(InitManager.class);
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void InitialManager() throws Exception {
        String InitialManager = Config.token().getManager();
        if (userService.findUser(InitialManager) == null) {
            log.info(Config.token().getNickName()+"不存在，开始进行初始化...");
            User manager = new  User();
            manager.setUsername(InitialManager);
            manager.setPassword(passwordEncoder.encode(Config.token().getPassword()));
            manager.setNickName(Config.token().getNickName());
            manager.setIsAdmin(true);
            manager.setCreateBy("system");
            manager.setEnabled(true);
            manager.setEmail(Config.token().getEmail());
            manager.setPhone(Config.token().getPhone());
            userService.create(manager);
            log.info("初始化"+Config.token().getNickName()+"成功");
        }

    }



}
