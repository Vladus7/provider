package test.Command;

import com.vlad.model.command.ChangeTariffCommand;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChangeTariffCommandTest {

    @Test
    public void getUrl() {
        Assert.assertEquals("/change_tariff",new ChangeTariffCommand(null).getUrl());
    }
}