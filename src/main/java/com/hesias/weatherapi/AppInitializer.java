package com.hesias.weatherapi;

import com.hesias.weatherapi.configuration.AppConfig;
import com.hesias.weatherapi.configuration.HibernateConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class AppInitializer extends AbstractDispatcherServletInitializer { //AbstractAnnotationConfigDispatcherServletInitializer {

//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[] { AppConfig.class, HibernateConfiguration.class };
//    }

//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return null;
//    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/*" };
    }

    @Override
    public WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.register(HibernateConfiguration.class);
        return context;
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}
