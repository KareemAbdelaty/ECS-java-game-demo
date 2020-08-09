
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.system.AppSettings;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
 class Main extends SimpleApplication {
   
     private String state = "MainMenu" ;
     private float width ;
     private float height;
     private boolean changeState = false;
     MainMenu mmenu;
     Game game;

    public static void main(String[] args) {
        Main app = new Main();
        AppSettings settings = new AppSettings(true);
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode[] modes = device.getDisplayModes();
        int i=0; // note: there are usually several, let's pick the first
        settings.setResolution(modes[i].getWidth(),modes[i].getHeight());
        settings.setFrameRate(60);
        settings.setTitle("Animation");
        app.setSettings(settings);
        
        app.start();
    }

    @Override
    public void simpleInitApp() {
        this.width = settings.getWidth();
        this.height = settings.getHeight();
     
        

        //        setup camera for 2D games
        cam.setParallelProjection(true);
        cam.setLocation(new Vector3f(0,0,0.5f));
        getFlyByCamera().setEnabled(false);
 
//        turn off stats view (you can leave it on, if you want)
        setDisplayStatView(false);
        setDisplayFps(false);      
        MainMenu mmenu = new MainMenu(width,height);
        Game game = new Game(width,height);
 //launch main menu	
       stateManager.attach(mmenu);
       game.setEnabled(false);
        stateManager.attach(game);
        }
  

     
        

  
    

    @Override
    public void simpleUpdate(float tpf) {

       

    }
    

    
    
 
}
