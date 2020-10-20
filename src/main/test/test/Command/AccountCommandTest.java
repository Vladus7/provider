package test.Command;

import com.vlad.model.command.AccountCommand;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountCommandTest {

    @Test
    public void getUrl() {
        Assert.assertEquals("/account", new AccountCommand(null, null).getUrl());
    }
}