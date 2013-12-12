package commands;

public class MoveEastCommand implements Command {

    Move move;

    public MoveEastCommand(Move move)
    {
        this.move = move;
    }
    
    public void execute()
    {
        move.setCurrentDirection("E");
        System.out.println("!! robot moving east !!");
    }
}
