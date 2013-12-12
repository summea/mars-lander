package menus;
// Note: Menu system referenced from Menu example in Head First Design Patterns book.

public class MenuSystem {
    MenuComponent allMenus;
    
    public MenuSystem(MenuComponent allMenus)
    {
        this.allMenus = allMenus;
    }
    
    public void printMenu()
    {
        allMenus.print();
    }
}