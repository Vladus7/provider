package test.Command;

import com.vlad.model.command.DownloadCommand;
import org.junit.Assert;
import org.junit.Test;

public class DownloadCommandTest {

    @Test
    public void getUrl() {
        Assert.assertEquals("/download", new DownloadCommand(null,null, null).getUrl());
    }
}