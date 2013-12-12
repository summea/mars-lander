package states;

public interface State {
    public void available();
    public void getCommand();
    public void processCommand();
    public void runCommand();
}
