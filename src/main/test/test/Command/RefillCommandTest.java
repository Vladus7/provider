package test.Command;

import com.vlad.model.command.RefillCommand;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RefillCommandTest {

    @Test
    public void getUrl() {
        Assert.assertEquals("/refill",new RefillCommand(null).getUrl());
    }
}