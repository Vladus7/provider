package com.vlad.model.dao.entity;

import com.vlad.model.dao.TariffDAO;

public class Tariff implements Comparable<Tariff> {
    private int id;
    private String name;
    private String description;
    private double price;

    public Tariff(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Tariff(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public static Tariff createTariff(String name, String description, double prise, TariffDAO tariffDAO) {
        Tariff tariff = new Tariff(name, description, prise);
        tariffDAO.createTariff(tariff);
        return tariff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


    @Override
    public int compareTo(Tariff o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "id=" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", prise='" + price + '\'' +
                '}';
    }

}
