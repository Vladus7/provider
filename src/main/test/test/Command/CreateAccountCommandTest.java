package test.Command;

import com.vlad.model.command.CreateAccountCommand;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateAccountCommandTest {

    @Test
    public void getUrl() {
        Assert.assertEquals("/create_account", new CreateAccountCommand(null).getUrl());
    }
}