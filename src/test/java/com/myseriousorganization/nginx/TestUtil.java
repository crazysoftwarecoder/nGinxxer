package com.myseriousorganization.nginx;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
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

    static List<String> stringToLines(String inputString) {
        List<String> lines = new LinkedList<String>();
        String line = "";
        try {
            BufferedReader in = new BufferedReader(new StringReader(inputString));
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }


    static String getJSONString(String classPath) {
        try (InputStream in = TestUtil.class.getResourceAsStream(classPath)) {
            Assert.assertNotNull("Input Stream is null for classPath:=" + classPath, in);
            String jsonString = IOUtils.toString(in);
            return jsonString;
        }
        catch (IOException e) {
            Assert.assertTrue("Exception occurred while trying to fetch:=" + classPath, false);
            return null;
        }
    }
}
