package commands;

public class MoveWestCommand implements Command {

    Move move;

    public MoveWestCommand(Move move)
    {
        this.move = move;
    }
    
    public void execute()
    {
        move.setCurrentDirection("W");
        System.out.println("!! robot moving west !!");
    }
}
