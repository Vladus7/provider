package test.Command;

import com.vlad.model.command.ResourceCommand;
import org.junit.Assert;
import org.junit.Test;


public class ResourceCommandTest {

    @Test
    public void getUrl() {
        Assert.assertEquals("/resource",new ResourceCommand().getUrl());
    }
}