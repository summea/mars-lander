package menus;
//Note: Menu system referenced from Menu example in Head First Design Patterns book.
import java.util.ArrayList;
import java.util.Iterator;

public class Menu extends MenuComponent {
    ArrayList<MenuComponent> menuComponents = new ArrayList<MenuComponent>();
    String name;
    String description;
    
    public Menu(String name, String description)
    {
        this.name = name;
        this.description = description;
    }
    
    public void add(MenuComponent menuComponent)
    {
        menuComponents.add(menuComponent);
    }
    
    public MenuComponent getChild(int i)
    {
        return (MenuComponent)menuComponents.get(i);
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
        System.out.print("\n" + getName());
        System.out.println("   " + getDescription());
        System.out.println("----------------------------------------");
        
        // iterator pattern
        Iterator<MenuComponent> itr = menuComponents.iterator();
        while (itr.hasNext()) {
            MenuComponent menuComponent = (MenuComponent)itr.next();
            menuComponent.print();
        }
        
        System.out.println("----------------------------------------");
        System.out.println("> ");
    }
    
    public void remove(MenuComponent menuComponent)
    {
        menuComponents.remove(menuComponent);
    }
}