package components;

public class CameraFilter extends CameraComponentDecorator {

    CameraComponent cameraComponent;
    
    public CameraFilter(CameraComponent cameraComponent)
    {
        this.cameraComponent = cameraComponent;
    }
    
    public String getDescription()
    {
        return cameraComponent.getDescription() + ", filter";
    }
    
}