package test.Command;

import com.vlad.model.command.RegisterCommand;
import org.junit.Assert;
import org.junit.Test;


public class RegisterCommandTest {

    @Test
    public void getUrl() {
        Assert.assertEquals("/register",new RegisterCommand(null).getUrl());
    }
}