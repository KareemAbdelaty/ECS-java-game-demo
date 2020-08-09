/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.ui.Picture;
import java.util.Random;

/**
 *
 * @author karim
 */
class Game extends BaseAppState {
    private SimpleApplication app;
    private final float width;
    private final float height;
    private final int numOfEntities = 10;
    private final Picture[] pictures = new Picture[numOfEntities];
    private final int[] hasComponents = new int[numOfEntities];
    private final HasNameOrId[] nameOrId = new HasNameOrId[numOfEntities];
    private final CanAttack[] canAttack = new CanAttack[numOfEntities];
    private final HasHealthBar[] hasHealthBar = new HasHealthBar[numOfEntities];
    private final Postion[] postions = new Postion[numOfEntities];
    private final Renderable[] renderable = new Renderable[numOfEntities];
    private final HasInventory[] hasInventory = new HasInventory[numOfEntities];
    private final  HasRespawn[] hasRespawns = new HasRespawn[numOfEntities];
    private final Movement[] canMove = new Movement[numOfEntities];
    private final String[] actionQue = new String[numOfEntities];
    boolean paused = false;
    
    
    public Game(float w,float h){
        width = w;
        height = h;
         for(int i =0;i<numOfEntities;i++){
            nameOrId[i] = new HasNameOrId();
            canAttack[i]= new CanAttack();
            hasHealthBar[i]= new HasHealthBar();
            postions[i] = new Postion();
            renderable[i] = new Renderable();
            hasInventory[i] = new HasInventory();
            hasRespawns[i] = new HasRespawn();
            canMove[i]= new Movement();
            actionQue[i] = "";
            
        }
        
    }
    @Override
    protected void initialize(Application app){
        
        this.app = (SimpleApplication) app;
        this.initEntities();
        this.initAttacks();
        this.initHealthBars();
        this.initNames();
        this.initPostions();
        this.initPlayerSprites();
        this.initEnemies();
        this.initInteractables();
        this.initPictures();
            
 


       
        
        
      
      
      
     
    }
    @Override
    protected void onEnable(){
        this.app.getGuiNode().detachAllChildren();
        this.app.getInputManager().clearMappings();
        this.app.getGuiNode().attachChild(pictures[7]);
        this.app.getGuiNode().attachChild(pictures[8]);
        this.app.getGuiNode().attachChild(pictures[9]);
        this.app.getGuiNode().attachChild(pictures[0]);
        this.app.getGuiNode().attachChild(pictures[1]);
        this.app.getGuiNode().attachChild(pictures[2]);
       this.app.getGuiNode().attachChild(pictures[4]);
       this.app.getGuiNode().attachChild(pictures[5]);
       this.app.getGuiNode().attachChild(pictures[6]);
        this.app.getInputManager().addMapping("moveL", new KeyTrigger(KeyInput.KEY_LEFT));
        this.app.getInputManager().addMapping("moveR", new KeyTrigger(KeyInput.KEY_RIGHT));
        this.app.getInputManager().addMapping("jump", new KeyTrigger(KeyInput.KEY_UP));
        this.app.getInputManager().addMapping("attack", new KeyTrigger(KeyInput.KEY_A));
        this.app.getInputManager().addMapping("pickup", new KeyTrigger(KeyInput.KEY_P));
        this.app.getInputManager().addMapping("duck", new KeyTrigger(KeyInput.KEY_D));
        this.app.getInputManager().addMapping("pause",new KeyTrigger(KeyInput.KEY_ESCAPE));
        this.app.getInputManager().addListener(analogListener,"moveL","moveR");
        this.app.getInputManager().addListener(actionListener, "jump","pickup","duck","pause","attack","moveL","moveR");

        
        
    }
    @Override
    protected void onDisable(){
        this.app.getGuiNode().detachAllChildren();
        this.app.getInputManager().clearMappings();
    }
    @Override
    protected void cleanup(Application app){
        
    }
    @Override
    public void update(float tpf){
        npcManager();
        render();
       
       
         
       
        
    }
    private void initEntities(){
        hasComponents[0]= Component.CAN_ATTACK|Component.HAS_HEALTH_BAR|Component.HAS_INVENTORY|
                Component.HAS_NAME_OR_ID|Component.HAS_POSITION|Component.IS_PLAYER_CHARACTER|Component.RENDERABLE|Component.CAN_MOVE;
        
        hasComponents[1] = Component.HAS_NAME_OR_ID|Component.HAS_POSITION|Component.RENDERABLE|Component.CAN_MOVE;
        hasComponents[2] = Component.HAS_NAME_OR_ID|Component.HAS_POSITION|Component.RENDERABLE|Component.CAN_MOVE|Component.IS_AN_NPC;
        hasComponents[3] = Component.CAN_ATTACK|Component.HAS_HEALTH_BAR|Component.HAS_NAME_OR_ID|Component.HAS_POSITION
                |Component.IS_AN_NPC|Component.RENDERABLE|Component.CAN_MOVE;
        hasComponents[4]= Component.HAS_NAME_OR_ID|Component.RENDERABLE|Component.CAN_MOVE|Component.HAS_POSITION;
        hasComponents[5]= Component.HAS_INVENTORY|Component.HAS_NAME_OR_ID|Component.HAS_POSITION|Component.RENDERABLE|Component.CAN_MOVE;
        hasComponents[6] = Component.HAS_NAME_OR_ID|Component.HAS_POSITION|Component.RENDERABLE|Component.HAS_HEALTH_BAR;
        hasComponents[7] = Component.HAS_POSITION|Component.HAS_NAME_OR_ID|Component.RENDERABLE;
        hasComponents[8] = Component.HAS_POSITION|Component.HAS_NAME_OR_ID|Component.RENDERABLE;
        hasComponents[9] = Component.HAS_POSITION|Component.HAS_NAME_OR_ID|Component.RENDERABLE;
        
        
    }
    private void initPictures()
    {

        pictures[0] = new Picture("player");
        pictures[1] =  new Picture("platform");
        pictures[2] =new Picture("skelton");
        pictures[3] = new Picture("gun");
        pictures[4] = new Picture("enemy");
        pictures[5] = new Picture("chest");
        pictures[6] = new Picture("training dummy");
        pictures[7] = new Picture("background");
        pictures[8] =  new Picture("background");
        pictures[9] = new Picture("background");    
        pictures[0].setImage(this.app.getAssetManager(), renderable[0].otherSprites.get(0),true);
        pictures[0].setWidth(width/12);
        pictures[0].setHeight(height/5);
        pictures[0].move(100,300,1);
        pictures[1].setImage(this.app.getAssetManager(), renderable[1].otherSprites.get(0),true);
        pictures[1].setWidth(width/15);
        pictures[1].setHeight(height/8);
        pictures[1].move(600, 300, 1);
        pictures[2].setImage(this.app.getAssetManager(), renderable[2].walkLeft.get(0),true);
        pictures[2].setWidth(width/15);
        pictures[2].setHeight(height/8);
        pictures[2].move(1000,300,1);
        pictures[3].setImage(this.app.getAssetManager(), renderable[3].otherSprites.get(0),true);
        pictures[3].setWidth(width/20);
        pictures[3].setHeight(height/20);
        pictures[3].move(1500,400,1);
        pictures[4].setImage(this.app.getAssetManager(), renderable[2].walkLeft.get(0),true);
        pictures[4].setWidth(width/15);
        pictures[4].setHeight(height/8);
        pictures[4].move(1600,300,1);
        pictures[5].setImage(this.app.getAssetManager(), renderable[5].otherSprites.get(0),true);
        pictures[5].setWidth(width/15);
        pictures[5].setHeight(height/8);
        pictures[5].move(1200,300,1);
        pictures[6].setImage(this.app.getAssetManager(), renderable[6].otherSprites.get(0),true);
        pictures[6].setWidth(width/15);
        pictures[6].setHeight(height/8);
        pictures[7].setImage(this.app.getAssetManager(), "Background/NewBackground.png", true);
        pictures[7].setHeight(height);
        pictures[7].setWidth(width);
        pictures[7].move(0,0,-1);
        pictures[8].setImage(this.app.getAssetManager(), "Background/NewBackground.png", true);
        pictures[8].setHeight(height);
        pictures[8].setWidth(width);
        pictures[8].move(width,0,-1);
        pictures[9].setImage(this.app.getAssetManager(), "Background/NewBackground.png", true);
        pictures[9].setHeight(height);
        pictures[9].setWidth(width);
        pictures[9].move(2*width,0,-1);   
    }
    private void initPlayerSprites(){
       renderable[0].otherSprites.add("Player/CurrentProtagonist.png");
       renderable[0].walkLeft.add("Player/WalkLeft1.png");
       renderable[0].walkLeft.add("Player/WalkLeft2.png");
       renderable[0].walkLeft.add("Player/WalkLeft3.png");
       renderable[0].walkLeft.add("Player/WalkLeft4.png");
       renderable[0].walkRight.add("Player/WalkRight1.png");
       renderable[0].walkRight.add("Player/WalkRight2.png");
       renderable[0].walkRight.add("Player/WalkRight3.png");
       renderable[0].walkRight.add("Player/WalkRight4.png");
       renderable[0].walkRight.add("Player/WalkRight1.png");
       renderable[0].walkRight.add("Player/WalkRight2.png");
       renderable[0].attack.add("Player/punchleft1.png");
       renderable[0].attack.add("Player/punchleft2.png");
       renderable[0].attack.add("Player/punchright1.png");
       renderable[0].attack.add("Player/punchright2.png");
       renderable[0].otherSprites.add("Player/jumpleft.png");
       renderable[0].otherSprites.add("Player/jumpright.png");
       renderable[0].otherSprites.add("Player/finalducksprite.png");
 /*      renderable[0].equipSprites.add("Player/equipwalkright01.png");
       renderable[0].equipSprites.add("Player/equipwalkright02.png");
       renderable[0].equipSprites.add("Player/equipwalkright01.png");
       renderable[0].equipSprites.add("Player/equipwalkright03.png");
       renderable[0].equipSprites.add("Player/equipwalkright04.png");
       renderable[0].equipSprites.add("Player/equipwalkright05.png");
       renderable[0].equipSprites.add("Player/equipwalkright06.png");
       renderable[0].equipSprites.add("Player/equipleft01.png");
       renderable[0].equipSprites.add("Player/equipleft02.png");
       renderable[0].equipSprites.add("Player/equipleft03.png");
       renderable[0].equipSprites.add("Player/equipleft04.png");
       renderable[0].equipSprites.add("Player/equipleft05.png");
       renderable[0].equipSprites.add("Player/equipleft06.png");
*/
        
    }
    private void initPostions(){
       postions[0].x =0;
       postions[0].y =0;
       postions[1].x =0;
       postions[1].y =0;
       postions[2].x =0;
       postions[2].y =0;
       postions[4].x =0;
       postions[4].y =0;
       postions[5].x =0;
       postions[5].y = 0;
       postions[0].x=100;
       postions[0].y = 300;
       postions[1].x = 600;
       postions[2].y = 300;
       postions[2].x =
       postions[3].x = 1000;
       postions[3].y =300;
       postions[4].x =1500;
       postions[4].y = 400;
       postions[5].x = 1600;
       postions[5].y = 300;
       postions[6].x =1200;
       postions[6].y = 300;
        
    }
    private void initHealthBars(){
        hasHealthBar[0].health =3;
       hasHealthBar[2].health =1;
       hasHealthBar[4].health = 30;
       hasHealthBar[6].health =1;
    }
    private void initNames(){
       nameOrId[0].name = "player";
       nameOrId[1].name = "platform";
       nameOrId[2].name = "skelton";
       nameOrId[3].name = "gun";
       nameOrId[4].name = "second enemy";
       nameOrId[5].name = "Chest";
       nameOrId[6].name = "Training Dummy";
       nameOrId[7].name = "background";
       nameOrId[8].name = "background";
       nameOrId[9].name = "background";
    }
    private void initAttacks(){
       canAttack[0].attackStrength =1;
       canAttack[2].attackStrength =0;
       canAttack[4].attackStrength = 10;
        
    }
    private void initInteractables(){
        renderable[5].otherSprites.add("Interactables/Chest.png");
        renderable[5].otherSprites.add("Interactables/openchest.png");
        renderable[6].otherSprites.add("Interactables/trainingdummy.png");
        renderable[1].otherSprites.add("Tiles/PlatformTile.png");
        renderable[3].otherSprites.add("Player/weapon01.png");
        renderable[3].otherSprites.add("weaponleft.png");    
        renderable[7].otherSprites.add("Background/NewBackground.png");   
        renderable[8].otherSprites.add("Background/NewBackground.png");   
        renderable[9].otherSprites.add("Background/NewBackground.png");   
    }
    public void initEnemies(){
        renderable[2].walkRight.add("Enemies/Skelton/SkeletonRight1.png");
        renderable[2].walkRight.add("Enemies/Skelton/SkeletonRight2.png");
        renderable[2].walkRight.add("Enemies/Skelton/SkeletonRight3.png");
        renderable[2].walkRight.add("Enemies/Skelton/SkeletonRight4.png");
        renderable[2].walkRight.add("Enemies/Skelton/SkeletonRight5.png");
        renderable[2].walkRight.add("Enemies/Skelton/SkeletonRight6.png");
        renderable[2].walkRight.add("Enemies/Skelton/SkeletonRight7.png");
        renderable[2].walkLeft.add("Enemies/Skelton/SkeletonLeft1.png");
        renderable[2].walkLeft.add("Enemies/Skelton/SkeletonLeft2.png");
        renderable[2].walkLeft.add("Enemies/Skelton/SkeletonLeft3.png");
        renderable[2].walkLeft.add("Enemies/Skelton/SkeletonLeft4.png");
        renderable[2].walkLeft.add("Enemies/Skelton/SkeletonLeft5.png");
        renderable[2].walkLeft.add("Enemies/Skelton/SkeletonLeft6.png");
        renderable[2].walkLeft.add("Enemies/Skelton/SkeletonLeft7.png");
        renderable[2].otherSprites.add("Enemies/Skelton/SkeletonRight1.png");
        renderable[4].otherSprites.add("Enemies/ExplodingPanda/explodingPanda1.png");
        renderable[4].otherSprites.add("Enemies/ExplodingPanda/explodingPanda2.png");
        renderable[4].otherSprites.add("Enemies/ExplodingPanda/explodingPanda3.png");
        renderable[4].otherSprites.add("Enemies/ExplodingPanda/explodingPanda4.png");
        renderable[4].otherSprites.add("Enemies/ExplodingPanda/explodingPanda5.png");
        renderable[4].otherSprites.add("Enemies/ExplodingPanda/explodingPanda6.png");
        renderable[4].otherSprites.add("Enemies/ExplodingPanda/explodingPanda7.png");
        renderable[4].otherSprites.add("Enemies/ExplodingPanda/explodingPanda8.png");
        renderable[4].otherSprites.add("Enemies/ExplodingPanda/explodingPanda9.png");
        renderable[4].otherSprites.add("Enemies/ExplodingPanda/explodingPanda10.png");
        renderable[4].otherSprites.add("Enemies/ExplodingPanda/explodingPanda12.png");
        renderable[4].otherSprites.add("Enemies/ExplodingPanda/explodingPanda13.png");
        renderable[4].otherSprites.add("Enemies/ExplodingPanda/explodingPanda14.png");
        renderable[4].otherSprites.add("Enemies/ExplodingPanda/explodingPanda15.png");
        renderable[4].otherSprites.add("Enemies/ExplodingPanda/explodingPanda16.png");
        
        
    }
    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
        if(!keyPressed && name.equals("moveL")){

            
            renderable[0].state = "stationary";
            System.out.println(renderable[0].state );
            
        }
        if(!keyPressed &&  name.equals("moveR")){

            
            
            renderable[0].state = "stationary";
            System.out.println(renderable[0].state );
            
        }
         
    };

  
    
    
            };
    public void movmentManager(String name,int direction){
        int index =0;
        boolean moveable;
        for(int i=0 ;i<nameOrId.length;i++){
            if(nameOrId[i].name.equals(name)){
                index =i;
            }
        
        }
        if((hasComponents[index]&Component.CAN_MOVE)==Component.CAN_MOVE){
            if(direction>0){
                postions[index].x += canMove[index].hSpeed;
            }
            else if(direction<0){
                postions[index].x -= canMove[index].hSpeed;
            }
        }
        
        
        
    }

    public void npcManager(){
                   int[] randomWalk = new int[3];
                   randomWalk[0] =0;
                   randomWalk[1] = 1;
                   randomWalk[2] = -1;
                   Random  r = new Random();
        for(int i =0; i<numOfEntities;i++){
            if((hasComponents[i]&Component.IS_AN_NPC) == Component.IS_AN_NPC){
                if(nameOrId[i].name.equals("skelton")){
                    int index  = r.nextInt(3);
                    if(randomWalk[index]>0){
                        renderable[i].state ="walkR";
                        postions[i].x += canMove[i].hSpeed;
                    }
                    else if(randomWalk[index]<0){
                        renderable[i].state = "walkL";
                        postions[i].x -= canMove[i].hSpeed;
                    }
                    if(Math.abs(postions[i].x-postions[0].x)<=width/80){
                        renderable[0].state = "attack";
                    }

                }
            }
                    
            
                }  

        
    }
    public void save(){
        
    }
    public void load(){
        
    }
    public void render(){
        for(int i =0;i<pictures.length;i++){
            switch (renderable[i].state) {
                case "stationary":
                 pictures[i].setImage(this.app.getAssetManager(),renderable[i].otherSprites.get(0) ,true);
                 pictures[i].setLocalTranslation(postions[i].x, postions[i].y, 1);
                    break;
                case "walkR":
                    if(renderable[i].nextFrame>=renderable[i].walkRight.size()){
                        renderable[i].nextFrame = 0;
                    }
                    pictures[i].setImage(this.app.getAssetManager(), renderable[i].walkRight.get(renderable[i].nextFrame),true);
                    pictures[i].setLocalTranslation(postions[i].x, postions[i].y, 1);
                    renderable[i].nextFrame  +=1;
                    
                    break;
                case "walkL":
                    
                      if(renderable[i].nextFrame>=renderable[i].walkLeft.size()){
                        renderable[i].nextFrame = 0;
                    }
                      
                    pictures[i].setImage(this.app.getAssetManager(), renderable[i].walkLeft.get(renderable[i].nextFrame),true);
                    renderable[i].nextFrame  +=1;
                    pictures[i].setLocalTranslation(postions[i].x, postions[i].y, 1);
                  
                    break;
                case "attack":
                                        if(renderable[i].nextFrame>=renderable[i].attack.size()){
                        renderable[i].nextFrame = 0;
                    }
                    pictures[i].setImage(this.app.getAssetManager(), renderable[i].attack.get(renderable[i].nextFrame),true);
                    renderable[i].nextFrame  +=1;
                    pictures[i].setLocalTranslation(postions[i].x, postions[i].y, 1);

                    break;
                case "other":
                                        if(renderable[i].nextFrame>=renderable[i].otherSprites.size()){
                        renderable[i].nextFrame = 0;
                    }
                    pictures[i].setImage(this.app.getAssetManager(), renderable[i].otherSprites.get(renderable[i].nextFrame),true);
                    renderable[i].nextFrame  +=1;
                    pictures[i].setLocalTranslation(postions[i].x, postions[i].y, 1);

                    break;
                case "equip":
                                        if(renderable[i].nextFrame>=renderable[i].equipSprites.size()){
                        renderable[i].nextFrame = 0;
                    }
                    pictures[i].setImage(this.app.getAssetManager(), renderable[i].equipSprites.get(renderable[i].nextFrame),true);
                    renderable[i].nextFrame  +=1;
                    pictures[i].setLocalTranslation(postions[i].x, postions[i].y, 1);

                    break;
                default:
                    break;
            }
            if(pictures[i].getName().equals("background")){
            pictures[i].setLocalTranslation(postions[i].x, postions[i].y,-1);
            }
            else{
            pictures[i].setLocalTranslation(postions[i].x, postions[i].y,1);
            }
            
           
        
        
        
    }
    }
    private final AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float value, float tpf) {
             if(name.equals("moveL")){
            postions[0].x -= canMove[0].hSpeed;
            renderable[0].state = "walkL";
              
          }
          else if(!name.equals("moveR")){
          } else {
              postions[0].x += canMove[0].hSpeed;
              renderable[0].state = "walkR";
             }

        }
    };
    
            }
    
    
    
    
    

