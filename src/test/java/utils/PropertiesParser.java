package utils;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesParser {
    public String[] parsProperties(String key) {
        FileInputStream propertyFile;
        Properties property = new Properties();

        try {
            propertyFile = new FileInputStream("src/test/resources/testData.properties");
            property.load(propertyFile);
        } catch (IOException e) {
            System.err.println("ERROR! File of 'testData' is not found.");
        }
        String dataString = property.getProperty(key);
        return StringUtils.splitByWholeSeparatorPreserveAllTokens(dataString, ";");
    }
}
