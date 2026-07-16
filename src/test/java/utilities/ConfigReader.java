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

    /**
     * Get data from configuration.properties file
     *
     * @param key in the configuration.properties file
     * @return Data from the configuration.properties file
     */
    public static String getProperty(String key) {
        try {
            String response = properties.getProperty(key);

            if (response == null){
                Logger4j.error(key+" not found in configuration.properties");
                throw new RuntimeException(key+" not found in configuration.properties");
            }

            return response;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while reading the "+key+" | Error message is "+e.getMessage());
        }
    };
}
