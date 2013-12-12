package commands;
import observers.DisplayElement;
import observers.Observer;
import observers.Subject;

public class CurrentCommandDisplay implements Observer, DisplayElement {
    
    private String command;
    private Subject commandData;

    public CurrentCommandDisplay(Subject commandData)
    {
        this.setCommandData(commandData);
        commandData.registerObserver(this);
    }
    
    public void display() {
        System.out.println("<current command:> " + command + "\n");
    }

    public Subject getCommandData() {
        return commandData;
    }

    public void setCommandData(Subject commandData) {
        this.commandData = commandData;
    }

    public void update(String command)
    {
        this.command = command;
        display();
    }

}
