package test.Command;

import com.vlad.model.command.BannedCommand;
import org.junit.Assert;
import org.junit.Test;



public class BannedCommandTest {

    @Test
    public void getUrl() {
        Assert.assertEquals("/banned", new BannedCommand(null).getUrl());
    }
}