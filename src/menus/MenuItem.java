package menus;
//Note: Menu system referenced from Menu example in Head First Design Patterns book.

public class MenuItem extends MenuComponent {
    String name;
    String description;
    
    public MenuItem(String name, String description)
    {
        this.name = name;
        this.description = description;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void print()
    {
        System.out.print(getName());
        if (!getDescription().isEmpty())
            System.out.print (" (" + getDescription() + ")");
        System.out.println();
    }
}