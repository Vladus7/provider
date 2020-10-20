package test.Command;

import com.vlad.model.command.BuyCommand;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BuyCommandTest {

    @Test
    public void getUrl() {
        Assert.assertEquals("/buy", new BuyCommand(null, null).getUrl());
    }
}