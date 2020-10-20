package test.Command;

import com.vlad.model.command.LogoutCommand;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogoutCommandTest {

    @Test
    public void getUrl() {
        Assert.assertEquals("/logout",new LogoutCommand().getUrl());

    }
}