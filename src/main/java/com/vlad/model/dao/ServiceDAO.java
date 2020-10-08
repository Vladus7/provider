package com.vlad.model.dao;

import com.vlad.model.dao.entity.Service;
import com.vlad.model.pool.Pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {
    
    private Pool dbPool;

    public ServiceDAO(Pool dbPool) {
        this.dbPool = dbPool;
    }

    public void createService(Service service){}

    public void setService(){}

    public Service getService(){
        Service service = new Service(null,null,null);
        return service;
    }

    private void deleteService(){}

    public List<Service> getAllService(){
        List<Service> services = new ArrayList();
        try {
            Connection connection = dbPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `provider`.`service`");
            while (resultSet.next()) {
                Service service = new Service(resultSet.getInt("id"),
                        resultSet.getString("service_name"),
                        resultSet.getString("image"),
                        resultSet.getString("description"));
                services.add(service);
            }
            dbPool.putConnection(connection);
        }catch (SQLException e){
            System.out.println(e);
        }
        return services;
    }
}
