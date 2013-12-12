package users;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PresidentInvocationHandler implements InvocationHandler {
    
    User user;

    public PresidentInvocationHandler(User user)
    {
        this.user = user;
    }
    
    public Object invoke(Object proxy, Method method, Object[] args)
            throws IllegalAccessException {
        try {
            if (method.getName().equals("auth")) {
                return method.invoke(user,  args);
            } else if (method.getName().equals("getName")) {
                return method.invoke(user,  args);
            } else {
                throw new IllegalAccessException();
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;    // for safety
    }

}