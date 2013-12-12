package commands;

public class RobotRemoteControl {
    
    Command[] allCommands;
    
    public RobotRemoteControl()
    {
        allCommands = new Command[10];
        
        Command noCommand = new NoCommand();
        for (int i = 0; i < 10; i++) {
            allCommands[i] = noCommand;
        }
    }
    
    public void buttonWasPressed(int slot)
    {
        allCommands[slot].execute();
    }
    
    public void setCommand(int slot, Command command)
    {
        allCommands[slot] = command;
    }
    
}