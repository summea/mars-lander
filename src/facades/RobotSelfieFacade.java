package facades;
import components.CameraComponent;
import components.FlashlightComponent;
import components.ProjectorComponent;

public class RobotSelfieFacade {
    CameraComponent camera;
    FlashlightComponent flashlight;
    ProjectorComponent projector;
    
    public RobotSelfieFacade(  CameraComponent camera,
                        FlashlightComponent flashlight,
                        ProjectorComponent projector )
    {
        this.camera = camera;
        this.flashlight = flashlight;
        this.projector = projector;
    }
    
    public void takeSelfie()
    {
        System.out.println("prepping for robot selfie...");
        projector.on();
        projector.projectFakeBackground("MARS");
        flashlight.on();
        flashlight.rotate(180);
        camera.on();
        camera.rotate(180);
        camera.action();    // camera takes photo
        camera.sendPhoto();
        camera.off();
        flashlight.off();
        projector.off();
    }
}
