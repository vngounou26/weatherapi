package com.hesias.weatherapi;

import com.hesias.weatherapi.configuration.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class WeatherapiApplication {

    public static void main(String[] args) {
        // run spring framework application

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
//        userService = context.getBean(IUserService.class);
//        userService.getUsers().forEach(System.out::println);

    }

}
