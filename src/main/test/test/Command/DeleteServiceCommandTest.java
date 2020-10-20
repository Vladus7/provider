package test.Command;

import com.vlad.model.command.DeleteServiceCommand;
import org.junit.Assert;
import org.junit.Test;

public class DeleteServiceCommandTest {

    @Test
    public void getUrl() {
        Assert.assertEquals("/delete_service", new DeleteServiceCommand(null).getUrl());

    }
}