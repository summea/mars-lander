package commands;

public class MoveNorthCommand implements Command {
    
    Move move;

    public MoveNorthCommand(Move move)
    {
        this.move = move;
    }
    
    public void execute()
    {
        move.setCurrentDirection("N");
        System.out.println("!! robot moving north !!");
    }
}
