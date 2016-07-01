package com.pride.dungeon;

import com.pride.dungeon.managers.ResourceManager;
import com.pride.dungeon.model.Resources;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;

public class ResourcesTest {
    @Test
    public void loading_iscorrect() throws Exception {
        String json = "{\"chunks\" : [" +
                "{\"filename\" : \"filename1\", \"filetype\" : \"filetype1\", \"name\" : \"name1\"}, " +
                "{\"filename\" : \"filename2\", \"filetype\" : \"filetype2\", \"name\" : \"name2\"}" +
                "]}";
        InputStream inputStream = new ByteArrayInputStream(json.getBytes());
        Resources resources = ResourceManager.loadResourcesMeta(inputStream);
        assertTrue(new String("filename1").equals(resources.getResourceMeta("name1").first));
        assertTrue(new String("filetype2").equals(resources.getResourceMeta("name2").second));
    }
}
