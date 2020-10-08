package com.vlad.model;

import com.vlad.model.dao.entity.Tariff;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Sorter {
    public static List<Tariff> sortTariffByCost(List<Tariff> tariffs){
        tariffs.sort(Comparator.comparing(Tariff::getPrice));
        return tariffs;
    }

    public static List<Tariff> sortTariffByName(List<Tariff> tariffs){
        tariffs.sort(Tariff::compareTo);
        return tariffs;

    }

    public static List<Tariff> sortTariffByNameRevers(List<Tariff> tariffs){
        tariffs.sort(Tariff::compareTo);
        tariffs.sort(Comparator.reverseOrder());
        return tariffs;
    }
}
