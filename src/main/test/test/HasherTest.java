package test;

import com.vlad.model.Hasher;
import org.junit.Assert;
import org.junit.Test;

public class HasherTest {

    @Test
    public void getHash() {
        Assert.assertEquals("912ec803b2ce49e4a541068d495ab570",Hasher.getHash("asdf","MD5"));
    }
}