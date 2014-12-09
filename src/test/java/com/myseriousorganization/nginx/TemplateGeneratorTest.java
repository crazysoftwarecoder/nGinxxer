package com.myseriousorganization.nginx;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by crazysoftwarecoder on 12/7/14.
 */
public class TemplateGeneratorTest {

    private static TemplateGenerator  templateGenerator = null;

    @BeforeClass
    public static void init() {
        templateGenerator = TemplateGenerator.getInstance();
    }

    @Test
    public void testNGinxOutput() {
        JSONObject jsonObject = TestUtil.getJSONObject("/com/myseriousorganization/nginx/sample_input.json");
        try {
            String nGinxConf = templateGenerator.generateTemplate(jsonObject);
            nGinxConf = nGinxConf.trim();
            List<String> generatedNginxConfLines = TestUtil.stringToLines(nGinxConf);
            String actualNGinxConf = IOUtils.toString(getClass().getResourceAsStream("/com/myseriousorganization/nginx/sample_nginx.conf"));
            actualNGinxConf = actualNGinxConf.trim();
            List<String> actualNGinxConfLines = TestUtil.stringToLines(actualNGinxConf);
            Patch patch = DiffUtils.diff(generatedNginxConfLines, actualNGinxConfLines);
            Assert.assertTrue("Source and generated nGinx conf have differences:=" + patch.getDeltas().size(), patch.getDeltas().size()==0);
        }
        catch (TemplateException | IOException e) {
            Assert.fail("Exception occurred while trying to generate template for sammple_input.json");
        }
    }
}
