package com.crevan.manager;

import com.crevan.manager.model.Role;
import com.crevan.manager.model.User;
import com.crevan.manager.web.user.AdminRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserRestController = appCtx.getBean(AdminRestController.class);
            adminUserRestController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));
        }
    }
}
