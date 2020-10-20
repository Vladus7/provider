package test.Command;

import com.vlad.model.command.ChangeServiceCommand;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChangeServiceCommandTest {

    @Test
    public void getUrl() {
        Assert.assertEquals("/change_service",new ChangeServiceCommand(null).getUrl());
    }
}