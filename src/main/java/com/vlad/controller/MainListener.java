package com.vlad.controller;

import com.vlad.model.command.*;
import com.vlad.model.dao.ServiceDAO;
import com.vlad.model.dao.TariffDAO;
import com.vlad.model.dao.UserDAO;
import com.vlad.model.pool.DBPool;
import com.vlad.model.pool.Pool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.Map;

public class MainListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("ServletContextListener destroyed");
    }

    //Run this before web application is started
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        Map<String, Command> commandHashMap = new HashMap<>();

        Pool pool = new DBPool("jdbc:mysql://localhost:3306/provider?verifyServerCertificate=false&allowPublicKeyRetrieval=true&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC&user=vlad&password=vlad", 5);

        ServiceDAO serviceDAO = new ServiceDAO(pool);
        TariffDAO tariffDAO = new TariffDAO(pool);
        UserDAO userDAO = new UserDAO(pool);

        Command loginPage = new LoginPageCommand();
        Command loginCommand = new LoginCommand(userDAO);
        Command tariffCommand = new TariffCommand(tariffDAO);
        Command resource = new Resource();
        Command refill = new RefillCommand(userDAO);
        Command refillPage = new RefillPage();
        Command service= new Service(serviceDAO);
        Command registerPage = new RegisterPage();

        commandHashMap.put(resource.getUrl(), resource);
        commandHashMap.put(loginPage.getUrl(), loginPage);
        commandHashMap.put(loginCommand.getUrl(), loginCommand);
        commandHashMap.put(tariffCommand.getUrl(),tariffCommand);
        commandHashMap.put(refill.getUrl(), refill);
        commandHashMap.put(refillPage.getUrl(), refillPage);
        commandHashMap.put(service.getUrl(),service);
        commandHashMap.put(registerPage.getUrl(), registerPage);
        arg0.getServletContext().setAttribute("commandHashMap",commandHashMap);

        System.out.println("ServletContextListener started");
    }


}
