package com.eladmin.modules.initialization;

import com.eladmin.modules.system.domain.User;
import com.eladmin.modules.system.repository.UserRepository;
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
public class Manager {
    private static final Logger log = LoggerFactory.getLogger(Manager.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void InitialManager() throws Exception {
        String InitialManager = Config.token().getManager();
        //如果用户不存在
        if (userRepository.findByUsername(InitialManager) == null) {
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
            userRepository.save(manager);
            log.info("初始化"+Config.token().getNickName()+"成功");
        }

    }



}
