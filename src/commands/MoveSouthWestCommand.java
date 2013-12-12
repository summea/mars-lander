package commands;

public class MoveSouthWestCommand implements Command {

    Move move;

    public MoveSouthWestCommand(Move move)
    {
        this.move = move;
    }
    
    public void execute()
    {
        move.setCurrentDirection("SW");
        System.out.println("!! robot moving southwest !!");
    }
}
