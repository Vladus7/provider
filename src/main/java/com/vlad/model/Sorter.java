package com.vlad.model;

import com.vlad.model.dao.entity.Tariff;
import com.vlad.model.dao.entity.User;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Sorter {

    /**
     * Sort user by spent.
     *
     * @param users
     *
     */
    public static List<User> sortUserBySpent(List<User> users){
        users.sort(Comparator.comparing(User::getSpent));
        return users;
    }

    /**
     * Sort tariffs by Cost.
     *
     * @param tariffs
     *
     */
    public static List<Tariff> sortTariffByCost(List<Tariff> tariffs) {
        tariffs.sort(Comparator.comparing(Tariff::getPrice));
        return tariffs;
    }

    /**
     * Sort tariffs by Name.
     *
     * @param tariffs
     *
     */
    public static List<Tariff> sortTariffByName(List<Tariff> tariffs) {
        tariffs.sort(Tariff::compareTo);
        return tariffs;

    }

    /**
     * Sort tariffs by Name revers.
     *
     * @param tariffs
     *
     */
    public static List<Tariff> sortTariffByNameRevers(List<Tariff> tariffs) {
        tariffs.sort(Tariff::compareTo);
        tariffs.sort(Comparator.reverseOrder());
        return tariffs;
    }
}
