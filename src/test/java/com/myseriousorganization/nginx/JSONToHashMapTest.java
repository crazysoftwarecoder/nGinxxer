package com.myseriousorganization.nginx;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

/**
 * Created by crazysoftwarecoder on 12/7/14.
 */
public class JSONToHashMapTest {

    private static JSONToHashMap jsonToHashMap;

    @BeforeClass
    public static void init() {
        jsonToHashMap = new JSONToHashMap();
    }


    @Test
    public void testConvertSimpleJSONToHashMap() {
        Map<String, Object> hashMap = TestUtil.getMapFromJSON("/com/myseriousorganization/nginx/simple.json", jsonToHashMap);
        Assert.assertTrue("Size must be 4", hashMap.size()==4);
        Assert.assertTrue("Name must equal crazysoftwarecoder", hashMap.get("name").equals("crazysoftwarecoder"));
        Assert.assertTrue("coding_skills must equal ninja", hashMap.get("coding_skills").equals("ninja"));
        Assert.assertTrue("interview_skills must equal wham!", hashMap.get("interview_skills").equals("wham!"));
    }
}
