public class Configuration {
    private static String API_KEY = "default-key-12345";
    private static String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
    private static int MAX_CONNECTIONS = 100;
    
    public static String getApiKey() {
        return API_KEY;
    }
    
    public static String getDatabaseUrl() {
        return DATABASE_URL;
    }
    
    public static int getMaxConnections() {
        return MAX_CONNECTIONS;
    }
}
