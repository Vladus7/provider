package test.Command;

import com.vlad.model.command.AccountListCommand;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountListCommandTest {

    @Test
    public void getUrl() {
        Assert.assertEquals("/accountList", new AccountListCommand(null).getUrl());
    }
}