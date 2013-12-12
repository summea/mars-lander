package commands;

public class MoveNorthWestCommand implements Command {

    Move move;

    public MoveNorthWestCommand(Move move)
    {
        this.move = move;
    }
    
    public void execute()
    {
        move.setCurrentDirection("NW");
        System.out.println("!! robot moving northwest !!");
    }
}
