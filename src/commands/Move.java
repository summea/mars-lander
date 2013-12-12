package commands;

public class Move {
    
    private String currentDirection;
    
    public Move()
    {
    }
    
    public String getCurrentDirection()
    {
        return this.currentDirection;
    }
    
    public void moveNorthCommand()
    {
        this.currentDirection = "N";
    }
    
    public void setCurrentDirection(String direction)
    {
        this.currentDirection = direction;
    }
    
}
