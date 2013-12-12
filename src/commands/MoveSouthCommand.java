package commands;

public class MoveSouthCommand implements Command {

    Move move;

    public MoveSouthCommand(Move move)
    {
        this.move = move;
    }
    
    public void execute()
    {
        move.setCurrentDirection("S");
        System.out.println("!! robot moving south !!");
    }
}
