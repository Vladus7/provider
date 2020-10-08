package com.vlad.model.dao.entity;

import com.vlad.model.dao.ServiceDAO;

public class Service {
    private int id;
    private String name;
    private String image;
    private String description;

    public Service(String name, String image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public Service(int id, String name, String image, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public Service createService(String name, String image, String description , ServiceDAO serviceDAO){
        Service service = new Service(name,image,description);
        serviceDAO.createService(service);
        return service;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
