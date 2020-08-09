/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;


import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.jme3.app.state.BaseAppState;
import com.jme3.input.MouseInput;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector2f;
import com.jme3.ui.Picture;

/**
 *
 * @author karim
 */
class MainMenu extends BaseAppState {
    private SimpleApplication app;
    private final float width;
    private final float height;
    private final  Picture  background = new Picture("background");
    private final  Picture start = new Picture("start");
    private final Picture exit = new Picture("exit");
    private  String state = "MainMenu";
    private boolean changeState = false;
    Vector2f mousePos;
       

    public MainMenu(float w,float h){
        width = w;
        height = h;
    }
   
    @Override 
    protected void initialize(Application app){
        this.app = (SimpleApplication) app;
            
 
        background.setImage(this.app.getAssetManager(), "Background/NewBackground.png", true);
        background.setHeight(height);
        background.setWidth(width);
        background.move(0f, 0f, -1);
        start.setImage(this.app.getAssetManager(),"MainMenu/StartButton.png",true);
        start.setWidth(width/3);
        start.setHeight(height/4);
        start.move(width/2f-250,height*3/4f-150,1);
        exit.setImage(this.app.getAssetManager(),"MainMenu/ExitButton.png",true);
        exit.setWidth(width/3);
        exit.setHeight(height/4);
        exit.move(width/2f-250,height*1/4f-150,1);
        mousePos = this.app.getInputManager().getCursorPosition();
     
             
       
        
        
    }
    
    @Override    
    protected void onEnable(){
        this.app.getGuiNode().detachAllChildren();
        this.app.getGuiNode().attachChild(background);
        this.app.getGuiNode().attachChild(start);
        this.app.getGuiNode().attachChild(exit);
        this.app.getInputManager().clearMappings();
        this.app.getInputManager().addMapping("click",new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        this.app.getInputManager().addListener(actionListener, "click");
        
        
        
    }
    @Override
    protected void onDisable(){
        this.app.getGuiNode().detachAllChildren();
        this.app.getInputManager().clearMappings();
        
    }
    @Override
    public void update(float tpf){
       Vector2f mpos = this.app.getInputManager().getCursorPosition();
       if((mpos.x>(width/2f-250)&&(mpos.x<((width/2f-250))+(width/3)))){
           if((mpos.y>(height*1/4-150))&&(mpos.y<(height/4-150+(height/4)))){
                exit.setImage(this.app.getAssetManager(),"MainMenu/ExitButtonClicked.png",true);
           }
           else if((mpos.y)>(height*3/4-150)&&(mpos.y<(height*3/4-150+height/4)))
           {
               start.setImage(this.app.getAssetManager(),"MainMenu/StartButtonClicked.png",true);
           }       
       }
       if((mpos.x<(width/2f-250)||(mpos.x>((width/2f-250))+(width/3)))){
           if((mpos.y<(height*1/4-150))||(mpos.y>(height/4-150+(height/4)))){
                exit.setImage(this.app.getAssetManager(),"MainMenu/ExitButton.png",true);
           }
           else if((mpos.y)<(height*3/4-150)||(mpos.y>(height*3/4-150+height/4)))
           {
               start.setImage(this.app.getAssetManager(),"MainMenu/StartButton.png",true);
           }       
       }
       if(changeState = true){
           
           changeState = false;
           if(state.equals("Game")){
            System.out.println("in");
            Game game = new Game(width,height);
            this.app.getStateManager().attach(game);
            AppState temp = this.app.getStateManager().getState(MainMenu.class);
            this.app.getStateManager().detach(temp);
             
           }
           else if(state.equals("Quit")){
               System.exit(0);
           }
          
           
       }
   
        
    }
    @Override
    protected void cleanup(Application app){
         //no need to implement it because all logic goes to onDisable
        
    }
    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("click") && keyPressed) {
                if((mousePos.x>(width/2f-250)&&(mousePos.x<((width/2f-250))+(width/3)))){
                    if((mousePos.y>(height*1/4-150))&&(mousePos.y<(height/4-150+(height/4)))){
                        
                    state ="Quit";
                    changeState = true;
                    System.out.print("quit");
                    }
                    else if((mousePos.y)>(height*3/4-150)&&(mousePos.y<(height*3/4-150+height/4)))
                    state = "Game";
                        changeState = true;
                        System.out.print("play");
                    {

           }       
       }
                
                
                
            }
        }
    };
    
    
    }
    
