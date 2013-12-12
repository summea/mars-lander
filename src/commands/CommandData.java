package commands;
import java.util.ArrayList;

import observers.Observer;
import observers.Subject;

public class CommandData implements Subject {

    private ArrayList<Observer> observers;
    private String command;
    
    public CommandData()
    {
        observers = new ArrayList<Observer>();
    }
    
    public void commandChanged()
    {
        notifyObservers();
    }

    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer)observers.get(i);
            observer.update(command);
        }
    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }
    
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0)
            observers.remove(i);
    }

    public void setCommand(String command)
    {
        this.command = command;
        commandChanged();
    }
}