package commands;

public class MoveSouthEastCommand implements Command {
    
    Move move;

    public MoveSouthEastCommand(Move move)
    {
        this.move = move;
    }
    
    public void execute()
    {
        move.setCurrentDirection("SE");
        System.out.println("!! robot moving southeast !!");
    }
}