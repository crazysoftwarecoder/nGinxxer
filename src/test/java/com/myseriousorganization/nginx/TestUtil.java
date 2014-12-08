package com.myseriousorganization.nginx;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by crazysoftwarecoder on 12/7/14.
 */
public class TestUtil {

    static Map<String, Object> getMapFromJSON(String classPath, JSONToHashMap obj) {
        try (InputStream in = TestUtil.class.getResourceAsStream(classPath)) {
            Assert.assertNotNull("Input Stream is null for classPath:=" + classPath, in);
            String jsonString = IOUtils.toString(in);
            JSONObject jsonObject = nGinxxer.getJSONObjectFromString(jsonString);
            Map<String, Object> hashMap = obj.jsonToMap(jsonObject);
            return hashMap;
        }
        catch (IOException e) {
            Assert.assertTrue("Exception occurred while trying to fetch:=" + classPath, false);
            return null;
        }
    }

    static JSONObject getJSONObject(String classPath) {
        try (InputStream in = TestUtil.class.getResourceAsStream(classPath)) {
            Assert.assertNotNull("Input Stream is null for classPath:=" + classPath, in);
            String jsonString = IOUtils.toString(in);
            JSONObject jsonObject = nGinxxer.getJSONObjectFromString(jsonString);
            return jsonObject;
        }
        catch (IOException e) {
            Assert.assertTrue("Exception occurred while trying to fetch:=" + classPath, false);
            return null;
        }
    }

}
