package test.Command;

import com.vlad.model.command.CreateServiceCommand;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateServiceCommandTest {

    @Test
    public void getUrl() {
        Assert.assertEquals("/create_service", new CreateServiceCommand(null).getUrl());
    }
}