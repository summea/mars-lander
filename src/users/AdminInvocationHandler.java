package users;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AdminInvocationHandler implements InvocationHandler {
    
    User user;

    public AdminInvocationHandler(User user)
    {
        this.user = user;
    }
    
    public Object invoke(Object proxy, Method method, Object[] args)
            throws IllegalAccessException {
        try {            
            return method.invoke(user,  args);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;    // for safety
    }

}
