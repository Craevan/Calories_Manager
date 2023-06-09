package com.crevan.manager;

import com.crevan.manager.repository.UserRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");

        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
        UserRepository userRepository = appCtx.getBean(UserRepository.class);
        userRepository.getAll();
        appCtx.close();
    }
}
