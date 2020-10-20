package test.Command;

import com.vlad.model.command.DeleteTariffCommand;
import org.junit.Assert;
import org.junit.Test;

public class DeleteTariffCommandTest {

    @Test
    public void getUrl() {
        Assert.assertEquals("/delete_tariff", new DeleteTariffCommand(null).getUrl());
    }
}