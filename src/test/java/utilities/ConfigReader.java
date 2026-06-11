package utilities;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    public static Properties properties;

    static {
        String path = "src/test/resources/configuration.properties";
        
        try{
            FileInputStream fileInputStream = new FileInputStream(path);

            properties = new Properties();
            properties.load(fileInputStream);

            fileInputStream.close();
        }catch (Exception e){
            throw new RuntimeException("An error occurred while reading the configuration.properties file. ERROR: "+ e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
