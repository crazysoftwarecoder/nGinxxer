package com.myseriousorganization.nginx;

import difflib.DiffUtils;
import difflib.Patch;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by crazysoftwarecoder on 12/8/14.
 */
public class nGinxxerTest {

    @Test
    public void testForSimpleJSON() {
        String sampleInputFile = "/com/myseriousorganization/nginx/sample_input.json";
        String jsonString = TestUtil.getJSONString(sampleInputFile);
        try {
            String generatednGinxConf = nGinxxer.getNGinxConfigurationString(jsonString);
            generatednGinxConf = generatednGinxConf.trim();
            List<String> generatednGinxConfLines = TestUtil.stringToLines(generatednGinxConf);
            String expectednGinxConf = IOUtils.toString(getClass().getResourceAsStream("/com/myseriousorganization/nginx/sample_nginx.conf"));
            expectednGinxConf = expectednGinxConf.trim();
            List<String> expectednGinxConfLines = TestUtil.stringToLines(expectednGinxConf);
            Patch patch = DiffUtils.diff(generatednGinxConfLines, expectednGinxConfLines);
            Assert.assertTrue("More than one patch differences between generated and actual nginx.conf", patch.getDeltas().size()==0);
        }
        catch (Exception e) {
            Assert.assertTrue("Exception occurred while trying to read (" + sampleInputFile + "):=" + e.getMessage(), false);
        }
    }
}
