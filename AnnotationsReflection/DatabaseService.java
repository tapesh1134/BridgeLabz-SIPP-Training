public class DatabaseService {
    private String connectionString;
    
    public DatabaseService() {
        this.connectionString = "jdbc:mysql://localhost:3306/mydb";
    }
    
    public String getConnectionString() {
        return connectionString;
    }
    
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
    
    public void connect() {
        System.out.println("Connected to database: " + connectionString);
    }
}
