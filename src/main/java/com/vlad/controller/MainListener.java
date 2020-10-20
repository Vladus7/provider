package com.vlad.controller;

import com.vlad.model.command.*;
import com.vlad.model.dao.ServiceDAO;
import com.vlad.model.dao.TariffDAO;
import com.vlad.model.dao.UserDAO;
import com.vlad.model.dao.OrderDAO;
import com.vlad.model.pool.DBPool;
import com.vlad.model.pool.Pool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Initialization (pool, dao, controller commands).
 *
 * @author V.But
 */
public class MainListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("ServletContextListener destroyed");
    }

    //Run before web application is started
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        Map<String, Command> commandHashMap = new HashMap<>();

        //pool
        Pool pool = new DBPool("jdbc:mysql://localhost:3306/provider?verifyServerCertificate=false&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC&user=vlad&password=vlad", 5);

        //dao
        ServiceDAO serviceDAO = new ServiceDAO(pool);
        TariffDAO tariffDAO = new TariffDAO(pool);
        UserDAO userDAO = new UserDAO(pool);
        OrderDAO orderDAO = new OrderDAO(pool);

        //command
        Command loginCommand = new LoginCommand(userDAO);
        Command tariffCommand = new TariffCommand(tariffDAO);
        Command resource = new ResourceCommand();
        Command refill = new RefillCommand(userDAO);
        Command service = new ServiceCommand(serviceDAO);
        Command accountCommand = new AccountCommand(tariffDAO, userDAO);
        Command registerCommand = new RegisterCommand(userDAO);
        Command downloadCommand = new DownloadCommand(userDAO, tariffDAO, orderDAO);
        Command buyCommand = new BuyCommand(tariffDAO, orderDAO);
        Command deleteServiceCommand = new DeleteServiceCommand(serviceDAO);
        Command deleteTariffCommand = new DeleteTariffCommand(tariffDAO);
        Command createServiceCommand = new CreateServiceCommand(serviceDAO);
        Command changeServiceCommand = new ChangeServiceCommand(serviceDAO);
        Command createTariffCommand = new CreateTariffCommand(tariffDAO);
        Command changeTariffCommand = new ChangeTariffCommand(tariffDAO);
        Command createAccountCommand = new CreateAccountCommand(userDAO);
        Command accountListCommand = new AccountListCommand(userDAO);
        Command bannedCommand = new BannedCommand(userDAO);
        Command logoutCommand = new LogoutCommand();

        //put command in hashMap
        commandHashMap.put(logoutCommand.getUrl(), logoutCommand);
        commandHashMap.put(bannedCommand.getUrl(), bannedCommand);
        commandHashMap.put(createAccountCommand.getUrl(), createAccountCommand);
        commandHashMap.put(accountListCommand.getUrl(), accountListCommand);
        commandHashMap.put(changeTariffCommand.getUrl(), changeTariffCommand);
        commandHashMap.put(createTariffCommand.getUrl(), createTariffCommand);
        commandHashMap.put(changeServiceCommand.getUrl(), changeServiceCommand);
        commandHashMap.put(createServiceCommand.getUrl(), createServiceCommand);
        commandHashMap.put(deleteTariffCommand.getUrl(), deleteTariffCommand);
        commandHashMap.put(deleteServiceCommand.getUrl(), deleteServiceCommand);
        commandHashMap.put(downloadCommand.getUrl(), downloadCommand);
        commandHashMap.put(buyCommand.getUrl(), buyCommand);
        commandHashMap.put(resource.getUrl(), resource);
        commandHashMap.put(loginCommand.getUrl(), loginCommand);
        commandHashMap.put(tariffCommand.getUrl(), tariffCommand);
        commandHashMap.put(refill.getUrl(), refill);
        commandHashMap.put(service.getUrl(), service);
        commandHashMap.put(accountCommand.getUrl(), accountCommand);
        commandHashMap.put(registerCommand.getUrl(), registerCommand);

        arg0.getServletContext().setAttribute("commandHashMap", commandHashMap);
    }
}
