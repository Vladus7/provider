package test.Command;

import com.vlad.model.command.CreateTariffCommand;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateTariffCommandTest {

    @Test
    public void getUrl() {
        Assert.assertEquals("/create_tariff", new CreateTariffCommand(null).getUrl());
    }
}