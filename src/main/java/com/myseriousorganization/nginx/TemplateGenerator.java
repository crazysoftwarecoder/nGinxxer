package com.myseriousorganization.nginx;

import com.google.common.base.Preconditions;
import freemarker.template.*;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.*;
import java.util.Map;

/**
 * This is a singleton class that can be used to generate templates at will.
 */
public class TemplateGenerator {

    private Template nGinxTemplate = null;

    private static TemplateGenerator singleton = new TemplateGenerator();

    private JSONToHashMap jsonToHashMap = new JSONToHashMap();

    private Configuration cfg = new Configuration(Configuration.VERSION_2_3_21);

    public static TemplateGenerator getInstance() {
        return singleton;
    }

    private TemplateGenerator() {
        try ( InputStream input = getClass().getResourceAsStream("/com/myseriousorganization/nginx/template/nginx.conf") ) {
            String template = IOUtils.toString(input);
            ObjectWrapper wrapper = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_21).build();
            cfg.setObjectWrapper(wrapper);
            nGinxTemplate = new Template("templateName", new StringReader(template), cfg);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Given a json object this returns a string representation of the nGinx configuration file
     *
     * @param jsonObject
     * @return
     * @throws TemplateException
     * @throws IOException
     */
    public String generateTemplate(JSONObject jsonObject) throws TemplateException, IOException {
        return generateTemplate(jsonToHashMap.jsonToMap(jsonObject));
    }

    private String generateTemplate(Map<String, Object> dataModel) throws TemplateException, IOException {
        Preconditions.checkNotNull(dataModel);

        StringWriter out = new StringWriter();
        try {
            this.nGinxTemplate.process(dataModel, out);
        }
        catch (TemplateException | IOException e) {
            throw e;
        }
        return out.getBuffer().toString();
    }
}
