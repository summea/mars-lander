package components;

public class CameraLens extends CameraComponentDecorator {

    CameraComponent cameraComponent;
    
    public CameraLens(CameraComponent cameraComponent)
    {
        this.cameraComponent = cameraComponent;
    }
    
    public String getDescription()
    {
        return cameraComponent.getDescription() + ", lens";
    }
    
}