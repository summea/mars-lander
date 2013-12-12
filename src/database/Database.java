package database;
import java.util.List;
import java.util.TreeMap;

public class Database {
    
    private TreeMap<Integer, List<String>> database = new TreeMap<Integer, List<String>>();
    private Integer currentKeyId;
    
    public Database()
    {
        this.setCurrentKeyId(0);    // init key id
    }
    
    public Integer getCurrentKeyId() {
        return currentKeyId;
    }
    
    public TreeMap<Integer, List<String>> getDB()
    {
        return this.database;
    }

    public void insert(List<String> newValues)
    {
        this.setCurrentKeyId(this.currentKeyId+1);
        this.database.put(this.getCurrentKeyId(), newValues);
    }
    
    public List<String> select(Integer key)
    {
        return this.database.get(key);
    }
    
    public TreeMap<Integer, List<String>> selectAll()
    {
        return this.database;
    }
    
    public void setCurrentKeyId(Integer currentKeyId) {
        this.currentKeyId = currentKeyId;
    }
    
}