import java.lang.reflect.Field;

public class AccessAndModifyStaticFields {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Configuration.class;
            
            System.out.println("Original values:");
            System.out.println("API_KEY: " + Configuration.getApiKey());
            System.out.println("DATABASE_URL: " + Configuration.getDatabaseUrl());
            System.out.println("MAX_CONNECTIONS: " + Configuration.getMaxConnections());
            
            Field apiKeyField = clazz.getDeclaredField("API_KEY");
            apiKeyField.setAccessible(true);
            
            String newApiKey = "new-secret-key-67890";
            apiKeyField.set(null, newApiKey);
            
            System.out.println("\nAfter modification:");
            System.out.println("API_KEY: " + Configuration.getApiKey());
            
            Field databaseUrlField = clazz.getDeclaredField("DATABASE_URL");
            databaseUrlField.setAccessible(true);
            
            String newDatabaseUrl = "jdbc:postgresql://localhost:5432/newdb";
            databaseUrlField.set(null, newDatabaseUrl);
            
            System.out.println("DATABASE_URL: " + Configuration.getDatabaseUrl());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
