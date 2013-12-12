package components;

public abstract class ComponentFramework {

    // template pattern
    final void commandSequenceTemplateMethod()
    {
        action();
        recalibrate();
    }
    
    public abstract void action();
    public abstract Boolean recalibrate();
    
    public void init()
    {
        System.out.println(">> initializing...");
    }
}