package users;

public class UserImpl implements User {

    String username;
    String permission;
    
    public String getName() {
        return username;
    }

    public String getPermissions() {
        return permission;
    }

    public void setName(String username) {
        this.username = username;
    }

    public void setPermissions(String permission) {
        this.permission = permission;
    }

}