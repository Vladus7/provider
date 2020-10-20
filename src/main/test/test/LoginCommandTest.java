package test;

import com.vlad.model.command.LoginCommand;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginCommandTest {

    @Test
    public void getUrl() {
        Assert.assertEquals("/login",new LoginCommand(null).getUrl());
    }
}