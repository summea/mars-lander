package commands;

public class MoveNorthEastCommand implements Command {
    
    Move move;

    public MoveNorthEastCommand(Move move)
    {
        this.move = move;
    }
    
    public void execute()
    {
        move.setCurrentDirection("NE");
        System.out.println("!! robot moving northeast !!");
    }
}