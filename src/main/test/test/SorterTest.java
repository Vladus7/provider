package test;

import com.vlad.model.Sorter;
import com.vlad.model.dao.entity.Tariff;
import com.vlad.model.dao.entity.User;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SorterTest {

    @Test
    public void sortUserBySpent() {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "login", "password", "permissions", 0.143, "name", "surname", "telephone", 0.0, true));
        list.add(new User(1, "login1", "password", "permissions", 0.143, "name", "surname", "telephone", 3.0, true));
        Sorter.sortUserBySpent(list);
        Assert.assertEquals("login" , list.get(0).getLogin());
    }

    @Test
    public void sortTariffByCost() {
        List<Tariff> list = new ArrayList<>();
        list.add(new Tariff(1, "tariff_name1",
                "description", 12));
        list.add(new Tariff(1, "tariff_name2",
                "description", 8));
        Sorter.sortTariffByCost(list);
        Assert.assertEquals("tariff_name2" , list.get(0).getName());
    }

    @Test
    public void sortTariffByName() {
        List<Tariff> list = new ArrayList<>();
        list.add(new Tariff(1, "tariff_name2",
                "description", 12));
        list.add(new Tariff(1, "tariff_name1",
                "description", 8));
        Sorter.sortTariffByName(list);
        Assert.assertEquals("tariff_name1" , list.get(0).getName());
    }

    @Test
    public void sortTariffByNameRevers() {
        List<Tariff> list = new ArrayList<>();
        list.add(new Tariff(1, "tariff_name1",
                "description", 12));
        list.add(new Tariff(1, "tariff_name2",
                "description", 8));
        Sorter.sortTariffByNameRevers(list);
        Assert.assertEquals("tariff_name2" , list.get(0).getName());
    }
}