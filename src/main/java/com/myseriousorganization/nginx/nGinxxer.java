package com.myseriousorganization.nginx;


import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by crazysoftwarecoder on 11/30/14.
 */
public class nGinxxer {

    static String getJSONFromFile(String fileName) throws IOException, nGinxxerException {
        if ( (fileName==null) || (fileName.length()==0) ) {
            throw new nGinxxerException("fileName needs to be present");
        }

        File file = new File(fileName);
        if (!file.exists()) {
            throw new nGinxxerException("fileName:= " + fileName + " does not exist");
        }

        try (FileInputStream is = new FileInputStream(file)) {
            return IOUtils.toString(is);
        }
        catch (IOException ioException) {
            throw ioException;
        }
    }

    static JSONObject getJSONObjectFromString(String jsonString) {
        return new JSONObject(jsonString);
    }

    public static String getNGinxConfigurationString(String json) throws Exception {
        JSONObject jsonObject = getJSONObjectFromString(json);
        TemplateGenerator templateGenerator = TemplateGenerator.getInstance();
        String configuration = templateGenerator.generateTemplate(jsonObject);
        return configuration;
    }

    public static void main(String[] args) throws Exception {

        if (args.length<2) {
            System.out.println("Usage: java -jar nginxxer.jar input_json output_path");
            return;
        }

        String inputFile=args[0];
        String jsonString = getJSONFromFile(inputFile);
        JSONObject jsonObject = getJSONObjectFromString(jsonString);
        TemplateGenerator templateGenerator = TemplateGenerator.getInstance();
        String configuration = templateGenerator.generateTemplate(jsonObject);

        String outputFile=args[1];
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(configuration);
        }
        catch (IOException e) {
            throw e;
        }
    }
}
