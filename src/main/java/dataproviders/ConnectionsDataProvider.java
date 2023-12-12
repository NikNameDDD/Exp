package dataproviders;

import java.util.Properties;

public class ConnectionsDataProvider {

    private static ConnectionsDataProvider instance;

    private static final String CONFIG_FILE_URL = "src/main/resources/connections.properties";

    private final Properties CONFIG_PROPERTIES = PropertyReader.getInstance().loadProperties(CONFIG_FILE_URL);

    private ConnectionsDataProvider() {
    }

    public static ConnectionsDataProvider getInstance() {
        if (instance == null) {
            instance = new ConnectionsDataProvider();
        }
        return instance;
    }

    public String getDataBaseHost() {
        return CONFIG_PROPERTIES.getProperty("dbHost");
    }

    public String getDataBasePort() {
        return CONFIG_PROPERTIES.getProperty("dbPort");
    }

    public String getDataBaseUser() {
        return CONFIG_PROPERTIES.getProperty("dbUser");
    }

    public String getDataBasePassword() {
        return CONFIG_PROPERTIES.getProperty("dbPass");
    }

    public String getDataBaseName() {
        return CONFIG_PROPERTIES.getProperty("dbName");
    }

    public String getBaseUri() {
        return CONFIG_PROPERTIES.getProperty("baseUri");
    }
}
