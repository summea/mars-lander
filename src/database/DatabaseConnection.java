package database;
import java.util.List;
import java.util.TreeMap;

// eagerly-created instance
public class DatabaseConnection {

    //private static DatabaseConnection uniqueInstance = new DatabaseConnection();
    private volatile static DatabaseConnection uniqueInstance;
    private static String server;
    private static TreeMap<Integer, List<String>> database;
    
    public static TreeMap<Integer, List<String>> getDatabase() {
        return database;
    }
    
    public static DatabaseConnection getInstance()
    {
        if (uniqueInstance == null) {
            synchronized (DatabaseConnection.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new DatabaseConnection();
                }
            }
        }
        return uniqueInstance;
    }
    
    public static String getServer() {
        return server;
    }
    
    public static void setDatabase(TreeMap<Integer, List<String>> database) {
        DatabaseConnection.database = database;
    }

    public static void setServer(String server) {
        DatabaseConnection.server = server;
    }

    private DatabaseConnection()
    {
    }

    public boolean init(String newServer)
    {
        server = newServer;
        System.out.println("% connected to server: " + server);
        return true;
    }

    public boolean init(String newServer, TreeMap<Integer, List<String>> newDatabase)
    {
        server = newServer;
        database = newDatabase;
        System.out.println("% connected to server: " + server + " and database is specified");
        return true;
    }
    
}
