package menus;
//Note: Menu system referenced from Menu example in Head First Design Patterns book.

public abstract class MenuComponent {
    
    public void add(MenuComponent menuComponent)
    {
        throw new UnsupportedOperationException();
    }
    
    public MenuComponent getChild(int i)
    {
        throw new UnsupportedOperationException();
    }
    
    public String getDescription()
    {
        throw new UnsupportedOperationException();
    }
    
    public String getName()
    {
        throw new UnsupportedOperationException();
    }
    
    public String getPrice()
    {
        throw new UnsupportedOperationException();
    }
    
    public void print()
    {
        throw new UnsupportedOperationException();
    }
    
    public void remove(MenuComponent menuComponent)
    {
        throw new UnsupportedOperationException();
    }
    
}